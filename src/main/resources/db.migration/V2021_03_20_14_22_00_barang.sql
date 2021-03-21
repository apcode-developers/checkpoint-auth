CREATE TABLE example.barang (
	id INT auto_increment NOT NULL,
	nama_barang varchar(100) NULL,
	id_kategori INT NULL,
	jumlah INT NULL,
	tgl_masuk TIMESTAMP NULL,
	last_update TIMESTAMP NULL,
	id_inventory varchar(100) NULL,
	CONSTRAINT barang_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;
