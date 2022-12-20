package org.example.framework.steps;

import io.cucumber.java.bg.И;
import org.example.framework.managers.DriverManager;
import org.example.framework.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



public class CommonSteps extends BasePage {

    protected final DriverManager driverManager = DriverManager.getDriverManager();


    @И("^Нажать на кнопку \"([^\"]*)\"$")
    public void pressButton(String buttonName) {
        WebElement button = driverManager.getDriver()
                .findElement(By.xpath("//button/span[text()='" + buttonName +"']"));
        waitUntilClickable(button);
        button.click();
    }



}
