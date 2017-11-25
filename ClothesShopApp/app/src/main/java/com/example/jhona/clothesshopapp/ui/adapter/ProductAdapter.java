package com.example.jhona.clothesshopapp.ui.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jhona.clothesshopapp.R;
import com.example.jhona.clothesshopapp.presenter.MainContract;
import com.example.jhona.clothesshopapp.storage.request.entity.ProductEntity;
import com.example.jhona.clothesshopapp.storage.request.model.Product;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by jhonahome on 21/11/17.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{
    private List<Product> productList;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tviBrand;
        public TextView tviName;
        public TextView tviPrice;
        public ImageView iviPhoto;
        public View view;
        public ViewHolder(View  v) {
            super(v);
            this.view = v;
            tviBrand = (TextView) v.findViewById(R.id.tviBrand);
            tviName= (TextView) v.findViewById(R.id.tviName);
            tviPrice= (TextView) v.findViewById(R.id.tviPrice);
            iviPhoto= (ImageView) v.findViewById(R.id.iviPhoto);
        }
    }

    public ProductAdapter(Context context,List<Product> productList) {
        this.context= context;
        this.productList = productList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_product, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Product product= productList.get(position);
        holder.tviBrand.setText(product.getBrand()+" - "+product.getType());
        holder.tviName.setText(product.getName());
        holder.tviPrice.setText(product.getPrice());

        Picasso.with(holder.iviPhoto.getContext()).load(product.getUrl()).into(
                holder.iviPhoto);

       // holder.iviPhoto.setImageBitmap(getBitmapFromAssets(product.getPhoto()));

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener!=null){
                    Log.v("ADAPTER", "iviPhoto "+holder.iviPhoto);
                    ViewCompat.setTransitionName(holder.iviPhoto, "iviPhoto");
                    onItemClickListener.onClikListener(position,view,holder.iviPhoto);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }



    public Bitmap getBitmapFromAssets(String fileName) {
        AssetManager assetManager = context.getAssets();

        InputStream istr = null;
        try {
            istr = assetManager.open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(istr);

        return bitmap;
    }
}

