package com.example.boozlet;

enum liquidType {
        SYRUP, ALCOHOL, JUICE
    }

public class Liquid extends Item {

    int sugarLevel; //out of 5
    int sourLevel; //out of 5
    int abv; // out of 100

    liquidType type;


    public Liquid () {
        super("Default");
        this.abv = 0;
        this.sugarLevel = 0;
        this.sourLevel = 0;
        this.type = liquidType.JUICE;

    }

    public Liquid (String name, int sugar, int sour, int alcohol){
        super(name);
        this.sourLevel = sour;
        this.sugarLevel = sugar;
        this.abv = alcohol;

        if(abv > 0)
            this.type = liquidType.ALCOHOL;
        else {
            if (sugar >= 50){
                this.type = liquidType.SYRUP;
            }
            else {
                this.type = liquidType.JUICE;
            }

        }

    }


}
