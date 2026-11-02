-- Ex1
DROP PROCEDURE IF EXISTS procedure_name;

DELIMITER //

create procedure procedure_name(inout y int)
BEGIN
		declare x int;
		if y = 0 then
				set y = 1;
		elseif y > 1 then
				set x = y - 1;
				while x > 1 do
						set y = y * x;
						set x = x - 1;
				end while;
		end if;		
END //

DELIMITER ;

-- Testing 1
select 'Factorial Calculation' as Part1;
set @val = 5;
select @val as input;
call procedure_name(@val);
select @val as result;

-- Ex2
drop procedure if exists ex_2;

delimiter $$
create procedure ex_2(
		in @stud varchar(20),
		out @finalgr int
		)
begin
	
