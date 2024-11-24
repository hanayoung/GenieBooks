drop database if exists ssafy_pennant;
create database ssafy_pennant;
use ssafy_pennant;

create table customer(
    c_id integer auto_increment primary key,
    nickname char(50) not null,
	id char(50) not null,
    pwd char(50) not null,
    age integer not null default 1,
	sex char(50) not null,
    point integer not null default 0
);                                                 

create table interest(
    i_id integer auto_increment primary key,
    c_id integer not null,
    type char(50) not null,
    constraint foreign key (c_id) references customer(c_id) on delete cascade
);    

create table staff(
    s_id integer auto_increment primary key,
    nickname char(50) not null,
	id char(50) not null,
    pwd char(50) not null
);                                                 
                   
create table p_customer(
    o_id integer auto_increment primary key,
    c_id integer not null,
    o_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_done boolean not null default false
);                               

create table p_book(
	id integer auto_increment primary key,
    isbn bigint,
    o_id integer not null,
    quantity integer not null default 1,
    constraint foreign key (o_id) references p_customer(o_id) on delete cascade
);  

create table gift(
    g_id integer auto_increment primary key,
    title char(50) not null,
    content char(50) not null,
    image char(50) not null default false,
	g_date char(50) not null
); 

create table recommend(
	id integer auto_increment primary key,
    isbn bigint not null
);

