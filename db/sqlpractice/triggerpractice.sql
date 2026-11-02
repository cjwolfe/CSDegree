DROP TABLE IF EXISTS course_info;
-- Create the course_info table
CREATE TABLE course_info (
    idCourse INT PRIMARY KEY,
    course_code VARCHAR(15) NOT NULL,
    course_description VARCHAR(100) NOT NULL,
    department VARCHAR(50) NOT NULL,
    units INT NOT NULL,
    offering_term VARCHAR(20),
    course_format VARCHAR(20),
    capacity INT
);

-- Insert sample course data
INSERT INTO course_info (idCourse, course_code, course_description, department, units, offering_term, course_format, capacity) VALUES
(101, 'FIN-101', 'Introduction to Finance', 'Finance', 4, 'Fall', 'In-person', 35),
(102, 'CS-319', 'Image Processing', 'Comp. Sci.', 3, 'Spring', 'In-person', 30),
(103, 'CS-347', 'Database System Concepts', 'Comp. Sci.', 3, 'Fall', 'Online', 50),
(104, 'EE-181', 'Intro. to Digital Systems', 'Elec. Eng.', 3, 'Fall', 'In-person', 40),
(105, 'FIN-201', 'Investment Banking', 'Finance', 3, 'Spring', 'In-person', 25),
(106, 'PHY-101', 'Physical Principles', 'Physics', 4, 'Fall', 'In-person', 60);



select * from course_info;
desc course_info;


delimiter $$
create trigger cs_only_trigger
	before insert
	on course_info for each row
begin
		if left(new.course_number, 2) <> 'CS' then
				signal sqlstate 'ERROR' set message_text='Cannot enter a non-cs course';
		end if;
end
$$
delimiter ;

insert into course_info values ('CS4532', 'Reinforcement Learning');

iselect left('CS4532',2) <> 'CS';
