package com.starwars.people.data.repository;

import com.starwars.people.data.repository.datasource.PeopleDataSource;
import com.starwars.people.domain.model.Person;
import com.starwars.people.domain.repository.PeopleRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PeopleRepositoryImplTest {

    private static final String MOCK_URL = "http://swapi.co/api/people/1/";
    private static final String MOCK_ID = "1";

    @Mock
    private PeopleDataSource restPeopleDataSource;

    @Mock
    private PeopleDataSource ormPeopleDataSource;

    private PeopleRepository peopleRepository;

    @Before
    public void setUp() {
        when(ormPeopleDataSource.getPeople()).thenReturn(Observable.fromArray(mockListPeople()));
        when(ormPeopleDataSource.getPerson(MOCK_URL)).thenReturn(Observable.just(mockPerson()));
        when(restPeopleDataSource.getPerson(MOCK_ID)).thenReturn(Observable.just(mockPerson()));

        peopleRepository = new PeopleRepositoryImpl(ormPeopleDataSource, restPeopleDataSource);
    }

    @Test
    public void must_call_ormdatasource_get_people() {
        peopleRepository.getPeople();
        verify(ormPeopleDataSource).getPeople();
    }

    @Test
    public void correctly_returns_person_in_get_people() {
        TestObserver<Person> testObserver = TestObserver.create();
        peopleRepository.getPeople().subscribe(testObserver);
        testObserver.assertResult(mockListPeople());
        testObserver.assertComplete();
    }

    @Test
    public void must_call_ormdatasource_get_person() {
        peopleRepository.getPerson(MOCK_URL, MOCK_ID);
        verify(ormPeopleDataSource).getPerson(MOCK_URL);
    }

    @Test
    public void correctly_returns_person_in_get_person() {
        TestObserver<Person> testObserver = TestObserver.create();
        peopleRepository.getPerson(MOCK_URL, MOCK_ID).subscribe(testObserver);
        testObserver.assertValue(mockPerson());
        testObserver.assertComplete();
    }

    @Test
    public void must_call_restdatasource_get_person() {
        when(ormPeopleDataSource.getPerson(MOCK_URL)).thenReturn(Observable.empty());
        peopleRepository.getPerson(MOCK_URL, MOCK_ID);
        verify(restPeopleDataSource).getPerson(MOCK_ID);
    }

    @Test
    public void must_call_ormdatasource_save_person() {
        peopleRepository.savePerson(mockPerson());
        verify(ormPeopleDataSource).savePerson(mockPerson());
    }

    private Person[] mockListPeople() {
        Person[] people = new Person[10];
        for (int i = 0; i < 10; i++){
            people[i] = mockPerson();
        }
        return people;
    }

    private Person mockPerson() {
        return Person.builder()
                .build();
    }

}
