-- =============================================
-- V3: Tabla de Hoteles
-- =============================================

CREATE TABLE hotels (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    hotel_branch VARCHAR(100),
    hotel_name VARCHAR(150) NOT NULL UNIQUE,
    hotel_address VARCHAR(255) NOT NULL,
    hotel_city VARCHAR(100) NOT NULL,
    hotel_phone VARCHAR(20) UNIQUE,
    hotel_email VARCHAR(150) UNIQUE,
    hotel_category VARCHAR(50),
    hotel_status VARCHAR(30) NOT NULL DEFAULT 'ACTIVE'
);
