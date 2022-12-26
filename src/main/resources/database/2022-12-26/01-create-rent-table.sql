--liquibase formatted sql
--changeset ddomanski:16

    create table rent(
        id bigint auto_increment not null primary key ,
        car_id bigint not null ,
        user_id bigint,
        rental_date datetime not null ,
        rental_place varchar(128) not null,
        return_date datetime not null,
        return_place varchar(128) not null,
        gross_value float(7,2) not null,
        days int not null,
        constraint fk_rent_car_id foreign key (car_id) references car(id)
    )