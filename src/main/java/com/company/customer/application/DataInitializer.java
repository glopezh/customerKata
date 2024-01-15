package com.company.customer.application;

import com.company.customer.infraestructure.persistence.UserRepository;
import com.company.customer.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void run(String... args) throws Exception {
		initializeUsers();
	}

	private void initializeUsers() {
		if (userRepository.findByUsername("user1") == null) {
			User user1 = new User("user1", passwordEncoder.encode("password1"));
			userRepository.save(user1);
		}

		if (userRepository.findByUsername("user2") == null) {
			User user2 = new User("user2", passwordEncoder.encode("password2"));
			userRepository.save(user2);
		}
	}
}
