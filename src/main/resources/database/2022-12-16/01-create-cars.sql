--liquibase formatted sql
--changeset ddomanski:1

create table car (
    id bigint primary key auto_increment not null,
    brand varchar(128) not null,
    model varchar(128) not null,
    year int not null
)