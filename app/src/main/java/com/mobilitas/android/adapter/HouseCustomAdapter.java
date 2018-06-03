package com.mobilitas.android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobilitas.android.R;
import com.mobilitas.android.house.House;

import java.util.List;
import java.util.Random;

public class HouseCustomAdapter extends RecyclerView.Adapter<HouseCustomAdapter.MyViewHolder> {

    private Context mContext;
    private List<House> houseList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, address, price;
        public LinearLayout ll_card_bike;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            address = (TextView) view.findViewById(R.id.address);
            price = (TextView) view.findViewById(R.id.price);
            ll_card_bike = (LinearLayout) view.findViewById(R.id.newsfeeds_card);
        }
    }

    public HouseCustomAdapter(Context mContext, List<House> houseList) {
        this.mContext = mContext;
        this.houseList = houseList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_house, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final House house = houseList.get(position);
        holder.name.setText("" + house.getName());
        holder.address.setText("" + house.getAddress());
        holder.price.setText("R$" + house.getPrice());
    }

    @Override
    public int getItemCount() {
        return houseList.size();
    }

}