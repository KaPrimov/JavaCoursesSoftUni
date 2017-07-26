package softuniBlog.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import softuniBlog.entity.User;
import softuniBlog.repository.UserRepository;

import java.util.Set;
import java.util.stream.Collectors;

    @Service("blogUserDetailsService")
    public class BlogUserDetailsService implements UserDetailsService {

    }
