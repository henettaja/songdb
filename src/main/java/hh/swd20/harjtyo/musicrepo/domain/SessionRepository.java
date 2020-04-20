package hh.swd20.harjtyo.musicrepo.domain;

import org.springframework.data.repository.CrudRepository;

public interface SessionRepository extends CrudRepository<Session, Long> {
    Session findByUsername(String username);
}
