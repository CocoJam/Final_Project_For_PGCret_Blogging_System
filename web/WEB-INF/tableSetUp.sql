DROP TABLE IF EXISTS Comments;
DROP TABLE IF EXISTS Articles;
DROP TABLE IF EXISTS UsersNames;

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


CREATE TABLE IF NOT EXISTS Article.Articles(
  ArticlesID int not NULL AUTO_INCREMENT,
  ArticlesName VARCHAR(64) not NULL ,
  UserIDName VARCHAR(64) not NULL ,
  Content TEXT NULL,
  SpecificDateCreated TIMESTAMP not NULL ,
  PRIMARY KEY (ArticlesID),
  FOREIGN KEY (UserIDName) REFERENCES UsersNames (Username)
);


CREATE TABLE IF NOT EXISTS Comment.Comments(
  CommentID int not NULL AUTO_INCREMENT,
  ArticlesID int not NULL,
  CommenterName VARCHAR(64) not NULL ,
  Comment.Comments VARCHAR(64) not NULL ,
  CommentTime TIMESTAMP NOT NULL ,
  PRIMARY KEY (CommentID),
  FOREIGN KEY (ArticlesID) REFERENCES Article.Articles (ArticlesID),
  FOREIGN KEY (CommenterName) REFERENCES UsersNames  (Username)
);

