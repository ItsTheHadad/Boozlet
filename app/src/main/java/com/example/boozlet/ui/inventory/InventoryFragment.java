package com.example.boozlet.ui.inventory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.boozlet.R;
import com.example.boozlet.databinding.FragmentInventoryBinding;

public class InventoryFragment extends Fragment {

    private RecyclerView myInventoryRV;

    private FragmentInventoryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InventoryViewModel inventoryViewModel =
                new ViewModelProvider(this).get(InventoryViewModel.class);

        binding = FragmentInventoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


//        final TextView textView = binding.textInventory;
//        inventoryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    private void findViews(View view){
        myInventoryRV = view.findViewById(R.id.inventory_mylist);
        myInventoryRV.setHasFixedSize(true);
        myInventoryRV.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}