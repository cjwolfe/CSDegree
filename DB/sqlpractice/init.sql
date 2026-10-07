-- sql practice area

drop table if exists inventory;

create table inventory (
	id integer primary key autoincrement,
	item_name text not null,
	quantity integer default 0,
	unit_price real
);

insert into inventory (item_name, quantity, unit_price) values
	('Widget A', 15, 2.50),
	('Widget B', 0, 9.99),
	('Widget B', 42, 1.25);

select item_name, quantity * unit_price as total_value from inventory where quantity > 0;


