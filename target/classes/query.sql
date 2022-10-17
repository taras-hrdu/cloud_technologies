SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';


DROP TABLE IF EXISTS `type_of_equipment`;
DROP TABLE IF EXISTS `equipment`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `equipment_has_user`;
DROP TABLE IF EXISTS `laser_cutter`;
DROP TABLE IF EXISTS `user_has_laser_cutter`;
DROP TABLE IF EXISTS `equipment_transfer`;
DROP TABLE IF EXISTS `equipment_has_equipment_transfer`;
DROP TABLE IF EXISTS `repairs_worker`;
DROP TABLE IF EXISTS `repair`;
DROP TABLE IF EXISTS `equipment_has_repairs`;
DROP TABLE IF EXISTS `laser_cutter_has_repair`;

CREATE SCHEMA IF NOT EXISTS `lab1db` DEFAULT CHARACTER SET utf8 ;
USE `lab1db` ;

CREATE TABLE IF NOT EXISTS `lab1db`.`type_of_equipment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` ENUM('frezer_machine', '3d_printer', '3d_scaner') NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE INDEX idx_type_of_equipment
ON type_of_equipment (type);

CREATE INDEX idx_type_of_equipment_sec
ON type_of_equipment (id);

CREATE TABLE IF NOT EXISTS `lab1db`.`equipment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `inventory_numbers` VARCHAR(5) NOT NULL,
  `color` VARCHAR(20) NULL,
  `body_material` VARCHAR(45) NULL,
  `condition_of_equipment` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE INDEX idx_equipment
ON equipment (color);

CREATE INDEX idx_equipment_sec
ON equipment (inventory_numbers);


CREATE TABLE IF NOT EXISTS `lab1db`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `surname` VARCHAR(30) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `student_number` NVARCHAR(10) NOT NULL,
  `zip` VARCHAR(5) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `surname_UNIQUE` (`surname` ASC) VISIBLE,
  UNIQUE INDEX `student_number_UNIQUE` (`student_number` ASC) VISIBLE)
ENGINE = InnoDB;

CREATE INDEX idx_user
ON user (name);

CREATE INDEX idx_user_sec
ON user (surname);


CREATE TABLE IF NOT EXISTS `lab1db`.`equipment_transfer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `time` DATETIME NOT NULL,
  `start_data` DATE NULL,
  `end_data` DATE NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `time_UNIQUE` (`time` ASC) VISIBLE)
ENGINE = InnoDB;

CREATE INDEX idx_equipment_transfer
ON equipment_transfer (time);

CREATE INDEX idx_equipment_transfer_sec
ON equipment_transfer (start_data);

CREATE TABLE IF NOT EXISTS `lab1db`.`repairs_worker` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `surname_UNIQUE` (`surname` ASC) VISIBLE)
ENGINE = InnoDB;

CREATE INDEX idx_repairs_worker
ON repairs_worker (name);

CREATE INDEX idx_repairs_worker_sec
ON repairs_worker (surname);

CREATE TABLE IF NOT EXISTS `lab1db`.`repair` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `time` DATETIME NOT NULL,
  `start_data` DATE NULL,
  `end_data` DATE NULL,
  `repairs_worker_id` INT NULL,
  `equipment_id` INT NULL,
  `repairs_worker_id1` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `time_UNIQUE` (`time` ASC) VISIBLE,
  INDEX `fk_repair_repairs_worker1_idx` (`repairs_worker_id1` ASC) VISIBLE,
  CONSTRAINT `fk_repair_repairs_worker1`
    FOREIGN KEY (`repairs_worker_id1`)
    REFERENCES `lab1db`.`repairs_worker` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



CREATE TABLE IF NOT EXISTS `lab1db`.`laser_cutter` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `time_using` DATETIME NOT NULL,
  `start_data` DATE NULL,
  `end_data` DATE NULL,
  `equipment_id` INT NOT NULL,
  PRIMARY KEY (`id`, `equipment_id`),
  UNIQUE INDEX `time_using_UNIQUE` (`time_using` ASC) VISIBLE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `lab1db`.`equipment_has_user` (
  `equipment_id` INT NOT NULL,
  `users_id` INT NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  INDEX `fk_equipment_has_users_users1_idx` (`users_id` ASC) VISIBLE,
  INDEX `fk_equipment_has_users_equipment_idx` (`equipment_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_equipment_has_users_equipment`
    FOREIGN KEY (`equipment_id`)
    REFERENCES `lab1db`.`equipment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_equipment_has_users_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `lab1db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



CREATE TABLE IF NOT EXISTS `lab1db`.`equipment_has_equipment_transfer` (
  `equipment_id` INT NOT NULL,
  `equipment_transfer_id` INT NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  INDEX `fk_equipment_has_equipment_transfer_equipment_transfer1_idx` (`equipment_transfer_id` ASC) VISIBLE,
  INDEX `fk_equipment_has_equipment_transfer_equipment1_idx` (`equipment_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_equipment_has_equipment_transfer_equipment1`
    FOREIGN KEY (`equipment_id`)
    REFERENCES `lab1db`.`equipment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_equipment_has_equipment_transfer_equipment_transfer1`
    FOREIGN KEY (`equipment_transfer_id`)
    REFERENCES `lab1db`.`equipment_transfer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `lab1db`.`equipment_has_repairs` (
  `equipment_id` INT NOT NULL,
  `repairs_id` INT NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  INDEX `fk_equipment_has_repairs_repairs1_idx` (`repairs_id` ASC) VISIBLE,
  INDEX `fk_equipment_has_repairs_equipment1_idx` (`equipment_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_equipment_has_repairs_equipment1`
    FOREIGN KEY (`equipment_id`)
    REFERENCES `lab1db`.`equipment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_equipment_has_repairs_repairs1`
    FOREIGN KEY (`repairs_id`)
    REFERENCES `lab1db`.`repair` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `lab1db`.`user_has_laser_cutter` (
  `user_id` INT NOT NULL,
  `laser_cutter_id` INT NOT NULL,
  `laser_cutter_equipment_id` INT NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  INDEX `fk_user_has_laser_cutter_laser_cutter1_idx` (`laser_cutter_id` ASC, `laser_cutter_equipment_id` ASC) VISIBLE,
  INDEX `fk_user_has_laser_cutter_user1_idx` (`user_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_user_has_laser_cutter_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `lab1db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_laser_cutter_laser_cutter1`
    FOREIGN KEY (`laser_cutter_id` , `laser_cutter_equipment_id`)
    REFERENCES `lab1db`.`laser_cutter` (`id` , `equipment_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



CREATE TABLE IF NOT EXISTS `lab1db`.`laser_cutter_has_repair` (
  `laser_cutter_id` INT NOT NULL,
  `laser_cutter_equipment_id` INT NOT NULL,
  `repair_id` INT NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  INDEX `fk_laser_cutter_has_repair_repair1_idx` (`repair_id` ASC) VISIBLE,
  INDEX `fk_laser_cutter_has_repair_laser_cutter1_idx` (`laser_cutter_id` ASC, `laser_cutter_equipment_id` ASC) VISIBLE,
  CONSTRAINT `fk_laser_cutter_has_repair_laser_cutter1`
    FOREIGN KEY (`laser_cutter_id` , `laser_cutter_equipment_id`)
    REFERENCES `lab1db`.`laser_cutter` (`id` , `equipment_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_laser_cutter_has_repair_repair1`
    FOREIGN KEY (`repair_id`)
    REFERENCES `lab1db`.`repair` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO `lab1db`.`type_of_equipment` ( `type`) VALUES ( '3d_scaner');
INSERT INTO `lab1db`.`type_of_equipment` ( `type`) VALUES ( '3d_printer');
INSERT INTO `lab1db`.`type_of_equipment` ( `type`) VALUES ('frezer_machine');
INSERT INTO `lab1db`.`type_of_equipment` ( `type`) VALUES ('3d_scaner');
INSERT INTO `lab1db`.`type_of_equipment` (`type`) VALUES ('frezer_machine');
INSERT INTO `lab1db`.`type_of_equipment` ( `type`) VALUES ( '3d_printer');
INSERT INTO `lab1db`.`type_of_equipment` ( `type`) VALUES ( 'frezer_machine');
INSERT INTO `lab1db`.`type_of_equipment` ( `type`) VALUES ( '3d_scaner');
INSERT INTO `lab1db`.`type_of_equipment` ( `type`) VALUES ( '3d_printer');
INSERT INTO `lab1db`.`type_of_equipment` (`type`) VALUES ('frezer_machine');

INSERT INTO `lab1db`.`equipment` (`id`, `inventory_numbers`, `color`,`body_material`,`condition_of_equipment`) VALUES ('1', '20312', 'black', 'leather','working');
INSERT INTO `lab1db`.`equipment` (`id`, `inventory_numbers`, `color`,`body_material`,`condition_of_equipment`) VALUES ('2', '54124', 'red', 'metal','good');
INSERT INTO `lab1db`.`equipment` (`id`, `inventory_numbers`, `color`,`body_material`,`condition_of_equipment`) VALUES ('3', '22312', 'green', 'metal','good');
INSERT INTO `lab1db`.`equipment` (`id`, `inventory_numbers`, `color`,`body_material`,`condition_of_equipment`) VALUES ('4', '30312', 'green', 'leather','good');
INSERT INTO `lab1db`.`equipment` (`id`, `inventory_numbers`, `color`,`body_material`,`condition_of_equipment`) VALUES ('5', '14211', 'white', 'leather','working');
INSERT INTO `lab1db`.`equipment` (`id`, `inventory_numbers`, `color`,`body_material`,`condition_of_equipment`) VALUES ('6', '20512', 'red', 'metal','working');
INSERT INTO `lab1db`.`equipment` (`id`, `inventory_numbers`, `color`,`body_material`,`condition_of_equipment`) VALUES ('7', '60612', 'black', 'metal','working');
INSERT INTO `lab1db`.`equipment` (`id`, `inventory_numbers`, `color`,`body_material`,`condition_of_equipment`) VALUES ('8', '80312', 'white', 'metal','working');
INSERT INTO `lab1db`.`equipment` (`id`, `inventory_numbers`, `color`,`body_material`,`condition_of_equipment`) VALUES ('9', '90312', 'red', 'leather','working');
INSERT INTO `lab1db`.`equipment` (`id`, `inventory_numbers`, `color`,`body_material`,`condition_of_equipment`) VALUES ('10', '54134', 'green', 'metal','working');

INSERT INTO `lab1db`.`user` (`id`, `surname`, `name`,`student_number`,`zip`) VALUES ('1', 'Hrudzinskyi','Taras','ВК12120222','79049');
INSERT INTO `lab1db`.`user` (`id`, `surname`, `name`,`student_number`,`zip`) VALUES ('2', 'Kanuka','Yura','ВК14120222','79049');
INSERT INTO `lab1db`.`user` (`id`, `surname`, `name`,`student_number`,`zip`) VALUES ('3', 'Kuzyk','Roman','ВК13120222','79049');
INSERT INTO `lab1db`.`user` (`id`, `surname`, `name`,`student_number`,`zip`) VALUES ('4', 'Oleh','Nazar','ВК22120222','79049');
INSERT INTO `lab1db`.`user` (`id`, `surname`, `name`,`student_number`,`zip`) VALUES ('5', 'Nazar','Ostap','ВК52120222','79049');
INSERT INTO `lab1db`.`user` (`id`, `surname`, `name`,`student_number`,`zip`) VALUES ('6', 'Ira','Misha','ВК62120222','79049');
INSERT INTO `lab1db`.`user` (`id`, `surname`, `name`,`student_number`,`zip`) VALUES ('7', 'Ernest','Vasya','ВК88120222','79049');
INSERT INTO `lab1db`.`user` (`id`, `surname`, `name`,`student_number`,`zip`) VALUES ('8', 'Erko','Dima','ВК99212022','79049');
INSERT INTO `lab1db`.`user` (`id`, `surname`, `name`,`student_number`,`zip`) VALUES ('9', 'Labe','Demutryi','ВК45120222','79049');
INSERT INTO `lab1db`.`user` (`id`, `surname`, `name`,`student_number`,`zip`) VALUES ('10', 'Rizka','Oksana','ВК16750222','79049');

INSERT INTO `lab1db`.`equipment_has_user` (`equipment_id`, `users_id`,`id`) VALUES ('1', '1','1');
INSERT INTO `lab1db`.`equipment_has_user` (`equipment_id`, `users_id`,`id`) VALUES ('2', '2','2');
INSERT INTO `lab1db`.`equipment_has_user` (`equipment_id`, `users_id`,`id`) VALUES ('3', '3','3');
INSERT INTO `lab1db`.`equipment_has_user` (`equipment_id`, `users_id`,`id`) VALUES ('4', '4','4');
INSERT INTO `lab1db`.`equipment_has_user` (`equipment_id`, `users_id`,`id`) VALUES ('5', '5','5');
INSERT INTO `lab1db`.`equipment_has_user` (`equipment_id`, `users_id`,`id`) VALUES ('6', '6','6');
INSERT INTO `lab1db`.`equipment_has_user` (`equipment_id`, `users_id`,`id`) VALUES ('7', '7','7');
INSERT INTO `lab1db`.`equipment_has_user` (`equipment_id`, `users_id`,`id`) VALUES ('8', '8','8');
INSERT INTO `lab1db`.`equipment_has_user` (`equipment_id`, `users_id`,`id`) VALUES ('9', '9','9');
INSERT INTO `lab1db`.`equipment_has_user` (`equipment_id`, `users_id`,`id`) VALUES ('10', '10','10');

INSERT INTO `lab1db`.`laser_cutter` (`id`, `time_using`,`start_data`,`end_data`,`equipment_id`) VALUES ('1', '2021-02-17 14:19:38','2021-02-17','2021-03-07','1');
INSERT INTO `lab1db`.`laser_cutter` (`id`, `time_using`,`start_data`,`end_data`,`equipment_id`) VALUES ('2', '2021-02-18 14:19:38','2021-02-18','2021-03-07','2');
INSERT INTO `lab1db`.`laser_cutter` (`id`, `time_using`,`start_data`,`end_data`,`equipment_id`) VALUES ('3', '2021-02-19 14:19:38','2021-02-19','2021-03-07','3');
INSERT INTO `lab1db`.`laser_cutter` (`id`, `time_using`,`start_data`,`end_data`,`equipment_id`) VALUES ('4', '2021-02-20 14:19:38','2021-02-20','2021-03-07','4');
INSERT INTO `lab1db`.`laser_cutter` (`id`, `time_using`,`start_data`,`end_data`,`equipment_id`) VALUES ('5', '2021-02-7 14:19:38','2021-02-7','2021-03-07','5');
INSERT INTO `lab1db`.`laser_cutter` (`id`, `time_using`,`start_data`,`end_data`,`equipment_id`) VALUES ('6', '2021-02-8 14:19:38','2021-02-8','2021-03-07','6');
INSERT INTO `lab1db`.`laser_cutter` (`id`, `time_using`,`start_data`,`end_data`,`equipment_id`) VALUES ('7', '2021-03-17 14:19:38','2021-03-17','2021-04-07','7');
INSERT INTO `lab1db`.`laser_cutter` (`id`, `time_using`,`start_data`,`end_data`,`equipment_id`) VALUES ('8', '2021-04-17 14:19:38','2021-04-17','2021-06-07','8');
INSERT INTO `lab1db`.`laser_cutter` (`id`, `time_using`,`start_data`,`end_data`,`equipment_id`) VALUES ('9', '2021-05-17 14:19:38','2021-05-17','2021-06-07','9');
INSERT INTO `lab1db`.`laser_cutter` (`id`, `time_using`,`start_data`,`end_data`,`equipment_id`) VALUES ('10', '2021-06-17 14:19:38','2021-06-17','2021-07-07','10');

INSERT INTO `lab1db`.`user_has_laser_cutter` (`user_id`, `laser_cutter_id`,`laser_cutter_equipment_id`,`id`) VALUES ('1', '1','1','1');
INSERT INTO `lab1db`.`user_has_laser_cutter` (`user_id`, `laser_cutter_id`,`laser_cutter_equipment_id`,`id`) VALUES ('2', '2','2','2');
INSERT INTO `lab1db`.`user_has_laser_cutter` (`user_id`, `laser_cutter_id`,`laser_cutter_equipment_id`,`id`) VALUES ('3', '3','3','3');
INSERT INTO `lab1db`.`user_has_laser_cutter` (`user_id`, `laser_cutter_id`,`laser_cutter_equipment_id`,`id`) VALUES ('4', '4','4','4');
INSERT INTO `lab1db`.`user_has_laser_cutter` (`user_id`, `laser_cutter_id`,`laser_cutter_equipment_id`,`id`) VALUES ('5', '5','5','5');
INSERT INTO `lab1db`.`user_has_laser_cutter` (`user_id`, `laser_cutter_id`,`laser_cutter_equipment_id`,`id`) VALUES ('6', '6','6','6');
INSERT INTO `lab1db`.`user_has_laser_cutter` (`user_id`, `laser_cutter_id`,`laser_cutter_equipment_id`,`id`) VALUES ('7', '7','7','7');
INSERT INTO `lab1db`.`user_has_laser_cutter` (`user_id`, `laser_cutter_id`,`laser_cutter_equipment_id`,`id`) VALUES ('8', '8','8','8');
INSERT INTO `lab1db`.`user_has_laser_cutter` (`user_id`, `laser_cutter_id`,`laser_cutter_equipment_id`,`id`) VALUES ('9', '9','9','9');
INSERT INTO `lab1db`.`user_has_laser_cutter` (`user_id`, `laser_cutter_id`,`laser_cutter_equipment_id`,`id`) VALUES ('10', '10','10','10');

INSERT INTO `lab1db`.`equipment_transfer` (`id`, `time`,`start_data`,`end_data`) VALUES ('1', '2021-11-27 14:19:38','2021-11-27','2021-10-29');
INSERT INTO `lab1db`.`equipment_transfer` (`id`, `time`,`start_data`,`end_data`) VALUES ('2', '2021-11-20 14:19:38','2021-11-20','2021-10-21');
INSERT INTO `lab1db`.`equipment_transfer` (`id`, `time`,`start_data`,`end_data`) VALUES ('3', '2021-11-21 14:19:38','2021-11-21','2021-10-24');
INSERT INTO `lab1db`.`equipment_transfer` (`id`, `time`,`start_data`,`end_data`) VALUES ('4', '2021-11-22 14:19:38','2021-11-22','2021-10-25');
INSERT INTO `lab1db`.`equipment_transfer` (`id`, `time`,`start_data`,`end_data`) VALUES ('5', '2021-11-23 14:19:38','2021-11-23','2021-10-25');
INSERT INTO `lab1db`.`equipment_transfer` (`id`, `time`,`start_data`,`end_data`) VALUES ('6', '2021-11-24 14:19:38','2021-11-24','2021-10-27');
INSERT INTO `lab1db`.`equipment_transfer` (`id`, `time`,`start_data`,`end_data`) VALUES ('7', '2021-11-25 14:19:38','2021-11-25','2021-10-26');
INSERT INTO `lab1db`.`equipment_transfer` (`id`, `time`,`start_data`,`end_data`) VALUES ('8', '2021-11-26 14:19:38','2021-11-26','2021-10-30');
INSERT INTO `lab1db`.`equipment_transfer` (`id`, `time`,`start_data`,`end_data`) VALUES ('9', '2021-11-28 14:19:38','2021-11-28','2021-10-29');
INSERT INTO `lab1db`.`equipment_transfer` (`id`, `time`,`start_data`,`end_data`) VALUES ('10', '2021-11-17 14:19:38','2021-11-17','2021-10-18');

INSERT INTO `lab1db`.`equipment_has_equipment_transfer` (`equipment_id`, `equipment_transfer_id`,`id`) VALUES ('1', '1','1');
INSERT INTO `lab1db`.`equipment_has_equipment_transfer` (`equipment_id`, `equipment_transfer_id`,`id`) VALUES ('2', '2','2');
INSERT INTO `lab1db`.`equipment_has_equipment_transfer` (`equipment_id`, `equipment_transfer_id`,`id`) VALUES ('3', '3','3');
INSERT INTO `lab1db`.`equipment_has_equipment_transfer` (`equipment_id`, `equipment_transfer_id`,`id`) VALUES ('4', '4','4');
INSERT INTO `lab1db`.`equipment_has_equipment_transfer` (`equipment_id`, `equipment_transfer_id`,`id`) VALUES ('5', '5','5');
INSERT INTO `lab1db`.`equipment_has_equipment_transfer` (`equipment_id`, `equipment_transfer_id`,`id`) VALUES ('6', '6','6');
INSERT INTO `lab1db`.`equipment_has_equipment_transfer` (`equipment_id`, `equipment_transfer_id`,`id`) VALUES ('7', '7','7');
INSERT INTO `lab1db`.`equipment_has_equipment_transfer` (`equipment_id`, `equipment_transfer_id`,`id`) VALUES ('8', '8','8');
INSERT INTO `lab1db`.`equipment_has_equipment_transfer` (`equipment_id`, `equipment_transfer_id`,`id`) VALUES ('9', '9','9');
INSERT INTO `lab1db`.`equipment_has_equipment_transfer` (`equipment_id`, `equipment_transfer_id`,`id`) VALUES ('10', '10','10');

INSERT INTO `lab1db`.`repairs_worker` (`id`, `name`,`surname`) VALUES ('1', 'Yaroslav','Shchudlyck');
INSERT INTO `lab1db`.`repairs_worker` (`id`, `name`,`surname`) VALUES ('2', 'Taras','Misha');
INSERT INTO `lab1db`.`repairs_worker` (`id`, `name`,`surname`) VALUES ('3', 'Ivan','Linda');
INSERT INTO `lab1db`.`repairs_worker` (`id`, `name`,`surname`) VALUES ('4', 'Nazar','Ihor');
INSERT INTO `lab1db`.`repairs_worker` (`id`, `name`,`surname`) VALUES ('5', 'Yura','Liza');
INSERT INTO `lab1db`.`repairs_worker` (`id`, `name`,`surname`) VALUES ('6', 'Yuriy','Baba');
INSERT INTO `lab1db`.`repairs_worker` (`id`, `name`,`surname`) VALUES ('7', 'Vasya','Ivan');
INSERT INTO `lab1db`.`repairs_worker` (`id`, `name`,`surname`) VALUES ('8', 'Ira','Taras');
INSERT INTO `lab1db`.`repairs_worker` (`id`, `name`,`surname`) VALUES ('9', 'Misha','Ira');
INSERT INTO `lab1db`.`repairs_worker` (`id`, `name`,`surname`) VALUES ('10', 'Igor','Nazar');

INSERT INTO `lab1db`.`repair` (`id`, `time`,`start_data`,`end_data`,`repairs_worker_id`,`equipment_id`,`repairs_worker_id1`) VALUES ('1', '2021-07-27 14:19:38','2021-07-27','2021-11-29','1','1','1');
INSERT INTO `lab1db`.`repair` (`id`, `time`,`start_data`,`end_data`,`repairs_worker_id`,`equipment_id`,`repairs_worker_id1`) VALUES ('2', '2021-06-27 14:18:38','2021-06-27','2021-11-29','2','2','2');
INSERT INTO `lab1db`.`repair` (`id`, `time`,`start_data`,`end_data`,`repairs_worker_id`,`equipment_id`,`repairs_worker_id1`) VALUES ('3', '2021-05-27 14:17:38','2021-05-27','2021-11-29','3','3','3');
INSERT INTO `lab1db`.`repair` (`id`, `time`,`start_data`,`end_data`,`repairs_worker_id`,`equipment_id`,`repairs_worker_id1`) VALUES ('4', '2021-04-27 14:16:38','2021-04-27','2021-11-29','4','4','4');
INSERT INTO `lab1db`.`repair` (`id`, `time`,`start_data`,`end_data`,`repairs_worker_id`,`equipment_id`,`repairs_worker_id1`) VALUES ('5', '2021-03-27 14:15:38','2021-03-27','2021-11-29','5','5','5');
INSERT INTO `lab1db`.`repair` (`id`, `time`,`start_data`,`end_data`,`repairs_worker_id`,`equipment_id`,`repairs_worker_id1`) VALUES ('6', '2021-02-27 14:14:38','2021-02-27','2021-11-29','6','6','6');
INSERT INTO `lab1db`.`repair` (`id`, `time`,`start_data`,`end_data`,`repairs_worker_id`,`equipment_id`,`repairs_worker_id1`) VALUES ('7', '2021-01-27 14:13:38','2021-01-27','2021-11-29','7','7','7');
INSERT INTO `lab1db`.`repair` (`id`, `time`,`start_data`,`end_data`,`repairs_worker_id`,`equipment_id`,`repairs_worker_id1`) VALUES ('8', '2021-12-27 14:12:38','2021-12-27','2021-12-29','8','8','8');
INSERT INTO `lab1db`.`repair` (`id`, `time`,`start_data`,`end_data`,`repairs_worker_id`,`equipment_id`,`repairs_worker_id1`) VALUES ('9', '2021-11-27 14:11:38','2021-11-27','2021-11-29','9','9','9');
INSERT INTO `lab1db`.`repair` (`id`, `time`,`start_data`,`end_data`,`repairs_worker_id`,`equipment_id`,`repairs_worker_id1`) VALUES ('10', '2021-10-27 14:10:38','2021-10-27','2021-11-29','10','10','10');

INSERT INTO `lab1db`.`equipment_has_repairs` (`equipment_id`, `repairs_id`,`id`) VALUES ('1', '1','1');
INSERT INTO `lab1db`.`equipment_has_repairs` (`equipment_id`, `repairs_id`,`id`) VALUES ('2', '2','2');
INSERT INTO `lab1db`.`equipment_has_repairs` (`equipment_id`, `repairs_id`,`id`) VALUES ('3', '3','3');
INSERT INTO `lab1db`.`equipment_has_repairs` (`equipment_id`, `repairs_id`,`id`) VALUES ('4', '4','4');
INSERT INTO `lab1db`.`equipment_has_repairs` (`equipment_id`, `repairs_id`,`id`) VALUES ('5', '5','5');
INSERT INTO `lab1db`.`equipment_has_repairs` (`equipment_id`, `repairs_id`,`id`) VALUES ('6', '6','6');
INSERT INTO `lab1db`.`equipment_has_repairs` (`equipment_id`, `repairs_id`,`id`) VALUES ('7', '7','7');
INSERT INTO `lab1db`.`equipment_has_repairs` (`equipment_id`, `repairs_id`,`id`) VALUES ('8', '8','8');
INSERT INTO `lab1db`.`equipment_has_repairs` (`equipment_id`, `repairs_id`,`id`) VALUES ('9', '9','9');
INSERT INTO `lab1db`.`equipment_has_repairs` (`equipment_id`, `repairs_id`,`id`) VALUES ('10', '10','10');

INSERT INTO `lab1db`.`laser_cutter_has_repair` (`laser_cutter_id`, `laser_cutter_equipment_id`,`repair_id`,`id`) VALUES ('1', '1','1','1');
INSERT INTO `lab1db`.`laser_cutter_has_repair` (`laser_cutter_id`, `laser_cutter_equipment_id`,`repair_id`,`id`) VALUES ('2', '2','2','2');
INSERT INTO `lab1db`.`laser_cutter_has_repair` (`laser_cutter_id`, `laser_cutter_equipment_id`,`repair_id`,`id`) VALUES ('3', '3','3','3');
INSERT INTO `lab1db`.`laser_cutter_has_repair` (`laser_cutter_id`, `laser_cutter_equipment_id`,`repair_id`,`id`) VALUES ('4', '4','4','4');
INSERT INTO `lab1db`.`laser_cutter_has_repair` (`laser_cutter_id`, `laser_cutter_equipment_id`,`repair_id`,`id`) VALUES ('5', '5','5','5');
INSERT INTO `lab1db`.`laser_cutter_has_repair` (`laser_cutter_id`, `laser_cutter_equipment_id`,`repair_id`,`id`) VALUES ('6', '6','6','6');
INSERT INTO `lab1db`.`laser_cutter_has_repair` (`laser_cutter_id`, `laser_cutter_equipment_id`,`repair_id`,`id`) VALUES ('7', '7','7','7');
INSERT INTO `lab1db`.`laser_cutter_has_repair` (`laser_cutter_id`, `laser_cutter_equipment_id`,`repair_id`,`id`) VALUES ('8', '8','8','8');
INSERT INTO `lab1db`.`laser_cutter_has_repair` (`laser_cutter_id`, `laser_cutter_equipment_id`,`repair_id`,`id`) VALUES ('9', '9','9','9');
INSERT INTO `lab1db`.`laser_cutter_has_repair` (`laser_cutter_id`, `laser_cutter_equipment_id`,`repair_id`,`id`) VALUES ('10', '10','10','10');