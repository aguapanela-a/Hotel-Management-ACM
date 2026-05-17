package com.acm.sgh.hotelManagement.controller;

import com.acm.sgh.hotelManagement.dto.BranchRequest;
import com.acm.sgh.hotelManagement.dto.HotelRequest;
import com.acm.sgh.hotelManagement.dto.HotelResponse;
import com.acm.sgh.hotelManagement.service.HotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    /** POST /api/hotels */
    @PostMapping
    public ResponseEntity<HotelResponse> createHotel(@Valid @RequestBody HotelRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createHotel(request));
    }

    /** GET /api/hotels */
    @GetMapping
    public ResponseEntity<List<HotelResponse>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    /** GET /api/hotels/{id} */
    @GetMapping("/{id}")
    public ResponseEntity<HotelResponse> getHotelById(@PathVariable UUID id) {
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }

    /** PUT /api/hotels/{id} */
    @PutMapping("/{id}")
    public ResponseEntity<HotelResponse> updateHotel(@PathVariable UUID id,
                                                      @Valid @RequestBody HotelRequest request) {
        return ResponseEntity.ok(hotelService.updateHotel(id, request));
    }

    /** DELETE /api/hotels/{id} */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable UUID id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }

    // ── Gestión de sucursales ──────────────────────────────────────────────

    /** PATCH /api/hotels/{id}/branch — Asignar o actualizar sucursal */
    @PatchMapping("/{id}/branch")
    public ResponseEntity<HotelResponse> assignBranch(@PathVariable UUID id,
                                                       @Valid @RequestBody BranchRequest request) {
        return ResponseEntity.ok(hotelService.assignBranch(id, request.getBranchName()));
    }

    /** DELETE /api/hotels/{id}/branch — Remover sucursal */
    @DeleteMapping("/{id}/branch")
    public ResponseEntity<HotelResponse> removeBranch(@PathVariable UUID id) {
        return ResponseEntity.ok(hotelService.removeBranch(id));
    }

    /** GET /api/hotels/branch/{branchName} — Hoteles por sucursal */
    @GetMapping("/branch/{branchName}")
    public ResponseEntity<List<HotelResponse>> getHotelsByBranch(@PathVariable String branchName) {
        return ResponseEntity.ok(hotelService.getHotelsByBranch(branchName));
    }
}
