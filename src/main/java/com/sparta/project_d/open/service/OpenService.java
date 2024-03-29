package com.sparta.project_d.open.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.project_d.Enum.PriceType;
import com.sparta.project_d.dto.ItemsDto;
import com.sparta.project_d.Enum.Category;
import com.sparta.project_d.dto.ItemsListDto;
import com.sparta.project_d.dto.ResponseDto;
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

    @Value("${jwt.secret.key}")
    private String secretKey;
    private final String AUTHORIZATION_HEADER = "Authorization";
    private final String BEARER_PREFIX = "Bearer ";
    private final ItemChecker itemChecker;
    private final ItemService itemService;
    private final StringRedisTemplate redisTemplate;


    @Transactional
    public ResponseDto<List<ItemsDto>> searchRedis() throws JsonProcessingException, InterruptedException {
        String key = "itemsDtoList";
        ObjectMapper objectMapper = new ObjectMapper();

        // Redis Data 존재하는지. (3초 이내에 누군가 검색했는지.)
        if (redisTemplate.hasKey(key)) {
            log.info("Redis data");
            return ResponseDto.success(objectMapper.readValue(redisTemplate.opsForValue().get(key), new TypeReference<List<ItemsDto>>() {}));
        }

        List<ItemsDto> itemsDtoList = searchItems(PriceType.CurrentMinPrice).getMaterialsDtoList();
        redisTemplate.opsForValue().set(key, objectMapper.writeValueAsString(itemsDtoList), 3, TimeUnit.SECONDS);
        log.info("Api data");
        return ResponseDto.success(itemsDtoList);
    }


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
            ResponseEntity<String> responseEntity = rest.exchange("https://developer-lostark.game.onstove.com/markets/items",
                    HttpMethod.POST, requestEntity, String.class);

            HttpStatus httpStatus = responseEntity.getStatusCode();
            int status = httpStatus.value();
            log.info("LOSTARK API Status Code : " + status);

            String response = responseEntity.getBody();
            fromJSONtoItems(response,
                    value == Category.융화 ? productsDtoList : materialsDtoList
                    , priceType);
        }

        return ItemsListDto.of(materialsDtoList, productsDtoList, null);
    }


    public void fromJSONtoItems(String response, List<ItemsDto> list, PriceType priceType) {
        JSONObject rjson = new JSONObject(response);
        JSONArray items = rjson.getJSONArray("Items");

        for (int i = 0; i < items.length(); i++) {
            JSONObject itemJson = items.getJSONObject(i);
            String itemName = itemJson.getString("Name");
            if (itemChecker.isItemNeeded(itemName)) {
                list.add(ItemsDto.ofJson(itemJson, priceType, itemChecker.getCategory(itemName)));
            }
        }
    }


    @Scheduled(cron = "0 10 0 * * *")
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
    }
}
