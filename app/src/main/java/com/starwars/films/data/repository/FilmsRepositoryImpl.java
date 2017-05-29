package com.starwars.films.data.repository;

import com.starwars.films.domain.model.Film;

import io.reactivex.Observable;

public class FilmsRepositoryImpl implements FilmsRepository {

    @Override
    public Observable<Film> getFilm(String url, String id) {
        return Observable.empty();
    }

    @Override
    public Observable<Film> saveFilm(Film film) {
        return Observable.empty();
    }

}
