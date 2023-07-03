package com.example.boozlet;

public class UserDataManager {

    private static UserDataManager instance = null;

    private User currUser;

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

    public void setCurrUser(User currUser){
        // logic inside?
        this.currUser = currUser;
    }

    public User getCurrUser(){
        return currUser;
    }

    public String getCurrUserID(){
        return getCurrUser().getUserID(); //do i need get instance here?
    }

    public boolean isInInventory(String itemKey){
        //return true if the item is in the user inventory
        return true;
    }

}
