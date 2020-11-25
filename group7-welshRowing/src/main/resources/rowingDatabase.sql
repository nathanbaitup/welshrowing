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
    PRIMARY KEY Athlete(AthleteID),
    FOREIGN KEY Athlete(`CoachID`) REFERENCES Athlete(`AthleteID`)
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
    PRIMARY KEY MedicalData(MedicalDataID),
    FOREIGN KEY MedicalData(AthleteID) REFERENCES Athlete(AthleteID)
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
    PRIMARY KEY AthletePreviousSports(ApsID),
    FOREIGN KEY AthletePreviousSports(AthleteID) REFERENCES Athlete(AthleteID) # double check with team

);

CREATE TABLE `Interview` (
    InterviewID INTEGER AUTO_INCREMENT NOT NULL,
    AthleteID INTEGER NOT NULL,
    Questions VARCHAR(100),
    PRIMARY KEY Interview(InterviewID),
    FOREIGN KEY Interview(AthleteID) REFERENCES Athlete(AthleteID)
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
    PRIMARY KEY AthleteTest(AthleteTestID),
    FOREIGN KEY AthleteTest(AthleteID) REFERENCES Athlete(AthleteID)
    ## Coach ID line does not work

);

CREATE TABLE `Coach` (
    CoachID INTEGER AUTO_INCREMENT NOT NULL,
    Name VARCHAR(16), ## First and Last
    Username VARCHAR(20),
    Password VARCHAR(20),
    PRIMARY KEY Coach(CoachID)
);

CREATE TABLE `MorningMonitoring` (
    MonitoringID INTEGER NOT NULL AUTO_INCREMENT,
    AthleteID INTEGER NOT NULL,
    Date DATE,
    PRIMARY KEY MorningMonitoring(MonitoringID),
    FOREIGN KEY MorningMonitoring(AthleteID) REFERENCES Athlete(AthleteID)
);

CREATE TABLE `SessionRPE` (
    SessionRPEID INTEGER NOT NULL AUTO_INCREMENT,
    AthleteID INTEGER NOT NULL,
    Date DATE,
    PRIMARY KEY SessionRPE(SessionRPEID),
    FOREIGN KEY SessionRPE(AthleteID) REFERENCES Athlete(AthleteID)
);

CREATE TABLE `CrossTraining` (
    CrossTrainingID INTEGER NOT NULL AUTO_INCREMENT,
    AthleteID INTEGER NOT NULL,
    Date DATE,
    PRIMARY KEY CrossTraining(CrossTrainingID),
    FOREIGN KEY CrossTraining(AthleteID) REFERENCES Athlete(AthleteID)
);
