create user 'spring5'@'localhost' identified by 'spring5';

create database spring5fs character set=utf8;

grant all privileges on spring5fs.* to 'spring5'@'localhost';

create table spring5fs.MEMBER (
    ID int auto_increment primary key,
    EMAIL varchar(255),
    PASSWORD varchar(100),
    NAME varchar(100),
    REGDATE datetime,
    unique key(EMAIL)
) engine=INNODB character set=utf8;

insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE)
values ('hello@hello.com', '1234', 'yunha', now());