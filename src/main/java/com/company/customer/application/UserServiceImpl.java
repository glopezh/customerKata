package com.company.customer.application;

import com.company.customer.infraestructure.persistence.UserRepository;
import com.company.customer.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		User user = userRepository.findByName(name);
		if (user == null) {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		return new org.springframework.security.core.userdetails.User(
			user.getName(), user.getLastname(), Collections.emptyList());
	}

	@Override
	public void saveUser(User user) {
		user.setLastname(passwordEncoder.encode(user.getLastname()));
		userRepository.save(user);
	}

	public boolean authenticate(String name, String lastname,String address,String city,String email, boolean terms) {
		User user = userRepository.findByName(name);
		return user != null && passwordEncoder.matches(lastname, user.getLastname());
	}

	@Override
	public List<User> getAllInformation() {
		return userRepository.findAll();
	}
}
