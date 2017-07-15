#1

CREATE TABLE `passports` (
	passport_id INT PRIMARY KEY auto_increment,
    passport_number VARCHAR(8) NOT NULL    
);

CREATE TABLE `persons` (
	person_id INT PRIMARY KEY auto_increment,
    first_name VARCHAR(50),
    salary decimal(7,2),
    passport_id INT NOT NULL UNIQUE,
    CONSTRAINT fk_persons_passports 
    FOREIGN KEY(passport_id)
    REFERENCES passports(passport_id)
);

INSERT INTO `passports`(passport_id, passport_number) VALUES
(101, 'N34FG21B'),
(102, 'K65LO4R7'),
(103, 'ZE657QP2');

INSERT INTO `persons`(person_id, first_name, salary, passport_id)
VALUES
(1, 'Roberto', 43300.00, 102),
(2, 'Tom', 56100.00, 103),
(3, 'Yana', 60200.00, 101);

#2
CREATE TABLE `manufacturers` (
	manufacturer_id INT PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
    name VARCHAR(55),
    established_on DATE
);

CREATE TABLE `models` (
	model_id INT PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
    name VARCHAR(255),
    manufacturer_id INT,
    CONSTRAINT fk_models_manufacturers
    FOREIGN KEY(manufacturer_id)
    REFERENCES manufacturers(manufacturer_id)
);

ALTER TABLE `models` AUTO_INCREMENT=101;

INSERT INTO manufacturers(`name`, `established_on`)
VALUES
('BMW', '1916/03/01'),
('Tesla', '2003/01/01'),
('Lada', '1966/05/01');


INSERT INTO models(`name`, `manufacturer_id`)
VALUES
('X1', 1),
('i6', 1),
('Model S', 2),
('Model X', 2),
('Model 3', 2),
('Nova', 3);

#3

CREATE TABLE `students` (
	student_id INT PRIMARY KEY AUTO_INCREMENT UNIQUE NOT NULL,
    name VARCHAR(50)
);

CREATE TABLE `exams` (
	exam_id INT PRIMARY KEY AUTO_INCREMENT UNIQUE NOT NULL,
    name VARCHAR(50)
);

ALTER TABLE `exams` AUTO_INCREMENT=101;

CREATE TABLE students_exams(
	student_id INT,
    exam_id INT,
    CONSTRAINT pk_students_exams
    PRIMARY KEY(student_id, exam_id),
    CONSTRAINT fk_students_exams_students
    FOREIGN KEY(student_id)
    REFERENCES students(student_id),
    CONSTRAINT fk_students_exams_exams 
    FOREIGN KEY (exam_id) 
    REFERENCES exams(exam_id)    
);

INSERT INTO students(`name`)
VALUES
('Mila'),
('Toni'),
('Ron');

INSERT INTO exams(`name`)
VALUES
('Spring MVC'),
('Neo4j'),
('Oracle 11g');

INSERT INTO students_exams(`student_id`, `exam_id`)
VALUES
(1, 101),
(1, 102),
(2, 101),
(3, 103),
(2, 102),
(2, 103);

#4

CREATE TABLE `teachers` (
	teacher_id INT PRIMARY KEY UNIQUE NOT NULL,
    name VARCHAR(50) NOT NULL,
    manager_id INT,
    CONSTRAINT fk_teachers_teachers FOREIGN KEY(manager_id) 
    REFERENCES teachers(teacher_id)
);


INSERT INTO `teachers`(teacher_id, name, manager_id) VALUES
(101, 'John', NULL),
(105, 'Mark', 101),
(106, 'Greta', 101),
(102,'Maya', 106),
(103, 'Silvia', 106),
(104, 'Ted', 105);

#5
CREATE DATABASE `online_store`;
USE `online_store`;

CREATE TABLE `item_types` (
	item_type_id INT(11) PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
    name VARCHAR(50)
);

CREATE TABLE `items` (
	item_id INT(11) PRIMARY KEY UNIQUE NOT NULL AUTO_INCREMENT,
    name VARCHAR(50),
    item_type_id INT (11),
    CONSTRAINT fk_items_item_types 
    FOREIGN KEY(item_type_id) 
    REFERENCES item_types(item_type_id)
);

CREATE TABLE `cities` (
	city_id INT(11) PRIMARY KEY UNIQUE NOT NULL AUTO_INCREMENT,
    name VARCHAR(50)
);

CREATE TABLE `orders` (
	order_id INT(11) PRIMARY KEY,
    customer_id INT(11)
);

CREATE TABLE `order_items` (
	order_id INT(11),
    item_id INT(11),
    PRIMARY KEY(order_id,item_id),
    CONSTRAINT fk_order_items_items
    FOREIGN KEY (item_id)
    REFERENCES items(item_id),
    CONSTRAINT fk_order_items_orders
    FOREIGN KEY (order_id) 
    REFERENCES orders(order_id)
);

CREATE TABLE `customers` (
	customer_id INT(11) PRIMARY KEY,
    name VARCHAR(50),
    birthday DATE,
    city_id INT(11),
    CONSTRAINT fk_customers_cities
    FOREIGN KEY (city_id)
    REFERENCES cities(city_id)
);

ALTER TABLE `orders` 
ADD CONSTRAINT fk_orders_customers
FOREIGN KEY (customer_id)
REFERENCES customers(customer_id);

#6
CREATE DATABASE `university`;
USE `university`;

CREATE TABLE `subjects` (
	subject_id INT(11) PRIMARY KEY,
    subject_name VARCHAR(50)
);


CREATE TABLE `majors` (
	major_id INT(11) PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE `students` (
	student_id INT(11) PRIMARY KEY,
    student_number VARCHAR(12),
    student_name VARCHAR(50),
    major_id INT(11),
    CONSTRAINT fk_students_majors
	FOREIGN KEY (major_id)
    REFERENCES majors(major_id)
);

CREATE TABLE `payments` (
	payment_id INT(11) PRIMARY KEY,
    payment_date DATE,
    payment_amount DECIMAL(8,2),
    student_id INT(11),
    CONSTRAINT fk_payments_students
	FOREIGN KEY (student_id)
    REFERENCES students(student_id)
);

CREATE TABLE `agenda` (
	student_id INT(11),
    subject_id INT(11),
    PRIMARY KEY(student_id,subject_id),
    CONSTRAINT fk_agenda_subjects
    FOREIGN KEY (subject_id)
    REFERENCES subjects(subject_id),
    CONSTRAINT fk_agenda_students
    FOREIGN KEY (student_id)
    REFERENCES students(student_id)
);

#9
SELECT m.mountain_range, p.peak_name, p.elevation FROM peaks AS p
JOIN mountains AS m ON m.id=p.mountain_id
WHERE m.mountain_range='Rila'
ORDER BY p.elevation DESC; 
