CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '账户名',
  `money` double(11,2) NOT NULL COMMENT '金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账号表';

INSERT INTO `mydb`.`account` (`id`, `name`, `money`) VALUES (1, '小明', 0.00);
INSERT INTO `mydb`.`account` (`id`, `name`, `money`) VALUES (2, '小强', 0.00);
INSERT INTO `mydb`.`account` (`id`, `name`, `money`) VALUES (3, '小智', 0.00);
