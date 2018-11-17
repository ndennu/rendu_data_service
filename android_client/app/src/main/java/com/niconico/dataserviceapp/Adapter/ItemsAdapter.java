package com.niconico.dataserviceapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.niconico.dataserviceapp.Models.Items;
import com.niconico.dataserviceapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder> {

    private Context context;
    private ArrayList<Items> itemsList;
    private Listener listener;

    public ItemsAdapter(Context context, ArrayList<Items> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
    }

    public void setItemsList(ArrayList<Items> itemsList) {
        this.itemsList = itemsList;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View moviesCell = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cell, parent, false);
        return new ItemsViewHolder(moviesCell);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder itemsViewHolder, int i) {
        final Items items = itemsList.get(i);
        itemsViewHolder.itemLabel.setText(items.getName());
        itemsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) listener.onItemClick(items);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class ItemsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_label)
        TextView itemLabel;

        private long id;

        public ItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }
    }

    public interface Listener {
        void onItemClick(Items item);
    }
}
