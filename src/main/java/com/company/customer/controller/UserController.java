package com.company.customer.controller;

import com.company.customer.application.UserService;
import com.company.customer.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
		String name = user.getName();
		String lastname = user.getLastname();

		if (userService.authenticate(name, lastname)) {
			return ResponseEntity.ok("Hi, " + name.toUpperCase() + " " + lastname.toUpperCase());
		} else {
			// Autenticación fallida
			return ResponseEntity.status(401).body("Error de autenticación: Credenciales incorrectas");
		}
	}

	@GetMapping("/signin")
	public ResponseEntity<List<User>> getInformation() {
		List<User> allInformation = userService.getAllInformation();
		return new ResponseEntity<>(allInformation, HttpStatus.OK);
	}

	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody User user) {
		userService.saveUser(user);
		return ResponseEntity.ok("Usuario registrado exitosamente");
	}
}
