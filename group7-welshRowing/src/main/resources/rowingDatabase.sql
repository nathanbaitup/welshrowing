DROP DATABASE IF EXISTS welshRowing;

CREATE DATABASE IF NOT EXISTS welshRowing;

USE welshRowing;

CREATE TABLE `Athlete` (
    athleteID INTEGER NOT NULL AUTO_INCREMENT NOT NULL,
    coachID INTEGER NOT NULL,
    name VARCHAR(30),
    gender VARCHAR(10),
    DOB DATE,
    applicationStatus BOOLEAN,
    email VARCHAR(20),
    password VARCHAR(20),
    mobileNumber VARCHAR(50), # allows country code and spaces and to ensure digits only
    telephoneNumber VARCHAR(50),
    placeOfEducation VARCHAR(50),
    guardianName VARCHAR(50),
    relationshipToAthlete VARCHAR(20),
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
    questions VARCHAR(100),
    PRIMARY KEY PKInterview(interviewID),
    FOREIGN KEY FKInterview(athleteID) REFERENCES Athlete(athleteID)
);

CREATE TABLE `Coach` (
    coachID INTEGER AUTO_INCREMENT NOT NULL,
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
    date DATE,
    typeOfSession VARCHAR(50),
    RPE INTEGER,
    sessionDurationMinutes INTEGER,
    PRIMARY KEY PKSessionRPE(sessionRPEID),
    FOREIGN KEY FKSessionRPE(athleteID) REFERENCES Athlete(athleteID)
);

CREATE TABLE `CrossTraining` (
    crossTrainingID INTEGER NOT NULL AUTO_INCREMENT,
    athleteID INTEGER NOT NULL,
    date DATE,
    typeOfCrossTraining VARCHAR(50),
    totalTimeMinutes INTEGER,
    totalDistance VARCHAR(20),
    PRIMARY KEY PKCrossTraining(crossTrainingID),
    FOREIGN KEY FKCrossTraining(athleteID) REFERENCES Athlete(athleteID)
);
