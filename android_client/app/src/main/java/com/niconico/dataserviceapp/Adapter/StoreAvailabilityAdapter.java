package com.niconico.dataserviceapp.Adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.niconico.dataserviceapp.Models.StoreAvailability;
import com.niconico.dataserviceapp.R;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoreAvailabilityAdapter extends RecyclerView.Adapter<StoreAvailabilityAdapter.ViewHolder> {

    private List<StoreAvailability> storeList;
    private Listener listener;

    public StoreAvailabilityAdapter(List<StoreAvailability> storeList) {
        this.storeList = storeList;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public StoreAvailabilityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View cell = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.store_cell, viewGroup, false);
        return new StoreAvailabilityAdapter.ViewHolder(cell);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreAvailabilityAdapter.ViewHolder viewHolder, int i) {
        final StoreAvailability store = storeList.get(i);

        viewHolder.storeName.setText(store.getName());
        viewHolder.storeAddress.setText(store.getAddress());
        viewHolder.storeStock.setText(String.format(Locale.getDefault(), "Stock: %d", store.getQuantity()));
        viewHolder.storeDistance.setText(String.format(Locale.getDefault(), "Distance: %s km", store.getDistance() / 1000));
        viewHolder.storeCrowd.setText(String.format(Locale.getDefault(), "Affluence: %d personnes", store.getVisitor()));

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) listener.onClick(store);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.storeList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.itemView)
        ConstraintLayout itemView;
        @BindView(R.id.store_name)
        TextView storeName;
        @BindView(R.id.store_address)
        TextView storeAddress;
        @BindView(R.id.store_stock)
        TextView storeStock;
        @BindView(R.id.store_distance)
        TextView storeDistance;
        @BindView(R.id.store_crowd)
        TextView storeCrowd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface Listener {
        void onClick(StoreAvailability store);
    }
}
