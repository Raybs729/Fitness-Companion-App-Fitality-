BEGIN TRANSACTION;

DROP TABLE IF EXISTS  users, Account, Exercise, Gym_Class, GymClassRegistrationRecord,   Workout, Workout_time, Workout_Exercise, Muscle_group,  Muscle_group_exercise , Equipment, Equipment_Exercise, EquipmentUsageLog CASCADE ;


CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE SEQUENCE seq_exercise_id
    INCREMENT BY 1
    START WITH 1001
    NO MAXVALUE;

CREATE TABLE Exercise (
	Exercise_id int NOT NULL DEFAULT nextval('seq_exercise_id'),
	Exercise_name varchar (100) NOT NULL UNIQUE,
	CONSTRAINT PK_Exercise PRIMARY KEY (Exercise_id)
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

CREATE SEQUENCE seq_class_id
    INCREMENT BY 1
    START WITH 2001
    NO MAXVALUE;

CREATE TABLE Gym_Class (
	Class_id int NOT NULL DEFAULT nextval('seq_class_id'),
	Class_name varchar(50),
	DateStart date NOT NULL,
	TimeStart time NOT NULL,
	DateEnd date NOT NULL,
	TimeEnd time NOT NULL,
	SignedUp int NULL,
	CONSTRAINT PK_Gym_Class PRIMARY KEY (Class_id)
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

CREATE SEQUENCE seq_workout_id
    INCREMENT BY 1
    START WITH 3001
    NO MAXVALUE;

CREATE TABLE Workout (
    Workout_id int NOT NULL DEFAULT nextval('seq_workout_id'),
    User_id int NOT NULL,
    Start_time timestamp NULL,
	CONSTRAINT PK_Workout PRIMARY KEY (Workout_id),
    CONSTRAINT FK_Workout FOREIGN KEY (User_id) REFERENCES Users (User_id)
);

CREATE TABLE Workout_Time (
	Workout_id int NOT NULL,
	workout_date date NULL,
	workout_duration time NULL,

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

CREATE SEQUENCE seq_equipment_id
    INCREMENT BY 1
    START WITH 4001
    NO MAXVALUE;


CREATE TABLE Equipment(
    Equipment_id int NOT NULL DEFAULT nextval('seq_equipment_id'),
    Equipment_name varchar (200) NOT NULL,
	Equipment_tutorial varchar (100) NOT NULL,
    CONSTRAINT PK_Equipment PRIMARY KEY (Equipment_id),
    CONSTRAINT UQ_Equipment_tutorial UNIQUE (Equipment_name)
);

CREATE TABLE Equipment_Exercise (
	Exercise_id int NOT NULL,
	Equipment_id int NOT NULL,
	CONSTRAINT PK_Equipment_Exercise PRIMARY KEY (Exercise_id, Equipment_id),
	CONSTRAINT FK_Equipment_Exercise_Exercise FOREIGN KEY (Exercise_id) REFERENCES Exercise (Exercise_id),
	CONSTRAINT FK_Equipment_Exercise_Equipment FOREIGN KEY (Equipment_id) REFERENCES Equipment (Equipment_id)
);

CREATE SEQUENCE seq_log_id
    INCREMENT BY 1
    START WITH 6001
    NO MAXVALUE;

CREATE TABLE EquipmentUsageLog (
	log_id int NOT NULL DEFAULT nextval('seq_log_id'),
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
	exercise_name)
	VALUES ('bench'),
			('squat'),
			('deadlift'),
			('biking'),
			('jogging');

INSERT INTO public.workout(
	user_id, start_time)
	VALUES (1 , '2023-03-31 19:10:25-07' ),
			(1 , '2023-04-01 19:10:25-07' ),
			(1 , '2023-04-05 19:10:25-07' ),
			(2, '2023-03-12 00:00:00'),
			(2, '2023-04-12 00:00:00'),
			(3, '2023-04-20 00:00:00'),
			(2, '2023-04-22 00:00:00'),
			(2, '2023-05-23 00:00:00'),
			(2, '2023-05-22 00:00:00'),
			(3, '2023-04-20 00:00:00')



INSERT INTO public.equipment(
	equipment_name, equipment_tutorial)
	VALUES ('treadmill','www.youtube.com/watch?v=usScM1QZrQw'),
			('lifecycle bike','www.youtube.com/watch?v=oAqA6byN9TI'),
			('squat rack','www.youtube.com/watch?v=kaO7Yw1hlcM'),
			('flat bench','www.youtube.com/watch?v=oAqA6byN9TI'),
			('olympic platform', 'www.getswole.com');

INSERT INTO public.workout_exercise(
	workout_id, exercise_id, weight, set, rep, duration)
	VALUES (3001, 1001, 400, 5, 5, '10'),
		   (3001, 1004, 200,2, 2, null),
		   (3002, 1001, 300,3, null, '100:00'),
		   (3002, 1004, 400,3, 3, null),
		   (3003, 1001, 500, 7 , 7, null);


INSERT INTO public.equipment_exercise(
	exercise_id, equipment_id)
	VALUES (1001, 4004),
			(1004, 4002),
			(1005, 4001),
			(1003, 4005),
			(1002, 4003);


INSERT INTO public.equipmentusagelog(
	user_id, equipment_id, equipment_usage_date_time)
	VALUES (1, 4004, '2023-03-31 19:10:25-07'),
	(1, 4003, '2023-03-31 19:10:25-07'),
	(1, 4002, '2023-03-31 19:10:25-07'),
	(1, 4001, '2023-03-31 19:10:25-07'),
	(1, 4005, '2023-03-31 19:10:25-07'),
	(1, 4003, '2023-04-01 19:10:25-07'),
	(1, 4002, '2023-04-01 19:10:25-07'),
	(1, 4001, '2023-04-01 19:10:25-07'),
	(1, 4005, '2023-04-01 19:10:25-07');

INSERT INTO public.gym_class(
    class_id, class_name, datestart, timestart, dateend, timeend, signedup)
VALUES
(1,'Yoga'  , '2023-04-13' , '12:16:49.84783' , '2023-04-13' , '14:16:49.84783' , 2),
(2,'Yoga1' , '2023-04-14' , '13:24:36.02789' , '2023-04-14' , '15:24:36.02789' , 3),
(3,'Yoga2' , '2023-04-15' , '14:24:36.02789' , '2023-04-15' , '16:24:36.02789' , 4),
(4,'Yoga3' , '2023-04-16' , '15:24:36.02789' , '2023-04-16' , '17:24:36.02789' , 5),
(5,'Yoga4' , '2023-04-17' , '16:24:36.02789' , '2023-04-17' , '18:24:36.02789' , 6),
(6,'Yoga5' , '2023-04-20' , '19:24:36.02789' , '2023-04-20' , '21:24:36.02789' , 7),
(7,'Yoga6' , '2023-05-21' , '08:24:36.02789' , '2023-05-21' , '10:24:36.02789' , 8);

INSERT INTO public.workout_time(
    workout_id, workout_date, duration)
VALUES
(3001,    '2023-03-31', '01:00:00'),
(3002,    '2023-04-01', '01:00:00'),
(3003,    '2023-04-05', '01:00:00'),
(3004,    '2023-04-12', '01:00:00'),
(3005,    '2023-04-12', '01:00:00'),
(3006,    '2023-05-20', '01:00:00'),
(3007,    '2023-05-22', '01:00:00'),
(3008,    '2023-05-23', '01:00:00'),
(3009,   '2023-05-22', '01:00:00'),
(3010, '2023-05-20', '01:00:00'),
COMMIT TRANSACTION;



