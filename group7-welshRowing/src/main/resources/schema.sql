SET MODE MySQL;
SET IGNORECASE=TRUE;
SET AUTHENTICATOR TRUE;

-- table used to test functionality, not final table and should be removed once database has been implemented

CREATE TABLE IF NOT EXISTS `user` (
  `userID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `username` VARCHAR(6) NOT NULL,
  `password` VARCHAR(10) NOT NULL,
  `role` VARCHAR(45),

  PRIMARY KEY (`userID`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `athlete` (
  `athleteID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `userID` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `gender` VARCHAR(6) NOT NULL,
  `dob` DATE NOT NULL,
  `email` VARCHAR(45),
  `password` VARCHAR(10) NOT NULL,
  `mobileNumber` VARCHAR(12),
  `telephoneNumber` VARCHAR(10),
--   will be made into own table, just for testing purposes currently.
  `address` VARCHAR(50),
  `postcode` VARCHAR (8),
  `placeOfEducation` VARCHAR(45) ,
  `guardianName` VARCHAR(45),
  `relationshipToAthlete` VARCHAR(45) ,
  `guardianContactNumber` VARCHAR(45),
  `guardianEmail` VARCHAR(45),
  `heardFrom` VARCHAR(45),
  `interestLetter` INT,

  PRIMARY KEY (`athleteID`),
  FOREIGN KEY (`userID`) REFERENCES user(`userID`))
ENGINE = InnoDB;

