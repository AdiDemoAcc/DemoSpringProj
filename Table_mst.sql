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

 
 ------------------------------------------------------------------------ 18/02/2025 --------------------------------------------------------------------------------
 
CREATE TABLE `com_ldgr_role_menu_mst` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `menu_map` varchar(512) DEFAULT NULL,
  `maker_cd` int(11) DEFAULT NULL,
  `maker_dt` datetime DEFAULT current_timestamp(),
  `author_cd` int(11) DEFAULT NULL,
  `author_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_role_id` (`role_id`),
  CONSTRAINT `fk_role_id` FOREIGN KEY (`role_id`) REFERENCES `com_ldgr_role_mst` (`role_id`)
) ;

INSERT INTO com_ldgr_dbrd_menu_mst (menu_id, menu_header_name, maker_cd, maker_dt, author_cd, author_dt, is_active)
VALUES 
(100, 'Dashboard', 1, NOW(), 1, NOW(), 1),
(200, 'Society Members', 1, NOW(), 1, NOW(), 1),
(300, 'Maintenance & Bills', 1, NOW(), 1, NOW(), 1),
(400, 'Transactions', 1, NOW(), 1, NOW(), 1),
(500, 'Reports & Analytics', 1, NOW(), 1, NOW(), 1);


INSERT INTO com_ldgr_dbrd_menu_items_mst (menu_item_name, menu_id, menu_sub_id, is_active, maker_cd, maker_dt, author_cd, author_dt)
VALUES 
-- Dashboard
('Overview', 100, 101, 1, 1, NOW(), 1, NOW()),
('Notifications', 100, 102, 1, 1, NOW(), 1, NOW()),

-- Society Members
('View Members', 200, 201, 1, 1, NOW(), 1, NOW()),
('Add New Member', 200, 202, 1, 1, NOW(), 1, NOW()),

-- Maintenance & Bills
('Generate Maintenance Bills', 300, 301, 1, 1, NOW(), 1, NOW()),
('View Paid & Pending Bills', 300, 302, 1, 1, NOW(), 1, NOW()),
('Late Payment Penalties', 300, 303, 1, 1, NOW(), 1, NOW()),

-- Transactions
('Transaction History', 400, 401, 1, 1, NOW(), 1, NOW()),
('New Payment Entry', 400, 402, 1, 1, NOW(), 1, NOW()),
('Reconciliation', 400, 403, 1, 1, NOW(), 1, NOW()),

-- Reports & Analytics
('Monthly Financial Report', 500, 501, 1, 1, NOW(), 1, NOW()),
('Yearly Summary', 500, 502, 1, 1, NOW(), 1, NOW()),
('Custom Reports', 500, 503, 1, 1, NOW(), 1, NOW());


INSERT INTO com_ldgr_role_menu_mst (role_id, menu_map, maker_cd, maker_dt, author_cd, author_dt) 
VALUES 
(100, '100=101~102 | 200=201~202 | 300=301~302~303 | 400=401~402~403 | 500=501~502~503', 1, NOW(), 1, NOW());

