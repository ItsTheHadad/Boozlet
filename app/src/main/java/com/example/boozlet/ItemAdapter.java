package com.example.boozlet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private Context context;
    private ItemDataManager itemDataManager;

    public ItemAdapter(Context context,ItemDataManager itemDataManager){
        this.context = context;
        this.itemDataManager = itemDataManager;
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
        Item item = getItem(position);
        holder.title_TXT_name.setText(item.getName());
        if(item.isOwned())
            holder.item_IMG_favorite.setImageResource(R.drawable.star_full);
        else
            holder.item_IMG_favorite.setImageResource(R.drawable.star_empty);
    }

    private Item getItem(int position){
        return this.itemDataManager.getItems().get(position);
    }

    @Override
    public int getItemCount() {
        return this.itemDataManager.getItems() == null ? 0 : itemDataManager.getItems().size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{


        private ShapeableImageView item_IMG_favorite;
        private MaterialTextView title_TXT_name;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            item_IMG_favorite = itemView.findViewById(R.id.item_IMG_favorite);
            title_TXT_name = itemView.findViewById(R.id.title_TXT_name);
        }
    }

    public void updateItems(ItemDataManager itemDataManager){
        this.itemDataManager = itemDataManager;
        notifyDataSetChanged();
    }


}
