package hh.swd20.harjtyo.musicrepo;

import hh.swd20.harjtyo.musicrepo.domain.Session;
import hh.swd20.harjtyo.musicrepo.domain.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final SessionRepository sessionRepository;

    @Autowired
    public UserDetailServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Session currentSession = sessionRepository.findByUsername(username);

        return new org.springframework.security.core.userdetails.User(username, currentSession.getPasswordHash(),
                AuthorityUtils.createAuthorityList(currentSession.getRole()));
    }
}