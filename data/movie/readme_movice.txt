
1/数据下载地址	http://grouplens.org/datasets/movielens/

movies.dat的文件描述是 电影编号::电影名::电影类别
ratings.dat的文件描述是 用户编号::电影编号::电影评分::时间戳
users.dat的文件描述是 用户编号::性别::年龄::职业::Zip-code

2/数据库准备

CREATE DATABASE IF NOT EXISTS movie DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
USE movie;   
// 保存电影相关的信息。  
CREATE TABLE movie (  
    id INTEGER NOT NULL AUTO_INCREMENT,   
    name varchar(100) NOT NULL,   
    publish_year varchar(4) default NULL,   
    type varchar(100) default NULL,   
    PRIMARY KEY (id)   
);   
 // 保存用户对电影的评分，即喜好程度  
CREATE TABLE movie_preference ( 
    userId INTEGER NOT NULL,   
    movieId INTEGER NOT NULL,   
    preference INTEGER NOT NULL DEFAULT 0,   
    time INTEGER not null default 0,   
    FOREIGN KEY (movieId) REFERENCES movies(id) ON DELETE CASCADE   
); 

CREATE TABLE user (  
    id INTEGER NOT NULL AUTO_INCREMENT,   
    gender varchar(2) NOT NULL,   
    age INTEGER default NULL,   
    job varchar(100) default NULL,   
    zipcode varchar(100) default NULL,   
    PRIMARY KEY (id)   
);   
//Movie：表示电影，包含电影的基本信息：编号、名称、发布时间、类型等等
//Movie Reference：表示某个用户对某个电影的喜好程度，包含用户编号、电影编号、用户的评分以及评分的时间
至于如何将dat文件中的内容导入到MySQL数据库中，分别由本工程目录文件下的ImportMovies.java和ImportMoviePreference.java文件实现。
//users.dat的文件描述是 用户编号::性别::年龄::职业::Zip-code


  