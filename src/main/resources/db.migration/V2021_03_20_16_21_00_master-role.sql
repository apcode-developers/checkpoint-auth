CREATE TABLE example.m_role (
	id INT auto_increment NOT NULL,
	role_name varchar(100) NULL,
	CONSTRAINT m_role_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;


CREATE TABLE example.m_role_user (
	id INT auto_increment NOT NULL,
	role_name varchar(100) NULL,
	CONSTRAINT m_role_user_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;
