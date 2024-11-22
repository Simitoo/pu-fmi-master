INSERT INTO Clients (name, location, phone, age, has_accidents)
SELECT 'Pesho', 'Varna', '0892246282', 30, 1
WHERE NOT EXISTS (
    SELECT 1 FROM Clients WHERE client_id = 1
);

        --- inserts for Locations ---

INSERT INTO Locations (city_name)
SELECT 'Plovdiv'
WHERE NOT EXISTS (
    SELECT 1 FROM Locations WHERE location_id = 1
);

INSERT INTO Locations (city_name)
SELECT 'Varna'
WHERE NOT EXISTS (
    SELECT 1 FROM Locations WHERE location_id = 2
);

INSERT INTO Locations (city_name)
SELECT 'Sofia'
WHERE NOT EXISTS (
    SELECT 1 FROM Locations WHERE location_id = 3
);

INSERT INTO Locations (city_name)
SELECT 'Burgas'
WHERE NOT EXISTS (
    SELECT 1 FROM Locations WHERE location_id = 4
);

            --- inserts for Cars ---

INSERT INTO Cars (model, price_per_day, location_id)
SELECT 'Toyota Camry', 20.50, 4
WHERE NOT EXISTS (
    SELECT 1 FROM Cars WHERE car_id = 1
);

INSERT INTO Cars (model, price_per_day, location_id)
SELECT 'Ford Mustang', 40, 1
WHERE NOT EXISTS (
    SELECT 1 FROM Cars WHERE car_id = 2
);

INSERT INTO Cars (model, price_per_day, location_id)
SELECT 'Porsche 911', 70.90, 2
WHERE NOT EXISTS (
    SELECT 1 FROM Cars WHERE car_id = 3
);

            --- inserts for Offers ---