package com.starwars.films.domain.usecase;

import com.starwars.films.domain.model.Film;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.security.InvalidParameterException;
import java.util.List;

import io.reactivex.observers.TestObserver;

@RunWith(MockitoJUnitRunner.class)
public class GetFilmImplTest {

    @Mock
    private List<String> urls;

    private GetFilm getFilm;

    @Test(expected = InvalidParameterException.class)
    public void must_throw_exception_on_null_url() {
        getFilm = new GetFilmImpl();
        getFilm.run();
    }

    @Test
    public void successful_get_people() {

        TestObserver<Film> testObserver = new TestObserver<>();

        getFilm = new GetFilmImpl();
        getFilm.setUrl(urls);
        getFilm.run().subscribe(testObserver);

        testObserver.assertNoValues();
        testObserver.assertNoErrors();
        testObserver.assertComplete();

    }

}
