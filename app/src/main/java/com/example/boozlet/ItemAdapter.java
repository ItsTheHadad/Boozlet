package com.example.boozlet;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.boozlet.Objects.Item;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private Context context; //neccesary?
    private ItemDataManager itemDataManager;

    public ItemAdapter(Context context, ItemDataManager itemDataManager){
        this.context = context;
        this.itemDataManager = itemDataManager; // maybe arraylist?
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_item,parent,false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = getItem(position); // always getting a null item. @@@
        holder.title_TXT_name.setText(item.getName());

        if(item.isOwned()) // should be only from the user not the db main list
            holder.item_IMG_favorite.setImageResource(R.drawable.star_full);
        else
            holder.item_IMG_favorite.setImageResource(R.drawable.star_empty);
    }

    private Item getItem(int position){
        return this.itemDataManager.getItemList().get(position);
    }


    @Override
    public int getItemCount() {
        return this.itemDataManager.getItemList() == null ? 0 : itemDataManager.getItemList().size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{


        private ShapeableImageView item_IMG_favorite;
        private MaterialTextView title_TXT_name;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            item_IMG_favorite = itemView.findViewById(R.id.item_IMG_favorite);
            title_TXT_name = itemView.findViewById(R.id.title_TXT_name);

            item_IMG_favorite.setOnClickListener(v ->{ // need a callback for clicked
               Item item = getItem(getAdapterPosition());
               if(item.isOwned())
                   item.setOwned(false); // set it in db maybe?
               else
                   item.setOwned(true);

               //updateItems(itemDataManager); //makes it work buy why
               //notifyItemChanged(getAdapterPosition()); //@@ makes it work but why
                //need to update the db after that ? or is it changed

                Log.d("bloop", "clicked on "+item.getName()+ " is owned: "+ item.isOwned());
            });

        }
    }


    public void updateItems(ItemDataManager itemDataManager){ //what
        this.itemDataManager = itemDataManager;
        notifyDataSetChanged();
    }



}
