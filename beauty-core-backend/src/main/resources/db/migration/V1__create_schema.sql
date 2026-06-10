create table specializations (
    id         bigint auto_increment not null primary key,
    name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp
);

create table professionals (
    id         bigint auto_increment not null primary key,
    name       varchar(40)             not null,
    email      varchar(100) unique     not null,
    password   varchar(225)            not null,
    is_active  boolean   default true  not null,
    is_working boolean   default false not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp,

    index      idx_professional_name (name)
);

create table professional_specialty (
    professional_id bigint not null,
    specialty_id    bigint not null,

    primary key (professional_id, specialty_id),
    foreign key (professional_id) references professionals (id),
    foreign key (specialty_id) references specializations (id)
);

create table roles (
    id        bigint auto_increment not null primary key,
    authority varchar(30) unique not null
);

create table professional_role (
    professional_id bigint not null,
    role_id         bigint not null,

    primary key (professional_id, role_id),
    foreign key (professional_id) references professionals (id),
    foreign key (role_id) references roles (id)
);

create table clients (
    id             bigint auto_increment not null primary key,
    name           varchar(50)             not null,
    phone          varchar(11) unique      not null,
    birth_date     date                    not null,
    credit         decimal(5, 2)           not null,
    in_appointment boolean   default false not null,
    created_at     timestamp default current_timestamp,
    updated_at     timestamp,

    index          idx_client_name (name)
);

create table services (
    id         bigint auto_increment not null primary key,
    name       varchar(30)   not null,
    base_price decimal(5, 2) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp
);

create table appointments (
    id                 bigint auto_increment not null primary key,
    professional_id    bigint                  not null,
    client_id          bigint                  not null,
    appointment_status enum ('WAITING', 'IN_PROGRESS', 'FINISHED', 'CANCELED') default 'WAITING' not null,
    discount           decimal(5, 2)           not null,
    total_value        decimal(5, 2)           not null,
    remaining_value    decimal(5, 2)           not null,
    is_paid            boolean   default false not null,
    created_at         timestamp default current_timestamp,
    updated_at         timestamp,
    finished_at        timestamp,

    foreign key (professional_id) references professionals (id),
    foreign key (client_id) references clients (id)
);

create table appointment_service (
    appointment_id  bigint        not null,
    service_id      bigint        not null,
    price_at_moment decimal(5, 2) not null,

    primary key (appointment_id, service_id),
    foreign key (appointment_id) references appointments (id),
    foreign key (service_id) references services (id)
);

create table payments (
    id             bigint auto_increment not null primary key,
    appointment_id bigint        not null,
    amount_paid    decimal(5, 2) not null,
    payment_method enum ('PIX', 'CASH', 'DEBIT', 'CREDIT') not null,
    paid_at        timestamp default current_timestamp,

    foreign key (appointment_id) references appointments (id)
);