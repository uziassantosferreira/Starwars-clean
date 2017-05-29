package com.starwars.people.data.repository.datasource.orm.entity;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.starwars.core.database.converter.StringListConverter;

import java.util.Date;
import java.util.List;

import io.requery.Convert;
import io.requery.Entity;
import io.requery.Generated;
import io.requery.Key;
import io.requery.Persistable;
import io.requery.Table;

@Entity
@Table(name = "person")
@AutoValue
public abstract class PersonEntity implements Persistable {

    @Key @Generated
    public abstract int id();
    @Nullable public abstract String name();
    @Nullable public abstract String height();
    @Nullable public abstract String mass();
    @Nullable public abstract String hairColor();
    @Nullable public abstract String skinColor();
    @Nullable public abstract String eyeColor();
    @Nullable public abstract String birthYear();
    @Nullable public abstract String gender();
    @Nullable public abstract String homeworld();
    @Convert(StringListConverter.class)
    @Nullable public abstract List<String> films();
    @Convert(StringListConverter.class)
    @Nullable public abstract List<String> species();
    @Convert(StringListConverter.class)
    @Nullable public abstract List<String> vehicles();
    @Convert(StringListConverter.class)
    @Nullable public abstract List<String> starships();
    @Nullable public abstract Date created();
    @Nullable public abstract Date edited();
    public abstract String url();

    public static PersonEntity.Builder builder(){
        return new AutoValue_PersonEntity.Builder()
                .setId(-1);
    }

    public static TypeAdapter<PersonEntity> typeAdapter(Gson gson){
        return new AutoValue_PersonEntity.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setId(int id);
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
        public abstract PersonEntity build();
    }

}
