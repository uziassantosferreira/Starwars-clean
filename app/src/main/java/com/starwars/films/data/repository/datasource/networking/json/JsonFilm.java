package com.starwars.films.data.repository.datasource.networking.json;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

@AutoValue
public abstract class JsonFilm {

    @Nullable public abstract String title();
    @SerializedName("episode_id") @Nullable public abstract Integer episodeId();
    @SerializedName("opening_crawl") @Nullable public abstract String openingCrawl();
    @Nullable public abstract String director();
    @Nullable public abstract String producer();
    @SerializedName("release_date") @Nullable public abstract Date releaseDate();
    @Nullable public abstract List<String> characters();
    @Nullable public abstract List<String> planets();
    @Nullable public abstract List<String> starships();
    @Nullable public abstract List<String> vehicles();
    @Nullable public abstract List<String> species();
    @Nullable public abstract Date created();
    @Nullable public abstract Date edited();
    @Nullable public abstract String url();

    public static JsonFilm.Builder builder(){
        return new AutoValue_JsonFilm.Builder();
    }

    public static TypeAdapter<JsonFilm> typeAdapter(Gson gson){
        return new AutoValue_JsonFilm.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract JsonFilm.Builder setEpisodeId(Integer episodeId);
        public abstract JsonFilm.Builder setTitle(String title);
        public abstract JsonFilm.Builder setOpeningCrawl(String openingCrawl);
        public abstract JsonFilm.Builder setDirector(String director);
        public abstract JsonFilm.Builder setProducer(String producer);
        public abstract JsonFilm.Builder setReleaseDate(Date releaseDate);
        public abstract JsonFilm.Builder setCharacters(List<String> characters);
        public abstract JsonFilm.Builder setPlanets(List<String> planets);
        public abstract JsonFilm.Builder setStarships(List<String> starships);
        public abstract JsonFilm.Builder setVehicles(List<String> vehicles);
        public abstract JsonFilm.Builder setSpecies(List<String> species);
        public abstract JsonFilm.Builder setCreated(Date created);
        public abstract JsonFilm.Builder setEdited(Date edited);
        public abstract JsonFilm.Builder setUrl(String url);
        public abstract JsonFilm build();
    }
}
