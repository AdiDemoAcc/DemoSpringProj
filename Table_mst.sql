CREATE TABLE `com_ldgr_dbrd_menu_items_mst` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_item_name` varchar(50) NOT NULL,
  `menu_id` int(11) NOT NULL,
  `menu_sub_id` int(11) NOT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT 0,
  `maker_cd` int(11) NOT NULL,
  `maker_dt` datetime NOT NULL DEFAULT current_timestamp(),
  `author_cd` int(11) DEFAULT NULL,
  `author_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `com_ldgr_gl_accnt_mst` (
  `gl_accnt_id` int(11) NOT NULL AUTO_INCREMENT,
  `accnt_id` bigint(20) NOT NULL,
  `accnt_no` bigint(20) NOT NULL,
  `accnt_bal` decimal(30,5) NOT NULL DEFAULT 0.00000,
  `bank_name` varchar(50) NOT NULL,
  `bank_branch` varchar(50) NOT NULL,
  `bank_city` varchar(30) NOT NULL,
  `bank_state` varchar(30) NOT NULL,
  `bank_pincode` varchar(10) NOT NULL,
  `maker_cd` int(11) NOT NULL,
  `maker_dt` datetime NOT NULL DEFAULT current_timestamp(),
  `author_cd` int(11) DEFAULT NULL,
  `author_dt` datetime DEFAULT NULL,
  `is_active` tinyint(4) NOT NULL,
  PRIMARY KEY (`gl_accnt_id`)
);

CREATE TABLE `com_ldgr_dbrd_menu_mst` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_id` int(11) NOT NULL,
  `menu_header_name` varchar(35) NOT NULL,
  `maker_cd` int(11) NOT NULL,
  `maker_dt` datetime NOT NULL DEFAULT current_timestamp(),
  `author_cd` int(11) DEFAULT NULL,
  `author_dt` datetime DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
);

CREATE TABLE `com_ldgr_txn_records` (
  `txn_id` int(11) NOT NULL AUTO_INCREMENT,
  `txn_date` datetime DEFAULT NULL,
  `aptmnt_id` int(11) DEFAULT NULL,
  `start_dt` date DEFAULT NULL,
  `end_dt` date DEFAULT NULL,
  `gl_accnt_id` int(11) NOT NULL,
  `txn_type` varchar(5) NOT NULL,
  `txn_amnt` decimal(30,5) NOT NULL,
  `auth_status` tinyint(2) NOT NULL DEFAULT 0,
  `maker_cd` int(11) NOT NULL,
  `maker_dt` datetime DEFAULT NULL,
  `maker_rmrks` varchar(500) NOT NULL,
  `author_cd` int(11) DEFAULT NULL,
  `author_dt` datetime DEFAULT NULL,
  `author_rmrks` varchar(500) DEFAULT NULL,
  `txn_category` varchar(50) NOT NULL DEFAULT 'OTHER',
  `gl_accnt_bal` decimal(30,5) DEFAULT 0.00000,
  PRIMARY KEY (`txn_id`),
  KEY `fk_gl_accnt` (`gl_accnt_id`),
  CONSTRAINT `fk_gl_accnt` FOREIGN KEY (`gl_accnt_id`) REFERENCES `com_ldgr_gl_accnt_mst` (`gl_accnt_id`)
);

RENAME TABLE com_ldgr_dbrd_menu_mst TO com_ldgr_admin_dbrd_menu_mst;

RENAME TABLE com_ldgr_dbrd_menu_items_mst TO com_ldgr_admin_dbrd_menu_items_mst;

ALTER TABLE com_ldgr_txn_records MODIFY COLUMN txn_amnt DECIMAL(30,5) DEFAULT 0.00;

ALTER TABLE com_ldgr_txn_records ADD COLUMN gl_accnt_bal DECIMAL(30,5) DEFAULT 0.00;

ALTER TABLE com_ldgr_txn_records ADD COLUMN gl_accnt_id INT(11) NOT NULL AFTER end_dt;

ALTER TABLE com_ldgr_txn_records ADD CONSTRAINT fk_gl_accnt FOREIGN KEY (gl_accnt_id) REFERENCES com_ldgr_gl_accnt_mst(gl_accnt_id);

ALTER TABLE `com_ldgr_txn_records` MODIFY COLUMN `aptmnt_id` INT(11) NULL, MODIFY COLUMN `txn_category` VARCHAR(20) NOT NULL DEFAULT 'OTHER';

 ALTER TABLE com_ldgr_txn_records MODIFY COLUMN `txn_category` varchar(50) NOT NULL DEFAULT 'OTHER';
