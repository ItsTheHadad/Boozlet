package com.example.boozlet;

import java.util.List;

public class InventoryDataModel {

    List<String> inventoryTitlesList;
    private String listName;


    public InventoryDataModel(List<String> inventoryTitlesList, String listName) {
        this.inventoryTitlesList = inventoryTitlesList;
        this.listName = listName;
    }



    
}
