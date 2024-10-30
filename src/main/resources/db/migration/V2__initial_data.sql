INSERT INTO category (id, name, created, updated)
VALUES 
    ('5ebe777a-65cf-4355-b965-fab5589882b2', 'Widgets', NOW(), NOW()),
    ('1e23fe5c-1529-44a7-8406-e4cbea7535c7', 'Cleaning Supplies', NOW(), NOW());

INSERT INTO product (id, name, description, category_id, price, inventory, created, updated)
VALUES
    ('b8e7d89f-19a2-468b-a177-a5a79048bdee', 'Small Cog', 'A small metal cog for machines that need small cogs', '5ebe777a-65cf-4355-b965-fab5589882b2', 600, 45, NOW(), NOW()),
    ('ae476411-8d03-4919-b571-d6aaccd074f4', 'Large Cog', 'A big steel cog for mighty machines', '5ebe777a-65cf-4355-b965-fab5589882b2', 1500, 30, NOW(), NOW()),
    ('fca0ac5e-875c-462e-a112-bf577a1ab103', 'Lever', 'A wooden lever for levering things', '5ebe777a-65cf-4355-b965-fab5589882b2', 800, 150, NOW(), NOW()),
    ('fb85a8c0-b3a9-45cb-8cb6-89a1aa0c0a9b', 'Rod', 'A big, heavy, iron rod', '5ebe777a-65cf-4355-b965-fab5589882b2', 3000, 4, NOW(), NOW()),
    ('3f8d97bf-8c80-4156-a98c-ae9f393664d5', 'Mr. Spiffy', 'A 24oz bottle of a powerful general cleaner', '1e23fe5c-1529-44a7-8406-e4cbea7535c7', 799, 200, NOW(), NOW()),
    ('14965c28-ed65-4720-a94a-dd982c960680', 'Square Sponge', 'One dozen square shaped sponges', '1e23fe5c-1529-44a7-8406-e4cbea7535c7', 399, 100, NOW(), NOW()),
    ('8ffae7ab-46e4-4fd6-837a-888ef9d26e6e', 'Brushy Brush', 'A handy brush', '1e23fe5c-1529-44a7-8406-e4cbea7535c7', 499, 3, NOW(), NOW()),
    ('2e1e821c-1e0d-48ea-9088-4e862ef4e1f3', 'Clear Shine', 'A 16oz bottle of super shiny glass cleaner', '1e23fe5c-1529-44a7-8406-e4cbea7535c7', 650, 44, NOW(), NOW());

INSERT INTO customer (id, first_name, last_name, email, created, updated)
VALUES
    ('5c59adb7-4643-4685-9770-62950973123d', 'John', 'Smith', 'jsmith@example.com', NOW(), NOW()),
    ('1afc6d27-d707-4ea6-add7-1bfb8fe95d76', 'Linda', 'Doe', 'ldoe@example.com', NOW(), NOW());

INSERT INTO orders (id, customer_id, status, created, updated)
VALUES
    ('23f74574-3a4d-4cde-88e9-a46e2ca93f5a', '5c59adb7-4643-4685-9770-62950973123d', 'COMPLETED', NOW(), NOW());

INSERT INTO order_line (id, order_id, product_id, quantity, created, updated)
VALUES
    ('feff9fda-6fa5-485e-85fb-4d1bdeeca8b5', '23f74574-3a4d-4cde-88e9-a46e2ca93f5a', '3f8d97bf-8c80-4156-a98c-ae9f393664d5', 2, NOW(), NOW()),
    ('2742b020-1c27-43dc-85f2-000a65a5dc4f', '23f74574-3a4d-4cde-88e9-a46e2ca93f5a', '14965c28-ed65-4720-a94a-dd982c960680', 1, NOW(), NOW());

