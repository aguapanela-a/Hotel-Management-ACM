package com.acm.sgh.auth.controller;

import com.acm.sgh.auth.dto.*;
import com.acm.sgh.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * POST /api/auth/register
     * Registra un nuevo usuario (cliente, empleado o admin)
     */
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registerUser(request));
    }

    /**
     * POST /api/auth/login
     * Inicio de sesión
     */
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    /**
     * GET /api/auth/users
     * Obtiene todos los usuarios
     */
    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(authService.getAllUsers());
    }

    /**
     * GET /api/auth/users/{id}
     * Obtiene un usuario por ID
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(authService.getUserById(id));
    }

    /**
     * GET /api/auth/customers
     * Obtiene todos los clientes
     */
    @GetMapping("/customers")
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        return ResponseEntity.ok(authService.getAllCustomers());
    }

    /**
     * GET /api/auth/employees
     * Obtiene todos los empleados
     */
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        return ResponseEntity.ok(authService.getAllEmployees());
    }
}
