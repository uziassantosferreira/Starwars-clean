package com.starwars.people.domain.model;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.Date;
import java.util.List;

@AutoValue
public abstract class Person {

    @Nullable public abstract Integer id();
    @Nullable public abstract String name();
    @Nullable public abstract String height();
    @Nullable public abstract String mass();
    @Nullable public abstract String hairColor();
    @Nullable public abstract String skinColor();
    @Nullable public abstract String eyeColor();
    @Nullable public abstract String birthYear();
    @Nullable public abstract String gender();
    @Nullable public abstract String homeworld();
    @Nullable public abstract List<String> films();
    @Nullable public abstract List<String> species();
    @Nullable public abstract List<String> vehicles();
    @Nullable public abstract List<String> starships();
    @Nullable public abstract Date created();
    @Nullable public abstract Date edited();
    @Nullable public abstract String url();

    public static TypeAdapter<Person> typeAdapter(Gson gson){
        return new AutoValue_Person.GsonTypeAdapter(gson);
    }

    public static Person.Builder builder(){
        return new AutoValue_Person.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Person.Builder setId(Integer id);
        public abstract Person.Builder setName(String name);
        public abstract Person.Builder setHeight(String height);
        public abstract Person.Builder setMass(String mass);
        public abstract Person.Builder setHairColor(String hairColor);
        public abstract Person.Builder setSkinColor(String skinColor);
        public abstract Person.Builder setEyeColor(String eyeColor);
        public abstract Person.Builder setBirthYear(String birthYear);
        public abstract Person.Builder setGender(String gender);
        public abstract Person.Builder setHomeworld(String homeworld);
        public abstract Person.Builder setFilms(List<String> films);
        public abstract Person.Builder setSpecies(List<String> species);
        public abstract Person.Builder setVehicles(List<String> vehicles);
        public abstract Person.Builder setStarships(List<String> starships);
        public abstract Person.Builder setCreated(Date created);
        public abstract Person.Builder setEdited(Date edited);
        public abstract Person.Builder setUrl(String url);
        public abstract Person build();
    }

}
