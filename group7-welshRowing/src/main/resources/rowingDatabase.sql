# https://lucid.app/lucidchart/invitations/accept/fade6f95-af6c-4643-9f07-1e402e18cd19 following schema

# DROP DATABASE IF EXISTS welshRowing;

CREATE DATABASE IF NOT EXISTS welshRowing;

USE welshRowing;

CREATE TABLE IF NOT EXISTS `User` (
  `userID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `username` VARCHAR(20) NOT NULL,
  `password` VARCHAR(128) NOT NULL,
  `role` VARCHAR(45),

  PRIMARY KEY (`userID`))
ENGINE = InnoDB;

CREATE TABLE `Athlete` (
    athleteID INTEGER,
    coachID INTEGER,
    name VARCHAR(30),
    gender VARCHAR(10),
    DOB DATE,
    applicationStatus BOOLEAN,
    email VARCHAR(30),
    mobileNumber VARCHAR(50), # allows country code and spaces and to ensure digits only
    telephoneNumber VARCHAR(50),
    address VARCHAR(50),
    postcode VARCHAR(8),
    placeOfEducation VARCHAR(50),
    guardianName VARCHAR(50),
    relationshipToAthlete VARCHAR(20),
    guardianEmail VARCHAR(30),
    guardianContactNumber VARCHAR(50),
    heardFrom VARCHAR(40),
    interestLetter BOOLEAN,
    postTestResult VARCHAR(30),
    PRIMARY KEY PKAthlete(athleteID),
    FOREIGN KEY FKAthlete(`coachID`) REFERENCES Athlete(`athleteID`)
);

CREATE TABLE `MedicalData` (
    medicalDataID INTEGER AUTO_INCREMENT NOT NULL,
    athleteID INTEGER NOT NULL,
    injuries VARCHAR(100),
    heightCM INTEGER,
    weightKG INTEGER,
    armSpanCM INTEGER,
    basicCore VARCHAR(50),
    bCNotes VARCHAR(50),
    flexibility VARCHAR(50),
    fNotes VARCHAR(50),
    PRIMARY KEY PKMedicalData(medicalDataID),
    FOREIGN KEY FKMedicalData(athleteID) REFERENCES Athlete(athleteID)
);

CREATE TABLE `AthletePreviousSports` (
    apsID INTEGER AUTO_INCREMENT NOT NULL,
    athleteID INTEGER NOT NULL,
    previousSport VARCHAR(100),
    monthsTesting VARCHAR(50),
    sessionsPerWeek VARCHAR(50),
    endurancePerWeek VARCHAR(50),
    strengthPerWeek VARCHAR(50),
    yearsAtLevel VARCHAR(50),
    PRIMARY KEY PKAthletePreviousSports(apsID),
    FOREIGN KEY FKAthletePreviousSports(athleteID) REFERENCES Athlete(athleteID)

);

CREATE TABLE `Interview` (
    interviewID INTEGER AUTO_INCREMENT NOT NULL,
    athleteID INTEGER NOT NULL,
    answer1 VARCHAR(100),
    answer2 VARCHAR(100),
    answer3 VARCHAR(100),
    answer4 VARCHAR(100),
    answer5 VARCHAR(100),
    answer6 VARCHAR(100),
    answer7 int,
    answer8 int,
    answer9 int,
    answer10 int,
    answer11 VARCHAR(100),
    answer12 VARCHAR(100),
    answer13 VARCHAR(100),
    answer14 int,
    answer15 int,
    answer16 int,
    answer17 int,
    answer18 int,
    answer19 int,
    answer20 int,
    answer21 int,
    answer22 int,
    answer23 int,
    answer24 int,
    PRIMARY KEY PKInterview(interviewID),
    FOREIGN KEY FKInterview(athleteID) REFERENCES Athlete(athleteID)
);

CREATE TABLE `Coach` (
    coachID INTEGER AUTO_INCREMENT,
    name VARCHAR(20),
    username VARCHAR(20),
    password VARCHAR(20),
    PRIMARY KEY PKCoach(coachID)
);

CREATE TABLE `AthleteTest` (
   athleteTestID INTEGER AUTO_INCREMENT NOT NULL,
   athleteID INTEGER NOT NULL,
   coachID INTEGER NOT NULL,
   dateOfTest DATE,
   athleteComments VARCHAR(100),
   legPress3Reps INTEGER NOT NULL,
   armPress3Reps INTEGER NOT NULL,
   armPull3Reps INTEGER NOT NULL,
   armPull15Reps INTEGER NOT NULL,
   score INTEGER NOT NULL,
   observations VARCHAR(150),
   PRIMARY KEY PKAthleteTest (athleteTestID),
   FOREIGN KEY FKAthleteTest(athleteID) REFERENCES Athlete(athleteID),
   FOREIGN KEY FKCoachAthleteTest (coachID) REFERENCES Coach(coachID)
);


CREATE TABLE `MorningMonitoring` (
    monitoringID INTEGER NOT NULL AUTO_INCREMENT,
    athleteID INTEGER NOT NULL,
    date DATE,
    walkingHeartRate INTEGER NOT NULL,
    standingHeartRate INTEGER NOT NULL,
    perceivedShape INTEGER NOT NULL,
    perceivedMentalState INTEGER NOT NULL,
    sleepQuantityHours INTEGER NOT NULL,
    sleepQuality INTEGER,
    PRIMARY KEY PKMorningMonitoring(monitoringID),
    FOREIGN KEY FKMorningMonitoring(athleteID) REFERENCES Athlete(athleteID)
);

CREATE TABLE `SessionRPE` (
    sessionRPEID INTEGER NOT NULL AUTO_INCREMENT,
    athleteID INTEGER NOT NULL,
    dateOfSession DATE,
    typeOfSession VARCHAR(50),
    RPE INTEGER,
    sessionDurationMinutes INTEGER,
    PRIMARY KEY PKSessionRPE(sessionRPEID),
    FOREIGN KEY FKSessionRPE(athleteID) REFERENCES Athlete(athleteID)
);

CREATE TABLE `CrossTraining` (
    crossTrainingID INTEGER NOT NULL AUTO_INCREMENT,
    athleteID INTEGER NOT NULL,
    dateOfSession DATE,
    typeOfCrossTraining VARCHAR(50),
    totalTimeMinutes INTEGER,
    totalDistance INTEGER,
    PRIMARY KEY PKCrossTraining(crossTrainingID),
    FOREIGN KEY FKCrossTraining(athleteID) REFERENCES Athlete(athleteID)
);



DELIMITER //

# Trigger in the event the role of the user during signup is left blank/null, auto default role to 'User'
CREATE TRIGGER roleCheckUser BEFORE INSERT ON User FOR EACH ROW IF NEW.role = NULL
THEN SET NEW.role = 'User'; END IF;//

# Trigger in the event that Athlete DOB is greater than the current date, it will auto to NULL
CREATE TRIGGER dobCheckAthlete BEFORE INSERT ON Athlete FOR EACH ROW IF NEW.DOB > CURDATE()
    THEN SET NEW.DOB = NULL; END IF;//

# Trigger in the event that bCNotes is left blank/null, set the value in the database to N/A
CREATE TRIGGER notesMedicalBC BEFORE INSERT ON MedicalData FOR EACH ROW IF NEW.bCNotes = NULL
    THEN SET NEW.bCNotes = 'N/A'; END IF ;//

# Trigger in the event that fNotes is left blank/null, set the value in the database to N/A
CREATE TRIGGER notesMedicalF BEFORE INSERT ON MedicalData FOR EACH ROW IF NEW.fNotes = NULL
    THEN SET NEW.fNotes = 'N/A'; END IF ;//

# Trigger in the event the INT for question 7 is not answered/null default the value to 0
CREATE TRIGGER interviewIntNullSeven BEFORE INSERT ON Interview FOR EACH ROW IF NEW.answer7 = NULL
    THEN SET NEW.answer7 = 0; END IF;//

# Trigger in the event the INT for question 8 is not answered/null default the value to 0
CREATE TRIGGER interviewIntNullEight BEFORE INSERT ON Interview FOR EACH ROW IF NEW.answer8 = NULL
THEN SET NEW.answer8 = 0; END IF;//

# Trigger in the event the INT for question 9 is not answered/null default the value to 0
CREATE TRIGGER interviewIntNullNine BEFORE INSERT ON Interview FOR EACH ROW IF NEW.answer9 = NULL
THEN SET NEW.answer9 = 0; END IF;//

# Trigger in the event the INT for question 9 is not answered/null default the value to 0
CREATE TRIGGER interviewIntNullTen BEFORE INSERT ON Interview FOR EACH ROW IF NEW.answer10 = NULL
    THEN SET NEW.answer10 = 0; END IF;//

#14-25 INT CHECKER
CREATE TRIGGER interviewIntNullFourteen BEFORE INSERT ON Interview FOR EACH ROW IF NEW.answer14 = NULL
THEN SET NEW.answer14 = 0; END IF;//

DELIMITER ;
