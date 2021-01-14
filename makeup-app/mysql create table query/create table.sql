SELECT * FROM makeup_artist_application_db.bridal_makeup_appointments;

CREATE TABLE `bridal_makeup_appointments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type_of_appointment` varchar(45) NOT NULL,
  `appointment_date` varchar(45) NOT NULL,
  `guest_makeup_required` varchar(45) NOT NULL,
  `customer_name` varchar(45) NOT NULL,
  `phone_number` varchar(45) NOT NULL,
  `number_of_guests` varchar(45) NOT NULL,
  `appointment_time` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  Constraint uq_phone_number Unique (appointment_date, phone_number)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
