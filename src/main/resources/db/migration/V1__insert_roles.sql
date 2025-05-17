DELETE FROM hr.role r WHERE r.ROLE in ('USER', 'ADMIN','MANAGER');
insert into hr.role r (r.role) values ('ADMIN');
insert into hr.role r (r.role) values ('USER');
insert into hr.role r (r.role) values ('MANAGER');