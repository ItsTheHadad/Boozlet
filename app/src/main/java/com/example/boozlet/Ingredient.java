package com.example.boozlet;

public class Ingredient {

    Item item;
    int ml;

    public Ingredient(){
        this.item = null;
        this.ml = 0;
    }

    public Ingredient(Item tItem, int tMl){
        this.item = tItem;
        this.ml = tMl;
    }

}
