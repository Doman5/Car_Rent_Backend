--liquibase formatted sql
--changeset ddomanski:23

create table rent_log(
    id bigint not null auto_increment primary key ,
    rent_id bigint not null,
    created datetime not null,
    note text
)