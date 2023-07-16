package com.example.boozlet.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.boozlet.Objects.Ingredient;
import com.example.boozlet.Objects.Recipe;
import com.example.boozlet.R;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ItemViewHolder> {

    private Context context; //neccesary? only because of glide
    private List<Recipe> recipes;

    public RecipeAdapter(Context context, List<Recipe> recipes){
        this.context = context;
        this.recipes = recipes;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item,parent,false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Recipe recipe = recipes.get(holder.getAdapterPosition());
        holder.tvTitle.setText(recipe.getName());
        ArrayList<Ingredient> tempIngList = recipe.getIngredientList();
        String ing1 = tempIngList.get(0).getMl() + " " + tempIngList.get(0).getItem().getName();
        String ing2 = tempIngList.get(1).getMl() + " " + tempIngList.get(1).getItem().getName();
        String ing3 = tempIngList.get(2).getMl() + " " + tempIngList.get(2).getItem().getName();
        holder.tvIngredient1.setText(ing1);
        holder.tvIngredient2.setText(ing2);
        holder.tvIngredient3.setText(ing3);
        Glide.with(context)
                .load(recipe.getPicture())
                .into(holder.ivPic);


        if (recipe.getMissingIngredients() == 0) {
            holder.tvSubTitle.setText("All Ingredients are available");
        } else if (recipe.getMissingIngredients() == 1) {
            holder.tvSubTitle.setText(recipe.getMissingIngredients() + " ingredients are missing");
        }else {
            holder.tvSubTitle.setText(recipe.getMissingIngredients() + " ingredients are missing");
        }

    }

    private Recipe getItem(int position){
        return recipes.get(position);
    }


    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle, tvSubTitle, tvIngredient1, tvIngredient2, tvIngredient3;
        ImageView ivPic;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvSubTitle = itemView.findViewById(R.id.tv_subtitle);
            tvIngredient1 = itemView.findViewById(R.id.tv_ing1);
            tvIngredient2 = itemView.findViewById(R.id.tv_ing2);
            tvIngredient3 = itemView.findViewById(R.id.tv_ing3);
            ivPic = itemView.findViewById(R.id.pic);

        }
    }


    public void updateItems(List<Recipe> recipes){ //what
        this.recipes = recipes;
        notifyDataSetChanged();
    }



}
