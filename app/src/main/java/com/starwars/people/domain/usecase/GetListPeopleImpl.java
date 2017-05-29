package com.starwars.people.domain.usecase;

import com.starwars.people.domain.model.Person;

import io.reactivex.Observable;

public class GetListPeopleImpl implements GetListPeople {

    @Override
    public Observable<Person> run() {
        return Observable.empty();
    }

}
