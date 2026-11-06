
select * from grade;
drop procedure if exists final_proc;

delimiter $$
create procedure final_proc()
begin
		-- declare local variables
	declare s int;
	declare q int;
	declare e int;
	declare ifend int default 0; -- handles bad things
		-- declare cursors
	declare cur_final cursor for 
		select stu_no, exam. quiz, from grade;
		-- declare handler
	declare continue handler for not found set ifend = 1; -- set keyword
		-- open cursors
	open cur_final; -- db will execute the query and store the result in memory, pointer points to a memory address
	loop_cur : loop
		-- updating ifend creates a loop. By checking ifend first, 
	loop
	ifend = 0	
		-- iterate cursor
	fetch cur_final into s, e, q; 
		if ifend = 1 then
			leave loop_cur;
		end if
				-- compare
		if e > q or q is null then
				update grade set final = e where stu_no = s;
		else
				update grade set final = q where stu_no = s;
		end if;
		-- clone cursor
	end loop
end$$
delimiter ;
