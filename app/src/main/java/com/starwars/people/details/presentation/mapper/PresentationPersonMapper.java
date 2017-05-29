package com.starwars.people.details.presentation.mapper;

import com.starwars.people.details.presentation.model.PresentationPerson;
import com.starwars.people.domain.model.Person;

import io.reactivex.functions.Function;

import static com.starwars.people.details.presentation.model.PresentationPerson.builder;

public class PresentationPersonMapper {

    public static Function<Person, PresentationPerson> transformFrom(){
        return PresentationPersonMapper::transformFrom;
    }

    public static PresentationPerson transformFrom(Person person) {
        return builder()
                .setName(person.name())
                .setHeight(person.height())
                .setMass(person.mass())
                .setHairColor(person.hairColor())
                .setSkinColor(person.skinColor())
                .setBirthYear(person.birthYear())
                .setEyeColor(person.eyeColor())
                .setGender(person.gender())
                .setHomeworld(person.homeworld())
                .setFilms(person.films())
                .setSpecies(person.species())
                .setVehicles(person.vehicles())
                .setStarships(person.starships())
                .setCreated(person.created())
                .setEdited(person.edited())
                .setUrl(person.url())
                .build();
    }
    
}
