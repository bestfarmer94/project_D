package com.sparta.project_d.selenium;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class Selenium {
    private WebDriver driver;
    private final String url = "https://hanghae99.spartacodingclub.kr/v2/checks";
    @Value("${hangId}")
    private String id;
    @Value("${hangPwd}")
    private String pwd;

    @Scheduled(cron = "30 59 4 * * *")
    public void checkOut() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(2000);
        driver.switchTo().alert().accept();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(2000);
        driver.findElement(By.className("css-6xo06l")).click();
        Thread.sleep(100);
        driver.findElement(By.className("css-1ff1fok")).sendKeys(id);
        Thread.sleep(100);
        driver.findElement(By.className("css-zcbjwm")).click();
        Thread.sleep(100);
        driver.findElement(By.xpath("//*[@id=\"__next\"]/section/section/section[3]/div[3]/input")).sendKeys(pwd);
        Thread.sleep(100);
        driver.findElement(By.className("css-17b9hty")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(2000);
        driver.findElement(By.className("css-307iw5")).click();
        System.out.println("출석 종료");
        driver.close();
        driver.quit();
    }

    @Scheduled(cron = "0 0 5 * * *")
    public void checkIn() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(2000);
        driver.switchTo().alert().accept();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(2000);
        driver.findElement(By.className("css-6xo06l")).click();
        Thread.sleep(100);
        driver.findElement(By.className("css-1ff1fok")).sendKeys(id);
        Thread.sleep(100);
        driver.findElement(By.className("css-zcbjwm")).click();
        Thread.sleep(100);
        driver.findElement(By.xpath("//*[@id=\"__next\"]/section/section/section[3]/div[3]/input")).sendKeys(pwd);
        Thread.sleep(100);
        driver.findElement(By.className("css-17b9hty")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(2000);
        driver.findElement(By.className("css-13067o3")).click();
        System.out.println("출석 시작");
        driver.close();
        driver.quit();
    }

//    @Scheduled(cron = "0 1 0 * * *")
//    public void updateItems() throws InterruptedException {
//        openService.updateItems();
//    }
}
