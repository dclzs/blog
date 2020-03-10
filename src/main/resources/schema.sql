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