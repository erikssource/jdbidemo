INSERT INTO category (id, name, created, updated) VALUES ('b1d7d1af-3004-4d6b-bf1a-df6e66f32dff', 'Fruits', now(), now());

INSERT INTO product (id, name, description, category_id, price, inventory, created, updated)
  VALUES ('7fd91807-f461-4946-b604-2e5cb2dcf6b9', 'Apple', 'A tasty red apple',
          'b1d7d1af-3004-4d6b-bf1a-df6e66f32dff', 125, 50, now(), now()),
         ('c01d180c-f0ba-4233-8d2b-f526dc4bbec0', 'Pear', 'A mostly good pear',
          'b1d7d1af-3004-4d6b-bf1a-df6e66f32dff', 119, 144, now(), now());


INSERT INTO customer (id, first_name, last_name, email, created, updated)
  VALUES ('918fa754-8bc9-438f-ba69-3adb2f8ef49e', 'John', 'Doe', 'jdoe@example.com', now(), now()),
         ('02334ccd-c5ed-4508-a9d2-1312284991cd', 'Mary', 'Smith', 'msmith@example.com', now(), now());

INSERT INTO orders(id, customer_id, status, created, updated)
  VALUES ('8b1e68cb-3a71-442b-a57e-ba8f163a4b49', '918fa754-8bc9-438f-ba69-3adb2f8ef49e', 'COMPLETED', now(), now()),
         ('43b555cd-37e0-4761-9d4f-aecc334d72c2', '02334ccd-c5ed-4508-a9d2-1312284991cd', 'CREATED', now(), now());

INSERT INTO order_line(id, order_id, product_id, quantity, price, created, updated)
  VALUES ('05dd4f2d-c0d8-4588-ba2b-4ddac00e03cf', '8b1e68cb-3a71-442b-a57e-ba8f163a4b49', '7fd91807-f461-4946-b604-2e5cb2dcf6b9', 2, 125, now(), now()),
         ('3cc44dee-eff4-4122-8d2e-ffd63a9855b1', '8b1e68cb-3a71-442b-a57e-ba8f163a4b49', 'c01d180c-f0ba-4233-8d2b-f526dc4bbec0', 5, 119, now(), now()),
         ('00700c1a-d696-4586-80f0-867fa322928d', '43b555cd-37e0-4761-9d4f-aecc334d72c2', 'c01d180c-f0ba-4233-8d2b-f526dc4bbec0', 3, 119, now(), now());