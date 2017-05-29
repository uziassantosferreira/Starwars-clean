package com.starwars.films.domain.usecase;

import com.starwars.core.domain.usecase.UseCase;
import com.starwars.films.domain.model.Film;

import java.util.List;

import io.reactivex.Observable;

public interface GetFilm extends UseCase<Observable<Film>> {
    GetFilm setUrl(List<String> urls);
}
