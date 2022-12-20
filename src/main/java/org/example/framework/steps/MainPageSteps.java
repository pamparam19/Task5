package org.example.framework.steps;

import io.cucumber.java.bg.И;
import org.example.framework.managers.PageManager;


public class MainPageSteps {

    PageManager pageManager = new PageManager().getPageManager();

    @И("^Выбрать раздел меню \"([^\"]*)\"$")
    public void chooseMenuItem(String itemName) {
        pageManager.getMainPage().chooseMenuItem(itemName);
    }

    @И("^Выбрать категорию товаров \"([^\"]*)\"$")
    public void chooseItemCategory(String categoryName) {
        pageManager.getMainPage().chooseLaptopCategory(categoryName);
    }
}
