DELETE FROM ROLE r WHERE r.ROLE in ('USER', 'ADMIN','MANAGER');
insert into ROLE r (r.ROLE) values ('ADMIN');
insert into ROLE r (r.ROLE) values ('USER');
insert into ROLE r (r.ROLE) values ('MANAGER');