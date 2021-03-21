CREATE TABLE example.`user` (
	`user` varchar(100) NOT NULL,
	user_name varchar(100) NULL,
	user_password varchar(100) NULL,
	first_name varchar(100) NULL,
	last_name varchar(100) NULL,
	email varchar(100) NULL,
	phone_number varchar(100) NULL,
	status varchar(100) NULL,
	account_status varchar(100) NULL,
	birth_date DATE NULL,
	last_update TIMESTAMP NULL,
	register_timr TIMESTAMP NULL,
	gender varchar(10) NULL,
	pin varchar(10) NULL,
	photo_profile varchar(100) NULL,
	`path` varchar(100) NULL,
	CONSTRAINT user_PK PRIMARY KEY (`user`)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;