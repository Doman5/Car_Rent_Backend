--liquibase formatted sql
--changeset ddomanski:10

    create table category(
        id bigint not null auto_increment primary key,
        name varchar(128) not null
    );
--changeset ddomanski:11
    alter table car add column category_id bigint;
    ALTER table car add constraint fk_car_category_id foreign key (category_id) references category(id);
    insert into category(id, name) value (1,'inne');
    update car set car.category_id = 1;
    alter table car modify column category_id bigint not null ;
