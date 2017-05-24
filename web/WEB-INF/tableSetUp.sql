DROP TABLE IF EXISTS Comments;
DROP TABLE IF EXISTS usersProfile;
DROP TABLE IF EXISTS Articles;
DROP TABLE IF EXISTS UsersNames;

CREATE TABLE IF NOT EXISTS UsersNames(
  Username VARCHAR(64) not NULL ,
  Password VARCHAR(100) not NULL,
  PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS usersProfile(
  UserID int NOT NULL AUTO_INCREMENT,
  Username VARCHAR(64) not NULL ,
  Name VARCHAR(64) not NULL ,
  Email VARCHAR(100),
  Address VARCHAR(200),
  Education VARCHAR(200),
  Race VARCHAR(200),
  PRIMARY KEY (UserID),
  FOREIGN KEY (Username) REFERENCES UsersNames (UserName)
);
-- Needed to add UserID for selections . Needed to be done not yet


CREATE TABLE IF NOT EXISTS Articles(
  ArticlesID int not NULL AUTO_INCREMENT,
  ArticlesName VARCHAR(64) not NULL ,
  UserIDName VARCHAR(64) not NULL ,
  Content TEXT NULL,
  SpecificDateCreated DATE not NULL ,
  PRIMARY KEY (ArticlesID),
  FOREIGN KEY (UserIDName) REFERENCES UsersNames (Username)
);

CREATE TABLE IF NOT EXISTS Comments(
  CommentID int not NULL AUTO_INCREMENT,
  ArticlesID int not NULL,
  CommenterName VARCHAR(64) not NULL ,
  Comments VARCHAR(64) not NULL ,
  PRIMARY KEY (CommentID),
  FOREIGN KEY (ArticlesID) REFERENCES Articles (ArticlesID),
  FOREIGN KEY (CommenterName) REFERENCES UsersNames  (Username)
);
