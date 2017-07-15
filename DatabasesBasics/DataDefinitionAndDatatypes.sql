#2
CREATE TABLE minions (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50),
	age INT
);

CREATE TABLE towns (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50)
);

#3
ALTER TABLE minions
ADD COLUMN town_id INT;

ALTER TABLE minions
ADD CONSTRAINT FK_MinionsTowns
FOREIGN KEY (town_id) REFERENCES towns(id);

#4
INSERT INTO towns(id,name) VALUES (1,'Sofia'),(2,'Plovdiv'),(3,'Varna');

INSERT INTO minions(id,name,age,town_id)
VALUES (1,'Kevin',22,1),(2,'Bob',15,3),(3,'Steward',NULL,2);

#7
CREATE TABLE people (
	id INT AUTO_INCREMENT UNIQUE,
	name VARCHAR(200) NOT NULL,
	picture BLOB(200000),
	height FLOAT(10,2),
	weight FLOAT(10,2),
	gender TINYINT NOT NULL,
	birthdate DATE NOT NULL,
	biography TEXT	
);

INSERT INTO people(name, gender, birthdate) VALUES
('Kalin', 1, '1992-01-18'),('stegan', 1, '1992-01-18'),('Pesho', 1, '1992-01-18'),
('Tosho', 1, '1992-01-18'),('Gosho', 1, '1992-01-18');

#8
CREATE TABLE users (
	id INT AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(30) NOT NULL,
	password VARCHAR(26) NOT NULL,
	profile_picture BLOB(90000),
	last_login_time TIMESTAMP,
	is_deleted BOOL
);

INSERT users(username, password, is_deleted)
VALUES ('roflcopter123', 'password1', false);
 
INSERT users(username, password, is_deleted)
VALUES ('randomuser', 'password123', false);
 
INSERT users(username, password, is_deleted)
VALUES ('milanisnoob', 'isuck.com', false);
 
INSERT users(username, password, is_deleted)
VALUES ('imthirsty', 'beargrills', false);
 
INSERT users(username, password, is_deleted)
VALUES ('wtfisgoingon', 'slashcare', false);

#9
ALTER TABLE users
DROP PRIMARY KEY,
ADD CONSTRAINT pk_users PRIMARY KEY (`id`, `username`);

#10
ALTER TABLE users MODIFY users.last_login_time 
TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

#11
ALTER TABLE users
DROP PRIMARY KEY,
ADD CONSTRAINT pk_users PRIMARY KEY (id);

ALTER TABLE users
ADD UNIQUE (username);

#12
CREATE TABLE directors
(`id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
`director_name` VARCHAR(50) NOT NULL,
`notes` TEXT NOT NULL);
 
CREATE TABLE genres
(`id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
`genre_name` VARCHAR(50) NOT NULL,
`notes` TEXT NOT NULL);
 
CREATE TABLE categories
(`id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
`category_name` VARCHAR(50) NOT NULL,
`notes` TEXT NOT NULL);
 
CREATE TABLE movies
(`id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
`title` VARCHAR(50) NOT NULL,
`director_id` INT NOT NULL,
`copyright_year` DATE NOT NULL,
`length` DOUBLE(8,2) NOT NULL,
`genre_id` INT NOT NULL,
`category_id` INT NOT NULL,
`rating` INT NOT NULL,
`notes` TEXT NOT NULL);
 
INSERT INTO directors(`director_name`, `notes`)
VALUES ('Georgi', 'Some text'),
('Ivan', 'Some text'),
('Aneta', 'Some text'),
('Daniela', 'Some text'),
('Petar', 'Some text');
 
INSERT INTO categories(`category_name`, `notes`)
VALUES ('Crimi', 'Some text'),
('Adventure', 'Some text'),
('Comedy', 'Some text'),
('Action', 'Some text'),
('Drama', 'Some text');
 
INSERT INTO genres(`genre_name`, `notes`)
VALUES ('Crimi', 'Some text'),
('Adventure', 'Some text'),
('Comedy', 'Some text'),
('Action', 'Some text'),
('Drama', 'Some text');
 
INSERT INTO movies(`title`, `director_id`, `copyright_year`, `length`, `genre_id`, `category_id`, `rating`, `notes`)
VALUES('Movie 1', 1, CURDATE(), 2.22, 2, 3, 10, 'Some text'),
('Movie 2', 2, CURDATE(), 3.32, 1, 2, 9, 'Some text'),
('Movie 3', 3, CURDATE(), 4.52, 5, 1, 8, 'Some text'),
('Movie 4', 4, CURDATE(), 6.72, 4, 5, 7, 'Some text'),
('Movie 1', 5, CURDATE(), 9.22, 3, 4, 6, 'Some text');

#13
CREATE TABLE categories
(`id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
`category` VARCHAR(50) NOT NULL,
`daily_rate` DOUBLE(4,2) NOT NULL,
`weekly_rate` DOUBLE(4,2) NOT NULL,
`monthly_rate` DOUBLE(4,2) NOT NULL,
`weekend_rate` DOUBLE(4,2) NOT NULL);
 
CREATE TABLE cars
(`id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
`plate_number` VARCHAR(50) NOT NULL,
`make` VARCHAR(50) NOT NULL,
`model` VARCHAR(50) NOT NULL,
`car_year` DATE NOT NULL,
`category_id` INT NOT NULL,
`doors` INT NOT NULL,
`picture` BLOB,
`car_condition` TEXT,
`available` TINYINT(1) NOT NULL);
 
CREATE TABLE employees
(`id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(50) NOT NULL,
`last_name` VARCHAR(50) NOT NULL,
`title` VARCHAR(50),
`notes` TEXT);
 
CREATE TABLE customers
(`id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
`driver_licence_number` VARCHAR(50) NOT NULL,
`full_name` VARCHAR(50) NOT NULL,
`address` TEXT NOT NULL,
`city` VARCHAR(50) NOT NULL,
`zip_code` VARCHAR(50) NOT NULL,
`notes` TEXT);
 
CREATE TABLE rental_orders
(`id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
`employee_id` INT NOT NULL,
`customer_id` INT NOT NULL,
`car_id` INT NOT NULL,
`car_condition` TEXT NOT NULL,
`tank_level` VARCHAR(50) NOT NULL,
`kilometrage_start` INT NOT NULL,
`kilometrage_end` INT NOT NULL,
`total_kilometrage` INT NOT NULL,
`start_date` DATE NOT NULL,
`end_date` DATE NOT NULL,
`total_days` INT NOT NULL,
`rate_applied` DOUBLE(4,2) NOT NULL,
`tax_rate` DOUBLE(4,2) NOT NULL,
`order_status` VARCHAR(50) NOT NULL,
`notes` TEXT);

 
INSERT INTO categories (`category`, `daily_rate`, `weekly_rate`, `monthly_rate`, `weekend_rate`)
VALUES('Crimi', 2.44, 3.55, 5.33, 10.22),
('Adventure', 5.44, 2.55, 8.33, 11.22),
('Action', 8.44, 13.55, 25.33, 13.22);
 
INSERT INTO cars (`plate_number`, `make`, `model`, `car_year`, `category_id`, `doors`, `picture`, `car_condition`, `available`)
VALUES('Some Number', 'Some text', 'BMW', '2009-05-12', 1, 4, NULL, 'Some text', 1),
('Some Number', 'Some text', 'Mercedes', '2008-06-10', 2, 3, NULL, 'Some text', 1),
('Some Number', 'Some text', 'VW', '2007-01-08', 3, 5, NULL, 'Some text', 0);
 
INSERT INTO employees (`first_name`, `last_name`, `title`, `notes`)
VALUES('Georgi', 'Stalev', 'seller', 'Some text'),
('Ivan', 'Stalev', 'customer suport', 'Some text'),
('Aneta', 'Moleva', 'human resurces', 'Some text');
 
INSERT INTO customers (`driver_licence_number`, `full_name`, `address`, `city`, `zip_code`, `notes`)
VALUES('Some Number', 'Vladislav Boichev', 'Some address', 'Some city', 'Some code', 'Some text'),
('Some Number', 'Dimitar Vasilev', 'Some address', 'Some city', 'Some code', 'Some text'),
('Some Number', 'Yanislav Marinov', 'Some address', 'Some city', 'Some code', 'Some text');
 
INSERT INTO rental_orders (`employee_id`, `customer_id`, `car_id`, `car_condition`, `tank_level`, `kilometrage_start`, `kilometrage_end`, `total_kilometrage`, `start_date`, `end_date`, `total_days`, `rate_applied`, `tax_rate`, `order_status`, `notes`)
VALUES(1, 1, 1, 'Some text', 'EMPTY', 10, 200, 250, '2016-05-05', '2017-05-05', 25, 24.45, 25.44, 'Available', 'Some text'),
(2, 2, 2, 'Some text', 'EMPTY', 5, 150, 200, '2014-05-05', '2015-05-05', 45, 55.22, 33.33, 'Available', 'Some text'),
(3, 3, 3, 'Some text', 'EMPTY', 20, 100, 150, '2015-05-05', '2016-05-05', 26, 27.49, 21.42, 'Available', 'Some text');

#14
CREATE TABLE employees (
	id INT PRIMARY KEY AUTO_INCREMENT,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	title VARCHAR(10),
	notes text
);

CREATE TABLE customers (
	account_number INT PRIMARY KEY AUTO_INCREMENT,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	phone_number VARCHAR(10) NOT NULL,
	emergency_name VARCHAR(50) NOT NULL,
	emergency_number VARCHAR(50) NOT NULL,
	notes TEXT
);

CREATE TABLE room_status (
	room_status VARCHAR(50) PRIMARY KEY NOT NULL,
	notes TEXT
);

CREATE TABLE room_types (
	room_type VARCHAR(50) PRIMARY KEY NOT NULL,
	notes TEXT
);

CREATE TABLE bed_types (
	bed_type VARCHAR(50) PRIMARY KEY NOT NULL,
	notes TEXT
);

CREATE TABLE rooms (
	room_number INT PRIMARY KEY NOT NULL,
	room_type VARCHAR(60) NOT NULL,
	bed_type VARCHAR(60),
	rate DOUBLE(8,2),
	room_status VARCHAR(20),
	notes TEXT
);

CREATE TABLE payments (
	id INT AUTO_INCREMENT PRIMARY KEY,
	employee_id INT NOT NULL,
	payment_date DATETIME,
	account_number VARCHAR(50) NOT NULL,
	first_date_occupied TIMESTAMP,
	last_date_occupied TIMESTAMP,
	total_days INT(5),
	amount_charged DOUBLE(8,2),
	tax_rate DOUBLE(8,2),
	tax_amount DOUBLE(8,2),
	payment_total INT,
	notes TEXT
);

CREATE TABLE occupancies (
	id INT AUTO_INCREMENT PRIMARY KEY,
	employee_id INT NOT NULL,
	date_occupied DATETIME,
	account_number VARCHAR(50) NOT NULL,
	room_number INT(4),
	rate_applied DOUBLE(8,2),
	phone_charge DOUBLE(8,2),
	notes TEXT
);

INSERT INTO employees(first_name, last_name, title) 
VALUES ('Gosho', 'Goshov', 'Mr.'),
('Gosho1', 'Goshov1', 'Mr.'),
('Gosho1', 'Goshov1', 'Mr.');

INSERT INTO customers(account_number, first_name, last_name, phone_number, emergency_name, emergency_number)
VALUES (1,'Gosho', 'Goshov', '23512321', 'help', 'help'),
(2,'Gosho1', 'Goshov1','23512321', 'help', 'help'),
(3,'Gosho1', 'Goshov1','23512321', 'help', 'help');

INSERT INTO room_status(room_status.room_status)
VALUES ('free'),
('freer'),
('freest');

INSERT INTO room_types(room_type)
VALUES ('free'),
('freer'),
('freest');

INSERT INTO bed_types(bed_type)
VALUES ('free'),
('freer'),
('freest');

INSERT INTO rooms(room_number, room_type) 
VALUES (1,'free'),
(2,'freer'),
(3,'freest');

INSERT INTO payments(employee_id, account_number) 
VALUES (1,'free'),
(2,'freer'),
(3,'freest');

INSERT INTO occupancies(employee_id, account_number) 
VALUES (1,'free'),
(2,'freer'),
(3,'freest');

#15
CREATE TABLE employees (
	id INT PRIMARY KEY AUTO_INCREMENT,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	title VARCHAR(10),
	notes text
);

CREATE TABLE customers (
	account_number INT PRIMARY KEY AUTO_INCREMENT,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	phone_number VARCHAR(10) NOT NULL,
	emergency_name VARCHAR(50) NOT NULL,
	emergency_number VARCHAR(50) NOT NULL,
	notes TEXT
);

CREATE TABLE room_status (
	room_status VARCHAR(50) PRIMARY KEY NOT NULL,
	notes TEXT
);

CREATE TABLE room_types (
	room_type VARCHAR(50) PRIMARY KEY NOT NULL,
	notes TEXT
);

CREATE TABLE bed_types (
	bed_type VARCHAR(50) PRIMARY KEY NOT NULL,
	notes TEXT
);

CREATE TABLE rooms (
	room_number INT PRIMARY KEY NOT NULL,
	room_type VARCHAR(60) NOT NULL,
	bed_type VARCHAR(60),
	rate DOUBLE(8,2),
	room_status VARCHAR(20),
	notes TEXT
);

CREATE TABLE payments (
	id INT AUTO_INCREMENT PRIMARY KEY,
	employee_id INT NOT NULL,
	payment_date DATETIME,
	account_number VARCHAR(50) NOT NULL,
	first_date_occupied TIMESTAMP,
	last_date_occupied TIMESTAMP,
	total_days INT(5),
	amount_charged DOUBLE(8,2),
	tax_rate DOUBLE(8,2),
	tax_amount DOUBLE(8,2),
	payment_total INT,
	notes TEXT
);

CREATE TABLE occupancies (
	id INT AUTO_INCREMENT PRIMARY KEY,
	employee_id INT NOT NULL,
	date_occupied DATETIME,
	account_number VARCHAR(50) NOT NULL,
	room_number INT(4),
	rate_applied DOUBLE(8,2),
	phone_charge DOUBLE(8,2),
	notes TEXT
);

INSERT INTO employees(first_name, last_name, title) 
VALUES ('Gosho', 'Goshov', 'Mr.'),
('Gosho1', 'Goshov1', 'Mr.'),
('Gosho1', 'Goshov1', 'Mr.');

INSERT INTO customers(account_number, first_name, last_name, phone_number, emergency_name, emergency_number)
VALUES (1,'Gosho', 'Goshov', '23512321', 'help', 'help'),
(2,'Gosho1', 'Goshov1','23512321', 'help', 'help'),
(3,'Gosho1', 'Goshov1','23512321', 'help', 'help');

INSERT INTO room_status(room_status.room_status)
VALUES ('free'),
('freer'),
('freest');

INSERT INTO room_types(room_type)
VALUES ('free'),
('freer'),
('freest');

INSERT INTO bed_types(bed_type)
VALUES ('free'),
('freer'),
('freest');

INSERT INTO rooms(room_number, room_type) 
VALUES (1,'free'),
(2,'freer'),
(3,'freest');

INSERT INTO payments(employee_id, account_number) 
VALUES (1,'free'),
(2,'freer'),
(3,'freest');

INSERT INTO occupancies(employee_id, account_number) 
VALUES (1,'free'),
(2,'freer'),
(3,'freest');

#17
INSERT INTO towns(name) VALUES ('Sofia'), ('Plovdiv'), ('Varna'), ('Burgas');

INSERT INTO departments(name) VALUES
('Engineering'), ('Sales'), ('Marketing'), ('Software Development'), ('Quality Assurance');

INSERT INTO employees(first_name, middle_name, last_name, job_title, department_id, hire_date, salary) 
VALUES
('Ivan', 'Ivanov', 'Ivanov', '.NET Developer', 4, '2013/02/01', 3500.00),
('Petar', 'Petrov', 'Petrov', 'Senior Engineer', 1, '2004/03/02', 4000.00),
('Maria', 'Petrova', 'Ivanova', 'Intern', 5, '2016/08/28', 525.25),
('Georgi', 'Terziev', 'Ivanov', 'CEO', 2, '2007/12/09', 3000.00),
('Peter', 'Pan', 'Pan', 'Intern', 3, '2016/08/28', 599.88);

#18