
-- Demo countries
INSERT INTO COUNTRY(code, name)
VALUES ('CZ', 'Czech Republic'),
       ('US', 'United States'),
       ('UK', 'United Kingdom'),
       ('DE', 'Germany');

-- Demo cities
INSERT INTO CITY(name, country)
VALUES ('Liberec', 'CZ'),
       ('Praha', 'CZ'),
       ('New York', 'US'),
       ('London', 'UK'),
       ('Berlin', 'DE');