package com.example.jhona.clothesshopapp.presenter;

import android.util.Log;

import com.example.jhona.clothesshopapp.storage.request.ApiClient;
import com.example.jhona.clothesshopapp.storage.request.StorageConstant;
import com.example.jhona.clothesshopapp.storage.request.entity.LogInBLRaw;
import com.example.jhona.clothesshopapp.storage.request.entity.LogInBLResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInPresenter {

    private LoginContract.View view;

    public void logIn(){
        if(view.validateForm()){
            //authentication();
            authenticationBL();
        }
    }
    public void goToUserRegister(){
        this.view.gotoUserRegister();
    }

    private void authenticationBL(){
        String username= view.getUsername();
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
                            view.gotoMain();
                        }
                    }else{
                        Log.v("CONSOLE", "error "+logInResponse);
                    }
                }else{
                    view.showMessage("Ocurrió un error");
                }
            }

            @Override
            public void onFailure(Call<LogInBLResponse> call, Throwable t) {
                view.hideLoading();
                view.showMessage(t.getMessage());
            }
        });
    }

    private void saveSession(LogInBLResponse logInBLResponse) {
        view.saveBLSession(logInBLResponse.getName(),logInBLResponse.getToken());
    }

    public void attachView(LoginContract.View view){
        this.view=view;
    }
    public void detachView(){
        this.view=null;
    }
}
