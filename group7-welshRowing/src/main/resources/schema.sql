SET MODE MySQL;
SET IGNORECASE=TRUE;
SET AUTHENTICATOR TRUE;

-- table used to test functionality, not final table and should be removed once database has been implemented
CREATE TABLE IF NOT EXISTS `athlete` (
  `athleteID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `gender` VARCHAR(6) NOT NULL,
  `dob` DATE NOT NULL,
  `email` VARCHAR(45),
  `password` VARCHAR(10) NOT NULL,
  `mobileNumber` VARCHAR(12),
  `telephoneNumber` VARCHAR(10),
--   will be made into own table, just for testing purposes currently.
  `homeAddress` VARCHAR(50),
  `uniAddress` VARCHAR(60),
  `postcode` VARCHAR (8),
  `placeOfEducation` VARCHAR(45) ,
  `guardianName` VARCHAR(45),
  `relationshipToAthlete` VARCHAR(45) ,
  `guardianContactNumber` VARCHAR(45),
  `guardianEmail` VARCHAR(45),
  `heardFrom` VARCHAR(45),
  `interestLetter` INT,

  PRIMARY KEY (`athleteID`))
ENGINE = InnoDB;

