package hh.swd20.harjtyo.musicrepo.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubgenreRepository extends CrudRepository<Subgenre, Long> {
    public List<Subgenre> findBySubgenreName (String name);
}
