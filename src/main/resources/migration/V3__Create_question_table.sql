CREATE TABLE `question` (
  `id` int NOT NULL,
  `item` longtext CHARACTER SET gbk COLLATE gbk_chinese_ci,
  `value1` int NOT NULL,
  `value2` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=gbk ROW_FORMAT=COMPACT;