CREATE TABLE `bridal_makeup_appointments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type_of_appointment` varchar(45) NOT NULL,
  `appointment_date` varchar(45) NOT NULL,
  `guest_makeup_required` varchar(45) NOT NULL,
  `customer_name` varchar(45) NOT NULL,
  `phone_number` varchar(45) NOT NULL,
  `number_of_guests` varchar(45) NOT NULL,
  `appointment_time` varchar(45) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `constr_ID` (`appointment_date`,`phone_number`),
  KEY `user_name_fk_idx` (`user_name`),
  CONSTRAINT `user_name_fk` FOREIGN KEY (`user_name`) REFERENCES `user` (`user_name`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

