package com.example.jhona.clothesshopapp.presenter;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter {

    private MainContract.MainView view;

    public void load(){
        view.showLoading();
    }


    public void attachView(MainContract.MainView view){
        this.view=view;
        Log.v("CONSOLE", " LogInPresenter attachView ");
    }

}
