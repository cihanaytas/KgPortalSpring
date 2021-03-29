package com.kgportal.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kgportal.data.entity.UserKG;
import com.kgportal.data.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
    private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserKG> userkg = userRepository.findById(username);
        if (userkg == null) {
            throw new UsernameNotFoundException(username);
        }
        String password = userkg.get().getPassword();
        String role = userkg.get().getRole().toString();
//        return new User(developer.getUsername(), developer.getPassword(), emptyList());
		return User
				.withUsername(username)
				//.password(password)
				.password(passwordEncoder.encode(password))
				.roles(role)
				.build();
        
    }
}