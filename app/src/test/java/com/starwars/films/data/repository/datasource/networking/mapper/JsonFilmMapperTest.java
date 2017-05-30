package com.starwars.films.data.repository.datasource.networking.mapper;

import com.starwars.core.utils.json.JsonObjectConverter;
import com.starwars.core.utils.json.JsonResourceLoader;
import com.starwars.films.data.repository.datasource.networking.json.JsonFilm;
import com.starwars.films.domain.model.Film;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class JsonFilmMapperTest {

    private JsonFilm jsonFilm;

    @Before
    public void setUp() throws IOException {
        jsonFilm = JsonObjectConverter.convertFromJson(getJson(), JsonFilm.class);
    }

    private String getJson() throws IOException {
        return JsonResourceLoader
                .forResource("json.film/JsonFilm.json")
                .getJson();
    }

    @Test
    public void correctly_transform_jsonfilm_to_domain_film() {
        Film film = JsonFilmMapper.transformFrom(jsonFilm);
        assertThat(film.title(), is(jsonFilm.title()));
        assertThat(film.episodeId(), is(jsonFilm.episodeId()));
        assertThat(film.openingCrawl(), is(jsonFilm.openingCrawl()));
        assertThat(film.director(), is(jsonFilm.director()));
        assertThat(film.producer(), is(jsonFilm.producer()));
        assertThat(film.releaseDate(), is(jsonFilm.releaseDate()));
        assertThat(film.characters(), is(jsonFilm.characters()));
        assertThat(film.planets(), is(jsonFilm.planets()));
        assertThat(film.starships(), is(jsonFilm.starships()));
        assertThat(film.vehicles(), is(jsonFilm.vehicles()));
        assertThat(film.species(), is(jsonFilm.species()));
        assertThat(film.created(), is(jsonFilm.created()));
        assertThat(film.edited(), is(jsonFilm.edited()));
        assertThat(film.url(), is(jsonFilm.url()));
        assertNull(film.id());
    }

}
