package com.starwars.films.domain.model;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.Date;
import java.util.List;

@AutoValue
public abstract class Film {

    @Nullable public abstract Integer id();
    @Nullable public abstract String title();
    @Nullable public abstract Integer episodeId();
    @Nullable public abstract String openingCrawl();
    @Nullable public abstract String director();
    @Nullable public abstract String producer();
    @Nullable public abstract Date releaseDate();
    @Nullable public abstract List<String> characters();
    @Nullable public abstract List<String> planets();
    @Nullable public abstract List<String> starships();
    @Nullable public abstract List<String> vehicles();
    @Nullable public abstract List<String> species();
    @Nullable public abstract Date created();
    @Nullable public abstract Date edited();
    @Nullable public abstract String url();

    public static Film.Builder builder(){
        return new AutoValue_Film.Builder();
    }

    public static TypeAdapter<Film> typeAdapter(Gson gson){
        return new AutoValue_Film.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Film.Builder setId(Integer id);
        public abstract Film.Builder setEpisodeId(Integer episodeId);
        public abstract Film.Builder setTitle(String title);
        public abstract Film.Builder setOpeningCrawl(String openingCrawl);
        public abstract Film.Builder setDirector(String director);
        public abstract Film.Builder setProducer(String producer);
        public abstract Film.Builder setReleaseDate(Date releaseDate);
        public abstract Film.Builder setCharacters(List<String> characters);
        public abstract Film.Builder setPlanets(List<String> planets);
        public abstract Film.Builder setStarships(List<String> starships);
        public abstract Film.Builder setVehicles(List<String> vehicles);
        public abstract Film.Builder setSpecies(List<String> species);
        public abstract Film.Builder setCreated(Date created);
        public abstract Film.Builder setEdited(Date edited);
        public abstract Film.Builder setUrl(String url);
        public abstract Film build();
    }

}
