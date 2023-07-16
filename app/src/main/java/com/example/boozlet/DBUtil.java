package com.example.boozlet;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.boozlet.Objects.Item;
import com.example.boozlet.Objects.Liquid;
import com.example.boozlet.Objects.Recipe;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class DBUtil {

    //@@@class members

    //instance, reference to this class;
    private static DBUtil instance = null; // do i need it?

    //the database var itself;
    private FirebaseDatabase firebaseDatabase = null; // the database


    //References to specific areas in the DB

    private DatabaseReference usersRef = null;
    private DatabaseReference currUserRef = null;
    private DatabaseReference fullListRef = null;

    //@@@class methods

    //private constructor
    private DBUtil(){
        if(this.firebaseDatabase == null){
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
                    }//need to add for more types - not for this project but future for github
                    items.add(item);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return items;
    }

    public void saveItemToDB(Item item){ // for the first time when its added.
        DatabaseReference itemListRef = DBUtil.getInstance().getFullListRef();

        String itemKey = item.getdBkey();
            // not working to change below thew item.getdbkey() just to itemkey
        if(item.getdBkey() == null){
            itemKey = itemListRef.push().getKey();
            item.setdBkey(itemKey);
        }
        //should i check and do something if itemkey null? its unneccesary here...
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

    private void saveRecipeToDB(Recipe recipe){ // for the first time when its added.
        DatabaseReference recipeListRef = FirebaseDatabase.getInstance().getReference(Constants.DBKeys.RECIPES);

        String itemKey = recipeListRef.push().getKey();
        recipeListRef.child(itemKey).setValue(recipe);
    }

    public void addPrePreRecipeToDB2(){

        for (Recipe recipe :
                PreDatabaseData.getRecipesPre() ) {
            saveRecipeToDB(recipe);
        }
    }

    public void addItemToCurrUserList(Item item){
        DatabaseReference tempUserRef = getCurrUserRef(); //gets the current user ref;
        tempUserRef.addListenerForSingleValueEvent(new ValueEventListener() { //listener for the curr user;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User tempUser = snapshot.getValue(User.class);
                if(tempUser != null){
                    ItemDataManager tempInventory = tempUser.getInventory();

                    tempInventory.addItem(item); // adds the item to that inventory (maybe its a problem, and want to the inventory before copy?)

                    tempUserRef.setValue(tempUser);

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

        ItemDataManager tempInventory = new ItemDataManager();

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User tempUser = snapshot.getValue(User.class);
                if(tempUser != null){
                    tempInventory.setItemList(tempUser.getInventory().getItemList());
                }
                else{
                    tempInventory.setItemList(null);
                }
            } //also, notice its not really returning the itemDM, but the arraylist inside only.

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return tempInventory;
    }

    public void getUserByID(String uid){

        DatabaseReference usersRef = getUsersRef();

        if(uid != null){
            usersRef.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
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


                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else {

        }


    }

    private void createNewUser(String uid){
        User user = new User(uid);
        getUsersRef().child(uid).setValue(user);
        UserDataManager.getInstance().setCurrUser(user);
    }

    public interface OwnedItemsListener {
        void onSuccess(List<String> ownedItems);
        void onFailure(String message);
    }

    public static void getOwnedItems(OwnedItemsListener ownedItemsListener) {
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(Constants.DBKeys.USER_KEYS).child(userID).child(Constants.DBKeys.ITEMS);

        ArrayList<String> ownedItems = new ArrayList<>();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    String item = dataSnapshot.getValue(String.class);
                    ownedItems.add(item);
                }
                ownedItemsListener.onSuccess(ownedItems);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                ownedItemsListener.onFailure(error.getMessage());
            }
        });

    }

    public static void addOwnedItem(String item) {
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(Constants.DBKeys.USER_KEYS).child(userID).child(Constants.DBKeys.ITEMS);
        databaseReference.child(item).setValue(item);
    }

    public static void removeOwnedItem(String item) {
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(Constants.DBKeys.USER_KEYS).child(userID).child(Constants.DBKeys.ITEMS);
        databaseReference.child(item).removeValue();
    }

}
