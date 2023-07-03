package com.example.boozlet;

public class User {

    private String userID = "";

    private ItemDataManager inventory;

    public User(){

    }

    public User(String userID){
        this.userID = userID;
        this.inventory = new ItemDataManager();
    }
    //this.userName = FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber(); maybe should add



    public String getUserID() { //to check if its not null / user not null
        return userID;
    }

    public User setUserID(String userID) {
        this.userID = userID;
        return this;
    }

    public ItemDataManager getInventory() {
        return inventory;
    }

    public User setInventory(ItemDataManager inventory) {
        this.inventory = inventory;
        return this;
    }

    //might add a ctor with a premade inventory

}
