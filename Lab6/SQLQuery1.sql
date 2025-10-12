-- =============================================
-- XÓA CSDL CŨ (NẾU CÓ)
-- =============================================
IF DB_ID('WebShop') IS NOT NULL
    DROP DATABASE WebShop;
GO

-- =============================================
-- TẠO CSDL MỚI
-- =============================================
CREATE DATABASE WebShop;
GO

USE WebShop;
GO

-- =============================================
-- BẢNG CATEGORIES
-- =============================================
CREATE TABLE Categories (
    Id NVARCHAR(10) NOT NULL PRIMARY KEY,
    Name NVARCHAR(100) NOT NULL
);
GO

-- =============================================
-- BẢNG PRODUCTS
-- =============================================
CREATE TABLE Products (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    Name NVARCHAR(100) NOT NULL,
    Image NVARCHAR(255),
    Price DECIMAL(18,2),
    CreateDate DATE DEFAULT GETDATE(),
    Available BIT DEFAULT 1,
    CategoryId NVARCHAR(10) NOT NULL,
    FOREIGN KEY (CategoryId) REFERENCES Categories(Id)
);
GO

-- =============================================
-- BẢNG ACCOUNTS
-- =============================================
CREATE TABLE Accounts (
    Username NVARCHAR(50) NOT NULL PRIMARY KEY,
    Password NVARCHAR(100) NOT NULL,
    Fullname NVARCHAR(100),
    Email NVARCHAR(100),
    Photo NVARCHAR(255),
    Activated BIT DEFAULT 1,
    Admin BIT DEFAULT 0
);
GO

-- =============================================
-- BẢNG ORDERS
-- =============================================
CREATE TABLE Orders (
    Id BIGINT IDENTITY(1,1) PRIMARY KEY,
    Address NVARCHAR(255),
    CreateDate DATE DEFAULT GETDATE(),
    Username NVARCHAR(50) NOT NULL,
    FOREIGN KEY (Username) REFERENCES Accounts(Username)
);
GO

-- =============================================
-- BẢNG ORDER DETAILS
-- =============================================
CREATE TABLE OrderDetails (
    Id BIGINT IDENTITY(1,1) PRIMARY KEY,
    Price DECIMAL(18,2),
    Quantity INT CHECK (Quantity > 0),
    ProductId INT NOT NULL,
    OrderId BIGINT NOT NULL,
    FOREIGN KEY (ProductId) REFERENCES Products(Id),
    FOREIGN KEY (OrderId) REFERENCES Orders(Id)
);
GO

-- =============================================
-- DỮ LIỆU MẪU
-- =============================================

-- CATEGORIES
INSERT INTO Categories (Id, Name) VALUES
('1000', N'Đồng hồ đeo tay'),
('1001', N'Máy tính xách tay'),
('1002', N'Máy ảnh'),
('1003', N'Điện thoại'),
('1004', N'Nước hoa'),
('1005', N'Nữ trang'),
('1006', N'Nón thời trang'),
('1007', N'Túi xách du lịch');
GO

-- PRODUCTS
INSERT INTO Products (Name, Image, Price, Available, CategoryId) VALUES
(N'Casio MTP-V002', 'watch1.jpg', 850000, 1, '1000'),
(N'Laptop Asus Vivobook', 'laptop1.jpg', 14500000, 1, '1001'),
(N'Canon EOS M50', 'camera1.jpg', 12500000, 1, '1002'),
(N'iPhone 15 Pro', 'phone1.jpg', 28900000, 1, '1003'),
(N'Nước hoa Dior Sauvage', 'perfume1.jpg', 3100000, 1, '1004'),
(N'Dây chuyền bạc nữ', 'jewel1.jpg', 450000, 1, '1005'),
(N'Nón lưỡi trai Adidas', 'cap1.jpg', 250000, 1, '1006'),
(N'Túi xách LV Mini', 'bag1.jpg', 9900000, 1, '1007');
GO

-- ACCOUNTS
INSERT INTO Accounts (Username, Password, Fullname, Email, Photo, Activated, Admin) VALUES
('admin', '123', N'Quản trị viên', 'admin@shop.vn', 'admin.jpg', 1, 1),
('khanh', '123', N'Nguyễn Khánh', 'khanh@gmail.com', 'khanh.jpg', 1, 0),
('an', '123', N'Bạn An', 'an@gmail.com', 'an.jpg', 1, 0);
GO

-- ORDERS
INSERT INTO Orders (Address, Username) VALUES
(N'123 Nguyễn Văn Linh, Q.7, TP.HCM', 'khanh'),
(N'45 Lê Lợi, Q.1, TP.HCM', 'an');
GO

-- ORDER DETAILS
INSERT INTO OrderDetails (Price, Quantity, ProductId, OrderId) VALUES
(850000, 2, 1, 1),      -- 2 đồng hồ
(28900000, 1, 4, 1),    -- 1 iPhone
(250000, 3, 7, 2);      -- 3 nón Adidas
GO

-- =============================================
-- KIỂM TRA DỮ LIỆU
-- =============================================


SELECT * FROM Products;
SELECT * FROM Accounts;
SELECT * FROM Orders;
SELECT * FROM OrderDetails;
GO
