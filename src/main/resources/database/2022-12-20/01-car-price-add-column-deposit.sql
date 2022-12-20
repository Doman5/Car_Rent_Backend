--liquibase formatted sql
--changeset ddomanski:9
    alter table car_price add column deposit int;
    update car_price set car_price.deposit = 1;
    alter table car_price modify column deposit int not null;