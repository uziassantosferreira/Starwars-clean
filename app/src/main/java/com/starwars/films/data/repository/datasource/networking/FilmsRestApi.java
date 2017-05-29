package com.starwars.films.data.repository.datasource.networking;

import com.starwars.films.data.repository.datasource.networking.json.JsonFilm;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FilmsRestApi {

    @GET("films/{id}/")
    Observable<JsonFilm> getFilm(@Path("id") String idUrl);

}
