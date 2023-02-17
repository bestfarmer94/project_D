package com.sparta.project_d.util;

import com.sparta.project_d.Enum.PriceType;
import com.sparta.project_d.dto.ItemsDto;
import com.sparta.project_d.dto.ItemsListDto;
import com.sparta.project_d.open.service.OpenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class Scheduler {
//
//    private final OpenService openService;
//
//    @Scheduled(cron = "0 1 0 * * *")
//    public void updateItems() throws InterruptedException {
//        log.info("가격 업데이트 실행");
//        ItemsListDto itemsListDto = openService.searchItems(PriceType.YDayAvgPrice);
//    }
//}
