CREATE TABLE `question`(
                     `id` int NOT NULL AUTO_INCREMENT,
                     `title` varchar(50) DEFAULT NULL,
                     `description` text DEFAULT NULL,
                     `gmt_create` bigint DEFAULT NULL,
                     `gmt_modified` bigint DEFAULT NULL,
                     `creator` int DEFAULT NULL,
                     `comment_count` int DEFAULT 0,
                     `view_count` int DEFAULT 0,
                     `like_count` int DEFAULT 0,
                     `tag` varchar(256) DEFAULT NULL,
                     PRIMARY KEY (`id`)
)
