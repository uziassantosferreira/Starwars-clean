package com.starwars.films.data.repository.datasource.networking;

import com.starwars.films.data.repository.datasource.FilmsDataSource;
import com.starwars.films.data.repository.datasource.networking.mapper.JsonFilmMapper;
import com.starwars.films.domain.model.Film;

import io.reactivex.Observable;

public class FilmsRestApiDataSource implements FilmsDataSource {

    private final FilmsRestApi filmsRestApi;

    public FilmsRestApiDataSource(FilmsRestApi peopleRestApi){
        this.filmsRestApi = peopleRestApi;
    }

    public Observable<Film> getFilm(String url) {
        return filmsRestApi.getFilm(url)
                .map(JsonFilmMapper::transformFrom);
    }

    @Override
    public Observable<Film> saveFilm(Film film) {
        throw new UnsupportedOperationException("getPerson not supported for "
                + this.getClass().getName());
    }

}
