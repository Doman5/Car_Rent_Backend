--liquibase formatted sql
--changeset ddomanski:23
create table users(
                      id bigint not null auto_increment PRIMARY KEY,
                      username varchar(50) not null unique,
                      password varchar(500) not null,
                      enabled boolean not null
);
--changeset mdabrowski:24
create table authorities (
                             username varchar(50) not null,
                             authority varchar(50) not null,
                             constraint fk_authorities_users foreign key(username) references users(username)
);
--changeset mdabrowski:25
create unique index ix_auth_username on authorities (username,authority);
--changeset mdabrowski:26
insert into users (id, username, password, enabled)
values (1, 'admin', '{bcrypt}$2a$10$upzXFsFUOClFRR69OMKF8eajGMRs0vhcSHqvNDKy9yfW45w7o9z6O', true);
insert into authorities (username, authority) values ('admin','ROLE_ADMIN');