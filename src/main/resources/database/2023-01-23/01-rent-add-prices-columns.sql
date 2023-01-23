--liquibase formatted sql
--changeset ddomanski:28
    alter table rent add column deposit float(8,2) not null;

--changeset ddomanski:29
    alter table rent add column final_price float(8,2) not null;