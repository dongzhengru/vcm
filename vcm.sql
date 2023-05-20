/*
 Navicat Premium Data Transfer

 Source Server         : Docker_MySQL
 Source Server Type    : MySQL
 Source Server Version : 50741 (5.7.41)
 Source Host           : localhost:3306
 Source Schema         : vcm

 Target Server Type    : MySQL
 Target Server Version : 50741 (5.7.41)
 File Encoding         : 65001

 Date: 16/05/2023 11:26:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for award
-- ----------------------------
DROP TABLE IF EXISTS `award`;
CREATE TABLE `award`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '奖项名称',
  `cid` int(11) NULL DEFAULT NULL COMMENT '竞赛ID',
  `uid` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of award
-- ----------------------------
INSERT INTO `award` VALUES (1, '省级二等奖', 7, 29);
INSERT INTO `award` VALUES (2, '省级一等奖', 39, 16);
INSERT INTO `award` VALUES (3, '省级三等奖', 5, 18);
INSERT INTO `award` VALUES (4, '省级二等奖', 30, 20);
INSERT INTO `award` VALUES (5, '院级一等奖', 38, 24);
INSERT INTO `award` VALUES (6, '院级二等奖', 43, 23);
INSERT INTO `award` VALUES (7, '院级二等奖', 31, 31);
INSERT INTO `award` VALUES (8, '院级三等奖', 4, 17);
INSERT INTO `award` VALUES (9, '省级一等奖', 13, 12);
INSERT INTO `award` VALUES (10, '国家一等奖', 41, 26);
INSERT INTO `award` VALUES (11, '国家二等奖', 2, 22);
INSERT INTO `award` VALUES (12, '国家三等奖', 6, 15);
INSERT INTO `award` VALUES (13, '院级二等奖', 7, 28);
INSERT INTO `award` VALUES (14, '院级二等奖', 39, 19);
INSERT INTO `award` VALUES (15, '省级一等奖', 5, 13);
INSERT INTO `award` VALUES (16, '省级三等奖', 30, 21);
INSERT INTO `award` VALUES (17, '省级二等奖', 38, 27);
INSERT INTO `award` VALUES (18, '省级一等奖', 43, 14);
INSERT INTO `award` VALUES (19, '院级二等奖', 31, 30);
INSERT INTO `award` VALUES (20, '院级二等奖', 4, 29);
INSERT INTO `award` VALUES (21, '省级一等奖', 13, 16);
INSERT INTO `award` VALUES (22, '院级二等奖', 41, 18);
INSERT INTO `award` VALUES (23, '省级三等奖', 2, 20);
INSERT INTO `award` VALUES (24, '省级二等奖', 6, 24);
INSERT INTO `award` VALUES (25, '院级一等奖', 35, 23);
INSERT INTO `award` VALUES (26, '国家二等奖', 40, 31);
INSERT INTO `award` VALUES (27, '国家三等奖', 29, 17);
INSERT INTO `award` VALUES (28, '院级二等奖', 3, 12);

-- ----------------------------
-- Table structure for competition
-- ----------------------------
DROP TABLE IF EXISTS `competition`;
CREATE TABLE `competition`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '竞赛名称',
  `teacher_num` int(255) NULL DEFAULT NULL COMMENT '指导教师数量',
  `student_num` int(255) NULL DEFAULT NULL COMMENT '学生人数限制',
  `teach_max` int(255) NULL DEFAULT NULL COMMENT '最多允许指导数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of competition
-- ----------------------------
INSERT INTO `competition` VALUES (1, '中国“互联网+”大学生创新创业大赛', 3, 5, 3);
INSERT INTO `competition` VALUES (2, '“挑战杯”中国大学生创业计划竞赛', 2, 6, 3);
INSERT INTO `competition` VALUES (3, 'ACM- ICPC国际大学生程序设计竞赛', 1, 1, 5);
INSERT INTO `competition` VALUES (4, 'CPCC中国大学生程序设计竞赛', 1, 1, 5);
INSERT INTO `competition` VALUES (5, '浙江省大学生程序设计竞赛', 1, 1, 5);
INSERT INTO `competition` VALUES (6, '全国大学生电子设计竞赛', 3, 4, 3);
INSERT INTO `competition` VALUES (7, '全国大学生数学建模竞赛', 2, 3, 5);
INSERT INTO `competition` VALUES (8, '全国大学生广告艺术设计大赛', 3, 5, 5);
INSERT INTO `competition` VALUES (9, '浙江省大学生广告创意设计竞赛', 3, 5, 3);
INSERT INTO `competition` VALUES (10, '“外研社”全国大学生英语演讲竞赛', 2, 1, 5);
INSERT INTO `competition` VALUES (11, '“外研社”全国大学生英语写作竞赛', 2, 1, 5);
INSERT INTO `competition` VALUES (12, '“外研社”全国大学生英语阅读竞赛', 2, 1, 5);
INSERT INTO `competition` VALUES (13, '“外研社”全国大学生英语辩论竞赛', 2, 5, 5);
INSERT INTO `competition` VALUES (28, '测试竞赛1', 1, 1, 5);
INSERT INTO `competition` VALUES (29, '浙江省大学生力学竟赛', 1, 3, 3);
INSERT INTO `competition` VALUES (30, '全国大学生机械创新设计大赛', 3, 4, 5);
INSERT INTO `competition` VALUES (31, '中国大学生服务外包创新创业大赛', 2, 3, 5);
INSERT INTO `competition` VALUES (32, '全国大学生智能汽车大赛', 1, 5, 5);
INSERT INTO `competition` VALUES (33, '全国大学生电子商务竟赛', 3, 1, 3);
INSERT INTO `competition` VALUES (34, '中国大学生工程实践与创新能力大赛', 2, 1, 5);
INSERT INTO `competition` VALUES (35, '浙江省大学生职业生涯规划大赛', 3, 4, 5);
INSERT INTO `competition` VALUES (36, '浙江省大学生多媒体作品设计竞赛', 1, 3, 5);
INSERT INTO `competition` VALUES (37, '浙江省大学生统计调查方案设计竞赛', 3, 5, 5);
INSERT INTO `competition` VALUES (38, '浙江省大学生工业设计竞赛', 2, 1, 5);
INSERT INTO `competition` VALUES (39, '浙江省大学生财会信息化竞赛', 3, 4, 3);
INSERT INTO `competition` VALUES (40, '浙江省大学生摄影竞赛', 3, 3, 5);
INSERT INTO `competition` VALUES (41, '浙江省大学生中华经典诵读竞赛', 1, 5, 5);
INSERT INTO `competition` VALUES (42, '浙江省大学生经济管理案例竞赛', 3, 1, 5);
INSERT INTO `competition` VALUES (43, '浙江省大学生证券投资竞赛', 2, 1, 3);
INSERT INTO `competition` VALUES (47, '测试竞赛2', 1, 3, 1);
INSERT INTO `competition` VALUES (48, '演示竞赛', 1, 3, 1);

-- ----------------------------
-- Table structure for invitation
-- ----------------------------
DROP TABLE IF EXISTS `invitation`;
CREATE TABLE `invitation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `cid` int(11) NULL DEFAULT NULL COMMENT '竞赛ID',
  `tid` int(11) NULL DEFAULT NULL COMMENT '队伍ID',
  `uid1` int(11) NULL DEFAULT NULL COMMENT '邀请人ID',
  `uid2` int(11) NULL DEFAULT NULL COMMENT '受邀请人ID',
  `accepted` tinyint(1) NULL DEFAULT NULL COMMENT '是否同意',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 231 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of invitation
-- ----------------------------
INSERT INTO `invitation` VALUES (182, 1, 35, 12, 15, 1);
INSERT INTO `invitation` VALUES (183, 1, 35, 12, 16, 1);
INSERT INTO `invitation` VALUES (184, 1, 35, 12, 18, 0);
INSERT INTO `invitation` VALUES (185, 1, 35, 12, 2, 1);
INSERT INTO `invitation` VALUES (186, 1, 35, 12, 3, 1);
INSERT INTO `invitation` VALUES (187, 2, 36, 12, 15, 1);
INSERT INTO `invitation` VALUES (188, 2, 36, 12, 16, 1);
INSERT INTO `invitation` VALUES (189, 2, 36, 12, 18, 0);
INSERT INTO `invitation` VALUES (190, 2, 36, 12, 2, 0);
INSERT INTO `invitation` VALUES (191, 2, 36, 12, 3, 1);
INSERT INTO `invitation` VALUES (192, 3, 37, 12, 2, 0);
INSERT INTO `invitation` VALUES (193, 6, 38, 12, 15, 0);
INSERT INTO `invitation` VALUES (194, 6, 38, 12, 16, 1);
INSERT INTO `invitation` VALUES (195, 6, 38, 12, 18, 0);
INSERT INTO `invitation` VALUES (196, 6, 38, 12, 2, 0);
INSERT INTO `invitation` VALUES (197, 6, 38, 12, 3, 0);
INSERT INTO `invitation` VALUES (203, 7, 40, 12, 15, 0);
INSERT INTO `invitation` VALUES (204, 7, 40, 12, 16, 0);
INSERT INTO `invitation` VALUES (205, 7, 40, 12, 18, 0);
INSERT INTO `invitation` VALUES (206, 7, 40, 12, 2, 0);
INSERT INTO `invitation` VALUES (207, 7, 40, 12, 3, 0);
INSERT INTO `invitation` VALUES (208, 5, 41, 12, 2, 0);
INSERT INTO `invitation` VALUES (209, 28, 42, 12, 2, 0);
INSERT INTO `invitation` VALUES (210, 40, 43, 14, 12, 0);
INSERT INTO `invitation` VALUES (211, 40, 43, 14, 13, 1);
INSERT INTO `invitation` VALUES (212, 40, 43, 14, 2, 1);
INSERT INTO `invitation` VALUES (213, 47, 44, 12, 13, 1);
INSERT INTO `invitation` VALUES (214, 47, 44, 12, 14, 0);
INSERT INTO `invitation` VALUES (215, 47, 44, 12, 3, 1);
INSERT INTO `invitation` VALUES (216, 41, 45, 12, 13, 0);
INSERT INTO `invitation` VALUES (217, 41, 45, 12, 3, 1);

-- ----------------------------
-- Table structure for material
-- ----------------------------
DROP TABLE IF EXISTS `material`;
CREATE TABLE `material`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `cid` int(11) NULL DEFAULT NULL COMMENT '竞赛ID',
  `tid` int(11) NULL DEFAULT NULL COMMENT '队伍ID',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '作品网盘链接',
  `pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '网盘提取码',
  `enabled` tinyint(1) NULL DEFAULT 0 COMMENT '审核状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of material
-- ----------------------------
INSERT INTO `material` VALUES (2, 1, 35, 'https://pan.baidu.com/s/1yK48icxsOXKrAl4Z8YDRAg', 'fht8', 1);
INSERT INTO `material` VALUES (3, 40, 43, 'https://pan.baidu.com/s/1yK4KRAg8icrAl4Z8YDxsOX', '3p5g', 1);
INSERT INTO `material` VALUES (4, 47, 44, 'https://pan.baidu.com/s/18DRAgOXKicxsZ8YrAlyK44', 'af3j', 1);

-- ----------------------------
-- Table structure for register
-- ----------------------------
DROP TABLE IF EXISTS `register`;
CREATE TABLE `register`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `tid` int(11) NULL DEFAULT NULL COMMENT '队伍ID',
  `uid` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  `isCaptain` tinyint(1) NOT NULL COMMENT '是否为队长',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 63 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of register
-- ----------------------------
INSERT INTO `register` VALUES (18, 35, 12, 1);
INSERT INTO `register` VALUES (19, 36, 12, 1);
INSERT INTO `register` VALUES (20, 37, 12, 1);
INSERT INTO `register` VALUES (21, 38, 12, 1);
INSERT INTO `register` VALUES (23, 40, 12, 1);
INSERT INTO `register` VALUES (24, 41, 12, 1);
INSERT INTO `register` VALUES (25, 42, 12, 1);
INSERT INTO `register` VALUES (26, 35, 15, 0);
INSERT INTO `register` VALUES (27, 35, 26, 0);
INSERT INTO `register` VALUES (28, 35, 2, 0);
INSERT INTO `register` VALUES (29, 35, 3, 0);
INSERT INTO `register` VALUES (30, 36, 3, 0);
INSERT INTO `register` VALUES (32, 36, 15, 0);
INSERT INTO `register` VALUES (33, 36, 16, 0);
INSERT INTO `register` VALUES (34, 38, 16, 0);
INSERT INTO `register` VALUES (35, 43, 14, 1);
INSERT INTO `register` VALUES (36, 43, 2, 0);
INSERT INTO `register` VALUES (37, 43, 13, 0);
INSERT INTO `register` VALUES (38, 44, 12, 1);
INSERT INTO `register` VALUES (39, 44, 13, 0);
INSERT INTO `register` VALUES (40, 44, 3, 0);
INSERT INTO `register` VALUES (41, 45, 12, 1);
INSERT INTO `register` VALUES (42, 45, 3, 0);
INSERT INTO `register` VALUES (43, 46, 21, 1);
INSERT INTO `register` VALUES (44, 46, 2, 0);

-- ----------------------------
-- Table structure for team
-- ----------------------------
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `cid` int(11) NULL DEFAULT NULL COMMENT '比赛ID',
  `team_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '队伍名称',
  `captain_uid` int(11) NULL DEFAULT NULL COMMENT '队长UID',
  `enabled` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否通过审核',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of team
-- ----------------------------
INSERT INTO `team` VALUES (35, 1, '光明使者队', 12, 1);
INSERT INTO `team` VALUES (36, 2, '冒险家联盟队', 12, 0);
INSERT INTO `team` VALUES (37, 3, '勇敢的海盗队', 12, 0);
INSERT INTO `team` VALUES (38, 6, '战斗机器人队', 12, 0);
INSERT INTO `team` VALUES (40, 7, '牛逼联盟队', 12, 0);
INSERT INTO `team` VALUES (41, 5, '紫色幻想队', 12, 0);
INSERT INTO `team` VALUES (42, 28, '宇宙探险家队', 12, 0);
INSERT INTO `team` VALUES (43, 40, '小萌冲冲冲队', 14, 1);
INSERT INTO `team` VALUES (44, 47, '信工一队', 12, 1);
INSERT INTO `team` VALUES (45, 41, '测试队伍66', 12, 1);
INSERT INTO `team` VALUES (46, 48, '指导老师演示队伍', 21, 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系方式',
  `role` tinyint(1) NULL DEFAULT NULL COMMENT '角色（0管理员，1教师，2学生）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '系统管理员', 'admin', '123', '18565558897', 0);
INSERT INTO `user` VALUES (2, '陈丹', 'teacher1', '123', '18795556693', 1);
INSERT INTO `user` VALUES (3, '章涛', 'zt', '123', '15698887795', 1);
INSERT INTO `user` VALUES (4, '张强', 'teacher3', '123', '15612347795', 1);
INSERT INTO `user` VALUES (5, '王玉', 'teacher4', '123', '14785559936', 1);
INSERT INTO `user` VALUES (6, '刘华', 'teacher5', '123', '15644442252', 1);
INSERT INTO `user` VALUES (7, '赵明', 'teacher6', '123', '15644441234', 1);
INSERT INTO `user` VALUES (8, '孙翔', 'teacher7', '123', '18979994478', 1);
INSERT INTO `user` VALUES (9, '徐琳', 'teacher8', '123', '15648887741', 1);
INSERT INTO `user` VALUES (10, '马骏', 'teacher9', '123', '13695557742', 1);
INSERT INTO `user` VALUES (11, '朱宁', 'teacher10', '123', '18565558897', 1);
INSERT INTO `user` VALUES (12, '邹川瑞', 'zcr', '123', '18795556693', 2);
INSERT INTO `user` VALUES (13, '王思源', 'wsy', '123', '15698887795', 2);
INSERT INTO `user` VALUES (14, '李骁蒙', 'lxm', '123', '15612347795', 2);
INSERT INTO `user` VALUES (15, '吴奕涵', 'wyh', '123', '14785559936', 2);
INSERT INTO `user` VALUES (16, '郭胜航', 'gsh', '123', '15644442252', 2);
INSERT INTO `user` VALUES (17, '孙梦婷', 'student6', '123', '15644441234', 2);
INSERT INTO `user` VALUES (18, '徐云龙', 'student7', '123', '18979994478', 2);
INSERT INTO `user` VALUES (19, '马天一', 'student8', '123', '15648887741', 2);
INSERT INTO `user` VALUES (20, '朱小花', 'student9', '123', '13695557742', 2);
INSERT INTO `user` VALUES (21, '李大炮', 'student10', '123', '18565558897', 2);
INSERT INTO `user` VALUES (22, '陈筱雨', 'student11', '123', '18795556693', 2);
INSERT INTO `user` VALUES (23, '张辰阳', 'student12', '123', '15612347795', 2);
INSERT INTO `user` VALUES (24, '王露茜', 'student13', '123', '14785559936', 2);
INSERT INTO `user` VALUES (25, '刘倩倩', 'student14', '123', '15644442252', 2);
INSERT INTO `user` VALUES (26, '赵伟民', 'student15', '123', '15644441234', 2);
INSERT INTO `user` VALUES (27, '孙文超', 'student16', '123', '18979994478', 2);
INSERT INTO `user` VALUES (28, '徐志强', 'student17', '123', '15648887741', 2);
INSERT INTO `user` VALUES (29, '马小龙', 'student18', '123', '18565558897', 2);
INSERT INTO `user` VALUES (30, '朱婷婷', 'student19', '123', '18795556693', 2);
INSERT INTO `user` VALUES (31, '李亚男', 'student20', '123', '15698887795', 2);
INSERT INTO `user` VALUES (32, '演示用户1', 'demo1', '123', 'demo888', 1);
INSERT INTO `user` VALUES (33, '演示用户2', 'demo2', '123', 'demo666', 2);

SET FOREIGN_KEY_CHECKS = 1;
