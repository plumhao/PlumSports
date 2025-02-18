/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80028 (8.0.28)
 Source Host           : localhost:3306
 Source Schema         : sportsgymnasium

 Target Server Type    : MySQL
 Target Server Version : 80028 (8.0.28)
 File Encoding         : 65001

 Date: 18/02/2025 14:21:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for appuser
-- ----------------------------
DROP TABLE IF EXISTS `appuser`;
CREATE TABLE `appuser`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '用户主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `Password` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `LoginTimePeriod` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '登录时间段',
  `RoleType` int NULL DEFAULT NULL COMMENT '角色',
  `UserName` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '账号',
  `PhoneNumber` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号码',
  `ImageUrls` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `Birth` timestamp NULL DEFAULT NULL COMMENT '出生年月',
  `Money` double(20, 5) NULL DEFAULT NULL COMMENT '余额',
  `Name` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `CardNo` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '会员卡号',
  `Notes` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `OpenCardTime` timestamp NULL DEFAULT NULL COMMENT '办卡时间',
  `CardId` int NULL DEFAULT NULL COMMENT '关联会员卡',
  `Certifications` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '资质认证',
  `AreasOfExpertise` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '专长领域',
  `WorkExperience` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '工作经验',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of appuser
-- ----------------------------
INSERT INTO `appuser` VALUES (5, '2024-05-03 00:34:52', 1, 'fitness123', '2024-04-16,2024-05-06', 1, 'member01', '13812345678', 'http://localhost:7245/images/1707651378105/52152126.jpg', NULL, 13150.00000, '张华', 'C1707620649827', NULL, NULL, 5, NULL, NULL, NULL);
INSERT INTO `appuser` VALUES (6, '2024-05-05 00:34:52', 1, 'fitpro12345', '2025-02-11', 2, 'coach01', '13800138000', 'http://localhost:7245/images/1707632868719/54363532412.jpg', NULL, NULL, '张伟', NULL, NULL, NULL, NULL, '国家体育总局认证', '增肌、减脂、体能训练', '10');
INSERT INTO `appuser` VALUES (7, '2024-04-30 00:34:52', 1, 'gymexpert45', '2024-04-16', 2, 'coach02', '13900139000', 'http://localhost:7245/images/1707632895058/34643523512.jpg', NULL, NULL, '王刚', NULL, NULL, NULL, NULL, '国际运动科学协会', '力量,燃脂', '15年');
INSERT INTO `appuser` VALUES (8, '2024-04-27 00:34:52', 1, 'fitboxing88', '', 2, 'coach04', '13712345678', 'http://localhost:7245/images/1707632924716/36235325.jpg', NULL, NULL, '赵雷', NULL, NULL, NULL, NULL, '专业举重认证', '举重、力量训练', '15年');
INSERT INTO `appuser` VALUES (9, '2024-05-01 00:34:52', NULL, '123456', '2025-01-21', 1, 'xiaoyu', '12345678901', 'http://localhost:7245/images/1707882539293/512521521521.jpg', NULL, 1141.00000, '小于', 'C1707875090956', NULL, '2024-04-29 10:36:20', 2, NULL, NULL, NULL);
INSERT INTO `appuser` VALUES (10, '2024-04-28 00:34:52', 1, 'user123456', '2024-04-16', 1, 'user123456', NULL, 'http://localhost:7245/images/1713278101109/26437437.jpg', NULL, 10541.50000, '李子木', 'C1713278106914', NULL, '2024-04-26 22:35:43', 5, NULL, NULL, NULL);
INSERT INTO `appuser` VALUES (11, '2025-01-10 10:03:45', NULL, 'lizihao666', '2025-01-10,2025-01-15,2025-01-21,2025-02-10,2025-02-11,2025-02-12', 1, 'plum', '15216090312', 'http://localhost:7245/images/1736474606884/hsq.jpg', NULL, 3500.00000, '李', 'C1736474625069', NULL, '2025-01-10 16:59:49', 3, NULL, NULL, NULL);
INSERT INTO `appuser` VALUES (12, '2025-01-10 10:45:16', NULL, 'Lyt123456', '2025-01-10', 1, 'lyt', NULL, 'http://localhost:7245/images/1736477096047/hsq.jpg', NULL, 500.00000, '赖阳涛', 'C1736477116364', NULL, '2025-01-10 10:50:04', 1, NULL, NULL, NULL);
INSERT INTO `appuser` VALUES (13, '2025-01-10 16:54:15', 1, 'xjx040901', '2025-02-10,2025-02-12', 0, 'admin', '15216090312', 'http://localhost:7245/images/1736499252074/IMG_20240218_181306.jpg', NULL, NULL, '谢宝', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `appuser` VALUES (14, '2025-01-21 14:13:38', 1, 'lzc123456', '2025-01-21', 1, 'luoke', NULL, 'http://localhost:7245/images/1737440011491/yb.jpg', NULL, 0.00000, '李子成', 'C1737440017832', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `appuser` VALUES (15, '2025-01-21 14:26:18', 14, 'lizihao111', NULL, 1, '111', NULL, 'http://localhost:7245/images/1737440726516/jj1.jpg', NULL, 0.00000, '子豪', 'C1737440777552', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `appuser` VALUES (16, '2025-01-21 14:30:02', 11, '1111111a', NULL, 1, 'yang', NULL, 'http://localhost:7245/images/1737440991137/hsq.jpg', NULL, 0.00000, '杨', 'C1737441002435', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `appuser` VALUES (17, '2025-01-21 14:41:50', 14, '1111111a', NULL, 1, '444', '14311111111', 'http://localhost:7245/images/1737441697387/a.jpg', NULL, 0.00000, '李', 'C1737441709606', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `appuser` VALUES (18, '2025-01-21 14:46:13', 11, '1111111a', NULL, 1, '111', '14312312341', 'http://localhost:7245/images/1737441953810/mc2.jpg', NULL, 0.00000, 'plumx', 'C1737441973016', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `appuser` VALUES (20, '2025-02-12 11:17:27', 13, 'lizihao666', NULL, 0, 'admin2', '15216090312', 'http://localhost:7245/images/1739330245461/Snipaste_2025-01-19_13-34-13_compressed.jpg', NULL, NULL, '李子豪', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '轮播图主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `ImageUrls` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '封面',
  `Remark` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `Title` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  `LinkUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '跳转路径',
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `CreatorId`(`CreatorId` ASC) USING BTREE,
  CONSTRAINT `banner_ibfk_1` FOREIGN KEY (`CreatorId`) REFERENCES `appuser` (`Id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of banner
-- ----------------------------
INSERT INTO `banner` VALUES (1, '2024-04-14 22:08:19', NULL, 'http://localhost:7245/images/1707636944291/125216436.jpg', NULL, '时尚运动健身会', 'https://www.google.com/');
INSERT INTO `banner` VALUES (2, '2024-04-09 22:08:19', NULL, 'http://localhost:7245/images/1707636960229/4362352353255.jpg', NULL, '运动健身', 'https://www.bing.com/');
INSERT INTO `banner` VALUES (3, '2024-04-16 22:08:19', NULL, 'http://localhost:7245/images/1707636974727/6475463253.jpg', NULL, '生命不息运动不止', 'https://www.bing.com/');
INSERT INTO `banner` VALUES (4, '2024-04-16 22:08:19', NULL, 'http://localhost:7245/images/1707637243510/43754643535.jpg', NULL, '健身运动NewHealTher', 'https://www.baidu.com');

-- ----------------------------
-- Table structure for card
-- ----------------------------
DROP TABLE IF EXISTS `card`;
CREATE TABLE `card`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '会员卡主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `Name` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '会员卡名',
  `Ranks` int NULL DEFAULT NULL COMMENT '等级',
  `Money` double(20, 5) NULL DEFAULT NULL COMMENT '达到充值余额',
  `Discounts` double(20, 5) NULL DEFAULT NULL COMMENT '折扣百分比',
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `CreatorId`(`CreatorId` ASC) USING BTREE,
  CONSTRAINT `card_ibfk_1` FOREIGN KEY (`CreatorId`) REFERENCES `appuser` (`Id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of card
-- ----------------------------
INSERT INTO `card` VALUES (1, '2024-05-01 00:34:28', NULL, '青铜卡', 1, 500.00000, 0.95000);
INSERT INTO `card` VALUES (2, '2024-05-02 00:34:28', NULL, '白银卡', 2, 1000.00000, 0.90000);
INSERT INTO `card` VALUES (3, '2024-04-29 00:34:28', NULL, '黄金卡', 3, 2000.00000, 0.85000);
INSERT INTO `card` VALUES (4, '2024-05-04 00:34:28', NULL, '铂金卡', 4, 5000.00000, 0.80000);
INSERT INTO `card` VALUES (5, '2024-05-05 00:34:28', NULL, '钻石卡', 5, 10000.00000, 0.75000);
INSERT INTO `card` VALUES (6, '2024-04-28 00:34:28', NULL, '星耀卡', 6, 20000.00000, 0.70000);
INSERT INTO `card` VALUES (7, '2024-04-28 00:34:28', NULL, '至尊卡', 7, 100000.00000, 0.50000);

-- ----------------------------
-- Table structure for cardrecord
-- ----------------------------
DROP TABLE IF EXISTS `cardrecord`;
CREATE TABLE `cardrecord`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '会员变动记录主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `UserId` int NULL DEFAULT NULL COMMENT '用户',
  `Operation` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '操作内容',
  `CardNo` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '会员卡号',
  `OperateTime` timestamp NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `CreatorId`(`CreatorId` ASC) USING BTREE,
  INDEX `UserId`(`UserId` ASC) USING BTREE,
  CONSTRAINT `cardrecord_ibfk_1` FOREIGN KEY (`CreatorId`) REFERENCES `appuser` (`Id`) ON DELETE SET NULL ON UPDATE RESTRICT,
  CONSTRAINT `cardrecord_ibfk_2` FOREIGN KEY (`UserId`) REFERENCES `appuser` (`Id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cardrecord
-- ----------------------------
INSERT INTO `cardrecord` VALUES (1, '2024-04-11 22:08:19', 5, 5, '会员C1707620649827会员升级为:钻石卡', 'C1707620649827', '2024-04-11 22:35:41');
INSERT INTO `cardrecord` VALUES (2, '2024-04-16 22:08:19', 9, 9, '会员C1707875090956会员升级为:青铜卡', 'C1707875090956', '2024-04-14 10:36:20');
INSERT INTO `cardrecord` VALUES (3, '2024-04-10 22:08:19', 9, 9, '会员C1707875090956会员升级为:白银卡', 'C1707875090956', '2024-04-14 10:38:02');
INSERT INTO `cardrecord` VALUES (4, '2024-04-07 22:08:19', 9, 9, '会员C1707875090956会员升级为:白银卡', 'C1707875090956', '2024-04-14 10:38:14');
INSERT INTO `cardrecord` VALUES (5, '2024-04-16 22:35:43', 10, 10, '会员C1713278106914会员升级为:青铜卡', 'C1713278106914', '2024-04-16 22:35:43');
INSERT INTO `cardrecord` VALUES (6, '2024-04-16 22:36:03', 10, 10, '会员C1713278106914会员升级为:钻石卡', 'C1713278106914', '2024-04-16 22:36:03');
INSERT INTO `cardrecord` VALUES (7, '2025-01-10 10:50:04', 12, 12, '会员C1736477116364会员升级为:青铜卡', 'C1736477116364', '2025-01-10 10:50:04');
INSERT INTO `cardrecord` VALUES (8, '2025-01-10 16:59:49', 11, 11, '会员C1736474625069会员升级为:青铜卡', 'C1736474625069', '2025-01-10 16:59:49');
INSERT INTO `cardrecord` VALUES (9, '2025-02-11 09:03:55', 11, 11, '会员C1736474625069会员升级为:黄金卡', 'C1736474625069', '2025-02-11 09:03:55');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '话题评论主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `TopicId` int NULL DEFAULT NULL COMMENT '话题',
  `Content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '评论内容',
  `NickName` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '昵称',
  `HeadImage` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `RootCommentId` int NULL DEFAULT NULL COMMENT '根评论',
  `TargetUserId` int NULL DEFAULT NULL COMMENT '回复用户',
  `SendUserId` int NULL DEFAULT NULL COMMENT '发送用户',
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `CreatorId`(`CreatorId` ASC) USING BTREE,
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`CreatorId`) REFERENCES `appuser` (`Id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, '2024-04-09 22:08:19', 5, 4, '<p>你好啊</p>', '张华', 'http://localhost:7245/images/1707651378105/52152126.jpg', 0, NULL, 5);
INSERT INTO `comment` VALUES (2, '2024-04-15 22:08:19', 5, 4, '<p>嘿嘿</p>', '张华', 'http://localhost:7245/images/1707651378105/52152126.jpg', 1, 5, 5);
INSERT INTO `comment` VALUES (3, '2024-04-13 22:08:19', 9, 6, '<p>Ni hao</p>', '小于', 'http://localhost:7245/images/1707882539293/512521521521.jpg', 0, NULL, 9);
INSERT INTO `comment` VALUES (4, '2024-04-15 22:08:19', 9, 6, '<p>嘿嘿</p>', '小于', 'http://localhost:7245/images/1707882539293/512521521521.jpg', 3, 9, 9);
INSERT INTO `comment` VALUES (5, '2024-04-16 22:28:08', 5, 2, '<p>你好啊</p>', '张华', 'http://localhost:7245/images/1707651378105/52152126.jpg', 0, NULL, 5);
INSERT INTO `comment` VALUES (6, '2025-01-10 10:22:21', 11, 7, '<p>哇哈哈哈哈</p>', '李', 'http://localhost:7245/images/1736474606884/hsq.jpg', 0, NULL, 11);
INSERT INTO `comment` VALUES (7, '2025-01-10 10:24:47', 11, 2, '<p>你好你好<img class=\"wscnph\" style=\"max-width: 100%; border-radius: 10px;\" src=\"http://localhost:7245/images/1736475885279/jj1.jpg\" /></p>', '李', 'http://localhost:7245/images/1736474606884/hsq.jpg', 0, NULL, 11);
INSERT INTO `comment` VALUES (8, '2025-01-10 10:46:54', 12, 2, '<p>Lyt来咯<img class=\"wscnph\" style=\"max-width: 100%; border-radius: 10px;\" src=\"http://localhost:7245/images/1736477213485/xy.png\" /></p>', '赖阳涛', 'http://localhost:7245/images/1736477096047/hsq.jpg', 0, NULL, 12);

-- ----------------------------
-- Table structure for commentlove
-- ----------------------------
DROP TABLE IF EXISTS `commentlove`;
CREATE TABLE `commentlove`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '评论点赞记录主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `TopicId` int NULL DEFAULT NULL COMMENT '话题',
  `CommentId` int NULL DEFAULT NULL COMMENT '点赞评论',
  `UserId` int NULL DEFAULT NULL COMMENT '点赞人',
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `CreatorId`(`CreatorId` ASC) USING BTREE,
  CONSTRAINT `commentlove_ibfk_1` FOREIGN KEY (`CreatorId`) REFERENCES `appuser` (`Id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of commentlove
-- ----------------------------
INSERT INTO `commentlove` VALUES (1, '2024-04-08 22:08:19', 9, 6, 3, 9);
INSERT INTO `commentlove` VALUES (2, '2024-04-16 22:28:10', 5, 2, 5, 5);

-- ----------------------------
-- Table structure for couse
-- ----------------------------
DROP TABLE IF EXISTS `couse`;
CREATE TABLE `couse`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '课程主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `Name` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '课程名称',
  `CouseTypeId` int NULL DEFAULT NULL COMMENT '类型',
  `Description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '课程描述',
  `Notes` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `VideoUrls` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '视频',
  `Cover` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '封面',
  `IsPutaway` tinyint(1) NULL DEFAULT NULL COMMENT '是否上架',
  `Fee` double(20, 5) NULL DEFAULT NULL COMMENT '费用',
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `CreatorId`(`CreatorId` ASC) USING BTREE,
  CONSTRAINT `couse_ibfk_1` FOREIGN KEY (`CreatorId`) REFERENCES `appuser` (`Id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of couse
-- ----------------------------
INSERT INTO `couse` VALUES (1, '2024-04-07 22:08:19', NULL, '搏击操', 1, '<p><strong><span class=\"spark-text\">搏击操是一种结合了有氧舞蹈、韵律操以及搏击动作的健身运动，它不仅能够帮助减肥塑形，还能有效地缓解压力</span></strong><span class=\"spark-text\">。</span></p>\n<p><span class=\"spark-text\">搏击操，也被称为搏击舞或tae-bo，是一种节奏感强烈、充满活力的健身方式。它通过模拟拳击、踢腿等搏击动作，并配合音乐节奏进行有氧运动，从而达到锻炼身体的目的。这种运动形式不仅能够提高心肺功能，还能增强肌肉力量和柔韧性，特别适合那些想要在锻炼身体的同时寻求情绪宣泄的人。</span></p>\n<p><span class=\"spark-text\">搏击操的特点包括：</span></p>\n<ul>\n<li><strong><span class=\"spark-text\">时尚新颖</span></strong><span class=\"spark-text\">：搏击操的动作设计时尚，能够吸引不同年龄层的人参与。</span></li>\n<li><strong><span class=\"spark-text\">减压放松</span></strong><span class=\"spark-text\">：搏击操能够帮助练习者在快节奏的音乐中释放压力，通过模拟搏击动作来达到心理上的放松和身体上的锻炼。</span></li>\n<li><strong><span class=\"spark-text\">适应性强</span></strong><span class=\"spark-text\">：无论是初学者还是有一定基础的健身爱好者，都可以根据自身情况选择合适的难度级别进行练习。</span></li>\n</ul>\n<p><span class=\"spark-text\">总的来说，搏击操是一种全面而富有挑战性的健身方式，它不仅能提升身体素质，还能帮助人们在运动中找到乐趣，释放生活和工作中的压力。如果你正在寻找一种既能锻炼身体又能带来精神愉悦的运动，搏击操无疑是一个不错的选择。</span></p>', '拳套需要自己带', 'http://localhost:7245/images/1707623497895/mda-pft9czi1gwy99hth.mp4', 'http://localhost:7245/images/1707623561988/436436346.jpg', 1, 50.00000);
INSERT INTO `couse` VALUES (2, '2024-04-16 22:08:19', NULL, '动感单车', 1, '<p><strong><span class=\"spark-text\">什么是动感单车课程？</span></strong></p>\n<p><span class=\"spark-text\">动感单车课程是一种室内自行车健身活动，它结合了音乐、运动和社交元素，为参与者提供了一种高强度、充满活力的锻炼体验。通过模拟户外骑行的场景，这种课程旨在提高心肺功能、增强下肢肌肉力量，并帮助参与者燃烧卡路里，达到减肥塑形的目标。</span></p>\n<p><strong><span class=\"spark-text\">课程特点</span></strong></p>\n<ol>\n<li><strong><span class=\"spark-text\">音乐驱动</span></strong><span class=\"spark-text\">：动感单车课程通常伴随着精心挑选的音乐，这些音乐节奏感强烈，能够帮助参与者保持高昂的运动热情，让锻炼变得更加有趣。</span></li>\n<li><strong><span class=\"spark-text\">高强度间歇训练</span></strong><span class=\"spark-text\">：课程中会有不同的速度和阻力级别，以及模拟上坡、下坡等不同骑行场景的间歇训练，这有助于提高心肺功能和增强肌肉力量。</span></li>\n<li><strong><span class=\"spark-text\">适合所有水平</span></strong><span class=\"spark-text\">：无论是初学者还是有经验的骑行者，都可以根据自己的能力调整自行车的阻力，参与课程。教练会提供不同的训练建议，确保每个人都能得到适合自己的锻炼。</span></li>\n<li><strong><span class=\"spark-text\">社交互动</span></strong><span class=\"spark-text\">：作为一项团体课程，动感单车也是结交健身伙伴的好机会。在课程中，你可以和其他参与者一起努力，分享彼此的进步和成就。</span></li>\n<li><strong><span class=\"spark-text\">低成本</span></strong><span class=\"spark-text\">：相比购买真正的自行车和外出骑行，动感单车课程通常成本较低，且不受天气影响。</span></li>\n</ol>\n<p><strong><span class=\"spark-text\">选择动感单车课程的好处</span></strong></p>\n<ol>\n<li>\n<p><strong><span class=\"spark-text\">全身锻炼</span></strong><span class=\"spark-text\">：动感单车课程能够锻炼到全身各个部位的肌肉，特别是腿部、臀部和核心肌群。</span></p>\n</li>\n<li>\n<p><strong><span class=\"spark-text\">提高心肺功能</span></strong><span class=\"spark-text\">：有氧运动是提高心肺功能的有效方式，而动感单车课程正好提供了这样的锻炼机会。</span></p>\n</li>\n<li>\n<p><strong><span class=\"spark-text\">燃烧卡路里</span></strong><span class=\"spark-text\">：高强度的锻炼有助于燃烧更多的卡路里，对于减肥和塑形非常有帮助。</span></p>\n</li>\n<li>\n<p><strong><span class=\"spark-text\">增强免疫力</span></strong><span class=\"spark-text\">：适度的运动可以提高人体的免疫力，减少生病的风险。</span></p>\n</li>\n<li>\n<p><strong><span class=\"spark-text\">社交互动</span></strong><span class=\"spark-text\">：在课程中，你可以认识新朋友，共同分享健身的乐趣和成果。</span></p>\n</li>\n</ol>\n<p><strong><span class=\"spark-text\">注意事项</span></strong></p>\n<ol>\n<li>\n<p><strong><span class=\"spark-text\">穿着适当</span></strong><span class=\"spark-text\">：参加动感单车课程时，应穿着舒适、透气的运动服装，以及适合室内骑行的运动鞋。</span></p>\n</li>\n<li>\n<p><strong><span class=\"spark-text\">保持正确姿势</span></strong><span class=\"spark-text\">：正确的骑行姿势可以避免受伤，并确保锻炼效果。如果不确定自己的姿势是否正确，可以请教教练。</span></p>\n</li>\n<li>\n<p><strong><span class=\"spark-text\">适量饮水</span></strong><span class=\"spark-text\">：在锻炼过程中，适量补充水分是非常重要的，以防止脱水和中暑。</span></p>\n</li>\n<li>\n<p><strong><span class=\"spark-text\">听从教练指导</span></strong><span class=\"spark-text\">：为了确保安全和获得最佳锻炼效果，应始终听从教练的指导和建议。</span></p>\n</li>\n</ol>\n<p><span class=\"spark-text\">总的来说，动感单车课程是一种既能锻炼身体又能享受乐趣的运动方式。无论你是想要提高心肺功能、增强肌肉力量还是减肥塑形，都可以从这种课程中获得益处。</span></p>', '穿着适当、保持正确姿势', 'http://localhost:7245/images/1707882814730/mda-pft9czi1gwy99hth (1).mp4', 'http://localhost:7245/images/1707882673828/23625236.jpg', 1, 10.00000);
INSERT INTO `couse` VALUES (3, '2024-04-13 22:08:19', NULL, '全民瑜伽', 3, '<p><span class=\"spark-text\">瑜伽课程是一种深受大众喜爱的健身活动，它结合了体式练习、呼吸控制和冥想等元素，旨在促进身心健康的和谐发展。通过参加瑜伽课程，参与者不仅能够提高身体的柔韧性、增强肌肉力量，还能有效缓解压力、提升精神状态。</span></p>\n<p><span class=\"spark-text\">瑜伽课程通常包括热身、主要体式练习、深度拉伸和冥想等环节。在热身环节中，教练会引导学员进行简单的动作，以唤醒身体、活跃关节。随后，主要体式练习是课程的核心部分，包括各种站立式、坐姿式、倒立式等动作，这些体式能够帮助塑造身体线条、提高平衡能力。深度拉伸环节则有助于放松紧张的肌肉、提高柔韧性。最后，在冥想环节中，学员在教练的引导下进行呼吸调整和心灵放松，达到平静内心、净化思绪的效果。</span></p>\n<p><span class=\"spark-text\">瑜伽课程适合不同年龄、性别和身体状况的人群参加。无论是初学者还是有经验的瑜伽爱好者，都可以在课程中找到适合自己的练习方式。此外，瑜伽课程还强调呼吸与动作的结合，帮助学员更好地感受身体的变化、掌握运动的节奏。</span></p>\n<p><span class=\"spark-text\">总的来说，瑜伽课程是一种全面的身心锻炼方式，它能够帮助我们保持身体活力、提升心灵平静。通过坚持练习瑜伽，我们可以在塑造健康身体的同时，培养出一种积极向上的生活态度。</span></p>', '穿着舒适、宽松的运动服装，以便自由移动', 'http://localhost:7245/images/1707882903789/mda-pft9czi1gwy99hth (1).mp4', 'http://localhost:7245/images/1707882887284/125346436125.jpg', 1, 10.00000);
INSERT INTO `couse` VALUES (4, '2024-04-12 22:08:19', NULL, '有氧操', 1, '<p>有氧操是一种传统的有氧运动课程，它包括踏步、跳跃、伸展等动作，旨在提高心肺功能和全身肌肉力量。</p>', '提前拉伸', 'http://localhost:7245/images/1707884402884/12536436326.jpg', 'http://localhost:7245/images/1707884394260/12536436326.jpg', 1, 30.00000);
INSERT INTO `couse` VALUES (5, '2024-04-13 22:08:19', NULL, '踏板有氧', 1, '<p>踏板有氧使用特制的踏板，通过上下踏板的动作来完成有氧运动。这种课程能够有效锻炼下肢肌肉，提高心肺功能。</p>', '无', 'http://localhost:7245/images/1707884416809/12536436326.jpg', 'http://localhost:7245/images/1707884438665/4326325125.jpg', 1, 55.00000);
INSERT INTO `couse` VALUES (6, '2024-04-13 22:08:19', NULL, '传统HIIT', 6, '<p>通常包括一系列快速、高强度的全身运动，如跳跃、蹲起、俯卧撑等，之后是低强度的恢复期或完全休息。</p>', '在参加HIIT课程之前，最好进行身体检查，确保自己身体状况适合参与高强度训练', 'http://localhost:7245/images/1707884806186/mda-pft9czi1gwy99hth (1).mp4', 'http://localhost:7245/images/1707884794063/63255.jpg', 1, 15.00000);

-- ----------------------------
-- Table structure for couseappointrecord
-- ----------------------------
DROP TABLE IF EXISTS `couseappointrecord`;
CREATE TABLE `couseappointrecord`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '课程预约记录主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `CousePlanId` int NULL DEFAULT NULL COMMENT '关联安排',
  `BeginTime` timestamp NULL DEFAULT NULL COMMENT '开始时间',
  `EndTime` timestamp NULL DEFAULT NULL COMMENT '结束时间',
  `UserId` int NULL DEFAULT NULL COMMENT '报名人',
  `CouseId` int NULL DEFAULT NULL COMMENT '课程',
  `CoachUserId` int NULL DEFAULT NULL COMMENT '教练',
  `CouseAppointStatus` int NULL DEFAULT NULL COMMENT '报名状态枚举',
  `CheckTime` timestamp NULL DEFAULT NULL COMMENT '签到时间',
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `CreatorId`(`CreatorId` ASC) USING BTREE,
  INDEX `CousePlanId`(`CousePlanId` ASC) USING BTREE,
  INDEX `UserId`(`UserId` ASC) USING BTREE,
  INDEX `CoachUserId`(`CoachUserId` ASC) USING BTREE,
  INDEX `CouseId`(`CouseId` ASC) USING BTREE,
  CONSTRAINT `couseappointrecord_ibfk_1` FOREIGN KEY (`CreatorId`) REFERENCES `appuser` (`Id`) ON DELETE SET NULL ON UPDATE RESTRICT,
  CONSTRAINT `couseappointrecord_ibfk_2` FOREIGN KEY (`CousePlanId`) REFERENCES `couseplan` (`Id`) ON DELETE SET NULL ON UPDATE RESTRICT,
  CONSTRAINT `couseappointrecord_ibfk_3` FOREIGN KEY (`UserId`) REFERENCES `appuser` (`Id`) ON DELETE SET NULL ON UPDATE RESTRICT,
  CONSTRAINT `couseappointrecord_ibfk_4` FOREIGN KEY (`CoachUserId`) REFERENCES `appuser` (`Id`) ON DELETE SET NULL ON UPDATE RESTRICT,
  CONSTRAINT `couseappointrecord_ibfk_5` FOREIGN KEY (`CouseId`) REFERENCES `couse` (`Id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of couseappointrecord
-- ----------------------------
INSERT INTO `couseappointrecord` VALUES (10, '2024-04-16 22:40:31', 10, 133, '2024-04-23 20:00:00', '2024-04-23 22:00:00', 10, 2, 7, 2, NULL);
INSERT INTO `couseappointrecord` VALUES (11, '2024-04-16 22:41:04', 10, 128, '2024-04-18 20:00:00', '2024-04-18 22:00:00', 10, 2, 7, 4, '2024-04-16 22:41:46');

-- ----------------------------
-- Table structure for couseplan
-- ----------------------------
DROP TABLE IF EXISTS `couseplan`;
CREATE TABLE `couseplan`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '课程安排主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `CouseId` int NULL DEFAULT NULL COMMENT '课程',
  `BeginTime` timestamp NULL DEFAULT NULL COMMENT '开始时间',
  `EndTime` timestamp NULL DEFAULT NULL COMMENT '结束时间',
  `MaxParticipants` int NULL DEFAULT NULL COMMENT '最大报名人数',
  `CurrentEnrollments` int NULL DEFAULT NULL COMMENT '当前报名人数',
  `Location` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '上课地点',
  `Notes` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `CoachUserId` int NULL DEFAULT NULL COMMENT '教练',
  `CardId` int NULL DEFAULT NULL COMMENT '要求会员卡',
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `CreatorId`(`CreatorId` ASC) USING BTREE,
  INDEX `CardId`(`CardId` ASC) USING BTREE,
  INDEX `CouseId`(`CouseId` ASC) USING BTREE,
  INDEX `CoachUserId`(`CoachUserId` ASC) USING BTREE,
  CONSTRAINT `couseplan_ibfk_1` FOREIGN KEY (`CreatorId`) REFERENCES `appuser` (`Id`) ON DELETE SET NULL ON UPDATE RESTRICT,
  CONSTRAINT `couseplan_ibfk_2` FOREIGN KEY (`CardId`) REFERENCES `card` (`Id`) ON DELETE SET NULL ON UPDATE RESTRICT,
  CONSTRAINT `couseplan_ibfk_3` FOREIGN KEY (`CouseId`) REFERENCES `couse` (`Id`) ON DELETE SET NULL ON UPDATE RESTRICT,
  CONSTRAINT `couseplan_ibfk_4` FOREIGN KEY (`CoachUserId`) REFERENCES `appuser` (`Id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 148 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of couseplan
-- ----------------------------
INSERT INTO `couseplan` VALUES (97, '2024-04-16 22:39:04', NULL, 2, '2024-04-16 18:00:00', '2024-04-16 22:00:00', 10, 0, '源泉健身房A区', '记得自带水', 6, 7);
INSERT INTO `couseplan` VALUES (98, '2024-04-16 22:39:04', NULL, 2, '2024-04-17 18:00:00', '2024-04-17 22:00:00', 10, 0, '源泉健身房A区', '记得自带水', 6, 7);
INSERT INTO `couseplan` VALUES (99, '2024-04-16 22:39:04', NULL, 2, '2024-04-18 18:00:00', '2024-04-18 22:00:00', 10, 0, '源泉健身房A区', '记得自带水', 6, 7);
INSERT INTO `couseplan` VALUES (100, '2024-04-16 22:39:04', NULL, 2, '2024-04-19 18:00:00', '2024-04-19 22:00:00', 10, 0, '源泉健身房A区', '记得自带水', 6, 7);
INSERT INTO `couseplan` VALUES (101, '2024-04-16 22:39:04', NULL, 2, '2024-04-20 18:00:00', '2024-04-20 22:00:00', 10, 0, '源泉健身房A区', '记得自带水', 6, 7);
INSERT INTO `couseplan` VALUES (102, '2024-04-16 22:39:04', NULL, 2, '2024-04-21 18:00:00', '2024-04-21 22:00:00', 10, 0, '源泉健身房A区', '记得自带水', 6, 7);
INSERT INTO `couseplan` VALUES (103, '2024-04-16 22:39:04', NULL, 2, '2024-04-22 18:00:00', '2024-04-22 22:00:00', 10, 0, '源泉健身房A区', '记得自带水', 6, 7);
INSERT INTO `couseplan` VALUES (104, '2024-04-16 22:39:04', NULL, 2, '2024-04-23 18:00:00', '2024-04-23 22:00:00', 10, 0, '源泉健身房A区', '记得自带水', 6, 7);
INSERT INTO `couseplan` VALUES (105, '2024-04-16 22:39:04', NULL, 2, '2024-04-24 18:00:00', '2024-04-24 22:00:00', 10, 0, '源泉健身房A区', '记得自带水', 6, 7);
INSERT INTO `couseplan` VALUES (106, '2024-04-16 22:39:04', NULL, 2, '2024-04-25 18:00:00', '2024-04-25 22:00:00', 10, 0, '源泉健身房A区', '记得自带水', 6, 7);
INSERT INTO `couseplan` VALUES (107, '2024-04-16 22:39:04', NULL, 2, '2024-04-26 18:00:00', '2024-04-26 22:00:00', 10, 0, '源泉健身房A区', '记得自带水', 6, 7);
INSERT INTO `couseplan` VALUES (108, '2024-04-16 22:39:04', NULL, 2, '2024-04-27 18:00:00', '2024-04-27 22:00:00', 10, 0, '源泉健身房A区', '记得自带水', 6, 7);
INSERT INTO `couseplan` VALUES (109, '2024-04-16 22:39:04', NULL, 2, '2024-04-28 18:00:00', '2024-04-28 22:00:00', 10, 0, '源泉健身房A区', '记得自带水', 6, 7);
INSERT INTO `couseplan` VALUES (110, '2024-04-16 22:39:04', NULL, 2, '2024-04-29 18:00:00', '2024-04-29 22:00:00', 10, 0, '源泉健身房A区', '记得自带水', 6, 7);
INSERT INTO `couseplan` VALUES (111, '2024-04-16 22:39:04', NULL, 2, '2024-04-30 18:00:00', '2024-04-30 22:00:00', 10, 0, '源泉健身房A区', '记得自带水', 6, 7);
INSERT INTO `couseplan` VALUES (112, '2024-04-16 22:39:04', NULL, 2, '2024-05-01 18:00:00', '2024-05-01 22:00:00', 10, 0, '源泉健身房A区', '记得自带水', 6, 7);
INSERT INTO `couseplan` VALUES (113, '2024-04-16 22:39:04', NULL, 2, '2024-05-02 18:00:00', '2024-05-02 22:00:00', 10, 0, '源泉健身房A区', '记得自带水', 6, 7);
INSERT INTO `couseplan` VALUES (114, '2024-04-16 22:39:04', NULL, 2, '2024-05-03 18:00:00', '2024-05-03 22:00:00', 10, 0, '源泉健身房A区', '记得自带水', 6, 7);
INSERT INTO `couseplan` VALUES (115, '2024-04-16 22:39:04', NULL, 2, '2024-05-04 18:00:00', '2024-05-04 22:00:00', 10, 0, '源泉健身房A区', '记得自带水', 6, 7);
INSERT INTO `couseplan` VALUES (116, '2024-04-16 22:39:04', NULL, 2, '2024-05-05 18:00:00', '2024-05-05 22:00:00', 10, 0, '源泉健身房A区', '记得自带水', 6, 7);
INSERT INTO `couseplan` VALUES (117, '2024-04-16 22:39:04', NULL, 2, '2024-05-06 18:00:00', '2024-05-06 22:00:00', 10, 0, '源泉健身房A区', '记得自带水', 6, 7);
INSERT INTO `couseplan` VALUES (118, '2024-04-16 22:39:04', NULL, 2, '2024-05-07 18:00:00', '2024-05-07 22:00:00', 10, 0, '源泉健身房A区', '记得自带水', 6, 7);
INSERT INTO `couseplan` VALUES (119, '2024-04-16 22:39:04', NULL, 2, '2024-05-08 18:00:00', '2024-05-08 22:00:00', 10, 0, '源泉健身房A区', '记得自带水', 6, 7);
INSERT INTO `couseplan` VALUES (120, '2024-04-16 22:39:04', NULL, 2, '2024-05-09 18:00:00', '2024-05-09 22:00:00', 10, 0, '源泉健身房A区', '记得自带水', 6, 7);
INSERT INTO `couseplan` VALUES (121, '2024-04-16 22:39:04', NULL, 2, '2024-05-10 18:00:00', '2024-05-10 22:00:00', 10, 0, '源泉健身房A区', '记得自带水', 6, 7);
INSERT INTO `couseplan` VALUES (122, '2024-04-16 22:39:04', NULL, 2, '2024-05-11 18:00:00', '2024-05-11 22:00:00', 10, 0, '源泉健身房A区', '记得自带水', 6, 7);
INSERT INTO `couseplan` VALUES (123, '2024-04-16 22:39:04', NULL, 2, '2024-05-12 18:00:00', '2024-05-12 22:00:00', 10, 0, '源泉健身房A区', '记得自带水', 6, 7);
INSERT INTO `couseplan` VALUES (124, '2024-04-16 22:39:04', NULL, 2, '2024-05-13 18:00:00', '2024-05-13 22:00:00', 10, 0, '源泉健身房A区', '记得自带水', 6, 7);
INSERT INTO `couseplan` VALUES (125, '2024-04-16 22:39:04', NULL, 2, '2024-05-14 18:00:00', '2024-05-14 22:00:00', 10, 0, '源泉健身房A区', '记得自带水', 6, 7);
INSERT INTO `couseplan` VALUES (126, '2024-04-16 22:40:15', NULL, 2, '2024-04-16 20:00:00', '2024-04-16 22:00:00', 10, 0, '健身房B区', '自带矿泉水', 7, 3);
INSERT INTO `couseplan` VALUES (127, '2024-04-16 22:40:15', NULL, 2, '2024-04-17 20:00:00', '2024-04-17 22:00:00', 10, 0, '健身房B区', '自带矿泉水', 7, 3);
INSERT INTO `couseplan` VALUES (128, '2024-04-16 22:40:15', NULL, 2, '2024-04-18 20:00:00', '2024-04-18 22:00:00', 10, 1, '健身房B区', '自带矿泉水', 7, 3);
INSERT INTO `couseplan` VALUES (129, '2024-04-16 22:40:15', NULL, 2, '2024-04-19 20:00:00', '2024-04-19 22:00:00', 10, 0, '健身房B区', '自带矿泉水', 7, 3);
INSERT INTO `couseplan` VALUES (130, '2024-04-16 22:40:15', NULL, 2, '2024-04-20 20:00:00', '2024-04-20 22:00:00', 10, 0, '健身房B区', '自带矿泉水', 7, 3);
INSERT INTO `couseplan` VALUES (131, '2024-04-16 22:40:15', NULL, 2, '2024-04-21 20:00:00', '2024-04-21 22:00:00', 10, 0, '健身房B区', '自带矿泉水', 7, 3);
INSERT INTO `couseplan` VALUES (132, '2024-04-16 22:40:15', NULL, 2, '2024-04-22 20:00:00', '2024-04-22 22:00:00', 10, 0, '健身房B区', '自带矿泉水', 7, 3);
INSERT INTO `couseplan` VALUES (133, '2024-04-16 22:40:15', NULL, 2, '2024-04-23 20:00:00', '2024-04-23 22:00:00', 10, 0, '健身房B区', '自带矿泉水', 7, 3);
INSERT INTO `couseplan` VALUES (134, '2024-04-16 22:40:15', NULL, 2, '2024-04-24 20:00:00', '2024-04-24 22:00:00', 10, 0, '健身房B区', '自带矿泉水', 7, 3);
INSERT INTO `couseplan` VALUES (135, '2024-04-16 22:40:15', NULL, 2, '2024-04-25 20:00:00', '2024-04-25 22:00:00', 10, 0, '健身房B区', '自带矿泉水', 7, 3);
INSERT INTO `couseplan` VALUES (136, '2024-04-16 22:40:15', NULL, 2, '2024-04-26 20:00:00', '2024-04-26 22:00:00', 10, 0, '健身房B区', '自带矿泉水', 7, 3);
INSERT INTO `couseplan` VALUES (137, '2024-04-16 22:40:15', NULL, 2, '2024-04-27 20:00:00', '2024-04-27 22:00:00', 10, 0, '健身房B区', '自带矿泉水', 7, 3);
INSERT INTO `couseplan` VALUES (138, '2024-04-16 22:40:15', NULL, 2, '2024-04-28 20:00:00', '2024-04-28 22:00:00', 10, 0, '健身房B区', '自带矿泉水', 7, 3);
INSERT INTO `couseplan` VALUES (139, '2024-04-16 22:40:15', NULL, 2, '2024-04-29 20:00:00', '2024-04-29 22:00:00', 10, 0, '健身房B区', '自带矿泉水', 7, 3);
INSERT INTO `couseplan` VALUES (140, '2024-04-16 22:40:15', NULL, 2, '2024-04-30 20:00:00', '2024-04-30 22:00:00', 10, 0, '健身房B区', '自带矿泉水', 7, 3);
INSERT INTO `couseplan` VALUES (141, '2024-04-16 22:40:15', NULL, 2, '2024-05-01 20:00:00', '2024-05-01 22:00:00', 10, 0, '健身房B区', '自带矿泉水', 7, 3);
INSERT INTO `couseplan` VALUES (142, '2024-04-16 22:40:15', NULL, 2, '2024-05-02 20:00:00', '2024-05-02 22:00:00', 10, 0, '健身房B区', '自带矿泉水', 7, 3);
INSERT INTO `couseplan` VALUES (143, '2024-04-16 22:40:15', NULL, 2, '2024-05-03 20:00:00', '2024-05-03 22:00:00', 10, 0, '健身房B区', '自带矿泉水', 7, 3);
INSERT INTO `couseplan` VALUES (144, '2024-04-16 22:40:15', NULL, 2, '2024-05-04 20:00:00', '2024-05-04 22:00:00', 10, 0, '健身房B区', '自带矿泉水', 7, 3);
INSERT INTO `couseplan` VALUES (145, '2024-04-16 22:40:15', NULL, 2, '2024-05-05 20:00:00', '2024-05-05 22:00:00', 10, 0, '健身房B区', '自带矿泉水', 7, 3);
INSERT INTO `couseplan` VALUES (146, '2024-04-16 22:40:15', NULL, 2, '2024-05-06 20:00:00', '2024-05-06 22:00:00', 10, 0, '健身房B区', '自带矿泉水', 7, 3);
INSERT INTO `couseplan` VALUES (147, '2024-04-16 22:40:15', NULL, 2, '2024-05-07 20:00:00', '2024-05-07 22:00:00', 10, 0, '健身房B区', '自带矿泉水', 7, 3);

-- ----------------------------
-- Table structure for cousetype
-- ----------------------------
DROP TABLE IF EXISTS `cousetype`;
CREATE TABLE `cousetype`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '课程类型主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `Name` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `Cover` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '封面',
  `Sort` int NULL DEFAULT NULL COMMENT '显示顺序',
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `CreatorId`(`CreatorId` ASC) USING BTREE,
  CONSTRAINT `cousetype_ibfk_1` FOREIGN KEY (`CreatorId`) REFERENCES `appuser` (`Id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cousetype
-- ----------------------------
INSERT INTO `cousetype` VALUES (1, '2024-04-14 22:08:19', NULL, '有氧运动课程', 'http://localhost:7245/images/1707622865309/5216212.jpg', 1);
INSERT INTO `cousetype` VALUES (2, '2024-04-08 22:08:19', NULL, '力量训练课程', 'http://localhost:7245/images/1707622890563/521632636.jpg', 2);
INSERT INTO `cousetype` VALUES (3, '2024-04-13 22:08:19', NULL, '瑜伽课程', 'http://localhost:7245/images/1707622909446/43643634734.jpg', 3);
INSERT INTO `cousetype` VALUES (4, '2024-04-12 22:08:19', NULL, '普拉提课程', 'http://localhost:7245/images/1707622935752/2643647.jpg', 4);
INSERT INTO `cousetype` VALUES (5, '2024-04-07 22:08:19', NULL, '柔韧性和平衡课程', 'http://localhost:7245/images/1707622959543/23632657.jpg', 5);
INSERT INTO `cousetype` VALUES (6, '2024-04-13 22:08:19', NULL, '高强度间歇训练课程', 'http://localhost:7245/images/1707622986052/45856546.jpg', 6);

-- ----------------------------
-- Table structure for equipment
-- ----------------------------
DROP TABLE IF EXISTS `equipment`;
CREATE TABLE `equipment`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '器材设备主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `No` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '设备编号',
  `Name` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '设备名称',
  `Type` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类型',
  `Brand` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '品牌',
  `PurchaseDate` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '购买日期',
  `EquipmentStatus` int NULL DEFAULT NULL COMMENT '使用状态枚举',
  `Location` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '存放位置',
  `Instructions` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '操作指南',
  `Notes` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `CreatorId`(`CreatorId` ASC) USING BTREE,
  CONSTRAINT `equipment_ibfk_1` FOREIGN KEY (`CreatorId`) REFERENCES `appuser` (`Id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of equipment
-- ----------------------------
INSERT INTO `equipment` VALUES (1, '2024-04-08 22:08:19', NULL, '001', '跑步机', '有氧设备', '舒华', NULL, 0, '有氧区', '请在开始前进行热身，逐渐增加速度至适宜的跑步速度，使用后请慢走降温。', '定期润滑跑带');
INSERT INTO `equipment` VALUES (2, '2024-04-13 22:08:19', NULL, '002', '动感单车', '有氧设备', '飞轮', NULL, 0, '自行车区', '调整座椅和把手以适应身高，使用合适的阻力，保持节奏稳定。', '需要提前预约时段');
INSERT INTO `equipment` VALUES (3, '2024-04-07 22:08:19', NULL, '003', '哑铃', '力量设备', '铁力士', NULL, 0, '自由重量区', '根据训练需求选择重量，注意动作标准，避免伤害。', '每组重量标注清晰');
INSERT INTO `equipment` VALUES (4, '2024-04-08 22:08:19', NULL, '004', '史密斯机', '力量设备', '力王', NULL, 0, '力量区', '请在教练指导下使用，确保安全锁扣。', '待维修，暂时无法使用');
INSERT INTO `equipment` VALUES (5, '2024-04-11 22:08:19', NULL, '005', '瑜伽垫', '柔韧性设备', '平衡点', NULL, 0, '瑜伽/普拉提区', '清洁干净，铺设在指定区域，使用后卷起放回原位。', '提供额外的瑜伽辅助工具');
INSERT INTO `equipment` VALUES (6, '2024-04-15 22:08:19', NULL, '006', '椭圆机', '有氧设备', '速尔', NULL, 3, '有氧区', '双手轻握把手，脚踏踏板，保持稳定的运动节奏。', '心率监测功能正常');

-- ----------------------------
-- Table structure for equipmentrent
-- ----------------------------
DROP TABLE IF EXISTS `equipmentrent`;
CREATE TABLE `equipmentrent`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '器材租借主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `EquipmentId` int NULL DEFAULT NULL COMMENT '设备',
  `UserId` int NULL DEFAULT NULL COMMENT '会员',
  `RentalTime` timestamp NULL DEFAULT NULL COMMENT '租借时间',
  `ReturnTime` timestamp NULL DEFAULT NULL COMMENT '预计归还时间',
  `ActualReturnTime` timestamp NULL DEFAULT NULL COMMENT '实际归还时间',
  `RentalFee` double(20, 5) NULL DEFAULT NULL COMMENT '租界费用',
  `DamageMoney` double(20, 5) NULL DEFAULT NULL COMMENT '损坏赔偿',
  `Notes` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `CreatorId`(`CreatorId` ASC) USING BTREE,
  INDEX `EquipmentId`(`EquipmentId` ASC) USING BTREE,
  INDEX `UserId`(`UserId` ASC) USING BTREE,
  CONSTRAINT `equipmentrent_ibfk_1` FOREIGN KEY (`CreatorId`) REFERENCES `appuser` (`Id`) ON DELETE SET NULL ON UPDATE RESTRICT,
  CONSTRAINT `equipmentrent_ibfk_2` FOREIGN KEY (`EquipmentId`) REFERENCES `equipment` (`Id`) ON DELETE SET NULL ON UPDATE RESTRICT,
  CONSTRAINT `equipmentrent_ibfk_3` FOREIGN KEY (`UserId`) REFERENCES `appuser` (`Id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of equipmentrent
-- ----------------------------
INSERT INTO `equipmentrent` VALUES (1, '2024-04-27 00:42:34', NULL, 6, 5, '2024-05-05 00:42:47', '2024-04-28 00:42:49', '2024-04-29 00:42:57', NULL, 0.00000, '无');
INSERT INTO `equipmentrent` VALUES (2, '2024-04-28 00:42:34', NULL, 6, 5, '2024-05-02 00:42:47', '2024-05-03 00:42:49', '2024-05-04 00:42:57', 0.00000, 0.00000, '没有问题');
INSERT INTO `equipmentrent` VALUES (3, '2024-05-05 00:42:34', NULL, 6, 5, '2024-04-30 00:42:47', '2024-05-06 00:42:49', '2024-04-28 00:42:57', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for leavemessage
-- ----------------------------
DROP TABLE IF EXISTS `leavemessage`;
CREATE TABLE `leavemessage`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '留言主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `UserId` int NULL DEFAULT NULL COMMENT '用户',
  `Content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '留言内容',
  `ReplyContent` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '回复内容',
  `ProcessingStatus` int NULL DEFAULT NULL COMMENT '处理状态',
  `Notes` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `PersonUserId` int NULL DEFAULT NULL COMMENT '负责人',
  `Type` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '留言类型',
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `CreatorId`(`CreatorId` ASC) USING BTREE,
  INDEX `PersonUserId`(`PersonUserId` ASC) USING BTREE,
  INDEX `UserId`(`UserId` ASC) USING BTREE,
  CONSTRAINT `leavemessage_ibfk_1` FOREIGN KEY (`CreatorId`) REFERENCES `appuser` (`Id`) ON DELETE SET NULL ON UPDATE RESTRICT,
  CONSTRAINT `leavemessage_ibfk_2` FOREIGN KEY (`PersonUserId`) REFERENCES `appuser` (`Id`) ON DELETE SET NULL ON UPDATE RESTRICT,
  CONSTRAINT `leavemessage_ibfk_3` FOREIGN KEY (`UserId`) REFERENCES `appuser` (`Id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of leavemessage
-- ----------------------------
INSERT INTO `leavemessage` VALUES (1, '2024-04-16 22:08:19', 5, 5, '<p>我不会使用系统</p>', '<p>我教你</p>', 2, NULL, NULL, '用户建议');

-- ----------------------------
-- Table structure for moneyrecord
-- ----------------------------
DROP TABLE IF EXISTS `moneyrecord`;
CREATE TABLE `moneyrecord`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '余额变动记录主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `UserId` int NULL DEFAULT NULL COMMENT '会员',
  `Amount` double(20, 5) NULL DEFAULT NULL COMMENT '变动金额',
  `Content` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '变动内容',
  `Balance` double(20, 5) NULL DEFAULT NULL COMMENT '余额',
  `TransactionType` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '变动类型',
  `TransactionTime` timestamp NULL DEFAULT NULL COMMENT '变动时间',
  `RelativeId` int NULL DEFAULT NULL COMMENT '关联编号',
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `CreatorId`(`CreatorId` ASC) USING BTREE,
  INDEX `UserId`(`UserId` ASC) USING BTREE,
  CONSTRAINT `moneyrecord_ibfk_1` FOREIGN KEY (`CreatorId`) REFERENCES `appuser` (`Id`) ON DELETE SET NULL ON UPDATE RESTRICT,
  CONSTRAINT `moneyrecord_ibfk_2` FOREIGN KEY (`UserId`) REFERENCES `appuser` (`Id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of moneyrecord
-- ----------------------------
INSERT INTO `moneyrecord` VALUES (15, '2024-04-10 22:08:19', 5, 5, 50.00000, '你通过支付宝充值了50元', 4000.00000, '充值', '2024-04-11 22:33:50', NULL);
INSERT INTO `moneyrecord` VALUES (16, '2024-04-13 22:08:19', 5, 5, 1000.00000, '你通过支付宝充值了1000元', 1050.00000, '充值', '2024-04-11 22:34:13', NULL);
INSERT INTO `moneyrecord` VALUES (17, '2024-04-11 22:08:19', 5, 5, 10000.00000, '你通过云闪付充值了10000元', 11050.00000, '充值', '2024-04-11 22:34:16', NULL);
INSERT INTO `moneyrecord` VALUES (18, '2024-04-07 22:08:19', 5, 5, 3000.00000, '你通过微信支付充值了3000元', 14050.00000, '充值', '2024-04-11 22:34:18', NULL);
INSERT INTO `moneyrecord` VALUES (19, '2024-04-08 22:08:19', 5, 5, 50.00000, '你通过银联充值了50元', 14100.00000, '充值', '2024-04-11 22:35:03', NULL);
INSERT INTO `moneyrecord` VALUES (20, '2024-04-10 22:08:19', 5, 5, 50.00000, '你通过银联充值了50元', 14150.00000, '充值', '2024-04-11 22:35:41', NULL);
INSERT INTO `moneyrecord` VALUES (22, '2024-04-11 22:08:19', 5, 5, 500.00000, '你预约了搏击操课程花费金额500.0', 13150.00000, '消费', '2024-04-12 12:14:14', NULL);
INSERT INTO `moneyrecord` VALUES (23, '2024-04-16 22:08:19', 5, 5, 5.00000, '你预约了搏击操课程花费金额5.0', 13145.00000, '消费', '2024-04-12 12:38:57', 8);
INSERT INTO `moneyrecord` VALUES (24, '2024-04-13 22:08:19', 5, 5, 5.00000, '你取消了搏击操课程退款金额5.0', 13150.00000, '退款', '2024-04-12 12:39:11', 8);
INSERT INTO `moneyrecord` VALUES (25, '2024-04-10 22:08:19', 9, 9, 1000.00000, '你通过支付宝充值了1000元', 1000.00000, '充值', '2024-04-14 10:36:19', NULL);
INSERT INTO `moneyrecord` VALUES (26, '2024-04-14 22:08:19', 9, 9, 50.00000, '你通过支付宝充值了50元', 1050.00000, '充值', '2024-04-14 10:38:02', NULL);
INSERT INTO `moneyrecord` VALUES (27, '2024-04-15 22:08:19', 9, 9, 100.00000, '你通过银联充值了100元', 1150.00000, '充值', '2024-04-14 10:38:14', NULL);
INSERT INTO `moneyrecord` VALUES (28, '2024-04-14 22:08:19', 9, 9, 9.00000, '你预约了动感单车课程花费金额9.0', 1141.00000, '消费', '2024-04-14 12:21:42', 9);
INSERT INTO `moneyrecord` VALUES (29, '2024-04-16 22:35:31', 10, 10, 50.00000, '你通过支付宝充值了50元', 50.00000, '充值', '2024-04-16 22:35:31', NULL);
INSERT INTO `moneyrecord` VALUES (30, '2024-04-16 22:35:43', 10, 10, 500.00000, '你通过支付宝充值了500元', 550.00000, '充值', '2024-04-16 22:35:43', NULL);
INSERT INTO `moneyrecord` VALUES (31, '2024-04-16 22:36:03', 10, 10, 10000.00000, '你通过云闪付充值了10000元', 10550.00000, '充值', '2024-04-16 22:36:03', NULL);
INSERT INTO `moneyrecord` VALUES (32, '2024-04-16 22:40:31', 10, 10, 8.50000, '你预约了动感单车课程花费金额8.5', 10541.50000, '消费', '2024-04-16 22:40:31', 10);
INSERT INTO `moneyrecord` VALUES (33, '2024-04-16 22:40:47', 10, 10, 8.50000, '你取消了动感单车课程退款金额8.5', 10550.00000, '退款', '2024-04-16 22:40:47', 10);
INSERT INTO `moneyrecord` VALUES (34, '2024-04-16 22:41:04', 10, 10, 8.50000, '你预约了动感单车课程花费金额8.5', 10541.50000, '消费', '2024-04-16 22:41:04', 11);
INSERT INTO `moneyrecord` VALUES (35, '2025-01-10 10:50:04', 12, 12, 500.00000, '你通过支付宝充值了500元', 500.00000, '充值', '2025-01-10 10:50:04', NULL);
INSERT INTO `moneyrecord` VALUES (36, '2025-01-10 16:59:49', 11, 11, 500.00000, '你通过银联充值了500元', 500.00000, '充值', '2025-01-10 16:59:49', NULL);
INSERT INTO `moneyrecord` VALUES (37, '2025-02-11 09:03:55', 11, 11, 3000.00000, '你通过支付宝充值了3000元', 3500.00000, '充值', '2025-02-11 09:03:55', NULL);

-- ----------------------------
-- Table structure for sysnotice
-- ----------------------------
DROP TABLE IF EXISTS `sysnotice`;
CREATE TABLE `sysnotice`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '系统通知主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `Name` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通知标题',
  `Author` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '作者',
  `Content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '通知内容',
  `File` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '附件',
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `CreatorId`(`CreatorId` ASC) USING BTREE,
  CONSTRAINT `sysnotice_ibfk_1` FOREIGN KEY (`CreatorId`) REFERENCES `appuser` (`Id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sysnotice
-- ----------------------------
INSERT INTO `sysnotice` VALUES (1, '2024-04-11 22:08:19', NULL, '运动源泉系统健身房开业通知', '官方运营', '<p><strong><span class=\"spark-text\">运动源泉系统健身房开业通知</span></strong></p>\n<p><span class=\"spark-text\">尊敬的健身爱好者们，</span></p>\n<p><span class=\"spark-text\">我们激动地宣布，全新的健身体验场所&mdash;&mdash;</span><strong><span class=\"spark-text\">运动源泉系统健身房</span></strong><span class=\"spark-text\">即将开业！经过精心筹备，我们将为您带来最先进的设备、最专业的教练团队以及最舒适的健身环境。无论您是健身初学者还是资深健身者，我们都将为您提供量身定制的健身方案，帮助您达到理想的健康目标。</span></p>\n<p><span class=\"spark-text\">以下是开业相关详情：</span></p>\n<ul>\n<li><strong><span class=\"spark-text\">开业日期</span></strong><span class=\"spark-text\">：[具体日期]</span></li>\n<li><strong><span class=\"spark-text\">时间</span></strong><span class=\"spark-text\">：上午9:00 - 晚上10:00</span></li>\n<li><strong><span class=\"spark-text\">地点</span></strong><span class=\"spark-text\">：[健身房详细地址]</span></li>\n<li><strong><span class=\"spark-text\">庆祝活动</span></strong><span class=\"spark-text\">：开业当天前100名到场会员将享有免费体验券一张，并有机会赢取年卡会员资格。</span></li>\n<li><strong><span class=\"spark-text\">特色服务</span></strong><span class=\"spark-text\">：私人教练一对一指导、团体课程、营养餐饮供应、健康评估与咨询等。</span></li>\n</ul>\n<p><span class=\"spark-text\">我们的教练团队已经准备就绪，期待与您一起汗水飞溅，共同见证每一次超越自我的瞬间。请携带您的运动装备，加入我们的健身行列，享受运动带来的无限活力和乐趣！</span></p>\n<p><span class=\"spark-text\">为了确保您能获得最佳的健身体验，请提前通过以下方式预约参观或咨询：</span></p>\n<ul>\n<li><strong><span class=\"spark-text\">联系电话</span></strong><span class=\"spark-text\">：[健身房电话]</span></li>\n<li><strong><span class=\"spark-text\">电子邮箱</span></strong><span class=\"spark-text\">：[健身房邮箱]</span></li>\n<li><strong><span class=\"spark-text\">官方网站</span></strong><span class=\"spark-text\">：[健身房网站]</span></li>\n<li><strong><span class=\"spark-text\">社交媒体</span></strong><span class=\"spark-text\">：关注我们的微博/微信/Instagram等，获取最新动态。</span></li>\n</ul>\n<p><span class=\"spark-text\">再次感谢您对运动源泉系统健身房的关注和支持，期待在开业日与您相见！</span></p>\n<p><span class=\"spark-text\">健康生活，从运动源泉开始。</span></p>\n<p><strong><span class=\"spark-text\">运动源泉系统健身房团队</span></strong></p>\n<p>&nbsp;</p>', NULL);
INSERT INTO `sysnotice` VALUES (2, '2025-02-10 16:18:36', NULL, 'Plumsports 健身房开业通知', '官方运营', '<p><strong>Plumsports 健身房开业通知</strong></p>\n<p>尊敬的健身爱好者，</p>\n<p>我们怀着激动的心情宣布，全新的健身体验场所&mdash;&mdash;Plumsports<strong>健身房</strong>即将盛大开业！经过拟定，我们将为您带来最先进的健身设备、最专业的教练团队以及最舒适的健身环境。无论是健身初学者还是资深健身达人，我们都将为您提供更加定制化的健身方案，帮助您实现理想的健康目标。</p>\n<h3>开业详情：</h3>\n<ul>\n<li><strong>开业日期</strong>： [具体日期]</li>\n<li><strong>时间</strong>： 上午9:00 - 晚上10:00</li>\n<li><strong>地址</strong>： [健身房详细地址]</li>\n<li><strong>开业活动</strong>：开业当天前100名到场会员即可领取<strong>免费体验券</strong>一张，并有机会赢取<strong>年度会员资格</strong>！</li>\n<li><strong>特色服务</strong>：\n<ul>\n<li>私人教练一对一指导</li>\n<li>团体课程</li>\n<li>营养餐饮供应</li>\n<li>人工智能与机器人</li>\n</ul>\n</li>\n</ul>\n<p>我们的专业教练团队已经做好准备，期待与您一起挑战，共同见证自我每一次超越极限的时刻！请携带您的运动装备，加入我们的健身行列，享受运动带来的无限活力和乐趣！</p>\n<h3>预约与咨询：</h3>\n<p>为确保您获得最佳的健身体验，请提前通过以下方式预约参观或咨询：</p>\n<ul>\n<li><strong>联系电话</strong>： 15216090312</li>\n<li><strong>电子邮箱</strong>： 2731626567qq.com</li>\n<li><strong>官方网站</strong>： plumsports.com</li>\n<li><strong>社交媒体</strong>：关注我们的<strong>微信/微博/Instagram</strong>，获取最新动态。</li>\n</ul>\n<p>感谢您对<strong>Plumsports健身房</strong>的关注与支持，期待在开业之日与您相见！</p>\n<p><strong>健康生活，从梅花运动开始。</strong></p>\n<p><strong>Plumsports 健身房团队</strong></p>', 'http://localhost:7245/images/1739175697485/abbf784897c2f848c176de92fcdb4fd.png');
INSERT INTO `sysnotice` VALUES (3, '2025-02-10 16:24:48', NULL, 'Plumsports健身房开业庆典公告', '官方运营', '<h2><strong><span> Plumsports健身房开业庆典公告</span></strong></h2>\n<p><span>尊敬的健身爱好者，</span></p>\n<p><span>我们怀着激动万分的心情宣布：</span><strong><span>Plumsports健身房</span></strong><span>即将盛大开业！作为最新、最具创新性的健身场馆，我们致力于为每位会员提供最优质的健身体验。</span></p>\n<h3><strong><span>开业活动详情：</span></strong></h3>\n<p><span>📅</span><strong><span>日期</span></strong><span>： [具体日期] </span><br /><span>🕘</span><strong><span>时间</span></strong><span>： 9:00 - 晚上10:00 </span><br /><span>📍</span><strong><span>地址</span></strong><span>： [健身房详细地址] </span><br /><span>🎉</span><strong><span>开业福利</span></strong><span>：</span></p>\n<ul>\n<li><span>前100名到场会员可领取</span><strong><span>免费体验券</span></strong></li>\n<li><span>抽奖活动，赢取</span><strong><span>半年/全年会员资格</span></strong></li>\n<li><span>现场报名可享</span><strong><span>限时折扣</span></strong></li>\n</ul>\n<p><span>💪</span><strong><span>特色服务</span></strong><span>：</span></p>\n<ul>\n<li><span>高端健身器械和智能训练系统</span></li>\n<li><span>专业私人教练模特一指导</span></li>\n<li><span>瑜伽团体课程（瑜伽、普拉提、搏击、HIIT等）</span></li>\n<li><span>健身计划定制和营养服务餐饮</span></li>\n</ul>\n<p><span>📞 </span><strong><span>&amp; 咨询</span></strong></p>\n<ul>\n<li><strong><span>联系电话</span></strong><span>： 15216090312</span></li>\n<li><strong><span>官方网站</span></strong><span>： plumsports.com</span></li>\n<li><strong><span>社交媒体</span></strong><span>： 微信 / 微博 / Instagram</span></li>\n</ul>\n<p><span>让我们一起迈向健康新生活！Plumsports健身房，等你来挑战！</span></p>', NULL);
INSERT INTO `sysnotice` VALUES (4, '2025-02-10 16:25:36', NULL, 'Plumsports会员福利升级公告', '官方策划', '<h2><strong><span>Plumsports会员福利升级公告</span></strong></h2>\n<p><span>亲爱的Plumsports会员：</span></p>\n<p><span>为了感谢大家对Plumsports健身房的支持，我们特别推出</span><strong><span>全新会员福利升级计划</span></strong><span>！</span></p>\n<p><span>🎁</span><strong><span>福利详情：</span></strong></p>\n<ol>\n<li><strong><span>老会员专享</span></strong><span>：凡已入会6个月以上的会员，可免费领取1次</span><strong><span>私人教练课程</span></strong><span>！</span></li>\n<li><strong><span>新会员福利</span></strong><span>：新注册会员即享</span><strong><span>首月8折优惠</span></strong><span>，还有机会赢取健身礼包！</span></li>\n<li><strong><span>邀请好友奖励</span></strong><span>：成功邀请好友加入，可获得</span><strong><span>7天免费训练体验</span></strong><span>！</span></li>\n</ol>\n<p><span>✨</span><strong><span>升级项目：</span></strong></p>\n<ul>\n<li><span>增设智能训练计划，定制您的专属健身方案</span></li>\n<li><span>增加团体课程内容，新增拳击、舞蹈、户外训练等新项目</span></li>\n<li><span>会员专属休息区，全新健康饮品免费供应</span></li>\n</ul>\n<p><span>🏋️&zwj;♂️</span><strong><span>立即加入，享受更多福利！</span></strong><br /><span> 📞 咨询电话：15216090312</span><br /><span>📩 电子邮箱：2731626567@qq.com</span></p>\n<p><span>Plumsports健身房，助你实现更好的自己！</span></p>', NULL);
INSERT INTO `sysnotice` VALUES (5, '2025-02-10 16:26:01', NULL, 'Plumsports专业课程介绍公告', '官方教练', '<h2><strong><span>Plumsports专业课程介绍公告</span></strong></h2>\n<p><span>尊敬的健身爱好者，</span></p>\n<p><span>为满足不同的健身需求，Plumsports健身房推出了舞蹈的</span><strong><span>专业课程体系</span></strong><span>，帮助大家塑形、提升体能、改善健康！</span></p>\n<p><span>📌</span><strong><span>热门课程推荐：</span></strong><br /><span> 💥 </span><strong><span>HIIT高强度间歇训练</span></strong><span>-提升心肺功能，燃脂更🧘</span><br /><span>瑜伽</span><strong><span>与普拉提</span></strong><span>-全身放松，塑造优美身形</span><br /><span>🥊</span><strong><span>搏击训练</span></strong><span>-提升力量、耐力和爆发力</span><br /><span>🏋️</span><strong><span>力量训练与体能提升</span></strong><span>-增强呼吸，雕刻完美线条</span><br /><span>🔥</span><strong><span>训练营</span></strong><span>-武术超燃，挑战自我极限</span></p>\n<p><span>👨&zwj;🏫</span><strong><span>私人教练服务</span></strong><span>：定制一次训练，专属健身方案，达成目标！</span></p>\n<p><span>💡</span><strong><span>预约课程：</span></strong><br /><span> 📞 电话：[电话] </span><br /><span>📩 邮箱：[邮箱] </span><br /><span>📍 健身房地址：[地址]</span></p>\n<p><span>欢迎预约体验，让Plumsports助您开启科学健身新生活！</span></p>', NULL);
INSERT INTO `sysnotice` VALUES (6, '2025-02-10 16:26:22', NULL, 'Plumsports主题活动公告', '赛事策划', '<h2><strong><span>Plumsports主题活动公告</span></strong></h2>\n<p><span>🎉Plumsports </span><strong><span>30天健身挑战赛正式开启！</span></strong><span> 🎉</span></p>\n<p><span>🔥</span><strong><span>挑战内容：</span></strong></p>\n<ul>\n<li><span>每日完成指定训练（全身燃脂、核心训练、徒手力量等）</span></li>\n<li><span>记录训练详情，挑战自我极限</span></li>\n<li><span>每周解锁新任务，累积积分，赢取惊喜大奖</span></li>\n</ul>\n<p><span>🏆</span><strong><span>环球奖励：</span></strong><br /><span> 🥇</span><strong><span>冠军</span></strong><span>：半年免费会员 + 私教课3节</span><br /><span>🥈</span><strong><span>亚军</span></strong><span>：3个月免费会员 + 健身礼包</span><br /><span>🥉</span><strong><span>季军</span></strong><span>：1个月免费会员 + 运动饮料礼包</span></p>\n<p><span>📅</span><strong><span>活动时间</span></strong><span>：[活动具体时间] </span><br /><span>📍</span><strong><span>地点</span></strong><span>：[地址健身房] </span><br /><span>🎟</span><strong><span>报名方式</span></strong><span>：扫描二维码或到店前台登记报名</span></p>\n<p><span>🚀</span><strong><span>接受挑战，突破极限，赢得荣誉！</span></strong></p>\n<p><span>梅花臂，期待与你并肩作战！💪</span></p>', NULL);

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '话题主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `Title` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  `Cover` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '封面',
  `ImageUrls` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '主图',
  `ViewCount` int NULL DEFAULT NULL COMMENT '浏览数',
  `Content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '内容',
  `TopicTypeId` int NULL DEFAULT NULL COMMENT '话题类型',
  `AuditStatus` int NULL DEFAULT NULL COMMENT '审核状态',
  `IsRecommand` tinyint(1) NULL DEFAULT NULL COMMENT '是否推荐',
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `CreatorId`(`CreatorId` ASC) USING BTREE,
  CONSTRAINT `topic_ibfk_1` FOREIGN KEY (`CreatorId`) REFERENCES `appuser` (`Id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES (1, '2024-04-29 00:34:58', 5, '健身5年了｜但在健身房被问的话会回答2.3年', 'http://localhost:7245/images/1707651235795/健身5年了｜但在健身房被问的话会回答2.3年_1_魏三刀说_来自小红书网页版.jpg', NULL, 1, '<p>健身带给我最大的变化是让我自信了很多，之前特别瘦整个人对自己很没自信，健身让我力气更大，对自己的身体掌控感更强，人也更自信了；但还是觉得自己肌肉量太小了。<br /><br />以后我要是有小孩我肯定每天带他去跑步去健身～大家觉得哪一年的我练的最好呢，好久没进步了</p>', 1, 1, 0);
INSERT INTO `topic` VALUES (2, '2024-05-06 00:34:58', 5, '给想要开始健身的小伙伴一点建议', 'http://localhost:7245/images/1707651269075/给想要开始健身的小伙伴一点建议_1_羊驼_来自小红书网页版.jpg', NULL, 30, '<p>1：别总到处问是否需要喝增肌粉或是蛋白粉或者什么肌酸、左旋、氮泵之类的健身补剂，你要做的第一<br />步就是先行动起来先练起来。<br />2:不要觉得自己一个人去健身房不会练会尴尬，谁都是从新手过来的，也不需要去买私教课，你想练哪一<br />块肌肉，可以直接抖音搜索某某部位怎么练，有很多博主都会很详细的视频教你动作要领。健身房也有很多热心的老会员，你看谁练得比较好你也可以直接上去问一问请教请教，一般都会很热心的帮你解答。<br />3;健身先健脑，你得先知道自己的需求是增肌还是减脂，很多人都认为健身的目的是把脂肪练成肌肉，我告诉大家脂肪是变不成肌肉的，脂肪是储存在你身体<br />里的能量只能被消耗掉，增肌是通过不断的进行重量<br />训练将肌肉撕裂再修复的过程。<br />4:别想着一步登天，别急于求成，一定要逼自己一<br />把，坚持下去，当你某天看到自己身材变化明显的时候，尝到了甜头，就更容易有动力坚持下去。<br />5：每个人都有一个&ldquo;新手福利期&rdquo;，指的就是刚开始<br />接触健身的新手，在刚开始健身的2-3个月，训练效果是特别明显的身材变化也很明显。<br />6:腹肌马甲线不是练出来的，不是练出来的，不是练出来的，重要的事说三遍。腹肌和马甲线是瘦出来的，也就是说你减脂过后，体脂率降下来了，自然而然就有了，每个人的腹肌形状和块数也是天生的，每个人路有不同。所以你想要拥有腹肌和马甲线就先自律起来减脂吧，加油。</p>', 1, 1, 1);
INSERT INTO `topic` VALUES (3, '2024-04-28 00:34:58', 5, '第一次去健身房丨小白必看 不要慌', 'http://localhost:7245/images/1707651299996/第一次去健身房丨小白必看 不要慌_1_K47__来自小红书网页版.jpg', NULL, 3, '<p>Hello 大家好，我是K47_，一名在健身房混迹了五年的健身爱好者💪。<br />还记得你们第一次踏进健身房大门的时候吗？🏋️<br />我第一次进健身房的时候，慌的不行，无从下手。<br />在这期间也走了不少的弯路，慢慢摸索出了一些自己的健身习惯。<br />感谢官方大大<a class=\"note-content-user\" href=\"https://www.xiaohongshu.com/user/profile/59b3829850c4b4197d115edf\" target=\"_blank\" rel=\"noopener\">@运动薯</a> 能给这次机会可以给大家介绍一下：<br />1⃣️练前篇<br />训练计划：一般我会在每个循环之前制定本周的一个训练计划，如果时间充裕，我会制订一套五分化的一个训练计划（胸背肩腿手臂）。<br /><br />服饰：我会选择透气舒适的衣服和鞋子，并按照当天所要训练的部位选择衣服（例如肩膀的训练日，我会选择坎袖或者背心，这样我们能更直观的看到肌肉充血的情况），练腿日要选择鞋底尽量平一点的鞋子。<br /><br />饮食补剂：我会在练前进行一个碳水的补充（例如香蕉或面包），可以做到持续给身体功能，减少肌糖原的消耗，防止肌肉分解。在训练状态不佳的时候我会提前20分钟左右摄入一勺氮泵，可以提高我们的训练状态和表现。<br /><br />2⃣️练中篇<br />热身：我会利用跑步机、椭圆仪等有氧器械进行10分钟左右的一个预热；然后再利用小重量的哑铃活动一下肩袖等小关节。<br /><br />正式组：我首先会采用一个递增的方式对所要训练的肌群增加重量（例如练胸日我会先进行平板卧推和上斜卧推，保证自己在有劲儿的时候，可以上上重量），然后进行细节一个训练（例如夹胸）。<br /><br />有氧：我会在正式组之后进行一个30-40分钟的有氧训练，更好的控制我们的体脂率。<br /><br />3⃣️练后篇：<br />拉伸：我会对今日所训练的肌群进行一次整体的拉伸和按摩，放松肌肉。<br /><br />饮食：我会在练后30分钟内补充一勺蛋白粉，再进行一次加餐（主要摄入蛋白质和碳水）。<br /><br />以上就是我在训练时所给大家的一些个人建议，最最重要的一点就是不要慌，要自信！好身材正在想你招手！</p>', 1, 1, 0);
INSERT INTO `topic` VALUES (4, '2024-05-02 00:34:58', 5, '健身朋友圈文案', 'http://localhost:7245/images/1707651339216/健身朋友圈文案_1_权权_来自小红书网页版.jpg', NULL, 17, '<div id=\"detail-desc\" class=\"desc\" data-v-104cd31e=\"\"><span data-v-104cd31e=\"\">1、生活像极了跑步，刚开始坚持很难，越努力奔跑越自由<br />2、所有的可能性都在你坚持下去的路上。<br />3、健身秀的不只是身材，还有健康。<br />4、活水贵在流动，生命贵在运动。<br />5、所有的可能性都在你跑下去的路上。<br />6、什么都可以忘掉，健身不能忘掉。<br />7、不为取悦任何人，只为更好的自己。<br />8、低调还是不够的，有时候还是要适当的秀一下自己，让<br />这平淡的日子发光。<br />9、运动是生活也是享受，它会让你遇见成长的自己。<br />10、运动是生活，也是享受，它会给你见证奇迹的时刻<br />11、有点爱好，让日子有兴趣；有点牵挂，让岁月有念想，愿美好，不期而遇。<br />12、不管在什么年纪，不断的去尝试，不断的去挑战，直到更好的你，那个过程，是可以无限去想象的。<br />13、星光不问赶路人，时光不负有心人，加油宝贝们。<br />14、低质量的社交、不如高质量的独处、现在你要慢慢改变自己、慢慢的变好、慢慢的自律、脱离安逸圈、不负自己。<br />15、与其安慰自己平凡可贵，不如拼尽全力活的漂亮．<br />16、用汗水去雕刻身材选择自己喜欢的方式去生活活得坦<br />然活得自在活得舒心。<br />17、花终向阳开，人终向前走，永远积极向上，永远热泪<br />盈眶，分享给正在努力的你我<br />18、每天如约而至你现在要做的就是努力变成想要的自己<br />19、愿你的自律，可以换来你的自信。愿你所有的样子<br />都是你最喜欢的模样。<br />20、健身没有高速路，也没有快车道，需要做的就是日复<br />日，年复一年的坚持和努力。<br />21、余生很贵，努力活成自己想要的样子，不负青春，不<br />负自己！<br />22、那些看似不起眼的日复一日，会在将来的某一天，让<br />你看到坚持的意义！<br />23、努力是一种态度，和年龄无关，只要开始什么时候都<br />不会晚，学最好的别人，做最好的自己。<br />24、人类最好的药是运动，最好的医生是睡眠，最好的爱<br />情是自爱，最快的减脂是跳绳！<br />25、保持炙热，坚持下去，或许前路未必是光明坦荡，但<br />也一定充满可能，热爱可抵岁月漫长。</span></div>\n<div class=\"bottom-container\" data-v-104cd31e=\"\">&nbsp;</div>', 1, 1, 1);
INSERT INTO `topic` VALUES (5, '2024-05-01 00:34:58', 9, '有哪些健身教练不愿意告诉你的瘦身方法？', NULL, NULL, 4, '<p data-first-child=\"\" data-pid=\"AATO2pOy\">五个月暴瘦49斤！！这个方法我吹爆！！</p>\n<p data-pid=\"WDTjyCD6\">不光效果好而且健康又快乐！！</p>\n<p data-pid=\"_wrVJQm2\">本人女，22岁，身高167，瘦身五个月的效果是：体重140-91，腰围81-61，大腿围62-45，小腿围40-33还拥有了锁骨，下颚线，五官变得立体，整个人的气质直线up！！</p>\n<figure data-size=\"normal\">\n<div><img class=\"origin_image zh-lightbox-thumb lazy\" src=\"https://pic1.zhimg.com/80/v2-bb8423bf619c93866bd41376dda28868_720w.webp\" width=\"960\" height=\"1280\" data-rawwidth=\"960\" data-rawheight=\"1280\" data-size=\"normal\" data-caption=\"\" data-original=\"https://pic1.zhimg.com/v2-bb8423bf619c93866bd41376dda28868_r.jpg\" data-actualsrc=\"https://pic1.zhimg.com/v2-bb8423bf619c93866bd41376dda28868_b.jpg\" data-original-token=\"v2-30dec06c456af9d48ad5ba4181ff7e51\" data-lazy-status=\"ok\" /></div>\n</figure>\n<p data-pid=\"DCIFN9Jy\">从一个基数很大浑身油腻的胖女生，摇身一变，被所有朋友惊呼&ldquo;天呐，你是去整容了吧&rdquo;</p>\n<figure data-size=\"normal\">\n<div><img class=\"origin_image zh-lightbox-thumb lazy\" src=\"https://pic1.zhimg.com/80/v2-df97b4eebb2384fdc77c8cef83c0175c_720w.webp\" width=\"1439\" height=\"1312\" data-rawwidth=\"1439\" data-rawheight=\"1312\" data-size=\"normal\" data-caption=\"\" data-original=\"https://pic1.zhimg.com/v2-df97b4eebb2384fdc77c8cef83c0175c_r.jpg\" data-actualsrc=\"https://pic1.zhimg.com/v2-df97b4eebb2384fdc77c8cef83c0175c_b.jpg\" data-original-token=\"v2-0cb9f24e15a067b3ef7e8a1494ebf4b3\" data-lazy-status=\"ok\" /></div>\n</figure>\n<p data-pid=\"PXPF1ym1\">所以我想跟大家说的是：瘦身才是最廉价的变美方式！！而且只要掌握住正确的方法，减重真的一点都不难！</p>\n<p data-pid=\"9ApNPaTi\"><strong>这个方法很简单还是我从一个身材管理师那里了解到的：祛湿！</strong></p>\n<p data-pid=\"kOpsTtks\">可能提前这个就有人会觉的假或者是我要给某个产品带货，在写之前我也想过。但这都无所谓了，仁者见仁智者见智嘛。</p>\n<p data-pid=\"pHdbOCU4\">我之前也是没有这个概念，根本不知道我为什么一直减不去肉。</p>\n<p data-pid=\"IGZem4j8\"><strong>以至于后来瘦身四处碰壁，穷尽一切方法也是短暂的维持个四五斤，根本做不到有效减称，根本想不到是身体内部出了问题。</strong></p>\n<p data-pid=\"fY0U1fyN\">因为对于90%普通人来说，肥胖难减都是因为湿气导致的水肿虚胖啊！！！</p>\n<figure data-size=\"normal\">\n<div><img class=\"origin_image zh-lightbox-thumb lazy\" src=\"https://pic4.zhimg.com/80/v2-4ca1b0dc5b516a51507e0f1ca3da6897_720w.webp\" width=\"1166\" height=\"1326\" data-rawwidth=\"1166\" data-rawheight=\"1326\" data-size=\"normal\" data-caption=\"\" data-original=\"https://pic4.zhimg.com/v2-4ca1b0dc5b516a51507e0f1ca3da6897_r.jpg\" data-actualsrc=\"https://pic4.zhimg.com/v2-4ca1b0dc5b516a51507e0f1ca3da6897_b.jpg\" data-original-token=\"v2-3e91f1dd1f863412853a3df4c5d072b2\" data-lazy-status=\"ok\" /></div>\n</figure>\n<p data-pid=\"6dIDV1iR\"><strong>简单来说，湿气就像一根棍子，横在了代谢和消化之间，导致人体运转受阻，长此以往，不光胖腿胖肚子，还会引起</strong><a class=\"internal\" href=\"https://www.zhihu.com/search?q=%E5%8F%A3%E5%B9%B2%E5%8F%A3%E8%8B%A6&amp;search_source=Entity&amp;hybrid_search_source=Entity&amp;hybrid_search_extra=%7B%22sourceType%22%3A%22answer%22%2C%22sourceId%22%3A3106027794%7D\" data-za-detail-view-id=\"1043\">口干口苦</a><strong>，头油乏力等一系列并发</strong></p>\n<p data-pid=\"TX5OCBJl\">而<a class=\"internal\" href=\"https://www.zhihu.com/search?q=%E7%A5%9B%E6%B9%BF%E4%B8%AD%E8%8D%89%E5%89%82&amp;search_source=Entity&amp;hybrid_search_source=Entity&amp;hybrid_search_extra=%7B%22sourceType%22%3A%22answer%22%2C%22sourceId%22%3A3106027794%7D\" data-za-detail-view-id=\"1043\">祛湿</a>的目的就相当于把这根棍子拿开了，消化吸收受到调节，身体运转变得正常，瘦身和健康的目的自然很容易就能达成</p>\n<p data-pid=\"hKkDEAsj\"><strong>我就是一个正儿八经的例子：</strong></p>\n<p data-pid=\"Jzj8-ZtD\">从开始喝第1天的嗜睡、头油、口臭、满身肥肉.....</p>\n<figure data-size=\"normal\">\n<div><img class=\"origin_image zh-lightbox-thumb lazy\" src=\"https://pic3.zhimg.com/80/v2-b1705a3aab0de8325a0512c0b83a27d2_720w.webp\" width=\"1080\" height=\"1440\" data-rawwidth=\"1080\" data-rawheight=\"1440\" data-size=\"normal\" data-caption=\"\" data-original=\"https://pic3.zhimg.com/v2-b1705a3aab0de8325a0512c0b83a27d2_r.jpg\" data-actualsrc=\"https://pic3.zhimg.com/v2-b1705a3aab0de8325a0512c0b83a27d2_b.jpg\" data-original-token=\"v2-4c1ddd58d0498c35639c407455d87ccb\" data-lazy-status=\"ok\" /></div>\n</figure>\n<p data-pid=\"Vk_Qk3wz\">到现在坚持了5个月之后的，清爽、纤细、五官立体，精神百倍！</p>\n<figure data-size=\"normal\">\n<div><img class=\"origin_image zh-lightbox-thumb lazy\" src=\"https://pic3.zhimg.com/80/v2-51d4027dfa5ddce978250d6513bb3a12_720w.webp\" width=\"1170\" height=\"1560\" data-rawwidth=\"1170\" data-rawheight=\"1560\" data-size=\"normal\" data-caption=\"\" data-original=\"https://pic3.zhimg.com/v2-51d4027dfa5ddce978250d6513bb3a12_r.jpg\" data-actualsrc=\"https://pic3.zhimg.com/v2-51d4027dfa5ddce978250d6513bb3a12_b.jpg\" data-original-token=\"v2-0d1b6487c9737fcac7f504406aab4291\" data-lazy-status=\"ok\" /></div>\n</figure>\n<p data-pid=\"GkNSEGbn\"><strong>它给我带来的变化远比外在体现的要多的多！！</strong></p>\n<blockquote data-pid=\"tZrg8mvk\"><strong>第一周</strong>，我就发现小便频繁了，身体在缓往外<a class=\"internal\" href=\"https://www.zhihu.com/search?q=%E6%8E%92%E6%B9%BF%E5%88%A9%E6%B0%B4&amp;search_source=Entity&amp;hybrid_search_source=Entity&amp;hybrid_search_extra=%7B%22sourceType%22%3A%22answer%22%2C%22sourceId%22%3A3120212899%7D\" data-za-detail-view-id=\"1043\">排湿利水</a><br /><strong>第二周</strong>，早上起床昏沉和<a class=\"internal\" href=\"https://www.zhihu.com/search?q=%E8%84%B8%E9%83%A8%E6%B5%AE%E8%82%BF&amp;search_source=Entity&amp;hybrid_search_source=Entity&amp;hybrid_search_extra=%7B%22sourceType%22%3A%22answer%22%2C%22sourceId%22%3A3120212899%7D\" data-za-detail-view-id=\"1043\">脸部浮肿</a>的情况已经开始减少，代谢变快了许多，大便顺畅<br /><strong>第三周</strong>，四肢浮肿变好，鼓起发胀小肚子的维度开始变小3~5cm不等，肉眼可见的变平<br /><strong>第四周</strong>，<a class=\"internal\" href=\"https://www.zhihu.com/search?q=%E5%8F%A3%E5%B9%B2%E5%8F%A3%E8%8B%A6&amp;search_source=Entity&amp;hybrid_search_source=Entity&amp;hybrid_search_extra=%7B%22sourceType%22%3A%22answer%22%2C%22sourceId%22%3A3120212899%7D\" data-za-detail-view-id=\"1043\">口干口苦</a>、嘴巴有异味儿，<a class=\"internal\" href=\"https://www.zhihu.com/search?q=%E8%88%8C%E8%8B%94%E9%BB%84%E8%85%BB&amp;search_source=Entity&amp;hybrid_search_source=Entity&amp;hybrid_search_extra=%7B%22sourceType%22%3A%22answer%22%2C%22sourceId%22%3A3120212899%7D\" data-za-detail-view-id=\"1043\">舌苔黄腻</a>，头脸发油...等等情况都会有不同程度的改善<br />.....</blockquote>\n<p data-pid=\"-lGsRMh8\"><strong>前后不过5个月时间，回头再看看，从140-91，难看的肥肉没有了、消化变好了、就连之前一些难缠的臭毛病都被去的七七八八</strong></p>\n<p data-pid=\"7uJNTKdq\">我从相册中翻出来了几张图片，给大家参考下</p>\n<p data-pid=\"S-FGFktw\">图一</p>\n<p data-pid=\"hatfz1Kk\">这是配合运动 瘦了9斤左右<br />主要瘦在肚子上了，表面可能看不太出来，但其实我最近肠胃好了不少，肚子没那么胀，脸也没有油的那么快了</p>\n<figure data-size=\"normal\">\n<div><img class=\"origin_image zh-lightbox-thumb lazy\" src=\"https://pic2.zhimg.com/80/v2-4b6afae3604f7e36e61988f61996a3a5_720w.webp\" width=\"1706\" height=\"1706\" data-rawwidth=\"1706\" data-rawheight=\"1706\" data-size=\"normal\" data-caption=\"\" data-original=\"https://pic2.zhimg.com/v2-4b6afae3604f7e36e61988f61996a3a5_r.jpg\" data-actualsrc=\"https://pic2.zhimg.com/v2-4b6afae3604f7e36e61988f61996a3a5_b.jpg\" data-original-token=\"v2-019dc679bb722987c345b70751bff919\" data-lazy-status=\"ok\" /></div>\n</figure>\n<p data-pid=\"w91ir_TG\">图二</p>\n<p data-pid=\"8KLROefc\">这是瘦了18斤的时候<br />脸已经小很多了，身上的浮肿都在慢慢下去，早上起床也没什么苦味儿，嘴不干臭....体重正在逐步减少</p>', 1, 1, 0);
INSERT INTO `topic` VALUES (6, '2024-05-02 00:34:58', 9, '有哪些是你健身久了知道的事？', NULL, NULL, 3, '<div>作者：王海<br />链接：https://www.zhihu.com/question/332765440/answer/2685452134<br />来源：知乎<br />著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。<br /><br />\n<div>\n<p data-first-child=\"\" data-pid=\"hGzl25uC\">只撸铁健身，对于大众来说，是条歧途。</p>\n<p data-pid=\"Znzy0SHU\">只撸铁，</p>\n<p data-pid=\"Yj3ExpBO\">首先会让人身体僵硬，灵活性差，有点像慢动作的鬼畜，给人的观感不佳；</p>\n<figure data-size=\"normal\"><noscript><img src=\"https://pic1.zhimg.com/50/v2-791001ea3dedd4eeb1fd8d55456e1f92_720w.gif?source=2c26e567\" data-rawwidth=\"300\" data-rawheight=\"301\" data-size=\"small\" data-original-token=\"v2-791001ea3dedd4eeb1fd8d55456e1f92\" data-thumbnail=\"https://picx.zhimg.com/50/v2-791001ea3dedd4eeb1fd8d55456e1f92_720w.jpg?source=2c26e567\" data-default-watermark-src=\"https://pic1.zhimg.com/50/v2-791001ea3dedd4eeb1fd8d55456e1f92_720w.jpg?source=2c26e567\" class=\"content_image\" width=\"300\"/></noscript>\n<div>\n<div class=\"GifPlayer css-o0k2vi\" data-size=\"small\" data-za-detail-view-path-module=\"GifItem\"><img class=\"ztext-gif\" role=\"presentation\" src=\"https://picx.zhimg.com/50/v2-791001ea3dedd4eeb1fd8d55456e1f92_720w.jpg?source=2c26e567\" alt=\"动图封面\" width=\"300\" data-thumbnail=\"https://picx.zhimg.com/50/v2-791001ea3dedd4eeb1fd8d55456e1f92_720w.jpg?source=2c26e567\" data-size=\"small\" />\n<div class=\"GifPlayer-icon css-d39tw7\">&nbsp;</div>\n</div>\n</div>\n</figure>\n<p class=\"ztext-empty-paragraph\">&nbsp;</p>\n<p data-pid=\"Cq-5UV0Y\">其次无氧肌群相对发达，在有氧运动时消耗较多血氧，导致心率上升较高，心肺功能欠佳；</p>\n<p data-pid=\"CjjY-F2S\">再者撸铁过程中，供能系统偏向于利用糖原，削弱脂肪代谢能力。</p>\n<p data-pid=\"PVOCPeSX\">但是撸铁确实是所有运动的基础，不能完全不练。</p>\n<p data-pid=\"jAsOLPDk\">最好结合其他运动，比如骑行，跑步，打拳，有针对性的做功能力量训练。</p>\n</div>\n</div>', 1, 1, 0);
INSERT INTO `topic` VALUES (7, '2024-05-03 00:34:58', 9, '健身到底有什么用？', NULL, NULL, 16, '<div>作者：FanFanFan<br />链接：https://www.zhihu.com/question/430592648/answer/1718074710<br />来源：知乎<br />著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。<br /><br />\n<div>\n<p data-first-child=\"\" data-pid=\"DT37paaU\">对于一个三十多岁的男人来说，通过撸铁来控制脂肪比例的难度，要低于长期坚持有氧，更低于长期坚持佛系饮食。虽然撸铁是控制脂肪比例最低效的方式吧。</p>\n<p data-pid=\"bzZ9B1J7\">这依然只能提升你的基础代谢率，却永远无法达成精瘦。精瘦的核心依然在摄入长期小于等于消耗。于是基本上只能靠长期坚持佛系饮食。饮食没有管控，长期的有氧也没用。</p>\n<p data-pid=\"LQMsGhNP\">那撸铁又是要闹哪样呢？</p>\n<p data-pid=\"plRV3Aml\">对我个人来说，重要性由高到低分别是：</p>\n<p data-pid=\"_uNFRda0\">1，继续维持正常衣服架子的身材，别过于中年拉胯；</p>\n<p data-pid=\"9fKCe0S2\">2，维持一个日常规律活动所带来的自我约束感，有益身心；</p>\n<p data-pid=\"bc0Leqna\">3，对一些运动爱好的辅助，如腿和核心对滑雪的辅助，背和上肢对游泳的辅助，等等；</p>\n<p data-pid=\"1xDQLk0p\">4，通过明显的肌肉来在群体中维持有一种仿佛有较高暴力能力的假象（虽然其实并没有），并合理利用这一假象；</p>\n<p data-pid=\"9f9FNhIT\">5，大大方方鉴赏各色姑娘冬季的legging勾勒出的腿和屁屁，以及夏季的裸腿和内内；</p>\n<p data-pid=\"cGi1RoWb\">6，顺便洗个澡，肉眼鉴定哪个裸男是基佬。</p>\n<p data-pid=\"JrqVV-bQ\">再其他的，也就真的没啥了。</p>\n<p data-pid=\"mNICjsem\">反正就是，撸铁纯属自娱自乐，没法太功利。你若很功利，就会很快意识到，撸铁确实没啥用，起码效率很低。</p>\n<p data-pid=\"osg4ffOC\">撸铁无法高效达成低体脂；撸铁无法高效提升心肺功能；撸铁无法直接提升运动效果；撸铁无法延寿；撸铁和提升性能力几乎没什么关系，虽然很多人以为有很大关系......</p>\n<p data-pid=\"vRKCOcDs\">所以个人的建议是，若你追求撸铁，最好就是冲着自娱自乐。功利心太强，大概率会只是撸了个寂寞。</p>\n</div>\n</div>', 1, 1, 0);
INSERT INTO `topic` VALUES (8, '2025-02-11 15:03:41', 11, '测试', 'http://localhost:7245/images/1739257417423/abbf784897c2f848c176de92fcdb4fd.png', NULL, 4, '<p>哇哈哈哈</p>', 2, 1, 1);
INSERT INTO `topic` VALUES (11, '2025-02-11 15:39:39', 11, '测试', 'http://localhost:7245/images/1739259571097/default-avatar.png', 'http://localhost:7245/images/1739259568127/pay4.jpg', 16, '<p>哇哇哇哇</p>', 2, 1, 0);

-- ----------------------------
-- Table structure for topiccollect
-- ----------------------------
DROP TABLE IF EXISTS `topiccollect`;
CREATE TABLE `topiccollect`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '话题收藏主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `TopicId` int NULL DEFAULT NULL COMMENT '话题',
  `UserId` int NULL DEFAULT NULL COMMENT '收藏人',
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `CreatorId`(`CreatorId` ASC) USING BTREE,
  CONSTRAINT `topiccollect_ibfk_1` FOREIGN KEY (`CreatorId`) REFERENCES `appuser` (`Id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of topiccollect
-- ----------------------------
INSERT INTO `topiccollect` VALUES (1, '2024-04-30 00:35:03', 9, 7, 9);
INSERT INTO `topiccollect` VALUES (2, '2024-05-04 00:35:03', 5, 2, 5);
INSERT INTO `topiccollect` VALUES (3, '2025-01-10 10:47:09', 12, 2, 12);
INSERT INTO `topiccollect` VALUES (4, '2025-02-12 10:32:25', 11, 8, 11);
INSERT INTO `topiccollect` VALUES (5, '2025-02-12 10:32:30', 11, 11, 11);

-- ----------------------------
-- Table structure for topicrecord
-- ----------------------------
DROP TABLE IF EXISTS `topicrecord`;
CREATE TABLE `topicrecord`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '话题浏览记录主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `TopicTypeId` int NULL DEFAULT NULL COMMENT '话题类型',
  `TopicId` int NULL DEFAULT NULL COMMENT '话题',
  `UserId` int NULL DEFAULT NULL COMMENT '浏览人',
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `CreatorId`(`CreatorId` ASC) USING BTREE,
  CONSTRAINT `topicrecord_ibfk_1` FOREIGN KEY (`CreatorId`) REFERENCES `appuser` (`Id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 64 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of topicrecord
-- ----------------------------
INSERT INTO `topicrecord` VALUES (1, '2024-05-06 00:35:07', 5, 1, 4, 5);
INSERT INTO `topicrecord` VALUES (2, '2024-05-01 00:35:07', 5, 1, 4, 5);
INSERT INTO `topicrecord` VALUES (3, '2024-05-02 00:35:07', 5, 1, 4, 5);
INSERT INTO `topicrecord` VALUES (4, '2024-04-29 00:35:07', 5, 1, 4, 5);
INSERT INTO `topicrecord` VALUES (5, '2024-05-03 00:35:07', 5, 1, 4, 5);
INSERT INTO `topicrecord` VALUES (6, '2024-05-02 00:35:07', 5, 1, 4, 5);
INSERT INTO `topicrecord` VALUES (7, '2024-05-05 00:35:07', 5, 1, 3, 5);
INSERT INTO `topicrecord` VALUES (8, '2024-04-30 00:35:07', 5, 1, 4, 5);
INSERT INTO `topicrecord` VALUES (9, '2024-04-30 00:35:07', 9, 1, 4, 9);
INSERT INTO `topicrecord` VALUES (10, '2024-05-05 00:35:07', 9, 1, 3, 9);
INSERT INTO `topicrecord` VALUES (11, '2024-05-06 00:35:07', 9, 1, 4, 9);
INSERT INTO `topicrecord` VALUES (12, '2024-04-29 00:35:07', 9, 1, 7, 9);
INSERT INTO `topicrecord` VALUES (13, '2024-05-01 00:35:07', 9, 1, 7, 9);
INSERT INTO `topicrecord` VALUES (14, '2024-04-30 00:35:07', 9, 1, 7, 9);
INSERT INTO `topicrecord` VALUES (15, '2024-05-04 00:35:07', 9, 1, 7, 9);
INSERT INTO `topicrecord` VALUES (16, '2024-04-30 00:35:07', 9, 1, 5, 9);
INSERT INTO `topicrecord` VALUES (17, '2024-05-01 00:35:07', 9, 1, 7, 9);
INSERT INTO `topicrecord` VALUES (18, '2024-04-30 00:35:07', 9, 1, 2, 9);
INSERT INTO `topicrecord` VALUES (19, '2024-05-01 00:35:07', 5, 1, 7, 5);
INSERT INTO `topicrecord` VALUES (20, '2024-04-28 00:35:07', 5, 1, 7, 5);
INSERT INTO `topicrecord` VALUES (21, '2024-05-01 00:35:07', 5, 1, 5, 5);
INSERT INTO `topicrecord` VALUES (22, '2024-05-06 00:35:07', 5, 1, 2, 5);
INSERT INTO `topicrecord` VALUES (23, '2024-04-30 00:35:07', 5, 1, 7, 5);
INSERT INTO `topicrecord` VALUES (24, '2024-05-06 00:35:07', 9, 1, 7, 9);
INSERT INTO `topicrecord` VALUES (25, '2024-05-02 00:35:07', 9, 1, 6, 9);
INSERT INTO `topicrecord` VALUES (26, '2024-04-27 00:35:07', 9, 1, 5, 9);
INSERT INTO `topicrecord` VALUES (27, '2024-05-03 00:35:07', 5, 1, 2, 5);
INSERT INTO `topicrecord` VALUES (28, '2025-01-10 10:22:14', 11, 1, 7, 11);
INSERT INTO `topicrecord` VALUES (29, '2025-01-10 10:23:50', 11, 1, 2, 11);
INSERT INTO `topicrecord` VALUES (30, '2025-01-10 10:24:32', 11, 1, 2, 11);
INSERT INTO `topicrecord` VALUES (31, '2025-01-10 10:46:41', 12, 1, 2, 12);
INSERT INTO `topicrecord` VALUES (32, '2025-01-10 11:03:43', 11, 1, 2, 11);
INSERT INTO `topicrecord` VALUES (33, '2025-01-10 16:56:31', 11, 1, 2, 11);
INSERT INTO `topicrecord` VALUES (34, '2025-01-21 10:15:14', 11, 1, 2, 11);
INSERT INTO `topicrecord` VALUES (35, '2025-01-21 10:16:46', 11, 1, 2, 11);
INSERT INTO `topicrecord` VALUES (36, '2025-02-11 14:44:41', 11, 1, 2, 11);
INSERT INTO `topicrecord` VALUES (37, '2025-02-11 14:53:57', 11, 1, 2, 11);
INSERT INTO `topicrecord` VALUES (38, '2025-02-12 10:22:14', 11, 1, 2, 11);
INSERT INTO `topicrecord` VALUES (39, '2025-02-12 10:22:17', 11, 1, 7, 11);
INSERT INTO `topicrecord` VALUES (40, '2025-02-12 10:22:22', 11, 1, 7, 11);
INSERT INTO `topicrecord` VALUES (41, '2025-02-12 10:22:29', 11, 1, 4, 11);
INSERT INTO `topicrecord` VALUES (42, '2025-02-12 10:32:23', 11, 2, 8, 11);
INSERT INTO `topicrecord` VALUES (43, '2025-02-12 10:32:27', 11, 2, 11, 11);
INSERT INTO `topicrecord` VALUES (44, '2025-02-12 10:32:34', 11, 1, 2, 11);
INSERT INTO `topicrecord` VALUES (45, '2025-02-12 10:32:38', 11, 1, 6, 11);
INSERT INTO `topicrecord` VALUES (46, '2025-02-12 10:32:43', 11, 2, 11, 11);
INSERT INTO `topicrecord` VALUES (47, '2025-02-12 10:32:47', 11, 1, 4, 11);
INSERT INTO `topicrecord` VALUES (48, '2025-02-12 10:32:50', 11, 1, 4, 11);
INSERT INTO `topicrecord` VALUES (49, '2025-02-12 10:32:54', 11, 1, 4, 11);
INSERT INTO `topicrecord` VALUES (50, '2025-02-12 10:32:56', 11, 2, 11, 11);
INSERT INTO `topicrecord` VALUES (51, '2025-02-12 10:33:04', 11, 2, 8, 11);
INSERT INTO `topicrecord` VALUES (52, '2025-02-12 10:33:08', 11, 2, 11, 11);
INSERT INTO `topicrecord` VALUES (53, '2025-02-12 10:33:35', 11, 2, 11, 11);
INSERT INTO `topicrecord` VALUES (54, '2025-02-12 10:33:37', 11, 2, 11, 11);
INSERT INTO `topicrecord` VALUES (55, '2025-02-12 10:37:17', 11, 2, 11, 11);
INSERT INTO `topicrecord` VALUES (56, '2025-02-12 10:37:23', 11, 2, 8, 11);
INSERT INTO `topicrecord` VALUES (57, '2025-02-12 10:37:24', 11, 2, 11, 11);
INSERT INTO `topicrecord` VALUES (58, '2025-02-12 10:39:22', 11, 2, 11, 11);
INSERT INTO `topicrecord` VALUES (59, '2025-02-12 10:40:20', 11, 2, 11, 11);
INSERT INTO `topicrecord` VALUES (60, '2025-02-12 10:43:38', 11, 2, 11, 11);
INSERT INTO `topicrecord` VALUES (61, '2025-02-12 10:44:43', 11, 2, 11, 11);
INSERT INTO `topicrecord` VALUES (62, '2025-02-12 10:50:19', 11, 2, 11, 11);
INSERT INTO `topicrecord` VALUES (63, '2025-02-12 11:57:24', 11, 2, 11, 11);

-- ----------------------------
-- Table structure for topictype
-- ----------------------------
DROP TABLE IF EXISTS `topictype`;
CREATE TABLE `topictype`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '话题类型主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `Name` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `Code` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '编码',
  `Content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '内容',
  `Cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '封面',
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `CreatorId`(`CreatorId` ASC) USING BTREE,
  CONSTRAINT `topictype_ibfk_1` FOREIGN KEY (`CreatorId`) REFERENCES `appuser` (`Id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of topictype
-- ----------------------------
INSERT INTO `topictype` VALUES (1, '2024-04-12 22:08:19', NULL, '健身打卡分享', '健身打卡分享', '期待与您一起汗水飞溅，共同见证每一次超越自我的瞬间', 'http://localhost:7245/images/1707651020130/125432512.jpg');
INSERT INTO `topictype` VALUES (2, '2024-04-16 22:08:19', NULL, '健身技巧', 'JSQJR', '说明该圈子致力于分享和讨论各种健身相关的技巧、策略和最佳实践。其目的是帮助成员提高训练效率，避免受伤，并达到他们的健康和健身目标', 'http://localhost:7245/images/1707651038197/5163643643.jpg');

SET FOREIGN_KEY_CHECKS = 1;
