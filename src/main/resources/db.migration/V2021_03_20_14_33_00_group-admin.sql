CREATE TABLE example.group_admin (
	id INT auto_increment NOT NULL,
	id_role INT NULL,
	id_user varchar(100) NULL,
	CONSTRAINT group_admin_pk PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;
