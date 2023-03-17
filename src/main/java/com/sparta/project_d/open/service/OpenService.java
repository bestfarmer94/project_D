package com.sparta.project_d.open.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.project_d.Enum.PriceType;
import com.sparta.project_d.dto.ItemsDto;
import com.sparta.project_d.Enum.Category;
import com.sparta.project_d.dto.ItemsListDto;
import com.sparta.project_d.entity.Materials;
import com.sparta.project_d.entity.Products;
import com.sparta.project_d.service.ItemService;
import com.sparta.project_d.util.ItemChecker;
import com.sparta.project_d.open.dto.BodyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpenService {
    //
    @Value("${jwt.secret.key}")
    private String secretKey;
    private final String AUTHORIZATION_HEADER = "Authorization";
    private final String BEARER_PREFIX = "Bearer ";
    private final ItemChecker itemChecker;
    private final ItemService itemService;

    private final StringRedisTemplate redisTemplate;

    @Transactional
    public List<ItemsDto> searchRedis() throws JsonProcessingException, InterruptedException {
        String key = "itemsDtoList";
        ObjectMapper objectMapper = new ObjectMapper();

        if (redisTemplate.hasKey(key)) {
            return objectMapper.readValue(redisTemplate.opsForValue().get(key), new TypeReference<List<ItemsDto>>() {});
        }

        List<ItemsDto> itemsDtoList = searchItems(PriceType.CurrentMinPrice).getMaterialsDtoList();
        redisTemplate.opsForValue().set(key, objectMapper.writeValueAsString(itemsDtoList), 3, TimeUnit.SECONDS);
        return itemsDtoList;
    }

    @Transactional
    public ItemsListDto searchItems(PriceType priceType) throws InterruptedException {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add(AUTHORIZATION_HEADER, BEARER_PREFIX + secretKey);
        List<ItemsDto> materialsDtoList = new ArrayList<>();
        List<ItemsDto> productsDtoList = new ArrayList<>();

        for (Category value : Category.values()) {
            TimeUnit.MILLISECONDS.sleep(200);
            if (priceType == PriceType.CurrentMinPrice && value == Category.융화) {
                continue;
            }

            HttpEntity<BodyDto> requestEntity = new HttpEntity<BodyDto>(new BodyDto(value), headers);
            ResponseEntity<String> responseEntity = rest.exchange("https://developer-lostark.game.onstove.com/markets/items", HttpMethod.POST, requestEntity, String.class);

            HttpStatus httpStatus = responseEntity.getStatusCode();
            int status = httpStatus.value();
            log.info("LOSTARK API Status Code : " + status);

            String response = responseEntity.getBody();
            fromJSONtoItems(response,
                    value == Category.융화 ? productsDtoList : materialsDtoList
                    , priceType);
        }

        return new ItemsListDto(materialsDtoList, productsDtoList, null);
    }

    public void fromJSONtoItems(String response, List<ItemsDto> list, PriceType priceType) {
        JSONObject rjson = new JSONObject(response);
        JSONArray items = rjson.getJSONArray("Items");

        for (int i = 0; i < items.length(); i++) {
            JSONObject itemJson = items.getJSONObject(i);
            String itemName = itemJson.getString("Name");
            if (itemChecker.isItemNeeded(itemName)) {
                list.add(new ItemsDto(itemJson, priceType, itemChecker.getCategory(itemName)));
            }
        }
    }

    @Transactional
    public void updateItems() throws InterruptedException {
        log.info("가격 업데이트 실행");
        ItemsListDto itemsListDto = searchItems(PriceType.YDayAvgPrice);
        List<ItemsDto> materialsDtoList = itemsListDto.getMaterialsDtoList();
        List<ItemsDto> productsDtoList = itemsListDto.getProductsDtoList();

        List<Materials> materialsList = itemService.getMaterials();
        for (int i = 0; i < materialsList.size(); i++) {
            materialsList.get(i).update(materialsDtoList.get(i));
        }

        List<Products> productsList = itemService.getProducts();
        for (int i = 0; i < productsList.size(); i++) {
            productsList.get(i).update(productsDtoList.get(i));
        }

//        for (ItemsDto itemsDto : materialsDtoList) {
//            materialsList.add(new Materials(itemsDto));
//        }
//        for (ItemsDto itemsDto : productsDtoList) {
//            productsList.add(new Products(itemsDto));
//        }
//        materialsRepository.saveAll(materialsList);
//        productsRepository.saveAll(productsList);
    }
}
