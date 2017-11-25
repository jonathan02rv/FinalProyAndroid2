package com.example.jhona.clothesshopapp.ui;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jhona.clothesshopapp.R;
import com.example.jhona.clothesshopapp.data.ProductData;
import com.example.jhona.clothesshopapp.presenter.MainContract;
import com.example.jhona.clothesshopapp.presenter.MainPresenter;
import com.example.jhona.clothesshopapp.presenter.ProductContract;
import com.example.jhona.clothesshopapp.presenter.ProductPresenter;
import com.example.jhona.clothesshopapp.storage.PreferencesHelper;
import com.example.jhona.clothesshopapp.storage.request.ApiClient;
import com.example.jhona.clothesshopapp.storage.request.StorageConstant;
import com.example.jhona.clothesshopapp.storage.request.entity.ProductEntity;
import com.example.jhona.clothesshopapp.storage.request.entity.ProductResponse;
import com.example.jhona.clothesshopapp.storage.request.model.Product;
import com.example.jhona.clothesshopapp.ui.adapter.OnItemClickListener;
import com.example.jhona.clothesshopapp.ui.adapter.ProductAdapter;
import com.example.jhona.clothesshopapp.utils.StringUtils;

import java.io.Console;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.jhona.clothesshopapp.storage.db.MyDatabase;
import com.example.jhona.clothesshopapp.storage.db.CRUDOperations;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    private TextView txtUser;
    private ProductPresenter mainPresenter;


    private final int DEFAULT_SPANCOUNT=3;
    private RecyclerView recyclerViewProduct;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<Product> productList;
    private ProductAdapter productAdapter;

    private CRUDOperations crudOperations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setUpPresenter();

        crudOperations= new CRUDOperations(new MyDatabase(this));

        ui();
        carga();



    }

    private void ui() {
        recyclerViewProduct= (RecyclerView)findViewById(R.id.recyclerViewProduct);
        mLayoutManager = new GridLayoutManager(this,2);
        recyclerViewProduct.setLayoutManager(mLayoutManager);

    }
    private void gotoDetails(Product product){
        Intent intent= new Intent(this,ProductDetailsActivity.class);
        startActivity(intent);
    }

    private void gotoDetailsAnimation(Product product,ImageView imageView){
        Intent intent= new Intent(this,ProductDetailsActivity.class);
        intent.putExtra("PRODUCT", product);
        intent.putExtra("IMAGE_TRANSITION", ViewCompat.getTransitionName(imageView));

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                imageView,
                ViewCompat.getTransitionName(imageView));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(intent,options.toBundle());
        }
    }

    private void renderProducts(){
        productAdapter = new ProductAdapter(this,productList);
        productAdapter.setOnItemClickListener(this);
        recyclerViewProduct.setAdapter(productAdapter);
    }

    private void loadProductData() {
        //productList= new ProductData().generate();
        renderProducts();
    }



    @Override
    public void onClikListener(int position, View container, ImageView imageView) {
        if(productList!=null){
            Product product= productList.get(position);
            gotoDetailsAnimation(product,imageView);
        }
    }

    public void  carga()
    {

        String token= PreferencesHelper.getTokenSession(this);


        Map<String, String> map = new HashMap<>();
        map.put("user-token",token);
        Call<ProductResponse> call= ApiClient.getMyApiClient().listproductos(
                StorageConstant.APPLICATIONID, StorageConstant.RESTAPIKEY,map);

        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                //view.hideLoading();
                if(response!=null){
                    ProductResponse notesResponse=null;
                    if(response.isSuccessful()){
                        notesResponse= response.body();

                        productList = notesResponse;
                        loadProductData();



                    }else{

                    }
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {

                String err = "error";
                // view.hideLoading();
                //view.showMessage(t.getMessage());
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actioncarro, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        Intent intent;
        intent=new Intent(MainActivity.this, OrderActivity.class);
        startActivity(intent);
        finish();

        return true ;
    }


}
