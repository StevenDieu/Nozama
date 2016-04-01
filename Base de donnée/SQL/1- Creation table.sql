-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema nozama
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `nozama` ;

-- -----------------------------------------------------
-- Schema nozama
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `nozama` DEFAULT CHARACTER SET utf8 ;
USE `nozama` ;

-- -----------------------------------------------------
-- Table `nozama`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nozama`.`product` (
  `id_product` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `name_tag_date_released` VARCHAR(255) NOT NULL,
  `description` TEXT NOT NULL,
  `url_picture` VARCHAR(1024) NOT NULL,
  `date_released` DATE NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `id_product_parent` INT(11) NULL,
  PRIMARY KEY (`id_product`),
  CONSTRAINT `fk_product_product`
    FOREIGN KEY (`id_product_parent`)
    REFERENCES `nozama`.`product` (`id_product`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;

CREATE UNIQUE INDEX `name_tag_date_released_UNIQUE` ON `nozama`.`product` (`name_tag_date_released` ASC);

CREATE INDEX `fk_product_product_idx` ON `nozama`.`product` (`id_product_parent` ASC);


-- -----------------------------------------------------
-- Table `nozama`.`artiste`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nozama`.`artiste` (
  `id_artiste` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `id_product` INT(11) NOT NULL,
  PRIMARY KEY (`id_artiste`),
  CONSTRAINT `fk_artiste_product1`
    FOREIGN KEY (`id_product`)
    REFERENCES `nozama`.`product` (`id_product`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_artiste_product1_idx` ON `nozama`.`artiste` (`id_product` ASC);


-- -----------------------------------------------------
-- Table `nozama`.`article`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nozama`.`article` (
  `id_article` INT NOT NULL AUTO_INCREMENT,
  `name_support` VARCHAR(45) NOT NULL,
  `price` FLOAT NOT NULL,
  `id_product` INT(11) NOT NULL,
  PRIMARY KEY (`id_article`),
  CONSTRAINT `fk_article_product1`
    FOREIGN KEY (`id_product`)
    REFERENCES `nozama`.`product` (`id_product`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_article_product1_idx` ON `nozama`.`article` (`id_product` ASC);


-- -----------------------------------------------------
-- Table `nozama`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nozama`.`user` (
  `idUsers` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `lastname` VARCHAR(255) NOT NULL,
  `emailAdress` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `ipAddress` VARCHAR(45) NOT NULL,
  `genre` VARCHAR(3) NOT NULL,
  `compte_prepaye` FLOAT NOT NULL,
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idUsers`));

CREATE UNIQUE INDEX `emailAdress_UNIQUE` ON `nozama`.`user` (`emailAdress` ASC);


-- -----------------------------------------------------
-- Table `nozama`.`adress`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nozama`.`adress` (
  `idAdress` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `nameLastName` VARCHAR(255) NOT NULL,
  `adress_principal` VARCHAR(1024) NOT NULL,
  `adress_secondaire` VARCHAR(1024) NULL,
  `region` VARCHAR(255) NULL,
  `code_postal` INT(5) NOT NULL,
  `city` VARCHAR(255) NOT NULL,
  `pays` VARCHAR(255) NOT NULL,
  `number_phone` INT(10) ZEROFILL NOT NULL,
  `id_users` INT NOT NULL,
  PRIMARY KEY (`idAdress`),
  CONSTRAINT `fk_adress_user1`
    FOREIGN KEY (`id_users`)
    REFERENCES `nozama`.`user` (`idUsers`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_adress_user1_idx` ON `nozama`.`adress` (`id_users` ASC);


-- -----------------------------------------------------
-- Table `nozama`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nozama`.`order` (
  `id_order` INT NOT NULL AUTO_INCREMENT,
  `id_adress` INT NOT NULL,
  `id_users` INT NOT NULL,
  `mode_payment` VARCHAR(45) NOT NULL,
  `mode_delivery` VARCHAR(45) NOT NULL,
  `total_product` FLOAT NOT NULL,
  `total_delivery` FLOAT NOT NULL,
  `total_order` FLOAT NOT NULL,
  `comment_delivery` VARCHAR(255) NULL,
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` DATETIME NOT NULL,
  PRIMARY KEY (`id_order`),
  CONSTRAINT `fk_order_adress1`
    FOREIGN KEY (`id_adress`)
    REFERENCES `nozama`.`adress` (`idAdress`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_user1`
    FOREIGN KEY (`id_users`)
    REFERENCES `nozama`.`user` (`idUsers`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1000000;

CREATE INDEX `fk_order_adress1_idx` ON `nozama`.`order` (`id_adress` ASC);

CREATE INDEX `fk_order_user1_idx` ON `nozama`.`order` (`id_users` ASC);


-- -----------------------------------------------------
-- Table `nozama`.`product_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nozama`.`product_order` (
  `id_product_order` INT NOT NULL AUTO_INCREMENT,
  `id_order` INT NOT NULL,
  `id_article` INT NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`id_product_order`),
  CONSTRAINT `fk_product_order_order1`
    FOREIGN KEY (`id_order`)
    REFERENCES `nozama`.`order` (`id_order`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_order_article1`
    FOREIGN KEY (`id_article`)
    REFERENCES `nozama`.`article` (`id_article`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_product_order_order1_idx` ON `nozama`.`product_order` (`id_order` ASC);

CREATE INDEX `fk_product_order_article1_idx` ON `nozama`.`product_order` (`id_article` ASC);


-- -----------------------------------------------------
-- Table `nozama`.`attr_product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nozama`.`attr_product` (
  `id_attr_product` INT NOT NULL AUTO_INCREMENT,
  `attribut` VARCHAR(1024) NOT NULL,
  `value` VARCHAR(1024) NOT NULL,
  `id_product` INT(11) NOT NULL,
  PRIMARY KEY (`id_attr_product`),
  CONSTRAINT `fk_attr_product_product1`
    FOREIGN KEY (`id_product`)
    REFERENCES `nozama`.`product` (`id_product`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_attr_product_product1_idx` ON `nozama`.`attr_product` (`id_product` ASC);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;