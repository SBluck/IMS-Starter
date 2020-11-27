drop schema ims;
CREATE SCHEMA IF NOT EXISTS `ims`;
USE `ims` ;
CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) NULL DEFAULT NULL,
    `surname` VARCHAR(40) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `ims`.`items` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(40) NULL DEFAULT NULL,
    `value` DECIMAL(5,2) NULL DEFAULT NULL,
    `quantity` INT(4) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `ims`.`orders` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `customer_id` INT(11) NOT NULL,
     PRIMARY KEY (`id`),
     CONSTRAINT `fk_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customers`(`id`)
);
CREATE TABLE IF NOT EXISTS `ims`.`order_items` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
    `order_id` INT(11) NOT NULL,
    `item_id` INT(11) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders`(`id`),
    CONSTRAINT `fk_item_id` FOREIGN KEY (`item_id`) REFERENCES `items`(`id`)
);