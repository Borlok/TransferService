create table if not exists users
(
    id            serial primary key,
    name          varchar(100) not null,
    date_of_birth timestamp,
    password      varchar(500)
);
create table if not exists accounts
(
    id              serial primary key,
    user_id         bigint unique references users (id),
    initial_balance decimal default 0.0 not null check ( initial_balance > 0 ),
    balance         decimal default 0.0 not null check ( balance > 0 )
);
create table if not exists email_data
(
    id      serial primary key,
    user_id bigint unique references users (id),
    email   varchar(200) not null unique
);
create table if not exists phone_data
(
    id      serial primary key,
    user_id bigint unique references users (id),
    phone   varchar(200) not null unique
);
insert into users (name, date_of_birth, password) VALUES
                                                      ('Petr', current_timestamp, 'asdfasdf');
insert into accounts (user_id, initial_balance, balance) VALUES (1, 1000, 1000);
insert into email_data (user_id, email) VALUES (1, 'asdf@bv.bg');
insert into phone_data (user_id, phone) VALUES (1, '12312312312')
