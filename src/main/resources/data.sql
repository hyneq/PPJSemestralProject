
-- Demo countries
INSERT IGNORE INTO country(code, name)
VALUES ('CZ', 'Czech Republic'),
       ('US', 'United States'),
       ('UK', 'United Kingdom'),
       ('DE', 'Germany');

-- Demo cities
INSERT IGNORE INTO city(id, name, country_code)
VALUES (3071961, 'Liberec', 'CZ'),
       (3067696, 'Prague', 'CZ'),
       (5128581, 'New York', 'US'),
       (2643743, 'London', 'UK'),
       (2950159, 'Berlin', 'DE');