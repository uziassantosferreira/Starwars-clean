package com.starwars.people.domain.repository;

import com.starwars.people.domain.model.Person;

import io.reactivex.Observable;

public interface PeopleRepository {

    Observable<Person> getPeople();

    Observable<Person> getPerson(String url, String id);

    Observable<Person> savePerson(Person person);

}