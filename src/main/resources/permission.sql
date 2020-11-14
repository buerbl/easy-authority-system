/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : dev

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 14/11/2020 16:14:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称',
  `type_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限路径',
  `type_pid` int(11) NOT NULL COMMENT '权限父节点',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role_permisson
-- ----------------------------
DROP TABLE IF EXISTS `role_permisson`;
CREATE TABLE `role_permisson`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `permission_id` int(11) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `adress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地址',
  `status` tinyint(255) NOT NULL COMMENT '状态 物理删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1000010 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;


INSERT INTO `dev`.`permission`(`id`, `type_name`, `type_path`, `type_pid`) VALUES (1, '用户管理', '', 0);
INSERT INTO `dev`.`permission`(`id`, `type_name`, `type_path`, `type_pid`) VALUES (2, '用户列表', '/user', 1);
INSERT INTO `dev`.`permission`(`id`, `type_name`, `type_path`, `type_pid`) VALUES (3, '权限管理', '', 0);
INSERT INTO `dev`.`permission`(`id`, `type_name`, `type_path`, `type_pid`) VALUES (4, '角色列表', '/role', 3);
INSERT INTO `dev`.`permission`(`id`, `type_name`, `type_path`, `type_pid`) VALUES (5, '权限列表', '/role-manage', 3);
INSERT INTO `dev`.`permission`(`id`, `type_name`, `type_path`, `type_pid`) VALUES (6, '添加用户', 'user-add', 2);
INSERT INTO `dev`.`permission`(`id`, `type_name`, `type_path`, `type_pid`) VALUES (7, '删除用户', 'user-del', 2);
INSERT INTO `dev`.`permission`(`id`, `type_name`, `type_path`, `type_pid`) VALUES (8, '添加角色', 'role-add', 4);
INSERT INTO `dev`.`permission`(`id`, `type_name`, `type_path`, `type_pid`) VALUES (9, '删除角色', 'role-del', 4);
INSERT INTO `dev`.`permission`(`id`, `type_name`, `type_path`, `type_pid`) VALUES (10, '修改角色', 'role-update', 4);
INSERT INTO `dev`.`permission`(`id`, `type_name`, `type_path`, `type_pid`) VALUES (11, '修改用户', 'user-update', 2);

INSERT INTO `dev`.`role`(`id`, `role_name`) VALUES (1, 'admin');
INSERT INTO `dev`.`role`(`id`, `role_name`) VALUES (2, 'normal');

INSERT INTO `dev`.`role_permisson`(`id`, `role_id`, `permission_id`) VALUES (1, 1, 1);
INSERT INTO `dev`.`role_permisson`(`id`, `role_id`, `permission_id`) VALUES (2, 1, 2);
INSERT INTO `dev`.`role_permisson`(`id`, `role_id`, `permission_id`) VALUES (3, 1, 3);
INSERT INTO `dev`.`role_permisson`(`id`, `role_id`, `permission_id`) VALUES (4, 1, 4);
INSERT INTO `dev`.`role_permisson`(`id`, `role_id`, `permission_id`) VALUES (5, 1, 5);
INSERT INTO `dev`.`role_permisson`(`id`, `role_id`, `permission_id`) VALUES (6, 1, 6);
INSERT INTO `dev`.`role_permisson`(`id`, `role_id`, `permission_id`) VALUES (7, 1, 7);
INSERT INTO `dev`.`role_permisson`(`id`, `role_id`, `permission_id`) VALUES (8, 1, 8);
INSERT INTO `dev`.`role_permisson`(`id`, `role_id`, `permission_id`) VALUES (9, 1, 9);
INSERT INTO `dev`.`role_permisson`(`id`, `role_id`, `permission_id`) VALUES (10, 1, 10);
INSERT INTO `dev`.`role_permisson`(`id`, `role_id`, `permission_id`) VALUES (14, 2, 1);
INSERT INTO `dev`.`role_permisson`(`id`, `role_id`, `permission_id`) VALUES (15, 2, 2);
INSERT INTO `dev`.`role_permisson`(`id`, `role_id`, `permission_id`) VALUES (16, 1, 11);

INSERT INTO `dev`.`user`(`id`, `name`, `password`, `adress`, `status`) VALUES (1, 'root', '123456', '中国', 1);
INSERT INTO `dev`.`user`(`id`, `name`, `password`, `adress`, `status`) VALUES (2, 'normal', '123456', '中国', 0);
INSERT INTO `dev`.`user`(`id`, `name`, `password`, `adress`, `status`) VALUES (3, 'a3', 'b3', '中国', 1);
INSERT INTO `dev`.`user`(`id`, `name`, `password`, `adress`, `status`) VALUES (4, 'a4', 'b4', '中国', 1);
INSERT INTO `dev`.`user`(`id`, `name`, `password`, `adress`, `status`) VALUES (5, 'a5', 'b5', '中国', 0);

INSERT INTO `dev`.`user_role`(`id`, `user_id`, `role_id`) VALUES (1, 1, 1);
INSERT INTO `dev`.`user_role`(`id`, `user_id`, `role_id`) VALUES (2, 2, 2);
INSERT INTO `dev`.`user_role`(`id`, `user_id`, `role_id`) VALUES (3, 1, 2);
