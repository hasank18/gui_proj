
-- -----------------------------------------------------
-- Schema project_gui
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `project_gui` ;

-- -----------------------------------------------------
-- Schema project_gui
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `project_gui` DEFAULT CHARACTER SET utf8 ;
USE `project_gui` ;

-- -----------------------------------------------------
-- Table `project_gui`.`Person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_gui`.`Person` (
  `username` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL,
  `birthdate` DATE NULL,
  `email` VARCHAR(45) NULL,
  `gender` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `image` VARCHAR(200) NULL,
  PRIMARY KEY (`username`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `project_gui`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_gui`.`admin` (
  `idadmin` INT NOT NULL AUTO_INCREMENT,
  `Person_username` VARCHAR(45) NOT NULL,
  `recieved_money` DOUBLE NULL DEFAULT 0,
  PRIMARY KEY (`idadmin`),
  UNIQUE INDEX `idadmin_UNIQUE` (`idadmin` ASC) ,
  INDEX `fk_admin_Person1_idx` (`Person_username` ASC) ,
  CONSTRAINT `fk_admin_Person1`
    FOREIGN KEY (`Person_username`)
    REFERENCES `project_gui`.`Person` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `project_gui`.`ClientPage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_gui`.`Client` (
  `idclient` INT NOT NULL AUTO_INCREMENT,
  `Person_username` VARCHAR(45) NOT NULL,
  `admin_idadmin` INT NOT NULL,
  PRIMARY KEY (`idclient`),
  UNIQUE INDEX `username_UNIQUE` (`idclient` ASC) ,
  INDEX `fk_Client_Person_idx` (`Person_username` ASC) ,
  INDEX `fk_Client_admin1_idx` (`admin_idadmin` ASC) ,
  CONSTRAINT `fk_Client_Person`
    FOREIGN KEY (`Person_username`)
    REFERENCES `project_gui`.`Person` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Client_admin1`
    FOREIGN KEY (`admin_idadmin`)
    REFERENCES `project_gui`.`admin` (`idadmin`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `project_gui`.`BabySitter`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_gui`.`BabySitter` (
  `idBabySitter` INT NOT NULL auto_increment,
  `price_hour` DOUBLE NOT NULL,
  `Person_username` VARCHAR(45) NOT NULL,
  `admin_idadmin` INT NOT NULL,
  `recieved_money` DOUBLE NULL DEFAULT 0,
  PRIMARY KEY (`idBabySitter`),
  INDEX `fk_BabySitter_Person1_idx` (`Person_username` ASC) ,
  INDEX `fk_BabySitter_admin1_idx` (`admin_idadmin` ASC) ,
  CONSTRAINT `fk_BabySitter_Person1`
    FOREIGN KEY (`Person_username`)
    REFERENCES `project_gui`.`Person` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_BabySitter_admin1`
    FOREIGN KEY (`admin_idadmin`)
    REFERENCES `project_gui`.`admin` (`idadmin`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `project_gui`.`sitter_payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_gui`.`sitter_payment` (
  `idsitter_payment` INT NOT NULL AUTO_INCREMENT,
  `paid` DOUBLE NULL,
  `recieved_sitter` DOUBLE NULL,
  `recieved_admin` DOUBLE NULL,
  `Client_idclient` INT NOT NULL,
  `admin_idadmin` INT NOT NULL,
  `BabySitter_idBabySitter` INT NOT NULL,
  PRIMARY KEY (`idsitter_payment`),
  INDEX `fk_sitter_payment_Client1_idx` (`Client_idclient` ASC) ,
  INDEX `fk_sitter_payment_admin1_idx` (`admin_idadmin` ASC) ,
  INDEX `fk_sitter_payment_BabySitter1_idx` (`BabySitter_idBabySitter` ASC) ,
  CONSTRAINT `fk_sitter_payment_Client1`
    FOREIGN KEY (`Client_idclient`)
    REFERENCES `project_gui`.`Client` (`idclient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sitter_payment_admin1`
    FOREIGN KEY (`admin_idadmin`)
    REFERENCES `project_gui`.`admin` (`idadmin`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sitter_payment_BabySitter1`
    FOREIGN KEY (`BabySitter_idBabySitter`)
    REFERENCES `project_gui`.`BabySitter` (`idBabySitter`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `project_gui`.`SitterBooking`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_gui`.`SitterBooking` (
  `idSitterBooking` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NULL,
  `Client_idclient` INT NOT NULL,
  `BabySitter_idBabySitter` INT NOT NULL,
  `sitter_payment_idsitter_payment` INT NOT NULL,
  PRIMARY KEY (`idSitterBooking`),
  UNIQUE INDEX `idSitterBooking_UNIQUE` (`idSitterBooking` ASC) ,
  INDEX `fk_SitterBooking_Client1_idx` (`Client_idclient` ASC) ,
  INDEX `fk_SitterBooking_BabySitter1_idx` (`BabySitter_idBabySitter` ASC) ,
  INDEX `fk_SitterBooking_sitter_payment1_idx` (`sitter_payment_idsitter_payment` ASC) ,
  CONSTRAINT `fk_SitterBooking_Client1`
    FOREIGN KEY (`Client_idclient`)
    REFERENCES `project_gui`.`Client` (`idclient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SitterBooking_BabySitter1`
    FOREIGN KEY (`BabySitter_idBabySitter`)
    REFERENCES `project_gui`.`BabySitter` (`idBabySitter`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SitterBooking_sitter_payment1`
    FOREIGN KEY (`sitter_payment_idsitter_payment`)
    REFERENCES `project_gui`.`sitter_payment` (`idsitter_payment`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `project_gui`.`SitterRating`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_gui`.`SitterRating` (
  `idSitterRating` INT NOT NULL AUTO_INCREMENT,
  `stars` INT NULL,
  `comment` MEDIUMTEXT NULL,
  `Client_idclient` INT NOT NULL,
  `BabySitter_idBabySitter` INT NOT NULL,
  PRIMARY KEY (`idSitterRating`),
  INDEX `fk_SitterRating_Client1_idx` (`Client_idclient` ASC) ,
  INDEX `fk_SitterRating_BabySitter1_idx` (`BabySitter_idBabySitter` ASC) ,
  CONSTRAINT `fk_SitterRating_Client1`
    FOREIGN KEY (`Client_idclient`)
    REFERENCES `project_gui`.`Client` (`idclient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SitterRating_BabySitter1`
    FOREIGN KEY (`BabySitter_idBabySitter`)
    REFERENCES `project_gui`.`BabySitter` (`idBabySitter`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;




DELIMITER $$
create procedure addPerson(in username varchar(45),in name varchar(45),
in phone VARCHAR(45),in address VARCHAR(45),
in birthdate Date,in email VARCHAR(45),in gender varchar(45),in password VARCHAR(45),in image BloB,in type varchar(45))
begin
insert into Person(username,name,phone,address,birthdate,email,gender,password,image,type)
values(username,name,phone,address,birthdate,email,gender,password,image,type);
end $$
DELIMITER ;

DELIMITER $$
create procedure addadmin(in Person_username varchar(45))
begin
insert into admin(Person_username)
values(Person_username);
end $$
DELIMITER ;

DELIMITER $$
create procedure addbabysitter(in price_hour Double,in Person_username varchar(45),in admin_idadmin int)
begin
insert into BabySitter(price_hour,Person_username,admin_idadmin)
values(price_hour,Person_username,admin_idadmin);
end $$
DELIMITER ;

DELIMITER $$
    create procedure addClient(in Person_username varchar(45))
    begin
    insert into CLient(Person_username)
    values(Person_username);
    end$$
    DELIMITER ;

DELIMITER $$
create procedure addSitterRating(in Stars int,in comment MEDIUMTEXT, in Client_idclient int,in BabySitter_idBabySitter int)
begin
insert into SitterRating(Stars,comment,Client_idclient,BabySitter_idBabySitter)
values(Stars,comment,Client_idclient,BabySitter_idBabySitter);
end$$
DELIMITER ;

DELIMITER $$
create procedure addSitterBooking(in date Date, in Client_idclient int,in BabySitter_idBabySitter int ,in sitter_payment_idsitter_payment int)
begin
insert into SitterBooking(date,Client_idclient,BabySitter_idBabySitter,sitter_payment_idsitter_payment,answer)
values(date,Client_idclient,BabySitter_idBabySitter,sitter_payment_ids,false);
end$$
DELIMITER ;


DELIMITER $$
create procedure addSitterpayment(in paid int,in recieved_sitter int,recieved_admin int, in Client_idclient int,in admin_idadmin int, in BabySitter_idBabySitter int)
begin
insert into sitter_payment(paid,recieved_sitter,recieved_admin,Client_idclient,admin_idadmin,BabySitter_idBabySitter)
values(paid,recieved_sitter,recieved_admin,Client_idclient,admin_idadmin,BabySitter_idBabySitter);
end$$
DELIMITER ;



DELIMITER $$
CREATE PROCEDURE updatename
(in Username varchar(45),
in pname varchar(45))
begin
update Person
set
name=pname
where Person.username=Username;
end $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE updateemail
(in Username varchar(45),
in Email varchar(45))
begin
update Person
set
email=Email
where Person.username=Username;
end $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE updategender
(in Username varchar(45),
in gender varchar(45))
begin
update Person
set
gender=gender
where Person.username=Username;
end $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE updatepass
(in Username varchar(45),
in pass varchar(45))
begin
update Person
set
password=pass
where Person.username=Username;
end $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE updateBirthdate
(in Username varchar(45),
in date date)
begin
update Person
set
birthdate=date
where Person.username=Username;
end $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE updateaddress
(in Username varchar(45),
in addr varchar(45))
begin
update Person
set
address=addr
where Person.username=Username;
end $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE updatenum
(in Username varchar(45),
in num varchar(45))
begin
update Person
set
phone=num
where Person.username=Username;
end $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE updateimage
(in Username varchar(45),
in img Blob)
begin
update Person
set
image=img
where Person.username=Username;
end $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE updatecomments
(in id int,
in com mediumtext)
begin
update SitterRating
set
comment=com
where SitterRating.idSitterRating=id;
end $$
DELIMITER ;


DELIMITER $$
CREATE PROCEDURE updatestars
(in id int,
in star mediumtext)
begin
update SitterRating
set
stars=star
where SitterRating.idSitterRating=id;
end $$
DELIMITER ;

drop procedure updatestars

DELIMITER $$
create procedure updatehours
(in id int,
in price_hour double)
begin
update BabySitter
set
price_hour=price_hour
where BabySitter.idBabySitter=id;
end $$
DELIMITER ;


DELIMITER $$
create procedure updatedate
(in id int,
in date date)
begin
update SitterBooking
set
date=date
where SitterBooking.idSitterBooking=id;
end $$
DELIMITER ;

-- ------------------------
-- triggers
-- --------------------------

DELIMITER $$
create trigger deleteclient before delete
on Client for each row
begin
delete from SitterBooking where SitterBooking.Client_idclient=old.idclient;
delete from SitterRating where SitterRating.Client_idclient=old.idclient;
delete from sitter_payment where sitter_payment.Client_idclient=old.idclient;
end $$
DELIMITER ;

DELIMITER $$
create trigger deletebabysitter_payment before delete
on sitter_payment for each row
begin
delete from SitterBooking where SitterBooking.sitter_payment_idsitter_payment=old.idsitter_payment;
end $$
DELIMITER ;

DELIMITER $$
create trigger delete_babysitter before delete
on BabySitter for each row
begin
delete from SitterBooking where SitterBooking.BabySitter_idBabySitter=old.idBabySitter;
delete from SitterRating where SitterRating.BabySitter_idBabySitter=old.idBabySitter;
delete from sitter_payment where sitter_payment.BabySitter_idBabySitter=old.idBabySitter;
end $$
DELIMITER ;

DELIMITER $$
create procedure addPersonAsClient(in username varchar(45),in name varchar(45),
in phone VARCHAR(45),in address VARCHAR(45),
in birthdate Date,in email VARCHAR(45),in gender varchar(45),in password VARCHAR(45),image varchar(45))
begin
insert into Person(username,name,phone,address,birthdate,email,gender,password,image)
values(username,name,phone,address,birthdate,email,gender,password,image);
insert into Client(Person_username,admin_idadmin) values(username,1);
end $$
DELIMITER ;


DELIMITER $$
create procedure addPersonAsBabySitter(in username varchar(45),in name varchar(45),
in phone VARCHAR(45),in address VARCHAR(45),
in birthdate Date,in email VARCHAR(45),in gender varchar(45),in password VARCHAR(45),image varchar(45))
begin
insert into Person(username,name,phone,address,birthdate,email,gender,password,image)
values(username,name,phone,address,birthdate,email,gender,password,image);
insert into BabySitter(price_hour,Person_username,admin_idadmin) values(10,username,1);
end $$
DELIMITER ;

DELIMITER $$
create procedure addPersonAsAdmin (in username varchar(45),in name varchar(45),
in phone VARCHAR(45),in address VARCHAR(45),
in birthdate Date,in email VARCHAR(45),in gender varchar(45),in password VARCHAR(45),image varchar(200))
begin
insert into Person(username,name,phone,address,birthdate,email,gender,password,image)
values(username,name,phone,address,birthdate,email,gender,password,image);
insert into admin(Person_username) values(username);
end $$
DELIMITER ;




use project_gui;
select * from Person;
select * from Client;
select * from admin;
select * from BabySitter;
select * from sitter_payment;
select * from SitterBooking;
insert into Person values("hoho","hasan","123123","bchamoun","1999-12-12","example@example.com","male","1234567",null);
insert into admin values(1,"hoho");
insert into Client value(1,"hoho11",1);
insert into BabySitter values(1,10,"hoho2",1);
insert into sitter_payment values(3,10,9,1,1,1,1);
insert into SitterBooking values(3,null,"1999-12-12",1,1,3);
select username,password  from Person where username='hoho2'and password='1234567';
select Person_username from admin where Person_username='hoho';
select * from BabySitterView;
create view BabySitterView as select * from BabySitter,Person where Person.username = BabySitter.Person_username;
insert into BabySitter values(2,10,"hoho3",1);
select load_file('/var/lib/mysql/all_images/default_profile_picture.png');
call addPersonAsBabySitter('hoho12',"hasan","123123","bchamoun","1999-12-12","example@example.com","male","1234567","file:../resources/defualt_profile_picture.png");
select max_allowed_packet;
alter table Person drop image;
alter table Person add image varchar(200);
update Person  set image = 'file:../resources/default_profile_picture.png';
drop procedure addPersonAsBabySitter;
drop table Client;
drop table Client;


-- -----------------------------------
-- hanin 3mele run lahole yalle tahet
-- ----------------------------------

alter table SitterBooking add answer boolean;
update SitterBooking set answer = false;

create view BookingData as select * from Person,SitterBooking,BabySitter where BabySitter.idBabySitter = SitterBooking.BabySitter_idBabySitter;


update BabySitterView set image = 'file:../resources/default_profile_picture.png' ;
alter table sitter_payment drop recieved_sitter;
alter table sitter_payment drop recieved_admin;

create view BabySitterRating as select * from BabySitter,Person,SitterRating where Person.username = BabySitter.Person_username and BabySitter.idBabySitter = SitterRating.idSitterRating;
select * from SitterRating;
select * from BabySitterRating;
insert SitterRating(stars,comment,Client_idclient,BabySitter_idBabySitter) values(2,'this is test',1,1);
alter table SitterBooking add finished boolean;
update SitterBooking set finished = false;

drop view BookingData;


