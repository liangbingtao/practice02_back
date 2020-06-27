CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(32) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `passWord` varchar(50) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `userRealname` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci DEFAULT NULL,
  `usergender` varchar(10) CHARACTER SET gbk COLLATE gbk_chinese_ci DEFAULT NULL,
  `userphone` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `userwechat` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci DEFAULT NULL,
  `userscore` int DEFAULT NULL,
  `userarea` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci DEFAULT NULL,
  `usersort` varchar(10) CHARACTER SET gbk COLLATE gbk_chinese_ci DEFAULT NULL,
  `userrank` int DEFAULT NULL,
  `admin` int DEFAULT NULL,
  `token` varchar(255) CHARACTER SET gbk COLLATE gbk_chinese_ci DEFAULT NULL,
  `vip` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=gbk ROW_FORMAT=COMPACT;