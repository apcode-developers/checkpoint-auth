CREATE TABLE example.pengajuan_pinjam (
	id INT auto_increment NOT NULL,
	jumlah_barang INT NULL,
	tgl_pengajuan TIMESTAMP NULL,
	tgl_pengembalian DATE NULL,
	id_status_pengajuan INT NULL,
	id_baran INT NULL,
	CONSTRAINT pengajuan_pinjam_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;
