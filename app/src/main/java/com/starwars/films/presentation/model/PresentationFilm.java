package com.starwars.films.presentation.model;

import com.google.auto.value.AutoValue;

import java.io.Serializable;

@AutoValue
public abstract class PresentationFilm {

    public abstract String title();
    public abstract String openingCrawl();


    public static PresentationFilm.Builder builder(){
        return new AutoValue_PresentationFilm.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract PresentationFilm.Builder setTitle(String title);
        public abstract PresentationFilm.Builder setOpeningCrawl(String openingCrawl);
        public abstract PresentationFilm build();
    }

}
