-- Desativa temporariamente as travas de chave estrangeira
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

-- Reativa as travas de segurança
SET FOREIGN_KEY_CHECKS = 1;

insert into specializations (name) VALUES ('Barbeiro');
insert into specializations (name) VALUES ('Trancista');
insert into specializations (name) VALUES ('Esteticista');

insert into professionals (name, email, password) VALUES ('Iasmin Dantas', 'iasmindantas@beauty.com', '$2a$10$k8fKUlWIUnrB0GEUgTYuzu3QXcyH9dTRZKDIbzbfj0iUKquuKTupi');
insert into professionals (name, email, password) VALUES ('Júnior', 'junior@beauty.com', '$2a$10$k8fKUlWIUnrB0GEUgTYuzu3QXcyH9dTRZKDIbzbfj0iUKquuKTupi');
insert into professionals (name, email, password) VALUES ('Alan', 'alan@beauty.com', '$2a$10$k8fKUlWIUnrB0GEUgTYuzu3QXcyH9dTRZKDIbzbfj0iUKquuKTupi');
insert into professionals (name, email, password) VALUES ('Daniel', 'daniel@beauty.com', '$2a$10$k8fKUlWIUnrB0GEUgTYuzu3QXcyH9dTRZKDIbzbfj0iUKquuKTupi');
insert into professionals (name, email, password) VALUES ('Thalyta', 'thayla@beauty.com', '$2a$10$k8fKUlWIUnrB0GEUgTYuzu3QXcyH9dTRZKDIbzbfj0iUKquuKTupi');
insert into professionals (name, email, password) VALUES ('David', 'david@beauty.com', '$2a$10$k8fKUlWIUnrB0GEUgTYuzu3QXcyH9dTRZKDIbzbfj0iUKquuKTupi');

insert into professional_specialty (professional_id, specialty_id) VALUES (1, 3);
insert into professional_specialty (professional_id, specialty_id) VALUES (2, 1);
insert into professional_specialty (professional_id, specialty_id) VALUES (2, 2);
insert into professional_specialty (professional_id, specialty_id) VALUES (3, 1);
insert into professional_specialty (professional_id, specialty_id) VALUES (4, 1);
insert into professional_specialty (professional_id, specialty_id) VALUES (5, 2);
insert into professional_specialty (professional_id, specialty_id) VALUES (6, 1);

insert into roles (authority) values ('ROLE_ADMIN');
insert into roles (authority) values ('ROLE_PROFESSIONAL');
insert into roles (authority) values ('ROLE_RECEPTIONIST');

-- Associando David como recepionista
insert into professional_role (professional_id, role_id) VALUES (6, 3);

-- Júnior é o dono do salão de beleza e barbeiro
insert into professional_role (professional_id, role_id) VALUES (2, 1);
insert into professional_role (professional_id, role_id) VALUES (2, 2);

-- O resto são apenas profissionais
insert into professional_role (professional_id, role_id) VALUES (1, 2);
insert into professional_role (professional_id, role_id) VALUES (3, 2);
insert into professional_role (professional_id, role_id) VALUES (4, 2);
insert into professional_role (professional_id, role_id) VALUES (5, 2);

insert into services (name, base_price) values ('Cabelo masculino', 25.00);
insert into services (name, base_price) values ('Barba', 15.00);
insert into services (name, base_price) values ('Sobrancelha', 10.00);
insert into services (name, base_price) values ('Hidratação', 50.00);
insert into services (name, base_price) values ('Corte feminino', 40.00);
insert into services (name, base_price) values ('Escova', 45.00);

insert into clients (name, phone, birth_date, credit) values ('Cauê', '1140028922', '2004-01-01', 00.00);
insert into clients (name, phone, birth_date, credit) values ('Herivelto', '1140028923', '1976-01-01', 00.00);
insert into clients (name, phone, birth_date, credit) values ('Enzo', '1140028924', '2003-01-01', 00.00);
insert into clients (name, phone, birth_date, credit) values ('Felipe', '1140028925', '2004-01-01', 00.00);

insert into clients (name, phone, birth_date, credit) values ('Marcos', '1140028926', '1995-02-11', 00.00);
insert into clients (name, phone, birth_date, credit) values ('João', '1140028927', '1998-03-15', 00.00);
insert into clients (name, phone, birth_date, credit) values ('Pedro', '1140028928', '2001-07-20', 00.00);
insert into clients (name, phone, birth_date, credit) values ('Lucas', '1140028929', '1999-05-09', 00.00);
insert into clients (name, phone, birth_date, credit) values ('Matheus', '1140028930', '2000-12-01', 00.00);
insert into clients (name, phone, birth_date, credit) values ('Gabriel', '1140028931', '1997-08-22', 00.00);
insert into clients (name, phone, birth_date, credit) values ('Rafael', '1140028932', '1996-09-18', 00.00);
insert into clients (name, phone, birth_date, credit) values ('Bruno', '1140028933', '2002-04-30', 00.00);
insert into clients (name, phone, birth_date, credit) values ('Thiago', '1140028934', '1994-11-13', 00.00);
insert into clients (name, phone, birth_date, credit) values ('André', '1140028935', '1993-06-25', 00.00);

insert into clients (name, phone, birth_date, credit) values ('Carlos', '1140028936', '1989-01-10', 00.00);
insert into clients (name, phone, birth_date, credit) values ('Ricardo', '1140028937', '1991-02-14', 00.00);
insert into clients (name, phone, birth_date, credit) values ('Daniel', '1140028938', '1988-10-05', 00.00);
insert into clients (name, phone, birth_date, credit) values ('Fernando', '1140028939', '1990-03-28', 00.00);
insert into clients (name, phone, birth_date, credit) values ('Vinicius', '1140028940', '2005-07-17', 00.00);
insert into clients (name, phone, birth_date, credit) values ('Eduardo', '1140028941', '1992-09-21', 00.00);
insert into clients (name, phone, birth_date, credit) values ('Gustavo', '1140028942', '2001-12-19', 00.00);
insert into clients (name, phone, birth_date, credit) values ('Leandro', '1140028943', '1987-04-12', 00.00);
insert into clients (name, phone, birth_date, credit) values ('Renato', '1140028944', '1996-08-08', 00.00);
insert into clients (name, phone, birth_date, credit) values ('Caio', '1140028945', '2000-06-06', 00.00);

insert into clients (name, phone, birth_date, credit) values ('Paulo', '1140028946', '1999-01-29', 00.00);
insert into clients (name, phone, birth_date, credit) values ('Samuel', '1140028947', '2003-11-11', 00.00);
insert into clients (name, phone, birth_date, credit) values ('Leonardo', '1140028948', '1995-05-24', 00.00);
insert into clients (name, phone, birth_date, credit) values ('Murilo', '1140028949', '2002-02-02', 00.00);
insert into clients (name, phone, birth_date, credit) values ('Nathan', '1140028950', '2004-10-10', 00.00);
insert into clients (name, phone, birth_date, credit) values ('Igor', '1140028951', '1997-07-07', 00.00);
insert into clients (name, phone, birth_date, credit) values ('Fábio', '1140028952', '1985-03-03', 00.00);
insert into clients (name, phone, birth_date, credit) values ('Wesley', '1140028953', '1998-12-27', 00.00);
insert into clients (name, phone, birth_date, credit) values ('Diego', '1140028954', '1991-09-09', 00.00);
insert into clients (name, phone, birth_date, credit) values ('Roberto', '1140028955', '1986-06-16', 00.00);

-- ====================================================================
-- 1. ATENDIMENTOS EM ESPERA (IDs de 1 a 5)
-- ====================================================================

insert into appointments (professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid) values (1, 6, 'WAITING', 0.00, 40.00, 0.00, true);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (1, 1, 25.00);

insert into appointments (professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid) values (1, 7, 'WAITING', 0.00, 40.00, 0.00, true);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (2, 1, 25.00);

insert into appointments (professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid) values (1, 8, 'WAITING', 0.00, 40.00, 0.00, true);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (3, 1, 25.00);

insert into appointments (professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid) values (1, 9, 'WAITING', 0.00, 40.00, 0.00, true);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (4, 1, 25.00);

insert into appointments (professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid) values (1, 10, 'WAITING', 0.00, 40.00, 0.00, true);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (5, 1, 25.00);


-- ====================================================================
-- 2. ATENDIMENTOS EM PROGRESSO (IDs de 6 a 10)
-- ====================================================================

insert into appointments (professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid) values (3, 11, 'IN_PROGRESS', 0.00, 15.00, 0.00, true);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (6, 2, 15.00);

insert into appointments (professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid) values (3, 12, 'IN_PROGRESS', 0.00, 15.00, 0.00, true);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (7, 2, 15.00);

insert into appointments (professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid) values (3, 13, 'IN_PROGRESS', 0.00, 15.00, 0.00, true);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (8, 2, 15.00);

insert into appointments (professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid) values (3, 14, 'IN_PROGRESS', 0.00, 15.00, 0.00, true);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (9, 2, 15.00);

insert into appointments (professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid) values (3, 15, 'IN_PROGRESS', 0.00, 40.00, 0.00, true);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (10, 2, 25.00);


-- ====================================================================
-- 3. ATENDIMENTOS FINALIZADOS (IDs 11 e 12)
-- ====================================================================

-- Atendimento 11: Cauê com Júnior
insert into appointments (professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid) values (2, 1, 'FINISHED', 0.00, 40.00, 0.00, true);
-- Serviços do Cauê: Cabelo + Barba
insert into appointment_service (appointment_id, service_id, price_at_moment) values (11, 1, 25.00);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (11, 2, 15.00);
-- Pagamento do Cauê
insert into payments (appointment_id, amount_paid, payment_method, paid_at) values (11, 40.00, 'PIX', '2026-05-14 18:00');

-- Atendimento 12: Felipe com Alan
insert into appointments (professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid) values (3, 2, 'FINISHED', 0.00, 50.00, 10.00, false);
-- Serviços do Felipe: Cabelo + Barba + Sobrancelha
insert into appointment_service (appointment_id, service_id, price_at_moment) values (12, 1, 25.00);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (12, 2, 15.00);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (12, 3, 10.00);


-- ====================================================================
-- 4. ATENDIMENTOS CANCELADOS (IDs de 13 a 16)
-- ====================================================================

insert into appointments (professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid) values (2, 17, 'CANCELED', 0.00, 25.00, 10.00, false);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (13, 1, 25.00);

insert into appointments (professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid) values (2, 18, 'CANCELED', 0.00, 25.00, 10.00, false);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (14, 1, 25.00);

insert into appointments (professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid) values (2, 19, 'CANCELED', 0.00, 25.00, 10.00, false);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (15, 1, 25.00);

insert into appointments (professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid) values (2, 20, 'CANCELED', 0.00, 25.00, 10.00, false);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (16, 1, 25.00);

-- Atendimento 2: Felipe pagou o atendimento parcialmente pelo Dinheiro
insert into payments (appointment_id, amount_paid, payment_method, paid_at) values (12, 40.00, 'CASH', '2026-05-14 18:00');