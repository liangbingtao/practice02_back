CREATE TABLE `profession` (
`description` longtext CHARACTER SET gbk COLLATE gbk_chinese_ci,
`subject` varchar(150) CHARACTER SET gbk COLLATE gbk_chinese_ci DEFAULT NULL,
`major` varchar(50) CHARACTER SET gbk COLLATE gbk_chinese_ci DEFAULT NULL,
`pid` int NOT NULL,
`proname` varchar(300) CHARACTER SET gbk COLLATE gbk_chinese_ci DEFAULT NULL,
`timelength` varchar(10) CHARACTER SET gbk COLLATE gbk_chinese_ci DEFAULT NULL,
`classes` varchar(500) CHARACTER SET gbk COLLATE gbk_chinese_ci DEFAULT NULL COMMENT '开设课程',
`job` varchar(500) CHARACTER SET gbk COLLATE gbk_chinese_ci DEFAULT NULL,
`graduate` varchar(500) CHARACTER SET gbk COLLATE gbk_chinese_ci DEFAULT NULL COMMENT '考研方向',
`code` varchar(50) CHARACTER SET gbk COLLATE gbk_chinese_ci DEFAULT NULL COMMENT '专业代码',
`sort` varchar(10) CHARACTER SET gbk COLLATE gbk_chinese_ci DEFAULT NULL,
PRIMARY KEY (`pid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=gbk ROW_FORMAT=COMPACT;