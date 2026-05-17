package com.acm.sgh.auth.service;

import com.acm.sgh.auth.dto.*;
import com.acm.sgh.auth.entities.*;
import com.acm.sgh.auth.enumeration.Role;
import com.acm.sgh.auth.enumeration.UserType;
import com.acm.sgh.auth.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final AdminRepository adminRepository;

    // ---- Registro de usuario ----

    @Transactional
    public UserResponse registerUser(RegisterUserRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("El nombre de usuario ya está en uso: " + request.getUsername());
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword()) // En producción se usaría BCryptPasswordEncoder
                .userType(request.getUserType())
                .build();

        User savedUser = userRepository.save(user);

        // Crear el perfil correspondiente según el tipo
        switch (request.getUserType()) {
            case CUSTOMER -> customerRepository.save(Customer.builder().user(savedUser).build());
            case ADMIN -> adminRepository.save(Admin.builder().user(savedUser).build());
            case EMPLOYEE -> employeeRepository.save(
                    Employee.builder().user(savedUser).role(Role.RECEPTIONIST).build()
            );
        }

        return toUserResponse(savedUser);
    }

    // ---- Login (básico sin JWT) ----

    @Transactional(readOnly = true)
    public UserResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("Contraseña incorrecta");
        }

        return toUserResponse(user);
    }

    // ---- Consultas ----

    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toUserResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserResponse getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con id: " + id));
        return toUserResponse(user);
    }

    @Transactional(readOnly = true)
    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(c -> CustomerResponse.builder()
                        .id(c.getId())
                        .user(toUserResponse(c.getUser()))
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(e -> EmployeeResponse.builder()
                        .id(e.getId())
                        .user(toUserResponse(e.getUser()))
                        .role(e.getRole())
                        .build())
                .collect(Collectors.toList());
    }

    // ---- Mapper ----

    private UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .userType(user.getUserType())
                .build();
    }
}
