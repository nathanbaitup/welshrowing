SET MODE MySQL;
SET IGNORECASE=TRUE;
SET AUTHENTICATOR TRUE;

-- table used to test functionality, not final table and should be removed once database has been implemented

CREATE TABLE IF NOT EXISTS `user` (
  `userID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `username` VARCHAR(6) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `role` VARCHAR(45),

  PRIMARY KEY (`userID`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `athlete` (
  `athleteID` INT UNSIGNED NOT NULL,
  `coachID` INT,
  `name` VARCHAR(45) NOT NULL,
  `gender` VARCHAR(6) NOT NULL,
  `dob` DATE NOT NULL,
  `applicationStatus` BOOLEAN,
  `email` VARCHAR(45),
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
  `interestLetter` BOOLEAN,
  `postTestResult` VARCHAR(45),

  PRIMARY KEY (`athleteID`))
ENGINE = InnoDB;

CREATE TABLE `SessionRPE` (
    `sessionRPEID` INTEGER NOT NULL AUTO_INCREMENT,
    `athleteID` INTEGER NOT NULL,
    `dateOfSession` DATE,
    `typeOfSession` VARCHAR(50),
    `RPE` INTEGER,
    `sessionDurationMinutes` INTEGER,
    PRIMARY KEY (`sessionRPEID`),
    FOREIGN KEY (`athleteID`) REFERENCES `Athlete`(`athleteID`)
);

CREATE TABLE `crossTraining` (
     `crossTrainingID` INTEGER NOT NULL AUTO_INCREMENT,
     `athleteID` INTEGER NOT NULL,
     `dateOfSession` DATE,
     `typeOfCrossTraining` VARCHAR(50),
     `totalTimeMinutes` INTEGER,
     `totalDistance`INTEGER,
     PRIMARY KEY (`crossTrainingID`),
     FOREIGN KEY (`athleteID`) REFERENCES Athlete(`athleteID`)
);

