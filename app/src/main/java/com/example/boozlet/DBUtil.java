package com.example.boozlet;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.boozlet.Objects.Item;
import com.example.boozlet.Objects.Liquid;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

//why some functions are viewable without asking for instance?

public class DBUtil {

    //@@@class members

    //instance, reference to this class;
    private static DBUtil instance = null; // do i need it?

    //context, do i really need it in fragments?
    //private static Context appContext; - if yes get it back, and the methods related
    //mants a init with context who check instance and calls a ctor with context
    //a empty just regular getInstance, without a check
    //a ctor  gets cotext and this.appContext = context;



    //the database var itself;
    private FirebaseDatabase firebaseDatabase = null; // the database


    //References to specific areas in the DB
    // should refs be null or not?, depends on getters.
    //might be bad implementation, maybe all refs should be final somewhere?
    private DatabaseReference usersRef = null;
    private DatabaseReference currUserRef = null;
    private DatabaseReference fullListRef = null;

    // static DatabaseReference userInventoryRef;
    // problem is here, how can i use it if the init is in the app, before user even initted;
    //userInventoryRef = usersRef.child(UserDataManager.getInstance().getUser().getUserID());
    // String brrr = userInventoryRef.push().getKey();
    //  userInventoryRef.child(brrr).setValue(PreDatabaseData.getItemsPre().getItemList());


    //@@@class methods

    //private constructor
    private DBUtil(){
        if(this.firebaseDatabase == null){ //neccessary?
            this.firebaseDatabase = FirebaseDatabase.getInstance();
        }
    }

    //static method who gets the static ref of its class;
    public static DBUtil getInstance() {
        if(instance == null){
            instance = new DBUtil();
        }
        return instance;
    }


    //@@@getters and setters

    public FirebaseDatabase getFirebaseDatabase() {
        if(this.firebaseDatabase == null){
           setFirebaseDatabase();
        }
        return this.firebaseDatabase;
    }
    public DBUtil setFirebaseDatabase() { // should i have an option to another db instance in args?
        this.firebaseDatabase = FirebaseDatabase.getInstance();
        return this;
    }

    public DatabaseReference getCurrUserRef(){
        if(currUserRef == null){
            setCurrUserRef(getUsersRef().child(UserDataManager.getInstance().getCurrUserID()));
        }
        return currUserRef;
    }
    public DBUtil setCurrUserRef(DatabaseReference currUserRef) {
        this.currUserRef = currUserRef;
        return this;
    }

    public DatabaseReference getUsersRef() {
        if(usersRef == null){
            setUsersRef(getFirebaseDatabase().getReference(Constants.DBKeys.USER_KEYS));
        }
        return usersRef;
    }
    public DBUtil setUsersRef(DatabaseReference usersRef) {
        this.usersRef = usersRef;
        return this;
    }

    public DatabaseReference getFullListRef() {
        if(fullListRef == null){
            setFullListRef(getFirebaseDatabase().getReference(Constants.DBKeys.ITEMS));
        }
        return fullListRef;
    }
    public DBUtil setFullListRef(DatabaseReference fullListRef) {
        this.fullListRef = fullListRef;
        return this;
    }

    // do i also need removers for the instances which makes them null?


    //@@@methods

    public ArrayList<Item> getItemsFromFirebase(){
        ArrayList<Item> items = new ArrayList<>();
        fullListRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot iListSnap : snapshot.getChildren()){
                    Item item = null;
                    if(iListSnap.getValue(Liquid.class) instanceof Liquid) {
                        item = iListSnap.getValue(Liquid.class);
                    }//need to add for more types.
                    items.add(item);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //why is the items empty here? // bug
        return items;
    }

    public void saveItemToDB(Item item){ // for the first time when its added.
        DatabaseReference itemListRef = DBUtil.getInstance().getFullListRef();

        String itemKey = item.getdBkey();
            //change below thew item.getdbkey() just to itemkey
        if(item.getdBkey() == null){
            itemKey = itemListRef.push().getKey();
            item.setdBkey(itemKey);
        }
        //should i check and do something if itemkey null?
        itemListRef.child(itemKey).setValue(item);
    }

    public void addPreToDB1(){
        fullListRef.setValue(PreDatabaseData.getItemsPre().getItemList());
    }
    public void addPreToDB2(){

        for (Item item :
            PreDatabaseData.getItemsPre().getItemList() ) {
            saveItemToDB(item);
        }
    }

    public void addItemToCurrUserList(Item item){
        DatabaseReference tempUserRef = getCurrUserRef(); //gets the current user ref;
        tempUserRef.addListenerForSingleValueEvent(new ValueEventListener() { //listener for the curr user;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) { // i dont know why that specifically
                User tempUser = snapshot.getValue(User.class); // get the user from snapshot;
                if(tempUser != null){
                    ItemDataManager tempInventory = tempUser.getInventory(); //get the inventory from that user to a temp inv

                    tempInventory.addItem(item); // add the item to that inventory (maybe its a problem, and want to the inventory before copy?)

                    tempUserRef.setValue(tempUser); // might not be true

                }
                else{
                    // error user is null;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public ItemDataManager retrieveUserInventoryFromDB(DatabaseReference userRef) {

        ItemDataManager tempInventory = new ItemDataManager(); //might not work because it async, will "die" at the end of the func;

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User tempUser = snapshot.getValue(User.class);
                if(tempUser != null){
                    tempInventory.setItemList(tempUser.getInventory().getItemList()); // same reason from above;
                }
                else{
                    tempInventory.setItemList(null);
                }
            } //also, notice its not really returning the itemDM, but the arraylist inside only.

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //handle error?
            }
        });

        return tempInventory; //same reason from above;
    }

    public void getUserByID(String uid){
        //this function need to init the useradatamanager curr user, however its not
        DatabaseReference usersRef = getUsersRef();//useless?

        if(uid != null){
            usersRef.child(uid).addListenerForSingleValueEvent(new ValueEventListener() { // never inside never data changed?
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){ // there is a user with that key
                        User user = snapshot.getValue(User.class);
                        UserDataManager.getInstance().setCurrUser(user);
                    }
                    else{
                        createNewUser(uid);
                    }
                }

                //after that the userdatamanager is fixed

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else {
            //toast a problem;
        }




        //exists or has child?

//        usersRef.child(uid).addListenerForSingleValueEvent(new ValueEventListener() { // never inside never data changed?
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists()){ // there is a user with that key
//                    User user = snapshot.getValue(User.class);
//                    UserDataManager.getInstance().setCurrUser(user);
//                }
//                else{
//                    createNewUser(uid);
//                }
//            }
//
//            //after that the userdatamanager is fixed
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

    }

    private void createNewUser(String uid){
        User user = new User(uid);
        getUsersRef().child(uid).setValue(user); //when to use addOnSuccessListener(new OnSuccessListener<Void>() , and when OnComplete?
        UserDataManager.getInstance().setCurrUser(user);
        Log.d("userTag", UserDataManager.getInstance().getCurrUser().getUserID());

//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) { // while debugging not entering since its async? ask to to explain
//                       // Log.d("checkOfSuc", "im here");
//                        UserDataManager.getInstance().setCurrUser(user);
//                        // add toast welcome new user
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        // add toast didnt succed
//                    }
//                });
    }



}
