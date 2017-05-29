package com.starwars.films.data.repository;

import com.starwars.films.domain.model.Film;

import io.reactivex.Observable;

public interface FilmsRepository {

    Observable<Film> getFilm(String url, String id);

    Observable<Film> saveFilm(Film film);

}