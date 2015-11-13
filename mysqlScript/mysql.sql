use test;
drop table if exists Tokens;
	CREATE  TABLE Tokens (
    email VARCHAR(200) NOT NULL ,
    token VARCHAR(200) NOT NULL ,
    PRIMARY KEY (email)
    );

drop table if exists Users;		
CREATE  TABLE Users (
    userid VARCHAR(200) NOT NULL ,
    name VARCHAR(100) NOT NULL ,
    email VARCHAR(100) NOT NULL ,
    password VARCHAR(100) NOT NULL ,
    role VARCHAR(200) NOT NULL ,
    PRIMARY KEY (email)
    );

drop table if exists Tweets;
CREATE  TABLE Tweets (
    tweetid VARCHAR(200) NOT NULL ,
    userid VARCHAR(200) NOT NULL ,
    message VARCHAR(500) NOT NULL ,
    PRIMARY KEY (tweetid)
    );

drop table if exists Followers;
CREATE  TABLE Followers (
    userid VARCHAR(200) NOT NULL ,
    followerid VARCHAR(200) NOT NULL ,
    PRIMARY KEY (userid, followerid)
    );
	