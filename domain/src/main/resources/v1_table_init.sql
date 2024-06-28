create table movie_land.rev_info
(
  rev        bigint auto_increment
        primary key,
  revstmp    bigint not null,
  created_at datetime default CURRENT_TIMESTAMP null
);

create table movie_land.users
(
  id         varchar(255) not null
    primary key,
  email varchar(255) not null,
  password   varchar(255) not null,
  social_id  varchar(255) null,
  login_type varchar(80)  not null,
  nickname   varchar(80)  not null,
  phone      varchar(80)  not null,
  role       varchar(80)  not null,
  deleted    bit          not null,
  created_at datetime     not null,
  updated_at datetime     not null
);

create index users_username_login_type_index
  on movie_land.users (username, login_type);

create table movie_land.users_histories
(
  id         varchar(255) not null,
  rev_id     bigint       not null,
  revtype    tinyint      not null,
  email varchar(255) not null,
  password   varchar(255) not null,
  social_id  varchar(255) null,
  login_type varchar(80)  not null,
  nickname   varchar(255) not null,
  phone      varchar(80)  not null,
  role       varchar(80)  not null,
  deleted    bit          not null,
  created_at datetime     not null,
  updated_at datetime     not null,
  primary key (id, rev_id)
);

