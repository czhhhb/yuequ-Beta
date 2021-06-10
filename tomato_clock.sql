/*
Navicat MySQL Data Transfer

Source Server         : zjz
Source Server Version : 80023
Source Host           : localhost:3306
Source Database       : yuequ

Target Server Type    : MYSQL
Target Server Version : 80023
File Encoding         : 65001

Date: 2021-06-10 16:35:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tomato_clock
-- ----------------------------
DROP TABLE IF EXISTS `tomato_clock`;
CREATE TABLE `tomato_clock` (
  `tomato_id` int NOT NULL AUTO_INCREMENT,
  `USER_ID` varchar(255) NOT NULL,
  `start_time` datetime NOT NULL,
  `time` int DEFAULT NULL,
  `tomato_state` int DEFAULT NULL,
  PRIMARY KEY (`tomato_id`,`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of tomato_clock
-- ----------------------------
INSERT INTO `tomato_clock` VALUES ('1', '123', '2021-06-09 15:14:31', '15', '0');
INSERT INTO `tomato_clock` VALUES ('2', '1234', '2021-06-09 15:35:58', '10000', '1');
INSERT INTO `tomato_clock` VALUES ('3', 'oVOkl5QKidhAOyZfgrx4xWYg72ns', '2021-06-09 15:36:27', '1620', '0');
INSERT INTO `tomato_clock` VALUES ('4', 'oVOkl5QKidhAOyZfgrx4xWYg72ns', '2021-06-09 15:49:35', '1500', '0');
INSERT INTO `tomato_clock` VALUES ('5', 'oVOkl5QKidhAOyZfgrx4xWYg72ns', '2021-06-09 15:50:42', '0', '0');
INSERT INTO `tomato_clock` VALUES ('6', 'oVOkl5QKidhAOyZfgrx4xWYg72ns', '2021-06-09 15:51:12', '0', '0');
INSERT INTO `tomato_clock` VALUES ('7', 'oVOkl5QKidhAOyZfgrx4xWYg72ns', '2021-06-09 15:51:29', '718', '0');
INSERT INTO `tomato_clock` VALUES ('8', 'oVOkl5QKidhAOyZfgrx4xWYg72ns', '2021-06-09 15:51:34', '713', '0');
INSERT INTO `tomato_clock` VALUES ('9', 'oVOkl5QKidhAOyZfgrx4xWYg72ns', '2021-06-09 15:59:43', '224', '0');
INSERT INTO `tomato_clock` VALUES ('10', 'oVOkl5QKidhAOyZfgrx4xWYg72ns', '2021-06-09 15:59:54', '3', '0');
INSERT INTO `tomato_clock` VALUES ('11', 'oVOkl5QKidhAOyZfgrx4xWYg72ns', '2021-06-09 16:00:14', '193', '0');
INSERT INTO `tomato_clock` VALUES ('12', 'oVOkl5QKidhAOyZfgrx4xWYg72ns', '2021-06-09 16:04:58', '30', '0');
INSERT INTO `tomato_clock` VALUES ('13', 'oVOkl5QKidhAOyZfgrx4xWYg72ns', '2021-06-09 16:06:49', '1500', '0');
INSERT INTO `tomato_clock` VALUES ('14', 'oVOkl5QKidhAOyZfgrx4xWYg72ns', '2021-06-09 16:07:35', '1454', '0');
INSERT INTO `tomato_clock` VALUES ('15', 'oVOkl5QKidhAOyZfgrx4xWYg72ns', '2021-06-09 16:08:02', '1427', '0');
INSERT INTO `tomato_clock` VALUES ('16', 'oVOkl5QKidhAOyZfgrx4xWYg72ns', '2021-06-09 16:08:40', '1500', '0');
INSERT INTO `tomato_clock` VALUES ('17', 'oVOkl5QKidhAOyZfgrx4xWYg72ns', '2021-06-09 16:08:44', '1496', '0');
INSERT INTO `tomato_clock` VALUES ('18', 'oVOkl5QKidhAOyZfgrx4xWYg72ns', '2021-06-09 16:08:51', '1489', '0');
INSERT INTO `tomato_clock` VALUES ('19', 'oVOkl5QKidhAOyZfgrx4xWYg72ns', '2021-06-09 16:09:33', '1500', '0');
INSERT INTO `tomato_clock` VALUES ('20', 'oVOkl5QKidhAOyZfgrx4xWYg72ns', '2021-06-09 16:09:58', '1475', '0');
INSERT INTO `tomato_clock` VALUES ('21', 'oVOkl5QKidhAOyZfgrx4xWYg72ns', '2021-06-09 16:10:03', '1471', '0');
INSERT INTO `tomato_clock` VALUES ('22', 'oVOkl5QKidhAOyZfgrx4xWYg72ns', '2021-06-09 16:10:10', '1500', '0');
INSERT INTO `tomato_clock` VALUES ('23', 'oVOkl5QKidhAOyZfgrx4xWYg72ns', '2021-06-09 16:10:19', '1491', '0');
