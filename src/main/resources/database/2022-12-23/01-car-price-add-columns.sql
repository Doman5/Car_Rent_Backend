--liquibase formatted sql
--changeset ddomanski:13
alter table car_price add column distance_limit int;
update car_price set car_price.distance_limit = 250;
alter table car_price modify distance_limit int not null;

--changeset ddomanski:14
alter table car_price add column distance_limit_penalty float(4,2);
update car_price set car_price.distance_limit_penalty = 2.5;
alter table car_price modify distance_limit_penalty float(4,2) not null;

--changeset ddomanski:15
alter table car_price add column transport_price_per_km float(4,2);
update car_price set car_price.transport_price_per_km = 3.0;
alter table car_price modify transport_price_per_km float(4,2) not null;
