package com.company.customer.controller;

import com.company.customer.application.UserService;
import com.company.customer.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/auth")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody User user) {
		String username = user.getUsername();
		String password = user.getPassword();

		if (userService.authenticate(username, password)) {
			return ResponseEntity.ok("Login exitoso para el usuario: " + username);
		} else {
			// Autenticación fallida
			return ResponseEntity.status(401).body("Error de autenticación: Credenciales incorrectas");
		}
	}

	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody User user) {
		userService.saveUser(user);
		return ResponseEntity.ok("Usuario registrado exitosamente");
	}
}
