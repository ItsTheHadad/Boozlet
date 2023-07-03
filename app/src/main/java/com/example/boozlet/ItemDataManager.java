package com.example.boozlet;

import com.example.boozlet.Objects.Item;

import java.util.ArrayList;

public class ItemDataManager {

    private ArrayList<Item> itemList;

    public ItemDataManager() {
        itemList = new ArrayList<>();
    } //to use with json, the def ctor takes not arguments.

    public ArrayList<Item> getItemList() {
        return this.itemList;
    } // maybe needs to return IDM?


    public ItemDataManager addItem(Item tItem) {
        this.itemList.add(tItem);
        return this;
    }

    public ItemDataManager setItemList(ArrayList<Item> itemList) {
        this.itemList = itemList;
        return this;
    }

    @Override
    public String toString() {
        return "ItemDataManager{" +
                "itemList=" + itemList +
                '}';
    }
}
