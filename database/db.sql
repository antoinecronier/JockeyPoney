#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------
DROP DATABASE IF EXISTS jockeyponey;
CREATE DATABASE jockeyponey;

use jockeyponey

#------------------------------------------------------------
# Table: Jockey
#------------------------------------------------------------

CREATE TABLE Jockey(
        jockey_id Int NOT NULL ,
        jockey_firstname Varchar (25) ,
        jockey_lastname  Varchar (25) ,
        jockey_weight    Int ,
        PRIMARY KEY (jockey_id )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Poney
#------------------------------------------------------------

CREATE TABLE Poney(
        poney_id Int NOT NULL ,
        poney_name   Varchar (25) ,
        poney_weight Int ,
        PRIMARY KEY (poney_id )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Course
#------------------------------------------------------------

CREATE TABLE Course(
        course_id Int NOT NULL ,
        dateCourse Datetime ,
        PRIMARY KEY (course_id )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Courir
#------------------------------------------------------------

CREATE TABLE Courir(
        rank      Int ,
        id_Jockey Int NOT NULL ,
        id_Course Int NOT NULL ,
        id_Poney  Int NOT NULL ,
        PRIMARY KEY (id_Jockey ,id_Course ,id_Poney )
)ENGINE=InnoDB;

ALTER TABLE Courir ADD CONSTRAINT FK_Courir_id_Jockey FOREIGN KEY (id_Jockey) REFERENCES Jockey(jockey_id);
ALTER TABLE Courir ADD CONSTRAINT FK_Courir_id_Course FOREIGN KEY (id_Course) REFERENCES Course(course_id);
ALTER TABLE Courir ADD CONSTRAINT FK_Courir_id_Poney FOREIGN KEY (id_Poney) REFERENCES Poney(poney_id);
