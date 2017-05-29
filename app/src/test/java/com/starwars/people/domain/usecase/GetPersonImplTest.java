package com.starwars.people.domain.usecase;

import com.starwars.people.domain.model.Person;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.security.InvalidParameterException;

import io.reactivex.observers.TestObserver;

@RunWith(MockitoJUnitRunner.class)
public class GetPersonImplTest {

    private static final String MOCK_URL = "http://swapi.co/api/people/1/";

    private GetPerson getPerson;

    @Test(expected = InvalidParameterException.class)
    public void must_throw_exception_on_null_url() {
        getPerson = new GetPersonImpl();
        getPerson.run();
    }

    @Test
    public void successful_get_people() {
        TestObserver<Person> testObserver = new TestObserver<>();

        getPerson = new GetPersonImpl();
        getPerson.setUrl(MOCK_URL);
        getPerson.run().subscribe(testObserver);

        testObserver.assertNoErrors();
        testObserver.assertComplete();
        testObserver.assertNoValues();
    }

}
