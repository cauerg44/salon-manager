INSERT INTO tb_barber (name, is_active) VALUES ('Júnior', true);
INSERT INTO tb_barber (name, is_active) VALUES ('Daniel', true);
INSERT INTO tb_barber (name, is_active) VALUES ('Allan', true);

INSERT INTO tb_client (name, phone, birth_date) VALUES ('Felipe Santos', '71991223344', '1995-05-15');
INSERT INTO tb_client (name, phone, birth_date) VALUES ('Ricardo Oliveira', '75988776655', '1988-10-20');
INSERT INTO tb_client (name, phone, birth_date) VALUES ('Gabriel Souza', '71987654321', '2002-01-30');
INSERT INTO tb_client (name, phone, birth_date) VALUES ('Mateus Rocha', '71999887766', '1992-03-12');
INSERT INTO tb_client (name, phone, birth_date) VALUES ('André Lima', '75992334455', '1997-07-25');
INSERT INTO tb_client (name, phone, birth_date) VALUES ('Lucas Carvalho', '71996112233', '1990-12-05');
INSERT INTO tb_client (name, phone, birth_date) VALUES ('Bruno Mendes', '71981445566', '1985-08-18');
INSERT INTO tb_client (name, phone, birth_date) VALUES ('Thiago Pereira', '75987112233', '2000-02-22');
INSERT INTO tb_client (name, phone, birth_date) VALUES ('Diego Fernandes', '71995009988', '1993-11-08');
INSERT INTO tb_client (name, phone, birth_date) VALUES ('Rafael Costa', '71994332211', '1999-06-14');
INSERT INTO tb_client (name, phone, birth_date) VALUES ('Marcos Vinícius', '71992334455', '1994-02-10');
INSERT INTO tb_client (name, phone, birth_date) VALUES ('Rodrigo Melo', '75981223344', '1987-11-25');
INSERT INTO tb_client (name, phone, birth_date) VALUES ('Caio Silveira', '71988445566', '2001-04-12');
INSERT INTO tb_client (name, phone, birth_date) VALUES ('Alexandre Pires', '71995667788', '1993-09-08');
INSERT INTO tb_client (name, phone, birth_date) VALUES ('Sandro Holanda', '75991112233', '1982-06-30');
INSERT INTO tb_client (name, phone, birth_date) VALUES ('Fabrício Dutra', '71987334455', '1996-01-20');
INSERT INTO tb_client (name, phone, birth_date) VALUES ('Leandro Machado', '71999228877', '1989-08-15');
INSERT INTO tb_client (name, phone, birth_date) VALUES ('Ítalo Ferreira', '75982443311', '2003-05-05');
INSERT INTO tb_client (name, phone, birth_date) VALUES ('Samuel Bastos', '71991002299', '1991-12-19');
INSERT INTO tb_client (name, phone, birth_date) VALUES ('Vitor Guedes', '71981556644', '1998-03-22');
INSERT INTO tb_client (name, phone, birth_date) VALUES ('Hugo Almeida', '75988119900', '1986-07-14');
INSERT INTO tb_client (name, phone, birth_date) VALUES ('Davi Cavalcante', '71994551122', '2004-10-02');

INSERT INTO tb_procedure (name, base_price) VALUES ('Corte Social', 25.00);
INSERT INTO tb_procedure (name, base_price) VALUES ('Degradê / Fade', 40.00);
INSERT INTO tb_procedure (name, base_price) VALUES ('Barba', 15.00);
INSERT INTO tb_procedure (name, base_price) VALUES ('Sobrancelha', 10.00);
INSERT INTO tb_procedure (name, base_price) VALUES ('Pigmentação', 20.00);

INSERT INTO tb_attendance (moment, gross_amount, barber_id, client_id, attendance_status) VALUES ('2026-04-28 09:00:00', 40.00, 1, 1, 'FINISHED');
INSERT INTO tb_attendance (moment, gross_amount, barber_id, client_id, attendance_status) VALUES ('2026-04-28 09:30:00', 55.00, 2, 2, 'FINISHED');
INSERT INTO tb_attendance (moment, gross_amount, barber_id, client_id, attendance_status) VALUES ('2026-04-28 10:15:00', 25.00, 3, 3, 'IN_PROGRESS');
INSERT INTO tb_attendance (moment, gross_amount, barber_id, client_id, attendance_status) VALUES ('2026-04-28 10:45:00', 0.00, 1, 4, 'WAITING');


INSERT INTO tb_attendance_procedure (attendance_id, procedure_id) VALUES (1, 2);
INSERT INTO tb_attendance_procedure (attendance_id, procedure_id) VALUES (2, 2);
INSERT INTO tb_attendance_procedure (attendance_id, procedure_id) VALUES (2, 3);
INSERT INTO tb_attendance_procedure (attendance_id, procedure_id) VALUES (3, 1);

-- Pagamento Atendimento 1: Felipe pagou no PIX (sem desconto)
INSERT INTO tb_payment (attendance_id, payment_method, discount, amount_paid, moment) VALUES (1, 'PIX', 0.00, 40.00, '2026-04-28 09:40:00');

-- Pagamento Atendimento 2: Ricardo pagou no Dinheiro (com 5 reais de desconto)
INSERT INTO tb_payment (attendance_id, payment_method, discount, amount_paid, moment)VALUES (2, 'CASH', 5.00, 50.00, '2026-04-28 10:20:00');