--liquibase formatted sql
--changeset ddomanski:2

create table car_technical_specification (
    id BIGINT not null primary key auto_increment,
    power int not null,
    engine varchar(128) not null ,
    drive varchar(128) not null ,
    acceleration varchar(128) not null ,
    gearbox varchar(128) not null ,
    seats varchar(128) not null ,
    fuel varchar(128) not null
);
--changeset ddomanski:3
    alter table car add column car_technical_specification_id bigint;
    alter table car add constraint fk_car_car_technical_specification_id foreign key (car_technical_specification_id) references car_technical_specification(id);