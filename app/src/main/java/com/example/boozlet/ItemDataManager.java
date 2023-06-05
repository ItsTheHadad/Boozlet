package com.example.boozlet;

import java.util.ArrayList;

public class ItemDataManager {

    ArrayList<Item> items;

    public ItemDataManager(){
        items = new ArrayList<>();
    }

    public ArrayList<Item> getItems() {
        return items;
    } // maybe needs to return IDM?

    public ItemDataManager addItems(Item tItem){
        this.items.add(tItem);
        return this;
    }
}
