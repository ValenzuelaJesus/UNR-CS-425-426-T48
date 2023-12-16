CREATE DATABASE CS_425_Database_Prototype;


/*
CREATE TABLE GEOCODING(
    latitude NUMERIC(3,5),
    longitude NUMERIC(3,5),
    gcode VARCHAR(255) PRIMARY KEY
);*/

CREATE TABLE BUILDING( 
    latitude NUMERIC(3,5)
    longitude NUMERIC(3,5)
    building_code VARCHAR(10) UNIQUE,
    building_name VARCHAR(255),
    building_num VARCHAR(10),
    department VARCHAR(255),
    operating_hours VARCHAR(255), --change to varchar Format M-F: Start - End, Sa: Start End, Su: Start End
    PRIMARY KEY(building_code)
    --PRIMARY KEY (gcode, building_code)
);


CREATE TABLE LIBRARY(
    building_code VARCHAR(10) references Building(building_code),
    location VARCHAR(255),
    description VARCHAR(255)
);

CREATE TABLE HANGOUTSPOTS( --add outlet bool 
    building_code VARCHAR(10) references Building(building_code),
    location VARCHAR(255),
    description VARCHAR(255),
    outlet boolean
);

CREATE TABLE RESTROOM(
    building_code VARCHAR(10) references BUILDING(building_code),
    location VARCHAR(255),
    accessibility boolean
);

CREATE TABLE VENDINGMAC(
    building_code VARCHAR(10) references BUILDING(building_code),
    location VARCHAR(255),
    type VARCHAR (20)
);

CREATE TABLE RESOURCE(
    building_code VARCHAR(10) references BUILDING(building_code),
    description VARCHAR(255),
    location VARCHAR(255),
    operating_hours VARCHAR(50),
    weblink VARCHAR(255) 
);

CREATE TABLE STORE(
    building_code VARCHAR(10) references BUILDING(building_code),
    description VARCHAR(255),
    location VARCHAR(255),
    operating_hours VARCHAR(50)
);

CREATE TABLE DINING_OPTION(
    building_code VARCHAR(10) references BUILDING(building_code),
    description VARCHAR(255),
    location VARCHAR(255),
    operating_hours VARCHAR(50),
    weblink VARCHAR(255),
    menulinkk VARCHAR(255)
);

CREATE TABLE LAB(
    building_code VARCHAR(10) references BUILDING(building_code),
    location VARCHAR(255),
    type VARCHAR (20),
    department VARCHAR(255)
);

CREATE TABLE ELEVATOR(
    building_code VARCHAR(10) references BUILDING(building_code),
    location VARCHAR(255)
);

CREATE TABLE STAIRCASE(
    building_code VARCHAR(10) references BUILDING(building_code),
    location VARCHAR(255)
);

CREATE TABLE SPECIAL_FEATURE(
    building_code VARCHAR(10) references BUILDING(building_code),
    location VARCHAR(255),
    description VARCHAR(255)
);


--labs, elevators, staircases
--special features

--39.540041518463504, -119.81186738652406 <- Coordinates of the WNPUB
INSERT INTO building VALUES ('WPEB','William N. Pennington Engineering Building','53', 'ENGINEERING', 'M-Th: 7AM-12AM , F: 7AM - 5:30PM , Sa:12PM-5PM, Su:12PM-12AM');
--Values Missing = NULL

--Add example to database
--restroom
--restroom firstfloor wheel chair accessible?
--Second floor has one near the outdoor entrance
--Third floor has one infront of the staircase/ elevator near a window
--Second floor engineering 300 has standalone accessible restrooms
INSERT INTO RESTROOM VALUES ('WPEB', 'Floor 1: Near Computer Lab #100', FALSE);
INSERT INTO RESTROOM VALUES ('WPEB', 'Floor 2: Near Entrance South, CSE Room 200', FALSE);
INSERT INTO RESTROOM VALUES ('WPEB', 'Floor 2: Opposite Entrance North, Engineering Education Lab',TRUE);
INSERT INTO RESTROOM VALUES ('WPEB', 'Floor 3: Infront of South Corridor Elevator and Staircase', FALSE);

--Dining
--bowlife on the first floor
INSERT INTO DINING_OPTION VALUES ('WPEB', 'Bowl Life', 'First Floor: South Cooridor past the Computer Lab #100', 'M-F: 11AM-3AM');

--resources
--resource Vihn and Sarah? have offices 
--Dean of engineering
--clean lab first floor
--breakout rooms
INSERT INTO RESOURCE VALUES ('WPEB', 'Professor Vihn Office','Room 205. Located on second floor south corridor.', '', '' );
INSERT INTO RESOURCE VALUES ('WPEB', 'Professor Sarah Office','Room 315. Located on third floor south corridor.', '', '' );
INSERT INTO RESOURCE VALUES ('WPEB', 'College of Engineering Dean Suite','Rooms 441-461 Located on fourth floor','','');
INSERT INTO RESOURCE VALUES ('WPEB', 'Collaboration Rooms','Rooms 204,214,314,320,326,340-342, 410, 418, 424, 438','','');

--Hangout spots
--Near bowlife
--In front of the computer labs seating, both floors
--second floor in front of the computer lab
INSERT INTO HANGOUTSPOTS VALUES ('WPEB', 'First Floor & Second Floor. Infront of 100,200', 'Sofas and Tables are offered for students to use.', TRUE);

--LAB
INSERT INTO LAB VALUES ('WPEB', 'First Floor Computer Lab First Floor Room 100 and 101','Computer','Computer Science');
INSERT INTO LAB VALUES ('WPEB', 'First Floor Clean Room. Past BOWL LIFE. Room 104','Clean Room','Variable');

--Elevator
INSERT INTO ELEVATOR VALUES ('WPEB','South Corridor');
INSERT INTO ELEVATOR VALUES ('WPEB','North Corridor');

--Staircase
INSERT INTO STAIRCASE VALUES ('WPEB', 'Near Elevators');
INSERT INTO STAIRCASE VALUES ('WPEB', 'Far South of the building');


--Queries Example
--See all UNR buildigns
SELECT * FROM Building;
--See the hours of a building
SELECT operating_hours FROM BUILDING WHERE building_code = 'WPEB';
--Find all resources of a building 
SELECT * FROM RESOURCE WHERE building_code = 'WPEB';

--Find Restrooms
SELECT * FROM RESTROOM WHERE building_code = 'WPEB';

--Find Accessible Restroom in a particular building
SELECT * FROM RESTROOM WHERE building_code='WPEB' and accessibility = TRUE;

--Find the locations of all elevators & stairs in a building
SELECT * FROM ELEVATOR,STAIRCASE  WHERE elevator.building_code='WPEB' or staircase.building_code = 'WPEB';

--Find the labs of a bulding
SELECT * FROM LAB WHERE building_code = 'WPEB';
--Find all computer labs
SELECT * FROM LAB WHERE type ='Computer'; 

--Find dining options
SELECT * FROM DINING_OPTION;

--Find the operation hours for a particular DINING_OPTION
SELECT operating_hours FROM DINING_OPTION WHERE description = 'Bowl Life';