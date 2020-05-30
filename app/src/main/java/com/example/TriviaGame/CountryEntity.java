package com.example.TriviaGame;

public class CountryEntity {
    private String name;
    private String capital;
    private boolean isSelected = false;


    public CountryEntity(String text) {
        this.name = text;
    }

    public CountryEntity(String name, String capital) {
        this.name = name;
        this.capital = capital;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    public boolean isSelected() {
        return isSelected;
    }


}