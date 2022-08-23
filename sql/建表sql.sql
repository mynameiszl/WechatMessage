
CREATE TABLE `wx_user` (
       `open_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
       `template_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
       `city` varchar(100) NOT NULL,
       `name` varchar(100) DEFAULT NULL,
       `birthday` datetime DEFAULT NULL,
       `disable` tinyint(1) DEFAULT NULL,
        `loading` tinyint(1) DEFAULT '0',
         PRIMARY KEY (`open_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;