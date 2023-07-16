package com.example.boozlet.Objects;

public class Ingredient {

    Item item;
    int ml;

    public Ingredient(){
        this.item = null;
        this.ml = 0;
    }

    public Ingredient(Item tItem, int tMl){
        setItem(tItem);
        setMl(tMl);
    }


    public Item getItem() {
        return item;
    }

    public Ingredient setItem(Item item) {
        this.item = item;
        return this;
    }

    public int getMl() {
        return ml;
    }

    public Ingredient setMl(int ml) {
        this.ml = ml;
        return this;
    }

}

