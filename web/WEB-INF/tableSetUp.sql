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
  Username VARCHAR(64) not NULL ,
  Name VARCHAR(64) not NULL ,
  Email VARCHAR(100),
  Address VARCHAR(200),
  Education VARCHAR(200),
  Race VARCHAR(200),
  FOREIGN KEY (Username) REFERENCES UsersNames (UserName)
);


CREATE TABLE IF NOT EXISTS Articles(
  ArticlesID int not NULL,
  ArticlesName VARCHAR(64) not NULL ,
  UserIDName VARCHAR(64) not NULL ,
  Content TEXT NULL,
  PRIMARY KEY (ArticlesID),
  FOREIGN KEY (UserIDName) REFERENCES UsersNames (Username)
);

CREATE TABLE IF NOT EXISTS Comments(
  ArticlesID int not NULL,
  CommenterName VARCHAR(64) not NULL ,
  Comments VARCHAR(64) not NULL ,
  FOREIGN KEY (ArticlesID) REFERENCES Articles (ArticlesID),
  FOREIGN KEY (CommenterName) REFERENCES UsersNames  (Username)
);
