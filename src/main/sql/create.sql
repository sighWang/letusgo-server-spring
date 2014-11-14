DROP DATABASE IF EXISTS letusgo;
CREATE DATABASE letusgo DEFAULT CHARACTER SET utf8;
USE letusgo;


## create category list
CREATE TABLE category (
  id            INT AUTO_INCREMENT PRIMARY KEY ,
  category_name      VARCHAR(50) NOT NULL
);


## create item list
CREATE TABLE item (
  id            INT AUTO_INCREMENT PRIMARY KEY ,
  barcode       VARCHAR(50) NOT NULl ,
  name          VARCHAR(50) NOT NULL ,
  unit          VARCHAR(50) NOT NULL ,
  price         DOUBLE NOT NULL ,
  category_id   INT REFERENCES category(id)
);


## create cart list
CREATE TABLE cart (
  id            INT AUTO_INCREMENT PRIMARY KEY ,
  item_id       INT REFERENCES item(id),
  number        DOUBLE NOT NULL
);

## insert original category data
INSERT INTO category (category_name)
    VALUES ('饮料'),
      ('水果'),
      ('日用品'),
      ('运动器材');

## insert original data
INSERT INTO item (barcode, name, unit, price, category_id)
    VALUES ('ITEM000001', '雪碧', '瓶', 3.50, 1),
      ('ITEM000002', '苹果', '斤', 10.00, 2),
      ('ITEM000003', '香蕉', '斤', 5.00, 2),
      ('ITEM000004', '电池', '个', 5.00, 3),
      ('ITEM000005', '荔枝', '斤', 10.00, 2),
      ('ITEM000006', '羽毛球拍', '副', 100.00, 4),
      ('ITEM000007', '袜子', '双', 5.00, 3),
      ('ITEM000008', '牙刷', '个', 5.00, 3);
## insert original data
INSERT INTO cart (item_id, number)
    VALUES (2,2),
      (3,2);