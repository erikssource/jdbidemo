create or replace procedure delete_order_by_id(
    orderid uuid
)
language plpgsql
as $$
begin
    -- Remove order lines
    delete from order_line where order_id=orderid;

    -- Remove from order table
    delete from orders where id=orderid;

    commit;
end;$$;