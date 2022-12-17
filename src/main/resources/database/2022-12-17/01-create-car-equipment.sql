--liquibase formatted sql
--changeset ddomanski:4

    create table car_equipment(
      id bigint auto_increment not null primary key ,
      name varchar(256) not null ,
      car_id bigint,
      constraint fk_car_equipment foreign key (car_id) references car(id)
    );
