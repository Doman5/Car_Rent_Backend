--liquibase formatted sql
--changeset ddomanski:8
    create table car_photo(
        id bigint not null auto_increment primary key ,
        photo varchar(128) not null ,
        car_id bigint not null ,
        constraint fk_car_photo_car_id foreign key (car_id) references car(id)
    )