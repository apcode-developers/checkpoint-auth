CREATE TABLE example.inventory (
	id VARCHAR(100) NOT NULL,
	inventory_name varchar(100) NOT NULL,
	id_admin varchar(100) NOT NULL,
	id_category INT NULL,
	tgl_upload TIMESTAMP NULL,
	last_update TIMESTAMP NULL,
	CONSTRAINT inventory_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;
