package hh.swd20.harjtyo.musicrepo.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SongRepository extends CrudRepository<Song, Long> {
    public List<Song> findByName (String name);

}
