CREATE TABLE `score_rank` (
  `id` int NOT NULL,
  `score` int DEFAULT NULL COMMENT '分数',
  `rank` int DEFAULT NULL COMMENT '位次',
  `year` int DEFAULT NULL COMMENT '年份',
  `sort` varchar(255) DEFAULT NULL COMMENT '文理科，1为理科，2为文科',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='一分一段表，用于查询位次';