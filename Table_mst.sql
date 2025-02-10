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
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accnt_id` bigint(20) NOT NULL,
  `accnt_no` bigint(20) NOT NULL,
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
  PRIMARY KEY (`id`)
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