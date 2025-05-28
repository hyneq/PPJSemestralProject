
-- Demo countries
INSERT INTO COUNTRY(code, name)
VALUES ('CZ', 'Czech Republic'),
       ('US', 'United States'),
       ('UK', 'United Kingdom'),
       ('DE', 'Germany');

-- Demo cities
INSERT INTO CITY(name, country_id)
VALUES ('Liberec', (SELECT id from COUNTRY where code = 'CZ')),
       ('Praha', (SELECT id from COUNTRY where code = 'CZ')),
       ('New York', (SELECT id from COUNTRY where code = 'US')),
       ('London', (SELECT id from COUNTRY where code = 'UK')),
       ('Berlin', (SELECT id from COUNTRY where code = 'DE'));