SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS PREREQUISITE;
DROP TABLE IF EXISTS GRADE_REPORT;
DROP TABLE IF EXISTS SECTION;
DROP TABLE IF EXISTS COURSE;
DROP TABLE IF EXISTS STUDENT;
SET FOREIGN_KEY_CHECKS = 1;


CREATE TABLE STUDENT (
    Student_number INT,
    Name           VARCHAR(100) NOT NULL,
    Class          INT,
    Major          VARCHAR(50),
    PRIMARY KEY (Student_number)
);

CREATE TABLE COURSE (
    Course_number VARCHAR(15),
    Course_name   VARCHAR(100) NOT NULL UNIQUE,
    Credit_hours  INT NOT NULL DEFAULT 4,
    Department    VARCHAR(50) NOT NULL,
    PRIMARY KEY (Course_number)
);

CREATE TABLE SECTION (
    Section_identifier INT,
    Course_number      VARCHAR(15) NOT NULL,
    Semester           VARCHAR(10) NOT NULL,
    Year               INT NOT NULL,
    Instructor         VARCHAR(100),
    PRIMARY KEY (Section_identifier),
    FOREIGN KEY (Course_number) REFERENCES COURSE(Course_number)
);

CREATE TABLE GRADE_REPORT (
    Student_number     INT,
    Section_identifier INT,
    Grade              CHAR(2),
    PRIMARY KEY (Student_number, Section_identifier),
    FOREIGN KEY (Student_number) REFERENCES STUDENT(Student_number),
    FOREIGN KEY (Section_identifier) REFERENCES SECTION(Section_identifier)
);

CREATE TABLE PREREQUISITE (
    Course_number       VARCHAR(15),
    Prerequisite_number VARCHAR(15),
    PRIMARY KEY (Course_number, Prerequisite_number),
    FOREIGN KEY (Course_number) REFERENCES COURSE(Course_number),
    FOREIGN KEY (Prerequisite_number) REFERENCES COURSE(Course_number)
);

-- DESC STUDENT;
-- DESC COURSE;
-- DESC SECTION;
-- DESC GRADE_REPORT;
-- DESC PREREQUISITE;


