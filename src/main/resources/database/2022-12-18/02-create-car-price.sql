--liquibase formatted sql
--changeset ddomanski:6

    create table car_price(
        id bigint not null auto_increment primary key ,
        price_day decimal(9,2) not null ,
        price_half_week decimal(9,2) not null ,
        price_week decimal(9,2) not null ,
        price_two_weeks decimal(9,2) not null ,
        price_month decimal(9,2) not null
    )
--changeset ddomanski:7
    alter table car add column car_price_id bigint;
    alter table car add constraint fk_car_car_price_car_id foreign key (car_price_id) references car_price(id);
