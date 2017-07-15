CREATE DATABASE nerd_herd;

USE `nerd_herd`;

#1
CREATE TABLE `credentials` (
	id INT PRIMARY KEY,
    email VARCHAR(30),
    password VARCHAR(20)
);

CREATE TABLE `locations` (
	id INT PRIMARY KEY,
    latitude FLOAT,
    longitude FLOAT
);

drop table users;
CREATE TABLE `users` (
	id INT PRIMARY KEY UNIQUE,
    nickname VARCHAR(25),
    gender CHAR(1),
    age INT,
    location_id INT,
    credential_id INT UNIQUE,
    CONSTRAINT `fk_users_locations`
    FOREIGN KEY(`location_id`)
    REFERENCES `locations`(`id`),
    CONSTRAINT `fk_users_credentials`
    FOREIGN KEY(`credential_id`) 
    REFERENCES `credentials`(`id`)
);

CREATE TABLE `chats` (
	id INT PRIMARY KEY,
    title VARCHAR(32),
    start_date DATE,
    is_active BIT
);

CREATE TABLE `messages` (
	id INT PRIMARY KEY AUTO_INCREMENT,
    content VARCHAR(200),
    sent_on DATE,
    chat_id INT,
    user_id INT,
    CONSTRAINT `fk_messages_chats`
    FOREIGN KEY(`chat_id`)
    REFERENCES `chats`(`id`),
    CONSTRAINT `fk_messages_users`
    FOREIGN KEY(`user_id`) 
    REFERENCES `users`(`id`)
);

CREATE TABLE `users_chats` (
	user_id INT,
    chat_id INT,
    CONSTRAINT `pk_users_chats`
    PRIMARY KEY(user_id, chat_id),
    CONSTRAINT `fk_users_chats_users`
    FOREIGN KEY(user_id) REFERENCES users(id),
    CONSTRAINT `fk_users_chats_chats`
    FOREIGN KEY(chat_id) REFERENCES chats(id)
);

#2
INSERT INTO messages(content, sent_on, chat_id, user_id)
SELECT
concat_ws('-', u.age, u.gender,l.latitude,l.longitude) AS `content`,
'2016-12-15' AS `sent_on`,
	CASE
		WHEN u.gender='F' THEN ceil(sqrt(u.age * 2))
        WHEN u.gender='M' THEN ceil(pow((u.age/18),3))
	END AS `chat_id`,
	u.id AS `user_id`
FROM users AS u
INNER JOIN locations AS l
ON l.id=u.location_id
WHERE u.id >= 10 AND u.id <= 20;

#3
UPDATE messages AS m
INNER JOIN chats AS c ON
c.id=m.chat_id
SET c.start_date = m.sent_on
WHERE m.sent_on < c.start_date;


UPDATE chats AS ch INNER JOIN (SELECT c .id, min(m.sent_on) AS min_date FROM chats AS c 
		INNER JOIN messages AS m
		ON m.chat_id=c.id
		WHERE c.start_date > m.sent_on
		GROUP BY c.id) AS min_dates
ON min_dates.id=ch.id
SET ch.start_date=min_dates.min_date;


#4
DELETE FROM locations
WHERE locations.id NOT IN (
	SELECT u.location_id FROM users AS u
    WHERE u.location_id IS NOT NULL
);

#5
SELECT u.nickname, u.gender, u.age FROM users AS u
WHERE u.age BETWEEN 22 AND 37;

#6
SELECT m.content, m.sent_on FROM messages AS m
WHERE m.sent_on > '2014-05-12' AND
m.content LIKE '%just%'
ORDER BY m.id DESC;

#7
SELECT c.title, c.is_active FROM chats AS c
WHERE (c.is_active = 0 AND length(c.title)<5)
OR c.title LIKE '__tl%'
ORDER BY c.title DESC;

#8
SELECT c.id, c.title, m.id FROM chats AS c
INNER JOIN messages AS m
ON m.chat_id=c.id
WHERE m.sent_on<'2012-03-26' AND right(c.title,1)='x'
ORDER BY c.id, m.id;

#9
SELECT c.id, count(m.id) AS `total_messages` FROM chats AS c
INNER JOIN messages AS m
ON m.chat_id=c.id
WHERE m.id<90
GROUP BY c.id
ORDER BY `total_messages` DESC, c.id
LIMIT 5;

#10
SELECT u.nickname, c.email, c.password FROM users AS u
INNER JOIN credentials AS c
ON c.id=u.credential_id
WHERE c.email LIKE '%co.uk'
ORDER BY c.email;

#11
SELECT u.id, u.nickname, u.age FROM users AS u
WHERE u.location_id IS NULL
ORDER BY u.id;

#12
SELECT m.id, m.chat_id, u.id FROM messages AS m
INNER JOIN chats AS c ON
c.id=m.chat_id
INNER JOIN users AS u
ON u.id=m.user_id
WHERE c.id = 17
AND u.id NOT IN (SELECT uc.user_id FROM users_chats AS uc
				WHERE uc.chat_id=17)
ORDER BY m.id DESC;

#13
SELECT u.nickname, c.title, l.latitude, l.longitude FROM users AS u
INNER JOIN locations AS l ON
l.id=u.location_id
INNER JOIN users_chats AS uc1
ON uc1.user_id=u.id
INNER JOIN chats AS c 
ON uc1.chat_id = c.id
WHERE (l.latitude BETWEEN  41.139999 AND 44.129999) 
AND (l.longitude BETWEEN 22.209999 AND 28.359999)
ORDER BY c.title;

#14
SELECT
	`c`.`title`,
    `m`.`content`
FROM
	(SELECT * FROM `chats` ORDER BY `start_date` DESC LIMIT 1) AS `c`
	LEFT OUTER JOIN
    `messages` AS `m`
    ON
    `m`.`chat_id` = `c`.`id`
		AND `m`.`sent_on` IS NOT NULL
		AND `m`.`sent_on` >= `c`.`start_date`
ORDER BY `m`.`sent_on` ASC;

#15
DELIMITER $$
CREATE FUNCTION udf_get_radians(degrees FLOAT)
RETURNS FLOAT
BEGIN
	RETURN (degrees * pi()) / 180;
END$$

#16
CREATE PROCEDURE udp_change_password(new_email VARCHAR(30), new_pass VARCHAR(20))
BEGIN

IF (new_email NOT IN (SELECT c.email FROM credentials AS c)) THEN
	SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT="The email does't exist!";
ELSE 
	UPDATE credentials SET password=new_pass WHERE email=new_email;
END IF;

END$$

#17
CREATE PROCEDURE udp_send_message(u_id INT, c_id INT, cont VARCHAR(200))
BEGIN
	IF (u_id NOT IN (SELECT uc.user_id FROM users_chats AS uc WHERE uc.chat_id=c_id)) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='There is no chat with that user!';
	ELSE 
		INSERT INTO messages(content, sent_on, chat_id, user_id)
        VALUES (cont, '2016-12-15', c_id, u_id);
	END IF;
END $$

#18 
DELIMITER ;
CREATE TABLE `messages_log` (
	id INT PRIMARY KEY AUTO_INCREMENT,
    content VARCHAR(200),
    sent_on DATE,
    chat_id INT,
    user_id INT    
);

DELIMITER $$
CREATE TRIGGER tr_log_messages
AFTER DELETE 
ON messages
FOR EACH ROW
BEGIN
	INSERT INTO `messages_log`(id,content, sent_on, chat_id, user_id)
    VALUES (old.id,old.content, old.sent_on, old.chat_id,old.user_id);
END$$

#19
DROP TRIGGER tr_delete_user
DELIMITER $$
CREATE TRIGGER tr_delete_user
BEFORE DELETE 
ON users
FOR EACH ROW
BEGIN
	DELETE FROM messages WHERE user_id=old.id;
    DELETE FROM users_chats WHERE user_id=old.id;
    DELETE FROM messages_log WHERE user_id=old.id;
END $$

DELIMITER ;
DELETE FROM users WHERE id=1;


