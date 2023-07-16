package com.example.boozlet;


import com.example.boozlet.Objects.Ingredient;
import com.example.boozlet.Objects.Item;
import com.example.boozlet.Objects.Liquid;
import com.example.boozlet.Objects.Recipe;

import java.util.ArrayList;

public class PreDatabaseData {

    public static ItemDataManager itemsManager = new ItemDataManager();
    public static ArrayList<Recipe> recipeManager = new ArrayList<>();


    public static void preMadeItems(){

        Item vodka = new Liquid()
                .setAbv(40)
                .setSourLevel(0)
                .setSugarLevel(0)
                .setType()
                .setName("Vodka")
                .setOwned(false);



        Item gin = new Liquid()
                .setAbv(40)
                .setSourLevel(0)
                .setSugarLevel(0)
                .setType()
                .setName("Gin")
                .setOwned(false);



        Item rum = new Liquid()
                .setAbv(40)
                .setSourLevel(0)
                .setSugarLevel(0)
                .setType()
                .setName("Rum")
                .setOwned(false);



        Item tequila = new Liquid()
                .setAbv(40)
                .setSourLevel(0)
                .setSugarLevel(0)
                .setType()
                .setName("Tequila")
                .setOwned(false);


        Item limeJuice = new Liquid()
                .setAbv(40)
                .setSourLevel(0)
                .setSugarLevel(0)
                .setType()
                .setName("Lime Juice")
                .setOwned(false);



        Item gingerBeer = new Liquid()
                .setAbv(40)
                .setSourLevel(0)
                .setSugarLevel(0)
                .setType()
                .setName("Ginger Beer")
                .setOwned(false);



        itemsManager.addItem(vodka);
        itemsManager.addItem(gin);
        itemsManager.addItem(rum);
        itemsManager.addItem(tequila);
        itemsManager.addItem(limeJuice);
        itemsManager.addItem(gingerBeer);

    }


    public static ItemDataManager getItemsPre() {
        ItemDataManager itemDataManager = new ItemDataManager();

        itemDataManager = itemsManager;
        return itemDataManager;


//        itemDataManager.addItem(new Liquid()
//
//                .setAbv(40)
//                .setSourLevel(0)
//                .setSugarLevel(0)
//                .setType()
//                .setName("Vodka")
//                .setOwned(false)
//
//        );
//
//        itemDataManager.addItem(new Liquid()
//
//                .setAbv(40)
//                .setSourLevel(0)
//                .setSugarLevel(0)
//                .setType()
//                .setName("Gin")
//                .setOwned(false)
//
//        );
//
//        itemDataManager.addItem(new Liquid()
//
//                .setAbv(40)
//                .setSourLevel(0)
//                .setSugarLevel(0)
//                .setType()
//                .setName("White Rum")
//                .setOwned(false)
//
//        );
//
//        itemDataManager.addItem(new Liquid()
//
//                .setAbv(40)
//                .setSourLevel(0)
//                .setSugarLevel(0)
//                .setType()
//                .setName("Tequila")
//                .setOwned(false)
//
//        );
//
//        itemDataManager.addItem(new Liquid()
//
//                .setAbv(40)
//                .setSourLevel(0)
//                .setSugarLevel(0)
//                .setType()
//                .setName("Anise")
//                .setOwned(false)
//
//        );
//
//        itemDataManager.addItem(new Liquid()
//
//                .setAbv(40)
//                .setSourLevel(0)
//                .setSugarLevel(0)
//                .setType()
//                .setName("Whisky")
//                .setOwned(false)
//
//        );
//
//        itemDataManager.addItem(new Liquid()
//
//                .setAbv(40)
//                .setSourLevel(0)
//                .setSugarLevel(0)
//                .setType()
//                .setName("Congac")
//                .setOwned(false)
//
//        );
//
//        itemDataManager.addItem(new Liquid()
//
//                .setAbv(24)
//                .setSourLevel(0)
//                .setSugarLevel(20)
//                .setType()
//                .setName("Campari")
//                .setOwned(false)
//
//        );
//
//        return itemDataManager;
    }




    public static ArrayList<Recipe> getRecipesPre(){
        ArrayList<Ingredient> allIngredients = getItemsPre().getItemList();

    }








}
