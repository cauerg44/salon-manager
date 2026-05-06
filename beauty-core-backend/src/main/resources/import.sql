-- 1. ESPECIALIZAÇÕES
INSERT INTO specializations (id, name, created_at, updated_at) VALUES (nextval('sq_specializations'), 'Cabeleleiro(a) tradicional', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO specializations (id, name, created_at, updated_at) VALUES (nextval('sq_specializations'), 'Cabeleleiro(a) kids', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO specializations (id, name, created_at, updated_at) VALUES (nextval('sq_specializations'), 'Manicure', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO specializations (id, name, created_at, updated_at) VALUES (nextval('sq_specializations'), 'Massagista', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 2. PROFISSIONAIS
INSERT INTO professionals (id, name, is_active, is_working, created_at, updated_at) VALUES (nextval('sq_professionals'), 'Alan', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO professionals (id, name, is_active, is_working, created_at, updated_at) VALUES (nextval('sq_professionals'), 'Daniel', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO professionals (id, name, is_active, is_working, created_at, updated_at) VALUES (nextval('sq_professionals'), 'Júnior', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO professional_specialty (professional_id, specialty_id) VALUES (1, 1);
INSERT INTO professional_specialty (professional_id, specialty_id) VALUES (1, 2);
INSERT INTO professional_specialty (professional_id, specialty_id) VALUES (2, 2);
INSERT INTO professional_specialty (professional_id, specialty_id) VALUES (3, 1);
INSERT INTO professional_specialty (professional_id, specialty_id) VALUES (3, 2);

-- 3. SERVIÇOS (JobItems)
INSERT INTO services (id, name, base_price, created_at, updated_at) VALUES (nextval('sq_services'), 'Corte de Cabelo Masculino', 25.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO services (id, name, base_price, created_at, updated_at) VALUES (nextval('sq_services'), 'Barba', 15.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO services (id, name, base_price, created_at, updated_at) VALUES (nextval('sq_services'), 'Sobrancelha', 10.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO services (id, name, base_price, created_at, updated_at) VALUES (nextval('sq_services'), 'Hidratação', 50.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO services (id, name, base_price, created_at, updated_at) VALUES (nextval('sq_services'), 'Corte feminino', 35.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 4. CLIENTES
INSERT INTO clients (id, name, phone, credit, created_at, updated_at) VALUES (nextval('sq_clients'), 'Cauê Rodrigues', '71988887777', 0.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO clients (id, name, phone, credit, created_at, updated_at) VALUES (nextval('sq_clients'), 'Lucas Oliveira', '71999881122', 0.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 5. APPOINTMENTS
INSERT INTO appointments (id, professional_id, client_id, appointment_status, created_at, finished_at) VALUES (nextval('sq_appointments'), 1, 1, 'FINISHED', '2026-05-04 10:00:00', '2026-05-04 10:45:00');
INSERT INTO appointments (id, professional_id, client_id, appointment_status, created_at, finished_at) VALUES (nextval('sq_appointments'), 1, 2, 'FINISHED', '2026-05-04 11:00:00', '2026-05-04 12:00:00');

INSERT INTO appointment_service (appointment_id, service_id, price_at_moment, discount) VALUES (1, 1, 25, 5);
INSERT INTO appointment_service (appointment_id, service_id, price_at_moment, discount) VALUES (1, 2, 15.00, 5);

-- 8. PAYMENTS
INSERT INTO payments (id, appointment_id, payment_method, amount_paid, paid_at) VALUES (nextval('sq_payments'), 1, 'PIX', 60.00, '2026-05-04 10:45:00');
INSERT INTO payments (id, appointment_id, payment_method, amount_paid, paid_at) VALUES (nextval('sq_payments'), 2, 'CASH', 50.00, '2026-05-04 12:00:00');