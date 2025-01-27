create table com_ldgr_txn_mst (
	txn_id int primary key auto_increment, 
	txn_date datetime, 
	start_dt date, 
	end_dt date,
	maker_cd int not null, 
	maker_dt datetime, 
	maker_rmrks varchar(500) not null, 
	author_cd int, 
	author_dt datetime, 
	author_rmrks varchar(500)
);