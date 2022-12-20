package org.example.framework.managers;


import org.example.framework.pages.CategoryPage;
import org.example.framework.pages.MainPage;

public class PageManager {
    private  PageManager pageManager;
    private MainPage mainPage;
    private CategoryPage categoryPage;


    public PageManager(){
    }

    public  PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    public MainPage getMainPage() {
        if (mainPage == null) {
            mainPage = new MainPage();
        }
        return mainPage;
    }

    public CategoryPage getCategoryPage() {
        if (categoryPage == null) {
            categoryPage = new CategoryPage();
        }
        return categoryPage;
    }




}
