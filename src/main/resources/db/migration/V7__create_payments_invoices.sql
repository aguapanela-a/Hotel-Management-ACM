-- =============================================
-- V7: Tablas de Pagos y Facturas
-- =============================================

CREATE TABLE payments (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    reservation_id UUID NOT NULL UNIQUE,
    payment_type VARCHAR(20) NOT NULL CHECK (payment_type IN ('CASH', 'CARD', 'BANK', 'DIGITAL')),
    amount NUMERIC(10, 2) NOT NULL,
    payment_date TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_payment_reservation FOREIGN KEY (reservation_id) REFERENCES reservations(id)
);

CREATE TABLE invoices (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    payment_id UUID NOT NULL UNIQUE,
    invoice_number VARCHAR(50) NOT NULL UNIQUE,
    issue_date TIMESTAMP NOT NULL DEFAULT NOW(),
    total NUMERIC(10, 2) NOT NULL,
    CONSTRAINT fk_invoice_payment FOREIGN KEY (payment_id) REFERENCES payments(id)
);
