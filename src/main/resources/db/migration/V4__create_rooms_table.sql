-- =============================================
-- V4: Tabla de Habitaciones
-- =============================================

CREATE TABLE hotel_rooms (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    room_type VARCHAR(20) NOT NULL CHECK (room_type IN ('SINGLE', 'DOUBLE', 'SUITE', 'PRESIDENTIAL')),
    capacity INT NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    availability VARCHAR(20) NOT NULL DEFAULT 'AVAILABLE',
    status BOOLEAN NOT NULL DEFAULT TRUE,
    floor INT NOT NULL,
    hotel_id UUID NOT NULL,
    CONSTRAINT fk_room_hotel FOREIGN KEY (hotel_id) REFERENCES hotels(id) ON DELETE CASCADE
);
