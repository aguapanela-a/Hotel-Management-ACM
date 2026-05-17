-- =============================================
-- V6: Tabla de Reservas
-- =============================================

CREATE TABLE reservations (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    room_id UUID NOT NULL,
    customer_id UUID NOT NULL,
    service_id UUID,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'EARRING' CHECK (status IN ('EARRING', 'CONFIRMED', 'CANCELLED', 'COMPLETED')),
    total_price NUMERIC(10, 2),
    CONSTRAINT fk_reservation_room FOREIGN KEY (room_id) REFERENCES hotel_rooms(id),
    CONSTRAINT fk_reservation_customer FOREIGN KEY (customer_id) REFERENCES customers(id),
    CONSTRAINT fk_reservation_service FOREIGN KEY (service_id) REFERENCES other_services(id)
);
