package com.starwars.films.data.repository.datasource.orm.mapper;

import com.google.common.reflect.TypeToken;
import com.starwars.core.utils.json.JsonObjectConverter;
import com.starwars.core.utils.json.JsonResourceLoader;
import com.starwars.films.data.repository.datasource.orm.entity.FilmEntity;
import com.starwars.films.domain.model.Film;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class FilmEntityMapperTest {

    private List<FilmEntity> filmEntities;

    @Before
    public void setUp() {
        filmEntities = JsonObjectConverter.convertArrayFromJson(getJson(),
                new TypeToken<List<FilmEntity>>() {}.getType());
    }

    private String getJson() {
        try {
            return JsonResourceLoader
                    .forResource("json.film/FilmEntityArray.json")
                    .getJson();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void correctly_transform_filmentity_to_domain_film() {
        FilmEntity filmEntity = filmEntities.get(0);
        Film film = FilmEntityMapper.transformFrom(filmEntity);
        assertThatFieldsInFilm(film, filmEntity);
    }

    @Test
    public void correctly_transform_filmentities_to_domain_films() {
        List<Film> films = FilmEntityMapper.transformFrom(filmEntities);
        int count = filmEntities.size();
        for (int position = 0; position < count; position++){
            assertThatFieldsInFilm(films.get(position), filmEntities.get(position));
        }
        assertThat(count, is(films.size()));
    }

    private void assertThatFieldsInFilm(Film film, FilmEntity filmEntity) {
        assertThat(film.id(), is(filmEntity.id()));
        assertThat(film.title(), is(filmEntity.title()));
        assertThat(film.episodeId(), is(filmEntity.episodeId()));
        assertThat(film.openingCrawl(), is(filmEntity.openingCrawl()));
        assertThat(film.director(), is(filmEntity.director()));
        assertThat(film.producer(), is(filmEntity.producer()));
        assertThat(film.releaseDate(), is(filmEntity.releaseDate()));
        assertThat(film.characters(), is(filmEntity.characters()));
        assertThat(film.planets(), is(filmEntity.planets()));
        assertThat(film.starships(), is(filmEntity.starships()));
        assertThat(film.vehicles(), is(filmEntity.vehicles()));
        assertThat(film.species(), is(filmEntity.species()));
        assertThat(film.created(), is(filmEntity.created()));
        assertThat(film.edited(), is(filmEntity.edited()));
        assertThat(film.url(), is(filmEntity.url()));
    }

}
