#1
SELECT e.employee_id, e.job_title, e.address_id, a.address_text FROM employees AS e
JOIN addresses AS a ON
a.address_id = e.address_id
ORDER BY a.address_id
LIMIT 5;

#2
SELECT e.first_name, e.last_name, t.name, a.address_text FROM employees AS e
JOIN addresses AS a ON
a.address_id = e.address_id
JOIN towns AS t ON
t.town_id = a.town_id
ORDER BY e.first_name, last_name
LIMIT 5;

#3
SELECT e.employee_id, e.first_name, e.last_name, d.name FROM employees AS e
JOIN departments AS d
ON e.department_id=d.department_id
WHERE d.name="Sales"
ORDER BY e.employee_id DESC;

#4
SELECT e.employee_id, e.first_name, e.salary, d.name FROM employees AS e
JOIN departments AS d
ON e.department_id=d.department_id
WHERE e.salary > 15000
ORDER BY d.department_id DESC
LIMIT 5;

#5
SELECT e.employee_id, e.first_name FROM employees AS e
LEFT OUTER JOIN employees_projects AS ap
ON e.employee_id = ap.employee_id
WHERE ap.project_id IS NULL
ORDER BY e.employee_id DESC
LIMIT 3;

#6
SELECT e.first_name, e.last_name, e.hire_date, d.name FROM employees AS e
INNER JOIN departments AS d
ON e.department_id=d.department_id
WHERE DATE(e.hire_date) > '1999/01/01'
AND d.name IN ('Sales', 'Finance')
ORDER BY e.hire_date;

#7
SELECT e.employee_id, e.first_name, p.name AS 'project_name' FROM employees AS e
INNER JOIN employees_projects AS ep
ON ep.employee_id=e.employee_id
INNER JOIN projects AS p
ON p.project_id=ep.project_id
WHERE p.end_date IS NULL
AND DATE(p.start_date) > '2002/08/13'
ORDER BY e.first_name,p.name
LIMIT 5;

#8
SELECT e.employee_id, e.first_name,
	CASE 
		WHEN YEAR(p.start_date) >= 2005
        THEN NULL
        ELSE p.name
        END AS 'project_name'
FROM employees AS e
INNER JOIN employees_projects AS ep
ON ep.employee_id=e.employee_id
INNER JOIN projects AS p
ON p.project_id=ep.project_id
WHERE e.employee_id=24
ORDER BY p.name;

#9
SELECT 
    e.employee_id,
    e.first_name,
    e.manager_id,
    em.first_name AS 'manager_name'
FROM
    employees AS e
        JOIN
    employees AS em ON em.employee_id = e.manager_id
WHERE
    e.manager_id IN (3 , 7)
ORDER BY e.first_name;

#10
SELECT 
    e.employee_id,
    concat(e.first_name, ' ', e.last_name) AS 'employee_name',
    concat(em.first_name, ' ', em.last_name) AS 'manager_name',
    d.name AS 'department_name'
FROM
    employees AS e
	JOIN
    employees AS em ON em.employee_id = e.manager_id
    JOIN
    departments AS d ON e.department_id=d.department_id
ORDER BY e.employee_id
LIMIT 5;

#11
SELECT MIN(a.average_salaries) AS 'min_average_salary' FROM
(SELECT 
    AVG(e.salary) AS 'average_salaries'
FROM
    employees AS e
GROUP BY e.department_id) AS a;

#12
SELECT c.country_code, m.mountain_range, p.peak_name, p.elevation FROM mountains AS m
JOIN peaks AS p ON p.mountain_id=m.id
JOIN mountains_countries AS mc ON mc.mountain_id = m.id
JOIN countries AS c ON mc.country_code=c.country_code
WHERE c.country_code='BG' 
AND p.elevation > 2835
ORDER BY p.elevation DESC;

#13
SELECT c.country_code, count(m.mountain_range) AS 'mountain_range' FROM mountains AS m
JOIN mountains_countries AS mc ON mc.mountain_id = m.id
JOIN countries AS c ON mc.country_code=c.country_code
GROUP BY c.country_code
HAVING c.country_code IN ('BG','RU','US')
ORDER BY count(m.mountain_range) DESC;

#14
SELECT c.country_name, r.river_name FROM countries AS c
LEFT OUTER JOIN countries_rivers AS cr
ON cr.country_code=c.country_code
LEFT OUTER JOIN rivers AS r
ON r.id=cr.river_id
WHERE c.continent_code='AF'
ORDER BY c.country_name
LIMIT 5;

#15
SELECT 
    usages.continent_code,
    usages.currency_code,
    usages.currency_usage
FROM
    (SELECT 
        c.continent_code,
            c.currency_code,
            COUNT(c.currency_code) AS currency_usage
    FROM
        countries AS c  
    GROUP BY c.continent_code , c.currency_code
    HAVING currency_usage > 1) AS usages
        INNER JOIN
    (SELECT 
        cur_usages.continent_code, MAX(cur_usages.currency_usage) AS max_usage
    FROM
        (SELECT 
        c.continent_code,
            c.currency_code,
            COUNT(c.currency_code) AS currency_usage
    FROM
        countries AS c
    GROUP BY c.continent_code , c.currency_code
    HAVING currency_usage > 1) AS cur_usages
    GROUP BY cur_usages.continent_code) 
    AS max_usages ON usages.continent_code = max_usages.continent_code
WHERE usages.currency_usage = max_usages.max_usage
ORDER BY usages.continent_code, usages.currency_code;

#16
SELECT count(*) AS 'country_count'
FROM countries AS c
LEFT JOIN mountains_countries AS mc
ON mc.country_code = c.country_code
WHERE mc.country_code IS NULL;

#17
SELECT 
    c.country_name,
    MAX(p.elevation) AS 'highest_peak_elevation',
    MAX(r.length) AS 'longest_river_length'
FROM
    countries AS c
        LEFT JOIN
    mountains_countries AS mc ON mc.country_code = c.country_code
        LEFT JOIN
    mountains AS m ON mc.mountain_id = m.id
        LEFT JOIN
    peaks AS p ON m.id = p.mountain_id
        LEFT JOIN
    countries_rivers AS cr ON cr.country_code = c.country_code
        LEFT JOIN
    rivers AS r ON r.id = cr.river_id
GROUP BY c.country_name
ORDER BY p.elevation DESC , r.length DESC , c.country_name
LIMIT 5;