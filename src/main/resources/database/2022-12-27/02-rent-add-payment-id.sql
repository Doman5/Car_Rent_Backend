--liquibase formatted sql
--changeset ddomanski:19
    alter table rent add column payment_id bigint not null after user_id;
    alter table rent add constraint fk_rent_payment_id foreign key (payment_id) references payment(id);