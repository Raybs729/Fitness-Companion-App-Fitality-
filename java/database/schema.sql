BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, Account, Exercise, Gym_Class, GymClassRegistrationRecord,   Workout,  Workout_Exercise, Muscle_group,  Muscle_group_exercise , Equipment, Equipment_Exercise CASCADE ;


CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE Exercise (
	Exercise_id serial,
	Exercise_name varchar (100) NOT NULL,
	CONSTRAINT PK_Exercise PRIMARY KEY (Exercise_id)
);

CREATE TABLE Gym_Class (
	Class_id serial,
	Class_name varchar(50),
	DateTimeStart timestamp NOT NULL,
	DateTimeEnd timestamp NOT NULL,
	SignedUp int NULL,
	CONSTRAINT PK_Gym_Class PRIMARY KEY (Class_id)
);

CREATE TABLE Account (
    User_id int NOT NULL,
    First_name varchar (20) NOT NULL,
    Last_name varchar (20) NOT NULL,
    Email varchar (40) NOT NULL,
    Phone varchar(12),
    Age int NULL,
    Height int  NULL,
    Weight int  NULL,
    Goals varchar(1000) NULL,
    Photo varchar(100) NULL,

    CONSTRAINT FK_Account_users FOREIGN KEY (User_id) REFERENCES users (user_id)  
);

CREATE TABLE GymClassRegistrationRecord (
	User_id int NOT NULL,
	Class_id int NOT NULL,
	CONSTRAINT FK_GymClassRegistrationRecord_Users FOREIGN KEY (User_id)  REFERENCES Users (User_id),
	CONSTRAINT FK_GymClassRegistrationRecord_Gym_Class FOREIGN KEY (Class_id) REFERENCES Gym_Class (Class_id)
);

CREATE TABLE Muscle_Group (
    Muscle_group_id serial,
    Muscle_group_name varchar (200) NOT NULL,
    CONSTRAINT PK_Muscle_Group PRIMARY KEY (Muscle_group_id)
);

CREATE TABLE Muscle_Group_Exercise (
	Muscle_group_id int NOT NULL,
	Exercise_id int NOT NULL,
	CONSTRAINT PK_Muscle_Group_Exercise PRIMARY KEY (Muscle_group_id, Exercise_id),
	CONSTRAINT FK_Muscle_Group_Exercise_Muscle_Group FOREIGN KEY (Muscle_group_id) REFERENCES Muscle_Group (Muscle_Group_id),
	CONSTRAINT FK_Muscle_Group_Exercise FOREIGN KEY (Exercise_id) REFERENCES Exercise (Exercise_id)
);


CREATE TABLE Workout (
    Workout_id Serial,
    User_id int NOT NULL,
    Start_time timestamp NULL,
	CONSTRAINT PK_Workout PRIMARY KEY (Workout_id),
    CONSTRAINT FK_Workout FOREIGN KEY (User_id) REFERENCES Users (User_id)
);


CREATE TABLE Workout_Exercise (
    Workout_id int NOT NULL,
    Exercise_id int NOT NULL,
	Weight int NULL,
    Set int NULL,
    Rep int NULL,
	Duration varchar(6) NULL,
    CONSTRAINT PK_Workout_Exercise PRIMARY KEY (Workout_id, Exercise_id),
    CONSTRAINT FK_Workout_Exercise_Exercise FOREIGN KEY (Exercise_id) REFERENCES Exercise (Exercise_id),
	CONSTRAINT FK_Workout_Exercise_Workout FOREIGN KEY (Workout_id) REFERENCES Workout (Workout_id)
);



CREATE TABLE Equipment(
    Equipment_id serial,
    Equipment_name varchar (200) NOT NULL,
	Equipment_tutorial varchar (100) NOT NULL,
    Barcode varchar (100) NOT NULL,
    Equipment_usage_date_time TIMESTAMP,
    CONSTRAINT PK_Equipment PRIMARY KEY (Equipment_id),
    CONSTRAINT UQ_Equipment_tutorial UNIQUE (Equipment_name),
    CONSTRAINT UQ_Equipment_barcode UNIQUE (Barcode)
);

CREATE TABLE Equipment_Exercise (
	Exercise_id int NOT NULL,
	Equipment_id int NOT NULL,
	CONSTRAINT PK_Equipment_Exercise PRIMARY KEY (Exercise_id, Equipment_id),
	CONSTRAINT FK_Equipment_Exercise_Exercise FOREIGN KEY (Exercise_id) REFERENCES Exercise (Exercise_id),
	CONSTRAINT FK_Equipment_Exercise_Equipment FOREIGN KEY (Equipment_id) REFERENCES Equipment (Equipment_id)
);

COMMIT;

END TRANSACTION;


COMMIT TRANSACTION;

