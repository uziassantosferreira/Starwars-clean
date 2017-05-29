package com.starwars.people.domain.usecase;

import com.starwars.people.domain.model.Person;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.observers.TestObserver;


@RunWith(MockitoJUnitRunner.class)
public class GetListPeopleImplTest {

    @Test
    public void successful_get_people() {
        TestObserver<Person> testObserver = new TestObserver<>();

        GetListPeople getListPeople = new GetListPeopleImpl();
        getListPeople.run().subscribe(testObserver);

        testObserver.assertNoErrors();
        testObserver.assertComplete();
        testObserver.assertNoValues();
    }

}
