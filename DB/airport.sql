drop database if exists testingap;
create database testingap;
use testingap;

drop table if exists hangar;
drop table if exists maintenance_log;
drop table if exists fuels;
drop table if exists personnel;
drop table if exists aircraft;


create table aircraft(
		tail_number varchar(30) primary key,
		manufacturer varchar(30),
		operating_co varchar(30),
		year int,
		hours float(20,1),
		model varchar(50),
		hangar_no int

);


create table hangar (
		hangar_no int primary key,
		hangar_bay varchar(10)
);

create table personnel(
		technician_id varchar(20) primary key,
		name varchar(30),
		quals_owned varchar(40)
);

create table maintenance_log(
		maintenance_item varchar(50) primary key,
		tail_number varchar(30),
		date_performed datetime,
		mx_technician varchar(20),
		quals_reqd varchar(40),
		current_acft_hrs float(20,1),
		projected_next_mx_hrs float(20,1),
		notes text,
		-- constraints
		constraint fk_tn
			foreign key (tail_number)
			references aircraft(tail_number)
				on delete cascade
				on update cascade,
		constraint fk_mx
			foreign key (mx_technician)
			references personnel(technician_id)
				on delete cascade
				on update cascade
);
create table fuels(
	fueling_time datetime primary key,
	liters_fueled float(10,3),
	technician_id varchar(20),
	tail_number varchar(30),
		constraint fk_tn2
			foreign key (tail_number)
			references aircraft(tail_number)
				on delete cascade
				on update cascade,
		constraint fk_mx2
			foreign key (technician_id)
			references personnel(technician_id)
				on delete cascade
				on update cascade
);
alter table aircraft
add constraint fk_hangar
foreign key (hangar_no)
references hangar(hangar_no);

-- adding in some sample data
insert into hangar(hangar_no, hangar_bay)
values
		(1, "Alpha"),
		(2, "Bravo"),
		(3, "Charlie");
insert into aircraft(tail_number, manufacturer, operating_co, year, hours, model, hangar_no)
values
		("89-1690", "Lockheed", "USAF", 1989, 29033.5, "F-15E Strike Eagle", 2),
		("89-0487", "Lockheed", "USAF", 1989, 30033.5, "F-15E Strike Eagle", 3);

insert into personnel(technician_id, name, quals_owned)
values
		("123456789", "G. Dlugiwicz", "Fuels"),
		("011899988", "M. Mario", "Avionics");

insert into maintenance_log(maintenance_item, tail_number, date_performed, mx_technician, quals_reqd, current_acft_hrs, projected_next_mx_hrs, notes)
values
		("Fuel Line Flush", "89-1690", "2026-11-08 21:25:00", "123456789", "Fuels", 29033.5, 32033.5, "Fuel Lines successfully flushed");
insert into fuels(fueling_time, tail_number, liters_fueled, technician_id)
values
		("2026-11-08 22:00:00", "89-1690", 304.56, "011899988");
-- display sample data
select * from aircraft;
select * from hangar;
select * from maintenance_log;
select * from personnel;
select * from fuels;
