CREATE TABLE user(
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `account_id` varchar(100) DEFAULT NULL,
                        `name` varchar(50) DEFAULT NULL,
                        `token` char(36) DEFAULT NULL,
                        `gmt_create` bigint(20) DEFAULT NULL,
                        `gmt_modified` bigint(20) DEFAULT NULL,
                        `avatar_url` varchar(100) DEFAULT NULL,
                        PRIMARY KEY (`id`)
)
