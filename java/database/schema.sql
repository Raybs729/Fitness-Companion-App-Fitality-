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
    Photo varchar(10000000) NULL,

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
	VALUES  (1, 'Nhan',  'Dang', 'nick@gmail.com', '8327744222', 32, 71, 178, 'GET BIG', null ),
			(2, 'Ray',   'B',     'RayB@gmail.com', '8327744452', 3, 70, 168, 'GET Skinny', null ),
			(3, 'Hewad', 'Noori', 'HewadN@gmail.com', '8327744252', 53, 75, 188, 'GET taller', null);


INSERT INTO public.exercise(
	exercise_name)
	VALUES  ('bench'),
			('squat'),
			('deadlift'),
			('biking'),
			('jogging'),
	        ('flying'),
            ('Incline Bench'),
            ('Decline Bench'),
            ('Dumbbel Tricep'),
	        ('Lat pulldown');

INSERT INTO public.workout(
	user_id, start_time)
	VALUES  (1 , '2023-03-28 19:10:25-07' ),
			(1 , '2023-04-01 19:10:25-07' ),
			(1 , '2023-04-06 19:10:25-07' ),
            (1 , '2023-04-07 19:10:25-07' ),
            (1 , '2023-04-08 19:10:25-07' ),
            (1 , '2023-04-09 19:10:25-07' ),
            (1 , '2023-04-10 19:10:25-07' ),
            (1 , '2023-04-11 19:10:25-07' ),
            (1 , '2023-04-12 19:10:25-07' ),
            (1 , '2023-04-13 19:10:25-07' ),
            (1 , '2023-04-14 19:10:25-07' ),
			(1 , '2023-04-16 16:00:00'),
			(1 , '2023-04-18 21:00:00'),
            (1 , '2023-05-16 16:00:00'),
            (1 , '2023-05-17 21:00:00'),
            (1 , '2023-05-18 16:00:00'),
            (1 , '2023-05-19 16:00:00'),
            (1 , '2023-05-20 21:00:00'),
            (1 , '2023-05-22 21:00:00'),
            (2 , '2023-04-13 21:00:00'),
            (2 , '2023-05-03 21:00:00'),
			(2 , '2023-05-22 22:00:00'),
			(2 , '2023-05-23 15:00:00'),
			(2 , '2023-05-22 15:00:00'),
            (3 , '2023-04-05 20:50:00'),
			(3 , '2023-04-20 15:00:00');



INSERT INTO public.equipment(
	equipment_name, equipment_tutorial)
	VALUES  ('TREADMILL','www.youtube.com/watch?v=usScM1QZrQw'),
			('LIFECYCLE BIKE','www.youtube.com/watch?v=oAqA6byN9TI'),
			('SQUAT RACK ','www.youtube.com/watch?v=kaO7Yw1hlcM'),
			('FLAT BENCH','www.youtube.com/watch?v=oAqA6byN9TI'),
			('OLYMPIC PLATFORM', 'www.getswole.com'),
            ('DUAL ADJUSTABLE PULLEY' , 'www.youtube.com/watch?v=oAqA6byN9TI'),
            ('HAMMER STRENGTH', 'www.youtube.com/watch?v=oAqA6byN9TI');

INSERT INTO public.workout_exercise(
	workout_id, exercise_id, weight, set, rep, duration)
	VALUES (3001, 1001, 400, 5, 5, '10'),
		   (3001, 1002, 200, 2, 2, null),
		   (3001, 1003, 300, 3, null, '10:00'),
		   (3001, 1004, null, null, null, '120:00'),
           (3001, 1005, null, null, null, '60:00'),
           (3001, 1006, 400, 3, 3, null),
           (3001, 1007, 300, 3, null, '15:00'),
           (3001, 1008, 400, 3, 3, null),
           (3002, 1009, 300, 3, null, '10:00'),
           (3002, 1010, 400, 3, 3, null);



INSERT INTO public.equipment_exercise(
	exercise_id, equipment_id)
	VALUES  (1001, 4004),
			(1002, 4002),
			(1003, 4001),
			(1004, 4005),
			(1005, 4003),
            (1006, 4006),
            (1007, 4007),
            (1008, 4002),
            (1009, 4001);


INSERT INTO public.equipmentusagelog(
	user_id, equipment_id, equipment_usage_date_time)
	VALUES (1, 4004, '2023-03-31 19:10:25-07'),
	       (1, 4003, '2023-03-31 19:10:25-07'),
	       (1, 4002, '2023-03-31 19:20:25-07'),
	       (1, 4001, '2023-03-31 19:30:25-07'),
	       (1, 4005, '2023-03-31 19:40:25-07'),
	       (1, 4006, '2023-04-01 19:30:25-07'),
	       (1, 4007, '2023-04-01 19:40:25-07'),
	       (1, 4001, '2023-04-01 19:50:25-07'),
	       (1, 4002, '2023-04-01 19:10:25-07'),
           (1, 4003, '2023-03-31 19:10:25-07'),
	       (1, 4004, '2023-04-01 19:10:25-07'),
	       (1, 4005, '2023-04-01 19:10:25-07'),
	       (1, 4006, '2023-04-01 19:10:25-07'),
	       (1, 4007, '2023-04-01 19:10:25-07');

INSERT INTO public.gym_class(
    class_id, class_name, datestart, timestart, dateend, timeend, signedup)
VALUES
(2001,  'Yoga'     , '2023-04-13' , '12:16:49.84783' , '2023-04-13' , '14:16:49.84783' , 2),
(2002,  'Running'  , '2023-04-14' , '13:24:36.02789' , '2023-04-14' , '15:24:36.02789' , 3),
(2003,  'Walking'  , '2023-04-15' , '14:24:36.02789' , '2023-04-15' , '16:24:36.02789' , 4),
(2004,  'Dacing'   , '2023-04-16' , '15:24:36.02789' , '2023-04-16' , '17:24:36.02789' , 5),
(2005,  'Diet'     , '2023-04-17' , '16:24:36.02789' , '2023-04-17' , '18:24:36.02789' , 6),
(2006,  'Yoga2'    , '2023-04-20' , '19:24:36.02789' , '2023-04-20' , '21:24:36.02789' , 7),
(2007,  'Yoga3'    , '2023-04-23' , '08:24:36.02789' , '2023-05-21' , '10:24:36.02789' , 8),
(2008,  'Running2' , '2023-04-25' , '19:24:36.02789' , '2023-04-20' , '21:24:36.02789' , 7),
(2009,  'Running3' , '2023-04-26' , '08:24:36.02789' , '2023-05-21' , '10:24:36.02789' , 8),
(2010, 'Dacing2'  , '2023-05-10' , '19:24:36.02789' , '2023-04-20' , '21:24:36.02789' , 7),
(2011, 'Jumping3' , '2023-05-9'  , '08:24:36.02789' , '2023-05-21' , '10:24:36.02789' , 8),
(2012, 'Yoga3'    , '2023-05-13' , '12:16:49.84783' , '2023-04-13' , '14:16:49.84783' , 2),
(2013, 'Running4' , '2023-05-14' , '13:24:36.02789' , '2023-04-14' , '15:24:36.02789' , 3),
(2014, 'Dacing3'  , '2023-05-15' , '14:24:36.02789' , '2023-04-15' , '16:24:36.02789' , 4);

INSERT INTO public.workout_time(
    workout_id, workout_date, workout_duration)
VALUES
(3001,    '2023-03-31', '01:00:00'),
(3002,    '2023-04-01', '01:00:00'),
(3003,    '2023-04-05', '01:00:00'),
(3004,    '2023-04-12', '01:00:00'),
(3005,    '2023-04-12', '01:00:00'),
(3006,    '2023-05-20', '01:00:00'),
(3007,    '2023-05-22', '01:00:00'),
(3008,    '2023-05-23', '01:00:00'),
(3009,    '2023-05-22', '01:00:00'),
(3010,    '2023-05-20', '01:00:00');

END TRANSACTION ;
COMMIT TRANSACTION;



