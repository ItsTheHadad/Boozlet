package com.example.boozlet.ui.inventory;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.boozlet.DBUtil;
import com.example.boozlet.Objects.Item;
import com.example.boozlet.ItemDataManager;
import com.example.boozlet.PreDatabaseData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class InventoryViewModel extends ViewModel {

    private MutableLiveData<ItemDataManager> mItems;

    public InventoryViewModel() {
        mItems = new MutableLiveData<>();
        ItemDataManager tempItemDM = new ItemDataManager();
        mItems.setValue(tempItemDM);

    }

    public void getItemsFromFirebase(boolean ownedFilter){
        ItemDataManager itemDataManager = new ItemDataManager();
        DBUtil.getOwnedItems(new DBUtil.OwnedItemsListener() {
            @Override
            public void onSuccess(List<String> ownedItems) {
                ArrayList<Item> items = new ArrayList<>();

                DBUtil.getInstance().getFullListRef().addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                            Item item = dataSnapshot.getValue(Item.class);
                            if (ownedItems.contains(item.getName())) {
                                item.setOwned(true);
                            } else {
                                item.setOwned(false);
                            }
                            if (ownedFilter) {
                                if(item.isOwned()) {
                                    items.add(item);
                                }
                            } else {
                                items.add(item);
                            }
                        }
                        itemDataManager.setItemList(items);
                        mItems.setValue(itemDataManager);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onFailure(String message) {
                itemDataManager.setItemList(PreDatabaseData.getItemsPre().getItemList());
                mItems.setValue(itemDataManager);
            }
        });
    }





    public LiveData<ItemDataManager> getItems() {
        return mItems;
    }
}