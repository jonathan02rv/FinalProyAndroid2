package com.example.jhona.clothesshopapp.presenter;

import android.util.Log;

import com.example.jhona.clothesshopapp.storage.request.ApiClient;
import com.example.jhona.clothesshopapp.storage.request.StorageConstant;
import com.example.jhona.clothesshopapp.storage.request.entity.ProductResponse;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jhonahome on 24/11/17.
 */

public class ProductPresenter {

    private ProductContract.ProductosView view;

    public void retrieveProductos(String token)
    {
        view.showLoading();

        Map<String, String> map = new HashMap<>();
        map.put("user-token",token);
        Call<ProductResponse> call= ApiClient.getMyApiClient().listproductos(
                StorageConstant.APPLICATIONID, StorageConstant.RESTAPIKEY,map);

        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                view.hideLoading();
                if(response!=null){
                    ProductResponse notesResponse=null;
                    if(response.isSuccessful()){
                        notesResponse= response.body();
                       // view.renderProductos(notesResponse);
                        //error
                    }else{
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                view.hideLoading();
                view.showMessage(t.getMessage());
            }
        });



    }

    public void detachView(){
        this.view=null;
    }
    public void attachView(ProductContract.ProductosView view){
        this.view=view;
        Log.v("CONSOLE", " LogInPresenter attachView ");
    }


}
