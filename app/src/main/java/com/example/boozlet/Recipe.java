package com.example.boozlet;

import java.util.ArrayList;

public class Recipe {

    private String name = "";
    private ArrayList<Ingredient> recipe;

    public Recipe(){

    }

    public Recipe(String tName){
        this.name = tName;
        this.recipe = new ArrayList<>();
    }

    public Recipe(String tName, ArrayList<Ingredient> arr){
        this.name = tName;
        this.recipe = arr;
    }
}
