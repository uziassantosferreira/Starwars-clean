package com.starwars.people.data.repository.datasource.networking.mapper;

import com.starwars.core.utils.json.JsonObjectConverter;
import com.starwars.core.utils.json.JsonResourceLoader;
import com.starwars.people.data.repository.datasource.networking.json.JsonPerson;
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
public class JsonPersonMapperTest {

    private JsonPerson jsonPerson;

    @Before
    public void setUp() throws IOException {
        jsonPerson = JsonObjectConverter.convertFromJson(getJson(), JsonPerson.class);
    }

    private String getJson() throws IOException {
        return JsonResourceLoader
                .forResource("json.person/JsonPerson.json")
                .getJson();
    }

    @Test
    public void correctly_transform_jsonperson_to_domain_person() {
        Person person = JsonPersonMapper.transformFrom(jsonPerson);
        assertThat(person.name(), is(jsonPerson.name()));
        assertThat(person.height(), is(jsonPerson.height()));
        assertThat(person.mass(), is(jsonPerson.mass()));
        assertThat(person.hairColor(), is(jsonPerson.hairColor()));
        assertThat(person.skinColor(), is(jsonPerson.skinColor()));
        assertThat(person.eyeColor(), is(jsonPerson.eyeColor()));
        assertThat(person.birthYear(), is(jsonPerson.birthYear()));
        assertThat(person.gender(), is(jsonPerson.gender()));
        assertThat(person.homeworld(), is(jsonPerson.homeworld()));
        assertThat(person.films(), is(jsonPerson.films()));
        assertThat(person.species(), is(jsonPerson.species()));
        assertThat(person.vehicles(), is(jsonPerson.vehicles()));
        assertThat(person.starships(), is(jsonPerson.starships()));
        assertThat(person.created(), is(jsonPerson.created()));
        assertThat(person.edited(), is(jsonPerson.edited()));
        assertThat(person.url(), is(jsonPerson.url()));
        assertNull(person.id());
    }

}
