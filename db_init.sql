
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
(5, 'Mouse', 50.00, 50),
(6, 'Monitor', 300.00, 10),
(7, 'Webcam', 85.00, 15),
(8, 'External HDD', 120.00, 20),
(9, 'USB-C Hub', 40.00, 30),
(10, 'Gaming Chair', 250.00, 12),
(11, 'Graphics Card', 450.00, 5),
(12, 'RAM 16GB', 90.00, 25),
(13, 'SSD 1TB', 130.00, 20),
(14, 'Power Supply', 100.00, 18),
(15, 'Motherboard', 200.00, 10),
(16, 'CPU i7', 350.00, 8),
(17, 'CPU Cooler', 60.00, 15),
(18, 'Desk Lamp', 35.00, 20),
(19, 'Router', 120.00, 15),
(20, 'Printer', 150.00, 10)
ON DUPLICATE KEY UPDATE
  name = VALUES(name),
  price = VALUES(price),
  stock = VALUES(stock);


CREATE TABLE IF NOT EXISTS users (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(150) NOT NULL UNIQUE,
  password VARCHAR(128) NOT NULL, 
  role VARCHAR(20) NOT NULL DEFAULT 'USER',
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO users (id, name, email, password, role) VALUES
(1, 'Admin', 'admin@example.com', '36513411', 'ADMIN'),
(2, 'Alice', 'alice@example.com', '36513411', 'USER'),
(3, 'Bob', 'bob@example.com', '36513411', 'USER'),
(4, 'Carol', 'carol@example.com', '36513411', 'USER'),
(5, 'David', 'david@example.com', '36513411', 'USER'),
(6, 'Eve', 'eve@example.com', '36513411', 'USER'),
(7, 'Frank', 'frank@example.com', '36513411', 'USER'),
(8, 'Grace', 'grace@example.com', '36513411', 'USER'),
(9, 'Hank', 'hank@example.com', '36513411', 'USER'),
(10, 'Ivy', 'ivy@example.com', '36513411', 'USER')
ON DUPLICATE KEY UPDATE name = VALUES(name);

-- PEDIDOS
CREATE TABLE IF NOT EXISTS orders (
  id INT NOT NULL AUTO_INCREMENT,
  user_id INT NOT NULL,
  total DECIMAL(10,2) NOT NULL,
  status VARCHAR(30) NOT NULL DEFAULT 'PENDING',
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO orders (id, user_id, total, status) VALUES
(1, 2, 2155.00, 'COMPLETED'),
(2, 3, 220.00, 'PENDING'),
(3, 4, 305.00, 'COMPLETED'),
(4, 5, 850.00, 'PENDING'),
(5, 6, 130.00, 'PENDING'),
(6, 7, 1205.00, 'COMPLETED'),
(7, 8, 300.00, 'PENDING'),
(8, 9, 150.00, 'COMPLETED'),
(9, 10, 70.00, 'PENDING'),
(10, 2, 50.00, 'COMPLETED'),
(11, 3, 400.00, 'PENDING'),
(12, 4, 800.00, 'COMPLETED'),
(13, 5, 100.00, 'PENDING'),
(14, 6, 200.00, 'COMPLETED'),
(15, 7, 350.00, 'PENDING')
ON DUPLICATE KEY UPDATE total = VALUES(total), status = VALUES(status);

CREATE TABLE IF NOT EXISTS order_items (
  id INT NOT NULL AUTO_INCREMENT,
  order_id INT NOT NULL,
  product_id INT NOT NULL,
  quantity INT NOT NULL,
  price_at_purchase DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
  FOREIGN KEY (product_id) REFERENCES products(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO order_items (id, order_id, product_id, quantity, price_at_purchase) VALUES
(1, 1, 1, 1, 1205.00),
(2, 1, 2, 1, 800.00),
(3, 1, 3, 1, 150.00),
(4, 2, 4, 2, 70.00),
(5, 2, 5, 1, 50.00),
(6, 3, 3, 2, 150.00),
(7, 3, 5, 1, 50.00),
(8, 4, 2, 1, 800.00),
(9, 4, 5, 1, 50.00),
(10, 5, 8, 1, 120.00),
(11, 6, 1, 1, 1205.00),
(12, 7, 6, 1, 300.00),
(13, 8, 3, 1, 150.00),
(14, 9, 4, 1, 70.00),
(15, 10, 5, 1, 50.00),
(16, 11, 2, 1, 800.00),
(17, 12, 2, 1, 800.00),
(18, 12, 3, 1, 150.00),
(19, 13, 5, 2, 50.00),
(20, 14, 6, 1, 300.00),
(21, 14, 7, 1, 85.00),
(22, 15, 1, 1, 1205.00)
ON DUPLICATE KEY UPDATE quantity = VALUES(quantity), price_at_purchase = VALUES(price_at_purchase);
