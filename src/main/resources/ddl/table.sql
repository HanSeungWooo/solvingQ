create database springSecurity_example;

use springSecurity_example;

create table user(
    id int auto_increment primary key
    , login_id varchar(255) not null
    , password varchar(255) not null
    , nickname varchar(255) not null
    , role enum('USER', 'ADMIN') not null
)

create table notice(
    notice_no int auto_increment primary key
    , title varchar(255) not null
    , content varchar(4000)
    , show_yn char(1) default 'Y'
)

----------------------------------------



create user 'spring5'@'localhost' identified by 'spring5';

create database spring5fs character set=utf8;

grant all privileges on spring5fs.* to 'spring5'@'localhost';

create table spring5fs.MEMBER (
                                  ID int auto_increment primary key,
                                  EMAIL varchar(255),
                                  PASSWORD varchar(100),
                                  NAME varchar(100),
                                  REGDATE datetime,
                                  unique key (EMAIL)
) engine=InnoDB character set = utf8;

------------------------------------------------

create database solveq;

grant all privileges on solveq.* to 'spring5'@'localhost';

-- 유저 테이블
create table solveq.user (
                             id int auto_increment primary key, -- 유저 id
                             role enum('USER', 'ADMIN') not null -- 권한(사용자, 관리자)
);

-- 전체문항 테이블
create table solveq.question (
                                 id int primary key, -- 문항 id
                                 desc_text varchar(255) not null, -- 문제
                                 opt1 varchar(255) not null, -- 보기1
                                 opt2 varchar(255) not null, -- 보기2
                                 opt3 varchar(255) not null, -- 보기3
                                 opt4 varchar(255) not null, -- 보기4
                                 correct varchar(255) not null -- 정답
);

-- 제공문항 테이블
create table solveq.shown_question (
                                       question_id int, -- 문항 id(FK)
                                       user_id int, -- 유저 id(FK)
                                       shown_dt varchar(8), -- 제공일자(YYYYMMDD)
                                       shown_yn char(1) not null, -- 제공여부
                                       primary key (question_id, user_id, shown_dt), -- PK (문항 id, 유저 id, 제공일자)
                                       foreign key (question_id) references solveq.question(id),
                                       foreign key (user_id) references solveq.user(id)
);

-- 풀이이력 테이블
create table solveq.solving_history (
                                        question_id int, -- 문항 id(FK)
                                        user_id int, -- 유저 id(FK)
                                        solved_dt varchar(8), -- 풀이일자(YYYYMMDD)
                                        answer varchar(255) not null, -- 답안
                                        solving_time_in_second int not null, -- 풀이시간(초)
                                        primary key (question_id, user_id, solved_dt), -- PK (문항 id, 유저 id, 풀이일자)
                                        foreign key (question_id) references solveq.question(id),
                                        foreign key (user_id) references solveq.user(id)
);