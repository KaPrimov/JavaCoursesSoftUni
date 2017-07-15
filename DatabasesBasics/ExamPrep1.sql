#1

CREATE TABLE `airlines` (
	airline_id INT PRIMARY KEY,
    airline_name VARCHAR(30) NOT NULL,
    nationality VARCHAR(30) NOT NULL,
    rating INT DEFAULT 0
);

CREATE TABLE `flights` (
	flight_id INT PRIMARY KEY AUTO_INCREMENT,
    departure_time DATETIME NOT NULL,
    arrival_time DATETIME NOT NULL,
    status VARCHAR(9),
    origin_airport_id INT,
    destination_airport_id INT,
    airline_id INT,
	CONSTRAINT fk_flights_airlines
    FOREIGN KEY(airline_id) REFERENCES airlines(airline_id)
);

CREATE TABLE `towns` (
	town_id INT PRIMARY KEY NOT NULL,
    town_name VARCHAR(30) NOT NULL
);

CREATE TABLE `airports` (
	airport_id INT PRIMARY KEY,
    airport_name VARCHAR(50) NOT NULL,
    town_id INT,
    CONSTRAINT fk_airports_towns 
    FOREIGN KEY(town_id) REFERENCES towns(town_id)
);

CREATE TABLE `customers` (
	customer_id INT PRIMARY KEY,
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    date_of_birth DATE NOT NULL,
    gender VARCHAR(1),
    home_town_id INT,
    CONSTRAINT fk_customers_towns
    FOREIGN KEY(home_town_id) REFERENCES towns(town_id)
);

CREATE TABLE tickets (
	ticket_id INT PRIMARY KEY AUTO_INCREMENT,
    price DECIMAL(8,2) NOT NULL,
    class VARCHAR(6),
    seat VARCHAR(5) NOT NULL,
    customer_id INT,
    flight_id INT,
    CONSTRAINT fk_tickets_customers
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
    CONSTRAINT fk_tickets_flights
    FOREIGN KEY (flight_id) REFERENCES flights(flight_id)
);

ALTER TABLE `flights`
ADD CONSTRAINT FOREIGN KEY(origin_airport_id) REFERENCES airports(airport_id);
ALTER TABLE `flights`
ADD CONSTRAINT FOREIGN KEY(destination_airport_id) REFERENCES airports(airport_id);

#2 INSERT
INSERT INTO `flights` (departure_time, arrival_time, status, origin_airport_id, destination_airport_id, airline_id)
SELECT 
'2017-06-19 14:00:00' AS `departure_time`,
'2017-06-21 11:00:00' AS `arrival_time`,
CASE
	WHEN airline_id % 4 = 0 THEN 'Departing'
    WHEN airline_id % 4 = 1 THEN 'Delayed'
    WHEN airline_id % 4 = 2 THEN 'Arrived'
    WHEN airline_id % 4 = 3 THEN 'Canceled'
END AS `status`,
CEIL(sqrt(char_length(`airline_name`))) AS `origin_airport_id`,
ceil(sqrt(char_length(`nationality`))) AS `destination_airport_id`,
`airline_id` AS `airline_id`
FROM airlines AS a
WHERE a.airline_id BETWEEN 1 AND 10;

#3
UPDATE flights AS f SET f.airline_id=1 
WHERE f.status='Arrived'; 

#4
UPDATE `tickets` SET price=price*1.5
WHERE `flight_id`= (
	SELECT f.flight_id FROM `airlines` AS `a`
    INNER  JOIN `flights` AS f
    ON f.airline_id=a.airline_id
    ORDER BY a.rating DESC
    LIMIT 1
);

#5
SELECT t.ticket_id, t.price, t.class, t.seat FROM tickets AS t
ORDER BY t.ticket_id;

#6
SELECT c.customer_id, concat(c.first_name, ' ', c.last_name) AS 'full_name', c.gender FROM customers AS c
ORDER BY full_name, gender;    

#7
SELECT f.flight_id, f.departure_time, f.arrival_time FROM flights AS f
WHERE f.status='Delayed'
ORDER BY f.flight_id;

#8
SELECT a.airline_id, a.airline_name, a.nationality, a.rating FROM airlines AS a
WHERE a.airline_id IN (SELECT f.airline_id FROM flights AS f)
ORDER BY a.rating DESC
LIMIT 5;

#9
SELECT t.ticket_id, a.airport_name AS 'destination',
 concat(c.first_name, ' ', c.last_name) AS 'customer_name'
 FROM tickets AS t
INNER JOIN customers AS c
ON t.customer_id=c.customer_id
INNER JOIN flights AS f
ON t.flight_id=f.flight_id
INNER JOIN airports AS a ON
a.airport_id=f.destination_airport_id
WHERE t.price<5000 AND t.class='First'
ORDER BY t.ticket_id;

#10
SELECT DISTINCT
    c.customer_id,
    CONCAT(c.first_name, ' ', c.last_name) AS 'full_name',
    tow.town_name AS 'home_town'
FROM
    customers AS c
    INNER JOIN tickets AS t
    ON t.customer_id=c.customer_id
    INNER JOIN flights AS f
    ON f.flight_id=t.flight_id
    INNER JOIN airports AS a ON
    a.airport_id=f.origin_airport_id
    INNER JOIN towns AS tow
    ON tow.town_id=a.town_id
WHERE c.home_town_id=tow.town_id AND f.status='Departing'
ORDER BY c.customer_id;

#11
SELECT DISTINCT
    c.customer_id,
    CONCAT(c.first_name, ' ', c.last_name) AS 'full_name',
    (2016-year(c.date_of_birth)) AS 'age'    
FROM
    customers AS c
        INNER JOIN
    tickets AS t ON t.customer_id = c.customer_id
        INNER JOIN
    flights AS f ON f.flight_id = t.flight_id
WHERE
    f.status = 'Departing'
ORDER BY `age`, c.customer_id;

#12
SELECT
    c.customer_id,
    CONCAT(c.first_name, ' ', c.last_name) AS 'full_name',
    t.price AS 'ticket_price',
    a.airport_name AS 'destination'
FROM
    customers AS c
    INNER JOIN tickets AS t
    ON t.customer_id=c.customer_id
    INNER JOIN flights AS f
    ON f.flight_id=t.flight_id
	INNER JOIN airports AS a
    ON a.airport_id=f.destination_airport_id
WHERE f.status='Delayed'
ORDER BY t.price DESC, c.customer_id
LIMIT 3;

#13
SELECT
x.flight_id, x.departure_time, x.arrival_time,
x.origin, x.destination
 FROM
(SELECT 
    f.flight_id, f.departure_time, f.arrival_time,
    oa.airport_name AS `origin`,
    da.airport_name AS `destination`
FROM
    flights AS f
        INNER JOIN
    airports AS oa ON oa.airport_id = f.origin_airport_id
		INNER JOIN
	airports AS da ON da.airport_id=f.destination_airport_id
WHERE f.status='Departing'
ORDER BY f.departure_time DESC
LIMIT 5) AS x
ORDER BY x.departure_time, x.flight_id;

#14
SELECT DISTINCT
    c.customer_id,
    CONCAT(c.first_name, ' ', c.last_name) AS 'full_name',
    (2016 - YEAR(c.date_of_birth)) AS 'age'
FROM
    customers AS c
    INNER JOIN tickets AS t
    ON t.customer_id=c.customer_id
    INNER JOIN flights AS f
    ON f.flight_id=t.flight_id
WHERE (2016 - YEAR(c.date_of_birth))<21 AND f.status='Arrived'
ORDER BY (2016 - YEAR(c.date_of_birth)) DESC, c.customer_id;

#15
SELECT 
    a.airport_id, a.airport_name, count(t.ticket_id) AS `passengers`
FROM
    `flights` AS `f`
        INNER JOIN
    `airports` AS `a` ON `a`.airport_id = `f`.origin_airport_id
        INNER JOIN
    `tickets` AS `t` ON `t`.`flight_id` = `f`.`flight_id`
    WHERE f.status='Departing'
    GROUP BY a.airport_id
    HAVING `passengers` > 0
    ORDER BY a.airport_id;

#16
DELIMITER $$
CREATE PROCEDURE udp_submit_review(c_id INT, r_content VARCHAR(255), r_grade INT, a_name VARCHAR(50))
BEGIN
	DECLARE air_id INT;
    set air_id := (SELECT `a`.`airline_id` FROM `airlines` AS `a` WHERE `a`.`airline_name`=a_name);
    IF (a_name NOT IN (SELECT `airline_name` FROM `airlines`)) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Airline does not exist';
	ELSE 
		INSERT INTO `customer_reviews`(`review_content`, `review_grade`, `airline_id`, `customer_id`)
        VALUE(r_content, r_grade, air_id, c_id);
	END IF;
END $$

#17
CREATE PROCEDURE udp_purchase_ticket(c_id INT, f_id INT, t_price DECIMAL(10,4), cl VARCHAR(6), se VARCHAR(5))
BEGIN
	DECLARE customer_current_balance DECIMAL(10,4);
    SET customer_current_balance:=(SELECT cba.balance FROM customer_bank_accounts cba WHERE cba.customer_id=c_id);
    IF (customer_current_balance < t_price) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Insufficient bank account balance for ticket purchase.';
	ELSE 
		INSERT INTO tickets(price,class,seat,customer_id,flight_id) 
        VALUES (t_price,cl,se,c_id,f_id);
        
        UPDATE customer_bank_accounts AS cba SET cba.balance = cba.balance-t_price
        WHERE cba.customer_id=c_id;
	END IF;
        
END $$

#18
DELIMITER $$
CREATE TRIGGER tr_update_flights
BEFORE UPDATE
ON flights
FOR EACH ROW
BEGIN
	DECLARE origin VARCHAR(50);
    DECLARE destination VARCHAR(50);
    DECLARE passengers INT;
    
    SET origin:=(SELECT a1.airport_name FROM airports AS a1 WHERE new.origin_airport_id=a1.airport_id);
    SET destination:=(SELECT a1.airport_name FROM airports AS a1 WHERE new.destination_airport_id=a1.airport_id);
    SET passengers := (SELECT count(t.ticket_id) FROM tickets AS t WHERE t.flight_id=new.flight_id );
    
    IF (new.status='Arrived' AND 
    old.status != 'Arrived' AND
    old.status != 'Cancelled' ) THEN
		INSERT INTO `arrived_flights`(`flight_id`,`arrival_time`,`origin`,`destination`,`passengers`)
			VALUES (new.flight_id, new.arrival_time, origin, destination, passengers);
	END IF;
END $$


