# https://lucid.app/lucidchart/invitations/accept/fade6f95-af6c-4643-9f07-1e402e18cd19 following schema

#creates admin user with password adminpassword and gives them all permissions over all tables
DROP USER IF EXISTS 'admin'@'localhost';
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'adminpassword';
GRANT ALL PRIVILEGES ON * . * TO 'admin'@'localhost';
FLUSH PRIVILEGES;

#creates coach user with password coachpassword and gives them all permissions to insert,select,update, and delete on all tables.
DROP USER IF EXISTS 'webappuser'@'localhost';
CREATE USER 'webappuser'@'localhost' IDENTIFIED BY 'XuKIB5IN';
GRANT INSERT ON welshrowing.* TO 'webappuser'@'localhost';
GRANT SELECT ON welshrowing.* TO 'webappuser'@'localhost';
GRANT UPDATE ON welshrowing.* TO 'webappuser'@'localhost';
GRANT DELETE ON welshrowing.* TO 'webappuser'@'localhost';
FLUSH PRIVILEGES;

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
    postTestResult VARCHAR(60),
    PRIMARY KEY PKAthlete(athleteID),
    FOREIGN KEY FKAthlete(`coachID`) REFERENCES Athlete(`athleteID`)
);

CREATE TABLE `MedicalData` (
    medicalDataID INTEGER AUTO_INCREMENT NOT NULL,
    athleteID INTEGER NOT NULL,
    injuries VARBINARY(255),
    heightCM VARBINARY(255),
    weightKG VARBINARY(255),
    armSpanCM VARBINARY(255),
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
   coachID INTEGER,
   dateOfTest DATE,
   athleteComments VARCHAR(100),
   legPress3Reps INTEGER NOT NULL,
   armPress3Reps INTEGER NOT NULL,
   armPull3Reps INTEGER NOT NULL,
   armPull15Reps INTEGER NOT NULL,
   score INTEGER NOT NULL,
   observations VARCHAR(150),
   basicCore VARCHAR(5),
   bCNotes VARCHAR(50),
   flexibility VARCHAR(5),
   fNotes VARCHAR(50),
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


CREATE VIEW crossTrainingAdmins
AS
  SELECT crossTrainingID, athleteID, dateofSession, typeOfCrossTraining, totalTimeMinutes, totalDistance FROM CrossTraining;

CREATE VIEW crossTrainingCoaches
AS
  SELECT crossTrainingID, athleteID, dateofSession, typeOfCrossTraining, totalTimeMinutes, totalDistance FROM CrossTraining;

CREATE VIEW crossTrainingAthletes
AS
  SELECT crossTrainingID, athleteID, dateofSession, typeOfCrossTraining, totalTimeMinutes, totalDistance FROM CrossTraining;

CREATE VIEW interviewAdmins
AS
  SELECT interviewID, athleteID, answer1, answer2, answer3, answer4, answer5, answer6, answer7, answer8, answer9, answer10, answer11, answer12, answer13, answer14, answer15, answer16, answer17, answer18, answer19, answer20, answer21, answer22, answer23, answer24 FROM interview;

CREATE VIEW interviewCoaches
AS
  SELECT interviewID, athleteID, answer1, answer2, answer3, answer4, answer5, answer6, answer7, answer8, answer9, answer10, answer11, answer12, answer13, answer14, answer15, answer16, answer17, answer18, answer19, answer20, answer21, answer22, answer23, answer24 FROM interview;

CREATE VIEW interviewAthletes
AS
  SELECT interviewID, athleteID FROM interview;

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


-- Trigger that encrypts an athletes medical data before it is inserted into the database.
CREATE TRIGGER encrypt_medical_data BEFORE INSERT ON MedicalData FOR EACH ROW
BEGIN
    SET NEW.injuries = AES_ENCRYPT(NEW.injuries, 'ypT9pYnCjTuP8n4jr7eW');
    SET NEW.heightCM = AES_ENCRYPT(NEW.heightCM, 'ypT9pYnCjTuP8n4jr7eW');
    SET NEW.weightKG = AES_ENCRYPT(NEW.weightKG, 'ypT9pYnCjTuP8n4jr7eW');
    SET NEW.armSpanCM = AES_ENCRYPT(NEW.armSpanCM, 'ypT9pYnCjTuP8n4jr7eW');
END//
-- Trigger that encrypts an athletes medical data before it is updated and saved to the database.
CREATE TRIGGER encrypt_medical_data_update BEFORE UPDATE ON MedicalData FOR EACH ROW
BEGIN
    SET NEW.injuries = AES_ENCRYPT(NEW.injuries, 'ypT9pYnCjTuP8n4jr7eW');
    SET NEW.heightCM = AES_ENCRYPT(NEW.heightCM, 'ypT9pYnCjTuP8n4jr7eW');
    SET NEW.weightKG = AES_ENCRYPT(NEW.weightKG, 'ypT9pYnCjTuP8n4jr7eW');
    SET NEW.armSpanCM = AES_ENCRYPT(NEW.armSpanCM, 'ypT9pYnCjTuP8n4jr7eW');
END//

-- SP that if provided the correct key, will decrypt an athletes medical data if updates are needed.
CREATE PROCEDURE decrypt_data(
    IN theAthleteID INT,
    IN theKey VARCHAR(255)
)
BEGIN
    SELECT medicalDataID, athleteID,
           AES_DECRYPT(injuries, theKey) as 'injuries',
           AES_DECRYPT(heightCM, theKey) as 'heightCM',
           AES_DECRYPT(weightKG, theKey) as 'weightKG',
           AES_DECRYPT(armSpanCM, theKey) as 'armSpanCM'
    FROM MedicalData WHERE athleteID = theAthleteID;

END //
DELIMITER ;

CREATE TABLE `UserAudit` (
     athleteID INTEGER NOT NULL,
     deletedDate DATE,
     deleted_by VARCHAR(50)
);




  
   DELIMITER //

CREATE TRIGGER athlete_after_delete
AFTER DELETE
   ON athlete FOR EACH ROW

BEGIN

   DECLARE userName varchar(50);

   -- Find username of person performing the DELETE into table
   SELECT USER() INTO userName;

   -- Insert record into audit table
   INSERT INTO UserAudit
   ( athleteID,
     deletedDate,
     deleted_by)
   VALUES
   ( OLD.athleteID,
     SYSDATE(),
     userName );

END; //

DELIMITER ;

   DELIMITER //

CREATE TRIGGER athletetest_after_delete
AFTER DELETE
   ON athletetest FOR EACH ROW

BEGIN

   DECLARE userName varchar(50);

   -- Find username of person performing the DELETE into table
   SELECT USER() INTO userName;

   -- Insert record into audit table
   INSERT INTO UserAudit
   ( athleteID,
     deletedDate,
     deleted_by)
   VALUES
   ( OLD.athleteID,
     SYSDATE(),
     userName );

END; //

DELIMITER ;

   DELIMITER //

CREATE TRIGGER crosstraining_after_delete
AFTER DELETE
   ON crosstraining FOR EACH ROW

BEGIN

   DECLARE userName varchar(50);

   -- Find username of person performing the DELETE into table
   SELECT USER() INTO userName;

   -- Insert record into audit table
   INSERT INTO UserAudit
   ( athleteID,
     deletedDate,
     deleted_by)
   VALUES
   ( OLD.athleteID,
     SYSDATE(),
     userName );

END; //

DELIMITER ;

   DELIMITER //

CREATE TRIGGER medicaldata_after_delete
AFTER DELETE
   ON medicaldata FOR EACH ROW

BEGIN

   DECLARE userName varchar(50);

   -- Find username of person performing the DELETE into table
   SELECT USER() INTO userName;

   -- Insert record into audit table
   INSERT INTO UserAudit
   ( athleteID,
     deletedDate,
     deleted_by)
   VALUES
   ( OLD.athleteID,
     SYSDATE(),
     userName );

END; //

DELIMITER ;

   DELIMITER //

CREATE TRIGGER morningmonitoring_after_delete
AFTER DELETE
   ON morningmonitoring FOR EACH ROW

BEGIN

   DECLARE userName varchar(50);

   -- Find username of person performing the DELETE into table
   SELECT USER() INTO userName;

   -- Insert record into audit table
   INSERT INTO UserAudit
   ( athleteID,
     deletedDate,
     deleted_by)
   VALUES
   ( OLD.athleteID,
     SYSDATE(),
     userName );

END; //

DELIMITER ;

   DELIMITER //

CREATE TRIGGER sessionrpe_after_delete
AFTER DELETE
   ON sessionrpe FOR EACH ROW

BEGIN

   DECLARE userName varchar(50);

   -- Find username of person performing the DELETE into table
   SELECT USER() INTO userName;

   -- Insert record into audit table
   INSERT INTO UserAudit
   ( athleteID,
     deletedDate,
     deleted_by)
   VALUES
   ( OLD.athleteID,
     SYSDATE(),
     userName );

END; //

DELIMITER ;
   DELIMITER //

CREATE TRIGGER user_after_delete
AFTER DELETE
   ON user FOR EACH ROW

BEGIN

   DECLARE userName varchar(50);

   -- Find username of person performing the DELETE into table
   SELECT USER() INTO userName;

   -- Insert record into audit table
   INSERT INTO UserAudit
   ( athleteID,
     deletedDate,
     deleted_by)
   VALUES
   ( OLD.userID,
     SYSDATE(),
     userName );

END; //

DELIMITER ;

CREATE VIEW userAuditAdmin
AS
	SELECT athleteID, deletedDate, deleted_by FROM useraudit;
    
CREATE VIEW userAuditCoach
AS
	SELECT deletedDate FROM useraudit;
    
   
DELIMITER //
CREATE DEFINER='webappuser'@'localhost' PROCEDURE user_cnt()
SQL SECURITY INVOKER
BEGIN
SELECT COUNT(*) as total_users FROM mysql.user;
END;//
DELIMITER ;

DELIMITER //
CREATE DEFINER='webappuser'@'localhost' PROCEDURE find_number_applicants()
SQL SECURITY INVOKER
BEGIN
SELECT COUNT(*) as total_applicants FROM athlete WHERE applicationStatus=1;
END;//
DELIMITER ;

DELIMITER //
CREATE DEFINER='webappuser'@'localhost' PROCEDURE find_number_coaches()
SQL SECURITY INVOKER
BEGIN
SELECT COUNT(*) as total_coaches FROM coach;
END;//
DELIMITER ;

DELIMITER //
CREATE DEFINER='webappuser'@'localhost' PROCEDURE find_number_athletes()
SQL SECURITY INVOKER
BEGIN
	SELECT COUNT(*) as total_applicants FROM athlete WHERE applicationStatus=0;
END;//
DELIMITER ;

GRANT EXECUTE ON PROCEDURE welshrowing.find_number_athletes TO 'webappuser'@'localhost';
GRANT EXECUTE ON PROCEDURE welshrowing.find_number_coaches TO 'webappuser'@'localhost';
GRANT EXECUTE ON PROCEDURE welshrowing.find_number_applicants TO 'webappuser'@'localhost';
GRANT EXECUTE ON PROCEDURE welshrowing.user_cnt TO 'webappuser'@'localhost';
GRANT EXECUTE ON PROCEDURE welshrowing.decrypt_data TO 'webappuser'@'localhost';

#inserting dummy data
INSERT INTO user(name,username,password,role) VALUES ("Bob Smith", "userbob", "$2a$10$3/Gbi2ytLNUsPgIoB8oeF.KllszbevLs4IxBmTbtNq48g8qD1PqDy","athlete");
INSERT INTO user(name,username,password,role) VALUES ("James Dean", "userjames", "$2a$10$Snmk439qzaDR7XYVxGrsbesPrulnJywTOnX20VZP5cL3htcz20nRm","athlete");
INSERT INTO user(name,username,password,role) VALUES ("Bob Ross", "painterbob", "$2a$10$AUxk0RXEIQ0wxbZjP8uFleCgTd96RNxpdgtqvQ83jEUzkxJIPVSky","athlete");
INSERT INTO user(name,username,password,role) VALUES ("Hamid Iqbal", "hamidiqbal", "$2a$10$wIq0AGD1hxeVUm7r9pN5memAdWg37MbqQX3iuk4suMVQcsOVf0nI2","athlete");
INSERT INTO user(name,username,password,role) VALUES ("Oliver Holdaway", "oliverholdaway", "$2a$10$2TwjPtNdaAuHj0PFXXSO5uIB2E8UIyfyh7hD29ysDyry5PlQPe2RK","athlete");

INSERT INTO user(name,username,password,role) VALUES ("Coach Name", "coachaccount", "$2a$10$hczrqi5VYWFoOBxUoGmsfudaJB6ZLEDjTj5QMDAub9NsUq9X0/KQG","coach");
INSERT INTO user(name,username,password,role) VALUES ("Nathan Baitup", "nathanbaitup", "$2a$10$aOrE8ZR.BEsL36mbnZAdLu5ZfXZdm9PykwlA29NefyWjvIOyGqzWK","coach");

INSERT INTO athlete(athleteID,name,gender,DOB,applicationStatus,email,mobileNumber,telephoneNumber,address,postcode,placeOfEducation,interestLetter,postTestResult)
VALUES (1,"Bob Smith","Male","2000-10-28",1,"bob@gmail.com","01757507758","02972503307","14 park road","PO13 5HE","Cardiff University",true,"");
INSERT INTO athlete(athleteID,name,gender,DOB,applicationStatus,email,mobileNumber,telephoneNumber,address,postcode,placeOfEducation,interestLetter,postTestResult)
VALUES (2,"James Dean","Male","2000-12-15",1,"james@gmail.com","01757506558","02972453307","14 green road","PO13 5HE","Cardiff University",true,"");
INSERT INTO athlete(athleteID,name,gender,DOB,applicationStatus,email,mobileNumber,telephoneNumber,address,postcode,placeOfEducation,interestLetter,postTestResult)
VALUES (3,"Bob Ross","Male","2000-08-15",0,"bobross@gmail.com","01753206558","02472453307","14 lake road","PO13 5HE","Cardiff University",true,"8 Weeks");
INSERT INTO athlete(athleteID,name,gender,DOB,applicationStatus,email,mobileNumber,telephoneNumber,address,postcode,placeOfEducation,interestLetter,postTestResult)
VALUES (4,"Hamid Iqbal","Male","2001-08-15",0,"hamid@gmail.com","01753209958","02472453907","14 red close","PO13 5HE","Cardiff University",true,"8 Weeks");
INSERT INTO athlete(athleteID,name,gender,DOB,applicationStatus,email,mobileNumber,telephoneNumber,address,postcode,placeOfEducation,interestLetter,postTestResult)
VALUES (5,"Oliver Holdaway","Male","2000-10-27",0,"oliver@gmail.com","01753209658","02473353907","19 red close","PO13 5HE","Cardiff University",true,"8 Weeks");

INSERT INTO morningmonitoring(athleteID,date,walkingHeartRate,standingHeartRate,perceivedShape,perceivedMentalState,sleepQuantityHours,sleepQuality)
VALUES (3,"2020-12-16",56,40,7,4,9,9);
INSERT INTO morningmonitoring(athleteID,date,walkingHeartRate,standingHeartRate,perceivedShape,perceivedMentalState,sleepQuantityHours,sleepQuality)
VALUES (3,"2020-12-17",70,99,6,7,8,9);
INSERT INTO morningmonitoring(athleteID,date,walkingHeartRate,standingHeartRate,perceivedShape,perceivedMentalState,sleepQuantityHours,sleepQuality)
VALUES (3,"2020-12-18",55,56,7,4,9,9);
INSERT INTO morningmonitoring(athleteID,date,walkingHeartRate,standingHeartRate,perceivedShape,perceivedMentalState,sleepQuantityHours,sleepQuality)
VALUES (3,"2020-12-19",65,70,4,3,7,9);
INSERT INTO morningmonitoring(athleteID,date,walkingHeartRate,standingHeartRate,perceivedShape,perceivedMentalState,sleepQuantityHours,sleepQuality)
VALUES (3,"2020-12-20",38,40,7,7,5,5);
INSERT INTO morningmonitoring(athleteID,date,walkingHeartRate,standingHeartRate,perceivedShape,perceivedMentalState,sleepQuantityHours,sleepQuality)
VALUES (3,"2020-12-21",56,60,2,1,7,9);
INSERT INTO morningmonitoring(athleteID,date,walkingHeartRate,standingHeartRate,perceivedShape,perceivedMentalState,sleepQuantityHours,sleepQuality)
VALUES (3,"2020-12-22",56,80,9,9,6,9);

INSERT INTO morningmonitoring(athleteID,date,walkingHeartRate,standingHeartRate,perceivedShape,perceivedMentalState,sleepQuantityHours,sleepQuality)
VALUES (4,"2020-12-16",67,90,7,4,9,9);
INSERT INTO morningmonitoring(athleteID,date,walkingHeartRate,standingHeartRate,perceivedShape,perceivedMentalState,sleepQuantityHours,sleepQuality)
VALUES (4,"2020-12-17",70,99,6,7,8,9);
INSERT INTO morningmonitoring(athleteID,date,walkingHeartRate,standingHeartRate,perceivedShape,perceivedMentalState,sleepQuantityHours,sleepQuality)
VALUES (4,"2020-12-18",33,44,7,4,9,9);
INSERT INTO morningmonitoring(athleteID,date,walkingHeartRate,standingHeartRate,perceivedShape,perceivedMentalState,sleepQuantityHours,sleepQuality)
VALUES (4,"2020-12-19",65,70,4,3,7,9);
INSERT INTO morningmonitoring(athleteID,date,walkingHeartRate,standingHeartRate,perceivedShape,perceivedMentalState,sleepQuantityHours,sleepQuality)
VALUES (4,"2020-12-20",38,40,7,7,5,5);
INSERT INTO morningmonitoring(athleteID,date,walkingHeartRate,standingHeartRate,perceivedShape,perceivedMentalState,sleepQuantityHours,sleepQuality)
VALUES (4,"2020-12-21",60,70,2,1,7,9);
INSERT INTO morningmonitoring(athleteID,date,walkingHeartRate,standingHeartRate,perceivedShape,perceivedMentalState,sleepQuantityHours,sleepQuality)
VALUES (4,"2020-12-22",56,80,9,9,6,9);

INSERT INTO morningmonitoring(athleteID,date,walkingHeartRate,standingHeartRate,perceivedShape,perceivedMentalState,sleepQuantityHours,sleepQuality)
VALUES (5,"2020-12-16",30,90,7,4,9,9);
INSERT INTO morningmonitoring(athleteID,date,walkingHeartRate,standingHeartRate,perceivedShape,perceivedMentalState,sleepQuantityHours,sleepQuality)
VALUES (5,"2020-12-17",56,70,6,7,8,9);
INSERT INTO morningmonitoring(athleteID,date,walkingHeartRate,standingHeartRate,perceivedShape,perceivedMentalState,sleepQuantityHours,sleepQuality)
VALUES (5,"2020-12-18",36,43,7,4,9,9);
INSERT INTO morningmonitoring(athleteID,date,walkingHeartRate,standingHeartRate,perceivedShape,perceivedMentalState,sleepQuantityHours,sleepQuality)
VALUES (5,"2020-12-19",57,70,4,3,7,9);
INSERT INTO morningmonitoring(athleteID,date,walkingHeartRate,standingHeartRate,perceivedShape,perceivedMentalState,sleepQuantityHours,sleepQuality)
VALUES (5,"2020-12-20",38,40,7,7,5,5);
INSERT INTO morningmonitoring(athleteID,date,walkingHeartRate,standingHeartRate,perceivedShape,perceivedMentalState,sleepQuantityHours,sleepQuality)
VALUES (5,"2020-12-21",50,70,2,1,7,9);
INSERT INTO morningmonitoring(athleteID,date,walkingHeartRate,standingHeartRate,perceivedShape,perceivedMentalState,sleepQuantityHours,sleepQuality)
VALUES (5,"2020-12-22",66,80,9,9,6,9);