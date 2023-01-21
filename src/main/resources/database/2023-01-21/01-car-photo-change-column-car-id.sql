--liquibase formatted sql
--changeset ddomanski:27

    alter table car_photo modify column car_id bigint;