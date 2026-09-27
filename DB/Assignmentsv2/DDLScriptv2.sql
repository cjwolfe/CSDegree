-- version 2 for 4. dml coding exercise

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS DEPENDENT;
DROP TABLE IF EXISTS WORKS_ON;
DROP TABLE IF EXISTS PROJECT;
DROP TABLE IF EXISTS DEPT_LOCATIONS;
-- ALTER TABLE IF EXISTS EMPLOYEE DROP CONSTRAINT IF EXISTS fk_emp_dept;
ALTER TABLE EMPLOYEE DROP FOREIGN KEY fk_emp_dept;
DROP TABLE IF EXISTS DEPARTMENT;
DROP TABLE IF EXISTS EMPLOYEE;
SET FOREIGN_KEY_CHECKS =1;

CREATE TABLE EMPLOYEE (
    Fname       VARCHAR(15)     NOT NULL,
    Minit       CHAR(1),
    Lname       VARCHAR(15)     NOT NULL,
    Ssn         CHAR(9)         NOT NULL,
    Bdate       DATE,
    Address     VARCHAR(50),
    Sex         CHAR(1)         CHECK (Sex IN ('M', 'F')),
    Salary      DECIMAL(10, 2),
    Super_ssn   CHAR(9),
    Dno         INT,
    CONSTRAINT pk_employee PRIMARY KEY (Ssn),
    CONSTRAINT fk_employee_supervisor FOREIGN KEY (Super_ssn) 
        REFERENCES EMPLOYEE(Ssn) 
        ON DELETE SET NULL 
        ON UPDATE CASCADE
);

CREATE TABLE DEPARTMENT (
    Dname           VARCHAR(25) NOT NULL UNIQUE,
    Dnumber         INT         NOT NULL,
    Mgr_ssn         CHAR(9)     NOT NULL,
    Mgr_start_date  DATE,
    CONSTRAINT pk_department PRIMARY KEY (Dnumber),
    CONSTRAINT fk_dept_manager FOREIGN KEY (Mgr_ssn) 
        REFERENCES EMPLOYEE(Ssn) 
        ON DELETE RESTRICT 
        ON UPDATE CASCADE
);

ALTER TABLE EMPLOYEE 
    ADD CONSTRAINT fk_emp_dept FOREIGN KEY (Dno) 
        REFERENCES DEPARTMENT(Dnumber) 
        ON DELETE SET NULL 
        ON UPDATE CASCADE;

CREATE TABLE DEPT_LOCATIONS (
    Dnumber     INT         NOT NULL,
    Dlocation   VARCHAR(20) NOT NULL,
    CONSTRAINT pk_dept_locations PRIMARY KEY (Dnumber, Dlocation),
    CONSTRAINT fk_dept_loc_dept FOREIGN KEY (Dnumber) 
        REFERENCES DEPARTMENT(Dnumber) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE
);

CREATE TABLE PROJECT (
    Pname       VARCHAR(25) NOT NULL UNIQUE,
    Pnumber     INT         NOT NULL,
    Plocation   VARCHAR(20),
    Dnum        INT         NOT NULL,
    CONSTRAINT pk_project PRIMARY KEY (Pnumber),
    CONSTRAINT fk_project_dept FOREIGN KEY (Dnum) 
        REFERENCES DEPARTMENT(Dnumber) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE
);

CREATE TABLE WORKS_ON (
    Essn    CHAR(9)         NOT NULL,
    Pno     INT             NOT NULL,
    Hours   DECIMAL(4, 1),
    CONSTRAINT pk_works_on PRIMARY KEY (Essn, Pno),
    CONSTRAINT fk_works_on_emp FOREIGN KEY (Essn) 
        REFERENCES EMPLOYEE(Ssn) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE,
    CONSTRAINT fk_works_on_proj FOREIGN KEY (Pno) 
        REFERENCES PROJECT(Pnumber) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE
);

CREATE TABLE DEPENDENT (
    Essn            CHAR(9)     NOT NULL,
    Dependent_name  VARCHAR(20) NOT NULL,
    Sex             CHAR(1)     CHECK (Sex IN ('M', 'F')),
    Bdate           DATE,
    Relationship    VARCHAR(15),
    CONSTRAINT pk_dependent PRIMARY KEY (Essn, Dependent_name),
    CONSTRAINT fk_dependent_emp FOREIGN KEY (Essn) 
        REFERENCES EMPLOYEE(Ssn) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE
);

INSERT INTO EMPLOYEE (Fname, Minit, Lname, Ssn, Bdate, Address, Sex, Salary, Super_ssn, Mgr_ssn, Dno) VALUES (('James', 'E', 'Borg', 888665555, '1937-11-10', '450 Stone, Houston, TX', 'M', 55000, null, 1),
('Franklin', 'T', 'Wong', 333445555, '1955-12-08', '638 Voss, Houston, TX', 'M', 40000, 888665555, 5),
('Jennifer', 'S', 'Wallace', 987654321, '1941-06-20', '291 Berry, Bellaire, TX', 'F', 43000, 888665555, 4),
('John', 'B', 'Smith', 123456789, '1965-01-09', '731 Fondren, Houston, TX', 'M', 30000, 333445555, 5),
('Ramesh', 'K', 'Narayan', 666884444, '1962-09-15', '975 Fire Oak, Humble, TX', 'M', 38000, 333445555, 5),
('Joyce', 'A', 'English', 453453453, '1972-07-31', '5631 Rice, Houston, TX', 'F', 25000, 333445555, 5),
('Alicia', 'J', 'Zelaya', 999887777, '1968-01-19', '3321 Castle, Spring, TX', 'F', 25000, 987654321, 4),
('Ahmad', 'V', 'Jabbar', 987987987, '1969-03-29', '980 Dallas, Houston, TX', 'M', 25000, 987654321, 4));



-- DESC EMPLOYEE;
-- DESC DEPARTMENT;
-- DESC DEPT_LOCATIONS;
-- DESC PROJECT;
-- DESC WORKS_ON;
-- DESC DEPENDENT;
