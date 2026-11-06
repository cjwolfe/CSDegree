drop table if exists inventory;
drop table if exists books;


create table books (
		book_id int auto_increment primary key,
		title varchar(100),
		author varchar(50),
		price decimal(10,2)
);

create table inventory (
		inventory_id int auto_increment primary key,
		book_id int,
		stock int
);

alter table inventory
add constraint book_ids
foreign key (book_id) references books(book_id);

delimiter //
create trigger after_book_insert
after insert on books
for each row
begin
    insert into inventory (book_id, stock)
    values (new.book_id, 0);
end //
create trigger after_book_delete
after delete on books
for each row
begin
    delete from inventory
    where book_id = old.book_id;
end //
create trigger before_inventory_insert
before insert on inventory
for each row
begin
    if exists (select 1 from inventory where book_id = new.book_id) then
        signal sqlstate '45000'
        set message_text = 'This book already exists. Please update the stock instead.';
    end if;
end //
create trigger before_inventory_update
before update on inventory
for each row
begin
    if new.stock < 0 then
        set new.stock = old.stock;
    end if;
end //
delimiter ;
-- Ex 1
select "Inserting Sample Data" as "Exercise 1";
select * from books;
select * from inventory;

insert into books (title, author, price)
values
	("Nightfall and other stories", "Isaac Asimov", 20.00),
	("NO BULLSHIT guide to LINEAR ALGEBRA", "Ivan Savov", 10.25),
	("How To Prove It: A Structured Approach Second Edition", "Daniel J. Velleman", 59.94),
	("Creative Illustration", "Andrew Loomis", 13.99),
	("1984", "George Orwell", 19.99),
	("Animal Farm", "George Orwell", 20.00),
	("Gone With the Wind", "Margaret Mitchell", 19.99),
	("Pride and Prejudice", "Jane Austen", 15.45),
	("A Tale of Two Cities", "Charles Dickens", 19.89)
;
select * from inventory;

select * from books;
-- Ex 2
select "Deleting from inventory book id 2" as "Exercise 2";

delete from inventory where book_id = 2;

select * from inventory;
-- Ex 3
select "Inserting into inventory book an inaccurate id" as "Exercise 3";
insert into inventory(book_id, stock)
values
	(1, 10);

-- Ex 4
select "Printing negative stock update" as "Exercise 4";
update inventory
set stock = 15
where book_id = 1;

select * from inventory where book_id = 1;

select "Changing Stock to -10 for book_id 1; expected no change" as "Exercise 4 pt 2";
update inventory
set stock = -10
where book_id = 1;

select * from inventory where book_id = 1;
