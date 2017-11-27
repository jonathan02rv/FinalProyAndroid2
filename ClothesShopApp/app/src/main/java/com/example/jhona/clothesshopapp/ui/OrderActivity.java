package com.example.jhona.clothesshopapp.ui;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jhona.clothesshopapp.R;
import com.example.jhona.clothesshopapp.presenter.LoginContract;
import com.example.jhona.clothesshopapp.storage.PreferencesHelper;
import com.example.jhona.clothesshopapp.storage.db.CRUDOperations;
import com.example.jhona.clothesshopapp.storage.db.MyDatabase;
import com.example.jhona.clothesshopapp.storage.request.ApiClient;
import com.example.jhona.clothesshopapp.storage.request.StorageConstant;
import com.example.jhona.clothesshopapp.storage.request.entity.CompraRaw;
import com.example.jhona.clothesshopapp.storage.request.entity.CompraResponse;
import com.example.jhona.clothesshopapp.storage.request.entity.LogInBLRaw;
import com.example.jhona.clothesshopapp.storage.request.entity.LogInBLResponse;
import com.example.jhona.clothesshopapp.storage.request.entity.ProductEntity;
import com.example.jhona.clothesshopapp.ui.adapter.OrderAdapter;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {

    private RecyclerView recyclerViewProduct;
    private RecyclerView.LayoutManager mLayoutManager;
    private CRUDOperations crudOperations;
    private OrderAdapter adapter;
    TextView totaltext;
    TextView fecha;
    Double total = 0.0;
    List<ProductEntity> lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        fecha = (TextView)findViewById(R.id.tviToday);
        totaltext = (TextView)findViewById(R.id.tviTotal);


        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        String date = df.format(Calendar.getInstance().getTime());
        fecha.setText(date);


        crudOperations = new CRUDOperations(new MyDatabase(this));
        recyclerViewProduct= (RecyclerView)findViewById(R.id.recyclerViewOrder);

        mLayoutManager = new LinearLayoutManager(OrderActivity.this);

        recyclerViewProduct.setLayoutManager(mLayoutManager);


        lista = crudOperations.getAllProductsPendientes();

        adapter = new OrderAdapter(this, lista);

        recyclerViewProduct.setAdapter(adapter);
        


        for(ProductEntity grade : lista) {     // foreach grade in grades

           total += Double.parseDouble(grade.getTotal()) ;    // print that grade
        }

        totaltext.setText(total.toString());


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    public void FinalizaCompra(View view){
        String user = PreferencesHelper.getUserSession(this);
        DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
        String date = df.format(Calendar.getInstance().getTime());
        String fecha = date;

        final CompraRaw logInRaw= new CompraRaw();
        logInRaw.setCliente(user);
        logInRaw.setFecha(fecha);
        logInRaw.setMonto(total.toString());

        Call<CompraResponse> call= ApiClient.getMyApiClient().combraBL(
                StorageConstant.APPLICATIONID,
                StorageConstant.RESTAPIKEY,logInRaw);

        call.enqueue(new Callback<CompraResponse>() {
            @Override
            public void onResponse(Call<CompraResponse> call, Response<CompraResponse> response) {

                if(response!=null){
                    CompraResponse logInResponse=null;

                    if(response.isSuccessful()){
                        logInResponse=response.body();
                        if(logInResponse!=null){

                            Toast toastConfirmation =
                                    Toast.makeText(getApplicationContext(),
                                            "Se genero la compra correctamente..", Toast.LENGTH_LONG);
                            toastConfirmation.show();


                            for(ProductEntity grade : lista) {

                                grade.setEstado(1);
                                crudOperations.updateNote(grade);
                            }

                            finish();

                        }
                    }else{
                        Log.v("CONSOLE", "error "+logInResponse);
                    }
                }else{

                }
            }

            @Override
            public void onFailure(Call<CompraResponse> call, Throwable t) {

            }
        });
    }




}
