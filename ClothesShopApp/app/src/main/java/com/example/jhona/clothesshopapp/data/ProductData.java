package com.example.jhona.clothesshopapp.data;

import com.example.jhona.clothesshopapp.storage.request.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jhonahome on 21/11/17.
 */

public class ProductData {
    public List<Product> getProductList() {
        return productList;
    }

    private List<Product> productList;

    public List<Product> generate(){

        Product product1= new Product();
        product1.setBrand("Adidas");
        product1.setName("Polera");
        product1.setType("Mujer");
        product1.setPrice("S/70.00");
        product1.setStock(10);
        product1.setPhoto("images/poleraMujer1.png");

        Product product2= new Product();
        product2.setBrand("Adidas");
        product2.setName("Polo");
        product2.setType("Mujer");
        product2.setPrice("S/35.00");
        product2.setStock(15);
        product2.setPhoto("images/poloMujer1.png");

        Product product3= new Product();
        product3.setBrand("Adidas");
        product3.setName("Abrigo");
        product3.setType("Mujer");
        product3.setPrice("S/120.00");
        product3.setStock(20);
        product3.setPhoto("images/abrigoMujer1.png");

        Product product4= new Product();
        product4.setBrand("Adidas");
        product4.setName("Polera");
        product4.setType("Hombre");
        product4.setPrice("S/90.00");
        product4.setStock(5);
        product4.setPhoto("images/poleraHombre1.png");

        productList= new ArrayList<>();

        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);

        return productList;
    }
}
