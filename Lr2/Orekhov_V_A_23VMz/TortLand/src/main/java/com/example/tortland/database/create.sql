create table users
(
    id                    bigint       not null
        primary key,
    created_by            varchar(255),
    created_when          date,
    deleted_by            varchar(255),
    deleted_when          date,
    is_deleted            boolean,
    updated_by            varchar(255),
    updated_when          date,
    about_me              varchar(255),
    address               varchar(255),
    birth_date            varchar(255),
    change_password_token varchar(255),
    city                  integer,
    email                 varchar(255) not null
        constraint uk_email
            unique,
    experience            varchar(255),
    first_name            varchar(255),
    last_name             varchar(255),
    login                 varchar(255) not null
        constraint uk_login
            unique,
    middle_name           varchar(255),
    password              varchar(255) not null,
    phone                 varchar(255),
    plug                  varchar(255),
    status                varchar(255),
    avatar_id             bigint
        constraint fk_user_avatar
            references images,
    role_id               bigint
        constraint fk_user_roles
            references roles
            on delete cascade
);

alter table users
    owner to postgres;

create table roles
(
    id          bigint not null
        primary key,
    description varchar(255),
    title       varchar(255)
);

alter table roles
    owner to postgres;

create table cakes
(
    id                 bigint not null
        primary key,
    created_by         varchar(255),
    created_when       date,
    deleted_by         varchar(255),
    deleted_when       date,
    is_deleted         boolean,
    updated_by         varchar(255),
    updated_when       date,
    city               integer,
    cooking_time       integer,
    decorating         varchar(255),
    form               integer,
    name               varchar(255),
    preview_image_plug varchar(255),
    second_image_plug  varchar(255),
    short_description  varchar(255),
    third_image_plug   varchar(255),
    weight_from        double precision,
    user_id            bigint
        constraint fk_cakes_users
            references users
);

alter table cakes
    owner to postgres;

create table images
(
    id                 bigint not null
        primary key,
    bytes              oid,
    content_type       varchar(255),
    is_preview_image   boolean,
    name               varchar(255),
    original_file_name varchar(255),
    title              bigint,
    status             boolean,
    cakes_id           bigint
        constraint fk_image_cake
            references cakes
            on delete cascade,
    users_id           bigint
        constraint fk_image_user
            references users
);

alter table images
    owner to postgres;

create table fillings
(
    id           bigint not null
        primary key,
    created_by   varchar(255),
    created_when date,
    deleted_by   varchar(255),
    deleted_when date,
    is_deleted   boolean,
    updated_by   varchar(255),
    updated_when date,
    name         varchar(255),
    price_per    integer,
    cakes_id     bigint
        constraint fk_filling_cake
            references cakes
            on delete cascade
);

alter table fillings
    owner to postgres;

create table orders
(
    id                    bigint  not null
        primary key,
    created_by            varchar(255),
    created_when          date,
    deleted_by            varchar(255),
    deleted_when          date,
    is_deleted            boolean,
    updated_by            varchar(255),
    updated_when          date,
    activity              boolean,
    address               varchar(255),
    city                  integer,
    delivery_date         date    not null,
    number                varchar(255),
    pickup                boolean not null,
    price                 double precision,
    status                integer,
    title                 varchar(255),
    weight_from           double precision,
    cakes_id              bigint
        constraint fk_order_cake
            references cakes
            on delete cascade,
    filling_id            bigint
        constraint fk_order_filling
            references fillings
            on delete cascade,
    user_confectioners_id bigint
        constraint fk_custom_order_user_confectioner
            references users
            on delete cascade,
    users_id              bigint
        constraint fk_order_user
            references users
            on delete cascade
);

alter table orders
    owner to postgres;

create table custom_orders
(
    id                    bigint not null
        primary key,
    created_by            varchar(255),
    created_when          date,
    deleted_by            varchar(255),
    deleted_when          date,
    is_deleted            boolean,
    updated_by            varchar(255),
    updated_when          date,
    activity              boolean,
    address               varchar(255),
    city                  integer,
    decoration            varchar(255),
    delivery_date         date,
    filling               varchar(255),
    form                  integer,
    number                varchar(255),
    price                 double precision,
    short_description     varchar(255),
    status                integer,
    tiers                 integer,
    title                 varchar(255),
    w_t                   double precision,
    user_confectioners_id bigint
        constraint fk_custom_order_user_confectioner
            references users
            on delete cascade,
    users_id              bigint
        constraint fk_custom_order_user
            references users
            on delete cascade
);

alter table custom_ordes
    owner to postgres;

