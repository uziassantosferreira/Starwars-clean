package com.starwars.people.domain.usecase;

import com.starwars.core.domain.usecase.UseCase;
import com.starwars.people.domain.model.Person;

import io.reactivex.Observable;

public interface GetPerson extends UseCase<Observable<Person>> {
    GetPerson setUrl(String url);
}
