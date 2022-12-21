--liquibase formatted sql
--changeset ddomanski:12

alter table car add column slug varchar(256) unique;
