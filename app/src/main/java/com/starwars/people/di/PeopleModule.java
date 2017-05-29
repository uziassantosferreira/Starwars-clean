package com.starwars.people.di;

import com.starwars.films.domain.usecase.GetFilm;
import com.starwars.people.domain.repository.PeopleRepository;
import com.starwars.people.data.repository.PeopleRepositoryImpl;
import com.starwars.people.data.repository.datasource.PeopleDataSource;
import com.starwars.people.data.repository.datasource.networking.PeopleRestApi;
import com.starwars.people.data.repository.datasource.networking.PeopleRestApiDataSource;
import com.starwars.people.data.repository.datasource.orm.PeopleOrmDataSource;
import com.starwars.people.details.presentation.presenter.PersonDetailsPresenter;
import com.starwars.people.details.presentation.presenter.PersonDetailsPresenterImpl;
import com.starwars.people.domain.usecase.GetListPeople;
import com.starwars.people.domain.usecase.GetListPeopleImpl;
import com.starwars.people.domain.usecase.GetPerson;
import com.starwars.people.domain.usecase.GetPersonImpl;
import com.starwars.people.list.presentation.adapter.ListOfPeopleAdapter;
import com.starwars.people.list.presentation.presenter.ListOfPeoplePresenter;
import com.starwars.people.list.presentation.presenter.ListOfPeoplePresenterImpl;
import com.starwars.people.navigation.Navigation;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.requery.Persistable;
import io.requery.reactivex.ReactiveEntityStore;
import retrofit2.Retrofit;

@Module
public class PeopleModule {

    @Provides
    @Named("PeopleOrmDataSource")
    PeopleDataSource providesRequeryDataSource(ReactiveEntityStore<Persistable> reactiveEntityStore){
        return new PeopleOrmDataSource(reactiveEntityStore);
    }

    @Provides
    @Named("PeopleRestApiDataSource")
    PeopleDataSource providesRestApiDataSource(PeopleRestApi peopleRestApi){
        return new PeopleRestApiDataSource(peopleRestApi);
    }

    @Provides
    PeopleRestApi providesPeopleRestApi(Retrofit retrofit){
        return retrofit.create(PeopleRestApi.class);
    }

    @Provides
    ListOfPeoplePresenter providesListOfPeoplePresenter(GetListPeople getListPeople, GetPerson getPerson) {
        return new ListOfPeoplePresenterImpl(getListPeople, getPerson);
    }

    @Provides
    GetListPeople providesGetListPeople(PeopleRepository peopleRepository){
        return new GetListPeopleImpl(peopleRepository);
    }

    @Provides
    GetPerson providesGetPerson(PeopleRepository peopleRepository){
        return new GetPersonImpl(peopleRepository);
    }

    @Provides
    PeopleRepository providesPeopleRepository(@Named("PeopleOrmDataSource") PeopleDataSource requeryDataSource,
                                              @Named("PeopleRestApiDataSource") PeopleDataSource restApiDataSource){
        return new PeopleRepositoryImpl(requeryDataSource, restApiDataSource);
    }

    @Provides
    ListOfPeopleAdapter providesPeopleAdapter(){
        return new ListOfPeopleAdapter();
    }

    @Provides
    Navigation providesNavigation(){
        return new Navigation();
    }

    @Provides
    PersonDetailsPresenter providesPersonDetailsPresenter(GetPerson getPerson, GetFilm getFilm) {
        return new PersonDetailsPresenterImpl(getPerson, getFilm);
    }

}
