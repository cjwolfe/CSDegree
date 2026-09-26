-- Homework 5 SQL
-- C Wolfe

-- Create Tables

CREATE TABLE Admin (
    Id_no INT PRIMARY KEY,
    Pay DECIMAL(10, 2) NOT NULL
);

CREATE TABLE Phys_Therapist (
    Id_no INT PRIMARY KEY,
    Manager_Id_no INT NOT NULL,
    Role VARCHAR(50) NOT NULL,
    Pay DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (Manager_Id_no) REFERENCES Admin(Id_no)
);

CREATE TABLE Coach (
    Id_no INT PRIMARY KEY,
    Manager_Id_no INT NOT NULL,
    Is_head_coach BOOLEAN NOT NULL,
    Pay DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (Manager_Id_no) REFERENCES Admin(Id_no)
);

CREATE TABLE Player (
    Id_no INT PRIMARY KEY,
    Coach_no INT NOT NULL,
    Manager_Id_no INT NOT NULL,
    Position VARCHAR(50) NOT NULL,
    Pay DECIMAL(10, 2) NOT NULL,
    Next_Phys_Appt_date DATE,
    FOREIGN KEY (Coach_no) REFERENCES Coach(Id_no),
    FOREIGN KEY (Manager_Id_no) REFERENCES Admin(Id_no)
);

-- Sample Data

INSERT INTO Admin (Id_no, Pay) VALUES
(101, 85000.00),
(102, 92000.00);

INSERT INTO Phys_Therapist (Id_no, Manager_Id_no, Role, Pay) VALUES
(201, 101, 'Sports Psychologist', 65000.00),
(202, 101, 'Physio', 62000.00);

INSERT INTO Coach (Id_no, Manager_Id_no, Is_head_coach, Pay) VALUES
(301, 102, TRUE, 120000.00),
(302, 102, FALSE, 75000.00);

INSERT INTO Player (Id_no, Coach_no, Manager_Id_no, Position, Pay, Next_Phys_Appt_date) VALUES
(401, 301, 102, 'Forward', 110000.00, '2026-10-15'),
(402, 302, 102, 'Goalkeeper', 95000.00, '2026-10-22');
