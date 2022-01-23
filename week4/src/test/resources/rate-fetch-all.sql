insert into member (id, created_date, updated_date, first_name, last_name )
values (1001, now(), now(), 'firstname1', 'lastname1'),
       (1002, now(), now(), 'firstname2', 'lastname2');

insert into movie (id, created_date, updated_date, genre, name, release_year )
values (2001, now(), now(), 'COMEDY', 'movie1', 2010),
       (2002, now(), now(), 'COMEDY', 'movie2', 2010);

insert into rate (id, created_date, updated_date, member_id, movie_id, point )
values (3001, now(), now(), 1001, 2001, 3),
       (3002, now(), now(), 1002, 2001, 4),
       (3003, now(), now(), 1001, 2002, 3),
       (3004, now(), now(), 1002, 2002, 4);