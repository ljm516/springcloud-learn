CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL COMMENT '姓名',
  `cell_phone` VARCHAR(255) NOT NULL COMMENT '手机号',
  `gender` VARCHAR(2) NOT NULL COMMENT '性别',
  `age` BIGINT NOT NULL COMMENT '年龄',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `username_INDEX` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;