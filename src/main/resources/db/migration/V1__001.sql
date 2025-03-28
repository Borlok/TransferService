create table if not exists users
(
    id            serial primary key,
    name          varchar(100) not null,
    date_of_birth date,
    password      varchar(500)
);
create table if not exists accounts
(
    id              serial primary key,
    user_id         bigint not null,
    initial_balance decimal default 0.0 not null check ( initial_balance > 0 ),
    balance         decimal default 0.0 not null check ( balance > 0 ),
    constraint fk_accounts_users foreign key (user_id) references users(id) on delete cascade
);
create table if not exists email_data
(
    id      serial primary key,
    user_id bigint not null,
    email   varchar(200) not null unique,
    constraint fk_emails_users foreign key (user_id) references users(id) on delete cascade
);
create table if not exists phone_data
(
    id      serial primary key,
    user_id bigint not null,
    phone   varchar(200) not null unique,
    constraint fk_phones_users foreign key (user_id) references users(id) on delete cascade
);
insert into users (name, date_of_birth, password) VALUES
                                                      ('Petr', current_timestamp, '$2a$10$au2FfZju/jRjpw39pUu5h.9iDyfADOCtW3oB7NilOidurkytT3mXa');
insert into accounts (user_id, initial_balance, balance) VALUES (1, 1000, 1000);
insert into email_data (user_id, email) VALUES (1, 'test@test.ts');
insert into phone_data (user_id, phone) VALUES (1, '72312312312')
