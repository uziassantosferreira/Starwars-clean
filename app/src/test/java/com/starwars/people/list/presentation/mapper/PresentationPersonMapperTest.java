package com.starwars.people.list.presentation.mapper;

import com.starwars.people.domain.model.Person;
import com.starwars.people.list.presentation.model.PresentationPerson;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PresentationPersonMapperTest {

    private static final String MOCK_NAME = "Luke Skywalker";
    private static final String MOCK_URL = "http://swapi.co/api/people/1/";

    @Test
    public void correctly_transform_person_to_presentation_person() {
        Person person = mockPerson();
        PresentationPerson presentationPerson = PresentationPersonMapper.transformFrom(person);
        assertThat(presentationPerson.name(), is(person.name()));
        assertThat(presentationPerson.url(), is(person.url()));
    }

    private Person mockPerson() {
        return Person.builder().setName(MOCK_NAME)
                .setUrl(MOCK_URL)
                .build();
    }

}
