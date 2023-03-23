--Create table user--
CREATE TABLE user (
	id int(2) AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50),
    license_number VARCHAR(12),
    user_name VARCHAR(20),
    password VARCHAR(100)
);



