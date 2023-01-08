--liquibase formatted sql
--changeset ddomanski:24
alter table users add column first_name varchar(255);
alter table users add column second_name varchar(255);
alter table users add column phone char(9);