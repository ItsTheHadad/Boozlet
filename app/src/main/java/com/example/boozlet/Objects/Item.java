package com.example.boozlet.Objects;

public abstract class Item {

    private String name = "";
    private String dBkey = null;
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

    public String getdBkey() {
        return dBkey;
    }

    public Item setdBkey(String dBkey) {
        this.dBkey = dBkey;
        return this;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", dBkey='" + dBkey + '\'' +
                ", owned=" + owned +
                '}';
    }
}
