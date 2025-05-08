-- Demo countries
INSERT INTO Country (code, name) VALUES ('CZ', 'Czech Republic');
INSERT INTO Country (code, name) VALUES ('US', 'United States');
INSERT INTO Country (code, name) VALUES ('UK', 'United Kingdom');
INSERT INTO Country (code, name) VALUES ('DE', 'Germany');

-- Demo cities
INSERT INTO City (name, country_id) VALUES ('Liberec', (SELECT id from COUNTRY where code = 'CZ'));
INSERT INTO City (name, country_id) VALUES ('Praha', (SELECT id from COUNTRY where code = 'CZ'));
INSERT INTO City (name, country_id) VALUES ('New York', (SELECT id from COUNTRY where code = 'US'));
INSERT INTO City (name, country_id) VALUES ('London', (SELECT id from COUNTRY where code = 'UK'));
INSERT INTO City (name, country_id) VALUES ('Berlin', (SELECT id from COUNTRY where code = 'DE'));