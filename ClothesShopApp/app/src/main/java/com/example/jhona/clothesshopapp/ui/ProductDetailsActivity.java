package com.example.jhona.clothesshopapp.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jhona.clothesshopapp.R;
import com.example.jhona.clothesshopapp.storage.PreferencesHelper;
import com.example.jhona.clothesshopapp.storage.db.CRUDOperations;
import com.example.jhona.clothesshopapp.storage.db.MyDatabase;
import com.example.jhona.clothesshopapp.storage.request.entity.ProductEntity;
import com.example.jhona.clothesshopapp.storage.request.model.Product;
import com.squareup.picasso.Picasso;


import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

public class ProductDetailsActivity extends AppCompatActivity {

    private Product product;
    private String imageTransitionName;

    private TextView tviName;
    private TextView tviBrand;
    private TextView tviPrice;
    private ImageView imageView;
    private Button btnAdd;
    private EditText edtCant;
    private CRUDOperations crudOperations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        crudOperations = new CRUDOperations(new MyDatabase(this));
        extras();
        ui();
        populate();
    }


    private void ui() {

        edtCant = (EditText) findViewById(R.id.tviAmount);
        tviBrand = (TextView)findViewById(R.id.tviBrand);
        tviPrice = (TextView)findViewById(R.id.tviPrice);
        tviName = (TextView)findViewById(R.id.tviName);
        imageView = (ImageView) findViewById(R.id.imageView);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void populate() {
        if(product!=null){

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imageView.setTransitionName(imageTransitionName);
            }

            //imageView.setImageBitmap(getBitmapFromAssets(product.getPhoto()));
             Picasso.with(imageView.getContext()).load(product.getUrl()).into(
                            imageView);

            tviBrand.setText(product.getBrand()+" - "+product.getType());
            tviName.setText(product.getName());
            tviPrice.setText("S/."+product.getPrice());
        }
    }

    private void extras() {
        if(getIntent()!=null && getIntent().getExtras()!=null){
            product= (Product) getIntent().getExtras().getSerializable("PRODUCT");
            imageTransitionName= getIntent().getExtras().getString("IMAGE_TRANSITION");
        }
    }


    public void agregaProducto(View view) {


       Integer canitidad = Integer.parseInt(edtCant.getText().toString());

        ProductEntity objnuevo = new ProductEntity();

        if(canitidad > 0)
        {
            objnuevo.setNombre(product.getName());
            objnuevo.setBrand(product.getBrand());
            objnuevo.setCantidad(canitidad.toString());
            String total = canitidad * Double.parseDouble(product.getPrice()) + "";
            objnuevo.setTotal(total);
            objnuevo.setEstado(0);

            crudOperations.addProducto(objnuevo);

            Toast toastConfirmation =
                    Toast.makeText(getApplicationContext(),
                            "Se agregó correctamente al carrito.", Toast.LENGTH_SHORT);
            toastConfirmation.show();


            btnAdd.setEnabled(false);

            TimerTask task = new TimerTask()
            {
                @Override
                public void run() {
                    Intent intent;
                    intent=new Intent(ProductDetailsActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            };

            Timer timer = new Timer();
            timer.schedule(task, 3000);
        }
        else
        {
            Toast toastConfirmation =
                    Toast.makeText(getApplicationContext(),
                            "Necesita agregar una cantidad.", Toast.LENGTH_SHORT);
            toastConfirmation.show();
        }


    }
}
