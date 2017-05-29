package com.starwars.people.domain.usecase;

import com.starwars.people.domain.model.Person;

import java.security.InvalidParameterException;

import io.reactivex.Observable;

public class GetPersonImpl implements GetPerson {

    private String url;

    @Override
    public Observable<Person> run() {
        if (url == null || url.isEmpty()){
            throw new InvalidParameterException("need to set url");
        }
        return Observable.empty();
    }

    @Override
    public GetPerson setUrl(String url) {
        this.url = url;
        return this;
    }
}
