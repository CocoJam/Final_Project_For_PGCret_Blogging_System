DROP TABLE IF EXISTS Comments;
DROP TABLE IF EXISTS usersProfile;
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


CREATE TABLE IF NOT EXISTS Articles(
  ArticlesID int not NULL AUTO_INCREMENT,
  ArticlesName VARCHAR(64) not NULL ,
  UserIDName VARCHAR(64) not NULL ,
  Content TEXT NULL,
  SpecificDateCreated TIMESTAMP not NULL ,
  PRIMARY KEY (ArticlesID),
  FOREIGN KEY (UserIDName) REFERENCES UsersNames (Username)
);

-- Date change to TIMESTAMP;
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
-- added the comments TIMESTAMP;
INSERT INTO Articles (ArticlesName, UserIDName, Content) VALUES('blah', 'ljam' ,'content content content'),
('blahasdasfsdfsdf', 'ljam' ,'sdfgdsfhhhgdfhfhgfjfgjghjfgj' )
INSERT INTO Comments (ArticlesID, CommenterName, Comments) VALUES('4', 'ljam763',' shit that is bad articles')
