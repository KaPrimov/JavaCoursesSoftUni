#1
DELIMITER $$

CREATE PROCEDURE usp_get_employees_salary_above_35000()
BEGIN
SELECT e.first_name, e.last_name FROM employees AS e
WHERE e.salary > 35000
ORDER BY e.first_name, e.last_name;
END $$


#2
CREATE PROCEDURE usp_get_employees_salary_above(max_salary DECIMAL(19,4))
BEGIN
SELECT e.first_name, e.last_name FROM employees AS e
WHERE e.salary >= max_salary
ORDER BY e.first_name, e.last_name;
END $$

#3
DELIMITER $$
CREATE PROCEDURE usp_get_towns_starting_with (starts_with VARCHAR(20))
BEGIN
SELECT t.name FROM towns AS t
WHERE substring(t.name, 1, length(starts_with)) LIKE starts_with
ORDER BY t.name;
END $$

#4
DELIMITER $$
CREATE PROCEDURE usp_get_employees_from_town (town_name VARCHAR(50))
BEGIN
SELECT e.first_name, e.last_name FROM employees AS e
INNER JOIN addresses AS a
ON e.address_id=a.address_id
INNER JOIN towns AS t
ON t.town_id=a.town_id
WHERE t.name=town_name
ORDER BY e.first_name, e.last_name;
END $$

#5
DELIMITER $$
CREATE FUNCTION ufn_get_salary_level  (salary_to_compare VARCHAR(50))
RETURNS VARCHAR(10)
BEGIN
DECLARE salary_level VARCHAR(10);
IF(salary_to_compare < 30000) THEN
SET salary_level:='Low';
ELSEIF(salary_to_compare <=50000) THEN
SET salary_level := 'Average';
ELSE 
SET salary_level:='High';
END IF;
RETURN salary_level;
END $$

#6
DELIMITER $$
CREATE PROCEDURE usp_get_employees_by_salary_level(salary_level VARCHAR(10))
BEGIN
DECLARE lower_salary DECIMAL(12,4);
DECLARE upper_salary DECIMAL(15,4);
IF (lower(salary_level) = 'low') THEN
SET lower_salary:=0;
SET upper_salary:=29999;
ELSEIF(lower(salary_level) = 'average') THEN
SET lower_salary:=30000;
SET upper_salary:=50000;
ELSEIF(lower(salary_level) = 'high') THEN
SET lower_salary:=50001;
SET upper_salary:=1000000000;
END IF;

SELECT e.first_name, e.last_name FROM employees AS e
WHERE e.salary>=lower_salary AND e.salary<upper_salary
ORDER BY e.first_name DESC, e.last_name DESC;
END $$

#7
DELIMITER $$
CREATE FUNCTION ufn_is_word_comprised(set_of_letters VARCHAR(50), word VARCHAR(50))
RETURNS BOOL
BEGIN
	DECLARE word_index INT DEFAULT 1;
    DECLARE letter VARCHAR(1);
	WHILE (char_length(word) >= word_index) DO
		SET letter:= substring(word,word_index,1);        
        IF(locate(letter, set_of_letters) < 1) THEN
			RETURN FALSE;
		END IF;
        SET word_index=word_index + 1;
    END WHILE;
    RETURN TRUE;
END $$

#8
DELIMITER ;

CREATE TABLE IF NOT EXISTS `to_be_delete` 
	(SELECT `e`.`employee_id` FROM `employees` AS `e` 
		WHERE `e`.`department_id` IN (SELECT `d`.`department_id` 
			FROM `departments` AS `d` 
			WHERE  `d`.`name` IN ('Production', 'Production Control')));

UPDATE `employees` 
SET `manager_id` = NULL 
WHERE `manager_id` IN (SELECT * FROM `to_be_delete`);

UPDATE `departments` 
SET `manager_id` = 2 
WHERE `manager_id` IN (SELECT * FROM `to_be_delete`);

DELETE FROM `employees_projects` 
WHERE `employee_id` IN (SELECT * FROM `to_be_delete`);

DELETE FROM `employees` 
WHERE `employee_id` IN (SELECT * FROM `to_be_delete`);

DELETE FROM `departments` 
WHERE `name` IN ('Production', 'Production Control');

#9
DELIMITER $$
CREATE PROCEDURE usp_get_holders_full_name()
BEGIN

SELECT concat(a.first_name, ' ', a.last_name) AS 'full_name' FROM account_holders AS a
ORDER BY full_name;

END $$

#10
CREATE PROCEDURE usp_get_holders_with_balance_higher_than (min_balance DECIMAL(19,4))
BEGIN

SELECT ah.first_name, ah.last_name FROM account_holders AS ah
INNER JOIN accounts AS a ON a.account_holder_id=ah.id
GROUP BY ah.first_name, ah.last_name
HAVING SUM(a.balance) > min_balance;
END $$

#11
DELimiter $$
CREATE FUNCTION ufn_calculate_future_value(
	init_sum DECIMAL(19,4),
    rate DECIMAL(19,4),
    years DECIMAL
)
RETURNS DECIMAL(19,4)
BEGIN
DECLARE result DECIMAL(19,4);
SET result:=init_sum *(pow((1 + rate), years));
RETURN result;
END $$

#12
DELimiter $$
CREATE PROCEDURE usp_calculate_future_value_for_account(id INT, interest DECIMAL(12,2))
BEGIN
SELECT a.id AS 'account_id',
ah.first_name,
ah.last_name, 
sum(a.balance) AS 'current_balance',
ufn_calculate_future_value(sum(a.balance), interest, 5) AS 'balance_in_5_years'
FROM accounts AS a
INNER JOIN account_holders AS ah ON a.account_holder_id=ah.id
WHERE a.id=id;
END$$

#13
DELIMITER $$
CREATE PROCEDURE usp_deposit_money (account_id INT, money DECIMAL(19, 4))
BEGIN
	START TRANSACTION;
	UPDATE accounts SET accounts.balance = accounts.balance + money
    WHERE accounts.id=account_id;
    IF (money < 0) THEN
		ROLLBACK;
    ELSE
		COMMIT;
	END IF;
END $$

#14
DELIMITER $$
DROP PROCEDURE usp_withdraw_money $$
CREATE PROCEDURE usp_withdraw_money (account_id INT, money DECIMAL(19, 4))
BEGIN
	START TRANSACTION;
	UPDATE accounts AS a SET a.balance = a.balance - money
    WHERE a.id=account_id;
    IF (money < 0 OR (
		SELECT a2.balance FROM accounts AS a2
        WHERE a2.id=account_id
    ) < money) THEN
		ROLLBACK;
    ELSE
		COMMIT;
	END IF;
END $$


#15

DELIMITER $$
DROP PROCEDURE usp_transfer_money $$
CREATE PROCEDURE usp_transfer_money(from_id INT, to_id INT, amount DECIMAL(19,4))
BEGIN
	START TRANSACTION;
    UPDATE accounts SET accounts.balance = accounts.balance + amount
    WHERE accounts.id=to_id;
    UPDATE accounts AS a SET a.balance = a.balance - amount
    WHERE a.id=from_id;
    IF (from_id=to_id) THEN
		ROLLBACK;
	ELSEIF (from_id NOT IN (SELECT a.id FROM accounts AS a)) THEN
		ROLLBACK;
	ELSEIF (to_id NOT IN (SELECT a.id FROM accounts AS a)) THEN
		ROLLBACK;
	ELSEIF (amount < 0) THEN
		ROLLBACK;
	ELSEIF (
    (SELECT a2.balance FROM accounts AS a2
	WHERE a2.id=from_id) < amount ) THEN
		ROLLBACK;
	ELSE 
		COMMIT;
	END IF;
END $$ 


CALL usp_transfer_money(1, 20, 60) 

#16
DELIMITER ;
DROP TABLE logs;
CREATE TABLE `logs` (
	log_id INT PRIMARY KEY AUTO_INCREMENT,
    account_id INT,
    old_sum DECIMAL(19,4),
    new_sum DECIMAL(19,4)
);

DELIMITER $$
CREATE TRIGGER tr_log_change
AFTER UPDATE
ON accounts 
FOR EACH ROW
BEGIN
	INSERT INTO `logs` (account_id, old_sum, new_sum)
    VALUES (new.id, old.balance, new.balance);
END $$

#17
DELIMITER ;
DROP TABLE notification_emails;
CREATE TABLE `notification_emails` (
	id INT PRIMARY KEY AUTO_INCREMENT,
    recipient INT,
    subject VARCHAR(255),
    body TEXT
);
DROP TRIGGER tr_email_notification;
DELIMITER $$
CREATE TRIGGER tr_email_notification
BEFORE INSERT
ON `logs`
FOR EACH ROW
BEGIN
	INSERT INTO notification_emails(recipient, subject, body)
    VALUES (
    new.account_id,
    concat('Balance for account:', ' ', new.account_id),
    concat(date_format(now(), 'On %b %d %Y at %r'), ' ', 'your balance was changed from '
    , new.old_sum, ' to ', new.new_sum)
    );
END $$

DELIMITER ;
CALL usp_transfer_money(2, 1, 60);

#18
DELIMITER $$
CREATE PROCEDURE usp_massive_shopping ()
BEGIN

	DECLARE u_id INT;
	DECLARE g_id INT;
	DECLARE ug_id INT;
    DECLARE i_id INT;
    DECLARE items_to_buy_i INT;
    DECLARE items_to_buy_len INT;
    DECLARE i_price DECIMAL(19, 4);
    
    SET u_id := (SELECT `u`.`id` FROM `users` AS `u` WHERE `u`.`user_name` = 'Stamat');
    SET g_id := (SELECT `g`.`id` FROM `games` AS `g` WHERE `g`.`name` = 'Safflower');
    SET ug_id := (SELECT `ug`.`id` FROM `users_games` AS `ug` WHERE u_id = `ug`.`user_id` AND g_id = `ug`.`game_id`);
    SET items_to_buy_i := 1;

	DROP TEMPORARY TABLE IF EXISTS `my_items` ;
	CREATE TEMPORARY TABLE `my_items` (`item_id` INT)
	AS
	(SELECT `ugi`.`item_id` AS `item_id` FROM `user_game_items` AS `ugi` WHERE `ugi`.`user_game_id` = ug_id);
    
    DROP TEMPORARY TABLE IF EXISTS `items_to_buy` ;
	CREATE TEMPORARY TABLE `items_to_buy` (
	`temp_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY, `item_id` INT, `name` VARCHAR(150), `price` DECIMAL(19, 4))
	AS
	(SELECT `id` AS `item_id`, `name` AS `name`, `price` AS `price` FROM `items` WHERE `min_level` IN (11, 12) AND `id` NOT IN (SELECT * FROM `my_items`));
    
    SET items_to_buy_len := (SELECT COUNT(*) FROM `items_to_buy`);
    
    START TRANSACTION;
    WHILE (items_to_buy_i <= items_to_buy_len) DO
		SET i_price := (SELECT `itb`.`price` FROM `items_to_buy` AS `itb` WHERE `itb`.`temp_id` = items_to_buy_i);
		SET i_id := (SELECT `itb`.`item_id` FROM `items_to_buy` AS `itb` WHERE `itb`.`temp_id` = items_to_buy_i);
        
        INSERT INTO `user_game_items` (`item_id`, `user_game_id`) VALUE (i_id, ug_id);
		UPDATE `users_games` SET `cash` = `cash` - i_price WHERE `id` = ug_id; 
    
		SET items_to_buy_i := items_to_buy_i + 1;
    END WHILE;
    IF ((SELECT `cash` FROM `users_games` WHERE `id` = ug_id) < 0) THEN
		ROLLBACK;
	ELSE
		COMMIT;
	END IF;
    
    DROP TABLE IF EXISTS `items_to_buy` ;
	CREATE TEMPORARY TABLE `items_to_buy` (
	`temp_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY, `item_id` INT, `name` VARCHAR(150), `price` DECIMAL(19, 4))
	AS
	(SELECT `id` AS `item_id`, `name` AS `name`, `price` AS `price` FROM `items` WHERE `min_level` IN (19, 20, 21) AND `id` NOT IN (SELECT * FROM `my_items`));
    
    SET items_to_buy_i := 1;
    SET items_to_buy_len := (SELECT COUNT(*) FROM `items_to_buy`);
    
    START TRANSACTION;
    WHILE (items_to_buy_i <= items_to_buy_len) DO
		SET i_price := (SELECT `itb`.`price` FROM `items_to_buy` AS `itb` WHERE `itb`.`temp_id` = items_to_buy_i);
		SET i_id := (SELECT `itb`.`item_id` FROM `items_to_buy` AS `itb` WHERE `itb`.`temp_id` = items_to_buy_i);
        
        INSERT INTO `user_game_items` (`item_id`, `user_game_id`) VALUE (i_id, ug_id);
		UPDATE `users_games` SET `cash` = `cash` - i_price WHERE `id` = ug_id; 
    
		SET items_to_buy_i := items_to_buy_i + 1;
    END WHILE;
    IF ((SELECT `cash` FROM `users_games` WHERE `id` = ug_id) < 0) THEN
		ROLLBACK;
	ELSE
		COMMIT;
	END IF;
    
    SELECT `i`.`name`
	FROM `user_game_items` AS `ugi` 
	JOIN `users_games` AS `ug`
	ON `ug`.`id` = `ugi`.`user_game_id`
	JOIN `games` AS `g`
	ON `ug`.`game_id` = `g`.`id` AND `g`.`name` = 'Safflower'
	JOIN `items` AS `i`
	ON `i`.`id` = `ugi`.`item_id`
	ORDER BY `i`.`name` ASC;
    
    SELECT `cash` 
    FROM `users_games` 
    WHERE `id` = ug_id;    

END $$

#19
CREATE PROCEDURE `usp_buy_item`(ug_id INT, u_level INT, i_name VARCHAR(100))
BEGIN
	
    DECLARE i_id INT;
    DECLARE i_level INT;
    DECLARE i_price DECIMAL(19, 4);
    
    SET i_id := (SELECT `id` FROM `items` WHERE `name` = i_name);
    SET i_price := (SELECT `price` FROM `items` WHERE `id` = i_id);
    SET i_level := (SELECT `min_level` FROM `items` WHERE `id` = i_id);
    
    IF (u_level >= i_level AND i_id NOT IN (SELECT `item_id` FROM `user_game_items` AS `ugi` WHERE `ugi`.`user_game_id` = ug_id)) THEN
		START TRANSACTION;
		INSERT INTO `user_game_items` (`item_id`, `user_game_id`) VALUE (i_id, ug_id);
		UPDATE `users_games` SET `cash` = `cash` - i_price WHERE `id` = ug_id;
		IF ((SELECT `cash` FROM `users_games` WHERE `id` = ug_id) < 0) THEN
			ROLLBACK;
		ELSE
			COMMIT;
		END IF;
	END IF;    

END $$

CREATE PROCEDURE `usp_buy_items_for_alex`()
BEGIN

	DECLARE u_id INT;
    DECLARE u_level INT;
	DECLARE g_id INT;
	DECLARE ug_id INT;
    
    SET u_id := (SELECT `u`.`id` FROM `users` AS `u` WHERE `u`.`user_name` = 'Alex');
    SET g_id := (SELECT `g`.`id` FROM `games` AS `g` WHERE `g`.`name` = 'Edinburgh');
    SET ug_id := (SELECT `ug`.`id` FROM `users_games` AS `ug` WHERE u_id = `ug`.`user_id` AND g_id = `ug`.`game_id`);
    SET u_level := (SELECT `ug`.`level` FROM `users_games` AS `ug` WHERE u_id = `ug`.`user_id` AND g_id = `ug`.`game_id`);
	
    CALL usp_buy_item(ug_id, u_level, 'Blackguard');
	CALL usp_buy_item(ug_id, u_level, 'Bottomless Potion of Amplification');
	CALL usp_buy_item(ug_id, u_level, 'Eye of Etlich (Diablo III)');
	CALL usp_buy_item(ug_id, u_level, 'Gem of Efficacious Toxin');
	CALL usp_buy_item(ug_id, u_level, 'Golden Gorget of Leoric');
	CALL usp_buy_item(ug_id, u_level, 'Ziggurat Tooth');
	CALL usp_buy_item(ug_id, u_level, 'The Three Hundredth Spear');
	CALL usp_buy_item(ug_id, u_level, 'The Short Mans Finger');
	CALL usp_buy_item(ug_id, u_level, 'Tzo Krins Gaze');
	CALL usp_buy_item(ug_id, u_level, 'Valtheks Rebuke');
	CALL usp_buy_item(ug_id, u_level, 'Utars Roar');
	CALL usp_buy_item(ug_id, u_level, 'Urn of Quickening');
	CALL usp_buy_item(ug_id, u_level, 'Boots');
	CALL usp_buy_item(ug_id, u_level, 'Bombardiers Rucksack');
	CALL usp_buy_item(ug_id, u_level, 'Cloak of Deception');
	CALL usp_buy_item(ug_id, u_level, 'Hellfire Amulet');
    
    SELECT `u`.`user_name`, `g`.`name`, `ug`.`cash`, `i`.`name` 
    FROM `users` AS `u`
    JOIN `users_games` AS `ug`
    ON `ug`.`user_id` = `u`.`id`
	JOIN `user_game_items` AS `ugi`
    ON `ug`.`id` = `ugi`.`user_game_id`
	JOIN `games` AS `g`
	ON `ug`.`game_id` = `g`.`id` AND `g`.`name` = 'Edinburgh'
	JOIN `items` AS `i`
	ON `i`.`id` = `ugi`.`item_id`
	ORDER BY `i`.`name` ASC;
	    
END $$

