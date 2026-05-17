package com.acm.sgh.reservationsManagement.service;

import com.acm.sgh.auth.entities.Customer;
import com.acm.sgh.auth.repository.CustomerRepository;
import com.acm.sgh.reservationsManagement.dto.ReservationRequest;
import com.acm.sgh.reservationsManagement.dto.ReservationResponse;
import com.acm.sgh.reservationsManagement.dto.UpdateReservationStatusRequest;
import com.acm.sgh.reservationsManagement.entities.Reservation;
import com.acm.sgh.reservationsManagement.enumerations.ReservationStatus;
import com.acm.sgh.reservationsManagement.repository.ReservationRepository;
import com.acm.sgh.roomManagement.entities.HotelRoom;
import com.acm.sgh.roomManagement.repository.RoomRepository;
import com.acm.sgh.servicesManagement.entitie.OtherService;
import com.acm.sgh.servicesManagement.repository.ServicesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final CustomerRepository customerRepository;
    private final ServicesRepository servicesRepository;

    @Transactional
    public ReservationResponse createReservation(ReservationRequest request) {
        if (request.getStartDate().isAfter(request.getEndDate()) ||
                request.getStartDate().isEqual(request.getEndDate())) {
            throw new IllegalArgumentException("La fecha de inicio debe ser anterior a la fecha de fin");
        }

        HotelRoom room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new IllegalArgumentException("Habitación no encontrada con id: " + request.getRoomId()));

        if (!"AVAILABLE".equals(room.getAvailability())) {
            throw new IllegalArgumentException("La habitación no está disponible");
        }

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con id: " + request.getCustomerId()));

        OtherService service = null;
        if (request.getServiceId() != null) {
            service = servicesRepository.findById(request.getServiceId())
                    .orElseThrow(() -> new IllegalArgumentException("Servicio no encontrado con id: " + request.getServiceId()));
        }

        // Calcular precio total (días * precio por noche + servicio)
        long days = ChronoUnit.DAYS.between(request.getStartDate().toLocalDate(), request.getEndDate().toLocalDate());
        if (days < 1) days = 1;
        BigDecimal roomTotal = room.getPrice().multiply(BigDecimal.valueOf(days));
        BigDecimal serviceTotal = service != null ? service.getPrice() : BigDecimal.ZERO;
        BigDecimal totalPrice = roomTotal.add(serviceTotal);

        Reservation reservation = Reservation.builder()
                .room(room)
                .customer(customer)
                .otherService(service)
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .status(ReservationStatus.PENDING)
                .totalPrice(totalPrice)
                .build();

        // Marcar habitación como no disponible
        room.setAvailability("OCCUPIED");
        roomRepository.save(room);

        return toResponse(reservationRepository.save(reservation));
    }

    @Transactional(readOnly = true)
    public List<ReservationResponse> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ReservationResponse getReservationById(UUID id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada con id: " + id));
        return toResponse(reservation);
    }

    @Transactional(readOnly = true)
    public List<ReservationResponse> getReservationsByCustomer(UUID customerId) {
        return reservationRepository.findByCustomerId(customerId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public ReservationResponse updateReservationStatus(UUID id, UpdateReservationStatusRequest request) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada con id: " + id));

        reservation.setStatus(request.getStatus());

        // Si se cancela o completa, liberar la habitación
        if (request.getStatus() == ReservationStatus.CANCELLED ||
                request.getStatus() == ReservationStatus.COMPLETED) {
            HotelRoom room = reservation.getRoom();
            room.setAvailability("AVAILABLE");
            roomRepository.save(room);
        }

        return toResponse(reservationRepository.save(reservation));
    }

    @Transactional
    public ReservationResponse cancelReservation(UUID id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada con id: " + id));

        if (reservation.getStatus() == ReservationStatus.COMPLETED) {
            throw new IllegalArgumentException("No se puede cancelar una reserva ya completada");
        }

        reservation.setStatus(ReservationStatus.CANCELLED);
        reservation.getRoom().setAvailability("AVAILABLE");
        roomRepository.save(reservation.getRoom());

        return toResponse(reservationRepository.save(reservation));
    }

    private ReservationResponse toResponse(Reservation r) {
        return ReservationResponse.builder()
                .id(r.getId())
                .roomId(r.getRoom().getId())
                .roomType(r.getRoom().getRoomType().getName())
                .customerId(r.getCustomer().getId())
                .customerUsername(r.getCustomer().getUser().getUsername())
                .serviceId(r.getOtherService() != null ? r.getOtherService().getId() : null)
                .serviceName(r.getOtherService() != null ? r.getOtherService().getServiceName() : null)
                .startDate(r.getStartDate())
                .endDate(r.getEndDate())
                .status(r.getStatus())
                .totalPrice(r.getTotalPrice())
                .build();
    }
}
