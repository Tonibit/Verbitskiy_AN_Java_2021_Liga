INSERT INTO roles(id, role_name) VALUES (1, "ROLE_USER");
INSERT INTO roles(id, role_name) VALUES (2, "ROLE_ADMIN");

INSERT INTO users(id, name, username, password, role_id)
    VALUES (1, 'John', 'Jo', '$2a$10$5BKHCcDYpgxG8p2VEGF34OSBk6lMkw9o0z5JjSIPhHPaOvC8Pe/IG', 1);
INSERT INTO users(id, name, username, password, role_id)
    VALUES (2, 'Elsa', 'El', '$2a$10$5BKHCcDYpgxG8p2VEGF34OSBk6lMkw9o0z5JjSIPhHPaOvC8Pe/IG', 2);
INSERT INTO users(id, name, username, password, role_id)
    VALUES (3, 'Peter', 'Pe', '$2a$10$5BKHCcDYpgxG8p2VEGF34OSBk6lMkw9o0z5JjSIPhHPaOvC8Pe/IG', 1);

INSERT INTO bookings(id, date_time_booking, date_of_creation, arrived, active, completed, user_id)
	VALUES (1, '2021-11-02 08:25', '2021-10-31 10:23', 0, 1, 0, 1);
INSERT INTO bookings(id, date_time_booking, date_of_creation, arrived, active, completed, user_id)
	VALUES (2, '2021-11-01 16:30', '2021-10-30 20:45', 0, 1, 0, 3);
INSERT INTO bookings(id, date_time_booking, date_of_creation, arrived, active, completed, user_id)
	VALUES (3, '2021-11-02 08:40', '2021-10-25 12:41', 0, 1, 0, 1);
INSERT INTO bookings(id, date_time_booking, date_of_creation, arrived, active, completed, user_id)
	VALUES (4, '2021-11-03 15:22', '2021-11-01 17:36', 0, 1, 0, 1);
INSERT INTO bookings(id, date_time_booking, date_of_creation, arrived, active, completed, user_id)
	VALUES (5, '2021-11-01 16:10', '2021-10-30 7:55', 0, 1, 0, 2);
INSERT INTO bookings(id, date_time_booking, date_of_creation, arrived, active, completed, user_id)
	VALUES (6, '2021-11-02 08:25', '2021-10-31 14:11', 0, 1, 0, 3);
INSERT INTO bookings(id, date_time_booking, date_of_creation, arrived, active, completed, user_id)
	VALUES (7, '2021-11-01 08:25', '2021-10-31 14:11', 0, 1, 0, 2);
INSERT INTO bookings(id, date_time_booking, date_of_creation, arrived, active, completed, user_id)
	VALUES (8, '2021-10-29 11:25', '2021-10-31 14:11', 0, 1, 0, 1);
INSERT INTO bookings(id, date_time_booking, date_of_creation, arrived, active, completed, user_id)
	VALUES (9, '2021-11-01 8:54:30', '2021-10-31 14:11', 0, 1, 0, 3);