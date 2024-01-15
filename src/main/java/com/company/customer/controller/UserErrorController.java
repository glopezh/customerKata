package com.company.customer.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserErrorController implements ErrorController {

	@RequestMapping("/error")
	public ResponseEntity<String> handleError() {
		return ResponseEntity.status(405).body("Método no permitido");
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception e) {
		return ResponseEntity.status(500).body("Error en el servidor: " + e.getMessage());
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<String> handleAuthenticationException(AuthenticationException ex) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error de autenticación: " + ex.getMessage());
	}
}
