--liquibase formatted sql
--changeset ddomanski:30
    alter table users rename column second_name to last_name;