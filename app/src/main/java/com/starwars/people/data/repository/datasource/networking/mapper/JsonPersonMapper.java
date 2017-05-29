package com.starwars.people.data.repository.datasource.networking.mapper;

import android.support.annotation.NonNull;

import com.starwars.people.data.repository.datasource.networking.json.JsonPerson;
import com.starwars.people.domain.model.Person;

public class JsonPersonMapper {

    public static Person transformFrom(@NonNull JsonPerson jsonPerson) {
        return Person.builder()
                .setName(jsonPerson.name())
                .setHeight(jsonPerson.height())
                .setMass(jsonPerson.mass())
                .setHairColor(jsonPerson.hairColor())
                .setSkinColor(jsonPerson.skinColor())
                .setBirthYear(jsonPerson.birthYear())
                .setEyeColor(jsonPerson.eyeColor())
                .setGender(jsonPerson.gender())
                .setHomeworld(jsonPerson.homeworld())
                .setFilms(jsonPerson.films())
                .setSpecies(jsonPerson.species())
                .setVehicles(jsonPerson.vehicles())
                .setStarships(jsonPerson.starships())
                .setCreated(jsonPerson.created())
                .setEdited(jsonPerson.edited())
                .setUrl(jsonPerson.url())
                .build();
    }
}
