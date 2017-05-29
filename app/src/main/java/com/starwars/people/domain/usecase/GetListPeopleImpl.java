package com.starwars.people.domain.usecase;

import com.starwars.people.domain.repository.PeopleRepository;
import com.starwars.people.domain.model.Person;

import io.reactivex.Observable;

public class GetListPeopleImpl implements GetListPeople {

    private final PeopleRepository peopleRepository;

    public GetListPeopleImpl(PeopleRepository peopleRepository){
        this.peopleRepository = peopleRepository;
    }

    @Override
    public Observable<Person> run() {
        return peopleRepository.getPeople();
    }

}
