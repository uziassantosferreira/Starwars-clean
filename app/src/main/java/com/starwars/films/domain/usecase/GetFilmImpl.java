package com.starwars.films.domain.usecase;

import com.starwars.films.domain.model.Film;

import java.security.InvalidParameterException;
import java.util.List;

import io.reactivex.Observable;

public class GetFilmImpl implements GetFilm {

    private List<String> urls;

    @Override
    public Observable<Film> run() {
        if (urls == null || urls.isEmpty()){
            throw new InvalidParameterException("need to set url");
        }
        return Observable.empty();
    }

    @Override
    public GetFilm setUrl(List<String> urls) {
        this.urls = urls;
        return this;
    }
}
