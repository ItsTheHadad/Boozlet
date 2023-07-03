package com.example.boozlet;

public class User {

    private String userID = "";

    private ItemDataManager personalInventory;

    public User(){

    }



    public User(String userID){
        this.userID = userID;
        this.personalInventory = new ItemDataManager();
    }
    //this.userName = FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber(); maybe should add



    public String getUserID() {
        return userID;
    }

    public User setUserID(String userID) {
        this.userID = userID;
        return this;
    }

    public ItemDataManager getPersonalInventory() {
        return personalInventory;
    }

    public User setPersonalInventory(ItemDataManager personalInventory) {
        this.personalInventory = personalInventory;
        return this;
    }

    //might add a ctor with a premade inventory



}
