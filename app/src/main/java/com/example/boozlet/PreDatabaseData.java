package com.example.boozlet;


import com.example.boozlet.Objects.Liquid;

public class PreDatabaseData {

    public static ItemDataManager getItemsPre() {
        ItemDataManager itemDataManager = new ItemDataManager();


//        String key = databaseReference.push().getKey();
//        databaseReference.child(key).setValue(item);




        itemDataManager.addItem(new Liquid()

                .setAbv(40)
                .setSourLevel(0)
                .setSugarLevel(0)
                .setType()
                .setName("Vodka")
                .setOwned(false)

        );

        itemDataManager.addItem(new Liquid()

                .setAbv(40)
                .setSourLevel(0)
                .setSugarLevel(0)
                .setType()
                .setName("Gin")
                .setOwned(false)

        );

        itemDataManager.addItem(new Liquid()

                .setAbv(40)
                .setSourLevel(0)
                .setSugarLevel(0)
                .setType()
                .setName("White Rum")
                .setOwned(false)

        );

        itemDataManager.addItem(new Liquid()

                .setAbv(40)
                .setSourLevel(0)
                .setSugarLevel(0)
                .setType()
                .setName("Tequila")
                .setOwned(false)

        );

        itemDataManager.addItem(new Liquid()

                .setAbv(40)
                .setSourLevel(0)
                .setSugarLevel(0)
                .setType()
                .setName("Anise")
                .setOwned(false)

        );

        itemDataManager.addItem(new Liquid()

                .setAbv(40)
                .setSourLevel(0)
                .setSugarLevel(0)
                .setType()
                .setName("Whisky")
                .setOwned(false)

        );

        itemDataManager.addItem(new Liquid()

                .setAbv(40)
                .setSourLevel(0)
                .setSugarLevel(0)
                .setType()
                .setName("Congac")
                .setOwned(false)

        );

        itemDataManager.addItem(new Liquid()

                .setAbv(24)
                .setSourLevel(0)
                .setSugarLevel(20)
                .setType()
                .setName("Campari")
                .setOwned(false)

        );

        return itemDataManager;
    }



}
