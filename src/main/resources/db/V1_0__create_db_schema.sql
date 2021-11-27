create table if not exists role (
    id int primary key,
    name varchar(20) not null
);

insert into role values (1, 'USER'), (2, 'MODERATOR'),
                        (3, 'ADMINISTRATOR');

create table if not exists usr (
    id int primary key,
    login varchar(50) unique not null,
    password text not null,
    name varchar(50) unique not null,
    role_id int not null references role
        on delete restrict on update cascade
);

insert into usr values (1, 'login', 'pass', 'John Lennon', 2),
                       (2, 'log2', 'pass2', 'Paul McCartney', 3),
                       (3, 'log3', 'pass3', 'George Harrison', 1),
                       (4, 'log4', 'pass4', 'Ringo Starr', 1);

create table if not exists blocking (
    id int primary key,
    user_id int not null references usr
        on delete cascade on update cascade,
    block_date date not null,
    unblock_date date not null
);

create table if not exists room_type (
    id int primary key,
    name varchar(20) not null,
    max_members_num int not null check ( max_members_num > 0 )
);

insert into room_type values (1, 'PUBLIC', 100),
                             (2, 'PRIVATE', 2),
                             (3, 'CHAT-BOT', 1);

create table if not exists room (
    id int primary key,
    name varchar(50) unique not null,
    owner_id int references usr
        on delete set null on update cascade,
    room_type_id int not null references room_type
        on delete restrict on update cascade
);

insert into room values (1, 'The Beatles', 2, 1),
                        (2, 'John and George', 1, 2),
                        (3, 'Ringo chat-bot', 4, 3),
                        (4, 'Paul is dead', 1, 1);

create table if not exists user_in_room (
    room_id int not null references room
        on delete cascade on update cascade,
    user_id int not null references usr
        on delete cascade on update cascade,
    primary key (room_id, user_id)
);

insert into user_in_room values (1, 1), (1, 2), (1, 3), (1, 4),
                                (2, 1), (2, 3),
                                (3, 4),
                                (4, 1), (4, 3), (4, 4);


create table if not exists message (
    id int primary key,
    author_id int references usr
        on delete set null on update cascade,
    room_id int not null references room
        on delete cascade on update cascade,
    text text not null,
    date timestamp not null
);

insert into message values (1, 2, 1, 'It was twenty years ago today', '1968-05-15 19:05:05'),
                           (2, 2, 1, 'Sgt. Pepper taught the band to play', '1968-05-15 19:06:02'),
                           (3, 2, 1, 'They''ve been going in and out of style' , '1968-05-15 19:06:49'),
                           (4, 2, 1, 'But they''re guaranteed the raise of smile', '1968-05-15 19:07:43'),
                           (5, 1, 1, 'Let me take you down, cause I''m going to', '1968-05-15 19:13:06'),
                           (6, 1, 1, 'Strawberry Fields...', '1968-05-15 19:13:49'),
                           (7, 3, 1, 'Nothing is real', '1968-05-15 19:15:40'),
                           (8, 4, 1, 'And nothing to be hung about', '1968-05-15 19:17:25'),
                           (9, 1, 1, 'Strawberry Fields Forever!', '1968-05-15 19:21:00');

