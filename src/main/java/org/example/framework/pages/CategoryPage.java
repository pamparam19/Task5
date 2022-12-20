package org.example.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CategoryPage extends BasePage{

    @FindBy(xpath="//h1")
    private WebElement title;

    @FindBy(xpath="//span[contains(@class, 'FilterTags_title')]")
    private List<WebElement> pickedFilters;


    @FindBy(xpath="//span[contains(@class, 'PageTitle_count')]")
    private WebElement filterInd;

    @FindBy(xpath = "//div[contains(@class,'Card_row')]")
    private List<WebElement> productList;

    @FindBy(xpath = "//h6[contains(@class,'CardText')]")
    private List<WebElement> productTitlesList;

    @FindBy(xpath = "//input[@aria-label='Поиск']")
    private WebElement searchInput;

    public CategoryPage checkCategoryPageOpen(String titleToBeChecked){
        wait.until(ExpectedConditions.titleContains(titleToBeChecked));
        wait.until(ExpectedConditions.textToBe(By.xpath("//h1"),titleToBeChecked));
        Assert.assertEquals("Заголовок отсутствует или не соответствует требуемому",
                titleToBeChecked,title.getText());
        return this;
    }

    public CategoryPage setFilterRange(String filterName, String param, String value){

        WebElement filterRange = driverManager.getDriver()
                .findElement(By.xpath("//h5//span[text()='" + filterName
                        + "']/ancestor::div[contains(@class,'Dropdown')]" +
                        "/following-sibling::*//input[@name='"+ param + "']"));
        waitUntilVisible(filterRange).clear();
        filterRange.sendKeys(value);
        return pageManager.getCategoryPage();
    }

    public CategoryPage selectCheckbox(String brand){
        WebElement checkbox1 = driverManager.getDriver()
                .findElement(By.xpath("//input[@type='checkbox' and contains(@id,'" + brand + "')]"));
        WebElement checkbox = driverManager.getDriver()
                        .findElement(By.xpath("//label[contains(@class, 'Checkbox') and text()='"+brand+"']"));
        waitUntilClickable(checkbox);
        checkbox.click();
        Assert.assertTrue("Чекбокс " + brand +" не отмечен", checkbox1.isSelected());
        return this;
    }

    public CategoryPage checkProdFound(String product){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class, 'PageTitle_count')]")));
        waitUntilVisible(filterInd);
        System.out.println(filterInd.getText());
        Assert.assertTrue("Поиск не выполнен", filterInd.getText().contains(product));
        return this;
    }

    public CategoryPage checkFiltersPerformed(String filterName){
        Assert.assertEquals("Поиск по выбранному фильтру не выполнен",
                filterName, checkList(filterName,pickedFilters).getText());
        return this;
    }

    public CategoryPage checkOne(String name){
        String elPath = "//div[contains(@class, 'FilterTags_group')]/span[text()='" + name +"']";
        Assert.assertTrue("Фильтр не работает", driverManager.getDriver().findElement(By.xpath(elPath)).isDisplayed());
        return this;
    }

    public CategoryPage checkNumberOfProd(int number){
        wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.xpath("//div[contains(@class,'Card_row')]"), number+1));
        List<WebElement> prs = driverManager.getDriver().findElements(By.xpath("//div[contains(@class,'Card_row')]"));
        Assert.assertTrue("Количество товаров " + prs.size()+ " больше заявленного", prs.size() <= number);
        return this;
    }

    public String savePrName(int number){
//        wait.until(ExpectedConditions.visibilityOfAllElements(driverManager
//                .getDriver().findElements(By.xpath("//p[contains(@class,'CardId_id')]"))));
        List<WebElement> prods = driverManager.getDriver().findElements(By.xpath("//a[contains(@class,'CardText_link')]"));
        List<WebElement> prodsT = driverManager.getDriver().findElements(By.xpath("//h6[contains(@class,'CardText')]"));
//        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[contains(@class,'FilterTags_group__values')]"),2));
//        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//h6[contains(@class,'CardText')]"),0));
        WebElement prod = prodsT.get(number-1);
        return prod.getText();
    }

    public CategoryPage fillInSearch(String value){
        waitUntilClickable(searchInput).clear();
        searchInput.sendKeys(value);
        searchInput.sendKeys(Keys.ENTER);
        return this;
    }

    public CategoryPage checkItemName(String value){
        Assert.assertEquals("Наименование товара не соответствует сохраненному ранее",
                value, productTitlesList.get(0).getText());
        return this;
    }

}
