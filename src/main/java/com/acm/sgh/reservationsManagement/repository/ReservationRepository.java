package com.acm.sgh.reservationsManagement.repository;

import com.acm.sgh.reservationsManagement.entities.Reservation;
import com.acm.sgh.reservationsManagement.enumerations.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    List<Reservation> findByCustomerId(UUID customerId);
    List<Reservation> findByStatus(ReservationStatus status);
    List<Reservation> findByRoomId(UUID roomId);
}
