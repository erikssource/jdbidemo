drop procedure if exists delete_orders_by_customer(uuid);
drop procedure if exists delete_order_by_id(uuid);
drop table if exists category;
drop table if exists customer;
drop table if exists order_line;
drop table if exists orders;
drop table if exists product;
drop table if exists flyway_schema_history;