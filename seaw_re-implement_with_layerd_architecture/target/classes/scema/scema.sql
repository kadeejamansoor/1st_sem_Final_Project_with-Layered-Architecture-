DROP DATABASE IF EXISTS Seew;
CREATE DATABASE IF NOT EXISTS Seew;
USE Seew;

-- Employee table
DROP TABLE IF EXISTS Employee;
CREATE TABLE IF NOT EXISTS Employee (
                                        empid VARCHAR(15) NOT NULL,
                                        name VARCHAR(50),

                                        contact VARCHAR(100),
                                        address VARCHAR(100),
                                        role VARCHAR(100),
                                        CONSTRAINT PRIMARY KEY (empid)
);

-- User table
DROP TABLE IF EXISTS User;
CREATE TABLE IF NOT EXISTS User (
                                    username VARCHAR(50) NOT NULL,
                                    password VARCHAR(50) NOT NULL
);


-- Salary table
DROP TABLE IF EXISTS Salary;
CREATE TABLE IF NOT EXISTS Salary (
                                      salary_id VARCHAR(15) NOT NULL,
                                      fullName VARCHAR(15),
                                      amount DOUBLE,
                                      Salarydate VARCHAR(15),
                                      employee_id VARCHAR(15),
                                      CONSTRAINT PRIMARY KEY (salary_id),
                                      CONSTRAINT FOREIGN KEY (employee_id) REFERENCES Employee(empid) on Delete Cascade on Update Cascade
);



-- Stock table
DROP TABLE IF EXISTS Stock;
CREATE TABLE IF NOT EXISTS Stock (
                                     stock_id VARCHAR(15) NOT NULL,
                                     stock_name VARCHAR(50),
                                     quantity INT,
                                     CONSTRAINT PRIMARY KEY (stock_id)
);


-- Guest table
DROP TABLE IF EXISTS Guest;
CREATE TABLE IF NOT EXISTS Guest (
                                     guest_id VARCHAR(15) NOT NULL,
                                     guest_Fullname VARCHAR(50),
                                     address VARCHAR(50),
                                     city VARCHAR(50),
                                     postal_code VARCHAR(50),
                                     country VARCHAR(50),
                                     email VARCHAR(50),
                                     age int,
                                     CONSTRAINT PRIMARY KEY (guest_id)
);


-- Booking table
DROP TABLE IF EXISTS Booking;
CREATE TABLE IF NOT EXISTS Booking (
                                       booking_id VARCHAR(100),
                                       check_in DATE,
                                       check_out DATE,
                                       no_of_guest int,
                                       select_room VARCHAR(1500),
                                       guest_id VARCHAR(15),
                                       BookingStatus VARCHAR(50) ,
                                       CONSTRAINT PRIMARY KEY (booking_id),
                                       CONSTRAINT FOREIGN KEY (guest_id) REFERENCES Guest(guest_id)  on Delete Cascade on Update Cascade
);




-- Room table
DROP TABLE IF EXISTS Room;
CREATE TABLE IF NOT EXISTS Room (
                                    room_id VARCHAR(15) NOT NULL,
                                    room_type VARCHAR(50),
                                    floor_Number INT,
                                    capacity INT,
                                    roomRate double
);

DROP TABLE IF EXISTS RoomStatus;
CREATE TABLE IF NOT EXISTS RoomStatus (
                                          room_id VARCHAR(15) NOT NULL,
                                          room_type VARCHAR(50),
                                          floor_Number INT,
                                          capacity INT,
                                          rate double,
                                          room_status VARCHAR(50),
                                          booking_id VARCHAR(50),
                                          CONSTRAINT PRIMARY KEY (room_id)
    /*CONSTRAINT FOREIGN KEY (guest_id) REFERENCES Guest(guest_id) on Delete Cascade on Update Cascade*/
);





-- Reservation table
DROP TABLE IF EXISTS Reservation;
CREATE TABLE IF NOT EXISTS Reservation (
                                           reservationId VARCHAR(15) NOT NULL,
                                           guestName VARCHAR(50),
                                           checkIn DATE,
                                           checkOut DATE,
                                           room_id VARCHAR(15),
                                           noOfGuest Int,
                                           reservation_status VARCHAR(15),
                                           CONSTRAINT PRIMARY KEY (reservationId)
                                           /*CONSTRAINT FOREIGN KEY (room_id) REFERENCES RoomStatus(room_id) on Delete Cascade on Update Cascade*/
);



-- Booking-Room association table
DROP TABLE IF EXISTS Booking_Room;
CREATE TABLE IF NOT EXISTS Booking_Room (
                                            room_id VARCHAR(15) NOT NULL,
                                            booking_id VARCHAR(50),
                                            CONSTRAINT PRIMARY KEY (room_id , booking_id),
                                            CONSTRAINT FOREIGN KEY (room_id) REFERENCES RoomStatus(room_id) on Delete Cascade on Update Cascade,
                                            CONSTRAINT FOREIGN KEY (booking_id) REFERENCES Booking(booking_id)  on Delete Cascade on Update Cascade
);

-- Payment table
DROP TABLE IF EXISTS Payment;
CREATE TABLE IF NOT EXISTS Payment (
                                       payment_id VARCHAR(50),
                                       booking_id VARCHAR(25),
                                       guest_id VARCHAR(25),
                                       amount DOUBLE,
                                       Payment_date DATE,
                                       CONSTRAINT PRIMARY KEY (payment_id)
    /*CONSTRAINT FOREIGN KEY (booking_id) REFERENCES Booking(booking_id) on Delete Cascade on Update Cascade,
    CONSTRAINT FOREIGN KEY (guest_id) REFERENCES Guest(guest_id) on Delete Cascade on Update Cascade*/
);






INSERT INTO Employee VALUES('E00-001', 'Nuwan Perera', '+94 77 123 4567', 'Colombo', 'worker');
INSERT INTO Employee VALUES('E00-002', 'Samantha Silva', '+94 76 234 5678', '456 Kandy Road, Gampaha', 'manager');
INSERT INTO Employee VALUES('E00-003', 'Chathura Rajapakse', '+94 71 345 6789', '789 Negombo Road, Wattala', 'worker');
INSERT INTO Employee VALUES('E00-004', 'Priyanka Fernando', '+94 77 456 7890', '321 Colombo Road, Nugegoda', 'receptionist');
INSERT INTO Employee VALUES('E00-005', 'Kamal Jayawardena', '+94 76 567 8901', '654 Galle Face Road, Colombo', 'worker');
INSERT INTO Employee VALUES('E00-006', 'Anusha Gunathilake', '+94 71 678 9012', '987 Colombo Road, Moratuwa', 'cleaner');
INSERT INTO Employee VALUES('E00-007', 'Dilan Perera', '+94 77 789 0123', '246 Kandy Road, Kadawatha', 'worker');
INSERT INTO Employee VALUES('E00-008', 'Sahan Silva', '+94 76 890 1234', '369 Negombo Road, Ja-ela', 'worker');
INSERT INTO Employee VALUES('E00-009', 'Chamara Rajapakse', '+94 71 901 2345', '987 Colombo Road, Nugegoda', 'receptionist');
INSERT INTO Employee VALUES('E00-010', 'Nimal Perera', '+94 77 012 3456', '654 Galle Road, Mount Lavinia', 'worker');



INSERT INTO User VALUES('niko','1');
INSERT INTO User VALUES('dathusena','letmein');
INSERT INTO User VALUES('1','1');


INSERT INTO Salary VALUES('S00-001','john doily',50000,'2023/03/04', 'E00-001');
INSERT INTO Salary VALUES('S00-002','jane doe',60000,'2023/03/04', 'E00-002');
INSERT INTO Salary VALUES('S00-003','jack smith',55000,'2023/03/04', 'E00-003');
INSERT INTO Salary VALUES('S00-004','mary lee',75000,'2023/03/04', 'E00-004');
INSERT INTO Salary VALUES('S00-005','adam turner',40000,'2023/03/04', 'E00-005');
INSERT INTO Salary VALUES('S00-006','sarah jones',80000,'2023/03/04', 'E00-006');
INSERT INTO Salary VALUES('S00-007','david morgan',45000,'2023/03/04', 'E00-007');
INSERT INTO Salary VALUES('S00-008','kelly smith',55000,'2023/03/04', 'E00-008');
INSERT INTO Salary VALUES('S00-009','joshua taylor',70000,'2023/03/04', 'E00-009');
INSERT INTO Salary VALUES('S00-010','emily anderson',65000,'2023/03/04', 'E00-010');


INSERT INTO Stock VALUES('St00-001','Towels', 100);
INSERT INTO Stock VALUES('St00-002','Bed Linens', 150);
INSERT INTO Stock VALUES('St00-003','Pillows', 75);
INSERT INTO Stock VALUES('St00-004','Toiletries', 200);
INSERT INTO Stock VALUES('St00-005','Cleaning Supplies', 50);
INSERT INTO Stock VALUES('St00-006','Utensils', 50);
INSERT INTO Stock VALUES('St00-007','Cups', 75);
INSERT INTO Stock VALUES('St00-008','Plates', 75);
INSERT INTO Stock VALUES('St00-009','Coffee Machine', 10);
INSERT INTO Stock VALUES('St00-010','Iron', 25);


INSERT INTO Guest VALUES('G00-001', 'John Doe', '1st street', 'New York', '8056', 'USA', 'john.doe@example.com', 34);
INSERT INTO Guest VALUES('G00-002', 'Jane Smith', '2nd street', 'Los Angeles', '90210', 'USA', 'jane.smith@example.com', 28);
INSERT INTO Guest VALUES('G00-003', 'Mark Johnson', '3rd avenue', 'Chicago', '60601', 'USA',  'mark.johnson@example.com', 42);
INSERT INTO Guest VALUES('G00-004', 'Samantha Lee', '4th street', 'San Francisco', '94103', 'USA',  'samantha.lee@example.com', 25);
INSERT INTO Guest VALUES('G00-005', 'David Brown', '5th avenue', 'Boston', '02108', 'USA', 'david.brown@example.com', 31);
INSERT INTO Guest VALUES('G00-006', 'Sarah Wilson', '6th street', 'Seattle', '98101', 'USA',  'sarah.wilson@example.com', 29);
INSERT INTO Guest VALUES('G00-007', 'Michael Davis', '7th street', 'Austin', '78701', 'USA',  'michael.davis@example.com', 37);
INSERT INTO Guest VALUES('G00-008', 'Emily Taylor', '8th avenue', 'Philadelphia', '19102', 'USA',  'emily.taylor@example.com', 24);
INSERT INTO Guest VALUES('G00-009', 'Christopher Wilson', '9th street', 'Miami', '33131', 'USA',  'christopher.wilson@example.com', 27);
INSERT INTO Guest VALUES('G00-010', 'Jessica Anderson', '10th street', 'Denver', '80202', 'USA', 'jessica.anderson@example.com', 33);
INSERT INTO Guest VALUES('G00-011', 'Jacob Perez', '42nd street', 'San Francisco', '94102', 'USA', 'jacob.perez@example.com', 34);
INSERT INTO Guest VALUES('G00-012', 'Thomas Miller', '11th street', 'Dallas', '75201', 'USA',  'thomas.miller@example.com', 39);
INSERT INTO Guest VALUES('G00-013', 'Hannah Garcia', '12th street', 'Phoenix', '85001', 'USA', 'hannah.garcia@example.com', 29);
INSERT INTO Guest VALUES('G00-014', 'David Rodriguez', '13th street', 'Houston', '77001', 'USA',  'david.rodriguez@example.com', 36);
INSERT INTO Guest VALUES('G00-015', 'Avery Scott', '14th street', 'Seattle', '98101', 'USA', 'avery.scott@example.com', 27);
INSERT INTO Guest VALUES('G00-016', 'Isabella Adams', '15th street', 'Austin', '78701', 'USA',  'isabella.adams@example.com', 23);
INSERT INTO Guest VALUES('G00-017', 'Josephine Kim', '16th street', 'Philadelphia', '19102', 'USA', 'josephine.kim@example.com', 25);
INSERT INTO Guest VALUES('G00-018', 'Sophie Smith', '17th avenue', 'New York', '10001', 'USA',  'sophie.smith@example.com', 28);
INSERT INTO Guest VALUES('G00-019', 'Isabella Thompson', '18th street', 'Los Angeles', '90015', 'USA',  'isabella.thompson@example.com', 25);
INSERT INTO Guest VALUES('G00-020', 'Ryan Clark', '19th street', 'Chicago', '60603', 'USA',  'ryan.clark@example.com', 32);
INSERT INTO Guest VALUES('G00-021', 'Olivia Wright', '20th street', 'San Francisco', '94103', 'USA',  'olivia.wright@example.com', 29);

INSERT INTO Guest VALUES('G00-022', 'Jacob Patel', '22nd street', 'San Francisco', '94102', 'USA',  'jacob.patel@example.com', 29);
INSERT INTO Guest VALUES('G00-023', 'Samantha Lee', '23rd street', 'Boston', '02108', 'USA',  'samantha.lee@example.com', 35);
INSERT INTO Guest VALUES('G00-024', 'Avery Robinson', '24th street', 'Seattle', '98101', 'USA',  'avery.robinson@example.com', 26);
INSERT INTO Guest VALUES('G00-025', 'Alexander Wright', '25th street', 'Chicago', '60601', 'USA',  'alexander.wright@example.com', 31);
INSERT INTO Guest VALUES('G00-026', 'Mia Hernandez', '26th street', 'Miami', '33131', 'USA',  'mia.hernandez@example.com', 28);
INSERT INTO Guest VALUES('G00-027', 'Nathan Johnson', '27th avenue', 'Los Angeles', '90014', 'USA',  'nathan.johnson@example.com', 31);
INSERT INTO Guest VALUES('G00-028', 'Olivia White', '28th street', 'Chicago', '60601', 'USA',  'olivia.white@example.com', 26);
INSERT INTO Guest VALUES('G00-029', 'William Martinez', '29th street', 'Houston', '77002', 'USA', 'william.martinez@example.com', 35);
INSERT INTO Guest VALUES('G00-030', 'Avery Lee', '30th street', 'Atlanta', '30303', 'USA',  'avery.lee@example.com', 30);
INSERT INTO Guest VALUES('G00-031', 'Hannah Moore', '31st avenue', 'Seattle', '98101', 'USA',  'hannah.moore@example.com', 23);
INSERT INTO Guest VALUES('G00-032', 'Aiden Clark', '32nd street', 'San Francisco', '94105', 'USA',  'aiden.clark@example.com', 25);
INSERT INTO Guest VALUES('G00-033', 'Ella Baker', '33rd avenue', 'New York', '10001', 'USA',  'ella.baker@example.com', 29);
INSERT INTO Guest VALUES('G00-034', 'Noah Green', '34th street', 'Austin', '78701', 'USA',  'noah.green@example.com', 32);
INSERT INTO Guest VALUES('G00-035', 'Avery Hill', '35th avenue', 'Denver', '80202', 'USA',  'avery.hill@example.com', 28);
INSERT INTO Guest VALUES('G00-036', 'Oliver Cooper', '36th street', 'Chicago', '60601', 'USA',  'oliver.cooper@example.com', 30);
INSERT INTO Guest VALUES('G00-037', 'Sophia Adams', '37th avenue', 'Los Angeles', '90071', 'USA',  'sophia.adams@example.com', 26);
INSERT INTO Guest VALUES('G00-038', 'William Lee', '38th street', 'Philadelphia', '19102', 'USA',  'william.lee@example.com', 33);
INSERT INTO Guest VALUES('G00-039', 'Chloe Foster', '39th avenue', 'Dallas', '75201', 'USA',  'chloe.foster@example.com', 31);
INSERT INTO Guest VALUES('G00-040', 'Henry Campbell', '40th street', 'Boston', '02108', 'USA',  'henry.campbell@example.com', 27);
INSERT INTO Guest VALUES('G00-041', 'Isabella James', '41st avenue', 'Miami', '33131', 'USA',  'isabella.james@example.com', 29);
INSERT INTO Guest VALUES('G00-042', 'Jacob Perez', '42nd street', 'San Francisco', '94102', 'USA', 'jacob.perez@example.com', 34);
INSERT INTO Guest VALUES('G00-043', 'Olivia Brown', '43rd street', 'New York', '10001', 'USA',  'olivia.brown@example.com', 26);
INSERT INTO Guest VALUES('G00-044', 'William Lee', '44th street', 'Seattle', '98101', 'USA',  'william.lee@example.com', 31);
INSERT INTO Guest VALUES('G00-045', 'Sophia Green', '45th avenue', 'Los Angeles', '90001', 'USA',  'sophia.green@example.com', 28);
INSERT INTO Guest VALUES('G00-046', 'Daniel Martin', '46th avenue', 'Chicago', '60601', 'USA',  'daniel.martin@example.com', 35);
INSERT INTO Guest VALUES('G00-047', 'Olivia Smith', '47th street', 'San Francisco', '94102', 'USA',  'olivia.smith@example.com', 31);
INSERT INTO Guest VALUES('G00-048', 'Liam Johnson', '48th street', 'New York', '10001', 'USA',  'liam.johnson@example.com', 26);
INSERT INTO Guest VALUES('G00-049', 'Avery Rodriguez', '49th avenue', 'Los Angeles', '90001', 'USA',  'avery.rodriguez@example.com', 22);
INSERT INTO Guest VALUES('G00-050', 'Benjamin Perez', '50th street', 'Houston', '77002', 'USA',  'benjamin.perez@example.com', 28);
INSERT INTO Guest VALUES('G00-051', 'Sophia Garcia', '51st street', 'Miami', '33131', 'USA',  'sophia.garcia@example.com', 24);
INSERT INTO Guest VALUES('G00-052', 'Lucas Hernandez', '52nd street', 'Seattle', '98101', 'USA',  'lucas.hernandez@example.com', 27);
INSERT INTO Guest VALUES('G00-053', 'Aria Martin', '53rd avenue', 'Chicago', '60601', 'USA',  'aria.martin@example.com', 33);
INSERT INTO Guest VALUES('G00-054', 'Ethan Thompson', '54th avenue', 'Dallas', '75201', 'USA',  'ethan.thompson@example.com', 29);
INSERT INTO Guest VALUES('G00-055', 'Avery Lewis', '55th street', 'New York', '10001', 'USA',  'avery.lewis@example.com', 26);
INSERT INTO Guest VALUES('G00-056', 'Harper Hall', '56th street', 'Los Angeles', '90001', 'USA',  'harper.hall@example.com', 22);
INSERT INTO Guest VALUES('G00-057', 'Olivia Brown', '57th street', 'San Francisco', '94101', 'USA',  'olivia.brown@example.com', 26);
INSERT INTO Guest VALUES('G00-058', 'Ethan Garcia', '58th street', 'San Diego', '92101', 'USA',  'ethan.garcia@example.com', 32);
INSERT INTO Guest VALUES('G00-059', 'Avery Perez', '59th street', 'Houston', '77001', 'USA',  'avery.perez@example.com', 27);
INSERT INTO Guest VALUES('G00-060', 'Lucas Flores', '60th street', 'Philadelphia', '19102', 'USA',  'lucas.flores@example.com', 23);
INSERT INTO Guest VALUES('G00-061', 'Natalie Martin', '61st avenue', 'Atlanta', '30301', 'USA',  'natalie.martin@example.com', 34);
INSERT INTO Guest VALUES('G00-062', 'Landon Wright', '62nd street', 'Denver', '80202', 'USA',  'landon.wright@example.com', 31);




INSERT INTO Booking VALUES('B00-001', '2023-04-17', '2023-04-18', 2, 'R00-001', 'G00-001' , 'expired');
INSERT INTO Booking VALUES('B00-002', '2023-04-17', '2023-04-18', 1, 'R00-002', 'G00-002' , 'expired');
INSERT INTO Booking VALUES('B00-003', '2023-04-17', '2023-04-18', 3, 'R00-003', 'G00-003' , 'expired');
INSERT INTO Booking VALUES('B00-004', '2023-04-17', '2023-04-18', 2, 'R00-004', 'G00-004' , 'expired');
INSERT INTO Booking VALUES('B00-005', '2023-04-17', '2023-04-18', 4, 'R00-005', 'G00-005' , 'expired');
INSERT INTO Booking VALUES('B00-006', '2023-04-17', '2023-04-18', 1, 'R00-006', 'G00-006' , 'expired');
INSERT INTO Booking VALUES('B00-007', '2023-04-17', '2023-04-18', 2, 'R00-007', 'G00-007' , 'expired');
INSERT INTO Booking VALUES('B00-008', '2023-04-17', '2023-04-18', 3, 'R00-008', 'G00-008' , 'expired');
INSERT INTO Booking VALUES('B00-009', '2023-04-17', '2023-04-18', 2, 'R00-009', 'G00-009' , 'expired');
INSERT INTO Booking VALUES('B00-010', '2023-04-17', '2023-04-18', 1, 'R00-010', 'G00-010' , 'expired');

INSERT INTO Booking VALUES('B00-011', '2023-04-17', '2023-04-18', 2, 'R00-001', 'G00-011' , 'expired');
INSERT INTO Booking VALUES('B00-012', '2023-04-17', '2023-04-18', 3, 'R00-001', 'G00-012' , 'expired');
INSERT INTO Booking VALUES('B00-013', '2023-04-17', '2023-04-18', 2, 'R00-001', 'G00-013' , 'expired');
INSERT INTO Booking VALUES('B00-014', '2023-04-17', '2023-04-18', 1, 'R00-001', 'G00-014' , 'expired');
INSERT INTO Booking VALUES('B00-015', '2023-04-17', '2023-04-18', 4, 'R00-001', 'G00-015' , 'expired');
INSERT INTO Booking VALUES('B00-016', '2023-04-17', '2023-04-18', 2, 'R00-001', 'G00-016' , 'expired');
INSERT INTO Booking VALUES('B00-017', '2023-04-17', '2023-04-18', 1, 'R00-001', 'G00-017' , 'expired');
INSERT INTO Booking VALUES('B00-018', '2023-04-17', '2023-04-18', 2, 'R00-001', 'G00-018' , 'expired');
INSERT INTO Booking VALUES('B00-019', '2023-04-17', '2023-04-18', 3, 'R00-001', 'G00-019' , 'expired');
INSERT INTO Booking VALUES('B00-020', '2023-04-17', '2023-04-18', 1, 'R00-001', 'G00-020' , 'expired');

INSERT INTO Booking VALUES('B00-021', '2023-04-17', '2023-04-18', 2, 'R00-001', 'G00-021' , 'expired');
INSERT INTO Booking VALUES('B00-022', '2023-04-17', '2023-04-18', 3, 'R00-001', 'G00-022' , 'expired');
INSERT INTO Booking VALUES('B00-023', '2023-04-17', '2023-04-18', 4, 'R00-001', 'G00-023' , 'expired');
INSERT INTO Booking VALUES('B00-024', '2023-04-17', '2023-04-18', 1, 'R00-001', 'G00-024' , 'expired');
INSERT INTO Booking VALUES('B00-025', '2023-04-17', '2023-04-18', 2, 'R00-001', 'G00-025' , 'expired');
INSERT INTO Booking VALUES('B00-026', '2023-04-17', '2023-04-18', 3, 'R00-001', 'G00-026' , 'expired');
INSERT INTO Booking VALUES('B00-027', '2023-04-17', '2023-04-18', 2, 'R00-001', 'G00-027' , 'expired');
INSERT INTO Booking VALUES('B00-028', '2023-04-17', '2023-04-18', 1, 'R00-001', 'G00-028' , 'expired');
INSERT INTO Booking VALUES('B00-029', '2023-04-17', '2023-04-18', 2, 'R00-001', 'G00-029' , 'expired');
INSERT INTO Booking VALUES('B00-030', '2023-04-17', '2023-04-18', 3, 'R00-001', 'G00-030' , 'expired');

INSERT INTO Booking VALUES('B00-031', '2023-04-17', '2023-04-18', 4, 'R00-001', 'G00-031' , 'expired');
INSERT INTO Booking VALUES('B00-032', '2023-04-17', '2023-04-18', 2, 'R00-001', 'G00-032' , 'expired');
INSERT INTO Booking VALUES('B00-033', '2023-04-17', '2023-04-18', 1, 'R00-001', 'G00-033' , 'expired');
INSERT INTO Booking VALUES('B00-034', '2023-04-17', '2023-04-18', 2, 'R00-001', 'G00-034' , 'expired');
INSERT INTO Booking VALUES('B00-035', '2023-04-17', '2023-04-18', 3, 'R00-001', 'G00-035' , 'expired');
INSERT INTO Booking VALUES('B00-036', '2023-04-17', '2023-04-18', 2, 'R00-001', 'G00-036' , 'expired');
INSERT INTO Booking VALUES('B00-037', '2023-04-17', '2023-04-18', 1, 'R00-001', 'G00-037' , 'expired');
INSERT INTO Booking VALUES('B00-038', '2023-04-17', '2023-04-18', 3, 'R00-001', 'G00-038' , 'expired');
INSERT INTO Booking VALUES('B00-039', '2023-04-17', '2023-04-18', 2, 'R00-001', 'G00-039' , 'expired');
INSERT INTO Booking VALUES('B00-040', '2023-04-17', '2023-04-18', 1, 'R00-001', 'G00-040' , 'expired');

INSERT INTO Booking VALUES('B00-041', '2023-04-17', '2023-04-18', 4, 'R00-001', 'G00-041' , 'expired');
INSERT INTO Booking VALUES('B00-042', '2023-04-17', '2023-04-18', 3, 'R00-001', 'G00-042' , 'expired');
INSERT INTO Booking VALUES('B00-043', '2023-04-17', '2023-04-18', 2, 'R00-001', 'G00-043' , 'expired');
INSERT INTO Booking VALUES('B00-044', '2023-04-17', '2023-04-18', 1, 'R00-001', 'G00-044' , 'expired');
INSERT INTO Booking VALUES('B00-045', '2023-04-17', '2023-04-18', 3, 'R00-001', 'G00-045' , 'expired');
INSERT INTO Booking VALUES('B00-046', '2023-04-17', '2023-04-18', 2, 'R00-001', 'G00-046' , 'expired');
INSERT INTO Booking VALUES('B00-047', '2023-04-17', '2023-04-18', 1, 'R00-001', 'G00-047' , 'expired');
INSERT INTO Booking VALUES('B00-048', '2023-04-17', '2023-04-18', 2, 'R00-001', 'G00-048' , 'expired');
INSERT INTO Booking VALUES('B00-049', '2023-04-17', '2023-04-18', 3, 'R00-001', 'G00-049' , 'expired');
INSERT INTO Booking VALUES('B00-050', '2023-04-17', '2023-04-18', 4, 'R00-001', 'G00-050' , 'expired');

INSERT INTO Booking VALUES('B00-051', '2023-04-17', '2023-04-18', 2, 'R00-001', 'G00-051' , 'expired');
INSERT INTO Booking VALUES('B00-052', '2023-04-17', '2023-04-18', 1, 'R00-001', 'G00-052' , 'expired');
INSERT INTO Booking VALUES('B00-053', '2023-04-17', '2023-04-18', 2, 'R00-001', 'G00-053' , 'expired');
INSERT INTO Booking VALUES('B00-054', '2023-04-17', '2023-04-18', 3, 'R00-001', 'G00-054' , 'expired');
INSERT INTO Booking VALUES('B00-055', '2023-04-17', '2023-04-18', 4, 'R00-001', 'G00-055' , 'expired');
INSERT INTO Booking VALUES('B00-056', '2023-04-17', '2023-04-18', 2, 'R00-001', 'G00-056' , 'expired');
INSERT INTO Booking VALUES('B00-057', '2023-04-17', '2023-04-18', 3, 'R00-001', 'G00-057' , 'expired');
INSERT INTO Booking VALUES('B00-058', '2023-04-17', '2023-04-18', 4, 'R00-001', 'G00-058' , 'expired');
INSERT INTO Booking VALUES('B00-059', '2023-04-19', '2023-04-25', 2, 'R00-001', 'G00-059' , 'expired');
INSERT INTO Booking VALUES('B00-060', '2023-04-19', '2023-04-26', 3, 'R00-001', 'G00-060' , 'expired');
INSERT INTO Booking VALUES('B00-061', '2023-04-19', '2023-04-27', 1, 'R00-001', 'G00-061' , 'expired');
INSERT INTO Booking VALUES('B00-062', '2023-04-19', '2023-04-28', 2, 'R00-001', 'G00-062' , 'expired');





INSERT INTO Room VALUES('R00-001','Standard',3,6,40000.00);
INSERT INTO Room VALUES('R00-002','Standard',2,6,40000.00);
INSERT INTO Room VALUES('R00-003','Deluxe',3,6,40000.00);
INSERT INTO Room VALUES('R00-004','Standard',2,6,40000.00);
INSERT INTO Room VALUES('R00-005','Superior',3,6,40000.00);
INSERT INTO Room VALUES('R00-006','Standard',2,6,40000.00);
INSERT INTO Room VALUES('R00-007','Deluxe',3,6,40000.00);
INSERT INTO Room VALUES('R00-008','Superior',2,6,40000.00);
INSERT INTO Room VALUES('R00-009','Standard',3,6,40000.00);
INSERT INTO Room VALUES('R00-010','Deluxe',2,6,40000.00);

INSERT INTO RoomStatus VALUES('R00-001','Standard',3,6,40000.00,'available','B00-001');
INSERT INTO RoomStatus VALUES('R00-002','Standard',2,6,40000.00,'available','B00-002');
INSERT INTO RoomStatus VALUES('R00-003','Deluxe',3,6,40000.00,'available','B00-003');
INSERT INTO RoomStatus VALUES('R00-004','Standard',2,6,40000.00,'available','B00-004');
INSERT INTO RoomStatus VALUES('R00-005','Superior',3,6,40000.00,'available','B00-005');
INSERT INTO RoomStatus VALUES('R00-006','Standard',2,6,40000.00,'available','B00-006');
INSERT INTO RoomStatus VALUES('R00-007','Deluxe',3,6,40000.00,'available','B00-007');
INSERT INTO RoomStatus VALUES('R00-008','Superior',2,6,40000.00,'available','B00-008');
INSERT INTO RoomStatus VALUES('R00-009','Standard',3,6,40000.00,'available','B00-009');
INSERT INTO RoomStatus VALUES('R00-010','Deluxe',2,6,40000.00,'available','B00-010');


/*INSERT INTO Booking_Room VALUES('r001', 'bk001');
INSERT INTO Booking_Room VALUES('r002', 'bk002');
INSERT INTO Booking_Room VALUES('r003', 'bk003');
INSERT INTO Booking_Room VALUES('r004', 'bk004');
INSERT INTO Booking_Room VALUES('r005', 'bk005');
INSERT INTO Booking_Room VALUES('r006', 'bk006');
INSERT INTO Booking_Room VALUES('r007', 'bk007');
INSERT INTO Booking_Room VALUES('r008', 'bk008');
INSERT INTO Booking_Room VALUES('r009', 'bk009');
INSERT INTO Booking_Room VALUES('r010', 'bk010');*/


INSERT INTO Payment VALUES('P00-001',  'B00-001' , 'G00-001' , 10000.00 , '2023-03-14');
INSERT INTO Payment VALUES('P00-002', 'B00-002', 'G00-002', 15000.00, '2023-04-15');
INSERT INTO Payment VALUES('P00-003', 'B00-003', 'G00-003', 20000.00, '2023-05-16');
INSERT INTO Payment VALUES('P00-004', 'B00-004', 'G00-004', 25000.00, '2023-05-17');
INSERT INTO Payment VALUES('P00-005', 'B00-005', 'G00-005', 30000.00, '2023-05-18');
INSERT INTO Payment VALUES('P00-006', 'B00-006', 'G00-006', 35000.00, '2023-05-19');
INSERT INTO Payment VALUES('P00-007', 'B00-007', 'G00-007', 40000.00, '2023-05-19');
INSERT INTO Payment VALUES('P00-008', 'B00-008', 'G00-008', 45000.00, '2023-05-19');
INSERT INTO Payment VALUES('P00-009', 'B00-009', 'G00-009', 50000.00, '2023-05-19');
INSERT INTO Payment VALUES('P00-010', 'B00-010', 'G00-010', 55000.00, '2023-05-19');

INSERT INTO Payment VALUES('P00-011', 'B00-011', 'G00-011', 60000.00, '2023-06-01');
INSERT INTO Payment VALUES('P00-012', 'B00-012', 'G00-012', 65000.00, '2023-06-01');
INSERT INTO Payment VALUES('P00-013', 'B00-013', 'G00-013', 70000.00, '2023-06-01');
INSERT INTO Payment VALUES('P00-014', 'B00-014', 'G00-014', 45000.00, '2023-06-01');
INSERT INTO Payment VALUES('P00-015', 'B00-015', 'G00-015', 47000.00, '2023-06-01');
INSERT INTO Payment VALUES('P00-016', 'B00-016', 'G00-016', 48000.00, '2023-06-01');
INSERT INTO Payment VALUES('P00-017', 'B00-017', 'G00-017', 49000.00, '2023-06-01');
INSERT INTO Payment VALUES('P00-018', 'B00-018', 'G00-018', 42000.00, '2023-06-02');
INSERT INTO Payment VALUES('P00-019', 'B00-019', 'G00-019', 43000.00, '2023-06-03');
INSERT INTO Payment VALUES('P00-020', 'B00-020', 'G00-020', 44000.00, '2023-06-04');
INSERT INTO Payment VALUES('P00-021', 'B00-021', 'G00-021', 40000.00, '2023-06-05');
INSERT INTO Payment VALUES('P00-022', 'B00-022', 'G00-022', 39000.00, '2023-06-06');
INSERT INTO Payment VALUES('P00-023', 'B00-023', 'G00-023', 38000.00, '2023-06-07');
INSERT INTO Payment VALUES('P00-024', 'B00-024', 'G00-024', 47000.00, '2023-06-08');
INSERT INTO Payment VALUES('P00-025', 'B00-025', 'G00-025', 48000.00, '2023-06-09');
INSERT INTO Payment VALUES('P00-026', 'B00-026', 'G00-026', 49000.00, '2023-06-10');
INSERT INTO Payment VALUES('P00-027', 'B00-027', 'G00-027', 45000.00, '2023-06-11');
INSERT INTO Payment VALUES('P00-028', 'B00-028', 'G00-028', 44000.00, '2023-06-12');
INSERT INTO Payment VALUES('P00-029', 'B00-029', 'G00-029', 43000.00, '2023-06-13');
INSERT INTO Payment VALUES('P00-030', 'B00-030', 'G00-030', 41000.00, '2023-06-14');
INSERT INTO Payment VALUES('P00-031', 'B00-031', 'G00-031', 38000.00, '2023-06-15');
INSERT INTO Payment VALUES('P00-032', 'B00-032', 'G00-032', 37000.00, '2023-06-16');

/*
INSERT INTO Payment VALUES('p033', 'bk033', 'g033', 35000.00, '2023-03-27');
INSERT INTO Payment VALUES('p034', 'bk034', 'g034', 42000.00, '2023-03-28');
INSERT INTO Payment VALUES('p035', 'bk035', 'g035', 43000.00, '2023-03-29');
INSERT INTO Payment VALUES('p036', 'bk036', 'g036', 44000.00, '2023-04-01');
INSERT INTO Payment VALUES('p037', 'bk037', 'g037', 41000.00, '2023-04-04');
INSERT INTO Payment VALUES('p038', 'bk038', 'g038', 39000.00, '2023-04-05');
INSERT INTO Payment VALUES('p039', 'bk039', 'g039', 42000.00, '2023-04-05');
INSERT INTO Payment VALUES('p040', 'bk040', 'g040', 44000.00, '2023-04-06');
INSERT INTO Payment VALUES('p041', 'bk041', 'g041', 35000.00, '2023-04-06');
INSERT INTO Payment VALUES('p042', 'bk042', 'g042', 42000.00, '2023-04-06');
INSERT INTO Payment VALUES('p043', 'bk043', 'g043', 39000.00, '2023-04-07');
INSERT INTO Payment VALUES('p044', 'bk044', 'g044', 43000.00, '2023-04-07');
INSERT INTO Payment VALUES('p045', 'bk045', 'g045', 42000.00, '2023-04-07');
INSERT INTO Payment VALUES('p046', 'bk046', 'g046', 38000.00, '2023-04-08');
INSERT INTO Payment VALUES('p047', 'bk047', 'g047', 44000.00, '2023-04-08');
INSERT INTO Payment VALUES('p048', 'bk048', 'g048', 40000.00, '2023-04-08');
INSERT INTO Payment VALUES('p049', 'bk049', 'g049', 39000.00, '2023-04-09');
INSERT INTO Payment VALUES('p050', 'bk050', 'g050', 42000.00, '2023-04-10');
INSERT INTO Payment VALUES('p051', 'bk051', 'g051', 47000.00, '2023-04-10');
INSERT INTO Payment VALUES('p052', 'bk052', 'g052', 36000.00, '2023-04-10');
INSERT INTO Payment VALUES('p053', 'bk053', 'g053', 43000.00, '2023-04-10');
INSERT INTO Payment VALUES('p054', 'bk054', 'g054', 48000.00, '2023-04-10');
INSERT INTO Payment VALUES('p055', 'bk055', 'g055', 45000.00, '2023-04-11');
INSERT INTO Payment VALUES('p056', 'bk056', 'g056', 39000.00, '2023-04-12');
INSERT INTO Payment VALUES('p057', 'bk057', 'g057', 42000.00, '2023-04-13');
INSERT INTO Payment VALUES('p058', 'bk058', 'g058', 38000.00, '2023-04-14');
INSERT INTO Payment VALUES('p059', 'bk059', 'g059', 37000.00, '2023-04-15');
INSERT INTO Payment VALUES('p060', 'bk060', 'g060', 45000.00, '2023-04-16');
INSERT INTO Payment VALUES('p061', 'bk061', 'g061', 41000.00, '2023-04-17');
INSERT INTO Payment VALUES('p062', 'bk062', 'g062', 39000.00, '2023-04-22');*/


DROP TABLE  User;

CREATE TABLE  User (
                                    username VARCHAR(50) NOT NULL,
                                    password VARCHAR(50) NOT NULL
);










