package com.starwars.films.domain.usecase;

import com.starwars.films.data.repository.FilmsRepository;
import com.starwars.films.domain.model.Film;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class GetFilmImpl implements GetFilm {

    private List<String> urls;
    private FilmsRepository filmsRepository;

    public GetFilmImpl(FilmsRepository filmsRepository){
        this.filmsRepository = filmsRepository;
    }

    @Override
    public Observable<Film> run() {
        if (urls == null || urls.isEmpty()){
            throw new InvalidParameterException("need to set url");
        }

        List<Observable<Film>> observable = new ArrayList<>(urls.size());
        for (String url: urls){
            String id = url.replaceAll("\\D+", "");
            observable.add(filmsRepository.getFilm(url, id));
        }
        return Observable.merge(observable);
    }

    @Override
    public GetFilm setUrl(List<String> urls) {
        this.urls = urls;
        return this;
    }
}
