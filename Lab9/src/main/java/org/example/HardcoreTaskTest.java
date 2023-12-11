package org.example;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.*;


import java.util.ArrayList;

public class HardcoreTaskTest {
    class TestValue {
        final private String input;
        final private String result;

        public TestValue(String input, String result) {
            this.input = input;
            this.result = result;
        }
    }

    private WebDriver driver;
    TestValue numberOfInstances= new TestValue("4", "4");
    TestValue vmClass = new TestValue("Regular", "Provisioning model: Regular");
    TestValue os = new TestValue("Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)", "Operating System / Software: Free");
    TestValue instanceType = new TestValue("n1-standard-8 (vCPUs: 8, RAM: 30GB)", "Instance type: n1-standard-8\nCommitted Use Discount applied");
    TestValue region = new TestValue("Frankfurt (europe-west3)", "Region: Frankfurt");
    TestValue localSSD = new TestValue("2x375 GB", "Local SSD: 2x375 GiB\nCommitted Use Discount applied");
    TestValue committedUsage = new TestValue("1 Year", "Commitment term: 1 Year");
    TestValue series = new TestValue("N1", "N1");
    TestValue gpuType = new TestValue("NVIDIA Tesla V100", "NVIDIA Tesla V100");
    TestValue gpuCount = new TestValue("1", "1");
    GoogleCloudCalculatorPage calculatorPage;
    EmailPage generateTmpEmail;
    ArrayList<String> tabs;

    @BeforeClass
    public void driverSetup() {
        driver = new EdgeDriver();
        GoogleCloudCalculatorPage hurtMePlentyPage = new GoogleCloudCalculatorPage(driver);
        hurtMePlentyPage.openPage();
        calculatorPage = hurtMePlentyPage.search();
        calculatorPage.clickComputeEngineTab();
        calculatorPage.enterNumberOfInstances(numberOfInstances.input);
        calculatorPage.enterOS(os.input);
        calculatorPage.enterVMClass(vmClass.input);
        calculatorPage.enterSeries(series.input);
        calculatorPage.enterMachineType(instanceType.input);
        calculatorPage.setCheckedAddGPUCheckbox();
        calculatorPage.enterGPUType(gpuType.input);
        calculatorPage.enterGPUCount(gpuCount.input);
        calculatorPage.enterLocalSSD(localSSD.input);
        calculatorPage.enterDatacenterLocation(region.input);
        calculatorPage.enterCommittedUsage(committedUsage.input);
        calculatorPage.clickButtonAddToEstimte();
        calculatorPage.sendEstimateToEmailButtonClick();
    }

    @Test
    public void Emails(){

        ((JavascriptExecutor) driver).executeScript("window.open()");
        tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        generateTmpEmail = new EmailPage(driver);
        generateTmpEmail.openPage();
        generateTmpEmail.copyEmailButtonClick();

        driver.switchTo().window(tabs.get(0));
        calculatorPage.pasteEmailToSendEstimateToEmailForm();
        calculatorPage.setSendEstimateToEmailPopupButtonClick();
    }

    @Test
    public void compareEstimateCost() {
        String calculatorEstimateCost = calculatorPage.getResultCalculatorEstimateCost();
        calculatorEstimateCost = calculatorEstimateCost.substring(calculatorEstimateCost.indexOf("USD") + new String("USD").length(), calculatorEstimateCost.indexOf("per")).trim();
        String emailEstimateCost;

        driver.switchTo().window(tabs.get(1));
        EmailPage emailListPage = generateTmpEmail.checkEmailButtonClick();
        emailListPage.openEmail();
        emailEstimateCost = emailListPage.getEstimateEmailCost();
        emailEstimateCost = emailEstimateCost.substring(emailEstimateCost.indexOf("USD") + new String("USD").length()).trim();
        Assert.assertEquals(emailEstimateCost, calculatorEstimateCost);
    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
