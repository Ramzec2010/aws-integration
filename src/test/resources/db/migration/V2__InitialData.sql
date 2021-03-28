INSERT INTO PRODUCT (name, price) VALUES ('Get', 100);
INSERT INTO PRODUCT (name, price) VALUES ('Update', 200);
INSERT INTO PRODUCT (name, price) VALUES ('Delete', 300);


INSERT INTO CATEGORY (name, description) VALUES ('Get', 'Get category');
INSERT INTO CATEGORY (name, description) VALUES ('Update', 'Update category');
INSERT INTO CATEGORY (name, description) VALUES ('Delete', 'Delete category');

INSERT INTO PRODUCT_CATEGORY_RELATION (PRODUCT_ID, CATEGORY_ID) VALUES (1,1);
INSERT INTO PRODUCT_CATEGORY_RELATION (PRODUCT_ID, CATEGORY_ID) VALUES (1,2);