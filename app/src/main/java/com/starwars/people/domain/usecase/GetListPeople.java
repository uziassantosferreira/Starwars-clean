package com.starwars.people.domain.usecase;

import com.starwars.core.domain.usecase.UseCase;
import com.starwars.people.domain.model.Person;

import io.reactivex.Observable;

public interface GetListPeople extends UseCase<Observable<Person>> {
}
