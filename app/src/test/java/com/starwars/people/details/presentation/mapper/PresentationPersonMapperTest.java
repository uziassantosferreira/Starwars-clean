package com.starwars.people.details.presentation.mapper;

import com.starwars.core.utils.json.JsonObjectConverter;
import com.starwars.core.utils.json.JsonResourceLoader;
import com.starwars.people.details.presentation.model.PresentationPerson;
import com.starwars.people.domain.model.Person;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PresentationPersonMapperTest {

    private Person person;

    @Before
    public void setUp() throws IOException {
        person = JsonObjectConverter.convertFromJson(getJson(), Person.class);
    }

    private String getJson() throws IOException {
        return JsonResourceLoader
                .forResource("json.person/Person.json")
                .getJson();
    }

    @Test
    public void correctly_transform_person_to_presentation_person() {
        PresentationPerson presentationPerson = PresentationPersonMapper.transformFrom(person);
        assertThat(person.name(), is(presentationPerson.name()));
        assertThat(person.height(), is(presentationPerson.height()));
        assertThat(person.mass(), is(presentationPerson.mass()));
        assertThat(person.hairColor(), is(presentationPerson.hairColor()));
        assertThat(person.skinColor(), is(presentationPerson.skinColor()));
        assertThat(person.eyeColor(), is(presentationPerson.eyeColor()));
        assertThat(person.birthYear(), is(presentationPerson.birthYear()));
        assertThat(person.gender(), is(presentationPerson.gender()));
        assertThat(person.homeworld(), is(presentationPerson.homeworld()));
        assertThat(person.films(), is(presentationPerson.films()));
        assertThat(person.species(), is(presentationPerson.species()));
        assertThat(person.vehicles(), is(presentationPerson.vehicles()));
        assertThat(person.starships(), is(presentationPerson.starships()));
        assertThat(person.created(), is(presentationPerson.created()));
        assertThat(person.edited(), is(presentationPerson.edited()));
        assertThat(person.url(), is(presentationPerson.url()));
        assertNull(person.id());
    }

}