-- 1. Schema Design
-- Create three tables: Products, Categories, and SalesTransactions.

CREATE DATABASE IF NOT EXISTS FreshMart;
USE FreshMart;

-- Drop tables if they already exist to allow re-running the script
DROP TABLE IF EXISTS SalesTransactions;
DROP TABLE IF EXISTS Products;
DROP TABLE IF EXISTS Categories;

-- Create Categories table
CREATE TABLE Categories (
    CategoryID INT PRIMARY KEY AUTO_INCREMENT,
    CategoryName VARCHAR(100) NOT NULL
);

-- Create Products table
CREATE TABLE Products (
    ProductID INT PRIMARY KEY AUTO_INCREMENT,
    ProductName VARCHAR(100) NOT NULL,
    CategoryID INT,
    StockCount INT NOT NULL,
    ExpiryDate DATE,
    Price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID)
);

-- Create SalesTransactions table
CREATE TABLE SalesTransactions (
    TransactionID INT PRIMARY KEY AUTO_INCREMENT,
    ProductID INT,
    QuantitySold INT NOT NULL,
    SaleDate DATE NOT NULL,
    TotalPrice DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

-- Populate Dummy Data
INSERT INTO Categories (CategoryName) VALUES
('Dairy'), ('Bakery'), ('Produce'), ('Beverages');

INSERT INTO Products (ProductName, CategoryID, StockCount, ExpiryDate, Price) VALUES
('Milk', 1, 60, DATE_ADD(CURRENT_DATE, INTERVAL 5 DAY), 3.50),         -- Expiring soon, stock > 50
('Bread', 2, 20, DATE_ADD(CURRENT_DATE, INTERVAL 3 DAY), 2.00),         -- Expiring soon, stock <= 50
('Apples', 3, 100, DATE_ADD(CURRENT_DATE, INTERVAL 14 DAY), 1.50),       -- Expiring later
('Yogurt', 1, 80, DATE_ADD(CURRENT_DATE, INTERVAL 6 DAY), 4.00),         -- Expiring soon, stock > 50
('Orange Juice', 4, 30, DATE_ADD(CURRENT_DATE, INTERVAL 30 DAY), 5.00),  -- Not expiring soon
('Stale Chips', 2, 100, DATE_ADD(CURRENT_DATE, INTERVAL 100 DAY), 2.00); -- Dead stock (no sales)

-- Insert sample sales transactions
-- Note: 'Milk' and 'Yogurt' have recent sales. 'Stale Chips' has none.
INSERT INTO SalesTransactions (ProductID, QuantitySold, SaleDate, TotalPrice) VALUES
(1, 10, DATE_SUB(CURRENT_DATE, INTERVAL 5 DAY), 35.00),
(2, 5, DATE_SUB(CURRENT_DATE, INTERVAL 10 DAY), 10.00),
(3, 20, DATE_SUB(CURRENT_DATE, INTERVAL 15 DAY), 30.00),
(4, 15, DATE_SUB(CURRENT_DATE, INTERVAL 20 DAY), 60.00),
(5, 5, DATE_SUB(CURRENT_DATE, INTERVAL 2 DAY), 25.00),
(1, 20, DATE_SUB(CURRENT_DATE, INTERVAL 35 DAY), 70.00); -- Older sale


-- 2. The "Expiring Soon" Query
-- Write a query to find all products where the ExpiryDate is within the next 7 days but StockCount is greater than 50.
SELECT 
    ProductID, 
    ProductName, 
    StockCount, 
    ExpiryDate 
FROM 
    Products 
WHERE 
    ExpiryDate BETWEEN CURRENT_DATE AND DATE_ADD(CURRENT_DATE, INTERVAL 7 DAY)
    AND StockCount > 50;


-- 3. The "Dead Stock" Analysis
-- Identify products that exist in the Products table but have zero entries in the SalesTransactions table for the last 2 months.
SELECT 
    ProductID, 
    ProductName 
FROM 
    Products 
WHERE 
    ProductID NOT IN (
        SELECT ProductID 
        FROM SalesTransactions 
        WHERE SaleDate >= DATE_SUB(CURRENT_DATE, INTERVAL 2 MONTH)
    );


-- 4. Revenue Contribution
-- Write a query using SUM and GROUP BY to show which Category generated the most revenue last month.
SELECT 
    c.CategoryName, 
    SUM(st.TotalPrice) AS TotalRevenue
FROM 
    Categories c
JOIN 
    Products p ON c.CategoryID = p.CategoryID
JOIN 
    SalesTransactions st ON p.ProductID = st.ProductID
WHERE 
    st.SaleDate >= DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH)
GROUP BY 
    c.CategoryName
ORDER BY 
    TotalRevenue DESC
LIMIT 1;
