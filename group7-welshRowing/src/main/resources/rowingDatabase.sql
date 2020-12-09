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

# Trigger for validation of Email within the Athlete table, checks to see if email contains '@'
# Signals SQL 45000 signing a unhandled exception, and prints out the message to the console
CREATE TRIGGER emailValidationAthlete BEFORE INSERT ON Athlete FOR EACH ROW
    BEGIN
        IF NEW.`email` NOT LIKE '%_@%_.__%' THEN
            SIGNAL SQLSTATE VALUE '45000'
            SET MESSAGE_TEXT = 'Athlete Table - Email = This is not a valid email, please try again';
        end if;
    end //

# Trigger for validation of Interview questions, making sure they are not null
# If null will trigger SQL Signal state printing out a message to the user
# Running for question 14-24

CREATE TRIGGER interviewNullOne BEFORE INSERT ON Interview FOR EACH ROW
BEGIN
    IF NEW.answer1 IS NULL THEN
        SIGNAL SQLSTATE VALUE '45000'
            SET MESSAGE_TEXT = 'Question 1 is NULL, please select / type an option';
    end if;
end //

CREATE TRIGGER interviewNullTwo BEFORE INSERT ON Interview FOR EACH ROW
BEGIN
    IF NEW.answer2 IS NULL THEN
        SIGNAL SQLSTATE VALUE '45000'
            SET MESSAGE_TEXT = 'Question 2 is NULL, please select / type an option';
    end if;
end //

CREATE TRIGGER interviewNullThree BEFORE INSERT ON Interview FOR EACH ROW
BEGIN
    IF NEW.answer3 IS NULL THEN
        SIGNAL SQLSTATE VALUE '45000'
            SET MESSAGE_TEXT = 'Question 3 is NULL, please select / type an option';
    end if;
end //

CREATE TRIGGER interviewNullFour BEFORE INSERT ON Interview FOR EACH ROW
BEGIN
    IF NEW.answer4 IS NULL THEN
        SIGNAL SQLSTATE VALUE '45000'
            SET MESSAGE_TEXT = 'Question 4 is NULL, please select / type an option';
    end if;
end //

CREATE TRIGGER interviewNullFive BEFORE INSERT ON Interview FOR EACH ROW
BEGIN
    IF NEW.answer5 IS NULL THEN
        SIGNAL SQLSTATE VALUE '45000'
            SET MESSAGE_TEXT = 'Question 5 is NULL, please select / type an option';
    end if;
end //

CREATE TRIGGER interviewNullSix BEFORE INSERT ON Interview FOR EACH ROW
BEGIN
    IF NEW.answer6 IS NULL THEN
        SIGNAL SQLSTATE VALUE '45000'
            SET MESSAGE_TEXT = 'Question 6 is NULL, please select / type an option';
    end if;
end //

CREATE TRIGGER interviewNullSeven BEFORE INSERT ON Interview FOR EACH ROW
BEGIN
    IF NEW.answer7 IS NULL THEN
        SIGNAL SQLSTATE VALUE '45000'
            SET MESSAGE_TEXT = 'Question 7 is NULL, please select / type an option';
    end if;
end //

CREATE TRIGGER interviewNullEight BEFORE INSERT ON Interview FOR EACH ROW
BEGIN
    IF NEW.answer8 IS NULL THEN
        SIGNAL SQLSTATE VALUE '45000'
            SET MESSAGE_TEXT = 'Question 8 is NULL, please select / type an option';
    end if;
end //

CREATE TRIGGER interviewNullNine BEFORE INSERT ON Interview FOR EACH ROW
BEGIN
    IF NEW.answer9 IS NULL THEN
        SIGNAL SQLSTATE VALUE '45000'
            SET MESSAGE_TEXT = 'Question 9 is NULL, please select / type an option';
    end if;
end //

CREATE TRIGGER interviewNullTen BEFORE INSERT ON Interview FOR EACH ROW
BEGIN
    IF NEW.answer10 IS NULL THEN
        SIGNAL SQLSTATE VALUE '45000'
            SET MESSAGE_TEXT = 'Question 10 is NULL, please select / type an option';
    end if;
end //

CREATE TRIGGER interviewNullEleven BEFORE INSERT ON Interview FOR EACH ROW
BEGIN
    IF NEW.answer11 IS NULL THEN
        SIGNAL SQLSTATE VALUE '45000'
            SET MESSAGE_TEXT = 'Question 11 is NULL, please select / type an option';
    end if;
end //

CREATE TRIGGER interviewNullTwelve BEFORE INSERT ON Interview FOR EACH ROW
BEGIN
    IF NEW.answer12 IS NULL THEN
        SIGNAL SQLSTATE VALUE '45000'
            SET MESSAGE_TEXT = 'Question 12 is NULL, please select / type an option';
    end if;
end //

CREATE TRIGGER interviewNullThirteen BEFORE INSERT ON Interview FOR EACH ROW
BEGIN
    IF NEW.answer13 IS NULL THEN
        SIGNAL SQLSTATE VALUE '45000'
            SET MESSAGE_TEXT = 'Question 13 is NULL, please select / type an option';
    end if;
end //

CREATE TRIGGER interviewNullFourteen BEFORE INSERT ON Interview FOR EACH ROW
    BEGIN
        IF NEW.answer14 IS NULL THEN
            SIGNAL SQLSTATE VALUE '45000'
            SET MESSAGE_TEXT = 'Question 14 is NULL, please select / type an option';
        end if;
    end //

CREATE TRIGGER interviewNullFifteen BEFORE INSERT ON Interview FOR EACH ROW
BEGIN
    IF NEW.answer15 IS NULL THEN
        SIGNAL SQLSTATE VALUE '45000'
            SET MESSAGE_TEXT = 'Question 15 is NULL, please select / type an option';
    end if;
end //

CREATE TRIGGER interviewNullSixteen BEFORE INSERT ON Interview FOR EACH ROW
BEGIN
    IF NEW.answer16 IS NULL THEN
        SIGNAL SQLSTATE VALUE '45000'
            SET MESSAGE_TEXT = 'Question 16 is NULL, please select / type an option';
    end if;
end //

CREATE TRIGGER interviewNullSeventeen BEFORE INSERT ON Interview FOR EACH ROW
BEGIN
    IF NEW.answer17 IS NULL THEN
        SIGNAL SQLSTATE VALUE '45000'
            SET MESSAGE_TEXT = 'Question 17 is NULL, please select / type an option';
    end if;
end //

CREATE TRIGGER interviewNullEighteen BEFORE INSERT ON Interview FOR EACH ROW
BEGIN
    IF NEW.answer18 IS NULL THEN
        SIGNAL SQLSTATE VALUE '45000'
            SET MESSAGE_TEXT = 'Question 18 is NULL, please select / type an option';
    end if;
end //

CREATE TRIGGER interviewNullNineteen BEFORE INSERT ON Interview FOR EACH ROW
BEGIN
    IF NEW.answer19 IS NULL THEN
        SIGNAL SQLSTATE VALUE '45000'
            SET MESSAGE_TEXT = 'Question 19 is NULL, please select / type an option';
    end if;
end //

CREATE TRIGGER interviewNullTwenty BEFORE INSERT ON Interview FOR EACH ROW
BEGIN
    IF NEW.answer20 IS NULL THEN
        SIGNAL SQLSTATE VALUE '45000'
            SET MESSAGE_TEXT = 'Question 20 is NULL, please select / type an option';
    end if;
end //

CREATE TRIGGER interviewNullTwentyOne BEFORE INSERT ON Interview FOR EACH ROW
BEGIN
    IF NEW.answer21 IS NULL THEN
        SIGNAL SQLSTATE VALUE '45000'
            SET MESSAGE_TEXT = 'Question 21 is NULL, please select / type an option';
    end if;
end //

CREATE TRIGGER interviewNullTwentyTwo BEFORE INSERT ON Interview FOR EACH ROW
BEGIN
    IF NEW.answer22 IS NULL THEN
        SIGNAL SQLSTATE VALUE '45000'
            SET MESSAGE_TEXT = 'Question 22 is NULL, please select / type an option';
    end if;
end //

CREATE TRIGGER interviewNullTwentyThree BEFORE INSERT ON Interview FOR EACH ROW
BEGIN
    IF NEW.answer23 IS NULL THEN
        SIGNAL SQLSTATE VALUE '45000'
            SET MESSAGE_TEXT = 'Question 23 is NULL, please select / type an option';
    end if;
end //

CREATE TRIGGER interviewNullTwentyFour BEFORE INSERT ON Interview FOR EACH ROW
BEGIN
    IF NEW.answer24 IS NULL THEN
        SIGNAL SQLSTATE VALUE '45000'
            SET MESSAGE_TEXT = 'Question 24 is NULL, please select / type an option';
    end if;
end //

DELIMITER ;

