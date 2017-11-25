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

    /*private void authentication() {
        String username= view.getUsername();
        String password= view.getPassword();

        final LogInBLRaw logInBLRaw= new LogInBLRaw();
        logInBLRaw.setLogin(username);
        logInBLRaw.setPassword(password);

        Call<LogInBLResponse> call= ApiClient.getMyApiClient().logInBL(logInBLRaw);
        call.enqueue(new Callback<LogInResponse>() {
            @Override
            public void onResponse(Call<LogInResponse> call, Response<LogInResponse> response) {
                view.hideLoading();
                if(response!=null){
                    LogInResponse logInResponse=null;

                    if(response.isSuccessful()){
                        logInResponse=response.body();
                        if(logInResponse!=null){
                            if(logInResponse.getStatus()==200){
                                Log.v("CONSOLE", "success "+logInResponse);
                                //saveSession(logInResponse);
                                view.gotoMain();
                            }else{
                                Log.v("CONSOLE", "error "+logInResponse);
                            }
                        }
                    }else{
                        Log.v("CONSOLE", "error "+logInResponse);

                        /*JSONObject jsonObject = null;
                        try {
                            jsonObject=new JSONObject (response.errorBody().string());
                        }catch (Exception e){
                            jsonObject= new JSONObject();
                        }

                        logInResponse= GsonHelper.responseToObject(jsonObject.toString());
                        showMessage(logInResponse.getMsg());*/
                    /*}
                }else{
                    view.showMessage("Ocurrió un error");
                }
            }

            @Override
            public void onFailure(Call<LogInResponse> call, Throwable t) {
                view.hideLoading();
                view.showMessage(t.getMessage());
                /*Toast.makeText(LoginActivity.this,
                        "error "+t.getMessage(),Toast.LENGTH_LONG).show();*/
                //hideLoading();
            /*}
        });
    }*/

    public void attachView(LoginContract.View view){
        this.view=view;
    }
    public void detachView(){
        this.view=null;
    }
}
