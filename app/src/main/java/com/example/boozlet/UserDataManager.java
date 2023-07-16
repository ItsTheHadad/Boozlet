package com.example.boozlet;

public class UserDataManager {


    //@@@class members

    //instance, reference to this class;
    private static UserDataManager instance = null;

    //the curr user var
    private User currUser = null;

    private UserDataManager(){
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
        this.currUser = currUser;
    }


    public String getCurrUserID(){
        return getCurrUser().getUserID(); //do i need get instance here?
    }

}
