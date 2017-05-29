package com.starwars.core.networking.gson;

import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

@GsonTypeAdapterFactory
public abstract class AutoValueGsonAdapterFactory implements TypeAdapterFactory {
    public static TypeAdapterFactory create(){
        return null;
        //TODO CREATE THE FIRST CLASS WITH AUTO VALUE FOR METHOD THIS PUBLIC
      //  return new AutoValueGson_AutoValueGsonAdapterFactory();
    }
}
