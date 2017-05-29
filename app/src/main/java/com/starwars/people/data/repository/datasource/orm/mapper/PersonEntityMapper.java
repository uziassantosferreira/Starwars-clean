package com.starwars.people.data.repository.datasource.orm.mapper;

import com.starwars.people.data.repository.datasource.orm.entity.PersonEntity;
import com.starwars.people.domain.model.Person;

import java.util.ArrayList;
import java.util.List;

import static com.starwars.people.domain.model.Person.builder;

public class PersonEntityMapper {

    public static List<Person> transformFrom(List<PersonEntity> personEntities) {
        List<Person> people = new ArrayList<>(personEntities.size());
        for (PersonEntity personEntity: personEntities)
            people.add(transformFrom(personEntity));
        return people;
    }

    public static Person transformFrom(PersonEntity personEntity) {
        return builder()
                .setId(personEntity.id())
                .setName(personEntity.name())
                .setHeight(personEntity.height())
                .setMass(personEntity.mass())
                .setHairColor(personEntity.hairColor())
                .setSkinColor(personEntity.skinColor())
                .setBirthYear(personEntity.birthYear())
                .setEyeColor(personEntity.eyeColor())
                .setGender(personEntity.gender())
                .setHomeworld(personEntity.homeworld())
                .setFilms(personEntity.films())
                .setSpecies(personEntity.species())
                .setVehicles(personEntity.vehicles())
                .setStarships(personEntity.starships())
                .setCreated(personEntity.created())
                .setEdited(personEntity.edited())
                .setUrl(personEntity.url())
                .build();
    }

    public static PersonEntity transformFrom(Person person) {
        return PersonEntity.builder()
                .setId(person.id() == null ? 0 : person.id())
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
