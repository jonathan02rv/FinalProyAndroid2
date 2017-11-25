package com.example.jhona.clothesshopapp.ui.adapter;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jhona.clothesshopapp.R;
import com.example.jhona.clothesshopapp.storage.request.entity.ProductEntity;
import com.example.jhona.clothesshopapp.storage.request.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by jhonahome on 24/11/17.
 */

public class OrderAdapter extends  RecyclerView.Adapter<OrderAdapter.ViewHolder>{

    private List<ProductEntity> orderList;
    private Context context;



    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tviBrand;
        public TextView tviName;
        public TextView tviPrice;
        public View view;
        public ViewHolder(View  v) {
            super(v);
            this.view = v;
            tviBrand = (TextView) v.findViewById(R.id.tviBrand);
            tviName= (TextView) v.findViewById(R.id.tviName);
            tviPrice= (TextView) v.findViewById(R.id.tviPrice);

        }
    }
    public OrderAdapter(Context context,List<ProductEntity> productList) {
        this.context= context;
        this.orderList = productList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_order, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        ProductEntity product= orderList.get(position);
        holder.tviBrand.setText(product.getBrand()+" - "+product.getNombre());
        holder.tviName.setText( "Cantidad: " + product.getCantidad());
        holder.tviPrice.setText(product.getTotal());


        // holder.iviPhoto.setImageBitmap(getBitmapFromAssets(product.getPhoto()));


    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
}
