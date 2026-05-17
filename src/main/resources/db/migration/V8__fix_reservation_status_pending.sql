-- =============================================
-- V8: Corrección estado EARRING → PENDING
-- =============================================

-- Actualizar registros existentes
UPDATE reservations SET status = 'PENDING' WHERE status = 'EARRING';

-- Reemplazar constraint de CHECK
ALTER TABLE reservations DROP CONSTRAINT IF EXISTS reservations_status_check;

ALTER TABLE reservations
    ADD CONSTRAINT reservations_status_check
    CHECK (status IN ('PENDING', 'CONFIRMED', 'CANCELLED', 'COMPLETED'));

-- Actualizar valor por defecto
ALTER TABLE reservations ALTER COLUMN status SET DEFAULT 'PENDING';
