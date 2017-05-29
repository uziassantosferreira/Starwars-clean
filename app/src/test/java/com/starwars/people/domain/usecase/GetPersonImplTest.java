package com.starwars.people.domain.usecase;

import com.starwars.people.domain.repository.PeopleRepository;
import com.starwars.people.domain.model.Person;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.security.InvalidParameterException;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetPersonImplTest {

    private static final String MOCK_URL = "http://swapi.co/api/people/1/";
    private static final String MOCK_ID = "1";

    @Mock
    private PeopleRepository peopleRepository;

    private GetPerson getPerson;

    @Test(expected = InvalidParameterException.class)
    public void must_throw_exception_on_null_url() {
        getPerson = new GetPersonImpl(peopleRepository);
        getPerson.run();
    }

    @Test
    public void successful_get_people() {
        when(peopleRepository.getPerson(MOCK_URL, MOCK_ID)).thenReturn(Observable.just(mockPerson()));

        TestObserver<Person> testObserver = new TestObserver<>();

        getPerson = new GetPersonImpl(peopleRepository);
        getPerson.setUrl(MOCK_URL);
        getPerson.run().subscribe(testObserver);

        testObserver.assertResult(mockPerson());
        testObserver.assertNoErrors();
        testObserver.assertComplete();

        verify(peopleRepository, times(1)).getPerson(MOCK_URL, MOCK_ID);

    }

    private Person mockPerson() {
        return Person.builder().build();
    }

}
