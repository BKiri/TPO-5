package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class OnlinerTest {

    private WebDriver driver;
    private OnlinerPage onlinerPage;


    @BeforeClass
    public void setUp() {
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        onlinerPage = new OnlinerPage(driver);
        onlinerPage.openPage();
    }

    @Test
    public void testOnliner() {
        onlinerPage.choosePhone();
        onlinerPage.getReviews();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
