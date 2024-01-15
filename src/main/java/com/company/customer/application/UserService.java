package com.company.customer.application;

import com.company.customer.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

	void saveUser(User user);

	boolean authenticate(String name, String lastname);

	List<User> getAllInformation();
}
