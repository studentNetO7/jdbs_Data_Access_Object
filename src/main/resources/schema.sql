-- Таблица для пользователей
CREATE TABLE IF NOT EXISTS CUSTOMERS (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    surname VARCHAR(255),
    age INT,
    phone_number VARCHAR(20)
);

-- Таблица для заказов
CREATE TABLE IF NOT EXISTS ORDERS (
    id SERIAL PRIMARY KEY,
    date TIMESTAMP,
    customer_id INT,
    product_name VARCHAR(255),
    amount INT,
    FOREIGN KEY (customer_id) REFERENCES CUSTOMERS(id)
);

-- Вставка тестовых данных для пользователей
INSERT INTO CUSTOMERS (name, surname, age, phone_number) VALUES
('Ivan', 'Ivanov', 25, '123456789'),
('Alexey', 'Petrov', 30, '987654321'),
('Maria', 'Sidorova', 28, '555555555');

-- Вставка тестовых данных для заказов
INSERT INTO ORDERS (date, customer_id, product_name, amount) VALUES
('2025-02-15 12:00:00', 1, 'Product 1', 2),
('2025-02-15 14:00:00', 2, 'Product 2', 3),
('2025-02-15 16:00:00', 3, 'Product 3', 1);
