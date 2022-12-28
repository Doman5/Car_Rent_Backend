--liquibase formatted sql
--changeset ddomanski:22
    alter table car add column body_type varchar(128) after year;