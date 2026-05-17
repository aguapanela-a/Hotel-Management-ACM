package com.acm.sgh.paymentManagement.controller;

import com.acm.sgh.paymentManagement.dto.PaymentRequest;
import com.acm.sgh.paymentManagement.dto.PaymentResponse;
import com.acm.sgh.paymentManagement.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    /** POST /api/payments */
    @PostMapping
    public ResponseEntity<PaymentResponse> registerPayment(@Valid @RequestBody PaymentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.registerPayment(request));
    }

    /** GET /api/payments */
    @GetMapping
    public ResponseEntity<List<PaymentResponse>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    /** GET /api/payments/{id} */
    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable UUID id) {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    /** GET /api/payments/reservation/{reservationId} */
    @GetMapping("/reservation/{reservationId}")
    public ResponseEntity<PaymentResponse> getPaymentByReservation(@PathVariable UUID reservationId) {
        return ResponseEntity.ok(paymentService.getPaymentByReservation(reservationId));
    }
}
