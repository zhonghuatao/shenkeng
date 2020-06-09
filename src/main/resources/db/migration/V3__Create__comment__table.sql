create table comment(
                        `id` bigint not null auto_increment primary key,
                        `parent_id` bigint ,
                        `type` int not null,
                        `commentator` bigint not null,
                        `gmt_create` bigint not null,
                        `gmt_modified` bigint ,
                        `like_count` bigint default 0 not null,
                        `content` varchar(1024)
)
