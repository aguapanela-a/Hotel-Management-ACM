-- =============================================
-- V5: Tabla de Servicios Adicionales
-- =============================================

CREATE TABLE other_services (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    service_type VARCHAR(30) NOT NULL CHECK (service_type IN ('RESTAURANT', 'SPA', 'LAUNDRY', 'TRANSPORTATION')),
    service_name VARCHAR(150) NOT NULL,
    description VARCHAR(255),
    price NUMERIC(10, 2) NOT NULL DEFAULT 0.00,
    hotel_id UUID NOT NULL,
    CONSTRAINT fk_service_hotel FOREIGN KEY (hotel_id) REFERENCES hotels(id) ON DELETE CASCADE
);
