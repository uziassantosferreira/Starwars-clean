package com.starwars.people.data.repository.datasource.networking.json;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

@AutoValue
public abstract class JsonPerson {
    @Nullable public abstract String name();
    @Nullable public abstract String height();
    @Nullable public abstract String mass();
    @SerializedName("hair_color") @Nullable public abstract String hairColor();
    @SerializedName("skin_color") @Nullable public abstract String skinColor();
    @SerializedName("eye_color") @Nullable public abstract String eyeColor();
    @SerializedName("birth_year") @Nullable public abstract String birthYear();
    @Nullable public abstract String gender();
    @SerializedName("homeworld") @Nullable public abstract String homeworld();
    @Nullable public abstract List<String> films();
    @Nullable public abstract List<String> species();
    @Nullable public abstract List<String> vehicles();
    @Nullable public abstract List<String> starships();
    @Nullable public abstract Date created();
    @Nullable public abstract Date edited();
    @Nullable public abstract String url();

    public static TypeAdapter<JsonPerson> typeAdapter(Gson gson){
        return new AutoValue_JsonPerson.GsonTypeAdapter(gson);
    }

    public static JsonPerson.Builder builder(){
        return new AutoValue_JsonPerson.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract JsonPerson.Builder setName(String name);
        public abstract JsonPerson.Builder setHeight(String height);
        public abstract JsonPerson.Builder setMass(String mass);
        public abstract JsonPerson.Builder setHairColor(String hairColor);
        public abstract JsonPerson.Builder setSkinColor(String skinColor);
        public abstract JsonPerson.Builder setEyeColor(String eyeColor);
        public abstract JsonPerson.Builder setBirthYear(String birthYear);
        public abstract JsonPerson.Builder setGender(String gender);
        public abstract JsonPerson.Builder setHomeworld(String homeworld);
        public abstract JsonPerson.Builder setFilms(List<String> films);
        public abstract JsonPerson.Builder setSpecies(List<String> species);
        public abstract JsonPerson.Builder setVehicles(List<String> vehicles);
        public abstract JsonPerson.Builder setStarships(List<String> starships);
        public abstract JsonPerson.Builder setCreated(Date created);
        public abstract JsonPerson.Builder setEdited(Date edited);
        public abstract JsonPerson.Builder setUrl(String url);
        public abstract JsonPerson build();
    }

}
