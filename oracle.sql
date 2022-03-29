create table board(
no      number          primary key,
title   varchar2(100)   not null,
name    varchar2(20)    not null,
content clob            not null,
regdate date            default sysdate,
readcount   number      default 0,
password    varchar2(128) not null
);

create sequence board_no_seq;
