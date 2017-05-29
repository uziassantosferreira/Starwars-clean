package com.starwars.films.data.repository.datasource;

import com.starwars.films.domain.model.Film;

import io.reactivex.Observable;

public interface FilmsDataSource {

    Observable<Film> getFilm(String parameter);

    Observable<Film> saveFilm(Film film);

}



