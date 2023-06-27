CREATE TABLE client (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  client_email VARCHAR(255),
  client_name VARCHAR(255),
  discount DOUBLE,
  nip_no VARCHAR(255)
);

CREATE TABLE order_pdf (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  created_at DATE,
  client_id INTEGER,
  FOREIGN KEY (client_id) REFERENCES client (id)
);

CREATE TABLE order_file (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  drawing_copy_qty INTEGER,
  drawing_size_hight DOUBLE,
  drawing_size_width DOUBLE,
  file_dir VARCHAR(255),
  file_name VARCHAR(255),
  is_drawing_color BOOLEAN,
  is_fold BOOLEAN,
  order_pdf_id INTEGER,
  FOREIGN KEY (order_pdf_id) REFERENCES order_pdf (id)
);