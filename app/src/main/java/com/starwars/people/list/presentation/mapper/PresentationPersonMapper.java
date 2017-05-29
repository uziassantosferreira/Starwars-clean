package com.starwars.people.list.presentation.mapper;

import com.starwars.people.domain.model.Person;
import com.starwars.people.list.presentation.model.PresentationPerson;

import io.reactivex.functions.Function;

public class PresentationPersonMapper {

    public static Function<Person, PresentationPerson> transformFrom(){
        return PresentationPersonMapper::transformFrom;
    }

    public static PresentationPerson transformFrom(Person person) {
        return PresentationPerson.create(person.name(), person.url());
    }

}
