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
  `mobileNumber` VARCHAR(10),
  `telephoneNumber` VARCHAR(10),
--   will be made into own table, just for testing purposes currently.
  `homeAddress` VARCHAR(50),
  `uniAddress` VARCHAR(60),
  `postcode` VARCHAR (7),
  `placeOfEducation` VARCHAR(45) NOT NULL,
  `guardianName` VARCHAR(45) NOT NULL,
  `relationshipToAthlete` VARCHAR(45) NOT NULL,
  `guardianContactNumber` VARCHAR(45) NOT NULL,
  `guardianEmail` VARCHAR(45) NOT NULL,
  `heardFrom` VARCHAR(45) NOT NULL,
  `interestLetter` BIT NOT NULL,

  PRIMARY KEY (`athleteID`))
ENGINE = InnoDB;

