package com.acm.sgh.auth.repository;

import com.acm.sgh.auth.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdminRepository extends JpaRepository<Admin, UUID> {
}
