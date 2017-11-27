package com.example.jhona.clothesshopapp.presenter;

import android.util.Log;

import com.example.jhona.clothesshopapp.storage.request.ApiClient;
import com.example.jhona.clothesshopapp.storage.request.StorageConstant;
import com.example.jhona.clothesshopapp.storage.request.entity.LogInBLRaw;
import com.example.jhona.clothesshopapp.storage.request.entity.LogInBLResponse;
import com.example.jhona.clothesshopapp.storage.request.entity.RegisterBLRaw;
import com.example.jhona.clothesshopapp.storage.request.entity.RegisterBLResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by arg_r on 11/26/2017.
 */

public class RegisterPresenter {

    private RegisterContract.View view;

    public void Register(){
        if(view.validateForm()){
            //authentication();
            registrationBL();
        }
    }

    private void registrationBL(){
        String username= view.getEmail();
        String password= view.getPassword();
        String dni = view.getDni();
        String name = view.getName();
        String lasName = view.getLastName();

        final RegisterBLRaw registerBLRaw= new RegisterBLRaw();
        registerBLRaw.setEmail(username);
        registerBLRaw.setPassword(password);
        registerBLRaw.setDni(dni);
        registerBLRaw.setName(name);
        registerBLRaw.setLast_name(lasName);

        Call<RegisterBLResponse> call= ApiClient.getMyApiClient().RegisterBL(
                StorageConstant.APPLICATIONID,
                StorageConstant.RESTAPIKEY,registerBLRaw);

        call.enqueue(new Callback<RegisterBLResponse>() {
            @Override
            public void onResponse(Call<RegisterBLResponse> call, Response<RegisterBLResponse> response) {
                view.hideLoading();
                if(response!=null){
                    RegisterBLResponse registerBLResponse=null;

                    if(response.isSuccessful()){
                        registerBLResponse=response.body();
                        if(registerBLResponse!=null){
                            /*saveSession(logInResponse);*/
                            logIn();
                        }
                    }else{
                        Log.v("CONSOLE", "error "+registerBLResponse);
                    }
                }else{
                    view.showErrorMessage("Ocurrió un error");
                }
            }

            @Override
            public void onFailure(Call<RegisterBLResponse> call, Throwable t) {
                view.hideLoading();
                view.showErrorMessage(t.getMessage());
            }
        });
    }

    public void logIn(){
            authenticationBL();
    }

    private void authenticationBL(){
        String username= view.getEmail();
        String password= view.getPassword();

        final LogInBLRaw logInRaw= new LogInBLRaw();
        logInRaw.setLogin(username);
        logInRaw.setPassword(password);

        Call<LogInBLResponse> call= ApiClient.getMyApiClient().logInBL(
                StorageConstant.APPLICATIONID,
                StorageConstant.RESTAPIKEY,logInRaw);

        call.enqueue(new Callback<LogInBLResponse>() {
            @Override
            public void onResponse(Call<LogInBLResponse> call, Response<LogInBLResponse> response) {
                view.hideLoading();
                if(response!=null){
                    LogInBLResponse logInResponse=null;

                    if(response.isSuccessful()){
                        logInResponse=response.body();
                        if(logInResponse!=null){
                            saveSession(logInResponse);
                            view.showSuccessMessage("Registro exitoso");
                            view.gotoMain();
                        }
                    }else{
                        Log.v("CONSOLE", "error "+logInResponse);
                    }
                }else{
                    view.showErrorMessage("Ocurrió un error");
                }
            }

            @Override
            public void onFailure(Call<LogInBLResponse> call, Throwable t) {
                view.hideLoading();
                view.showErrorMessage(t.getMessage());
            }
        });
    }

    private void saveSession(LogInBLResponse logInBLResponse) {
        view.saveBLSession(logInBLResponse.getName(),logInBLResponse.getToken());
    }

    public void attachView(RegisterContract.View view){
        this.view=view;
    }
    public void detachView(){
        this.view=null;
    }
}
