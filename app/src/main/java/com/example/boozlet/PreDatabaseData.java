package com.example.boozlet;


import com.example.boozlet.Objects.Ingredient;
import com.example.boozlet.Objects.Item;
import com.example.boozlet.Objects.Liquid;
import com.example.boozlet.Objects.Recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PreDatabaseData {

    public static ItemDataManager itemsManager = new ItemDataManager();
    public static ArrayList<Recipe> recipeManager = new ArrayList<>();

    public static boolean madeOnce = false;

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




        Ingredient vodka60 = new Ingredient(vodka,60);
        Ingredient limeJuice30 = new Ingredient(limeJuice,30);
        Ingredient gingerBeer180 = new Ingredient(gingerBeer,180);





        Recipe moscowMule = new Recipe()
                .setName("Moscow Mule")
                .addIngredient(vodka60)
                .addIngredient(limeJuice30)
                .addIngredient(gingerBeer180)
                .setPicURL(Constants.PictureURLs.MoscowMule);




        if(madeOnce == false){

            itemsManager.addItem(vodka);
            itemsManager.addItem(gin);
            itemsManager.addItem(rum);
            itemsManager.addItem(tequila);
            itemsManager.addItem(limeJuice);
            itemsManager.addItem(gingerBeer);


            recipeManager.add(moscowMule);

            madeOnce = true;
        }












    }


    public static ItemDataManager getItemsPre() {

        if(madeOnce == false){
            preMadeItems();
        }
        return itemsManager;



        //ItemDataManager itemDataManager = new ItemDataManager();

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

        //return itemDataManager;
    }


    public static List<Recipe> getRecipesPre() {
//        List<Item> allIngredients = getItemsPre().getItemList();
//
//        Recipe recipe1 = new Recipe();
//        recipe1.setPicURL(Constants.PictureURLs.URL1);
//        recipe1.setName("Recipe 1");
//        recipe1.getIngredientList().addAll(Arrays.asList(allIngredients.get(0),allIngredients.get(1),allIngredients.get(2)));
//
//        Recipe recipe2 = new Recipe();
//        recipe2.setPicURL(Constants.PictureURLs.URL2);
//        recipe2.setName("Recipe 2");
//        recipe2.getIngredientList().addAll(Arrays.asList(allIngredients.get(2),allIngredients.get(3),allIngredients.get(7)));
//
//
//        Recipe recipe3 = new Recipe();
//        recipe3.setPicURL(Constants.PictureURLs.URL3);
//        recipe3.setName("Recipe 3");
//        recipe3.getIngredientList().addAll(Arrays.asList(allIngredients.get(1),allIngredients.get(4),allIngredients.get(5)));
//
//        Recipe recipe4 = new Recipe();
//        recipe4.setPicURL(Constants.PictureURLs.URL4);
//        recipe4.setName("Recipe 4");
//        recipe4.getIngredientList().addAll(Arrays.asList(allIngredients.get(6),allIngredients.get(2),allIngredients.get(3)));
//
//        Recipe recipe5 = new Recipe();
//        recipe5.setPicURL(Constants.PictureURLs.URL5);
//        recipe5.setName("Recipe 5");
//        recipe5.getIngredientList().addAll(Arrays.asList(allIngredients.get(3),allIngredients.get(7),allIngredients.get(0)));

        //return Arrays.asList(recipe1,recipe2,recipe3,recipe4,recipe5);

        if(madeOnce == false){
            preMadeItems();
        }
        return recipeManager;

    }



}
