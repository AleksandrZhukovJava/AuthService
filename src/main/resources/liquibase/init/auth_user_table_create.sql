--liquibase formatted sql

--changeset "Aleksandr Zhukov":"Add user table"
create table auth_user
(
    id       uuid primary key,
    username varchar not null unique,
    password varchar not null,
    role     varchar not null
)
