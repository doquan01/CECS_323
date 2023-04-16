INSERT INTO CUSTOMERS (CUSTOMER_ID, FIRST_NAME, LAST_NAME, PHONE, STREET, ZIP)
VALUES (0, 'John', 'Apale', '714-888-0000', '1234 University Way', '98000'),
       (1, 'Quan', 'Do', '562-888-5000', '1600 Pennsylvania Ave', '63000'),
       (2, 'Matthew', 'Chung', '888-222-5930', '1300 City Drive', '92800');

INSERT INTO PRODUCTS(UPC, MFGR, MODEL, PROD_NAME, UNIT_LIST_PRICE, UNITS_IN_STOCK)
VALUES ('72527273070', 'Dewalt', '42', 'Cordless Impact Driver', 59.99, 100),
       ('72527273071', 'Dewalt', '43', 'Cordless Saw', 49.99, 100),
       ('72527273072', 'Dewalt', '44', '6 in. angle grinder', 213.20, 100),
       ('72527273073', 'Milwaukee', '25', 'Metal Cutting Circular Saw', 128.99, 50),
       ('72527273074', 'Milwaukee', '26', 'M18 Hammer Drill/Impact Combo Kit', 329.00, 40),
       ('72527273075', 'Ryobi', '11', '7-1/4 in. Compound Sliding Miter Saw', 189.00, 7),
       ('72527273076', 'Ryobi', '13', '18V 4-Tool Combo Kit', 169.99, 15),
       ('72527273077', 'Ryobi', '12', '3-1/4 in. Planer', 69.99, 30);

Delete
FROM CUSTOMERS;
Delete
FROM PRODUCTS;