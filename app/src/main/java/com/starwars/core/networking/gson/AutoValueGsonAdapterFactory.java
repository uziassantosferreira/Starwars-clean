package com.starwars.core.networking.gson;

import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

@GsonTypeAdapterFactory
public abstract class AutoValueGsonAdapterFactory implements TypeAdapterFactory {
    public static TypeAdapterFactory create(){
       return new AutoValueGson_AutoValueGsonAdapterFactory();
    }
}
