package com.starwars.people.di;

import com.starwars.core.di.AppComponent;
import com.starwars.core.networking.MockRestApi;
import com.starwars.people.data.repository.datasource.networking.PeopleRestApi;
import com.starwars.people.data.repository.datasource.orm.PeopleOrmDataSourceTest;

import dagger.Component;

@PeopleScopeTest
@Component(dependencies = AppComponent.class, modules = {PeopleModule.class})
public interface PeopleComponentTest {
    void inject(PeopleOrmDataSourceTest peopleRequeryDataSourceTest);
    void inject(MockRestApi<PeopleRestApi> mockRestApi);
}
