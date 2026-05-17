package com.acm.sgh.paymentManagement.service;

import com.acm.sgh.paymentManagement.dto.PaymentRequest;
import com.acm.sgh.paymentManagement.dto.PaymentResponse;
import com.acm.sgh.paymentManagement.entities.Payment;
import com.acm.sgh.paymentManagement.repository.PaymentRepository;
import com.acm.sgh.reservationsManagement.entities.Reservation;
import com.acm.sgh.reservationsManagement.enumerations.ReservationStatus;
import com.acm.sgh.reservationsManagement.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ReservationRepository reservationRepository;

    @Transactional
    public PaymentResponse registerPayment(PaymentRequest request) {
        Reservation reservation = reservationRepository.findById(request.getReservationId())
                .orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada con id: " + request.getReservationId()));

        if (reservation.getStatus() == ReservationStatus.CANCELLED) {
            throw new IllegalArgumentException("No se puede registrar un pago para una reserva cancelada");
        }

        if (paymentRepository.findByReservationId(request.getReservationId()).isPresent()) {
            throw new IllegalArgumentException("Esta reserva ya tiene un pago registrado");
        }

        Payment payment = Payment.builder()
                .reservation(reservation)
                .paymentType(request.getPaymentType())
                .amount(request.getAmount())
                .paymentDate(LocalDateTime.now())
                .build();

        // Confirmar la reserva al pagar
        reservation.setStatus(ReservationStatus.CONFIRMED);
        reservationRepository.save(reservation);

        return toResponse(paymentRepository.save(payment));
    }

    @Transactional(readOnly = true)
    public List<PaymentResponse> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PaymentResponse getPaymentById(UUID id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pago no encontrado con id: " + id));
        return toResponse(payment);
    }

    @Transactional(readOnly = true)
    public PaymentResponse getPaymentByReservation(UUID reservationId) {
        Payment payment = paymentRepository.findByReservationId(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró pago para la reserva: " + reservationId));
        return toResponse(payment);
    }

    private PaymentResponse toResponse(Payment payment) {
        return PaymentResponse.builder()
                .id(payment.getId())
                .reservationId(payment.getReservation().getId())
                .paymentType(payment.getPaymentType())
                .amount(payment.getAmount())
                .paymentDate(payment.getPaymentDate())
                .build();
    }
}
