package com.starwars.films.di;

import com.starwars.core.di.AppComponent;
import com.starwars.core.networking.MockRestApi;
import com.starwars.films.data.repository.datasource.networking.FilmsRestApi;
import com.starwars.films.data.repository.datasource.orm.FilmsOrmDataSourceTest;

import dagger.Component;

@FilmsScopeTest
@Component(dependencies = AppComponent.class, modules = {FilmsModule.class})
public interface FilmsComponentTest {
    void inject(FilmsOrmDataSourceTest filmsOrmDataSourceTest);
    void inject(MockRestApi<FilmsRestApi> filmsRestApiMockRestApi);
}
