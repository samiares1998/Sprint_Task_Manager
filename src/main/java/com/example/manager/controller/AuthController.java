package com.example.manager.controller;

import com.example.manager.config.security.JwtService;
import com.example.manager.dto.AuthRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            // Si la autenticación es exitosa, genera un JWT y retorna la respuesta
            final String jwt = jwtService.generateToken(authRequest.getUsername());// Lógica para generar el token JWT
            return ResponseEntity.ok(jwt);
        } catch (BadCredentialsException e) {
            throw new Exception("Credenciales incorrectas", e);
        }
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Test endpoint is working");
    }
}

