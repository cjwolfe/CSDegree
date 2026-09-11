-- temp storage for sql

drop table if exists department;

create table department(
	dname varchar (20),
	dnumber char (4) primary KEY,
	mgr_ssn varchar(9),
	mgr_start_date date	
);

-- alter table employee 
-- add constraint fk_employee_department
-- foreign key (dno) references department(dnumber);

create table dept_locations(
	dnumber char (4) references department(dnumber),
	dlocation varchar (20),
	primary KEY(dnumber,dlocation)
);

drop table if exists project;

create table project(
	pname varchar(20),
	pnumber char(8) primary_KEY,
	plocation varchar(20),
	dnum char(4)
);

drop table if exists works_on;

create table works_on(
	essn varchar(9),
	pno char(8),
	hours int,
	primary KEY(essn,pno)
);

drop table if exists dependent;
create table dependent(
	essn varchar(9),
	dependent_name varchar(30),
	sex char(1),
	bdate date,
	relationship varchar(30),
	primary KEY(essn,dependent_name)
);

desc employee;
