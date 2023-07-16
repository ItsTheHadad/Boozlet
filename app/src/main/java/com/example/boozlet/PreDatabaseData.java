package com.example.boozlet;


import com.example.boozlet.Objects.Ingredient;
import com.example.boozlet.Objects.Item;
import com.example.boozlet.Objects.Liquid;
import com.example.boozlet.Objects.Recipe;

import java.util.ArrayList;
import java.util.List;

public class PreDatabaseData {

    public static ItemDataManager itemsManager = new ItemDataManager();
    public static ArrayList<Recipe> recipeManager = new ArrayList<>();

    public static boolean madeOnce = false;

    public static void preMadeItems(){

        Item angostura = new Liquid()
                .setAbv(47)
                .setSourLevel(0)
                .setSugarLevel(0)
                .setType()
                .setName("Angostura")
                .setOwned(false);

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

        Item whiskey = new Liquid()
                .setAbv(40)
                .setSourLevel(0)
                .setSugarLevel(0)
                .setType()
                .setName("Whiskey")
                .setOwned(false);


        Item tequila = new Liquid()
                .setAbv(40)
                .setSourLevel(0)
                .setSugarLevel(0)
                .setType()
                .setName("Tequila")
                .setOwned(false);

        Item vermouthDry = new Liquid()
                .setAbv(25)
                .setSourLevel(30)
                .setSugarLevel(20)
                .setType()
                .setName("Dry Vermouth")
                .setOwned(false);

        Item vermouthSweet = new Liquid()
                .setAbv(25)
                .setSourLevel(30)
                .setSugarLevel(20)
                .setType()
                .setName("Sweet Vermouth")
                .setOwned(false);

        Item campari = new Liquid()
                .setAbv(20)
                .setSourLevel(0)
                .setSugarLevel(20)
                .setType()
                .setName("Campari")
                .setOwned(false);

        Item limeJuice = new Liquid()
                .setAbv(0)
                .setSourLevel(75)
                .setSugarLevel(20)
                .setType()
                .setName("Lime Juice")
                .setOwned(false);

        Item simpleSyrup = new Liquid()
                .setAbv(0)
                .setSourLevel(0)
                .setSugarLevel(80)
                .setType()
                .setName("Simple Syrup")
                .setOwned(false);


        Item gingerBeer = new Liquid()
                .setAbv(0)
                .setSourLevel(20)
                .setSugarLevel(40)
                .setType()
                .setName("Ginger Beer")
                .setOwned(false);

        Item grapefruitJuice = new Liquid()
                .setAbv(0)
                .setSourLevel(30)
                .setSugarLevel(40)
                .setType()
                .setName("Grapefruit Juice")
                .setOwned(false);

        Item cranberryJuice = new Liquid()
                .setAbv(0)
                .setSourLevel(40)
                .setSugarLevel(35)
                .setType()
                .setName("Cranberry Juice")
                .setOwned(false);

        Item oliveJuice = new Liquid()
                .setAbv(0)
                .setSourLevel(40)
                .setSugarLevel(0)
                .setType()
                .setName("Olive Juice")
                .setOwned(false);

        Item orangeJuice = new Liquid()
                .setAbv(0)
                .setSourLevel(40)
                .setSugarLevel(40)
                .setType()
                .setName("Orange Juice")
                .setOwned(false);

        Item cointreau = new Liquid()
                .setAbv(35)
                .setSourLevel(0)
                .setSugarLevel(40)
                .setType()
                .setName("Cointreau")
                .setOwned(false);

        Item grenadine = new Liquid()
                .setAbv(0)
                .setSourLevel(0)
                .setSugarLevel(80)
                .setType()
                .setName("Grenadine")
                .setOwned(false);

        Item cola = new Liquid()
                .setAbv(0)
                .setSourLevel(0)
                .setSugarLevel(40)
                .setType()
                .setName("Cola")
                .setOwned(false);



        Ingredient rum60 = new Ingredient(rum,60);
        Ingredient vodka60 = new Ingredient(vodka,60);
        Ingredient gin90 = new Ingredient(gin,90);
        Ingredient gin30 = new Ingredient(gin,30);
        Ingredient whiskey60 = new Ingredient(whiskey,60);
        Ingredient whiskey30 = new Ingredient(whiskey,30);
        Ingredient tequila60 = new Ingredient(tequila,60);

        Ingredient dryVermouth20 = new Ingredient(vermouthDry,20);
        Ingredient sweetVermouth30 = new Ingredient(vermouthSweet,30);
        Ingredient campari30 = new Ingredient(campari,30);
        Ingredient cointreau30 = new Ingredient(cointreau,30);

        Ingredient angostura5 = new Ingredient(angostura,5);

        Ingredient limeJuice30 = new Ingredient(limeJuice,30);
        Ingredient orangeJuice180 = new Ingredient(orangeJuice,180);
        Ingredient grapefruitJuice60 = new Ingredient(grapefruitJuice,60);
        Ingredient cranberryJuice90 = new Ingredient(cranberryJuice,90);
        Ingredient olive10 = new Ingredient(oliveJuice,10);
        Ingredient gingerBeer180 = new Ingredient(gingerBeer,180);
        Ingredient simpleSyrup20 = new Ingredient(simpleSyrup,20);
        Ingredient simpleSyrup30 = new Ingredient(simpleSyrup,30);
        Ingredient grenadine20 = new Ingredient(grenadine,20);
        Ingredient cola180 = new Ingredient(cola,80);


        Recipe cubaLibre = new Recipe()
                .setName("Cuba Libre")
                .addIngredient(rum60)
                .addIngredient(limeJuice30)
                .addIngredient(cola180)
                .setPicture(R.drawable.cubalibre);


        Recipe daiquiri = new Recipe()
                .setName("Daiquiri")
                .addIngredient(rum60)
                .addIngredient(limeJuice30)
                .addIngredient(simpleSyrup30)
                .setPicture(R.drawable.daquiri);



        Recipe tequilaSunrise = new Recipe()
                .setName("Tequila Sunrise")
                .addIngredient(tequila60)
                .addIngredient(orangeJuice180)
                .addIngredient(grenadine20)
                .setPicture(R.drawable.sunrise);

        Recipe margarita = new Recipe()
                .setName("Margarita")
                .addIngredient(tequila60)
                .addIngredient(cointreau30)
                .addIngredient(limeJuice30)
                .setPicture(R.drawable.margarita);

        Recipe boulevardier = new Recipe()
                .setName("Boulevardier")
                .addIngredient(whiskey30)
                .addIngredient(campari30)
                .addIngredient(sweetVermouth30)
                .setPicture(R.drawable.boulevardier);


        Recipe oldFashioned = new Recipe()
                .setName("Old Fashioned")
                .addIngredient(whiskey60)
                .addIngredient(angostura5)
                .addIngredient(simpleSyrup20)
                .setPicture(R.drawable.oldfashioned);



        Recipe negroni = new Recipe()
                .setName("Negroni")
                .addIngredient(gin30)
                .addIngredient(campari30)
                .addIngredient(sweetVermouth30)
                .setPicture(R.drawable.negroni);


        Recipe dirtyMartini = new Recipe()
                .setName("Dirty Martini")
                .addIngredient(gin90)
                .addIngredient(dryVermouth20)
                .addIngredient(olive10)
                .setPicture(R.drawable.dirtymartini);

        Recipe seaBreeze = new Recipe()
                .setName("Sea Breeze")
                .addIngredient(vodka60)
                .addIngredient(grapefruitJuice60)
                .addIngredient(cranberryJuice90)
                .setPicture(R.drawable.seabreeze);


        Recipe moscowMule = new Recipe()
                .setName("Moscow Mule")
                .addIngredient(vodka60)
                .addIngredient(limeJuice30)
                .addIngredient(gingerBeer180)
                .setPicture(R.drawable.moscmule);




        if(madeOnce == false){

            itemsManager.addItem(vodka);
            itemsManager.addItem(gin);
            itemsManager.addItem(rum);
            itemsManager.addItem(tequila);
            itemsManager.addItem(whiskey);
            itemsManager.addItem(angostura);
            itemsManager.addItem(cointreau);
            itemsManager.addItem(campari);
            itemsManager.addItem(vermouthDry);
            itemsManager.addItem(vermouthSweet);
            itemsManager.addItem(limeJuice);
            itemsManager.addItem(orangeJuice);
            itemsManager.addItem(cranberryJuice);
            itemsManager.addItem(grapefruitJuice);
            itemsManager.addItem(oliveJuice);
            itemsManager.addItem(gingerBeer);
            itemsManager.addItem(simpleSyrup);
            itemsManager.addItem(grenadine);
            itemsManager.addItem(cola);



            recipeManager.add(moscowMule);
            recipeManager.add(cubaLibre);
            recipeManager.add(seaBreeze);
            recipeManager.add(tequilaSunrise);
            recipeManager.add(daiquiri);
            recipeManager.add(boulevardier);
            recipeManager.add(dirtyMartini);
            recipeManager.add(margarita);
            recipeManager.add(negroni);
            recipeManager.add(oldFashioned);

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
