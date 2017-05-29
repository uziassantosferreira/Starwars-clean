package com.starwars.people.list.presentation.presenter;

import com.starwars.core.presentation.util.RxTest;
import com.starwars.people.domain.model.Person;
import com.starwars.people.domain.usecase.GetListPeople;
import com.starwars.people.domain.usecase.GetPerson;
import com.starwars.people.list.presentation.model.PresentationPerson;
import com.starwars.people.list.presentation.view.ListOfPeopleView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ListOfPeoplePresenterImplTest extends RxTest {

    private static final String MOCK_NAME = "Luke Skywalker";
    private static final String MOCK_URL = "http://swapi.co/api/people/1/";

    @Mock
    private GetPerson getPerson;

    @Mock
    private GetListPeople getListPeople;

    @Mock
    private ListOfPeopleView listOfPeopleView;

    private ListOfPeoplePresenter listOfPeoplePresenter;

    @Before
    public void setUp() throws Exception {
        when(getPerson.setUrl(MOCK_URL)).thenReturn(getPerson);
        when(getPerson.run()).thenReturn(Observable.just(mockPerson()));
        when(getListPeople.run()).thenReturn(Observable.fromArray(mockListPeople()));
        listOfPeoplePresenter = new ListOfPeoplePresenterImpl(getListPeople, getPerson);
        listOfPeoplePresenter.attachTo(listOfPeopleView);
    }

    @Test
    public void should_call_addpresentationperson()  {
        verify(listOfPeopleView, times(10)).addPresentationPerson(any(PresentationPerson.class));
    }

    @Test
    public void should_call_opencustomdialogfablink()  {
        listOfPeoplePresenter.fabLinkClicked();
        verify(listOfPeopleView).openCustomDialogFabLink();
    }

    @Test
    public void should_call_openqrcodeView()  {
        listOfPeoplePresenter.fabQrcodeClicked();
        verify(listOfPeopleView).openQrcodeView();
    }

    @Test
    public void should_call_gotodetails()  {
        listOfPeoplePresenter.searchByLink(MOCK_URL);
        listOfPeoplePresenter.resultMaterialBarcode(MOCK_URL);
        verify(getPerson, times(2)).run();
        verify(listOfPeopleView, times(2)).goToDetails(MOCK_URL);
    }


    private Person[] mockListPeople() {
        Person[] people = new Person[10];
        for (int i = 0; i < 10; i++){
            people[i] = mockPerson();
        }
        return people;
    }

    private Person mockPerson() {
        return Person.builder().setName(MOCK_NAME)
                .setUrl(MOCK_URL)
                .build();
    }

}
