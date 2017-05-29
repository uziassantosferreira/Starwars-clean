package com.starwars.films.data.repository;

import com.starwars.films.data.repository.datasource.FilmsDataSource;
import com.starwars.films.domain.model.Film;

import io.reactivex.Observable;

public class FilmsRepositoryImpl implements FilmsRepository {

    private final FilmsDataSource ormDataSource;
    private FilmsDataSource restDataSource;

    public FilmsRepositoryImpl(FilmsDataSource ormDataSource, FilmsDataSource restDataSource) {
        this.ormDataSource = ormDataSource;
        this.restDataSource = restDataSource;
    }
    @Override
    public Observable<Film> getFilm(String url, String id) {
        return ormDataSource.getFilm(url)
                .switchIfEmpty(restDataSource.getFilm(id).flatMap(this::saveFilm));
    }

    @Override
    public Observable<Film> saveFilm(Film film) {
        return ormDataSource.saveFilm(film);
    }

}
