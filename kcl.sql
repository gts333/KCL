# tables should be placed in a database named "kcl"
create table administrators
(
    username varchar(50)  not null
        primary key,
    password varchar(100) null,
    identity varchar(50)  not null,
    constraint username
        unique (username)
);

create table project_properties
(
    property_name varchar(100) not null
        primary key,
    value         varchar(50)  null
);

create table resource_groups
(
    group_name varchar(50) not null
        primary key
);

create table students
(
    username        varchar(50)  not null
        primary key,
    password        varchar(100) null,
    identity        varchar(50)  not null,
    priority_status varchar(50)  null,
    constraint username
        unique (username)
);

create table requests
(
    request_id       int auto_increment
        primary key,
    student_username varchar(50)                         null,
    group_name       varchar(50)                         null,
    time_intervals   int                                 not null,
    title            varchar(50)                         null,
    content          varchar(500)                        null,
    appointment_type varchar(50)                         null,
    creation_time    timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    constraint requests_ibfk_1
        foreign key (group_name) references resource_groups (group_name),
    constraint requests_ibfk_2
        foreign key (student_username) references students (username)
);

create index group_name
    on requests (group_name);

create index student_username
    on requests (student_username);

create table student_resource_groups
(
    username   varchar(50) null,
    group_name varchar(50) null,
    constraint student_resource_groups_ibfk_1
        foreign key (group_name) references resource_groups (group_name),
    constraint student_resource_groups_ibfk_2
        foreign key (username) references students (username)
);

create index group_name
    on student_resource_groups (group_name);

create index username
    on student_resource_groups (username);

create table teaching_assistants
(
    username   varchar(50)          not null
        primary key,
    password   varchar(100)         null,
    identity   varchar(50)          not null,
    available  tinyint(1) default 1 null,
    adjustable tinyint(1) default 1 null,
    constraint username
        unique (username)
);

create table appointments
(
    appointment_id              int auto_increment
        primary key,
    student_username            varchar(50)                         null,
    teaching_assistant_username varchar(50)                         null,
    group_name                  varchar(50)                         null,
    title                       varchar(50)                         null,
    content                     varchar(500)                        null,
    appointment_type            varchar(50)                         null,
    start_time                  varchar(50)                         null,
    end_time                    varchar(50)                         null,
    creation_time               timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    constraint appointments_ibfk_1
        foreign key (teaching_assistant_username) references teaching_assistants (username),
    constraint appointments_ibfk_2
        foreign key (student_username) references students (username),
    constraint appointments_ibfk_3
        foreign key (group_name) references resource_groups (group_name)
);

create index group_name
    on appointments (group_name);

create index student_username
    on appointments (student_username);

create index teaching_assistant_username
    on appointments (teaching_assistant_username);

create table teaching_assistant_available_times
(
    time_id   int auto_increment
        primary key,
    username  varchar(50)            null,
    time      varchar(50) default '' not null,
    available tinyint(1)  default 1  null,
    constraint teaching_assistant_available_times_ibfk_1
        foreign key (username) references teaching_assistants (username)
);

create index username
    on teaching_assistant_available_times (username);

create table teaching_assistant_resource_groups
(
    username   varchar(50) null,
    group_name varchar(50) null,
    constraint teaching_assistant_resource_groups_ibfk_1
        foreign key (group_name) references resource_groups (group_name),
    constraint teaching_assistant_resource_groups_ibfk_2
        foreign key (username) references teaching_assistants (username)
);

create index group_name
    on teaching_assistant_resource_groups (group_name);

create index username
    on teaching_assistant_resource_groups (username);

