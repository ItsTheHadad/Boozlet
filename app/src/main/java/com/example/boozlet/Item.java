package com.example.boozlet;

public abstract class Item {

    private String name = "";
    private String DBkey = null;
    private boolean owned = false;

    public Item(){}


//    public Item(String tName){
//        this.name = tName;
//        this.owned = false;
//    }// do i need?

    public String getName() {
        return name;
    }

    public Item setName(String name) {  //is it ok? because its an abstract.
        this.name = name;
        return this;
    }

    public boolean isOwned() {
        return owned;
    }

    public Item setOwned(boolean iHave) {
        this.owned = iHave;
        return this;
    }

    public String getDBkey() {
        return DBkey;
    }

    public Item setDBkey(String DBkey) {
        this.DBkey = DBkey;
        return this;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", owned=" + owned +
                '}';
    }
}
