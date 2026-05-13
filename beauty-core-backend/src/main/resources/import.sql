-- 1. ROLES
INSERT INTO roles (authority) VALUES ('ROLE_ADMIN');
INSERT INTO roles (authority) VALUES ('ROLE_PROFESSIONAL');

-- 2. ESPECIALIZAÇÕES
INSERT INTO specializations (name, created_at, updated_at) VALUES ('Cabeleleiro(a) tradicional', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO specializations (name, created_at, updated_at) VALUES ('Cabeleleiro(a) kids', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO specializations (name, created_at, updated_at) VALUES ('Manicure', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO specializations (name, created_at, updated_at) VALUES ('Massagista', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 3. PROFISSIONAIS
INSERT INTO professionals (name, email, password, is_active, is_working, created_at, updated_at) VALUES ('Alan', 'alan@beautycore.com', '$2a$10$Y50UaEVPu8fzJr8n3hRzHuHnB8hXyGVX9D.W8Xy8Q8y8Q8y8Q8y8Q', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO professionals (name, email, password, is_active, is_working, created_at, updated_at) VALUES ('Daniel', 'daniel@beautycore.com', '$2a$10$Y50UaEVPu8fzJr8n3hRzHuHnB8hXyGVX9D.W8Xy8Q8y8Q8y8Q8y8Q', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO professionals (name, email, password, is_active, is_working, created_at, updated_at) VALUES ('Júnior', 'junior@beautycore.com', '$2a$10$Y50UaEVPu8fzJr8n3hRzHuHnB8hXyGVX9D.W8Xy8Q8y8Q8y8Q8y8Q', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 4. VÍNCULOS DE ACESSO E ESPECIALIDADE
INSERT INTO professional_role (professional_id, role_id) VALUES (1, 1);
INSERT INTO professional_role (professional_id, role_id) VALUES (2, 2);
INSERT INTO professional_role (professional_id, role_id) VALUES (3, 2);

INSERT INTO professional_specialty (professional_id, specialty_id) VALUES (1, 1);
INSERT INTO professional_specialty (professional_id, specialty_id) VALUES (1, 2);
INSERT INTO professional_specialty (professional_id, specialty_id) VALUES (2, 2);
INSERT INTO professional_specialty (professional_id, specialty_id) VALUES (3, 1);
INSERT INTO professional_specialty (professional_id, specialty_id) VALUES (3, 2);

-- 5. SERVIÇOS
INSERT INTO services (name, base_price, created_at, updated_at) VALUES ('Corte de Cabelo Masculino', 25.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO services (name, base_price, created_at, updated_at) VALUES ('Barba', 15.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO services (name, base_price, created_at, updated_at) VALUES ('Sobrancelha', 10.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO services (name, base_price, created_at, updated_at) VALUES ('Hidratação', 50.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO services (name, base_price, created_at, updated_at) VALUES ('Corte feminino', 35.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 6. CLIENTES
INSERT INTO clients (name, phone, credit, in_appointment, created_at, updated_at) VALUES ('Cauê Rodrigues', '71988887777', 0.00, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO clients (name, phone, credit, in_appointment, created_at, updated_at) VALUES ('Lucas Oliveira', '71999881122', 0.00, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 7. APPOINTMENTS (Inserção manual do ID para garantir integridade referencial)
INSERT INTO appointments (id, professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid, created_at, finished_at) VALUES (1, 1, 1, 'FINISHED', 0.00, 35.00, 0.00, true, '2026-05-04 10:00:00', '2026-05-04 10:45:00');
INSERT INTO appointments (id, professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid, created_at, finished_at) VALUES (2, 2, 2, 'FINISHED', 0.00, 50.00, 0.00, true, '2026-05-04 11:00:00', '2026-05-04 12:00:00');
INSERT INTO appointments (id, professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid, created_at, finished_at) VALUES (3, 3, 2, 'FINISHED', 0.00, 50.00, 0.00, true, '2026-05-04 11:00:00', '2026-05-04 12:00:00');

-- 8. APPOINTMENT_SERVICE
INSERT INTO appointment_service (appointment_id, service_id, price_at_moment) VALUES (1, 1, 35.00);
INSERT INTO appointment_service (appointment_id, service_id, price_at_moment) VALUES (1, 2, 35.00);
INSERT INTO appointment_service (appointment_id, service_id, price_at_moment) VALUES (3, 2, 35.00);

-- 9. PAYMENTS
INSERT INTO payments (appointment_id, payment_method, amount_paid, paid_at) VALUES (1, 'PIX', 35.00, '2026-05-11 10:45:00');
INSERT INTO payments (appointment_id, payment_method, amount_paid, paid_at) VALUES (2, 'CASH', 35.00, '2026-05-11 12:00:00');