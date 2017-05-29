package com.starwars.people.data.repository;

import com.starwars.people.domain.model.Person;

import io.reactivex.Observable;

public class PeopleRepositoryImpl implements PeopleRepository {

    @Override
    public Observable<Person> getPeople() {
        return Observable.empty();
    }

    @Override
    public Observable<Person> getPerson(String url, String id) {
        return Observable.empty();
    }

    @Override
    public Observable<Person> savePerson(Person person) {
        return Observable.empty();
    }

}
