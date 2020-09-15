-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema machines
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema machines
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `machines` DEFAULT CHARACTER SET utf8 ;
USE `machines` ;

-- -----------------------------------------------------
-- Table `machines`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `machines`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `phone_number` INT NOT NULL,
  `password` VARCHAR(64) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  `enabled` TINYINT(1) NOT NULL,
  `department` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `machines`.`authorities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `machines`.`authorities` (
  `authority` VARCHAR(45) NOT NULL,
  `user_id` INT NOT NULL,
  INDEX `fk_authorities_users1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_authorities_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `machines`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO `machines`.`users`
VALUES 
(1,"mateusz123","Mateusz", "Jaworski", 724680012, 
"$2y$12$z7InUjdFoHIQfezwbYByjOde/DtFi7l3AanhdQNUrrWhY24r5knE2",
"ADMIN", 1, "Service CTO"),
(2,"joedoe@email.com","Joe", "Doe", 661213019, 
"$2y$12$z7InUjdFoHIQfezwbYByjOde/DtFi7l3AanhdQNUrrWhY24r5knE2",
"WORKER", 1, "Department of Plenumatics");

INSERT INTO `authorities` 
VALUES 
('ROLE_ADMIN', 1),
('ROLE_WORKER', 2);

-- -----------------------------------------------------
-- Table `machines`.`application_problems`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `machines`.`application_problems` (
  `application_problem_id` INT NOT NULL AUTO_INCREMENT,
  `keywords` VARCHAR(45) NOT NULL,
  `description` TEXT NOT NULL,
  `version_code` VARCHAR(45) NOT NULL,
  `user_id` INT NULL,
  PRIMARY KEY (`application_problem_id`),
  INDEX `fk_application_problems_users1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_application_problems_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `machines`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `machines`.`tasks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `machines`.`tasks` (
  `task_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `description` TEXT NOT NULL,
  `date` DATETIME NOT NULL,
  `solved` TINYINT(1) NOT NULL,
  PRIMARY KEY (`task_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `machines`.`users_tasks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `machines`.`users_tasks` (
  `user_id` INT NOT NULL,
  `task_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `task_id`),
  INDEX `fk_users_has_tasks_tasks1_idx` (`task_id` ASC) VISIBLE,
  INDEX `fk_users_has_tasks_users1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_users_has_tasks_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `machines`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_users_has_tasks_tasks1`
    FOREIGN KEY (`task_id`)
    REFERENCES `machines`.`tasks` (`task_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `machines`.`machines`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `machines`.`machines` (
  `machine_id` INT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT NOT NULL,
  `image` VARCHAR(100) NULL,
  `service_instruction` VARCHAR(100) NULL,
  PRIMARY KEY (`machine_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `machines`.`users_machines`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `machines`.`users_machines` (
  `user_id` INT NOT NULL,
  `machine_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `machine_id`),
  INDEX `fk_users_has_machines_machines1_idx` (`machine_id` ASC) VISIBLE,
  INDEX `fk_users_has_machines_users1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_users_has_machines_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `machines`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_users_has_machines_machines1`
    FOREIGN KEY (`machine_id`)
    REFERENCES `machines`.`machines` (`machine_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `machines`.`services`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `machines`.`services` (
  `service_id` INT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NOT NULL,
  `component_name` VARCHAR(45) NOT NULL,
  `description` TEXT NOT NULL,
  `result` TINYINT NOT NULL,
  `machine_id` INT NULL,
  PRIMARY KEY (`service_id`),
  INDEX `fk_services_machines1_idx` (`machine_id` ASC) VISIBLE,
  CONSTRAINT `fk_services_machines1`
    FOREIGN KEY (`machine_id`)
    REFERENCES `machines`.`machines` (`machine_id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `machines`.`issues`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `machines`.`issues` (
  `issue_id` INT NOT NULL AUTO_INCREMENT,
  `keywords` VARCHAR(45) NOT NULL,
  `description` TEXT NOT NULL,
  `solution` TEXT NULL,
  `worker_signature` TEXT NULL,
  `machine_id` INT NULL,
  PRIMARY KEY (`issue_id`),
  INDEX `fk_issues_machines1_idx` (`machine_id` ASC) VISIBLE,
  CONSTRAINT `fk_issues_machines1`
    FOREIGN KEY (`machine_id`)
    REFERENCES `machines`.`machines` (`machine_id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

