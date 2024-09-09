create table if not exists users
(
    id                 bigserial  primary key,
    username           varchar(50) not null  unique,
    password           varchar(50) not null,
    email              varchar(255) not null unique,
    role               varchar(50) not null,
    created_date       timestamp not null default current_timestamp,
    last_modified_date timestamp not null default current_timestamp
    );



create table if not exists tasks
(
    id                 bigserial primary key,
    title              varchar(255) not null,
    description        varchar(255) not null,
    status             varchar(50),
    priority           varchar(50),
    owner_id           bigint not null constraint tasks_owner_id_fk references users (id),
    created_date       timestamp not null default current_timestamp,
    last_modified_date timestamp not null default current_timestamp
    );

create table if not exists executers
(
    id        bigserial primary key,
    task_id   bigint not null constraint executers_task_id_fk references tasks (id),
    user_id   bigint not null constraint executers_user_id_fk references users (id)
    );

create table if not exists comments
(
    id                  bigserial primary key,
    comment             varchar(500) not null,
    task_id             bigint not null constraint comment_task_id_fk references tasks (id),
    user_id             bigint not null constraint comment_user_id_fk references users (id),
    created_date        timestamp not null default current_timestamp,
    last_modified_date  timestamp not null default current_timestamp
    );


