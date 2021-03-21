CREATE TABLE example.info_pinajaman (
	id INT auto_increment NOT NULL,
	no_pengajuan INT NOT NULL,
	penerima_pinjaman varchar(100) NULL,
	CONSTRAINT info_pinajaman_pk PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;