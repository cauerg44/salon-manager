-- 1. Desativa temporariamente as travas de chave estrangeira
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

-- 2. Reativa as travas de segurança
SET FOREIGN_KEY_CHECKS = 1;

insert into specializations (name) VALUES ('Barbeiro');
insert into specializations (name) VALUES ('Trancista');
insert into specializations (name) VALUES ('Esteticista');

insert into professionals (name, email, password) VALUES ('Iasmin Dantas', 'iasmindantas@beauty.com', '$2a$10$k8fKUlWIUnrB0GEUgTYuzu3QXcyH9dTRZKDIbzbfj0iUKquuKTupi');
insert into professionals (name, email, password) VALUES ('Júnior', 'junior@beauty.com', '$2a$10$k8fKUlWIUnrB0GEUgTYuzu3QXcyH9dTRZKDIbzbfj0iUKquuKTupi');
insert into professionals (name, email, password) VALUES ('Alan', 'alan@beauty.com', '$2a$10$k8fKUlWIUnrB0GEUgTYuzu3QXcyH9dTRZKDIbzbfj0iUKquuKTupi');
insert into professionals (name, email, password) VALUES ('Daniel', 'daniel@beauty.com', '$2a$10$k8fKUlWIUnrB0GEUgTYuzu3QXcyH9dTRZKDIbzbfj0iUKquuKTupi');
insert into professionals (name, email, password) VALUES ('Thayla', 'thayla@beauty.com', '$2a$10$k8fKUlWIUnrB0GEUgTYuzu3QXcyH9dTRZKDIbzbfj0iUKquuKTupi');

insert into professional_specialty (professional_id, specialty_id) VALUES (1, 3);
insert into professional_specialty (professional_id, specialty_id) VALUES (2, 1);
insert into professional_specialty (professional_id, specialty_id) VALUES (3, 1);
insert into professional_specialty (professional_id, specialty_id) VALUES (4, 1);
insert into professional_specialty (professional_id, specialty_id) VALUES (5, 2);

insert into roles (authority) values ('ROLE_ADMIN');
insert into roles (authority) values ('ROLE_PROFESSIONAL');

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

-- Atendimento 1: Cauê foi atendido com Júnior finalizado:
insert into appointments (professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid)
values (2, 1, 'FINISHED', 0.00, 40.00, 0.00, true);

-- Atendimento 1: Cauê escolheu os serviços: Cabelo + Barba -> Total: R$40.00
insert into appointment_service (appointment_id, service_id, price_at_moment) values (1, 1, 25.00);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (1, 2, 15.00);

-- Atendimento 1: Cauê pagou o atendimento completo pelo PIX
insert into payments (appointment_id, amount_paid, payment_method, paid_at) values (1, 40.00, 'PIX', '2026-05-14 18:00');

-- Atendimento 2: Felipe foi atendido com Alan finalizado:
insert into appointments (professional_id, client_id, appointment_status, discount, total_value, remaining_value, is_paid)
values (3, 2, 'FINISHED', 0.00, 50.00, 10.00, false);

-- Atendimento 2: Felipe escolheu os serviços: Cabelo + Barba + Sobrancelha -> Total: R$50.00
insert into appointment_service (appointment_id, service_id, price_at_moment) values (2, 1, 25.00);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (2, 2, 15.00);
insert into appointment_service (appointment_id, service_id, price_at_moment) values (2, 3, 10.00);

-- Atendimento 2: Felipe pagou o atendimento parcialmente pelo Dinheiro
insert into payments (appointment_id, amount_paid, payment_method, paid_at) values (2, 40.00, 'CASH', '2026-05-14 18:00');