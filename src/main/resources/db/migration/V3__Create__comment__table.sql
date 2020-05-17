create table comment(
                        `id` int not null auto_increment primary key,
                        `parent_id` bigint ,
                        `type` int not null,
                        `commentator` int not null,
                        `gmt_create` bigint not null,
                        `gmt_modified` bigint ,
                        `like_count` bigint default 0,
                        `content` varchar(1024)
)
