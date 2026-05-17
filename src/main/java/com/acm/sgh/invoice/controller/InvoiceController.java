package com.acm.sgh.invoice.controller;

import com.acm.sgh.invoice.dto.InvoiceResponse;
import com.acm.sgh.invoice.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    /** POST /api/invoices/generate/{paymentId} */
    @PostMapping("/generate/{paymentId}")
    public ResponseEntity<InvoiceResponse> generateInvoice(@PathVariable UUID paymentId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceService.generateInvoice(paymentId));
    }

    /** GET /api/invoices */
    @GetMapping
    public ResponseEntity<List<InvoiceResponse>> getAllInvoices() {
        return ResponseEntity.ok(invoiceService.getAllInvoices());
    }

    /** GET /api/invoices/{id} */
    @GetMapping("/{id}")
    public ResponseEntity<InvoiceResponse> getInvoiceById(@PathVariable UUID id) {
        return ResponseEntity.ok(invoiceService.getInvoiceById(id));
    }

    /** GET /api/invoices/payment/{paymentId} */
    @GetMapping("/payment/{paymentId}")
    public ResponseEntity<InvoiceResponse> getInvoiceByPayment(@PathVariable UUID paymentId) {
        return ResponseEntity.ok(invoiceService.getInvoiceByPayment(paymentId));
    }

    /** GET /api/invoices/number/{invoiceNumber} */
    @GetMapping("/number/{invoiceNumber}")
    public ResponseEntity<InvoiceResponse> getInvoiceByNumber(@PathVariable String invoiceNumber) {
        return ResponseEntity.ok(invoiceService.getInvoiceByNumber(invoiceNumber));
    }
}
