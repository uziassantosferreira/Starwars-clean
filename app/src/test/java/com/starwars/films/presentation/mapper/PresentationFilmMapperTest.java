package com.starwars.films.presentation.mapper;

import com.starwars.films.domain.model.Film;
import com.starwars.films.presentation.model.PresentationFilm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PresentationFilmMapperTest {

    private static final String MOCK_TITLE = "title";
    private static final String MOCK_OPENING_CRAWL = "opening crawl";

    @Test
    public void correctly_transform_film_to_presentation_film() {
        Film film = Film.builder()
                .setTitle(MOCK_TITLE)
                .setOpeningCrawl(MOCK_OPENING_CRAWL)
                .build();

        PresentationFilm presentationFilm = PresentationFilmMapper.transformFrom(film);
        assertThat(film.title(), is(presentationFilm.title()));
        assertThat(film.openingCrawl(), is(presentationFilm.openingCrawl()));
    }
    
}
