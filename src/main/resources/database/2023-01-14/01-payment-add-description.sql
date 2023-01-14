--liquibase formatted sql
--changeset ddomanski:25
    alter table payment add column description text;
--changeset ddomanski:26
    update payment set payment.description='Ta opcja polega na wykonaniu tradycyjnego przelewu bankowego' where id=1;
    update payment set payment.description='Ta opcja polega na wykonaniu przelewu poprzez platformę Przelewy24' where id=2;
    update payment set payment.description='Wybór tej opcji polega na możliwości płatności przy odbiorze, ale trzeba przelać 25% kwoty by potwierdzić rezerwacje' where id=3;