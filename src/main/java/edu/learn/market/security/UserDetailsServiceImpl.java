package edu.learn.market.security;

import edu.learn.market.domain.UserMP;
import edu.learn.market.repository.UserMPRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Collections;
import java.util.Locale;
import java.util.Optional;

@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOG = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final UserMPRepository userMPRepository;

    @Inject
    public UserDetailsServiceImpl(UserMPRepository userMPRepository) {
        this.userMPRepository = userMPRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        LOG.debug("Authenticating {}", login);
        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
        Optional<UserMP> userFromDatabase = userMPRepository.findOneByLogin(lowercaseLogin);
        return new User(lowercaseLogin, userFromDatabase.get().getPassword(), Collections.EMPTY_LIST);
//        return userFromDatabase
//                .map(userMP -> new User(lowercaseLogin, userMP.getPassword(), Collections.EMPTY_LIST))
//                .orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found"));
    }
}
