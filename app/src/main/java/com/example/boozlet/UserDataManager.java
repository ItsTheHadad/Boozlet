package com.example.boozlet;

import com.google.firebase.database.DatabaseReference;

public class UserDataManager {

    private static UserDataManager instance = null;

    private User user;

    public void removeItemFromInventory(){
        //think of arguments, and how to implement
    }



    private UserDataManager(){}

    public static UserDataManager getInstance(){
        if (instance == null){
            instance = new UserDataManager();
        }
        return instance;
    }

    public void setUser(User user){
        // logic inside?
        this.user = user;
    }

    public User getUser(){
        return user;
    }



    public boolean isInInventory(String itemKey){
        //return true if the item is in the user inventory
        return true;
    }

}
