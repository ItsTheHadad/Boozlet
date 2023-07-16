package com.example.boozlet.Objects;

public class Item {

    private String name = "";
    private String dBkey = null;
    private boolean owned = false;

    public Item(){}

    public String getName() {
        return name;
    }

    public Item setName(String name) {
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
