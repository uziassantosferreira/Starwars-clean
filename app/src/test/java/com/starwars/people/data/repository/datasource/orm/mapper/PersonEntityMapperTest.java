package com.starwars.people.data.repository.datasource.orm.mapper;

import com.google.gson.reflect.TypeToken;
import com.starwars.core.utils.json.JsonObjectConverter;
import com.starwars.core.utils.json.JsonResourceLoader;
import com.starwars.people.data.repository.datasource.orm.entity.PersonEntity;
import com.starwars.people.domain.model.Person;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PersonEntityMapperTest {

    private List<PersonEntity> personEntities;

    @Before
    public void setUp() throws IOException {
        personEntities = JsonObjectConverter.convertArrayFromJson(getJson(),
                new TypeToken<List<PersonEntity>>() {}.getType());

    }

    private String getJson() throws IOException {
        return JsonResourceLoader
                .forResource("json.person/PersonEntityArray.json")
                .getJson();
    }

    @Test
    public void correctly_transform_personentity_to_domain_person() {
        PersonEntity personEntity = personEntities.get(0);
        Person person = PersonEntityMapper.transformFrom(personEntity);
        assertThatFieldsInPerson(person, personEntity);
    }

    @Test
    public void correctly_transform_personentities_to_domain_people() {
        List<Person> people = PersonEntityMapper.transformFrom(personEntities);
        int count = personEntities.size();
        for (int position = 0; position < count; position++){
            assertThatFieldsInPerson(people.get(position), personEntities.get(position));
        }
        assertThat(count, is(people.size()));
    }

    private void assertThatFieldsInPerson(Person person, PersonEntity personEntity) {
        assertThat(person.name(), is(personEntity.name()));
        assertThat(person.height(), is(personEntity.height()));
        assertThat(person.mass(), is(personEntity.mass()));
        assertThat(person.hairColor(), is(personEntity.hairColor()));
        assertThat(person.skinColor(), is(personEntity.skinColor()));
        assertThat(person.eyeColor(), is(personEntity.eyeColor()));
        assertThat(person.birthYear(), is(personEntity.birthYear()));
        assertThat(person.gender(), is(personEntity.gender()));
        assertThat(person.homeworld(), is(personEntity.homeworld()));
        assertThat(person.films(), is(personEntity.films()));
        assertThat(person.species(), is(personEntity.species()));
        assertThat(person.vehicles(), is(personEntity.vehicles()));
        assertThat(person.starships(), is(personEntity.starships()));
        assertThat(person.created(), is(personEntity.created()));
        assertThat(person.edited(), is(personEntity.edited()));
        assertThat(person.url(), is(personEntity.url()));
        assertThat(person.id(), is(personEntity.id()));
    }

}
