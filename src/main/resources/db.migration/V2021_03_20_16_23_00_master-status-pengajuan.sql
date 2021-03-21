CREATE TABLE example.m_status_pengajuan (
	id INT auto_increment NOT NULL,
	status_pinjaman varchar(100) NULL,
	CONSTRAINT m_status_pengajuan_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;
