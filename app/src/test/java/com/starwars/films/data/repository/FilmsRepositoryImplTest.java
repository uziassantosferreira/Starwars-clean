package com.starwars.films.data.repository;

import com.starwars.films.domain.model.Film;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.observers.TestObserver;

@RunWith(MockitoJUnitRunner.class)
public class FilmsRepositoryImplTest {

    private static final String MOCK_URL = "http://swapi.co/api/people/1/";
    private static final String MOCK_ID = "1";

    @Mock
    private Film film;

    private FilmsRepository filmsRepository;

    @Before
    public void setUp() {
        filmsRepository = new FilmsRepositoryImpl();
    }

    @Test
    public void correctly_return_film_in_get_film() {
        TestObserver<Film> testObserver = TestObserver.create();
        filmsRepository.getFilm(MOCK_URL, MOCK_ID)
                .subscribe(testObserver);
        testObserver.assertNoValues();
        testObserver.assertNoErrors();
        testObserver.assertComplete();
    }

    @Test
    public void correctly_save_film() {
        TestObserver<Film> testObserver = TestObserver.create();
        filmsRepository.saveFilm(film)
                .subscribe(testObserver);
        testObserver.assertNoValues();
        testObserver.assertNoErrors();
        testObserver.assertComplete();
    }

}
