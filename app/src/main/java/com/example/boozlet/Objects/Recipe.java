package com.example.boozlet.Objects;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;

public class Recipe {

    private String name = "";

    private String picURL = "";

    private ArrayList<Ingredient> ingredientList = new ArrayList<>();

    @Exclude
    private int missingIngredients; // dont want it in the database, only to calculate it each time


    public Recipe(){

    }






}
