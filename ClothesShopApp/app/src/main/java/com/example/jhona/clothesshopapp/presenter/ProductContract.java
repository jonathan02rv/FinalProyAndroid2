package com.example.jhona.clothesshopapp.presenter;

import com.example.jhona.clothesshopapp.storage.request.entity.ProductEntity;

import java.util.List;

/**
 * Created by jhonahome on 24/11/17.
 */

public interface ProductContract {

    interface ProductosView{
        void showMessage(String message);
        void showLoading();
        void hideLoading();
        void emptyProductos();
        void retrieveProductos();
        void renderProductos(List<ProductEntity> notes);
        void gotoProducto(int action, ProductEntity noteEntity);

    }

}
