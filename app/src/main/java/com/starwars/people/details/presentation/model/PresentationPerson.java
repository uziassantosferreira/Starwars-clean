package com.starwars.people.details.presentation.model;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

import java.util.Date;
import java.util.List;

@AutoValue
public abstract class PresentationPerson {

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


    public static Builder builder(){
        return new AutoValue_PresentationPerson.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setName(String name);
        public abstract Builder setHeight(String height);
        public abstract Builder setMass(String mass);
        public abstract Builder setHairColor(String hairColor);
        public abstract Builder setSkinColor(String skinColor);
        public abstract Builder setEyeColor(String eyeColor);
        public abstract Builder setBirthYear(String birthYear);
        public abstract Builder setGender(String gender);
        public abstract Builder setHomeworld(String homeworld);
        public abstract Builder setFilms(List<String> films);
        public abstract Builder setSpecies(List<String> species);
        public abstract Builder setVehicles(List<String> vehicles);
        public abstract Builder setStarships(List<String> starships);
        public abstract Builder setCreated(Date created);
        public abstract Builder setEdited(Date edited);
        public abstract Builder setUrl(String url);
        public abstract PresentationPerson build();
    }

}
