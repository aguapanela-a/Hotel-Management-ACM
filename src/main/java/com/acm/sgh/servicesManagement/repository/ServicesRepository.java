package com.acm.sgh.servicesManagement.repository;

import com.acm.sgh.hotelManagement.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServicesRepository extends JpaRepository<Hotel, UUID> {
}
