package com.example.boozlet.Objects;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;

public class Recipe {

    private String name = "";
    private String picURL = "";
    private ArrayList<Ingredient> ingredientList = new ArrayList<>();

    @Exclude
    private int missingIngredients;

    public Recipe(){

    }

    public String getName() {
        return name;
    }

    public Recipe setName(String name) {
        this.name = name;
        return this;
    }

    public String getPicURL() {
        return picURL;
    }

    public Recipe setPicURL(String picURL) {
        this.picURL = picURL;
        return this;
    }

    public ArrayList<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(ArrayList<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public Recipe addIngredient(Ingredient ing){
        this.ingredientList.add(ing);
        return this;
    }


    public int getMissingIngredients() {
        return missingIngredients;
    }

    public void setMissingIngredients(int missingIngredients) {
        this.missingIngredients = missingIngredients;
    }
}
