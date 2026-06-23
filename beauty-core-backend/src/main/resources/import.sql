SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE payments;
TRUNCATE TABLE appointment_service;
TRUNCATE TABLE appointments;
TRUNCATE TABLE professional_role;
TRUNCATE TABLE professional_specialty;
TRUNCATE TABLE professionals;
TRUNCATE TABLE specializations;
TRUNCATE TABLE roles;
TRUNCATE TABLE clients;
TRUNCATE TABLE services;

SET FOREIGN_KEY_CHECKS = 1;

-- =========================
-- SPECIALIZATIONS
-- =========================
insert into specializations (name) values ('Barbeiro');
insert into specializations (name) values ('Trancista');
insert into specializations (name) values ('Esteticista');

-- =========================
-- PROFESSIONALS
-- =========================

insert into professionals (name, email, password) values ('Júnior', 'junior@beauty.com', '$2a$10$k8fKUlWIUnrB0GEUgTYuzu3QXcyH9dTRZKDIbzbfj0iUKquuKTupi');
insert into professionals (name, email, password) values ('Alan', 'alan@beauty.com', '$2a$10$k8fKUlWIUnrB0GEUgTYuzu3QXcyH9dTRZKDIbzbfj0iUKquuKTupi');
insert into professionals (name, email, password) values ('Daniel', 'daniel@beauty.com', '$2a$10$k8fKUlWIUnrB0GEUgTYuzu3QXcyH9dTRZKDIbzbfj0iUKquuKTupi');
insert into professionals (name, email, password) values ('Taila', 'taila@beauty.com', '$2a$10$k8fKUlWIUnrB0GEUgTYuzu3QXcyH9dTRZKDIbzbfj0iUKquuKTupi');

-- =========================
-- PROFESSIONAL_SPECIALTY
-- =========================
insert into professional_specialty (professional_id, specialty_id) values (1, 1);
insert into professional_specialty (professional_id, specialty_id) values (2, 1);
insert into professional_specialty (professional_id, specialty_id) values (3, 1);
insert into professional_specialty (professional_id, specialty_id) values (4, 2);

-- =========================
-- ROLES
-- =========================
insert into roles (authority) values ('ROLE_ADMIN');
insert into roles (authority) values ('ROLE_PROFESSIONAL');
insert into roles (authority) values ('ROLE_RECEPTIONIST');

-- =========================
-- PROFESSIONAL_ROLE
-- =========================
insert into professional_role (professional_id, role_id) values (1, 1);
insert into professional_role (professional_id, role_id) values (1, 2);
insert into professional_role (professional_id, role_id) values (2, 2);
insert into professional_role (professional_id, role_id) values (3, 2);
insert into professional_role (professional_id, role_id) values (4, 2);

-- =========================
-- SERVICES
-- =========================
insert into services (name, base_price) values ('Cabelo masculino', 25.00);
insert into services (name, base_price) values ('Barba', 15.00);
insert into services (name, base_price) values ('Sobrancelha', 10.00);
insert into services (name, base_price) values ('Hidratação', 50.00);
insert into services (name, base_price) values ('Corte feminino', 40.00);
insert into services (name, base_price) values ('Escova', 45.00);
insert into services (name, base_price) values ('Tranças Nagô', 55.00);

-- =========================
-- CLIENTS
-- =========================
insert into clients (name, phone, birth_date, credit) values ('Cauê', '1140028922', '2004-01-01', 0.00);
insert into clients (name, phone, birth_date, credit) values ('Herivelto', '1140028923', '1976-01-01', 0.00);
insert into clients (name, phone, birth_date, credit) values ('Enzo', '1140028924', '2003-01-01', 0.00);
insert into clients (name, phone, birth_date, credit) values ('Felipe', '1140028925', '2004-01-01', 0.00);

insert into clients (name, phone, birth_date, credit) values ('Marcos', '1140028926', '1995-02-11', 0.00);
insert into clients (name, phone, birth_date, credit) values ('João', '1140028927', '1998-03-15', 0.00);
insert into clients (name, phone, birth_date, credit) values ('Pedro', '1140028928', '2001-07-20', 0.00);
insert into clients (name, phone, birth_date, credit) values ('Lucas', '1140028929', '1999-05-09', 0.00);
insert into clients (name, phone, birth_date, credit) values ('Matheus', '1140028930', '2000-12-01', 0.00);
insert into clients (name, phone, birth_date, credit) values ('Gabriel', '1140028931', '1997-08-22', 0.00);
insert into clients (name, phone, birth_date, credit) values ('Rafael', '1140028932', '1996-09-18', 0.00);
insert into clients (name, phone, birth_date, credit) values ('Bruno', '1140028933', '2002-04-30', 0.00);
insert into clients (name, phone, birth_date, credit) values ('Thiago', '1140028934', '1994-11-13', 0.00);
insert into clients (name, phone, birth_date, credit) values ('André', '1140028935', '1993-06-25', 0.00);

insert into clients (name, phone, birth_date, credit) values ('Carlos', '1140028936', '1989-01-10', 0.00);
insert into clients (name, phone, birth_date, credit) values ('Ricardo', '1140028937', '1991-02-14', 0.00);
insert into clients (name, phone, birth_date, credit) values ('Daniel', '1140028938', '1988-10-05', 0.00);
insert into clients (name, phone, birth_date, credit) values ('Fernando', '1140028939', '1990-03-28', 0.00);
insert into clients (name, phone, birth_date, credit) values ('Vinicius', '1140028940', '2005-07-17', 0.00);
insert into clients (name, phone, birth_date, credit) values ('Eduardo', '1140028941', '1992-09-21', 0.00);

-- =========================
-- APPOINTMENTS (01/06/2026 - 20/06/2026)
-- =========================

insert into appointments (professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid) values (1, 2, 'FINISHED', 0.00, 40.00, 0.00, true);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (1, 1, 25.00);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (1, 2, 15.00);
insert into payments (appointment_id, amount_paid, payment_method, paid_at) values (1, 40.00, 'PIX', '2026-06-01 10:15:00');

insert into appointments (professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid) values (2, 2, 'FINISHED', 0.00, 25.00, 0.00, true);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (2, 1, 25.00);
insert into payments (appointment_id, amount_paid, payment_method, paid_at) values (2, 25.00, 'CASH', '2026-06-02 12:45:00');

insert into appointments (professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid) values (4, 10, 'FINISHED', 0.00, 55.00, 0.00, true);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (3, 7, 55.00);
insert into payments (appointment_id, amount_paid, payment_method, paid_at) values (3, 55.00, 'DEBIT', '2026-06-03 16:32:17');

insert into appointments (professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid) values (4, 10, 'FINISHED', 0.00, 50.00, 0.00, true);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (4, 1, 25.00);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (4, 2, 15.00);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (4, 3, 10.00);
insert into payments (appointment_id, amount_paid, payment_method, paid_at) values (4, 50.00, 'CREDIT', '2026-06-08 16:32:17');

insert into appointments (professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid) values (1, 3, 'FINISHED', 0.00, 25.00, 0.00, true);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (5, 1, 25.00);
insert into payments (appointment_id, amount_paid, payment_method, paid_at) values (5, 25.00, 'PIX', '2026-06-10 09:10:00');

insert into appointments (professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid) values (2, 4, 'FINISHED', 0.00, 40.00, 0.00, true);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (6, 1, 25.00);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (6, 2, 15.00);
insert into payments (appointment_id, amount_paid, payment_method, paid_at) values (6, 40.00, 'CASH', '2026-06-11 11:25:00');

insert into appointments (professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid) values (3, 5, 'FINISHED', 0.00, 15.00, 0.00, true);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (7, 2, 15.00);
insert into payments (appointment_id, amount_paid, payment_method, paid_at) values (7, 15.00, 'PIX', '2026-06-12 14:40:00');

insert into appointments (professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid) values (4, 6, 'FINISHED', 0.00, 55.00, 0.00, true);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (8, 7, 55.00);
insert into payments (appointment_id, amount_paid, payment_method, paid_at) values (8, 55.00, 'DEBIT', '2026-06-13 16:00:00');

insert into appointments (professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid) values (1, 7, 'FINISHED', 0.00, 25.00, 0.00, true);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (9, 1, 25.00);
insert into payments (appointment_id, amount_paid, payment_method, paid_at) values (9, 25.00, 'PIX', '2026-06-14 10:30:00');

insert into appointments (professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid) values (2, 8, 'FINISHED', 0.00, 50.00, 0.00, true);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (10, 1, 25.00);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (10, 3, 10.00);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (10, 2, 15.00);
insert into payments (appointment_id, amount_paid, payment_method, paid_at) values (10, 50.00, 'CREDIT', '2026-06-15 17:45:00');

insert into appointments (professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid) values (3, 9, 'FINISHED', 0.00, 25.00, 0.00, true);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (11, 1, 25.00);
insert into payments (appointment_id, amount_paid, payment_method, paid_at) values (11, 25.00, 'CASH', '2026-06-16 09:20:00');

insert into appointments (professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid) values (4, 10, 'FINISHED', 0.00, 40.00, 0.00, true);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (12, 1, 25.00);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (12, 2, 15.00);
insert into payments (appointment_id, amount_paid, payment_method, paid_at) values (12, 40.00, 'PIX', '2026-06-17 13:10:00');

insert into appointments (professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid) values (1, 11, 'FINISHED', 0.00, 30.00, 0.00, true);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (13, 1, 25.00);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (13, 2, 15.00);
insert into payments (appointment_id, amount_paid, payment_method, paid_at) values (13, 50.00, 'PIX', '2026-06-18 08:50:00');

insert into appointments (professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid) values (2, 12, 'FINISHED', 0.00, 25.00, 0.00, true);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (14, 1, 25.00);
insert into payments (appointment_id, amount_paid, payment_method, paid_at) values (14, 25.00, 'CASH', '2026-06-19 15:05:00');

insert into appointments (professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid) values (3, 13, 'FINISHED', 0.00, 55.00, 0.00, true);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (15, 7, 55.00);
insert into payments (appointment_id, amount_paid, payment_method, paid_at) values (15, 55.00, 'DEBIT', '2026-06-20 18:25:00');

-- =========================
-- APPOINTMENTS (WAITING)
-- =========================
insert into appointments (professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid) values (1, 2, 'WAITING', 0.00, 25.00, 25.00, false);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (16, 1, 25.00);

insert into appointments (professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid) values (2, 1, 'WAITING', 0.00, 25.00, 25.00, false);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (17, 1, 25.00);

insert into appointments (professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid) values (3, 5, 'WAITING', 0.00, 25.00, 25.00, false);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (18, 1, 25.00);