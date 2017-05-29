package com.starwars.people.data.repository.datasource;

import com.starwars.people.domain.model.Person;

import io.reactivex.Observable;

public interface PeopleDataSource {

    Observable<Person> getPeople();

    Observable<Person> getPerson(String parameter);

    Observable<Person> savePerson(Person person);

}



