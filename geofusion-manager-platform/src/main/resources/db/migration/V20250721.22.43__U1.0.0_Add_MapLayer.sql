create table if not exists map_layer_directory (
    id varchar(64) primary key,
    directory_name varchar(64) not null,
    directory_title varchar(64) not null,
    directory_order int2 not null,
    directory_parent varchar(64)
);

create table if not exists map_layer (
    id varchar(64) primary key,
    layer_name varchar(64) not null,
    layer_title varchar(64) not null,
    layer_order int2 not null,
    layer_parent varchar(64),
    layer_config jsonb not null,
    layer_zindex int2 not null
);
