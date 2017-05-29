package com.starwars.people.domain.usecase;

import com.starwars.people.domain.repository.PeopleRepository;
import com.starwars.people.domain.model.Person;

import java.security.InvalidParameterException;

import io.reactivex.Observable;

public class GetPersonImpl implements GetPerson {

    private String url;
    private PeopleRepository peopleRepository;

    public GetPersonImpl(PeopleRepository peopleRepository){
        this.peopleRepository = peopleRepository;
    }

    @Override
    public Observable<Person> run() {
        if (url == null || url.isEmpty()){
            throw new InvalidParameterException("need to set url");
        }

        String id = url.replaceAll("\\D+", "");

        return peopleRepository.getPerson(url, id);
    }

    @Override
    public GetPerson setUrl(String url) {
        this.url = url;
        return this;
    }
}
