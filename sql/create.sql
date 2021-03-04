drop database HorseRacingManagement;
create database HorseRacingManagement;

use HorseRacingManagement1;

create table Owner_Info(Ownerid varchar(30) not null primary key,Owner_Name varchar(30) not null,Owner_Contact varchar(30) not null);
create table Sponsor_Info(Sponsorid varchar(10) not null primary key,Sponsor_name varchar(20) not null,Sponsor_contact varchar(30) not null);
create table Horse_Info(Age smallint(6) not null,Weight smallint(6) not null,
  No_of_wins smallint(6) not null,No_of_races smallint(6) not null,
  Best_time  varchar(10) not null,Worst_time varchar(10) not null,
  Sponsorid varchar(10),
    Ownerid varchar(30),
  Horseid varchar(10) not null primary key,
  constraint fk_Owner foreign key(Ownerid) references Owner_Info(Ownerid),
  CONSTRAINT FK_Sponsor FOREIGN KEY (Sponsorid) REFERENCES Sponsor_Info(Sponsorid)
);
create table Race_Info(
  Raceid varchar(10) not null primary key,Track_Name varchar(30) not null,
  Track_Distance varchar(30) not null,Time varchar(10) not null,
  Position varchar(15) not null, Competition_Name varchar(15) not null,
  Horseid varchar(10), CONSTRAINT FK_Horse FOREIGN KEY (Horseid)
    REFERENCES Horse_Info(Horseid)
);
