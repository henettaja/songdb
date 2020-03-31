package hh.swd20.harjtyo.musicrepo.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GenreRepository extends CrudRepository<Genre, Long> {
    public List<Genre> findByGenreName (String name);
}
