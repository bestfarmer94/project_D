package com.sparta.project_d.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class Selenium {
    private WebDriver driver;
    private final String url = "https://hanghae99.spartacodingclub.kr/v2/checks";
    @Value("${hangId}")
    private String id;
    @Value("${hangPwd}")
    private String pwd;

    @Scheduled(cron = "30 * * * * *")
    public void checkOut() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(options);
        driver.get(url);
        driver.switchTo().alert().accept();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(1000);
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
        Thread.sleep(100);
        driver.findElement(By.className("css-307iw5")).click();
        driver.close();
        driver.quit();
    }

    @Scheduled(cron = "5 0 5 * * *")
    public void checkIn() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(options);
        driver.get(url);
        driver.switchTo().alert().accept();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(1000);
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
        Thread.sleep(100);
        driver.findElement(By.className("css-13067o3")).click();

        driver.close();
        driver.quit();
    }
}
