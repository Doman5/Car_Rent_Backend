--liquibase formatted sql
--changeset ddomanski:20
alter table rent add column rent_status varchar(128) not null ;

--changeset ddomanski:21
alter table rent drop column days;