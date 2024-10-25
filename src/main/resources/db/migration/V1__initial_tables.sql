CREATE TABLE category (
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    name varchar(128),
    created timestamp,
    updated timestamp
);

CREATE UNIQUE INDEX idx_category_name ON category(name);

CREATE TABLE product (
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    name varchar(128),
    description varchar(128),
    category_id uuid,
    price integer,
    inventory integer,
    created timestamp,
    updated timestamp
);

CREATE INDEX idx_product_category_id ON product(category_id);

CREATE TABLE customer (
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    first_name varchar(128),
    last_name varchar(128),
    email varchar(255),
    created timestamp,
    updated timestamp
);

CREATE UNIQUE INDEX idx_email ON customer(email);

CREATE TABLE order (
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    customer_id uuid,
    status varchar(16)
    created timestamp,
    updated timestamp    
);

CREATE INDEX idx_order_customer_id ON order(customer_id);
CREATE INDEX idx_order_status ON order(status);

CREATE TABLE order_line (
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    order_id uuid,
    product_id uuid,
    created timestamp,
    updated timestamp
);




