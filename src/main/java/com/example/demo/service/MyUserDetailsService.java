package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.DoctorEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.MyUserDetails;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<UserEntity> user = userRepository.findByUsername(username);

		if (user.isEmpty()) {
			throw new UsernameNotFoundException(username + " does not exist.");
		}
		if ((user.get() instanceof DoctorEntity) && user.get().getActive()) {
			User userN = new User(username, user.get().getPassword(), null);
			return MyUserDetails(userN, 2);
		}
		return null;
	}

	}

}
