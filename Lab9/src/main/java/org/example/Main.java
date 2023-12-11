package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.List;
import java.util.Set;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Hello world!");
        WebDriver driver=new EdgeDriver();
        driver.get("http://catalog.onliner.by");
        sleep(2000);
        driver.quit();
    }
}