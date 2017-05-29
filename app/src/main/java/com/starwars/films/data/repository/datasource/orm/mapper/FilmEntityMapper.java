package com.starwars.films.data.repository.datasource.orm.mapper;

import com.starwars.films.data.repository.datasource.orm.entity.FilmEntity;
import com.starwars.films.domain.model.Film;

import java.util.ArrayList;
import java.util.List;

public class FilmEntityMapper {

    public static List<Film> transformFrom(List<FilmEntity> filmEntities) {
        List<Film> films = new ArrayList<>(filmEntities.size());
        for (FilmEntity filmEntity : filmEntities)
            films.add(transformFrom(filmEntity));
        return films;
    }

    public static Film transformFrom(FilmEntity filmEntity) {
        return Film.builder()
                .setId(filmEntity.id())
                .setEpisodeId(filmEntity.episodeId())
                .setTitle(filmEntity.title())
                .setOpeningCrawl(filmEntity.openingCrawl())
                .setDirector(filmEntity.director())
                .setProducer(filmEntity.producer())
                .setReleaseDate(filmEntity.releaseDate())
                .setCharacters(filmEntity.characters())
                .setPlanets(filmEntity.planets())
                .setStarships(filmEntity.starships())
                .setVehicles(filmEntity.vehicles())
                .setSpecies(filmEntity.species())
                .setCreated(filmEntity.created())
                .setEdited(filmEntity.edited())
                .setUrl(filmEntity.url())
                .build();
    }

    public static FilmEntity transformFrom(Film film) {
        return FilmEntity.builder()
                .setId(film.id() == null ? 0 : film.id())
                .setEpisodeId(film.episodeId())
                .setTitle(film.title())
                .setOpeningCrawl(film.openingCrawl())
                .setDirector(film.director())
                .setProducer(film.producer())
                .setReleaseDate(film.releaseDate())
                .setCharacters(film.characters())
                .setPlanets(film.planets())
                .setStarships(film.starships())
                .setVehicles(film.vehicles())
                .setSpecies(film.species())
                .setCreated(film.created())
                .setEdited(film.edited())
                .setUrl(film.url())
                .build();
    }
}
