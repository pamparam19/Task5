package org.example.framework.steps;

import io.cucumber.java.bg.И;
import org.example.framework.managers.PageManager;
import org.example.framework.utils.SharedData;

public class CategoryPageSteps extends SharedData {

    private static SharedData data;

    public CategoryPageSteps(SharedData data) {
        this.data = data;
    }

    PageManager pageManager = new PageManager().getPageManager();

    @И("^В фильтре \"([^\"]*)\" ввести \"([^\"]*)\" значение \"([^\"]*)\"$")
    public void sendFilterValue(String filName, String range, String value) {
        pageManager.getCategoryPage().setFilterRange(filName,range,value);
    }

    @И("^Проверить появление заголовка \"([^\"]*)\"$")
    public void checkPageTitle(String title) {
        pageManager.getCategoryPage().checkCategoryPageOpen(title);
    }

    @И("^Отметить чекбокс \"([^\"]*)\"$")
    public void clickCheckbox (String brand) {
        pageManager.getCategoryPage().selectCheckbox(brand);
    }

    @И("^Проверить завершение поиска по категории \"([^\"]*)\"$")
    public void checkFilterPerf (String filter) {
        pageManager.getCategoryPage().checkOne(filter);
    }

    @И("^Проверить, что фильтр завершил работу и найден \"([^\"]*)\"$")
    public void checkFilterFound (String prod) {
        pageManager.getCategoryPage().checkProdFound(prod);
    }

    @И("^Проверить, что количество товаров в поисковой выдаче не более \"([^\"]*)\"$")
    public void checkNumProd (int number) {
        pageManager.getCategoryPage().checkNumberOfProd(number);
    }

    @И("^Сохранить наименование товара в строке \"([^\"]*)\"$")
    public void saveProdName (int number) {
        data.productName =  pageManager.getCategoryPage().savePrName(number);
    }

    @И("^Ввести в поисковую строку сохраненное наименование товара$")
    public void searchInput () {
       pageManager.getCategoryPage().fillInSearch(data.productName);
    }

    @И("^Проверить, что наименование товара соответствует сохраненному ранее$")
    public void checkItemName() {
        pageManager.getCategoryPage().checkItemName(data.productName);
    }


}
