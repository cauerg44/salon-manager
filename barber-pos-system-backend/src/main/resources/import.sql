INSERT INTO specializations (name) VALUES ('BARBER');
INSERT INTO specializations (name) VALUES ('MANICURE');
INSERT INTO specializations (name) VALUES ('BRAIDER');

INSERT INTO professionals (name, is_active, created_at, updated_at) VALUES ('Júnior', true, '2026-01-10 08:00:00', '2026-01-10 08:00:00');
INSERT INTO professionals (name, is_active, created_at, updated_at) VALUES ('Daniel', true, '2026-02-15 09:30:00', '2026-02-15 09:30:00');
INSERT INTO professionals (name, is_active, created_at, updated_at) VALUES ('Allan', true, '2026-03-20 14:00:00', '2026-03-20 14:00:00');
INSERT INTO professionals (name, is_active, created_at, updated_at) VALUES ('Felipe', false, '2026-01-05 10:00:00', '2026-01-05 10:00:00');

INSERT INTO professional_specialty (professional_id, specialty_id) VALUES (1, 1);
INSERT INTO professional_specialty (professional_id, specialty_id) VALUES (2, 1);
INSERT INTO professional_specialty (professional_id, specialty_id) VALUES (3, 1);
INSERT INTO professional_specialty (professional_id, specialty_id) VALUES (4, 1);

INSERT INTO professionals (name, is_active, created_at, updated_at) VALUES ('Júnior', true, '2026-01-10 08:00:00', '2026-01-10 08:00:00');
INSERT INTO professionals (name, is_active, created_at, updated_at) VALUES ('Daniel', true, '2026-02-15 09:30:00', '2026-02-15 09:30:00');
INSERT INTO professionals (name, is_active, created_at, updated_at) VALUES ('Allan', true, '2026-03-20 14:00:00', '2026-03-20 14:00:00');
INSERT INTO professionals (name, is_active, created_at, updated_at) VALUES ('Felipe', false, '2026-01-05 10:00:00', '2026-01-05 10:00:00');

INSERT INTO services (name, base_price, created_at, updated_at) VALUES ('Corte Degradê', 45.00, '2026-01-01 08:00:00', '2026-01-01 08:00:00');
INSERT INTO services (name, base_price, created_at, updated_at) VALUES ('Barba Completa', 30.00, '2026-01-01 08:05:00', '2026-01-01 08:05:00');
INSERT INTO services (name, base_price, created_at, updated_at) VALUES ('Pezinho e Sobrancelha', 20.00, '2026-01-02 09:00:00', '2026-01-02 09:00:00');
INSERT INTO services (name, base_price, created_at, updated_at) VALUES ('Pé e Mão', 50.00, '2026-01-10 10:00:00', '2026-01-10 10:00:00');
INSERT INTO services (name, base_price, created_at, updated_at) VALUES ('Esmaltação em Gel', 80.00, '2026-01-10 10:30:00', '2026-01-10 10:30:00');
INSERT INTO services (name, base_price, created_at, updated_at) VALUES ('Trança Nagô', 120.00, '2026-01-15 14:00:00', '2026-01-15 14:00:00');
INSERT INTO services (name, base_price, created_at, updated_at) VALUES ('Box Braids (Média)', 250.00, '2026-01-15 14:10:00', '2026-01-15 14:10:00');

INSERT INTO clients (name, phone, birth_date, created_at, updated_at) VALUES ('Felipe Santos', '71991223344', '1995-05-15', '2026-01-02 10:30:00', '2026-01-02 10:30:00');
INSERT INTO clients (name, phone, birth_date, created_at, updated_at) VALUES ('Ricardo Oliveira', '75988776655', '1988-10-20', '2026-01-05 14:20:00', '2026-01-05 14:20:00');
INSERT INTO clients (name, phone, birth_date, created_at, updated_at) VALUES ('Gabriel Souza', '71987654321', '2002-01-30', '2026-01-10 09:00:00', '2026-01-10 09:00:00');
INSERT INTO clients (name, phone, birth_date, created_at, updated_at) VALUES ('Mateus Rocha', '71999887766', '1992-03-12', '2026-01-12 16:45:00', '2026-01-12 16:45:00');
INSERT INTO clients (name, phone, birth_date, created_at, updated_at) VALUES ('André Lima', '75992334455', '1997-07-25', '2026-01-15 11:15:00', '2026-01-15 11:15:00');
INSERT INTO clients (name, phone, birth_date, created_at, updated_at) VALUES ('Lucas Carvalho', '71996112233', '1990-12-05', '2026-01-20 18:30:00', '2026-01-20 18:30:00');
INSERT INTO clients (name, phone, birth_date, created_at, updated_at) VALUES ('Bruno Mendes', '71981445566', '1985-08-18', '2026-01-25 10:00:00', '2026-01-25 10:00:00');
INSERT INTO clients (name, phone, birth_date, created_at, updated_at) VALUES ('Thiago Pereira', '75987112233', '2000-02-22', '2026-02-01 08:45:00', '2026-02-01 08:45:00');
INSERT INTO clients (name, phone, birth_date, created_at, updated_at) VALUES ('Diego Fernandes', '71995009988', '1993-11-08', '2026-02-05 13:10:00', '2026-02-05 13:10:00');
INSERT INTO clients (name, phone, birth_date, created_at, updated_at) VALUES ('Rafael Costa', '71994332211', '1999-06-14', '2026-02-10 17:00:00', '2026-02-10 17:00:00');
INSERT INTO clients (name, phone, birth_date, created_at, updated_at) VALUES ('Marcos Vinícius', '71992334455', '1994-02-10', '2026-02-15 09:20:00', '2026-02-15 09:20:00');
INSERT INTO clients (name, phone, birth_date, created_at, updated_at) VALUES ('Rodrigo Melo', '75981223344', '1987-11-25', '2026-02-20 15:50:00', '2026-02-20 15:50:00');
INSERT INTO clients (name, phone, birth_date, created_at, updated_at) VALUES ('Caio Silveira', '71988445566', '2001-04-12', '2026-02-28 11:30:00', '2026-02-28 11:30:00');
INSERT INTO clients (name, phone, birth_date, created_at, updated_at) VALUES ('Alexandre Pires', '71995667788', '1993-09-08', '2026-03-05 10:15:00', '2026-03-05 10:15:00');
INSERT INTO clients (name, phone, birth_date, created_at, updated_at) VALUES ('Sandro Holanda', '75991112233', '1982-06-30', '2026-03-10 14:40:00', '2026-03-10 14:40:00');
INSERT INTO clients (name, phone, birth_date, created_at, updated_at) VALUES ('Fabrício Dutra', '71987334455', '1996-01-20', '2026-03-15 12:00:00', '2026-03-15 12:00:00');
INSERT INTO clients (name, phone, birth_date, created_at, updated_at) VALUES ('Leandro Machado', '71999228877', '1989-08-15', '2026-03-20 16:25:00', '2026-03-20 16:25:00');
INSERT INTO clients (name, phone, birth_date, created_at, updated_at) VALUES ('Ítalo Ferreira', '75982443311', '2003-05-05', '2026-03-22 09:10:00', '2026-03-22 09:10:00');
INSERT INTO clients (name, phone, birth_date, created_at, updated_at) VALUES ('Samuel Bastos', '71991002299', '1991-12-19', '2026-03-25 11:55:00', '2026-03-25 11:55:00');
INSERT INTO clients (name, phone, birth_date, created_at, updated_at) VALUES ('Vitor Guedes', '71981556644', '1998-03-22', '2026-04-01 08:30:00', '2026-04-01 08:30:00');
INSERT INTO clients (name, phone, birth_date, created_at, updated_at) VALUES ('Hugo Almeida', '75988119900', '1986-07-14', '2026-04-05 15:40:00', '2026-04-05 15:40:00');
INSERT INTO clients (name, phone, birth_date, created_at, updated_at) VALUES ('Davi Cavalcante', '71994551122', '2004-10-02', '2026-04-10 10:20:00', '2026-04-10 10:20:00');

INSERT INTO appointments (created_at, finished_at, gross_amount, professional_id, client_id, appointment_status) VALUES ('2026-04-28 09:00:00', '2026-04-28 09:40:00', 40.00, 1, 1, 'FINISHED');
INSERT INTO appointments (created_at, finished_at, gross_amount, professional_id, client_id, appointment_status) VALUES ('2026-04-28 09:30:00', '2026-04-28 10:20:00', 55.00, 2, 2, 'FINISHED');
INSERT INTO appointments (created_at, finished_at, gross_amount, professional_id, client_id, appointment_status) VALUES ('2026-04-28 10:15:00', NULL, 25.00, 3, 3, 'IN_PROGRESS');
INSERT INTO appointments (created_at, finished_at, gross_amount, professional_id, client_id, appointment_status) VALUES ('2026-04-28 10:45:00', NULL, 0.00, 1, 4, 'WAITING');
INSERT INTO appointments (created_at, finished_at, gross_amount, professional_id, client_id, appointment_status) VALUES ('2026-04-30 14:00:00', '2026-04-30 14:45:00', 45.00, 1, 1, 'FINISHED');
INSERT INTO appointments (created_at, finished_at, gross_amount, professional_id, client_id, appointment_status) VALUES ('2026-04-30 15:00:00', '2026-04-30 16:00:00', 120.00, 2, 2, 'FINISHED');

INSERT INTO appointment_service (appointment_id, service_id) VALUES (1, 2);
INSERT INTO appointment_service (appointment_id, service_id) VALUES (2, 2);
INSERT INTO appointment_service (appointment_id, service_id) VALUES (2, 3);
INSERT INTO appointment_service (appointment_id, service_id) VALUES (3, 1);
INSERT INTO appointment_service (appointment_id, service_id) VALUES (5, 1);
INSERT INTO appointment_service (appointment_id, service_id) VALUES (6, 6);

INSERT INTO payments (appointment_id, payment_method, discount, amount_paid, debt_amount, is_paid, finished_at) VALUES (1, 'PIX', 0.00, 40.00, 0.00, true, '2026-04-28 09:40:00');
INSERT INTO payments (appointment_id, payment_method, discount, amount_paid, debt_amount, is_paid, finished_at) VALUES (2, 'CASH', 5.00, 50.00, 0.00, true, '2026-04-28 10:20:00');
INSERT INTO payments (appointment_id, payment_method, discount, amount_paid, debt_amount, is_paid, finished_at) VALUES (5, 'CASH', 0.00, 20.00, 25.00, false, '2026-04-30 14:45:00');
INSERT INTO payments (appointment_id, payment_method, discount, amount_paid, debt_amount, is_paid, finished_at) VALUES (6, 'DEBIT', 0.00, 0.00, 120.00, false, '2026-04-30 16:00:00');

INSERT INTO debts (remaining_value, total_value, client_id, created_at, payment_id, updated_at, debt_status) VALUES (25.00, 25.00, 1, '2026-04-30 14:45:00', 5, '2026-04-30 14:45:00', 'PENDING');
INSERT INTO debts (remaining_value, total_value, client_id, created_at, payment_id, updated_at, debt_status) VALUES (120.00, 120.00, 2, '2026-04-30 16:00:00', 6, '2026-04-30 16:00:00', 'PENDING');