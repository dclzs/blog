DROP TABLE IF EXISTS category;
CREATE TABLE category(
     `category_id` BIGINT(20) COMMENT '分类ID',
     `user_id` BIGINT(20) COMMENT '用户ID',
     `category_name` VARCHAR(100) DEFAULT '' COMMENT '分类名称',
     `category_desc` VARCHAR(200) DEFAULT '' COMMENT '分类描述',
     `display_status` TINYINT DEFAULT '1' COMMENT '显示状态',
     `create_time` DATETIME DEFAULT  NOW() COMMENT '创建时间',
     `update_time` DATETIME DEFAULT  NOW() COMMENT '更新时间',
     PRIMARY KEY (`category_id`)
)ENGINE = INNODB DEFAULT CHARSET 'UTF8' COMMENT '分类表';

DROP TABLE IF EXISTS article;
CREATE TABLE article(
    `article_id` BIGINT(20) COMMENT '文章ID',
    `category_id` BIGINT(20) COMMENT '分类ID',
    `user_id` BIGINT(20) COMMENT '用户ID',
    `tags` VARCHAR(200) DEFAULT ''COMMENT '标签（逗号分隔）',
    `article_title` VARCHAR(100) DEFAULT '' COMMENT '文章标题',
    `article_author` VARCHAR(50) DEFAULT '' COMMENT '原作者',
    `article_desc` VARCHAR(200) DEFAULT '' COMMENT '文章描述',
    `article_content` TEXT COMMENT '文章内容',
    `public_status` TINYINT DEFAULT '1' COMMENT '公开状态',
    `create_time` DATETIME DEFAULT  NOW() COMMENT '创建时间',
    `update_time` DATETIME DEFAULT  NOW() COMMENT '更新时间',
    primary key (`article_id`)
)ENGINE = INNODB DEFAULT CHARSET 'UTF8' COMMENT '文章表';


DROP TABLE IF EXISTS user;
CREATE TABLE user(
    `user_id` BIGINT(20) COMMENT '用户ID',
    `avatar` VARCHAR(100) DEFAULT '' COMMENT '头像',
    `nick_name` VARCHAR(100) DEFAULT '' COMMENT '昵称',
    `email` VARCHAR(50) DEFAULT '' COMMENT '邮箱',
    `phone` VARCHAR(50) DEFAULT '' COMMENT '电话',
    `password` VARCHAR(100) DEFAULT '' COMMENT '密码',
    `user_desc` VARCHAR(200) COMMENT '描述',
    `status` TINYINT DEFAULT '1' COMMENT '状态',
    `create_time` DATETIME DEFAULT  NOW() COMMENT '创建时间',
    `update_time` DATETIME DEFAULT  NOW() COMMENT '更新时间',
    primary key (`user_id`)
)ENGINE = INNODB DEFAULT CHARSET 'UTF8' COMMENT '用户表';


DROP TABLE IF EXISTS bookmark;
CREATE TABLE bookmark(
    `bookmark_id` BIGINT(20) COMMENT '书签ID',
    `article_id` BIGINT(20) COMMENT '文章ID',
    `user_id` BIGINT(20) COMMENT '用户ID',
    `create_time` DATETIME DEFAULT  NOW() COMMENT '创建时间',
    primary key (`bookmark_id`)
)ENGINE = INNODB DEFAULT CHARSET 'UTF8' COMMENT '书签表';