package com.starwars.films.presentation.mapper;

import com.starwars.films.domain.model.Film;
import com.starwars.films.presentation.model.PresentationFilm;

import io.reactivex.functions.Function;

public class PresentationFilmMapper {

    public static Function<Film, PresentationFilm> transformFrom(){
        return PresentationFilmMapper::transformFrom;
    }

    public static PresentationFilm transformFrom(Film film) {
        return PresentationFilm.builder()
                .setTitle(film.title())
                .setOpeningCrawl(film.openingCrawl())
                .build();
    }
    
}
