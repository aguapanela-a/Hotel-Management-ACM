-- =============================================
-- V1: Tabla de Usuarios
-- =============================================

CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    user_type VARCHAR(20) NOT NULL CHECK (user_type IN ('ADMIN', 'CUSTOMER', 'EMPLOYEE'))
);
