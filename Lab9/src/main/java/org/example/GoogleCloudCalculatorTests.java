package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class GoogleCloudCalculatorTests {

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

    @BeforeClass
    public void driverSetup() {
        driver = new EdgeDriver();
        GoogleCloudCalculatorPage hurtMePlentyPage = new GoogleCloudCalculatorPage(driver);
        hurtMePlentyPage.openPage();
        calculatorPage = hurtMePlentyPage.search();
        calculatorPage.clickComputeEngineTab();
    }

    @Test
    public void enterValues() {
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
    }

    @Test
    public void compareValues() {
        Assert.assertEquals(calculatorPage.getResultVM(), vmClass.result);
        Assert.assertEquals(calculatorPage.getResultOS(), os.result);
        Assert.assertEquals(calculatorPage.getResultInstanceType(), instanceType.result);
        Assert.assertEquals(calculatorPage.getResultRegion(), region.result);
        Assert.assertEquals(calculatorPage.getResultLocalSSD(), localSSD.result);
        Assert.assertEquals(calculatorPage.getResultCommitmentTerm(), committedUsage.result);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
