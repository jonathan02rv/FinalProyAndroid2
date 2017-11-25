package com.example.jhona.clothesshopapp.presenter;


public interface LoginContract {

    public interface View{
        void showLoading();
        void hideLoading();
        void gotoMain();
        void gotoUserRegister();
        void showMessage(String message);
        void saveBLSession(String username, String token);

        boolean validateForm();

        String getUsername();
        String getPassword();
    }
}
