package com.company.customer.application;

import com.company.customer.infraestructure.persistence.UserRepository;
import com.company.customer.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;  // Inyecta el PasswordEncoder aquí

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		return new org.springframework.security.core.userdetails.User(
			user.getUsername(), user.getPassword(), Collections.emptyList());
	}

	@Override
	public void saveUser(User user) {
		// Antes de guardar el usuario, asegúrate de codificar la contraseña
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	public boolean authenticate(String username, String password) {
		User user = userRepository.findByUsername(username);
		return user != null && passwordEncoder.matches(password, user.getPassword());
	}
}
