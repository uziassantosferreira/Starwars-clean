package com.starwars.films.data.repository.datasource.networking.mapper;

import android.support.annotation.NonNull;

import com.starwars.films.data.repository.datasource.networking.json.JsonFilm;
import com.starwars.films.domain.model.Film;

public class JsonFilmMapper {

    public static Film transformFrom(@NonNull JsonFilm jsonFilm) {
        return Film.builder()
                .setEpisodeId(jsonFilm.episodeId())
                .setTitle(jsonFilm.title())
                .setOpeningCrawl(jsonFilm.openingCrawl())
                .setDirector(jsonFilm.director())
                .setProducer(jsonFilm.producer())
                .setReleaseDate(jsonFilm.releaseDate())
                .setCharacters(jsonFilm.characters())
                .setPlanets(jsonFilm.planets())
                .setStarships(jsonFilm.starships())
                .setVehicles(jsonFilm.vehicles())
                .setSpecies(jsonFilm.species())
                .setCreated(jsonFilm.created())
                .setEdited(jsonFilm.edited())
                .setUrl(jsonFilm.url())
                .build();
    }

}
