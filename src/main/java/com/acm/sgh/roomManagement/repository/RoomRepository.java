package com.acm.sgh.roomManagement.repository;

import com.acm.sgh.roomManagement.entities.HotelRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<HotelRoom, UUID> {
    List<HotelRoom> findByHotelId(UUID hotelId);
    List<HotelRoom> findByHotelIdAndAvailability(UUID hotelId, String availability);
}
