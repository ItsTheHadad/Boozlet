package com.example.boozlet;

import com.example.boozlet.Objects.Item;

import java.util.ArrayList;

public class ItemDataManager {

    private ArrayList<Item> itemList;

    public ItemDataManager() {
        itemList = new ArrayList<>();
    }

    public ArrayList<Item> getItemList() {
        return this.itemList;
    }


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
