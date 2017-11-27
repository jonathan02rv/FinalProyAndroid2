package com.example.jhona.clothesshopapp.presenter;

/**
 * Created by arg_r on 11/26/2017.
 */

public interface RegisterContract {

    public interface View {

        void showLoading();
        void hideLoading();
        void gotoMain();
        void showErrorMessage(String message);
        void showSuccessMessage(String message);
        boolean validateForm();
        void saveBLSession(String username, String token);

        String getEmail();
        String getPassword();
        String getDni();
        String getName();
        String getLastName();
        String getConfPassword();

    }

}
