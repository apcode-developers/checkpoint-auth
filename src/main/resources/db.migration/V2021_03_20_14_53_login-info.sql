CREATE TABLE example.login_info (
	id INT auto_increment NOT NULL,
	user_name varchar(100) NOT NULL,
	token varchar(100) NOT NULL,
	`as` varchar(100) NULL,
	CONSTRAINT login_info_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;
