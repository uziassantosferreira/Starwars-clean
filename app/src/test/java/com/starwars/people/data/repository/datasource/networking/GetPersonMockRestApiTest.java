package com.starwars.people.data.repository.datasource.networking;

import com.starwars.core.networking.MockRestApi;
import com.starwars.core.utils.json.JsonObjectConverter;
import com.starwars.people.data.repository.datasource.networking.json.JsonPerson;
import com.starwars.people.di.DaggerPeopleComponentTest;
import com.starwars.people.di.PeopleComponentTest;

import org.junit.Test;

import java.io.IOException;

import io.reactivex.observers.TestObserver;

public class GetPersonMockRestApiTest extends MockRestApi<PeopleRestApi> {

    private static final String MOCK_ID = "1";

    @Override
    public void setUp() throws IOException {
        super.setUp();

        PeopleComponentTest peopleComponentTest = DaggerPeopleComponentTest.builder()
                .appComponent(getTestAppComponent())
                .build();

        peopleComponentTest.inject(this);
    }

    @Test
    public void check_get_person_response_success() throws IOException {
        JsonPerson jsonPerson = JsonObjectConverter.convertFromJson(getJson(), JsonPerson.class);

        TestObserver<JsonPerson> testObserver = new TestObserver<>();

        restApi.getPerson(MOCK_ID)
                .subscribe(testObserver);
        testObserver.assertValue(jsonPerson);
        testObserver.assertComplete();
    }

    @Override
    public String getResource() {
        return "json.person/JsonPerson.json";
    }

}
