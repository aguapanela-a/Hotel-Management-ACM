package com.acm.sgh.reservationsManagement.repository;

import com.acm.sgh.reservationsManagement.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
