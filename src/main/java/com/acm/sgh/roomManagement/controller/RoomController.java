package com.acm.sgh.roomManagement.controller;

import com.acm.sgh.roomManagement.dto.RoomRequest;
import com.acm.sgh.roomManagement.dto.RoomResponse;
import com.acm.sgh.roomManagement.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    /** POST /api/rooms */
    @PostMapping
    public ResponseEntity<RoomResponse> createRoom(@Valid @RequestBody RoomRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roomService.createRoom(request));
    }

    /** GET /api/rooms */
    @GetMapping
    public ResponseEntity<List<RoomResponse>> getAllRooms() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    /** GET /api/rooms/{id} */
    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> getRoomById(@PathVariable UUID id) {
        return ResponseEntity.ok(roomService.getRoomById(id));
    }

    /** GET /api/rooms/hotel/{hotelId} */
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<RoomResponse>> getRoomsByHotel(@PathVariable UUID hotelId) {
        return ResponseEntity.ok(roomService.getRoomsByHotel(hotelId));
    }

    /** GET /api/rooms/hotel/{hotelId}/available */
    @GetMapping("/hotel/{hotelId}/available")
    public ResponseEntity<List<RoomResponse>> getAvailableRooms(@PathVariable UUID hotelId) {
        return ResponseEntity.ok(roomService.getAvailableRoomsByHotel(hotelId));
    }

    /** PUT /api/rooms/{id} */
    @PutMapping("/{id}")
    public ResponseEntity<RoomResponse> updateRoom(@PathVariable UUID id,
                                                    @Valid @RequestBody RoomRequest request) {
        return ResponseEntity.ok(roomService.updateRoom(id, request));
    }

    /** DELETE /api/rooms/{id} */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable UUID id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }
}
