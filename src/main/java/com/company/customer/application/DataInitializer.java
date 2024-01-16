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
		if (userRepository.findByName("geraldine") == null) {
			User user1 = new User("geraldine", passwordEncoder.encode("lopez"),"Carrer de Neptu 33","Barcelona", "geraldine@gmail.com",false);
			userRepository.save(user1);
		}

		if (userRepository.findByName("sergi") == null) {
			User user2 = new User("sergi",passwordEncoder.encode("lopez"),"Carrer de Eusebi 6","Barcelona", "sergi@gmail.com",true);
			userRepository.save(user2);
		}
	}
}
