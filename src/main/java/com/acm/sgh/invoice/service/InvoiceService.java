package com.acm.sgh.invoice.service;

import com.acm.sgh.invoice.dto.InvoiceResponse;
import com.acm.sgh.invoice.entities.Invoice;
import com.acm.sgh.invoice.repository.InvoiceRepository;
import com.acm.sgh.paymentManagement.entities.Payment;
import com.acm.sgh.paymentManagement.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final PaymentRepository paymentRepository;

    @Transactional
    public InvoiceResponse generateInvoice(UUID paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new IllegalArgumentException("Pago no encontrado con id: " + paymentId));

        if (invoiceRepository.findByPaymentId(paymentId).isPresent()) {
            throw new IllegalArgumentException("Ya existe una factura para este pago");
        }

        String invoiceNumber = generateInvoiceNumber();

        Invoice invoice = Invoice.builder()
                .payment(payment)
                .invoiceNumber(invoiceNumber)
                .issueDate(LocalDateTime.now())
                .total(payment.getAmount())
                .build();

        return toResponse(invoiceRepository.save(invoice));
    }

    @Transactional(readOnly = true)
    public List<InvoiceResponse> getAllInvoices() {
        return invoiceRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public InvoiceResponse getInvoiceById(UUID id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Factura no encontrada con id: " + id));
        return toResponse(invoice);
    }

    @Transactional(readOnly = true)
    public InvoiceResponse getInvoiceByPayment(UUID paymentId) {
        Invoice invoice = invoiceRepository.findByPaymentId(paymentId)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró factura para el pago: " + paymentId));
        return toResponse(invoice);
    }

    @Transactional(readOnly = true)
    public InvoiceResponse getInvoiceByNumber(String invoiceNumber) {
        Invoice invoice = invoiceRepository.findByInvoiceNumber(invoiceNumber)
                .orElseThrow(() -> new IllegalArgumentException("Factura no encontrada con número: " + invoiceNumber));
        return toResponse(invoice);
    }

    private String generateInvoiceNumber() {
        String datePart = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String uniquePart = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        return "SGH-" + datePart + "-" + uniquePart;
    }

    private InvoiceResponse toResponse(Invoice invoice) {
        Payment payment = invoice.getPayment();
        return InvoiceResponse.builder()
                .id(invoice.getId())
                .invoiceNumber(invoice.getInvoiceNumber())
                .paymentId(payment.getId())
                .reservationId(payment.getReservation().getId())
                .paymentType(payment.getPaymentType())
                .total(invoice.getTotal())
                .issueDate(invoice.getIssueDate())
                .customerUsername(payment.getReservation().getCustomer().getUser().getUsername())
                .build();
    }
}
