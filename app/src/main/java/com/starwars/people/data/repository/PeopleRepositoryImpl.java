package com.starwars.people.data.repository;

import com.starwars.people.data.repository.datasource.PeopleDataSource;
import com.starwars.people.domain.model.Person;

import io.reactivex.Observable;

public class PeopleRepositoryImpl implements PeopleRepository {

    private final PeopleDataSource requeryDataSource;
    private PeopleDataSource restDataSource;

    public PeopleRepositoryImpl(PeopleDataSource requeryDataSource, PeopleDataSource restDataSource) {
        this.requeryDataSource = requeryDataSource;
        this.restDataSource = restDataSource;
    }

    @Override
    public Observable<Person> getPeople() {
        return requeryDataSource.getPeople();
    }

    @Override
    public Observable<Person> getPerson(String url, String id) {
        return requeryDataSource.getPerson(url)
                .switchIfEmpty(restDataSource.getPerson(id).flatMap(this::savePerson));
    }

    @Override
    public Observable<Person> savePerson(Person person) {
        return requeryDataSource.savePerson(person);
    }


}
