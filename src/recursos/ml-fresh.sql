USE ml-fulfillment-fresh;

INSERT INTO buyer (buyer_id, buyer_name) VALUES ('1', 'Naruto');
INSERT INTO buyer (buyer_id, buyer_name) VALUES ('2', 'Sasuke');
INSERT INTO buyer (buyer_id, buyer_name) VALUES ('3', 'Sakura');
INSERT INTO seller (id, seller_name) VALUES ('1', 'Marina');
INSERT INTO seller (id, seller_name) VALUES ('2', 'Yago');
INSERT INTO seller (id, seller_name) VALUES ('3', 'Monica');
INSERT INTO sector(sector_id, capacity, category, max_capacity) VALUES ('1', '10', 'FF', '1000');
INSERT INTO product(id, bulk, price, product_name, id_seller, validate_date) VALUES ('1', '1', '1', '1', '1', '2022-09-01');
INSERT INTO product(id, bulk, price, product_name, id_seller, validate_date) VALUES ('2', '10', '3', '1', '1', '2022-09-01');
INSERT INTO product (id, bulk, price, product_name,id_seller, validate_date) VALUES ('3', '10', '2', '2','1','2022-07-30');
INSERT INTO product (id, bulk, price, product_name,id_seller, validate_date) VALUES ('4', '5', '2', '2','1','2022-07-30');
INSERT INTO product (id, bulk, price, product_name,id_seller, validate_date) VALUES ('5', '10', '2', '3','1','2022-07-30');
INSERT INTO product (id, bulk, price, product_name,id_seller, validate_date) VALUES ('6', '5', '2', '3','1','2022-07-30');
