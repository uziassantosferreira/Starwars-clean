package com.starwars.people.domain.usecase;

import com.starwars.people.data.repository.PeopleRepository;
import com.starwars.people.domain.model.Person;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetListPeopleImplTest {

    @Mock
    private PeopleRepository peopleRepository;

    @Test
    public void successful_get_people() {
        when(peopleRepository.getPeople()).thenReturn(Observable.fromArray(mockListPeople()));

        TestObserver<Person> testObserver = new TestObserver<>();

        GetListPeople getListPeople = new GetListPeopleImpl(peopleRepository);
        getListPeople.run().subscribe(testObserver);

        testObserver.assertResult(mockListPeople());
        testObserver.assertNoErrors();
        testObserver.assertComplete();

        verify(peopleRepository, times(1)).getPeople();
    }

    private Person[] mockListPeople() {
        Person[] people = new Person[10];
        for (int i = 0; i < 10; i++){
            people[i] = Person.builder().build();
        }
        return people;
    }

}
