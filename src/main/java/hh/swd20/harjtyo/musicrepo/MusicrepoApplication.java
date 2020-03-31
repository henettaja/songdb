package hh.swd20.harjtyo.musicrepo;

import hh.swd20.harjtyo.musicrepo.domain.GenreRepository;
import hh.swd20.harjtyo.musicrepo.domain.*;
import hh.swd20.harjtyo.musicrepo.domain.SongRepository;
import hh.swd20.harjtyo.musicrepo.domain.SubgenreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MusicrepoApplication {

	private static final Logger log = LoggerFactory.getLogger(MusicrepoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MusicrepoApplication.class, args);
	}

	@Bean
	public CommandLineRunner musicdemo (SongRepository songRepository,
										SubgenreRepository subgenreRepository,
										GenreRepository genreRepository) {
		return(args) -> {
			log.info("Saving some songs, subgenres and genres...");

			genreRepository.save(new Genre("Rock"));
			genreRepository.save(new Genre("Electronic"));
			genreRepository.save(new Genre("Pop"));

			subgenreRepository.save(new Subgenre("Electro-house", genreRepository.findByGenreName("Electronic").get(0)));
			subgenreRepository.save(new Subgenre("Indie", genreRepository.findByGenreName("Pop").get(0)));
			subgenreRepository.save(new Subgenre("Dance", genreRepository.findByGenreName("Electronic").get(0)));

			songRepository.save(new Song("Daybreak", "OVERWERK", "Daybreak",
												(subgenreRepository.findBySubgenreName("Electro-house").get(0))));
			songRepository.save(new Song("I'm so tired", "Lauv", "~how i'm feeling~",
												(subgenreRepository.findBySubgenreName("Indie").get(0))));
			songRepository.save(new Song("Good Right Here", "Kasperg", "Good Right Here",
												(subgenreRepository.findBySubgenreName("Dance").get(0))));
		};
	}

}
