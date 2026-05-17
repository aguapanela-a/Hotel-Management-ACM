package com.acm.sgh.invoice.repository;

import com.acm.sgh.invoice.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {
    Optional<Invoice> findByPaymentId(UUID paymentId);
    Optional<Invoice> findByInvoiceNumber(String invoiceNumber);
}
