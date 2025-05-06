create or replace procedure delete_orders_by_customer(
    customerid uuid
)
language plpgsql
as $$
begin
    -- Remove order lines
    delete from order_line where order_id in (SELECT id from orders WHERE customer_id=customerid);

    -- Remove orders from order table
    delete from orders where customer_id=customerid;

    commit;
end;$$;