CREATE TABLE `question`(
                     `id` bigint NOT NULL AUTO_INCREMENT,
                     `title` varchar(50) DEFAULT NULL,
                     `description` text DEFAULT NULL,
                     `gmt_create` bigint DEFAULT NULL,
                     `gmt_modified` bigint DEFAULT NULL,
                     `creator` bigint DEFAULT NULL,
                     `comment_count` bigint DEFAULT 0 not null ,
                     `view_count` bigint DEFAULT 0 not null,
                     `like_count` bigint DEFAULT 0 not null,
                     `tag` varchar(256) DEFAULT NULL,
                     PRIMARY KEY (`id`)
)
