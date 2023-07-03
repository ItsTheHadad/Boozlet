package com.example.boozlet.ui.inventory;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.boozlet.ItemAdapter;
import com.example.boozlet.ItemDataManager;
import com.example.boozlet.databinding.FragmentInventoryBinding;

public class InventoryFragment extends Fragment {

    private RecyclerView mainLSTItems; // should it be final? like when generating

    private ItemAdapter itemAdapter;

    private FragmentInventoryBinding binding;


    private Observer<ItemDataManager> observer = new Observer<ItemDataManager>() {
        @Override
        public void onChanged(ItemDataManager itemDataManager) {
            Log.d("bloop", "changed");
            itemAdapter.updateItems(itemDataManager); // should i change the update items method?
        }
    };



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InventoryViewModel inventoryViewModel =
                new ViewModelProvider(this).get(InventoryViewModel.class);

        binding = FragmentInventoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mainLSTItems = binding.mainLSTItems;
        //mainLSTItems.setHasFixedSize(true);
        //not sure if needed
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mainLSTItems.setLayoutManager(linearLayoutManager);

        itemAdapter = new ItemAdapter(getContext(),inventoryViewModel.getItems().getValue());

        mainLSTItems.setAdapter(itemAdapter);
        inventoryViewModel.getItems().observe(getViewLifecycleOwner(),observer);


        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}