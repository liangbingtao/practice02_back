CREATE TABLE `schoolscore` (
  `scid` int NOT NULL DEFAULT '0',
  `sort` int NOT NULL,
  `name` varchar(50) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `minScore` int NOT NULL,
  `minRank` int NOT NULL,
  `batch` int NOT NULL,
  `year` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk ROW_FORMAT=COMPACT;