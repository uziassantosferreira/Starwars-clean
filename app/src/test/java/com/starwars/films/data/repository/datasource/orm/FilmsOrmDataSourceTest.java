package com.starwars.films.data.repository.datasource.orm;

import android.app.Application;
import android.support.v4.app.FragmentManager;

import com.starwars.core.database.di.DatabaseModuleTest;
import com.starwars.core.di.AppComponent;
import com.starwars.core.di.AppModule;
import com.starwars.core.di.DaggerAppComponent;
import com.starwars.core.utils.json.JsonObjectConverter;
import com.starwars.core.utils.json.JsonResourceLoader;
import com.starwars.films.data.repository.datasource.orm.entity.FilmEntity;
import com.starwars.films.data.repository.datasource.orm.entity.FilmEntityType;
import com.starwars.films.data.repository.datasource.orm.mapper.FilmEntityMapper;
import com.starwars.films.di.DaggerFilmsComponentTest;
import com.starwars.films.di.FilmsComponentTest;
import com.starwars.films.di.FilmsModule;
import com.starwars.films.domain.model.Film;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.observers.TestObserver;
import io.requery.Persistable;
import io.requery.reactivex.ReactiveEntityStore;

@RunWith(MockitoJUnitRunner.class)
public class FilmsOrmDataSourceTest {

    private static final String MOCK_URL = "http://swapi.co/api/films/1/";

    @Mock
    private Application application;
    @Mock
    private FragmentManager fragmentManager;

    private Film film;
    private FilmsOrmDataSource filmsOrmDataSource;

    @Inject
    ReactiveEntityStore<Persistable> data;


    @Before
    public void setUp() {
        injectDependencies();

        insertFilmInOrm();
        filmsOrmDataSource = new FilmsOrmDataSource(data);
    }

    private void insertFilmInOrm() {
        FilmEntity filmEntity = JsonObjectConverter.convertFromJson(getJson(), FilmEntity.class);
        data.insert(filmEntity, Integer.class).blockingGet();
        FilmEntity filmEntitySave = data.select(FilmEntity.class)
                .orderBy(FilmEntityType.ID.desc())
                .get().first();
        film = FilmEntityMapper.transformFrom(filmEntitySave);
    }

    private void injectDependencies(){
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .databaseModule(new DatabaseModuleTest())
                .build();

        FilmsComponentTest filmsComponentTest = DaggerFilmsComponentTest.builder()
                .filmsModule(new FilmsModule(fragmentManager))
                .appComponent(appComponent)
                .build();

        filmsComponentTest.inject(this);
    }

    private String getJson() {
        try {
            return JsonResourceLoader
                    .forResource("json.film/FilmEntity.json")
                    .getJson();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @After
    public void teardown() {
        if (data != null) {
            data.close();
        }
    }

    @Test
    public void successful_get_film(){
        TestObserver<Film> personTestObserver = TestObserver.create();
        filmsOrmDataSource.getFilm(MOCK_URL)
                .subscribe(personTestObserver);
        personTestObserver.assertResult(film);
        personTestObserver.onComplete();
    }

    @Test
    public void successful_save_film(){
        TestObserver<Film> personTestObserver = TestObserver.create();
        filmsOrmDataSource.saveFilm(film)
                .subscribe(personTestObserver);
        FilmEntity filmEntity = data.select(FilmEntity.class)
                .orderBy(FilmEntityType.ID.desc())
                .get().first();
        data.delete(FilmEntity.class)
                .where(FilmEntityType.ID.eq(filmEntity.id()))
                .get()
                .value();
        Film filmExpectedResult = FilmEntityMapper.transformFrom(filmEntity);
        personTestObserver.assertResult(filmExpectedResult);
        personTestObserver.onComplete();
    }

}