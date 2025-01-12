CREATE TABLE com_ldgr_user_sec_log(
	id int primary key,
    user_id int not null,
    username varchar(50) not null,
    login_dt datetime not null,
    logout_dt datetime,
    user_active tinyInt(1),
    domain_name varchar(50),
    session_id varchar(50),
    ip_address varchar(50)
);