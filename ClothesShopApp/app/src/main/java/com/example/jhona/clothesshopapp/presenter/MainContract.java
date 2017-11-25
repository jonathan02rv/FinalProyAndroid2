package com.example.jhona.clothesshopapp.presenter;


import android.view.View;
import android.widget.ImageView;

public interface MainContract {

    interface MainView{
        void showMessage(String message);
        void showLoading();
        void hideLoading();
    }

    void onClikListener(int position, View container, ImageView imageView);
}
