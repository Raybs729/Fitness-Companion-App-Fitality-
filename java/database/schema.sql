BEGIN TRANSACTION;

DROP TABLE IF EXISTS  users, Account, Exercise, Gym_Class, GymClassRegistrationRecord,   Workout, Workout_time, Workout_Exercise, Muscle_group,  Muscle_group_exercise , Equipment, Equipment_Exercise, EquipmentUsageLog CASCADE ;


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
    First_name varchar (20) NULL,
    Last_name varchar (20) NULL,
    Email varchar (40) NULL UNIQUE,
    Phone varchar(12),
    Age int NULL,
    Height int  NULL,
    Weight int  NULL,
    Goals varchar(1000) NULL,
    Photo varchar(1000) NULL,

    CONSTRAINT PK_Account_users PRIMARY KEY (user_id),
    CONSTRAINT FK_Account_users FOREIGN KEY (user_id) REFERENCES users (user_id)
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

CREATE TABLE Workout_Time (
	Workout_id int NOT NULL,
	Workout_start_time time NULL,
	Workout_end_time time NULL,

	CONSTRAINT PK_Workout_Time PRIMARY KEY (Workout_id),
	CONSTRAINT FK_Workout_Time_Workout FOREIGN KEY (Workout_id) REFERENCES Workout (Workout_id)
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

CREATE TABLE EquipmentUsageLog (
	log_id serial,
    User_id int NOT NULL,
    Equipment_id int NOT NULL,
    Equipment_usage_date_time TIMESTAMP,
    CONSTRAINT PK_EquipmentUsageLog PRIMARY KEY (log_id),
    CONSTRAINT FK_EquipmentUsageLog_user FOREIGN KEY (User_id) REFERENCES Users (User_id),
    CONSTRAINT FK_EquipmentUsageLog_equipment FOREIGN KEY (Equipment_id) REFERENCES Equipment (Equipment_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('admin1','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO public.account(
	user_id, first_name, last_name, email, phone, age, height, weight, goals, photo)
	VALUES  (1, 'Nhan',  'Dang', 'nick@gmail.com', '8327744222', 32, 71, 178, 'GET BIG', 'http://welcometomyPic'),
			(2, 'Ray',   'B',     'RayB@gmail.com', '8327744452', 3, 70, 168, 'GET Skinny', 'http://welcometomyPic123'),
			(3, 'Hewad', 'Noori', 'HewadN@gmail.com', '8327744252', 53, 75, 188, 'GET taller', 'http://welcometomyPic123223');


INSERT INTO public.exercise(
	exercise_id, exercise_name)
	VALUES (1001, 'Bench'),
			(1002, 'Squat'),
			(1003, 'Dealift'),
			(1004, 'Biking'),
			(1005, 'Jogging');

INSERT INTO public.workout(
	workout_id, user_id, start_time)
	VALUES (3001, 1 , '2016-06-22 19:10:25-07' ),
			(3002, 1 , '2016-07-22 19:10:25-07' ),
			(3003, 1 , '2016-05-22 19:10:25-07' );

INSERT INTO public.equipment(
	equipment_id, equipment_name, equipment_tutorial, barcode)
	VALUES (1, 'TreadMill','www.youtube.com/watch?v=usScM1QZrQw' , 'barcode1'),
			(2, 'LifeCycle Bike','www.youtube.com/watch?v=oAqA6byN9TI' , 'barcode2'),
			(3, 'Climber','www.youtube.com/watch?v=oAqA6byN9TI' , 'barcode3'),
			(4, 'Bench','www.youtube.com/watch?v=oAqA6byN9TI' , 'barcode4');

INSERT INTO public.workout_exercise(
	workout_id, exercise_id, weight, set, rep, duration)
	VALUES (3001, 1001, 400, 5, 5, '10'),
		   (3001, 1004, 200,2, 2, null),
		   (3002, 1001, 300,3, null, '100:00'),
		   (3002, 1004, 400,3, 3, null),
		   (3003, 1001, 500, 7 , 7, null);


INSERT INTO public.equipment_exercise(
	exercise_id, equipment_id)
	VALUES (1001, 4),
			(1004, 2),
			(1005,1);

GRANT INSERT, SELECT, UPDATE on public.account TO public

COMMIT;

END TRANSACTION;

COMMIT TRANSACTION;

