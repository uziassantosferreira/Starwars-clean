package com.starwars.people.data.repository.datasource.orm;

import android.app.Application;

import com.google.gson.reflect.TypeToken;
import com.starwars.core.database.di.DatabaseModuleTest;
import com.starwars.core.di.AppComponent;
import com.starwars.core.di.AppModule;
import com.starwars.core.di.DaggerAppComponent;
import com.starwars.core.utils.json.JsonObjectConverter;
import com.starwars.core.utils.json.JsonResourceLoader;
import com.starwars.people.data.repository.datasource.orm.entity.PersonEntity;
import com.starwars.people.data.repository.datasource.orm.entity.PersonEntityType;
import com.starwars.people.data.repository.datasource.orm.mapper.PersonEntityMapper;
import com.starwars.people.di.DaggerPeopleComponentTest;
import com.starwars.people.di.PeopleComponentTest;
import com.starwars.people.domain.model.Person;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.TestObserver;
import io.requery.Persistable;
import io.requery.reactivex.ReactiveEntityStore;

@RunWith(MockitoJUnitRunner.class)
public class PeopleOrmDataSourceTest {

    @Mock
    private Application application;

    private Person[] people;
    private Person person;
    private PeopleOrmDataSource peopleOrmDataSource;

    @Inject
    ReactiveEntityStore<Persistable> data;


    @Before
    public void setUp() {
        injectDependencies();

        populateListPeople();
        person = people[0];
        peopleOrmDataSource = new PeopleOrmDataSource(data);
    }

    private void populateListPeople() {
        List<PersonEntity> personEntities = JsonObjectConverter.
                convertArrayFromJson(getJsonArrayMockList(),
                        new TypeToken<List<PersonEntity>>() {}.getType());
        data.insert(personEntities, Integer.class).blockingGet();
        personEntities = data.select(PersonEntity.class).get().toList();
        people = PersonEntityMapper.transformFrom(personEntities)
                .toArray(new Person[personEntities.size()]);
    }

    private void injectDependencies(){
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .databaseModule(new DatabaseModuleTest())
                .build();

        PeopleComponentTest peopleComponentTest = DaggerPeopleComponentTest.builder()
                .appComponent(appComponent)
                .build();

        peopleComponentTest.inject(this);
    }

    private String getJsonArrayMockList() {
        try {
            return JsonResourceLoader
                    .forResource("json.person/PersonEntityArray.json")
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
    public void successful_get_people(){
        TestObserver<Person> personTestObserver = TestObserver.create();
        peopleOrmDataSource.getPeople()
                .subscribe(personTestObserver);
        personTestObserver.assertResult(people);
        personTestObserver.onComplete();
    }

    @Test
    public void successful_get_person(){
        TestObserver<Person> personTestObserver = TestObserver.create();
        peopleOrmDataSource.getPerson(person.url())
                .subscribe(personTestObserver);
        personTestObserver.assertResult(person);
        personTestObserver.onComplete();
    }

    @Test
    public void successful_save_person(){
        TestObserver<Person> personTestObserver = TestObserver.create();
        peopleOrmDataSource.savePerson(person)
                .subscribe(personTestObserver);
        PersonEntity personEntity = data.select(PersonEntity.class)
                .orderBy(PersonEntityType.ID.desc())
                .get().first();
        data.delete(PersonEntity.class)
                .where(PersonEntityType.ID.eq(personEntity.id()))
                .get()
                .value();
        Person personExpectedResult = PersonEntityMapper.transformFrom(personEntity);
        personTestObserver.assertResult(personExpectedResult);
        personTestObserver.onComplete();
    }
}