package com.example.boozlet.Objects;

enum liquidType {
    SYRUP, ALCOHOL, JUICE, SOUR , NONE
}

public class Liquid extends Item {

    private int sugarLevel = 0; //out of 100 %
    private int sourLevel = 0; //out of 100 %
    private int abv = 0; // out of 100 %
    private liquidType type = liquidType.NONE;

    //maybe add is bitter? if yes, update for it.
    //is sparkly? and so...

//needs to make sure of % while collecting data, and limits, and not use defaults.


    public Liquid() {
    }

    public int getSugarLevel() {
        return sugarLevel;
    }

    public Liquid setSugarLevel(int sugarLevel) {
        if(sugarLevel < 0){
            this.sugarLevel = 0;
        }
        else if (sugarLevel > 100) {
            this.sugarLevel = 100;
        }
        else{
            this.sugarLevel = sugarLevel;
        }
        return this;
    }

    public int getSourLevel() {
        return sourLevel;
    }

    public Liquid setSourLevel(int sourLevel) {
        if(sourLevel < 0){
            this.sourLevel = 0;
        }
        else if (sourLevel > 100) {
            this.sourLevel = 100;
        }
        else{
            this.sourLevel = sourLevel;
        }

        return this;
    }

    public int getAbv() {
        return abv;
    }

    public Liquid setAbv(int abv) {
        if(abv < 0){
            this.abv = 0;
        }
        else if (abv >= 100) {
            this.abv = 100;
        }
        else{
            this.abv = abv;
        }
        return this;
    }

    public liquidType getType() {
        return type;
    }

    public Liquid setType() { // how to make sure this happenes last?

        if (this.abv > 0){
            this.type = liquidType.ALCOHOL;
        }
        else if (this.sourLevel >= 60) {
            this.type = liquidType.SOUR;
        }
        else if (this.sugarLevel >= 50){
            this.type = liquidType.SYRUP;
        }
        else if((this.sugarLevel <= 5)&&(this.sourLevel <= 5)){
            this.type = liquidType.NONE;
        }
        else {
            this.type = liquidType.JUICE;
        }

        return this;
    }


    //    public Liquid () {
//        super("Default");
//        this.abv = 0;
//        this.sugarLevel = 0;
//        this.sourLevel = 0;
//        this.type = liquidType.JUICE;
//
//    }

//    public Liquid (String name, int sugar, int sour, int alcohol){
//        super(name);
//        this.sourLevel = sour;
//        this.sugarLevel = sugar;
//        this.abv = alcohol;
//
//        if(abv > 0)
//            this.type = liquidType.ALCOHOL;
//        else {
//            if (sugar >= 50){
//                this.type = liquidType.SYRUP;
//            }
//            else {
//                this.type = liquidType.JUICE;
//            }
//
//        }
//
//    }


    @Override
    public String toString() {
        return super.toString()+" " +
                "sugarLevel=" + sugarLevel +
                ", sourLevel=" + sourLevel +
                ", abv=" + abv +
                ", type=" + type +
                '}';
    }
}
