package com.acm.sgh.roomManagement.service;

import com.acm.sgh.hotelManagement.entities.Hotel;
import com.acm.sgh.hotelManagement.repository.HotelRepository;
import com.acm.sgh.roomManagement.dto.RoomRequest;
import com.acm.sgh.roomManagement.dto.RoomResponse;
import com.acm.sgh.roomManagement.entities.HotelRoom;
import com.acm.sgh.roomManagement.entities.RoomType;
import com.acm.sgh.roomManagement.repository.RoomRepository;
import com.acm.sgh.roomManagement.repository.RoomTypeRepository; // Import RoomTypeRepository
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final RoomTypeRepository roomTypeRepository; // Inject RoomTypeRepository

    @Transactional
    public RoomResponse createRoom(RoomRequest request) {
        Hotel hotel = hotelRepository.findById(request.getHotelId())
                .orElseThrow(() -> new IllegalArgumentException("Hotel no encontrado con id: " + request.getHotelId()));

        RoomType roomType = roomTypeRepository.findById(request.getRoomTypeId()) // Fetch RoomType
                .orElseThrow(() -> new IllegalArgumentException("Tipo de habitación no encontrado con id: " + request.getRoomTypeId()));

        HotelRoom room = HotelRoom.builder()
                .roomType(roomType) // Use the fetched RoomType entity
                .capacity(request.getCapacity())
                .price(request.getPrice())
                .availability(request.getAvailability() != null ? request.getAvailability() : "AVAILABLE")
                .status(request.getStatus() != null ? request.getStatus() : true)
                .floor(request.getFloor())
                .hotel(hotel)
                .build();

        return toResponse(roomRepository.save(room));
    }

    @Transactional(readOnly = true)
    public List<RoomResponse> getAllRooms() {
        return roomRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<RoomResponse> getRoomsByHotel(UUID hotelId) {
        return roomRepository.findByHotelId(hotelId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<RoomResponse> getAvailableRoomsByHotel(UUID hotelId) {
        return roomRepository.findByHotelIdAndAvailability(hotelId, "AVAILABLE").stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RoomResponse getRoomById(UUID id) {
        HotelRoom room = roomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Habitación no encontrada con id: " + id));
        return toResponse(room);
    }

    @Transactional
    public RoomResponse updateRoom(UUID id, RoomRequest request) {
        HotelRoom room = roomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Habitación no encontrada con id: " + id));

        Hotel hotel = hotelRepository.findById(request.getHotelId())
                .orElseThrow(() -> new IllegalArgumentException("Hotel no encontrado con id: " + request.getHotelId()));

        RoomType roomType = roomTypeRepository.findById(request.getRoomTypeId()) // Fetch RoomType
                .orElseThrow(() -> new IllegalArgumentException("Tipo de habitación no encontrado con id: " + request.getRoomTypeId()));

        room.setRoomType(roomType); // Use the fetched RoomType entity
        room.setCapacity(request.getCapacity());
        room.setPrice(request.getPrice());
        if (request.getAvailability() != null) room.setAvailability(request.getAvailability());
        if (request.getStatus() != null) room.setStatus(request.getStatus());
        room.setFloor(request.getFloor());
        room.setHotel(hotel);

        return toResponse(roomRepository.save(room));
    }

    @Transactional
    public void deleteRoom(UUID id) {
        if (!roomRepository.existsById(id)) {
            throw new IllegalArgumentException("Habitación no encontrada con id: " + id);
        }
        roomRepository.deleteById(id);
    }

    private RoomResponse toResponse(HotelRoom room) {
        return RoomResponse.builder()
                .id(room.getId())
                .roomTypeName(room.getRoomType().getName())
                .capacity(room.getCapacity())
                .price(room.getPrice())
                .availability(room.getAvailability())
                .status(room.getStatus())
                .floor(room.getFloor())
                .hotelId(room.getHotel().getId())
                .hotelName(room.getHotel().getHotelName())
                .build();
    }
}
