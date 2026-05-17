-- =============================================
-- V9: Add new relationships and RoomType table
-- =============================================

-- Add admin_id to employees table
ALTER TABLE employees
ADD COLUMN admin_id UUID;

-- Add foreign key constraint for admin_id in employees
ALTER TABLE employees
ADD CONSTRAINT fk_employee_admin FOREIGN KEY (admin_id) REFERENCES admins(id) ON DELETE SET NULL;

-- Add hotel_id to users table
ALTER TABLE users
ADD COLUMN hotel_id UUID;

-- Add foreign key constraint for hotel_id in users
ALTER TABLE users
ADD CONSTRAINT fk_user_hotel FOREIGN KEY (hotel_id) REFERENCES hotels(id) ON DELETE SET NULL;

-- Create room_types table
CREATE TABLE room_types (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(50) NOT NULL UNIQUE
);

-- Add room_type_id to hotel_rooms table
ALTER TABLE hotel_rooms
ADD COLUMN room_type_id UUID;

-- Add foreign key constraint for room_type_id in hotel_rooms
ALTER TABLE hotel_rooms
ADD CONSTRAINT fk_hotel_room_room_type FOREIGN KEY (room_type_id) REFERENCES room_types(id) ON DELETE RESTRICT;

-- IMPORTANT: You might need to update existing hotel_rooms records
-- to set a valid room_type_id if you have existing data.
-- For example, if you had an enum 'SINGLE', you might insert a 'SINGLE'
-- room type and then update existing rooms.
-- Example:
-- INSERT INTO room_types (id, name) VALUES (gen_random_uuid(), 'SINGLE');
-- UPDATE hotel_rooms SET room_type_id = (SELECT id FROM room_types WHERE name = 'SINGLE') WHERE room_type = 'SINGLE';
-- Then drop the old room_type column if it was an enum.
-- ALTER TABLE hotel_rooms DROP COLUMN room_type;
