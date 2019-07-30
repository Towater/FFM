

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bank_account_info
-- ----------------------------
DROP TABLE IF EXISTS `bank_account_info`;
CREATE TABLE `bank_account_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `accountNumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '银行账户',
  `openBank` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户行',
  `
balance` decimal(10, 0) NOT NULL DEFAULT 0 COMMENT '余额',
  `userId` bigint(20) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for family_info
-- ----------------------------
DROP TABLE IF EXISTS `family_info`;
CREATE TABLE `family_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `familyMembers` int(3) NOT NULL COMMENT '家庭成员数量',
  `managerId` bigint(20) NOT NULL COMMENT '管理员Id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of family_info
-- ----------------------------
INSERT INTO `family_info` VALUES (8, 1, 13);
INSERT INTO `family_info` VALUES (9, 3, 14);
INSERT INTO `family_info` VALUES (11, 1, 18);
INSERT INTO `family_info` VALUES (22, 1, 43);
INSERT INTO `family_info` VALUES (23, 1, 44);
INSERT INTO `family_info` VALUES (24, 1, 45);
INSERT INTO `family_info` VALUES (25, 2, 4);

-- ----------------------------
-- Table structure for income_info
-- ----------------------------
DROP TABLE IF EXISTS `income_info`;
CREATE TABLE `income_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `quota` decimal(10, 0) NOT NULL COMMENT '金额大小',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收入类型',
  `userid` bigint(20) NOT NULL COMMENT '收入者',
  `incomeDate` date NOT NULL COMMENT '收入日期',
  `incomeRoot` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收入源',
  `remakers` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '相关备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 229 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of income_info
-- ----------------------------
INSERT INTO `income_info` VALUES (15, 728, '支付宝', 4, '2020-01-10', 'Gouda', '907-8725 Integer St.');
INSERT INTO `income_info` VALUES (16, 510, '微信', 4, '1919-01-23', 'Henis', 'P.O. Box 329, 3520 Rutrum Av.');
INSERT INTO `income_info` VALUES (17, 722, '银行卡', 4, '1818-01-26', 'Los Sauces', '4379 Massa. Street');
INSERT INTO `income_info` VALUES (20, 158, '微信', 4, '2020-01-22', 'Prato Carnico', 'P.O. Box 353, 9117 Fusce Avenue');
INSERT INTO `income_info` VALUES (24, 439, '现金', 4, '1919-01-28', 'Enterprise', '3692 Eu Avenue');
INSERT INTO `income_info` VALUES (25, 475, '其他', 4, '2020-01-23', 'Luzzara', '6734 Sagittis. Road');
INSERT INTO `income_info` VALUES (26, 212, '现金', 4, '1919-01-21', 'Heerhugowaard', '7552 Phasellus Road');
INSERT INTO `income_info` VALUES (27, 821, '现金', 201601, '1818-01-20', 'Buckley', '2079 Facilisis St.');
INSERT INTO `income_info` VALUES (28, 460, 'Nostra Per Corporation支付宝\r\n微信\r\n银行卡\r\n微信\r\n现金\r\n其他\r\n现金\r\n现金', 201601, '1919-12-17', 'Elen', 'P.O. Box 348, 3502 Lacus. Road');
INSERT INTO `income_info` VALUES (29, 739, 'Praesent Eu Institute', 201601, '1818-12-15', 'Vlezenbeek', '608 Vitae Avenue');
INSERT INTO `income_info` VALUES (30, 210, 'Dui Nec Incorporated', 201601, '1818-12-03', 'Enns', 'Ap #895-4670 Eget Rd.');
INSERT INTO `income_info` VALUES (31, 704, 'Lacus Quisque Purus Corp.', 201601, '2020-01-26', 'Wierde', 'Ap #489-8253 Natoque St.');
INSERT INTO `income_info` VALUES (32, 34, 'Dapibus Corporation', 201601, '1919-06-28', 'Rixensart', '4048 Felis Street');
INSERT INTO `income_info` VALUES (33, 340, 'Porttitor Corp.', 201601, '1818-08-19', 'Kędzierzyn-Koźle', '827-898 Sapien. Rd.');
INSERT INTO `income_info` VALUES (35, 1, 'Suspendisse Non Limited', 201601, '1818-11-27', 'Bunbury', 'Ap #823-5648 In Avenue');
INSERT INTO `income_info` VALUES (36, 401, 'Commodo Tincidunt Nibh Ltd', 201601, '2020-06-12', 'Campagna', '592 Duis Avenue');
INSERT INTO `income_info` VALUES (37, 874, 'Odio Phasellus Corp.', 201601, '2020-05-29', 'Lota', 'P.O. Box 183, 9909 Suspendisse Road');
INSERT INTO `income_info` VALUES (38, 466, 'Ipsum Suspendisse Sagittis Consulting', 201601, '1919-04-21', 'Colwood', '904-8378 Cubilia Rd.');
INSERT INTO `income_info` VALUES (39, 198, 'Ut Inc.', 201601, '1919-01-10', 'Gmunden', 'P.O. Box 537, 670 In Ave');
INSERT INTO `income_info` VALUES (40, 745, 'Scelerisque Scelerisque Corp.', 201601, '1919-09-08', 'Mannheim', 'P.O. Box 756, 2495 Sapien. Road');
INSERT INTO `income_info` VALUES (41, 613, 'Magnis LLC', 201601, '1919-01-06', 'Cantley', '8039 Elementum, Ave');
INSERT INTO `income_info` VALUES (42, 295, 'Risus LLP', 201601, '1919-11-10', 'Burlington', '186-6090 Dolor. Rd.');
INSERT INTO `income_info` VALUES (43, 699, 'Interdum Associates', 201601, '2020-05-14', 'Villarrica', 'Ap #249-8621 Enim Street');
INSERT INTO `income_info` VALUES (44, 950, 'Interdum Ligula Consulting', 201601, '1818-10-31', 'Dawson Creek', '4500 Erat Rd.');
INSERT INTO `income_info` VALUES (45, 830, 'Turpis Non Enim Corporation', 201601, '1919-06-22', 'Chicago', '924-7217 Facilisis St.');
INSERT INTO `income_info` VALUES (46, 26, 'Proin Sed Turpis Associates', 201601, '1919-04-19', 'Didim', 'P.O. Box 264, 5254 Vehicula. Street');
INSERT INTO `income_info` VALUES (47, 1411, 'Natoque Institute', 201601, '1919-01-24', 'Bever Bievene', 'P.O. Box 136, 763 Luctus Rd.');
INSERT INTO `income_info` VALUES (48, 639, 'Interdum Incorporated', 201601, '1818-10-11', 'Le Puy-en-Velay', '2753 Ante. Avenue');
INSERT INTO `income_info` VALUES (49, 877, 'Blandit Consulting', 201601, '1919-09-16', 'Gandhidham', 'Ap #531-5055 Nunc St.');
INSERT INTO `income_info` VALUES (50, 905, 'Risus Donec LLC', 201601, '1818-12-04', 'Pisa', '455-5723 Sapien. Av.');
INSERT INTO `income_info` VALUES (51, 990, 'Elit Etiam Ltd', 201601, '2020-05-12', 'Blois', '6212 Risus. Rd.');
INSERT INTO `income_info` VALUES (52, 247, 'Egestas Company', 201601, '1818-10-15', 'Palanzano', 'P.O. Box 889, 9553 Sem Avenue');
INSERT INTO `income_info` VALUES (53, 366, 'Velit Aliquam Nisl Institute', 201601, '1818-09-04', 'Saint-Pierre', '5758 Ornare St.');
INSERT INTO `income_info` VALUES (54, 84, 'Ipsum Suspendisse Sagittis Consulting', 201601, '1919-02-06', 'Venezia', 'P.O. Box 234, 8928 Pellentesque Av.');
INSERT INTO `income_info` VALUES (55, 557, 'Tincidunt Pede Ltd', 201601, '2020-05-08', 'Daly', '823-5484 Sit Road');
INSERT INTO `income_info` VALUES (56, 645, 'Sociis Natoque Penatibus Limited', 201601, '1919-05-21', 'Nuremberg', '180 Enim. Av.');
INSERT INTO `income_info` VALUES (57, 753, 'Feugiat Placerat Limited', 201601, '2020-05-03', 'Apeldoorn', 'P.O. Box 690, 5597 Sed Road');
INSERT INTO `income_info` VALUES (58, 435, 'Convallis Convallis Foundation', 201601, '2020-02-12', 'Temploux', 'P.O. Box 725, 3430 Nec Ave');
INSERT INTO `income_info` VALUES (59, 242, 'Nulla Corp.', 201601, '1818-10-18', 'Baunatal', '4568 Gravida. St.');
INSERT INTO `income_info` VALUES (60, 224, 'Risus At Fringilla LLP', 201601, '1919-10-04', 'Taber', 'P.O. Box 697, 426 Felis. St.');
INSERT INTO `income_info` VALUES (61, 845, 'Eleifend Vitae Erat Incorporated', 201601, '1919-07-28', 'Bad Ischl', '5088 Lacus. St.');
INSERT INTO `income_info` VALUES (62, 738, 'Commodo Hendrerit LLP', 201601, '1919-06-19', 'Ananindeua', 'Ap #636-5735 Morbi Av.');
INSERT INTO `income_info` VALUES (63, 141, 'Libero Donec Consectetuer Consulting', 201601, '2020-06-19', 'Rae-Edzo', 'P.O. Box 947, 1393 Dolor Rd.');
INSERT INTO `income_info` VALUES (64, 643, 'In Consectetuer Ipsum PC', 201601, '1919-04-19', 'Coihaique', 'Ap #251-1636 Risus. Rd.');
INSERT INTO `income_info` VALUES (65, 939, 'Nam Consequat Dolor Corporation', 201601, '2020-03-29', 'Stornaway', 'P.O. Box 572, 3452 Eu St.');
INSERT INTO `income_info` VALUES (66, 122, 'Sem Consulting', 201601, '1818-11-09', 'Topeka', '503-5867 Lacinia. Rd.');
INSERT INTO `income_info` VALUES (70, 113, 'Curabitur Ut Odio Corp.', 201601, '1919-03-01', 'Poggiodomo', '9187 Aenean Avenue');
INSERT INTO `income_info` VALUES (71, 666, 'Phasellus Limited', 201601, '1919-04-08', 'Ede', 'Ap #926-5654 Ipsum Av.');
INSERT INTO `income_info` VALUES (72, 482, 'Elementum Dui Quis Ltd', 201601, '1818-12-20', 'Kearny', 'P.O. Box 588, 6638 Dolor Ave');
INSERT INTO `income_info` VALUES (73, 739, 'Et Consulting', 201601, '1818-10-14', 'Elen', '3731 Nunc Av.');
INSERT INTO `income_info` VALUES (74, 586, 'Feugiat Institute', 201601, '1818-08-12', 'Sooke', 'Ap #681-2452 Enim Avenue');
INSERT INTO `income_info` VALUES (75, 405, 'Venenatis PC', 201601, '1919-05-07', 'LaSalle', 'P.O. Box 432, 5850 Aliquet Av.');
INSERT INTO `income_info` VALUES (76, 393, 'Mauris Quis Limited', 201601, '2020-01-06', 'Morhet', '8773 Iaculis Av.');
INSERT INTO `income_info` VALUES (77, 609, 'Dui Suspendisse Ac Consulting', 201601, '1818-08-18', 'Goes', 'P.O. Box 542, 9385 Id, Rd.');
INSERT INTO `income_info` VALUES (78, 313, 'Eros LLP', 201601, '1919-01-25', 'Sint-Pauwels', 'P.O. Box 737, 9168 Nulla Ave');
INSERT INTO `income_info` VALUES (79, 112, 'Rutrum Urna Foundation', 201601, '1919-07-09', 'Neunkirchen', 'P.O. Box 727, 7120 Ac Street');
INSERT INTO `income_info` VALUES (80, 657, 'Conubia LLP', 201601, '1919-03-08', 'Ludlow', '3741 Luctus Av.');
INSERT INTO `income_info` VALUES (81, 666, 'Phasellus Corp.', 201601, '1818-10-06', 'Brandenburg', '5606 Luctus Ave');
INSERT INTO `income_info` VALUES (82, 351, 'Cursus A Incorporated', 201601, '1818-07-19', 'Gandhinagar', '501-6296 Sed Av.');
INSERT INTO `income_info` VALUES (83, 919, 'Ullamcorper Nisl Institute', 201601, '1919-05-19', 'Toruń', '469 Rutrum Avenue');
INSERT INTO `income_info` VALUES (84, 811, 'Phasellus At Augue Corp.', 201601, '1919-08-28', 'Sanluri', 'P.O. Box 605, 2373 Arcu. Rd.');
INSERT INTO `income_info` VALUES (85, 364, 'Quam Dignissim LLC', 201601, '1919-08-11', 'Seilles', '241-5973 A, St.');
INSERT INTO `income_info` VALUES (86, 420, 'Suscipit Nonummy Incorporated', 201601, '1919-02-12', 'San Vicente', '379-2367 Facilisis St.');
INSERT INTO `income_info` VALUES (88, 331, 'Eget Mollis Ltd', 201601, '1919-07-13', 'Kailua', '4195 Porttitor St.');
INSERT INTO `income_info` VALUES (89, 282, 'Metus Eu Erat LLP', 201601, '1919-01-24', 'Baileux', 'Ap #973-4966 Consectetuer Ave');
INSERT INTO `income_info` VALUES (90, 215, 'Vulputate Posuere LLC', 201601, '1919-09-08', 'Prince Albert', 'Ap #979-793 Pulvinar Street');
INSERT INTO `income_info` VALUES (91, 503, 'Nunc Company', 201601, '1919-03-09', 'Talca', 'P.O. Box 436, 9360 Aliquet Rd.');
INSERT INTO `income_info` VALUES (92, 710, 'Neque Nullam Ut Company', 201601, '2020-02-25', 'Santo Domingo', 'Ap #227-7678 Lectus, Av.');
INSERT INTO `income_info` VALUES (93, 565, '微信', 201601, '1818-01-30', 'Werder', '8730 Phasellus Road');
INSERT INTO `income_info` VALUES (95, 104, 'Suspendisse Eleifend PC', 201601, '1818-11-24', 'Würzburg', 'P.O. Box 619, 1902 Tellus. Rd.');
INSERT INTO `income_info` VALUES (96, 664, 'Aliquet Vel Industries', 201601, '1818-09-28', 'Maizeret', 'Ap #766-7902 Lobortis Av.');
INSERT INTO `income_info` VALUES (97, 842, 'Nec Ante Corp.', 201601, '1818-08-17', 'Niterói', '550-3592 Magna Av.');
INSERT INTO `income_info` VALUES (98, 61, 'Non Cursus Non PC', 201601, '1818-10-08', 'Agra', '595-7976 Tellus St.');
INSERT INTO `income_info` VALUES (99, 426, 'Nibh Limited', 201601, '2020-01-17', 'Sulzano', '743-607 Nunc Ave');
INSERT INTO `income_info` VALUES (100, 51, 'Enim Mauris Quis Ltd', 201601, '2020-04-07', 'Tiverton', '892-8261 Nonummy Rd.');
INSERT INTO `income_info` VALUES (101, 231, 'Diam Duis Incorporated', 201601, '2020-04-16', 'Aschaffenburg', '632-7763 Pellentesque Av.');
INSERT INTO `income_info` VALUES (103, 383, 'Aliquam Adipiscing Lacus LLC', 201601, '1818-11-01', 'Edmundston', '5729 Euismod Avenue');
INSERT INTO `income_info` VALUES (104, 944, 'Mauris Company', 201601, '1919-10-25', 'Bridlington', 'P.O. Box 368, 4482 Non, Rd.');
INSERT INTO `income_info` VALUES (116, 5555, '微信', 222, '2019-06-30', 'zxZ', 'xzcz');
INSERT INTO `income_info` VALUES (117, 6666, '微信', 222, '2020-06-30', '卖菜', 'ssss');
INSERT INTO `income_info` VALUES (118, 999999, '银行卡', 222, '2019-07-12', '杀猪', '鱼鱼鱼');
INSERT INTO `income_info` VALUES (119, 7487, '现金', 222, '2019-07-19', '摆地摊', '哭唧唧');
INSERT INTO `income_info` VALUES (120, 9856, '银行卡', 201601, '2019-01-09', '调课', 'sds');
INSERT INTO `income_info` VALUES (121, 256, '微信', 201601, '2019-07-08', 'vue新增', '好多字');
INSERT INTO `income_info` VALUES (122, 969, '现金', 201601, '2019-07-15', 'vue再次新增', '又是我--');
INSERT INTO `income_info` VALUES (130, 0, '支付宝', 43, '1817-01-30', 'Castelbuono', '3641 Donec Av.');
INSERT INTO `income_info` VALUES (131, 52, 'Denmark', 43, '1919-12-30', 'Tournefeuille', 'Ap #858-8688 Pellentesque Avenue');
INSERT INTO `income_info` VALUES (132, 212, 'Tunisia', 43, '1818-08-05', 'Gboko', '540-4605 Tempus Av.');
INSERT INTO `income_info` VALUES (133, 102, 'Niger', 43, '1818-11-16', 'Santa María', '9295 Sed, Av.');
INSERT INTO `income_info` VALUES (134, 781, 'Ukraine', 43, '1818-12-15', 'Spokane', 'P.O. Box 204, 8199 Quis Road');
INSERT INTO `income_info` VALUES (135, 529, 'Syria', 43, '1919-12-04', 'Mundare', '8201 Elit St.');
INSERT INTO `income_info` VALUES (136, 746, 'Burkina Faso', 43, '1919-01-16', 'Märsta', '995-6765 Non, Street');
INSERT INTO `income_info` VALUES (137, 782, 'Marshall Islands', 43, '2020-01-30', 'Hudiksvall', '9067 Arcu. Street');
INSERT INTO `income_info` VALUES (138, 893, 'Cayman Islands', 43, '2020-03-17', 'San Pablo', '774-4191 At Street');
INSERT INTO `income_info` VALUES (139, 872, 'Åland Islands', 43, '1818-10-05', 'Pescopagano', '7547 Tincidunt, Rd.');
INSERT INTO `income_info` VALUES (140, 856, 'Nauru', 43, '1919-10-20', 'Cochrane', 'Ap #437-4294 Sem St.');
INSERT INTO `income_info` VALUES (141, 658, 'Singapore', 43, '2020-06-26', 'Zeveneken', '487-1102 Est. Street');
INSERT INTO `income_info` VALUES (142, 504, 'Maldives', 43, '1919-05-29', 'Ollolai', '5504 Tristique Road');
INSERT INTO `income_info` VALUES (143, 426, 'Algeria', 43, '1919-10-11', 'Monte Patria', 'P.O. Box 513, 9400 Nam Ave');
INSERT INTO `income_info` VALUES (144, 498, 'Ethiopia', 43, '1919-10-25', 'Newmarket', '211-9727 Mauris. Street');
INSERT INTO `income_info` VALUES (145, 755, 'Mauritius', 43, '1818-10-10', 'Chicoutimi', 'Ap #328-790 Eu Av.');
INSERT INTO `income_info` VALUES (146, 644, 'Chad', 43, '1919-02-21', 'Karlsruhe', '2447 Et Av.');
INSERT INTO `income_info` VALUES (147, 174, 'Indonesia', 43, '1919-04-12', 'Kasterlee', 'Ap #591-545 Semper. Avenue');
INSERT INTO `income_info` VALUES (148, 16, 'Eritrea', 43, '2020-01-10', 'San Giorgio Albanese', '529-4628 Quis, Rd.');
INSERT INTO `income_info` VALUES (149, 874, 'Cayman Islands', 43, '1818-11-10', 'Montpelier', '3414 A, Av.');
INSERT INTO `income_info` VALUES (150, 932, 'Bahamas', 43, '1818-09-28', 'Bothey', '8367 Gravida. Rd.');
INSERT INTO `income_info` VALUES (151, 174, 'Madagascar', 43, '2020-05-25', 'Cochrane', '554-6264 Eu Street');
INSERT INTO `income_info` VALUES (152, 430, 'Northern Mariana Islands', 43, '2020-04-21', 'Barrhead', 'Ap #523-317 Euismod St.');
INSERT INTO `income_info` VALUES (153, 38, 'Syria', 43, '2020-05-31', 'Le Havre', 'Ap #700-1578 Faucibus St.');
INSERT INTO `income_info` VALUES (154, 695, 'Western Sahara', 43, '1919-03-25', 'Offenburg', 'Ap #159-724 Lectus, Road');
INSERT INTO `income_info` VALUES (155, 218, 'Western Sahara', 43, '2020-01-02', 'Leval-Chaudeville', '3659 Eget, Av.');
INSERT INTO `income_info` VALUES (156, 111, 'Monaco', 43, '1919-08-05', 'Piracicaba', 'Ap #798-6330 In, Rd.');
INSERT INTO `income_info` VALUES (157, 51, 'Algeria', 43, '2020-03-03', 'Perth', '6044 Fringilla St.');
INSERT INTO `income_info` VALUES (158, 495, 'Holy See (Vatican City State)', 43, '2020-01-15', 'Linton', 'P.O. Box 549, 1988 Duis St.');
INSERT INTO `income_info` VALUES (159, 288, 'Germany', 43, '2020-01-23', 'Ladispoli', '375-7482 Lorem, Av.');
INSERT INTO `income_info` VALUES (160, 393, 'Panama', 43, '1919-08-24', 'Scarborough', 'Ap #518-3482 Natoque St.');
INSERT INTO `income_info` VALUES (161, 119, 'Sudan', 43, '1818-10-04', 'Colomiers', 'Ap #966-5404 Montes, Avenue');
INSERT INTO `income_info` VALUES (162, 109, 'Monaco', 43, '2020-06-27', 'Siliguri', '5380 Dui, Ave');
INSERT INTO `income_info` VALUES (163, 405, 'Guyana', 43, '1919-09-28', 'Chiusa/Klausen', 'P.O. Box 494, 2717 Nulla. Street');
INSERT INTO `income_info` VALUES (164, 489, 'Aruba', 43, '1919-10-01', 'Dorval', 'Ap #657-4811 Habitant Street');
INSERT INTO `income_info` VALUES (165, 550, 'Åland Islands', 43, '1818-07-22', 'Capena', 'P.O. Box 927, 1777 Ut Ave');
INSERT INTO `income_info` VALUES (166, 883, 'Peru', 43, '1818-08-06', 'Glendale', '558-7743 Orci. Avenue');
INSERT INTO `income_info` VALUES (167, 595, 'Panama', 43, '1919-06-11', 'Kenosha', 'P.O. Box 874, 9128 Semper St.');
INSERT INTO `income_info` VALUES (168, 465, 'Cyprus', 43, '1919-06-12', 'Lauro de Freitas', '658-6651 Phasellus Road');
INSERT INTO `income_info` VALUES (169, 662, 'Tunisia', 43, '2020-01-17', 'Pomarolo', 'P.O. Box 276, 1751 Ullamcorper, Ave');
INSERT INTO `income_info` VALUES (170, 762, 'Namibia', 43, '1919-05-07', 'Santa Juana', '813-3181 Aenean Street');
INSERT INTO `income_info` VALUES (171, 613, 'Singapore', 43, '1919-02-14', 'Lucknow', '3615 Erat St.');
INSERT INTO `income_info` VALUES (172, 89, 'China', 43, '1818-10-07', 'São Luís', 'P.O. Box 291, 9193 Natoque Av.');
INSERT INTO `income_info` VALUES (173, 116, 'Gibraltar', 43, '1818-11-11', 'Saint-Prime', 'Ap #134-6719 Sem St.');
INSERT INTO `income_info` VALUES (174, 700, 'Isle of Man', 43, '1919-02-06', 'Lewiston', '7471 Arcu. Ave');
INSERT INTO `income_info` VALUES (175, 482, 'Brunei', 43, '1818-12-04', 'King\'s Lynn', 'Ap #948-4133 Quis St.');
INSERT INTO `income_info` VALUES (176, 168, 'Belgium', 43, '2020-01-15', 'Coltauco', '644-7919 Vivamus Street');
INSERT INTO `income_info` VALUES (177, 1000, 'Laos', 43, '1919-11-07', 'Cupar', 'Ap #428-5520 Lacus. Street');
INSERT INTO `income_info` VALUES (178, 208, 'Tanzania', 43, '1818-01-05', 'Cetara', 'P.O. Box 641, 7733 In Ave');
INSERT INTO `income_info` VALUES (179, 484, 'Niger', 43, '2020-03-18', 'Koersel', '131-727 Ut, St.');
INSERT INTO `income_info` VALUES (180, 45, 'Comoros', 43, '2020-03-09', 'Donk', 'Ap #663-7386 Non Road');
INSERT INTO `income_info` VALUES (181, 223, 'Laos', 43, '2020-04-07', 'Vichuquén', 'Ap #373-6271 Vivamus Rd.');
INSERT INTO `income_info` VALUES (182, 830, 'Croatia', 43, '2020-04-01', 'Oud-Turnhout', '5500 Phasellus Road');
INSERT INTO `income_info` VALUES (183, 207, 'Pitcairn Islands', 43, '2020-06-23', 'Ponoka', '4553 Morbi St.');
INSERT INTO `income_info` VALUES (184, 305, 'Uganda', 43, '1919-02-09', 'Abbotsford', 'Ap #995-984 Ipsum Street');
INSERT INTO `income_info` VALUES (185, 686, 'Benin', 43, '1919-06-23', 'Panguipulli', 'P.O. Box 681, 7814 Mi Rd.');
INSERT INTO `income_info` VALUES (186, 523, 'Guinea-Bissau', 43, '2020-06-01', 'Maryborough', 'P.O. Box 541, 7870 Turpis. St.');
INSERT INTO `income_info` VALUES (187, 35, 'Colombia', 43, '2020-06-15', 'San Pedro', 'P.O. Box 579, 7049 Facilisis Avenue');
INSERT INTO `income_info` VALUES (188, 310, 'Slovakia', 43, '1919-01-12', 'Rocca Massima', '1132 Cras Street');
INSERT INTO `income_info` VALUES (189, 900, 'Austria', 43, '1919-07-09', 'Stralsund', 'P.O. Box 208, 5600 Scelerisque Road');
INSERT INTO `income_info` VALUES (190, 227, 'Korea, North', 43, '1818-09-20', 'Lagundo/Algund', '786-1426 Integer Street');
INSERT INTO `income_info` VALUES (191, 276, 'Madagascar', 43, '1919-08-11', 'Molino dei Torti', '8679 Vulputate Street');
INSERT INTO `income_info` VALUES (192, 731, 'Palestine, State of', 43, '1919-10-24', 'Calle Larga', 'P.O. Box 503, 7531 In Avenue');
INSERT INTO `income_info` VALUES (193, 141, 'Maldives', 43, '1818-08-08', 'Erchie', '685-4747 Libero. St.');
INSERT INTO `income_info` VALUES (194, 8, 'Croatia', 43, '1919-12-29', 'Muzaffargarh', 'P.O. Box 468, 4833 Congue St.');
INSERT INTO `income_info` VALUES (195, 809, 'Tokelau', 43, '1919-12-06', 'Pastena', '8665 Velit St.');
INSERT INTO `income_info` VALUES (196, 345, 'Turks and Caicos Islands', 43, '2020-04-15', 'Manukau', '3612 Felis Street');
INSERT INTO `income_info` VALUES (197, 77, 'Bhutan', 43, '1818-09-17', 'Scunthorpe', 'Ap #842-1954 Montes, Ave');
INSERT INTO `income_info` VALUES (198, 244, 'Guernsey', 43, '2020-04-29', 'Birkenhead', '965 Quis Ave');
INSERT INTO `income_info` VALUES (199, 581, 'Haiti', 43, '1919-10-08', 'Mannekensvere', '843-8132 Vitae Street');
INSERT INTO `income_info` VALUES (200, 150, 'Trinidad and Tobago', 43, '2020-07-19', 'Schleswig', '8223 A, Street');
INSERT INTO `income_info` VALUES (201, 183, 'Japan', 43, '1919-08-17', 'Houtvenne', '1868 Sagittis St.');
INSERT INTO `income_info` VALUES (203, 556, 'Lesotho', 43, '1919-10-14', 'San Mauro Marchesato', '5009 Elementum St.');
INSERT INTO `income_info` VALUES (204, 840, 'Greece', 43, '2020-03-12', 'Riksingen', '288-3029 Non Rd.');
INSERT INTO `income_info` VALUES (205, 226, 'Mozambique', 43, '2020-03-04', 'Aalst', '334-5871 Accumsan St.');
INSERT INTO `income_info` VALUES (206, 197, 'Saint Martin', 43, '1919-07-25', 'Moircy', 'Ap #352-7802 Nostra, Rd.');
INSERT INTO `income_info` VALUES (207, 583, 'Paraguay', 43, '1919-12-29', 'Sens', 'P.O. Box 616, 3032 Mauris Avenue');
INSERT INTO `income_info` VALUES (208, 338, 'Dominican Republic', 43, '1919-09-11', 'Courbevoie', 'P.O. Box 802, 4014 Aenean St.');
INSERT INTO `income_info` VALUES (209, 936, 'Djibouti', 43, '2020-07-11', 'Thisnes', 'Ap #242-6060 Fusce Rd.');
INSERT INTO `income_info` VALUES (210, 950, 'Cambodia', 43, '1919-01-01', 'Kaneohe', 'Ap #226-2444 Ligula Rd.');
INSERT INTO `income_info` VALUES (211, 755, 'Thailand', 43, '2020-04-16', 'Bay Roberts', '6396 Fusce St.');
INSERT INTO `income_info` VALUES (212, 166, 'Åland Islands', 43, '1919-12-09', 'Moircy', 'P.O. Box 681, 9362 Dictum Ave');
INSERT INTO `income_info` VALUES (213, 700, 'Egypt', 43, '1919-10-12', 'Mosciano Sant\'Angelo', 'P.O. Box 797, 9030 Aliquet. Rd.');
INSERT INTO `income_info` VALUES (214, 367, 'Kiribati', 43, '1919-10-21', 'Nil-Saint-Vincent-Saint-Martin', 'P.O. Box 686, 599 Lorem Rd.');
INSERT INTO `income_info` VALUES (215, 336, 'Mauritania', 43, '1919-10-24', 'Putaendo', 'Ap #971-7988 Eget, Street');
INSERT INTO `income_info` VALUES (216, 607, 'Botswana', 43, '1818-11-30', 'Clare', '101-9476 Quam, Road');
INSERT INTO `income_info` VALUES (217, 619, 'Dominica', 43, '1818-11-04', 'Stargard Szczeciński', 'Ap #579-3297 Lorem, Rd.');
INSERT INTO `income_info` VALUES (218, 142, 'Tunisia', 43, '1919-03-22', 'Darıca', '5386 Tellus Av.');
INSERT INTO `income_info` VALUES (219, 68, 'British Indian Ocean Territory', 43, '1818-07-20', 'Whitehorse', '958-1049 Ornare, Ave');
INSERT INTO `income_info` VALUES (220, 683, 'Burundi', 43, '1919-03-13', 'La Thuile', 'Ap #556-6592 Vehicula Rd.');
INSERT INTO `income_info` VALUES (221, 198, 'Cayman Islands', 43, '2020-02-29', 'Banbury', '7039 Cubilia Rd.');
INSERT INTO `income_info` VALUES (222, 315, 'Anguilla', 43, '1919-01-13', 'Cherbourg-Octeville', '5563 Cras Rd.');
INSERT INTO `income_info` VALUES (226, 5656, '微信', 43, '2019-07-15', 'tyty', 'trd65');
INSERT INTO `income_info` VALUES (227, 266, '微信', 43, '2019-07-08', 'dfg', 'dfgd');
INSERT INTO `income_info` VALUES (228, 321, '支付宝', 43, '2019-07-02', 'xx', 'sres');

-- ----------------------------
-- Table structure for pay_info
-- ----------------------------
DROP TABLE IF EXISTS `pay_info`;
CREATE TABLE `pay_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `quota` decimal(10, 0) NOT NULL COMMENT '金额大小',
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型',
  `userid` bigint(20) NOT NULL COMMENT '支出者',
  `payDate` date NOT NULL COMMENT '支出日期',
  `payFor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支出用途',
  `remakers` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '相关备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 112 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pay_info
-- ----------------------------
INSERT INTO `pay_info` VALUES (2, 155, '微信', 4, '2016-05-23', '饮料', 'nothing');
INSERT INTO `pay_info` VALUES (16, 551, 'Iran', 43, '1919-02-12', 'Nibh Vulputate Mauris Associates', 'Kruibeke');
INSERT INTO `pay_info` VALUES (18, 268, 'Nauru', 43, '1919-05-19', 'At Iaculis PC', 'Hofheim am Taunus');
INSERT INTO `pay_info` VALUES (19, 952, 'Iceland', 43, '2020-04-01', 'Lectus Corp.', 'Arendonk');
INSERT INTO `pay_info` VALUES (20, 832, 'Oman', 43, '1919-08-29', 'Nam Tempor Diam Corp.', 'Cervino');
INSERT INTO `pay_info` VALUES (22, 159, 'Saint Martin', 43, '1818-12-04', 'Est Congue A Institute', 'Strijtem');
INSERT INTO `pay_info` VALUES (23, 567, 'Ireland', 43, '1919-02-15', 'Vehicula Aliquet Incorporated', 'Oppido Mamertina');
INSERT INTO `pay_info` VALUES (24, 328, 'Trinidad and Tobago', 43, '2020-03-29', 'Dignissim Corporation', 'Mayerthorpe');
INSERT INTO `pay_info` VALUES (26, 340, 'Palestine, State of', 43, '2020-03-22', 'Varius Nam Porttitor Industries', 'Stevenage');
INSERT INTO `pay_info` VALUES (27, 287, 'Korea, South', 43, '2020-03-31', 'Nonummy Fusce Company', 'Malgesso');
INSERT INTO `pay_info` VALUES (30, 290, 'Mayotte', 43, '2020-07-05', 'Aenean Eget Ltd', 'Llandrindod Wells');
INSERT INTO `pay_info` VALUES (31, 206, 'Italy', 43, '1919-04-17', 'Nunc Est Mollis Corp.', 'Frankfurt');
INSERT INTO `pay_info` VALUES (32, 151, 'Saint Lucia', 43, '2020-02-27', 'Iaculis Lacus Incorporated', 'Maipú');
INSERT INTO `pay_info` VALUES (33, 895, 'Macedonia', 43, '1919-10-26', 'At Incorporated', 'Gonars');
INSERT INTO `pay_info` VALUES (35, 162, 'South Sudan', 43, '1919-06-20', 'Libero Nec Ligula Company', 'Mogliano');
INSERT INTO `pay_info` VALUES (36, 791, 'Venezuela', 43, '1919-01-10', 'Sodales Corp.', 'Bromyard');
INSERT INTO `pay_info` VALUES (37, 863, 'Libya', 43, '1818-12-12', 'Sed Pede Industries', 'Bath');
INSERT INTO `pay_info` VALUES (38, 93, 'Vanuatu', 43, '1919-01-28', 'Ac Libero Nec Corp.', 'Châtellerault');
INSERT INTO `pay_info` VALUES (40, 335, 'Peru', 43, '1919-06-03', 'Lectus Pede Et Corporation', 'Itzehoe');
INSERT INTO `pay_info` VALUES (41, 600, 'Sierra Leone', 43, '1919-07-13', 'Id Nunc Interdum Associates', 'Langley');
INSERT INTO `pay_info` VALUES (42, 221, 'Finland', 43, '1818-08-02', 'Nec Mollis Consulting', 'Castel Maggiore');
INSERT INTO `pay_info` VALUES (43, 655, 'Samoa', 43, '2020-02-25', 'At Consulting', 'Valkenburg aan de Geul');
INSERT INTO `pay_info` VALUES (44, 95, 'Singapore', 43, '1919-01-30', 'Eu Accumsan Sed Consulting', 'Pointe-Claire');
INSERT INTO `pay_info` VALUES (45, 124, 'Guernsey', 43, '2020-01-01', 'Magnis PC', 'BiercŽe');
INSERT INTO `pay_info` VALUES (46, 190, 'Grenada', 43, '1919-09-28', 'Dolor Tempus LLP', 'Marchihue');
INSERT INTO `pay_info` VALUES (47, 586, 'Brazil', 43, '1919-09-02', 'Eu Dolor Company', 'Terneuzen');
INSERT INTO `pay_info` VALUES (48, 311, 'Seychelles', 43, '1818-11-26', 'Ac Sem Ut Corp.', 'Mustafakemalpaşa');
INSERT INTO `pay_info` VALUES (49, 919, 'Peru', 43, '1818-08-29', 'Est Mauris Rhoncus Associates', 'Rostock');
INSERT INTO `pay_info` VALUES (51, 731, 'Tajikistan', 43, '2020-03-08', 'Volutpat Nulla Incorporated', 'Épinal');
INSERT INTO `pay_info` VALUES (52, 605, 'Gibraltar', 43, '1919-04-23', 'Duis Gravida Praesent Company', 'Flint');
INSERT INTO `pay_info` VALUES (53, 75, 'Korea, South', 43, '1919-08-29', 'Eros Non Enim Inc.', 'Drumheller');
INSERT INTO `pay_info` VALUES (54, 374, 'Finland', 43, '2020-01-09', 'Facilisis Lorem Inc.', 'Gattatico');
INSERT INTO `pay_info` VALUES (56, 715, 'Greenland', 43, '1919-03-01', 'At Velit Incorporated', 'Rochester');
INSERT INTO `pay_info` VALUES (57, 259, 'Belize', 43, '1818-08-27', 'Nunc Id Enim Inc.', 'Merdorp');
INSERT INTO `pay_info` VALUES (58, 325, 'Ukraine', 43, '2020-05-28', 'Feugiat PC', 'Orp-le-Grand');
INSERT INTO `pay_info` VALUES (59, 691, 'Western Sahara', 43, '1919-05-03', 'Aliquet Diam Corp.', 'Blue Mountains');
INSERT INTO `pay_info` VALUES (60, 530, 'Suriname', 43, '1919-11-06', 'Elit Institute', 'Casacalenda');
INSERT INTO `pay_info` VALUES (61, 792, 'Sudan', 43, '1919-05-30', 'Et Nunc Quisque Associates', 'Telfs');
INSERT INTO `pay_info` VALUES (62, 970, 'Kyrgyzstan', 43, '1919-12-09', 'Urna Ut Incorporated', 'Worksop');
INSERT INTO `pay_info` VALUES (63, 858, 'Iran', 43, '1919-04-03', 'A Magna Lorem Inc.', 'San Gregorio');
INSERT INTO `pay_info` VALUES (64, 119, 'China', 43, '1919-01-31', 'Augue Scelerisque Institute', 'Phoenix');
INSERT INTO `pay_info` VALUES (65, 927, 'Malta', 43, '2020-05-11', 'Quisque Fringilla Euismod Foundation', 'Spaniard\'s Bay');
INSERT INTO `pay_info` VALUES (66, 797, 'Honduras', 43, '1919-10-06', 'Tempor Diam Dictum PC', 'Ragnies');
INSERT INTO `pay_info` VALUES (67, 305, 'Isle of Man', 43, '2020-01-07', 'A LLP', 'Ercolano');
INSERT INTO `pay_info` VALUES (68, 20, 'Nicaragua', 43, '1818-08-27', 'Massa Integer Incorporated', 'Davenport');
INSERT INTO `pay_info` VALUES (69, 701, 'Slovenia', 43, '2020-07-05', 'Ac Corporation', 'Gary');
INSERT INTO `pay_info` VALUES (70, 475, 'Japan', 43, '2020-03-26', 'Mollis Inc.', 'Rockville');
INSERT INTO `pay_info` VALUES (71, 53, 'Andorra', 43, '1919-07-17', 'Tristique Senectus Et Company', 'Duluth');
INSERT INTO `pay_info` VALUES (72, 626, 'Western Sahara', 43, '2020-02-27', 'Aliquet Limited', 'Gary');
INSERT INTO `pay_info` VALUES (73, 119, 'Iceland', 43, '2020-06-01', 'Risus Quis Associates', 'Sossano');
INSERT INTO `pay_info` VALUES (74, 623, 'Kenya', 43, '1919-08-15', 'Aliquam Enim Nec Corporation', 'Lowell');
INSERT INTO `pay_info` VALUES (75, 900, 'Portugal', 43, '1818-09-23', 'Augue Eu Incorporated', 'Roccasicura');
INSERT INTO `pay_info` VALUES (76, 285, 'Seychelles', 43, '2020-04-28', 'Eleifend Institute', 'College');
INSERT INTO `pay_info` VALUES (77, 8, 'Russian Federation', 43, '1818-09-21', 'Et Magnis Inc.', 'Orroli');
INSERT INTO `pay_info` VALUES (78, 267, 'Liechtenstein', 43, '2020-04-10', 'Suscipit Nonummy Limited', 'Caldarola');
INSERT INTO `pay_info` VALUES (79, 727, 'Estonia', 43, '2020-04-14', 'Tortor Dictum PC', 'Essen');
INSERT INTO `pay_info` VALUES (80, 949, 'Luxembourg', 43, '1919-04-09', 'Sit PC', 'Champigny-sur-Marne');
INSERT INTO `pay_info` VALUES (81, 980, 'Madagascar', 43, '1919-02-13', 'Amet Nulla Donec Industries', 'La Cruz');
INSERT INTO `pay_info` VALUES (82, 927, 'Iceland', 43, '1818-11-09', 'Vehicula Et Rutrum Limited', 'Ancarano');
INSERT INTO `pay_info` VALUES (83, 295, 'American Samoa', 43, '2020-04-01', 'Nulla Company', 'Badajoz');
INSERT INTO `pay_info` VALUES (84, 396, 'nunc.In@montes.edu', 43, '1919-01-22', 'Ornare Consulting', 'San Miguel');
INSERT INTO `pay_info` VALUES (85, 469, 'Tokelau', 43, '1919-03-07', 'Consectetuer LLC', 'Saint-Servais');
INSERT INTO `pay_info` VALUES (86, 586, 'Mauritania', 43, '1818-07-24', 'Eros Foundation', 'Springfield');
INSERT INTO `pay_info` VALUES (87, 195, 'India', 43, '1919-09-27', 'Mi Lacinia Incorporated', 'Cellara');
INSERT INTO `pay_info` VALUES (88, 621, 'Marshall Islands', 43, '1919-08-03', 'Nulla In Inc.', 'Tampa');
INSERT INTO `pay_info` VALUES (89, 227, 'Mayotte', 43, '2020-03-25', 'Blandit Limited', 'Märsta');
INSERT INTO `pay_info` VALUES (90, 954, 'Saint Lucia', 43, '2020-05-03', 'Consequat Lectus Sit Ltd', 'Loverval');
INSERT INTO `pay_info` VALUES (91, 954, 'Suriname', 43, '1919-12-18', 'Enim Etiam Consulting', 'Malvern');
INSERT INTO `pay_info` VALUES (92, 245, 'Russian Federation', 43, '1919-09-13', 'Orci Tincidunt Industries', 'Armadale');
INSERT INTO `pay_info` VALUES (93, 240, 'Ukraine', 43, '2020-01-16', 'Cum Sociis Associates', 'Colonnella');
INSERT INTO `pay_info` VALUES (94, 427, 'Algeria', 43, '2020-04-10', 'Sociosqu Ad Consulting', 'Mount Pearl');
INSERT INTO `pay_info` VALUES (95, 547, 'Singapore', 43, '1919-06-25', 'Nam Inc.', 'Lagos');
INSERT INTO `pay_info` VALUES (97, 95, 'Lesotho', 43, '2020-07-08', 'Aenean Sed Pede Industries', 'Kettering');
INSERT INTO `pay_info` VALUES (98, 544, 'Cook Islands', 43, '2020-06-29', 'Donec Feugiat Metus Consulting', 'Balclutha');
INSERT INTO `pay_info` VALUES (100, 693, 'Bahrain', 43, '1818-10-26', 'Augue Ut Lacus PC', 'Viernheim');
INSERT INTO `pay_info` VALUES (101, 301, 'Cook Islands', 43, '1919-09-16', 'Eget Dictum Placerat Institute', 'Oud-Turnhout');
INSERT INTO `pay_info` VALUES (102, 439, 'Trinidad and Tobago', 43, '1919-04-21', 'Gravida Praesent Corporation', 'Durham');
INSERT INTO `pay_info` VALUES (103, 284, 'Zambia', 4, '1919-06-25', 'Velit LLC', 'Bischofshofen');
INSERT INTO `pay_info` VALUES (104, 723, 'Åland Islands', 4, '1919-08-02', 'Non Enim Inc.', 'Padre las Casas');
INSERT INTO `pay_info` VALUES (105, 107, 'Martinique', 4, '2020-02-10', 'Interdum Ligula Foundation', 'Norman Wells');
INSERT INTO `pay_info` VALUES (106, 602, 'Reunion', 4, '2020-01-10', 'Aenean Euismod Mauris Corp.', 'Fayetteville');
INSERT INTO `pay_info` VALUES (107, 124, 'Afghanistan', 4, '1919-12-20', 'Eleifend Vitae PC', 'Laramie');
INSERT INTO `pay_info` VALUES (110, 523, '现金', 4, '2019-07-08', 'uy', 'VUE数据');
INSERT INTO `pay_info` VALUES (111, 656, '现金', 4, '2019-07-02', 'dtyd', '支付宝');

-- ----------------------------
-- Table structure for power
-- ----------------------------
DROP TABLE IF EXISTS `power`;
CREATE TABLE `power`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` int(5) NOT NULL DEFAULT 1 COMMENT 'url状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of power
-- ----------------------------
INSERT INTO `power` VALUES (1, '/login', 1);
INSERT INTO `power` VALUES (2, '/register', 1);
INSERT INTO `power` VALUES (4, '/selectUserByid', 1);
INSERT INTO `power` VALUES (5, '/selectFamiliesById', 1);
INSERT INTO `power` VALUES (6, '/test', 1);
INSERT INTO `power` VALUES (7, '/updateUser', 1);
INSERT INTO `power` VALUES (8, '/updatePassword', 1);
INSERT INTO `power` VALUES (9, '/deleteUserById', 1);
INSERT INTO `power` VALUES (10, '/deleteUsersByIdList', 1);
INSERT INTO `power` VALUES (11, '/addUser', 1);
INSERT INTO `power` VALUES (12, '/ffs/income-info/byid', 1);
INSERT INTO `power` VALUES (14, '/databast/pullAllDataByFile', 1);
INSERT INTO `power` VALUES (15, '/database/format', 1);
INSERT INTO `power` VALUES (16, '/ffs/pay-info/byid', 1);
INSERT INTO `power` VALUES (17, '/ffs/income-info/idLimit', 1);
INSERT INTO `power` VALUES (18, '/ffs/income-info/s/idPage', 1);
INSERT INTO `power` VALUES (19, '/ffs/income-info/update', 1);
INSERT INTO `power` VALUES (20, '/ffs/income-info/del', 1);
INSERT INTO `power` VALUES (21, '/ffs/income-info/add', 1);
INSERT INTO `power` VALUES (23, '/ffs/pay-info/idLimit', 1);
INSERT INTO `power` VALUES (24, '/ffs/pay-info/s/idPage', 1);
INSERT INTO `power` VALUES (25, '/ffs/stock/byID', 1);
INSERT INTO `power` VALUES (26, '/ffs/stock/hs', 1);
INSERT INTO `power` VALUES (27, '/ffs/pay-info/del', 1);
INSERT INTO `power` VALUES (28, '/ffs/pay-info/add', 1);
INSERT INTO `power` VALUES (29, '/ffs/pay-info/update', 1);
INSERT INTO `power` VALUES (30, '/ffs/stock/add', 1);
INSERT INTO `power` VALUES (31, '/ffs/stock/del', 1);
INSERT INTO `power` VALUES (32, '/ffs/stock/gold', 1);
INSERT INTO `power` VALUES (33, '/ffs/stock/exchange', 1);

-- ----------------------------
-- Table structure for role_info
-- ----------------------------
DROP TABLE IF EXISTS `role_info`;
CREATE TABLE `role_info`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `roleName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_info
-- ----------------------------
INSERT INTO `role_info` VALUES (1, '个人用户');
INSERT INTO `role_info` VALUES (2, '管理员');

-- ----------------------------
-- Table structure for role_mapping_power
-- ----------------------------
DROP TABLE IF EXISTS `role_mapping_power`;
CREATE TABLE `role_mapping_power`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `roleId` bigint(20) NOT NULL COMMENT '角色id',
  `powerId` bigint(20) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_mapping_power
-- ----------------------------
INSERT INTO `role_mapping_power` VALUES (5, 2, 1);
INSERT INTO `role_mapping_power` VALUES (6, 2, 2);
INSERT INTO `role_mapping_power` VALUES (7, 2, 4);
INSERT INTO `role_mapping_power` VALUES (8, 2, 5);
INSERT INTO `role_mapping_power` VALUES (9, 2, 6);
INSERT INTO `role_mapping_power` VALUES (10, 2, 7);
INSERT INTO `role_mapping_power` VALUES (11, 2, 8);
INSERT INTO `role_mapping_power` VALUES (12, 2, 9);
INSERT INTO `role_mapping_power` VALUES (13, 2, 10);
INSERT INTO `role_mapping_power` VALUES (14, 2, 11);
INSERT INTO `role_mapping_power` VALUES (15, 1, 1);
INSERT INTO `role_mapping_power` VALUES (16, 1, 4);
INSERT INTO `role_mapping_power` VALUES (18, 1, 7);
INSERT INTO `role_mapping_power` VALUES (19, 1, 8);
INSERT INTO `role_mapping_power` VALUES (20, 1, 9);
INSERT INTO `role_mapping_power` VALUES (21, 1, 6);
INSERT INTO `role_mapping_power` VALUES (22, 1, 12);
INSERT INTO `role_mapping_power` VALUES (23, 2, 14);
INSERT INTO `role_mapping_power` VALUES (24, 2, 15);
INSERT INTO `role_mapping_power` VALUES (25, 2, 16);
INSERT INTO `role_mapping_power` VALUES (26, 1, 16);
INSERT INTO `role_mapping_power` VALUES (27, 2, 17);
INSERT INTO `role_mapping_power` VALUES (28, 2, 18);
INSERT INTO `role_mapping_power` VALUES (29, 2, 19);
INSERT INTO `role_mapping_power` VALUES (30, 2, 20);
INSERT INTO `role_mapping_power` VALUES (31, 2, 21);
INSERT INTO `role_mapping_power` VALUES (32, 2, 23);
INSERT INTO `role_mapping_power` VALUES (33, 2, 25);
INSERT INTO `role_mapping_power` VALUES (34, 1, 25);
INSERT INTO `role_mapping_power` VALUES (35, 2, 26);
INSERT INTO `role_mapping_power` VALUES (36, 1, 26);
INSERT INTO `role_mapping_power` VALUES (37, 2, 24);
INSERT INTO `role_mapping_power` VALUES (38, 2, 27);
INSERT INTO `role_mapping_power` VALUES (39, 2, 28);
INSERT INTO `role_mapping_power` VALUES (40, 2, 29);
INSERT INTO `role_mapping_power` VALUES (41, 1, 28);
INSERT INTO `role_mapping_power` VALUES (42, 2, 30);
INSERT INTO `role_mapping_power` VALUES (43, 1, 30);
INSERT INTO `role_mapping_power` VALUES (44, 2, 31);
INSERT INTO `role_mapping_power` VALUES (45, 1, 31);
INSERT INTO `role_mapping_power` VALUES (46, 1, 32);
INSERT INTO `role_mapping_power` VALUES (47, 2, 32);
INSERT INTO `role_mapping_power` VALUES (48, 1, 33);
INSERT INTO `role_mapping_power` VALUES (49, 2, 33);

-- ----------------------------
-- Table structure for stock
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stockCode` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '股票代码',
  `stockName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '股票名称',
  `userId` bigint(20) NOT NULL COMMENT '用户账号id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stock
-- ----------------------------
INSERT INTO `stock` VALUES (7, 'sh601009', '南京银行', 4);
INSERT INTO `stock` VALUES (8, 'sh000001', '上证指数', 4);
INSERT INTO `stock` VALUES (10, 'sh600031', '三一重工', 4);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `familyCall` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '家庭称呼',
  `wages` decimal(10, 0) NULL DEFAULT 0 COMMENT '工资',
  `roleId` bigint(20) NOT NULL COMMENT '用户角色id（个人用户，管理员）',
  `familyId` bigint(20) NOT NULL DEFAULT 0 COMMENT '家庭id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (4, '222', 'ce14115f6699d306c9cc04e9c7a0a97b7b43bf87c9a1ef20fff044444cab8160', 'linrunpeng', 12, '弟弟', 2000, 2, 25);
INSERT INTO `user_info` VALUES (13, 'qwe', 'ce14115f6699d306c9cc04e9c7a0a97b7b43bf87c9a1ef20fff044444cab8160', 'qwe', 2, '2', 2, 2, 8);
INSERT INTO `user_info` VALUES (14, 'asd', '10abc899841b7e10d4cb17570215525b5aef991a61ab4afd71b36570f3522eea', 'asd', 22, 'dd', 22, 2, 9);
INSERT INTO `user_info` VALUES (18, 'qasd', '7d19f6df9d99210f93dfe91385865962de42d368d3f42f63a8ab4dae83808eb8', 'asd', 11111, '11111', 11111, 2, 11);
INSERT INTO `user_info` VALUES (32, 'lll', '10abc899841b7e10d4cb17570215525b5aef991a61ab4afd71b36570f3522eea', 'qweqwe', 22, 'qwe', 2000, 1, 25);
INSERT INTO `user_info` VALUES (40, 'lll', '10abc899841b7e10d4cb17570215525b5aef991a61ab4afd71b36570f3522eea', 'qweqwe', 22, 'qwe', 2000, 1, 25);
INSERT INTO `user_info` VALUES (41, '312', '3b30688d206760095bd898cdf31f553ec501b3f6e36c86c6c0bcdb315f801e2e', 'wqeqweqw', 22, '321123', 321, 1, 25);
INSERT INTO `user_info` VALUES (43, '201601', '76c32dc23cb75c408a07ba10bfd793953658c7b5f114b3c099b643c8fcdaebe6', 'zenyi', 5, '爸爸', 45, 2, 22);
INSERT INTO `user_info` VALUES (44, '201602', '76c32dc23cb75c408a07ba10bfd793953658c7b5f114b3c099b643c8fcdaebe6', 'yi', 95, 'xx', 2500, 2, 23);
INSERT INTO `user_info` VALUES (45, '201603', '76c32dc23cb75c408a07ba10bfd793953658c7b5f114b3c099b643c8fcdaebe6', 'yi2', 59, 'll', 98, 2, 24);

-- ----------------------------
-- View structure for income_data
-- ----------------------------
DROP VIEW IF EXISTS `income_data`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `income_data` AS select `u`.`account` AS `account`,`u`.`password` AS `password`,`u`.`name` AS `name`,`u`.`age` AS `age`,`u`.`familyCall` AS `familyCall`,`u`.`roleId` AS `roleId`,`u`.`familyId` AS `familyId`,`i`.`id` AS `id`,`i`.`quota` AS `quota`,`i`.`type` AS `type`,`i`.`userid` AS `userid`,`i`.`incomeDate` AS `incomeDate`,`i`.`incomeRoot` AS `incomeRoot`,`i`.`remakers` AS `remakers` from (`user_info` `u` left join `income_info` `i` on((`u`.`id` = `i`.`userid`))) order by `u`.`id`;

-- ----------------------------
-- View structure for pay_data
-- ----------------------------
DROP VIEW IF EXISTS `pay_data`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `pay_data` AS select `u`.`account` AS `account`,`u`.`password` AS `password`,`u`.`name` AS `name`,`u`.`age` AS `age`,`u`.`familyCall` AS `familyCall`,`u`.`roleId` AS `roleId`,`u`.`familyId` AS `familyId`,`p`.`id` AS `id`,`p`.`quota` AS `quota`,`p`.`type` AS `type`,`p`.`userid` AS `userid`,`p`.`payDate` AS `payDate`,`p`.`payFor` AS `payFor`,`p`.`remakers` AS `remakers` from (`user_info` `u` left join `pay_info` `p` on((`u`.`id` = `p`.`userid`))) order by `u`.`id`;

SET FOREIGN_KEY_CHECKS = 1;
