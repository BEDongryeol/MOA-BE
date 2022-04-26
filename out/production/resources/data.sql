DROP TABLE if exists bank cascade ;
DROP TABLE if exists bank_account cascade ;
DROP TABLE if exists bank_saving_products cascade ;
DROP TABLE if exists moachallenge cascade ;
DROP TABLE if exists user cascade ;
DROP TABLE if exists user_service_info cascade ;
DROP TABLE if exists bank_transaction_history cascade ;
DROP TABLE if exists my_challenge cascade ;
DROP TABLE if exists registration_management cascade ;
DROP TABLE if exists usergoal cascade ;
DROP TABLE if exists user_account cascade ;


create table bank (
id bigint not null auto_increment,
bank_image_url varchar(255),
bank_name varchar(255),
primary key (id)
);

create table bank_account (
id bigint not null auto_increment,
account_number varchar(255),
account_type varchar(255),
birth_date date,
created_date datetime,
current_amount decimal(19,2),
expiration_date datetime,
goal_amount decimal(19,2),
owner varchar(255),
password bigint,
payment decimal(19,2),
product_name varchar(255),
bank_id bigint,
primary key (id)
);

create table bank_saving_products (
id bigint not null auto_increment,
amount_explanation varchar(255),
basic_interest decimal(19,2),
highest_interest decimal(19,2),
product_name varchar(255),
subscription_limit varchar(255),
subscription_period varchar(255),
bank_id bigint,
primary key (id)
);

create table moachallenge (
id bigint not null,
challenge_count integer,
challenge_key_sum integer,
challenge_name varchar(255),
challenge_state varchar(255),
e_date datetime,
first_detail_challenge_name varchar(255),
first_detail_challenge_url varchar(255),
firstbet_key integer,
main_challenge_url varchar(255),
second_detail_challenge_name varchar(255),
second_detail_challenge_url varchar(255),
secondbet_key integer,
winner bit not null,
primary key (id)
);

create table user (
id bigint not null auto_increment,
auth varchar(255),
birth_date date,
key_points integer,
login_id varchar(255),
name varchar(255),
phone_num varchar(255),
pw varchar(255),
user_service_info_id bigint,
primary key (id)
);


create table user_service_info (
id bigint not null auto_increment,
end_day date,
military_unit varchar(255),
military_rank varchar(255),
service_day date,
service_num varchar(255),
start_day date,
user_id bigint,
primary key (id)
);

create table user_account (
id bigint not null auto_increment,
account_number varchar(255),
account_type varchar(255),
birth_date date,
created_date datetime,
expiration_date datetime,
current_amount decimal(19,2),
goal_amount decimal(19,2),
owner varchar(255),
account_nickname varchar(255),
user_id bigint,
bank_id bigint,
product_name varchar(255),
account_state varchar(255),
saving_type varchar(255),
payment decimal(19,2),
withdrawal_account_id bigint,
password bigint,
primary key (id)
);

create table bank_transaction_history (
id bigint not null auto_increment,
amount decimal(19,2),
balance decimal(19,2),
memo varchar(255),
transaction_date datetime,
account_id bigint,
primary key (id)
);

create table registration_management (
id bigint not null auto_increment,
account_number varchar(255),
account_nickname varchar(255),
password bigint,
payment decimal(19,2),
product_id bigint,
saving_type varchar(255),
subscription_period integer,
status varchar(255),
bank_saving_products_id bigint,
user_id bigint,
primary key (id)
);

create table my_challenge (
id bigint not null auto_increment,
my_challenge_key integer,
my_selected boolean,
my_challenge_bet_timer datetime,
primary key (id)
);

create table usergoal(
id bigint not null auto_increment,
goal_name varchar(255),
saving_mode varchar(255),
current_amount decimal(19,2),
goal_amount decimal(19,2),
s_date datetime,
e_date datetime,
deposit_method varchar(255),
limit_cycle varchar(255),
amount_per_cycle decimal(19,2),
category varchar(255),
goal_state varchar(255),
bank_name varchar(255),
product_name varchar(255),
account_number varchar(255),
account_current_amount decimal(19,2),
back_image_url varchar(255),
account_state varchar(255),
primary key (id)
);


INSERT INTO bank (`BANK_NAME`, `BANK_IMAGE_URL`) VALUES ('신한', 'https://cdn.jsdelivr.net/gh/BuenCamino3rd/test/image/shinhan.svg');
INSERT INTO bank (`BANK_NAME`, `BANK_IMAGE_URL`) VALUES ('IBK', 'https://cdn.jsdelivr.net/gh/BuenCamino3rd/test/image/ibk.svg');
INSERT INTO bank (`BANK_NAME`, `BANK_IMAGE_URL`) VALUES ('KJB', 'https://cdn.jsdelivr.net/gh/BuenCamino3rd/test/image/kjb.svg');
INSERT INTO bank (`BANK_NAME`, `BANK_IMAGE_URL`) VALUES ('우체국', 'https://cdn.jsdelivr.net/gh/BuenCamino3rd/test/image/woopyeon.svg');
INSERT INTO bank (`BANK_NAME`, `BANK_IMAGE_URL`) VALUES ('DGB', 'https://cdn.jsdelivr.net/gh/BuenCamino3rd/test/image/dgb.svg');
INSERT INTO bank (`BANK_NAME`, `BANK_IMAGE_URL`) VALUES ('KB국민', 'https://cdn.jsdelivr.net/gh/BuenCamino3rd/test/image/kb.svg');
INSERT INTO bank (`BANK_NAME`, `BANK_IMAGE_URL`) VALUES ('NH', 'https://cdn.jsdelivr.net/gh/BuenCamino3rd/test/image/nh.svg');
INSERT INTO bank (`BANK_NAME`, `BANK_IMAGE_URL`) VALUES ('우리은행', 'https://cdn.jsdelivr.net/gh/BuenCamino3rd/test/image/woori.svg');
INSERT INTO bank (`BANK_NAME`, `BANK_IMAGE_URL`) VALUES ('하나', 'https://cdn.jsdelivr.net/gh/BuenCamino3rd/test/image/hana.svg');

INSERT INTO bank_saving_products (`ID`,`AMOUNT_EXPLANATION`, `PRODUCT_NAME`, `BASIC_INTEREST`, `HIGHEST_INTEREST`, `SUBSCRIPTION_PERIOD`, `SUBSCRIPTION_LIMIT`, `BANK_ID`) VALUES ('1','월 20만원 이하,(전 금융기관 합산 40만원 이내)', '장병내일준비적금', 4.5, 5.2, '12개월', '6개월 이상 24개월 이하', '1');
INSERT INTO bank_saving_products (`ID`,`AMOUNT_EXPLANATION`, `PRODUCT_NAME`, `BASIC_INTEREST`, `HIGHEST_INTEREST`, `SUBSCRIPTION_PERIOD`, `SUBSCRIPTION_LIMIT`, `BANK_ID`) VALUES ('2','월 20만원 이하,(전 금융기관 합산 40만원 이내)', '장병내일준비적금', 4.5, 5.0, '12개월', '6개월 이상 24개월 이하', '2');
INSERT INTO bank_saving_products (`ID`,`AMOUNT_EXPLANATION`, `PRODUCT_NAME`, `BASIC_INTEREST`, `HIGHEST_INTEREST`, `SUBSCRIPTION_PERIOD`, `SUBSCRIPTION_LIMIT`, `BANK_ID`) VALUES ('3','월 20만원 이하,(전 금융기관 합산 40만원 이내)', '장병내일준비적금', 4.5, 5.0, '12개월', '6개월 이상 24개월 이하', '3');
INSERT INTO bank_saving_products (`ID`,`AMOUNT_EXPLANATION`, `PRODUCT_NAME`, `BASIC_INTEREST`, `HIGHEST_INTEREST`, `SUBSCRIPTION_PERIOD`, `SUBSCRIPTION_LIMIT`, `BANK_ID`) VALUES ('4','월 20만원 이하,(전 금융기관 합산 40만원 이내)', '장병내일준비적금', 4.5, 5.0, '12개월', '6개월 이상 24개월 이하', '4');
INSERT INTO bank_saving_products (`ID`,`AMOUNT_EXPLANATION`, `PRODUCT_NAME`, `BASIC_INTEREST`, `HIGHEST_INTEREST`, `SUBSCRIPTION_PERIOD`, `SUBSCRIPTION_LIMIT`, `BANK_ID`) VALUES ('5','월 20만원 이하,(전 금융기관 합산 40만원 이내)', '장병내일준비적금', 4.5, 4.5, '12개월', '6개월 이상 24개월 이하', '5');
INSERT INTO bank_saving_products (`ID`,`AMOUNT_EXPLANATION`, `PRODUCT_NAME`, `BASIC_INTEREST`, `HIGHEST_INTEREST`, `SUBSCRIPTION_PERIOD`, `SUBSCRIPTION_LIMIT`, `BANK_ID`) VALUES ('6','월 20만원 이하,(전 금융기관 합산 40만원 이내)', '장병내일준비적금', 4.5, 4.5, '12개월', '6개월 이상 24개월 이하', '6');
INSERT INTO bank_saving_products (`ID`,`AMOUNT_EXPLANATION`, `PRODUCT_NAME`, `BASIC_INTEREST`, `HIGHEST_INTEREST`, `SUBSCRIPTION_PERIOD`, `SUBSCRIPTION_LIMIT`, `BANK_ID`) VALUES ('7','월 20만원 이하,(전 금융기관 합산 40만원 이내)', '장병내일준비적금', 4.0, 4.5, '12개월', '6개월 이상 24개월 이하', '7');
INSERT INTO bank_saving_products (`ID`,`AMOUNT_EXPLANATION`, `PRODUCT_NAME`, `BASIC_INTEREST`, `HIGHEST_INTEREST`, `SUBSCRIPTION_PERIOD`, `SUBSCRIPTION_LIMIT`, `BANK_ID`) VALUES ('8','월 20만원 이하,(전 금융기관 합산 40만원 이내)', '장병내일준비적금', 4.0, 4.5, '12개월', '6개월 이상 24개월 이하', '8');
INSERT INTO bank_saving_products (`ID`,`AMOUNT_EXPLANATION`, `PRODUCT_NAME`, `BASIC_INTEREST`, `HIGHEST_INTEREST`, `SUBSCRIPTION_PERIOD`, `SUBSCRIPTION_LIMIT`, `BANK_ID`) VALUES ('9','월 20만원 이하,(전 금융기관 합산 40만원 이내)', '장병내일준비적금', 4.0, 4.5, '12개월', '6개월 이상 24개월 이하', '9');

INSERT INTO bank_account (`OWNER`, `BIRTH_DATE`, `PRODUCT_NAME`, `BANK_ID`, `GOAL_AMOUNT`, `CURRENT_AMOUNT`, `ACCOUNT_NUMBER`, `ACCOUNT_TYPE`, `CREATED_DATE`, `EXPIRATION_DATE`, `PAYMENT`, `PASSWORD`) VALUES ('정인우', '1995-05-17', '장병내일준비적금','1', 1200000, 100000, '123-5145-61436', '예적금', '2022-01-01', '2023-01-01', 100000, 1234);
INSERT INTO bank_account (`OWNER`, `BIRTH_DATE`, `PRODUCT_NAME`, `BANK_ID`, `GOAL_AMOUNT`, `CURRENT_AMOUNT`, `ACCOUNT_NUMBER`, `ACCOUNT_TYPE`, `CREATED_DATE`, `EXPIRATION_DATE`, `PAYMENT`, `PASSWORD`) VALUES ('정인우', '1995-05-17', '장병내일준비적금','2', 1200000, 100000, '123-5144-51345', '예적금', '2022-01-01', '2023-01-01', 100000, 1234);
INSERT INTO bank_account (`OWNER`, `BIRTH_DATE`, `PRODUCT_NAME`, `BANK_ID`, `GOAL_AMOUNT`, `CURRENT_AMOUNT`, `ACCOUNT_NUMBER`, `ACCOUNT_TYPE`, `CREATED_DATE`, `EXPIRATION_DATE`, `PAYMENT`, `PASSWORD`) VALUES ('정인우', '1995-05-17', '나라사랑우대통장','1', 0, 140000, '414-5145-43146', '입출금', '2014-04-09', '2023-01-01', 0, 1234);
INSERT INTO bank_account (`OWNER`, `BIRTH_DATE`, `PRODUCT_NAME`, `BANK_ID`, `GOAL_AMOUNT`, `CURRENT_AMOUNT`, `ACCOUNT_NUMBER`, `ACCOUNT_TYPE`, `CREATED_DATE`, `EXPIRATION_DATE`, `PAYMENT`, `PASSWORD`) VALUES ('정동렬', '1995-05-17', '장병내일준비적금','4', 2400000, 200000, '489-4390-9931', '예적금', '2022-01-01', '2023-01-01', 200000, 1234);
INSERT INTO bank_account (`OWNER`, `BIRTH_DATE`, `PRODUCT_NAME`, `BANK_ID`, `GOAL_AMOUNT`, `CURRENT_AMOUNT`, `ACCOUNT_NUMBER`, `ACCOUNT_TYPE`, `CREATED_DATE`, `EXPIRATION_DATE`, `PAYMENT`, `PASSWORD`) VALUES ('정동렬', '1995-05-17', '자유입출금계좌','5', 0, 100000, '093-2321-40923', '입출금', '2022-01-01', '2023-01-01', 0, 1234);
INSERT INTO bank_account (`OWNER`, `BIRTH_DATE`, `PRODUCT_NAME`, `BANK_ID`, `GOAL_AMOUNT`, `CURRENT_AMOUNT`, `ACCOUNT_NUMBER`, `ACCOUNT_TYPE`, `CREATED_DATE`, `EXPIRATION_DATE`, `PAYMENT`, `PASSWORD`) VALUES ('정동렬', '1995-05-17', '나라사랑우대통장','1', 0, 100000, '021-1512-5341', '입출금', '2022-01-01', '2023-01-01', 0, 1234);
INSERT INTO bank_account (`OWNER`, `BIRTH_DATE`, `PRODUCT_NAME`, `BANK_ID`, `GOAL_AMOUNT`, `CURRENT_AMOUNT`, `ACCOUNT_NUMBER`, `ACCOUNT_TYPE`, `CREATED_DATE`, `EXPIRATION_DATE`, `PAYMENT`, `PASSWORD`) VALUES ('홍길동', '1999-09-21', '자유입출금계좌','7', 0, 4000000, '213-123012-3012', '입출금', '2022-01-01', '2023-01-01', 0, 1234);

INSERT INTO bank_transaction_history (`AMOUNT`, `BALANCE`, `MEMO`, `TRANSACTION_DATE`, `ACCOUNT_ID`) VALUES (100000, 100000, '정기이체', '2022-01-10 13:00:28', 1);
INSERT INTO bank_transaction_history (`AMOUNT`, `BALANCE`, `MEMO`, `TRANSACTION_DATE`, `ACCOUNT_ID`) VALUES (100000, 100000, '정기이체', '2022-01-10 13:05:49', 2);
INSERT INTO bank_transaction_history (`AMOUNT`, `BALANCE`, `MEMO`, `TRANSACTION_DATE`, `ACCOUNT_ID`) VALUES (5000, 169000, 'GS25', '2021-12-20 18:42:52', 3);
INSERT INTO bank_transaction_history (`AMOUNT`, `BALANCE`, `MEMO`, `TRANSACTION_DATE`, `ACCOUNT_ID`) VALUES (17000, 152000, '용두동쭈꾸미', '2021-12-31 09:05:49', 3);
INSERT INTO bank_transaction_history (`AMOUNT`, `BALANCE`, `MEMO`, `TRANSACTION_DATE`, `ACCOUNT_ID`) VALUES (7000, 145000, '파리바게트', '2021-12-31 17:05:24', 3);
INSERT INTO bank_transaction_history (`AMOUNT`, `BALANCE`, `MEMO`, `TRANSACTION_DATE`, `ACCOUNT_ID`) VALUES (5000, 140000, 'GS25', '2022-01-10 13:05:49', 3);
INSERT INTO bank_transaction_history (`AMOUNT`, `BALANCE`, `MEMO`, `TRANSACTION_DATE`, `ACCOUNT_ID`) VALUES (200000, 200000, '정기이체', '2022-01-10 17:42:32', 4);
INSERT INTO bank_transaction_history (`AMOUNT`, `BALANCE`, `MEMO`, `TRANSACTION_DATE`, `ACCOUNT_ID`) VALUES (6000, 104500, 'CU', '2022-01-18 11:14:42', 5);
INSERT INTO bank_transaction_history (`AMOUNT`, `BALANCE`, `MEMO`, `TRANSACTION_DATE`, `ACCOUNT_ID`) VALUES (4500, 100000, 'GS25', '2022-01-19 20:11:32', 5);
INSERT INTO bank_transaction_history (`AMOUNT`, `BALANCE`, `MEMO`, `TRANSACTION_DATE`, `ACCOUNT_ID`) VALUES (200000, 4000000, '정인우', '2022-01-16 11:42:39', 6);

INSERT INTO user (`NAME`, `BIRTH_DATE`) VALUES ('정인우', '1995-05-17');
INSERT INTO user (`NAME`, `BIRTH_DATE`) VALUES ('정동렬', '1995-05-17');
INSERT INTO user (`NAME`, `BIRTH_DATE`, `LOGIN_ID`, `PHONE_NUM`, `PW`) VALUES ('홍길동', '1996-02-01', '2112345678', '01012341234', '{bcrypt}$2a$10$KmeQMmC62oTmxf6Lv56NDOVCGR5LNh5VkjMe98741HNQg14TBsNPO');

INSERT INTO user_account (`ID`, `ACCOUNT_NUMBER`, `ACCOUNT_TYPE`, `BIRTH_DATE`, `CREATED_DATE`, `CURRENT_AMOUNT`, `EXPIRATION_DATE`, `GOAL_AMOUNT`, `OWNER`, `ACCOUNT_NICKNAME`, `USER_ID`, `BANK_ID`, `PRODUCT_NAME`, `ACCOUNT_STATE`, `SAVING_TYPE`, `PAYMENT`, `WITHDRAWAL_ACCOUNT_ID`, `PASSWORD`) VALUES (3, '414-5145-43146', '예적금', '1995-05-17', '2014-04-09', 100000, '2023-01-01', 0, '정인우', '', 1, 1, '나라사랑우대통장', '연동', '해당없음', 0, 3, 1234);
INSERT INTO user_account (`ID`, `ACCOUNT_NUMBER`, `ACCOUNT_TYPE`, `BIRTH_DATE`, `CREATED_DATE`, `CURRENT_AMOUNT`, `EXPIRATION_DATE`, `GOAL_AMOUNT`, `OWNER`, `ACCOUNT_NICKNAME`, `USER_ID`, `BANK_ID`, `PRODUCT_NAME`, `ACCOUNT_STATE`, `SAVING_TYPE`, `PAYMENT`, `WITHDRAWAL_ACCOUNT_ID`, `PASSWORD`) VALUES (1, '123-5145-61436', '예적금', '1995-05-17', '2022-01-01', 100000, '2023-01-01', 1200000, '정인우', '', 1, 1, '장병내일준비적금', '연동', '자동이체', 100000, 3, 1234);
--INSERT INTO user_account (`ID`, `ACCOUNT_NUMBER`, `ACCOUNT_TYPE`, `BIRTH_DATE`, `CREATED_DATE`, `CURRENT_AMOUNT`, `EXPIRATION_DATE`, `GOAL_AMOUNT`, `OWNER`, `ACCOUNT_NICKNAME`, `USER_ID`, `BANK_ID`, `PRODUCT_NAME`, `ACCOUNT_STATE`, `SAVING_TYPE`, `PAYMENT`, `WITHDRAWAL_ACCOUNT_ID`, `PASSWORD`) VALUES (2, '123-5144-51345', '예적금', '1995-05-17', '2022-01-01', 100000, '2023-01-01', 1200000, '정인우', '', 1, 2, '장병내일준비적금', '연동', '자동이체', 100000, 3, 1234);


INSERT INTO registration_management (`ACCOUNT_NUMBER`, `ACCOUNT_NICKNAME`, `PASSWORD`, `PAYMENT`, `PRODUCT_ID`, `SAVING_TYPE`, `SUBSCRIPTION_PERIOD`, `STATUS`, `BANK_SAVING_PRODUCTS_ID`, `USER_ID`) VALUES ('412-6529-12312', '', '1234', 200000, 1, '자동이체', 10, '신청', 1, 2);

