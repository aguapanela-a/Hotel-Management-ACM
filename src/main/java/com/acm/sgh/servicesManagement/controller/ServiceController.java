package com.acm.sgh.servicesManagement.controller;

import com.acm.sgh.servicesManagement.dto.ServiceRequest;
import com.acm.sgh.servicesManagement.dto.ServiceResponse;
import com.acm.sgh.servicesManagement.service.OtherServiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
public class ServiceController {

    private final OtherServiceService otherServiceService;

    /** POST /api/services */
    @PostMapping
    public ResponseEntity<ServiceResponse> createService(@Valid @RequestBody ServiceRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(otherServiceService.createService(request));
    }

    /** GET /api/services */
    @GetMapping
    public ResponseEntity<List<ServiceResponse>> getAllServices() {
        return ResponseEntity.ok(otherServiceService.getAllServices());
    }

    /** GET /api/services/{id} */
    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse> getServiceById(@PathVariable UUID id) {
        return ResponseEntity.ok(otherServiceService.getServiceById(id));
    }

    /** GET /api/services/hotel/{hotelId} */
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<ServiceResponse>> getServicesByHotel(@PathVariable UUID hotelId) {
        return ResponseEntity.ok(otherServiceService.getServicesByHotel(hotelId));
    }

    /** PUT /api/services/{id} */
    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponse> updateService(@PathVariable UUID id,
                                                          @Valid @RequestBody ServiceRequest request) {
        return ResponseEntity.ok(otherServiceService.updateService(id, request));
    }

    /** DELETE /api/services/{id} */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable UUID id) {
        otherServiceService.deleteService(id);
        return ResponseEntity.noContent().build();
    }
}
