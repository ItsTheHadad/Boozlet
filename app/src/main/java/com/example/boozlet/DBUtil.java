package com.example.boozlet;

import android.content.Context;

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

    private static DBUtil instance; // do i need it?

    static FirebaseDatabase firebaseDatabase;





    static DatabaseReference usersRef;
    static DatabaseReference currUserRef = null;
    static DatabaseReference fullListRef;

    static DatabaseReference userInventoryRef;

    private static Context appContext; // necessary in a fragments?

    private DBUtil(Context context){this.appContext = context;}

    public static DBUtil getInstance() {return instance;} // i dont really use it?

    public static DBUtil initDBUtil( Context context){
        if (instance == null)
            instance = new DBUtil(context);

        firebaseDatabase = FirebaseDatabase.getInstance(); // should it be here?

        fullListRef = firebaseDatabase.getReference(Constants.DBKeys.ITEMS);
        usersRef = firebaseDatabase.getReference(Constants.DBKeys.USER_KEYS);


        //problem is here, how can i use it if the init is in the app, before user even initted;
        //userInventoryRef = usersRef.child(UserDataManager.getInstance().getUser().getUserID());

       // String brrr = userInventoryRef.push().getKey();
      //  userInventoryRef.child(brrr).setValue(PreDatabaseData.getItemsPre().getItemList());


        return instance;
    }

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
        DatabaseReference itemListRef = DBUtil.getFullListRef();

        String itemKey = item.getdBkey();

        if(item.getdBkey() == null){
            itemKey = itemListRef.push().getKey();
            item.setdBkey(itemKey);
        }
//        else{
//            itemListRef.push();
//        } //need it?

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


    public DatabaseReference getCurrUserRef(){
        if(currUserRef == null){
            currUserRef = getUsersRef().child(UserDataManager.getInstance().getCurrUserID());
        }
        return currUserRef;
    }


    public static DatabaseReference getUsersRef() {
        return usersRef;
    }

    public static void setUsersRef(DatabaseReference usersRef) {
        DBUtil.usersRef = usersRef;
    }

    public void getUserByID(String uid){

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

            //after that the userdatamanager is fixed

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    private void createNewUser(String uid){
        User user = new User(uid);
        usersRef.child(uid).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        UserDataManager.getInstance().setCurrUser(user);
                        // add toast welcome new user
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // add toast didnt succed
                    }
                });
    }


    public static DatabaseReference getFullListRef() {
        return fullListRef;
    }

    public static void setFullListRef(DatabaseReference fullListRef) {
        DBUtil.fullListRef = fullListRef;
    }
}
