package com.starwars.films.data.repository.datasource.orm.entity;

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
@Table(name = "film")
@AutoValue
public abstract class FilmEntity implements Persistable {

    @Key @Generated
    public abstract int id();
    @Nullable public abstract String title();
    @Nullable public abstract Integer episodeId();
    @Nullable public abstract String openingCrawl();
    @Nullable public abstract String director();
    @Nullable public abstract String producer();
    @Nullable public abstract Date releaseDate();
    @Convert(StringListConverter.class)
    @Nullable public abstract List<String> characters();
    @Convert(StringListConverter.class)
    @Nullable public abstract List<String> planets();
    @Convert(StringListConverter.class)
    @Nullable public abstract List<String> starships();
    @Convert(StringListConverter.class)
    @Nullable public abstract List<String> vehicles();
    @Convert(StringListConverter.class)
    @Nullable public abstract List<String> species();
    @Nullable public abstract Date created();
    @Nullable public abstract Date edited();
    @Nullable public abstract String url();

    public static FilmEntity.Builder builder(){
        return new AutoValue_FilmEntity.Builder()
                .setId(-1);
    }

    public static TypeAdapter<FilmEntity> typeAdapter(Gson gson){
        return new AutoValue_FilmEntity.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract FilmEntity.Builder setId(int id);
        public abstract FilmEntity.Builder setEpisodeId(Integer episodeId);
        public abstract FilmEntity.Builder setTitle(String title);
        public abstract FilmEntity.Builder setOpeningCrawl(String openingCrawl);
        public abstract FilmEntity.Builder setDirector(String director);
        public abstract FilmEntity.Builder setProducer(String producer);
        public abstract FilmEntity.Builder setReleaseDate(Date releaseDate);
        public abstract FilmEntity.Builder setCharacters(List<String> characters);
        public abstract FilmEntity.Builder setPlanets(List<String> planets);
        public abstract FilmEntity.Builder setStarships(List<String> starships);
        public abstract FilmEntity.Builder setVehicles(List<String> vehicles);
        public abstract FilmEntity.Builder setSpecies(List<String> species);
        public abstract FilmEntity.Builder setCreated(Date created);
        public abstract FilmEntity.Builder setEdited(Date edited);
        public abstract FilmEntity.Builder setUrl(String url);
        public abstract FilmEntity build();
    }

}
