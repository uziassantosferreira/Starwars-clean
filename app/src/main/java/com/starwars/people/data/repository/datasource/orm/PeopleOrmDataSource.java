package com.starwars.people.data.repository.datasource.orm;

import com.starwars.people.data.repository.datasource.PeopleDataSource;
import com.starwars.people.data.repository.datasource.orm.entity.PersonEntity;
import com.starwars.people.data.repository.datasource.orm.entity.PersonEntityType;
import com.starwars.people.data.repository.datasource.orm.mapper.PersonEntityMapper;
import com.starwars.people.domain.model.Person;

import io.reactivex.Observable;
import io.requery.Persistable;
import io.requery.reactivex.ReactiveEntityStore;

public class PeopleOrmDataSource implements PeopleDataSource {

    private ReactiveEntityStore<Persistable> reactiveEntityStore;

    public PeopleOrmDataSource(ReactiveEntityStore<Persistable> reactiveEntityStore) {
        this.reactiveEntityStore = reactiveEntityStore;
    }

    @Override
    public Observable<Person> getPeople() {
        return reactiveEntityStore.select(PersonEntity.class)
                .get()
                .observable()
                .map(PersonEntityMapper::transformFrom);
    }

    @Override
    public Observable<Person> getPerson(String url) {
        return reactiveEntityStore.select(PersonEntity.class)
                .where(PersonEntityType.URL.eq(url))
                .get().observable().map(PersonEntityMapper::transformFrom);
    }

    @Override
    public Observable<Person> savePerson(Person person) {
        return reactiveEntityStore.insert(PersonEntityMapper.transformFrom(person), Integer.class)
                .toObservable()
                .flatMap(integer -> integer > 0 ?
                        reactiveEntityStore.findByKey(PersonEntity.class, integer)
                                .toObservable() :
                        Observable.error(new Throwable("dont save person")))
                .map(PersonEntityMapper::transformFrom);
    }

}
