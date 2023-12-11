package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class PastebinTest {

    private WebDriver driver;
    private PastebinPage pastebinPage;

    @BeforeClass
    public void setUp() {
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        pastebinPage = new PastebinPage(driver);

    }

    @Test
    public void testCreateNewPaste() throws InterruptedException {
        pastebinPage.openPage();
        pastebinPage.createNewPaste("Hello from WebDriver", "10 Minutes", "helloweb","None");
        // Добавьте дополнительные проверки по необходимости
        sleep(10000);
    }


    @Test
    public void testCreateNewGitPaste() throws InterruptedException {
        pastebinPage.openPage();
        pastebinPage.createNewPaste(
                "git config --global user.name  \"New Sheriff in Town\"\n" +
                        "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
                        "git push origin master --force",

                "10 Minutes",
                "how to gain dominance among developers",
                "Bash"
        );
        sleep(10000);
        // Add assertions as needed to check page title, syntax highlighting, and code
    }

    @AfterClass
    public void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }
}
