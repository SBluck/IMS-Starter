INSERT INTO `ims`.`customers` (`first_name`, `surname`) VALUES ('jordan', 'harrison');
INSERT INTO `ims`.`customers` (`first_name`, `surname`) VALUES ('jane', 'green');
INSERT INTO `ims`.`items` (`name`, `value`) VALUES ('hand sanitiser 50ml', 4.25);
INSERT INTO `ims`.`items` (`name`, `value`) VALUES ('reusable mask medium', 6.50);
INSERT INTO `ims`.`items` (`name`, `value`) VALUES ('reusable mask small', 5.40);
INSERT INTO `ims`.`orders` (`customer_id`) VALUES (2);
INSERT INTO `ims`.`order_items` (`order_id`, `item_id`) VALUES (1, 1);
INSERT INTO `ims`.`order_items` (`order_id`, `item_id`) VALUES (1, 2);
