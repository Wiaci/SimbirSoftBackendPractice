create table if not exists role (
    id int primary key,
    name varchar(20) not null
);

create table if not exists usr (
    id int primary key,
    login varchar(50) not null,
    password text not null,
    name varchar(50) not null,
    role_id int not null references role
        on delete restrict on update cascade
);

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

create table if not exists room (
    id int primary key,
    name varchar(50) not null,
    owner_id int references usr
        on delete set null on update cascade,
    room_type_id int not null references room_type
        on delete restrict on update cascade
);

create table if not exists message (
    id int primary key,
    author_id int references usr
        on delete set null on update cascade,
    room_id int not null references room
        on delete cascade on update cascade,
    text text not null
);