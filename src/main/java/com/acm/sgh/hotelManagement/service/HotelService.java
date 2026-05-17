package com.acm.sgh.hotelManagement.service;

import com.acm.sgh.hotelManagement.dto.HotelRequest;
import com.acm.sgh.hotelManagement.dto.HotelResponse;
import com.acm.sgh.hotelManagement.entities.Hotel;
import com.acm.sgh.hotelManagement.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;

    @Transactional
    public HotelResponse createHotel(HotelRequest request) {
        Hotel hotel = Hotel.builder()
                .hotelBranch(request.getHotelBranch())
                .hotelName(request.getHotelName())
                .hotelAddress(request.getHotelAddress())
                .hotelCity(request.getHotelCity())
                .hotelPhone(request.getHotelPhone())
                .hotelEmail(request.getHotelEmail())
                .hotelCategory(request.getHotelCategory())
                .hotelStatus(request.getHotelStatus() != null ? request.getHotelStatus() : "ACTIVE")
                .build();

        return toResponse(hotelRepository.save(hotel));
    }

    @Transactional(readOnly = true)
    public List<HotelResponse> getAllHotels() {
        return hotelRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public HotelResponse getHotelById(UUID id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Hotel no encontrado con id: " + id));
        return toResponse(hotel);
    }

    @Transactional
    public HotelResponse updateHotel(UUID id, HotelRequest request) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Hotel no encontrado con id: " + id));

        hotel.setHotelBranch(request.getHotelBranch());
        hotel.setHotelName(request.getHotelName());
        hotel.setHotelAddress(request.getHotelAddress());
        hotel.setHotelCity(request.getHotelCity());
        hotel.setHotelPhone(request.getHotelPhone());
        hotel.setHotelEmail(request.getHotelEmail());
        hotel.setHotelCategory(request.getHotelCategory());
        if (request.getHotelStatus() != null) hotel.setHotelStatus(request.getHotelStatus());

        return toResponse(hotelRepository.save(hotel));
    }

    // ── Gestión de sucursales ──────────────────────────────────────────────

    @Transactional
    public HotelResponse assignBranch(UUID id, String branchName) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Hotel no encontrado con id: " + id));
        hotel.setHotelBranch(branchName);
        return toResponse(hotelRepository.save(hotel));
    }

    @Transactional
    public HotelResponse removeBranch(UUID id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Hotel no encontrado con id: " + id));
        hotel.setHotelBranch(null);
        return toResponse(hotelRepository.save(hotel));
    }

    @Transactional(readOnly = true)
    public List<HotelResponse> getHotelsByBranch(String branchName) {
        return hotelRepository.findAll().stream()
                .filter(h -> branchName.equalsIgnoreCase(h.getHotelBranch()))
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // ── Eliminar hotel ─────────────────────────────────────────────────────

    @Transactional
    public void deleteHotel(UUID id) {
        if (!hotelRepository.existsById(id)) {
            throw new IllegalArgumentException("Hotel no encontrado con id: " + id);
        }
        hotelRepository.deleteById(id);
    }

    private HotelResponse toResponse(Hotel hotel) {
        return HotelResponse.builder()
                .id(hotel.getId())
                .hotelBranch(hotel.getHotelBranch())
                .hotelName(hotel.getHotelName())
                .hotelAddress(hotel.getHotelAddress())
                .hotelCity(hotel.getHotelCity())
                .hotelPhone(hotel.getHotelPhone())
                .hotelEmail(hotel.getHotelEmail())
                .hotelCategory(hotel.getHotelCategory())
                .hotelStatus(hotel.getHotelStatus())
                .build();
    }
}
