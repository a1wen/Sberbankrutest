package ru.sbt;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by a1wen on 30.08.2017.
 */
public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public MainPage(WebDriver webDriver){
        this.driver = webDriver;
        this.wait = new WebDriverWait(driver,40, 500);
        PageFactory.initElements(driver, this);
    }

    private WebElement waiter(final WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    @FindBy(xpath = "//div[@class='region-list']//a")
    public WebElement regionSelector;

    @FindBy(xpath = "//div[@class='kit-modal-body']//input[@class='kit-input__control']")
    public WebElement regionInput;

    @FindBy(xpath = "//div[@class = 'footer-info']")
    public WebElement footer;

    @FindBy(xpath = "//div[@class = 'social__wrapper']")
    public WebElement socialButtons;

    public void regionListClick(){
        waiter(this.regionSelector).click();
    }

    public void inputAndSelectRegion(String region){
        WebElement input = waiter(this.regionInput);
        input.sendKeys(region);
        Action builder = new Actions(driver)
                .moveToElement(this.regionInput)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER)
                .build();
        builder.perform();
        this.driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
    }

    public void scrollToFooter(){
        waiter(this.footer).isEnabled();
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",footer);
    }

    public String getRegionValue(){
        WebElement region = waiter(this.regionSelector);
        return region.getAttribute("title");
    }

    public void assertRegion(String expectedRegion){
        assertEquals(getRegionValue(),expectedRegion);
    }

    public void assertSocialButton(){
        assertTrue(waiter(this.socialButtons).isEnabled());
    }

}
