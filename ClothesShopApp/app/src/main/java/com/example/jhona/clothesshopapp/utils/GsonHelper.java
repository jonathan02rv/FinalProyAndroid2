package com.example.jhona.clothesshopapp.utils;

import com.example.jhona.clothesshopapp.storage.request.entity.LogInBLResponse;
import com.google.gson.GsonBuilder;

/**
 * Created by emedinaa on 20/10/17.
 */

public class GsonHelper {

    public static LogInBLResponse responseToObject(String json){
        if(json==null){
            return new LogInBLResponse();
        }
        GsonBuilder gson = new GsonBuilder();
        //Type collectionType = new TypeToken<T>(){}.getType();
        LogInBLResponse logInBLResponse = gson.create().fromJson(json, LogInBLResponse.class);

        return logInBLResponse;
    }
}
