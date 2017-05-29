package com.starwars.people.data.repository;

import com.starwars.people.domain.model.Person;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.observers.TestObserver;

@RunWith(MockitoJUnitRunner.class)
public class PeopleRepositoryImplTest {

    private static final String MOCK_URL = "http://swapi.co/api/people/1/";
    private static final String MOCK_ID = "1";

    @Mock
    private Person person;

    private PeopleRepository peopleRepository;

    @Before
    public void setUp() {
        peopleRepository = new PeopleRepositoryImpl();
    }

    @Test
    public void correctly_returns_person_in_get_people() {
        TestObserver<Person> testObserver = TestObserver.create();
        peopleRepository.getPeople().subscribe(testObserver);
        testObserver.assertNoErrors();
        testObserver.assertComplete();
        testObserver.assertNoValues();
    }

    @Test
    public void correctly_returns_person_in_get_person() {
        TestObserver<Person> testObserver = TestObserver.create();
        peopleRepository.getPerson(MOCK_URL, MOCK_ID).subscribe(testObserver);
        testObserver.assertNoErrors();
        testObserver.assertComplete();
        testObserver.assertNoValues();
    }

    @Test
    public void correctly_returns_person_in_save() {
        TestObserver<Person> testObserver = TestObserver.create();
        peopleRepository.savePerson(person).subscribe(testObserver);
        testObserver.assertNoErrors();
        testObserver.assertComplete();
        testObserver.assertNoValues();
    }

}
