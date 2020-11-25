DROP DATABASE IF EXISTS welshRowing;

CREATE DATABASE IF NOT EXISTS welshRowing;

USE welshRowing;

CREATE TABLE `Athlete` (
    AthleteID INTEGER NOT NULL AUTO_INCREMENT NOT NULL,
    CoachID INTEGER NOT NULL,
    FirstName VARCHAR(8),
    LastName VARCHAR(8),
    DOB DATE,
    ApplicationStatus BOOLEAN,
    Email VARCHAR(20),
    Password VARCHAR(20),
    MobileNumber VARCHAR(50), # allows country code and spaces and to ensure digits only
    TelephoneNumber VARCHAR(50),
    PlaceOfEducation VARCHAR(50),
    GuardianName VARCHAR(50),
    RelationshipToAthlete VARCHAR(20),
    GuardianContactNumber VARCHAR(50),
    HeardFrom VARCHAR(40),
    InterestLetter BOOLEAN,
    PostTestResult VARCHAR(30),
    PRIMARY KEY PKAthlete(AthleteID),
    FOREIGN KEY FKAthlete(`CoachID`) REFERENCES Athlete(`AthleteID`)
);

CREATE TABLE `MedicalData` (
    MedicalDataID INTEGER AUTO_INCREMENT NOT NULL,
    AthleteID INTEGER NOT NULL,
    Injuries VARCHAR(100),
    HeightCM INTEGER,
    WeightKG INTEGER,
    ArmSpanCM INTEGER,
    BasicCore VARCHAR(50),
    BCNotes VARCHAR(50),
    Flexibility VARCHAR(50),
    FNotes VARCHAR(50),
    PRIMARY KEY PKMedicalData(MedicalDataID),
    FOREIGN KEY FKMedicalData(AthleteID) REFERENCES Athlete(AthleteID)
);

CREATE TABLE `AthletePreviousSports` (
    ApsID INTEGER AUTO_INCREMENT NOT NULL,
    AthleteID INTEGER NOT NULL,
    PreviousSport VARCHAR(100),
    MonthsTesting VARCHAR(50),
    SessionsPerWeek VARCHAR(50),
    EndurancePerWeek VARCHAR(50),
    StrengthPerWeek VARCHAR(50),
    YearsAtLevel VARCHAR(50),
    PRIMARY KEY PKAthletePreviousSports(ApsID),
    FOREIGN KEY FKAthletePreviousSports(AthleteID) REFERENCES Athlete(AthleteID) # double check with team

);

CREATE TABLE `Interview` (
    InterviewID INTEGER AUTO_INCREMENT NOT NULL,
    AthleteID INTEGER NOT NULL,
    Questions VARCHAR(100),
    PRIMARY KEY PKInterview(InterviewID),
    FOREIGN KEY FKInterview(AthleteID) REFERENCES Athlete(AthleteID)
);

CREATE TABLE `Coach` (
    CoachID INTEGER AUTO_INCREMENT NOT NULL,
    Name VARCHAR(20),
    Username VARCHAR(20),
    Password VARCHAR(20),
    PRIMARY KEY PKCoach(CoachID)
);

CREATE TABLE `AthleteTest` (
   AthleteTestID INTEGER AUTO_INCREMENT NOT NULL,
   AthleteID INTEGER NOT NULL,
   CoachID INTEGER NOT NULL,
   DateOfTest DATE,
   AthleteComments VARCHAR(100),
   LegPress3Reps INTEGER NOT NULL,
   ArmPress3Reps INTEGER NOT NULL,
   ArmPull3Reps INTEGER NOT NULL,
   ArmPull15Reps INTEGER NOT NULL,
   Score INTEGER NOT NULL,
   Observations VARCHAR(150),
   PRIMARY KEY PKAthleteTest (AthleteTestID),
   FOREIGN KEY FKAthleteTest(AthleteID) REFERENCES Athlete(AthleteID),
   FOREIGN KEY FKCoachAthleteTest (CoachID) REFERENCES Coach(CoachID)
);


CREATE TABLE `MorningMonitoring` (
    MonitoringID INTEGER NOT NULL AUTO_INCREMENT,
    AthleteID INTEGER NOT NULL,
    Date DATE,
    WalkingHeartRate INTEGER NOT NULL,
    StandingHeartRate INTEGER NOT NULL,
    PerceivedShape INTEGER NOT NULL,
    PerceivedMentalState INTEGER NOT NULL,
    SleepQuantityHours INTEGER NOT NULL,
    sleepQuality INTEGER,
    PRIMARY KEY PKMorningMonitoring(MonitoringID),
    FOREIGN KEY FKMorningMonitoring(AthleteID) REFERENCES Athlete(AthleteID)
);

CREATE TABLE `SessionRPE` (
    SessionRPEID INTEGER NOT NULL AUTO_INCREMENT,
    AthleteID INTEGER NOT NULL,
    Date DATE,
    typeOfSession VARCHAR(50),
    RPE INTEGER,
    sessionDurationMinutes INTEGER,
    PRIMARY KEY PKSessionRPE(SessionRPEID),
    FOREIGN KEY FKSessionRPE(AthleteID) REFERENCES Athlete(AthleteID)
);

CREATE TABLE `CrossTraining` (
    CrossTrainingID INTEGER NOT NULL AUTO_INCREMENT,
    AthleteID INTEGER NOT NULL,
    Date DATE,
    typeOfCrossTraining VARCHAR(50),
    totalTimeMinutes INTEGER,
    totalDistance VARCHAR(20),
    PRIMARY KEY PKCrossTraining(CrossTrainingID),
    FOREIGN KEY FKCrossTraining(AthleteID) REFERENCES Athlete(AthleteID)
);
