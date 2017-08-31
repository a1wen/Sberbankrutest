package ru.sbt;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by a1wen on 30.08.2017.
 */
public class SberbankruTest {
    WebDriver driver;
    WebDriverWait wait;
    private String expectedRegion = "Нижегородская область";

    MainPage mainpage;
    @Before
    public void setUp(){
        System.setProperty("webdriver.ie.driver", "C:\\Users\\a1wen\\Desktop\\example\\sbetTest\\drivers\\IEDriverServer.exe");
        driver = new InternetExplorerDriver();
        mainpage = new MainPage(driver);
        driver.get("sberbank.ru");
    }
    @Test
    public void testCheckRegion(){
        mainpage.regionListClick();
        mainpage.inputAndSelectRegion("ниже");
        mainpage.assertRegion(expectedRegion);
        mainpage.scrollToFooter();
        mainpage.assertSocialButton();
        /*WebElement searchRegion = driver.findElement(By.xpath("//input[@class='kit-input__control']"));
        wait.until(ExpectedConditions.visibilityOf(searchRegion));
        searchRegion.click();
        searchRegion.clear();
        searchRegion.sendKeys("ниж");*/
    }
    @After
    public void tearDown(){
        if(driver != null)
            driver.quit();
    }
}
