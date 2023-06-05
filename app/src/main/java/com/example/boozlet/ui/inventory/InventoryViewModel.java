package com.example.boozlet.ui.inventory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.boozlet.Constants;
import com.example.boozlet.Item;
import com.example.boozlet.ItemDataManager;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InventoryViewModel extends ViewModel {

    private final MutableLiveData<ItemDataManager> mItems;

    public InventoryViewModel() {
        mItems = new MutableLiveData<>();
        mItems.setValue(getItemsFromFirebase());
    }

    private ItemDataManager getItemsFromFirebase() {
        ItemDataManager itemDataManager = new ItemDataManager();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference(Constants.DBKeys.ITEMS);

        databaseReference.addChildEventListener(
                new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        Item item = snapshot.getValue(Item.class);
                        itemDataManager.addItems(item);
                        mItems.setValue(itemDataManager);
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        Item item = snapshot.getValue(Item.class);
                        for (int i = 0; i < itemDataManager.getItems().size(); i++) {
                            if(itemDataManager.getItems().get(i).getName().equals(item.getName())){
                                itemDataManager.getItems().set(i,item);
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


    public LiveData<ItemDataManager> getItems() {
        return mItems;
    } // maybe should return the arraylist inside?
}