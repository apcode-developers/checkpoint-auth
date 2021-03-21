CREATE TABLE example.pengajuan_master (
	no_pengajuan INT auto_increment NOT NULL,
	id_user varchar(100) NULL,
	requested_role INT NULL,
	deskripsi varchar(100) NULL,
	CONSTRAINT pengajuan_master_PK PRIMARY KEY (no_pengajuan)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;
