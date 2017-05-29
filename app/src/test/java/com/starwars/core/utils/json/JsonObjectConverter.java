package com.starwars.core.utils.json;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.starwars.core.networking.gson.AutoValueGson_AutoValueGsonAdapterFactory;

import java.lang.reflect.Type;
import java.util.List;

public class JsonObjectConverter {

    private static Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(AutoValueGson_AutoValueGsonAdapterFactory.create())
            .create();

    public static <T> T convertFromJson(String json, Class<T> jsonObjectClass) {
        return gson.fromJson(json, jsonObjectClass);
    }
    public static <T> List<T> convertArrayFromJson(String json, Type type){
        return gson.fromJson(json, type);
    }
}
