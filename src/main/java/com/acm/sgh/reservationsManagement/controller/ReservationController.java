package com.acm.sgh.reservationsManagement.controller;

import com.acm.sgh.reservationsManagement.dto.ReservationRequest;
import com.acm.sgh.reservationsManagement.dto.ReservationResponse;
import com.acm.sgh.reservationsManagement.dto.UpdateReservationStatusRequest;
import com.acm.sgh.reservationsManagement.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    /** POST /api/reservations */
    @PostMapping
    public ResponseEntity<ReservationResponse> createReservation(@Valid @RequestBody ReservationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.createReservation(request));
    }

    /** GET /api/reservations */
    @GetMapping
    public ResponseEntity<List<ReservationResponse>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    /** GET /api/reservations/{id} */
    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponse> getReservationById(@PathVariable UUID id) {
        return ResponseEntity.ok(reservationService.getReservationById(id));
    }

    /** GET /api/reservations/customer/{customerId} */
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ReservationResponse>> getReservationsByCustomer(@PathVariable UUID customerId) {
        return ResponseEntity.ok(reservationService.getReservationsByCustomer(customerId));
    }

    /** PATCH /api/reservations/{id}/status */
    @PatchMapping("/{id}/status")
    public ResponseEntity<ReservationResponse> updateStatus(@PathVariable UUID id,
                                                             @Valid @RequestBody UpdateReservationStatusRequest request) {
        return ResponseEntity.ok(reservationService.updateReservationStatus(id, request));
    }

    /** PATCH /api/reservations/{id}/cancel */
    @PatchMapping("/{id}/cancel")
    public ResponseEntity<ReservationResponse> cancelReservation(@PathVariable UUID id) {
        return ResponseEntity.ok(reservationService.cancelReservation(id));
    }
}
