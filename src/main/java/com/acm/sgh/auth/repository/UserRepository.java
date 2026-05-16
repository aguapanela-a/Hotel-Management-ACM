package com.acm.sgh.auth.repository;

import com.acm.sgh.auth.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
