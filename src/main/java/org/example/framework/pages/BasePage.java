package org.example.framework.pages;

import org.example.framework.managers.DriverManager;
import org.example.framework.managers.PageManager;
import org.example.framework.managers.TestPropManager;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {
    protected final DriverManager driverManager = DriverManager.getDriverManager();
    protected PageManager pageManager = new PageManager().getPageManager();
    protected Actions action = new Actions(driverManager.getDriver());
    protected JavascriptExecutor js = (JavascriptExecutor) driverManager.getDriver();
    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), 20, 10000);
    private final TestPropManager props = TestPropManager.getTestPropManager();

    public BasePage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    protected WebElement waitUntilClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement waitUntilVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

//    protected WebElement waitUntilLocated(WebElement element) {
//        return wait.until(ExpectedConditions.presenceOfElementLocated(element));
//    }

    protected WebElement scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }

    protected void fillInputField(WebElement field, String value) {
       // scrollToElementJs(field);
        waitUntilClickable(field).clear();
        field.click();
        field.sendKeys(value);
    }

    protected WebElement checkList(String name, List<WebElement> list){
        for (WebElement element : list) {
            waitUntilVisible(element);
            if (element.getText().contains(name)){
                return element;
            }
        }
        Assert.fail("Элемент с наименованием '" + name + "' отсутствует " +
                "на странице ");
        return null;
    }


}
