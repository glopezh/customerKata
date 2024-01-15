package com.company.customer.application;

import com.company.customer.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
	void saveUser(User user);

	boolean authenticate(String username, String password);
}
