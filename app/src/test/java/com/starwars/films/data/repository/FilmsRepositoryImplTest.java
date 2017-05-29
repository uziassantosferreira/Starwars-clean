package com.starwars.films.data.repository;

import com.starwars.films.data.repository.datasource.FilmsDataSource;
import com.starwars.films.domain.model.Film;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FilmsRepositoryImplTest {

    private static final String MOCK_URL = "http://swapi.co/api/films/1/";
    private static final String MOCK_ID = "1";

    @Mock
    private Film film;

    @Mock
    private FilmsDataSource restFilmsDataSource;
    @Mock
    private FilmsDataSource ormFilmsDataSource;

    private FilmsRepository filmsRepository;

    @Before
    public void setUp() {
        when(ormFilmsDataSource.getFilm(MOCK_URL)).thenReturn(Observable.just(mockFilm()));
        when(restFilmsDataSource.getFilm(MOCK_ID)).thenReturn(Observable.just(mockFilm()));
        when(ormFilmsDataSource.saveFilm(film)).thenReturn(Observable.just(film));
        filmsRepository = new FilmsRepositoryImpl(ormFilmsDataSource, restFilmsDataSource);
    }

    private Film mockFilm() {
        return Film.builder().build();
    }

    @Test
    public void must_call_ormdatasource_get_film() {
        TestObserver<Film> testObserver = TestObserver.create();
        filmsRepository.getFilm(MOCK_URL, MOCK_ID)
                .subscribe(testObserver);
        testObserver.assertResult(Film.builder().build());
        testObserver.assertNoErrors();
        testObserver.assertComplete();
        verify(ormFilmsDataSource, times(1)).getFilm(MOCK_URL);
    }

    @Test
    public void must_call_rest_datasource_get_film() {
        when(ormFilmsDataSource.getFilm(MOCK_URL)).thenReturn(Observable.empty());
        filmsRepository.getFilm(MOCK_URL, MOCK_ID);
        verify(restFilmsDataSource, times(1)).getFilm(MOCK_ID);
    }

    @Test
    public void correctly_save_film() {
        TestObserver<Film> testObserver = TestObserver.create();
        filmsRepository.saveFilm(film)
                .subscribe(testObserver);
        testObserver.assertResult(film);
        testObserver.assertNoErrors();
        testObserver.assertComplete();
        verify(ormFilmsDataSource, times(1)).saveFilm(film);
    }

}
