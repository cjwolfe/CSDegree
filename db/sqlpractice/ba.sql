
DROP TABLE IF EXISTS wolfecj1.bank_account;

CREATE TABLE wolfecj1.bank_account (
	account_no INT PRIMARY KEY AUTO_INCREMENT,
    account_name CHAR(20) NOT NULL,
    balance INT UNSIGNED

)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS order_record;
CREATE TABLE order_record(
	order_id int auto_increment primary key,
	account_no int,
	constraint acct_no
		foreign key (account_no)
		references bank_account(account_no)
			on delete cascade
			on update cascade,
	amount int,
	order_type char(10),
	order_posted tinyint
);

DROP PROCEDURE IF EXISTS transfer;

delimiter $$
CREATE PROCEDURE transfer (IN from_account INT, IN to_account INT, IN money INT)
BEGIN
	UPDATE bank_account SET balance = balance + money
	WHERE account_no = to_account;
	UPDATE bank_account SET balance = balance - money
	WHERE account_no = from_account;
END$$
create procedure new_order(in a int, in m int, in t char(10), out success tinyint)
begin
		if t like 'debit' then
				-- update bank acct table by subtracting m from accts balance (m can be negative)
				insert into order_record 
						(account_no, amount, order_type)
				values(
						a, -m, t)
				);
				
				-- select 'debit transaction' as status_message;
		elseif t like 'credit' then
				-- insert a new record into order_record table using passed-in information and assign 0 to order_posted
				select 'credit transaction' as status_message;
		else
			
				-- signal statement to throw an error
				select 'value does not match' as status_message;
		end if;
		-- if there is no error, commit changes
		-- somewhere... declare error handler for sqlexception
		-- if error occurs, rollback any changes and display a customized message
end$$

delimiter ;

INSERT INTO bank_account VALUES
	(1, 'Tom', 1000),
	(2, 'Jerry', 1000);

-- test data

call new_order(1,500,'debit');
call new_order(1,800,'debit');
call new_order(1,-800,'debit');
call new_order(2,200,'credit');
call new_order(3,200,'credit');
call new_order(2,200,'order');



SELECT * FROM bank_account;
