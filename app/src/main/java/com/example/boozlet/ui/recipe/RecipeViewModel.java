package com.example.boozlet.ui.recipe;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.boozlet.Constants;
import com.example.boozlet.DBUtil;
import com.example.boozlet.ItemDataManager;
import com.example.boozlet.Objects.Item;
import com.example.boozlet.Objects.Liquid;
import com.example.boozlet.Objects.Recipe;
import com.example.boozlet.PreDatabaseData;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecipeViewModel extends ViewModel {

    private MutableLiveData<List<Recipe>> mItems;

    public RecipeViewModel() {
        mItems = new MutableLiveData<>();
        List<Recipe> tempItemDM = new ArrayList<>();
        mItems.setValue(tempItemDM);

    }

    public void getRecipesFromFirebase(){
        DBUtil.getOwnedItems(new DBUtil.OwnedItemsListener() {
            @Override
            public void onSuccess(List<String> ownedItems) {
                ArrayList<Recipe> recipes = new ArrayList<>();
                FirebaseDatabase.getInstance().getReference(Constants.DBKeys.RECIPES).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot iListSnap : snapshot.getChildren()){
                            Recipe recipe = iListSnap.getValue(Recipe.class);
                            recipe.setMissingIngredients(3);
                            if(ownedItems.contains(recipe.getIngredientList().get(0).getItem().getName())) {
                                recipe.setMissingIngredients(recipe.getMissingIngredients()-1);
                            }

                            if(ownedItems.contains(recipe.getIngredientList().get(1).getItem().getName())) {
                                recipe.setMissingIngredients(recipe.getMissingIngredients()-1);
                            }

                            if(ownedItems.contains(recipe.getIngredientList().get(2).getItem().getName())) {
                                recipe.setMissingIngredients(recipe.getMissingIngredients()-1);
                            }

                            recipes.add(recipe);
                        }
                        mItems.setValue(recipes);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        mItems.setValue(PreDatabaseData.getRecipesPre());
                    }
                });
            }

            @Override
            public void onFailure(String message) {
                mItems.setValue(PreDatabaseData.getRecipesPre());
            }
        });
    }

    public MutableLiveData<List<Recipe>> getmItems() {
        return mItems;
    }
}