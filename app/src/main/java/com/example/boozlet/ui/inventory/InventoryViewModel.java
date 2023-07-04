package com.example.boozlet.ui.inventory;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.boozlet.Constants;
import com.example.boozlet.DBUtil;
import com.example.boozlet.Objects.Item;
import com.example.boozlet.ItemDataManager;
import com.example.boozlet.Objects.Liquid;
import com.example.boozlet.PreDatabaseData;
import com.example.boozlet.UserDataManager;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class InventoryViewModel extends ViewModel {

    private final MutableLiveData<ItemDataManager> mItems;

    public InventoryViewModel() {
        mItems = new MutableLiveData<>();
        //mItems.setValue(getItemsFromFirebase());
        ItemDataManager tempItemDM = new ItemDataManager();
        tempItemDM.setItemList(getItemsFromFirebase4());
        //tempItemDM.setItemList(DBUtil.getInstance().getItemsFromFirebase()); // why it wont work?
        mItems.setValue(tempItemDM);

    }



    private ItemDataManager getItemsFromFirebase1() {
        ItemDataManager itemDataManager = new ItemDataManager();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference(Constants.DBKeys.ITEMS);

        databaseReference.addChildEventListener(
                new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        for(DataSnapshot ds : snapshot.getChildren()){
                            if(ds.getValue(Liquid.class) instanceof Liquid){
                                Liquid liquid = ds.getValue(Liquid.class);
                                itemDataManager.addItem(liquid);
                            }
                            //add elsif for every type
                        }
                        mItems.setValue(itemDataManager);

                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {







                        Item item = snapshot.getValue(Item.class);
                        for (int i = 0; i < itemDataManager.getItemList().size(); i++) {
                            if(itemDataManager.getItemList().get(i).getName().equals(item.getName())){
                                itemDataManager.getItemList().set(i,item);
                            }
                        }
                        mItems.setValue(itemDataManager);
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }
        );
        return itemDataManager;
    }

    private ArrayList<Item> getItemsFromFirebase2() {
        ItemDataManager itemDataManager = new ItemDataManager();
        ArrayList<Item> items = new ArrayList<>();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference(Constants.DBKeys.ITEMS);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.d("bloop", "onChildAdded: ");
                Item item = null;
                if(snapshot.getValue(Liquid.class) instanceof Liquid){
                    item = snapshot.getValue(Liquid.class);
                } // need to add key for items
                items.add(item);
                itemDataManager.setItemList(items);
                mItems.setValue(itemDataManager);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Item item = null;
                if(snapshot.getValue(Liquid.class) instanceof Liquid){
                    item = snapshot.getValue(Liquid.class);
                }


//                for(int i = 0 ; i < mItems.getValue().getItemList().size(); i++){
//                    if(item.getName().equals(mItems.getValue().getItemList().get(i))){
//                        if(item.isOwned() != mItems.getValue().getItemList().get(i).isOwned()){
//                            item.setOwned(!item.isOwned());
//                        }
//                    }
//                }




//                Log.d("TAG", "onChildChanged: " + item);
//                for (int i = 0; i < items.size(); i++) {
//                    if (items.get(i).getName().equals(item.getName())) {
//                        Log.d("TAG3", "onChildChanged: " + i);
//                        items.set(i, item);
//                    }
//                }
//                Log.d("TAG_All", "onChildChanged: " + items);
                itemDataManager.setItemList(items);
                mItems.setValue(itemDataManager);
            }


            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }
        );
        return items;
    }


    public  ArrayList<Item> getItemsFromFirebase3(){
        ItemDataManager itemDataManager = new ItemDataManager();
        ArrayList<Item> items = new ArrayList<>();
        DBUtil.getInstance().getFullListRef().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot iListSnap : snapshot.getChildren()){
                    Item item = null;
                    if(iListSnap.getValue(Liquid.class) instanceof Liquid) {
                        item = iListSnap.getValue(Liquid.class);
                    }//need to add for more types.
                    items.add(item);
                }
                itemDataManager.setItemList(items);
                mItems.setValue(itemDataManager);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //why is the items empty here? // bug
        return items;
    }


    public ArrayList<Item> getItemsFromFirebase4(){
        ItemDataManager itemDataManager = new ItemDataManager();
        ArrayList<Item> items = new ArrayList<>(); // Allways made?

        DBUtil.getInstance().getFullListRef().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Item item = null;
                if(snapshot.getValue(Liquid.class) instanceof Liquid){
                    item = snapshot.getValue(Liquid.class);
                } // need to add key for items
                items.add(item);
                itemDataManager.setItemList(items);
                mItems.setValue(itemDataManager);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Item itemChanged = null;
                if(snapshot.getValue(Liquid.class) instanceof Liquid){
                    itemChanged = snapshot.getValue(Liquid.class);
                }

                //sounds too weird, why do i use the size when i just made that arraylist?
                for(int i = 0; i < items.size(); i++){ // how is the size not 0 ?
                    Item itemToChange = items.get(i);
                    if (itemToChange.getdBkey() == itemChanged.getdBkey()){
                        Log.d("TAG", "itemToChange: " + itemToChange.getdBkey());
                        Log.d("TAG", "itemChanged: " + itemChanged.getdBkey());
                        items.set(i,itemChanged);
                    }
                }
                itemDataManager.setItemList(items);
                mItems.setValue(itemDataManager);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return items;
    }




    public LiveData<ItemDataManager> getItems() {
        return mItems;
    } // maybe should return the arraylist inside?
}