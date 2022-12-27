--liquibase formatted sql
--changeset ddomanski:17

    create table payment(
        id bigint not null primary key auto_increment,
        name varchar(128) not null,
        type varchar(64) not null,
        default_payment boolean default false,
        note text
    );

--changeset ddomanski:18
insert into payment values (1, 'Przelew bankowy', 'BANK_TRANSFER', true, 'Prosimy o dokonanie przelewu na konto:\n 30 2000 1000 4000 3000 2000 1000\n w tytule proszę podać nr zamówienia'),
                           (2, 'Przelewy 24', 'P24', false, 'Zaraz nastąpi przekierowanie na stronę przelewy24'),
                           (3, 'Przy odbiorze', 'CASH_ON_DELIVERY', false, 'Prosimy o dokonanie przelewu połowy kwoty na konto:\n 30 2000 1000 4000 3000 2000 1000\n w tytule proszę podać nr zamówienia')