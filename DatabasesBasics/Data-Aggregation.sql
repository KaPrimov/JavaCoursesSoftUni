SELECT 
	CASE 
	WHEN w.age BETWEEN 0 AND 10 THEN '[0-10]'
	WHEN w.age BETWEEN 11 AND 20 THEN '[11-20]'
	WHEN w.age BETWEEN 21 AND 30 THEN '[21-30]'
	WHEN w.age BETWEEN 31 AND 40 THEN '[31-40]'
	WHEN w.age BETWEEN 41 AND 50 THEN '[41-50]'
	WHEN w.age BETWEEN 51 AND 60 THEN '[51-60]'
	WHEN w.age >= 61 THEN '[61+]'
	END AS 'age_group',
	COUNT(*) AS 'wizard_count'
	FROM wizzard_deposits AS w
GROUP BY CASE
	WHEN w.age BETWEEN 0 AND 10 THEN '[0-10]'
	WHEN w.age BETWEEN 11 AND 20 THEN '[11-20]'
	WHEN w.age BETWEEN 21 AND 30 THEN '[21-30]'
	WHEN w.age BETWEEN 31 AND 40 THEN '[31-40]'
	WHEN w.age BETWEEN 41 AND 50 THEN '[41-50]'
	WHEN w.age BETWEEN 51 AND 60 THEN '[51-60]'
	WHEN w.age >= 61 THEN '[61+]'
	END;
	
SELECT LEFT(w.first_name, 1) AS 'first_letter' 
FROM wizzard_deposits AS w
WHERE w.deposit_group='Troll Chest'
GROUP BY `first_letter`
ORDER BY `first_letter`;

SELECT w.deposit_group, w.is_deposit_expired, AVG(w.deposit_interest) AS 'average_interes' 
FROM wizzard_deposits AS w
WHERE w.deposit_start_date > DATE('1985-01-01')
GROUP BY w.deposit_group, w.is_deposit_expired
ORDER BY w.deposit_group DESC, w.is_deposit_expired;

CREATE VIEW wiizards_differences AS
SELECT
w1.first_name AS host_wizard, w1.deposit_amount AS host_wizard_amount,
w2.first_name AS guest_wizard, w2.deposit_amount AS guest_wizard_amount
FROM wizzard_deposits AS w1, wizzard_deposits AS w2
WHERE w1.id = w2.id-1;

SELECT SUM(host_wizard_amount - guest_wizard_amount) AS `sum_difference`
FROM wiizards_differences as wd;

SELECT e.department_id, MIN(e.salary) AS `minimum_salary` 
FROM employees AS e
WHERE e.department_id IN (2,5,7) AND e.hire_date > DATE('2000-01-01')
GROUP BY e.department_id
ORDER BY e.department_id;

CREATE TABLE `high_paid_employees`
AS SELECT e.department_id, e.manager_id, e.salary 
FROM employees AS e
WHERE e.salary > 30000;

DELETE FROM high_paid_employees
WHERE `manager_id`=42;

UPDATE high_paid_employees
SET salary = salary+5000
WHERE department_id=1;

SELECT department_id, AVG(salary) 
FROM high_paid_employees
GROUP BY department_id
ORDER BY department_id;

SELECT e.department_id, MAX(e.salary) AS 'max_salary' 
FROM employees AS e
GROUP BY e.department_id
HAVING max_salary NOT BETWEEN 30000 AND 70000
ORDER BY e.department_id;

SELECT COUNT(*) AS 'count'
FROM employees AS e
WHERE e.manager_id IS NULL;

SELECT e.first_name, e.last_name, e.department_id
FROM employees AS e, (SELECT e.department_id, AVG(e.salary) as 'avg_salary'
					      FROM employees AS e
					      GROUP BY e.department_id) AS avg_salary_by_department
WHERE e.department_id = avg_salary_by_department.department_id
	AND e.salary > avg_salary_by_department.avg_salary
ORDER BY e.department_id
LIMIT 10;

SELECT e.department_id, SUM(e.salary) 
FROM employees AS e
GROUP BY e.department_id
ORDER BY e.department_id;