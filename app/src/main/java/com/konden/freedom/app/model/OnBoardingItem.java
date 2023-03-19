package com.konden.freedom.app.model;

public class OnBoardingItem {
    private String title;
    private String description;

    public OnBoardingItem(String title, String description) {
        this.title = title;
        this.description = description;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
}
