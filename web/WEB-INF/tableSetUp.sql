DROP TABLE IF EXISTS Comments;
DROP TABLE IF EXISTS Articles;
DROP TABLE IF EXISTS UsersNames;
Drop TABLE If EXISTS youtube;
Drop TABLE If EXISTS Friendlist;

CREATE TABLE IF NOT EXISTS UsersNames(
  Username VARCHAR(64) not NULL ,
  Password VARCHAR(100) not NULL,
  Name VARCHAR(64) ,
  Email VARCHAR(100),
  Address VARCHAR(200),
  Education VARCHAR(200),
  Ethnicity VARCHAR(200),
  DateOfBirth DATE ,
  PRIMARY KEY ( Username)
);

-- CREATE TABLE IF NOT EXISTS usersProfile(
--   UserID int NOT NULL AUTO_INCREMENT,
--   Username VARCHAR(64) not NULL ,
--   Name VARCHAR(64) not NULL ,
--   Email VARCHAR(100),
--   Address VARCHAR(200),
--   Education VARCHAR(200),
--   Ethnicity VARCHAR(200),
--   DateOfBirth DATE ,
--   PRIMARY KEY (UserID),
--   FOREIGN KEY (Username) REFERENCES UsersNames (UserName)
-- );
-- Needed to add UserID for selections . Needed to be done not yet


CREATE TABLE IF NOT EXISTS Articles(
  ArticlesID int not NULL AUTO_INCREMENT,
  ArticlesName VARCHAR(64) not NULL ,
  UserIDName VARCHAR(64) not NULL ,
  Content TEXT NULL,
  SpecificDateCreated TIMESTAMP not NULL ,
  PRIMARY KEY (ArticlesID),
  FOREIGN KEY (UserIDName) REFERENCES UsersNames (Username)
);



CREATE TABLE IF NOT EXISTS Comments(
  CommentID int not NULL AUTO_INCREMENT,
  ArticlesID int not NULL,
  CommenterName VARCHAR(64) not NULL ,
  Comments VARCHAR(64) not NULL ,
  CommentTime TIMESTAMP NOT NULL ,
  PRIMARY KEY (CommentID),
  FOREIGN KEY (ArticlesID) REFERENCES Articles (ArticlesID),
  FOREIGN KEY (CommenterName) REFERENCES UsersNames  (Username)
);

-- CREATE TABLE if not Exists youtube(
-- ArticlesId int not NULL ,
-- youtubeURL VARCHAR (1000) not NULL ,
-- FOREIGN KEY (ArticlesID) REFERENCES Articles (ArticlesID)
-- )
CREATE TABLE if not Exists Friendlist(
username VARCHAR(64) not NULL ,
friendusername VARCHAR (64) not NULL ,
FOREIGN KEY (username) REFERENCES UsersNames (Username),
FOREIGN KEY (friendusername) REFERENCES UsersNames (Username)
);

ALTER TABLE table_name
ADD column_name datatype;


