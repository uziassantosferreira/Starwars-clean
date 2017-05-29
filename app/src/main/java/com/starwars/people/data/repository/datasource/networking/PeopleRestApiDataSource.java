package com.starwars.people.data.repository.datasource.networking;

import com.starwars.people.data.repository.datasource.PeopleDataSource;
import com.starwars.people.data.repository.datasource.networking.mapper.JsonPersonMapper;
import com.starwars.people.domain.model.Person;

import io.reactivex.Observable;

public class PeopleRestApiDataSource implements PeopleDataSource {

    private final PeopleRestApi peopleRestApi;

    public PeopleRestApiDataSource(PeopleRestApi peopleRestApi){
        this.peopleRestApi = peopleRestApi;
    }

    @Override
    public Observable<Person> getPeople() {
        throw new UnsupportedOperationException("getPerson not supported for "
                + this.getClass().getName());
    }

    public Observable<Person> getPerson(String url) {
        return peopleRestApi.getPerson(url)
                .map(JsonPersonMapper::transformFrom);
    }

    @Override
    public Observable<Person> savePerson(Person person) {
        throw new UnsupportedOperationException("getPerson not supported for "
                + this.getClass().getName());
    }

}
