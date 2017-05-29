package com.starwars.films.data.repository.datasource.orm;

import com.starwars.films.data.repository.datasource.FilmsDataSource;
import com.starwars.films.data.repository.datasource.orm.entity.FilmEntity;
import com.starwars.films.data.repository.datasource.orm.entity.FilmEntityType;
import com.starwars.films.data.repository.datasource.orm.mapper.FilmEntityMapper;
import com.starwars.films.domain.model.Film;

import io.reactivex.Observable;
import io.requery.Persistable;
import io.requery.reactivex.ReactiveEntityStore;

public class FilmsOrmDataSource implements FilmsDataSource {

    private ReactiveEntityStore<Persistable> reactiveEntityStore;

    public FilmsOrmDataSource(ReactiveEntityStore<Persistable> reactiveEntityStore) {
        this.reactiveEntityStore = reactiveEntityStore;
    }

    @Override
    public Observable<Film> getFilm(String parameter) {
        return reactiveEntityStore.select(FilmEntity.class)
                .where(FilmEntityType.URL.eq(parameter))
                .get().observable().map(FilmEntityMapper::transformFrom);
    }

    @Override
    public Observable<Film> saveFilm(Film film) {
        return reactiveEntityStore.insert(FilmEntityMapper.transformFrom(film), Integer.class)
                .toObservable()
                .flatMap(integer -> integer > 0 ?
                        reactiveEntityStore.findByKey(FilmEntity.class, integer)
                                .toObservable() :
                        Observable.error(new Throwable("dont save person")))
                .map(FilmEntityMapper::transformFrom);
    }

}
