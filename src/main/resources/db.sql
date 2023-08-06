CREATE TABLE `book` (
	`id` INT(11) NOT NULL,
	`title` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
	`author` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
	`description` LONGTEXT NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
	`genre` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
	`image` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
	`isbn` BIGINT(20) NULL DEFAULT NULL,
	`published` DATETIME NULL DEFAULT NULL,
	`publisher` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
;
