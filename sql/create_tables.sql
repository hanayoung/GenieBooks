create database ssafy_pennant;
use ssafy_pennant;

create table customer(
    c_id integer auto_increment primary key,
    nickname char(50) not null,
	id char(50) not null,
    pwd char(50) not null,
    age integer not null default 1,
	sex char(50) not null,
    point integer not null default 0,
    fcm_token varchar(255)
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
    is_pickup boolean not null default false,
    is_done boolean not null default false,
    payment integer not null default 0,
    p_date TIMESTAMP
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
    content char(100) not null,
    image char(255) not null default false,
	g_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    recipient_id int not null default 0,
    sender_id int not null,
    sender_name varchar(50) NOT NULL,
    constraint foreign key (sender_id) references customer(c_id) on delete cascade
); 

create table recommend(
	id integer auto_increment primary key,
    isbn bigint not null
);

create table attendance(
	id integer auto_increment primary key,
    c_id integer not null,
    attend_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    constraint foreign key (c_id) references customer(c_id) on delete cascade
);
