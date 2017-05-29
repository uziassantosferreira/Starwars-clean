package com.starwars.films.data.repository.datasource.networking;

import com.starwars.core.networking.MockRestApi;
import com.starwars.core.utils.json.JsonObjectConverter;
import com.starwars.films.data.repository.datasource.networking.json.JsonFilm;
import com.starwars.films.di.DaggerFilmsComponentTest;
import com.starwars.films.di.FilmsComponentTest;
import com.starwars.people.data.repository.datasource.networking.PeopleRestApi;
import com.starwars.people.data.repository.datasource.networking.json.JsonPerson;
import com.starwars.people.di.DaggerPeopleComponentTest;
import com.starwars.people.di.PeopleComponentTest;

import org.junit.Test;

import java.io.IOException;

import io.reactivex.observers.TestObserver;

public class GetFilmMockRestApiTest extends MockRestApi<FilmsRestApi> {

    private static final String MOCK_ID = "1";

    @Override
    public void setUp() throws IOException {
        super.setUp();

        FilmsComponentTest filmsComponentTest = DaggerFilmsComponentTest.builder()
                .appComponent(getTestAppComponent())
                .build();

        filmsComponentTest.inject(this);
    }

    @Test
    public void check_get_film_response_success() throws IOException {
        JsonFilm jsonFilm = JsonObjectConverter.convertFromJson(getJson(), JsonFilm.class);

        TestObserver<JsonFilm> testObserver = new TestObserver<>();

        restApi.getFilm(MOCK_ID)
                .subscribe(testObserver);
        testObserver.assertValue(jsonFilm);
        testObserver.assertComplete();
    }

    @Override
    public String getResource() {
        return "json.film/JsonFilm.json";
    }

}
