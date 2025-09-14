
CREATE DATABASE IF NOT EXISTS ecommerce_db;
USE ecommerce_db;


CREATE TABLE IF NOT EXISTS products (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  price DECIMAL(10,2) NOT NULL,
  stock INT NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO products (id, name, price, stock) VALUES
(1, 'Laptop', 1205.00, 20),
(2, 'Smartphone', 800.00, 15),
(3, 'Headphones', 150.00, 25),
(4, 'Keyboard', 70.00, 30),
(5, 'Mouse', 50.00, 50)
ON DUPLICATE KEY UPDATE
  name = VALUES(name),
  price = VALUES(price),
  stock = VALUES(stock);
