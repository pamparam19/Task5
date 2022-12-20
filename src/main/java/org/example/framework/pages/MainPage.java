package org.example.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends BasePage{

    @FindBy(xpath="//input[@aria-label='Поиск']")
    private WebElement searchInput;

    @FindBy(xpath="//div[contains(@class, 'Catalog_content')]/ul/a/div")
    private List<WebElement> mainMenu;

    @FindBy(xpath="//div[contains(@class, 'Grid_row') and contains (@class, 'scopeItems')]/div[contains(@class,'laptop')]//p[contains(@class,'CardCategory_title')]")
    private List<WebElement> laptopCategories;

    public MainPage chooseMenuItem(String itemName) {
        waitUntilClickable(checkList(itemName,mainMenu)).click();
        return pageManager.getMainPage();
    }

    public CategoryPage chooseLaptopCategory(String categoryName){
        waitUntilClickable(checkList(categoryName,laptopCategories)).click();
        return pageManager.getCategoryPage();
    }




}
