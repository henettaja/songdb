package hh.swd20.harjtyo.musicrepo;

import static org.assertj.core.api.Assertions.*;

import hh.swd20.harjtyo.musicrepo.domain.Genre;
import hh.swd20.harjtyo.musicrepo.domain.GenreRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

//Tests not working due to PostgreSQL being the main database

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = {MusicrepoApplication.class, MusicAppTestJpaConfig.class})
public class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void findByGenreNameShouldReturnGenre() {
        List<Genre> genres = genreRepository.findByGenreName("Rock");

        assertThat(genres).hasSize(1);
        assertThat(genres.get(0).getGenreName()).isEqualTo("Rock");
    }

    @Test
    public void createNewGenre() {
        List<Genre> genres = new ArrayList<>();
        Genre genre = new Genre("Jazz");

        genreRepository.save(genre);
        assertThat(genreRepository.findByGenreName("Jazz").get(0)).isEqualTo(genre);
    }

    @Test
    public void deleteGenre() {
        Genre genre = new Genre("Jazz");

        genreRepository.save(genre);
        assertThat(genreRepository.findByGenreName("Jazz").get(0)).isEqualTo(genre);

        genreRepository.delete(genre);
        assertThat(genreRepository.findByGenreName("Jazz")).isEmpty();
    }
}
