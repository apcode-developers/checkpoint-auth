CREATE TABLE example.user_admin (
	id varchar(100) NOT NULL,
	user_name varchar(100) NULL,
	user_password varchar(100) NULL,
	first_name varchar(100) NULL,
	last_name varchar(100) NULL,
	CONSTRAINT user_admin_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;
