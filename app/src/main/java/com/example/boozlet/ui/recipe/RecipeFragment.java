package com.example.boozlet.ui.recipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.boozlet.Objects.Recipe;
import com.example.boozlet.Adapters.RecipeAdapter;
import com.example.boozlet.databinding.FragmentRecipesBinding;

import java.util.List;

public class RecipeFragment extends Fragment {

    private FragmentRecipesBinding binding;

    private RecipeAdapter itemAdapter;
    private RecipeViewModel recipeViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        recipeViewModel =
                new ViewModelProvider(this).get(RecipeViewModel.class);

        binding = FragmentRecipesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.mainLSTItems.setLayoutManager(linearLayoutManager);

        itemAdapter = new RecipeAdapter(getContext(),recipeViewModel.getmItems().getValue());

        binding.mainLSTItems.setAdapter(itemAdapter);


        recipeViewModel.getmItems().observe(getViewLifecycleOwner(), new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                itemAdapter.updateItems(recipes);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        recipeViewModel.getRecipesFromFirebase();
    }
}