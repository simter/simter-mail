/**
 * Create table script.
 * @author RJ
 */
create table st_mail (
  id serial primary key
);
comment on table st_mail     is 'Mail';
comment on column st_mail.id is 'ID';