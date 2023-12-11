package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.lang.Thread.sleep;


public class OnlinerPage {

    private WebDriver driver;

    public OnlinerPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        //driver.get("https://catalog.onliner.by/");
        driver.get("https://catalog.onliner.by/mobile");
    }

    public void choosePhone(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement phoneLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='js-product-title-link']")));
        phoneLink.click();
    }
    public void getReviews() {
        WebElement reviewsTab = driver.findElement(By.xpath("//span[contains(text(), 'отзыв')]"));
        reviewsTab.click();
    }
}
