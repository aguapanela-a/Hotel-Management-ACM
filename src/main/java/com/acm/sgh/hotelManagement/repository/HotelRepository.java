package com.acm.sgh.hotelManagement.repository;

import com.acm.sgh.hotelManagement.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface HotelRepository extends JpaRepository<Hotel, UUID> {
}
