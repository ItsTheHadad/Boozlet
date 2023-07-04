package com.example.boozlet;

public class UserDataManager {


    //@@@class members

    //instance, reference to this class;
    private static UserDataManager instance = null;

    //the curr user var
    private User currUser = null;

    private UserDataManager(){ //major problem, cant init curr user
        //this.currUser = new User(); // adding it wont change


       //empty? check about it
        //add something to init the currUser even if no one called setcurrUser
    }



    //static method who gets the static ref of its class;
    public static UserDataManager getInstance(){
        if (instance == null){
            instance = new UserDataManager();
        }
        return instance;
    }


    public User getCurrUser(){ //major problem if init without set curr
        return currUser;
    }
    public void setCurrUser(User currUser){
        // logic inside?
        this.currUser = currUser;
    }


    public String getCurrUserID(){
        return getCurrUser().getUserID(); //do i need get instance here?
    }

    public boolean isInInventory(String itemKey){
        //return true if the item is in the user inventory
        return true;
    }

    public void removeItemFromInventory(){
        //think of arguments, and how to implement
    }



}
