package com.acm.sgh.servicesManagement.repository;

import com.acm.sgh.servicesManagement.entitie.OtherService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ServicesRepository extends JpaRepository<OtherService, UUID> {
    List<OtherService> findByHotelId(UUID hotelId);
}
