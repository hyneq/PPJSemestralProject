-- Table Country, represents Country objects and is accessed via CountryDao
CREATE TABLE Country (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code CHAR(2) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL
) ENGINE=InnoDB;

-- Table City, represents City objects and is accessed via CityDao
CREATE TABLE City (
    id INT AUTO_INCREMENT PRIMARY KEY,
    country_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    FOREIGN KEY (country_id) REFERENCES Country(id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- Table Measurement, represents Measurement objects and is accessed via MeasurementDao
CREATE TABLE Measurement (
    city_id INT NOT NULL,
    datetime DATETIME NOT NULL,
    temp DOUBLE,
    temp_feels_like DOUBLE,
    temp_min DOUBLE,
    temp_max DOUBLE,
    pressure INT,
    humidity INT,
    wind_speed DOUBLE,
    wind_direction INT,
    wind_gust DOUBLE,
    clouds INT,
    condition_id INT,
    PRIMARY KEY (city_id, datetime),
    FOREIGN KEY (city_id) REFERENCES City(id) ON DELETE CASCADE
) ENGINE=InnoDB;
