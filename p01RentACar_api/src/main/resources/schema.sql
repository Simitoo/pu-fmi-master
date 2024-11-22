CREATE TABLE IF NOT EXISTS Clients(
client_id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(256) NOT NULL,
location VARCHAR(256) NOT NULL,
phone VARCHAR(256) NOT NULL,
age INT NOT NULL,
has_accidents TINYINT DEFAULT 0,
is_active TINYINT DEFAULT 1
);

CREATE TABLE IF NOT EXISTS Locations(
location_id INT AUTO_INCREMENT PRIMARY KEY,
city_name VARCHAR(256) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS Cars(
car_id INT AUTO_INCREMENT PRIMARY KEY,
model VARCHAR(256) NOT NULL,
price_per_day DECIMAL(10, 2) NOT NULL,
is_active TINYINT DEFAULT 1,
location_id INT NOT NULL,
FOREIGN KEY (location_id) REFERENCES Locations(location_id)
);

CREATE TABLE IF NOT EXISTS Offers (
offer_id INT AUTO_INCREMENT PRIMARY KEY,
client_id INT NOT NULL,
car_id INT NOT NULL,
rent_days INT NOT NULL,
final_price DECIMAL(10, 2) NOT NULL,
is_active TINYINT DEFAULT 0,
FOREIGN KEY (client_id) REFERENCES Clients(client_id),
FOREIGN KEY (car_id) REFERENCES Cars(car_id)
);

