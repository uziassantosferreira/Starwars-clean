package com.starwars.people.data.repository.datasource.networking;

import com.starwars.people.data.repository.datasource.networking.json.JsonPerson;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PeopleRestApi {

    @GET("people/{id}/")
    Observable<JsonPerson> getPerson(@Path("id") String idUrl);
}
