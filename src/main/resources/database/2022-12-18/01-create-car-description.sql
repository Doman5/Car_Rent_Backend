--liquibase formatted sql
--changeset ddomanski:5

    create table car_description(
        id bigint not null primary key auto_increment,
        description text not null,
        car_id bigint,
        constraint fk_car_description_car_id foreign key (car_id) references car(id)
    )