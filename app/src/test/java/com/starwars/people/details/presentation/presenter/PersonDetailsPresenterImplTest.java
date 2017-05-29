package com.starwars.people.details.presentation.presenter;


import com.starwars.core.presentation.util.RxTest;
import com.starwars.core.utils.json.JsonObjectConverter;
import com.starwars.core.utils.json.JsonResourceLoader;
import com.starwars.people.details.presentation.mapper.PresentationPersonMapper;
import com.starwars.people.details.presentation.model.PresentationPerson;
import com.starwars.people.details.presentation.view.PersonDetailsView;
import com.starwars.people.domain.model.Person;
import com.starwars.people.domain.usecase.GetPerson;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import io.reactivex.Observable;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonDetailsPresenterImplTest extends RxTest {

    private static final String MOCK_URL = "http://swapi.co/api/people/1/";

    @Mock
    private GetPerson getPerson;

    @Mock
    private PersonDetailsView personDetailsView;

    private PersonDetailsPresenter personDetailsPresenter;

    @Before
    public void setUp() throws Exception {
        when(getPerson.setUrl(MOCK_URL)).thenReturn(getPerson);
        when(getPerson.run()).thenReturn(Observable.just(mockPerson()));
        personDetailsPresenter = new PersonDetailsPresenterImpl(getPerson);
        personDetailsPresenter.attachTo(personDetailsView);
    }

    @Test
    public void should_call_showdetails()  {
        personDetailsPresenter.setUrl(MOCK_URL);
        PresentationPerson presentationPerson = PresentationPersonMapper.transformFrom(mockPerson());
        verify(personDetailsView, times(1)).showDetails(presentationPerson);
    }

    private Person mockPerson() {
        try {
            return JsonObjectConverter.convertFromJson(getJson(), Person.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getJson() throws IOException {
        return JsonResourceLoader
                .forResource("json.person/Person.json")
                .getJson();
    }
}
