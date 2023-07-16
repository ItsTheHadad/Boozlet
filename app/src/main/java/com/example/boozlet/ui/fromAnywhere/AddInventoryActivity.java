package com.example.boozlet.ui.fromAnywhere;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;

import com.example.boozlet.Adapters.ItemAdapter;
import com.example.boozlet.ItemDataManager;
import com.example.boozlet.R;
import com.example.boozlet.ui.inventory.InventoryViewModel;

public class AddInventoryActivity extends AppCompatActivity {

    private RecyclerView mainLSTItems; // should it be final? like when generating
    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inventory);
        InventoryViewModel inventoryViewModel =
                new ViewModelProvider(this).get(InventoryViewModel.class);

        mainLSTItems = findViewById(R.id.main_LST_items);
        //mainLSTItems.setHasFixedSize(true);
        //not sure if needed
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mainLSTItems.setLayoutManager(linearLayoutManager);

        itemAdapter = new ItemAdapter(this,inventoryViewModel.getItems().getValue(),false);

        mainLSTItems.setAdapter(itemAdapter);
        inventoryViewModel.getItems().observeForever(observer);
        inventoryViewModel.getItemsFromFirebase(false);

        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }




    private Observer<ItemDataManager> observer = new Observer<ItemDataManager>() {
        @Override
        public void onChanged(ItemDataManager itemDataManager) {
            Log.d("bloop", "changed");
            itemAdapter.updateItems(itemDataManager); // should i change the update items method?
        }
    };


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

}