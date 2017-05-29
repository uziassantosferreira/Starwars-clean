package com.starwars.people.details.presentation.presenter;

import com.starwars.core.presentation.util.RxTest;
import com.starwars.core.utils.json.JsonObjectConverter;
import com.starwars.core.utils.json.JsonResourceLoader;
import com.starwars.films.domain.model.Film;
import com.starwars.films.domain.usecase.GetFilm;
import com.starwars.films.presentation.mapper.PresentationFilmMapper;
import com.starwars.films.presentation.model.PresentationFilm;
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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonDetailsPresenterImplTest extends RxTest {

    private static final String MOCK_URL = "http://swapi.co/api/people/1/";
    private static final String MOCK_TITLE = "title";
    private static final String MOCK_OPENING_CRAWL = "opening crawl";

    @Mock
    private GetPerson getPerson;

    @Mock
    private GetFilm getFilm;

    @Mock
    private PersonDetailsView personDetailsView;

    private PersonDetailsPresenter personDetailsPresenter;

    @Before
    public void setUp() throws Exception {
        when(getPerson.setUrl(MOCK_URL)).thenReturn(getPerson);
        when(getPerson.run()).thenReturn(Observable.just(mockPerson()));
        when(getFilm.setUrl(mockPerson().films())).thenReturn(getFilm);
        when(getFilm.run()).thenReturn(Observable.just(mockFilm()));
        personDetailsPresenter = new PersonDetailsPresenterImpl(getPerson, getFilm);
        personDetailsPresenter.attachTo(personDetailsView);
    }

    private Film mockFilm() {
        return Film.builder()
                .setTitle(MOCK_TITLE)
                .setOpeningCrawl(MOCK_OPENING_CRAWL)
                .build();
    }

    @Test
    public void should_call_showdetails()  {
        personDetailsPresenter.setUrl(MOCK_URL);
        PresentationPerson presentationPerson = PresentationPersonMapper.transformFrom(mockPerson());
        verify(personDetailsView, times(1)).showDetails(presentationPerson);
    }

    @Test
    public void should_call_add_film()  {
        personDetailsPresenter.setUrl(MOCK_URL);
        PresentationFilm presentationFilm = PresentationFilmMapper.transformFrom(mockFilm());
        verify(personDetailsView, times(1)).addFilm(presentationFilm);
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
