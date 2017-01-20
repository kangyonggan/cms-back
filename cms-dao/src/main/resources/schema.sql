DROP DATABASE IF EXISTS cms;

CREATE DATABASE cms
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_general_ci;

USE cms;

-- ----------------------------
--  Table structure for user
-- ----------------------------
DROP TABLE
IF EXISTS user;

CREATE TABLE user
(
  id           BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  username     VARCHAR(20)                           NOT NULL
  COMMENT '用户名',
  email        VARCHAR(64)                           NOT NULL                    DEFAULT ''
  COMMENT '邮箱',
  mobile       VARCHAR(20)                           NOT NULL                    DEFAULT ''
  COMMENT '手机号',
  password     VARCHAR(64)                           NOT NULL
  COMMENT '密码',
  salt         VARCHAR(64)                           NOT NULL
  COMMENT '密码盐',
  fullname     VARCHAR(32)                           NOT NULL
  COMMENT '姓名',
  small_avatar VARCHAR(255)                          NOT NULL                    DEFAULT ''
  COMMENT '小头像',
  is_deleted   TINYINT                               NOT NULL                    DEFAULT 0
  COMMENT '逻辑删除:{0:未删除, 1:已删除}',
  created_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '用户表';
CREATE UNIQUE INDEX id_UNIQUE
  ON user (id);
CREATE INDEX create_ix
  ON user (created_time);
CREATE UNIQUE INDEX username_UNIQUE
  ON user (username);

-- ----------------------------
--  Table structure for user_profile
-- ----------------------------
DROP TABLE
IF EXISTS user_profile;

CREATE TABLE user_profile
(
  id            BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  username      VARCHAR(20)                           NOT NULL
  COMMENT '用户名',
  medium_avatar VARCHAR(255)                          NOT NULL                    DEFAULT ''
  COMMENT '中头像',
  large_avatar  VARCHAR(255)                          NOT NULL                    DEFAULT ''
  COMMENT '大头像',
  sex           TINYINT                               NOT NULL                    DEFAULT 0
  COMMENT '性别:{0:男, 1:女}',
  phone         VARCHAR(32)                           NOT NULL                    DEFAULT ''
  COMMENT '座机号',
  qq            VARCHAR(16)                           NOT NULL                    DEFAULT ''
  COMMENT 'QQ号',
  weixin        VARCHAR(64)                           NOT NULL                    DEFAULT ''
  COMMENT '微信号',
  id_card       VARCHAR(32)                           NOT NULL                    DEFAULT ''
  COMMENT '身份证',
  web_site      VARCHAR(64)                           NOT NULL                    DEFAULT ''
  COMMENT '个人网站',
  country_code  VARCHAR(16)                           NOT NULL                    DEFAULT '000'
  COMMENT '国家代码',
  country_name  VARCHAR(32)                           NOT NULL                    DEFAULT '中国'
  COMMENT '国家名称',
  city_code     VARCHAR(16)                           NOT NULL                    DEFAULT ''
  COMMENT '城市代码',
  city_name     VARCHAR(32)                           NOT NULL                    DEFAULT ''
  COMMENT '城市名称',
  district_code VARCHAR(16)                           NOT NULL                    DEFAULT ''
  COMMENT '地区代码',
  district_name VARCHAR(32)                           NOT NULL                    DEFAULT ''
  COMMENT '地区名称',
  address       VARCHAR(128)                          NOT NULL                    DEFAULT ''
  COMMENT '详细地址',
  remarks       VARCHAR(512)                          NOT NULL                    DEFAULT ''
  COMMENT '备注',
  is_deleted    TINYINT                               NOT NULL                    DEFAULT 0
  COMMENT '逻辑删除:{0:未删除, 1:已删除}',
  created_time  TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time  TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '用户信息表';
CREATE UNIQUE INDEX id_UNIQUE
  ON user_profile (id);
CREATE UNIQUE INDEX username_UNIQUE
  ON user_profile (username);

-- ----------------------------
--  Table structure for role
-- ----------------------------
DROP TABLE
IF EXISTS role;

CREATE TABLE role
(
  id           BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  code         VARCHAR(32)                           NOT NULL
  COMMENT '角色代码',
  name         VARCHAR(32)                           NOT NULL
  COMMENT '角色名称',
  is_deleted   TINYINT                               NOT NULL                DEFAULT 0
  COMMENT '逻辑删除:{0:未删除, 1:已删除}',
  created_time TIMESTAMP                             NOT NULL                DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time TIMESTAMP                             NOT NULL                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '角色表';
CREATE UNIQUE INDEX id_UNIQUE
  ON role (id);
CREATE INDEX create_ix
  ON role (created_time);
CREATE UNIQUE INDEX code_UNIQUE
  ON role (code);

-- ----------------------------
--  Table structure for menu
-- ----------------------------
DROP TABLE
IF EXISTS menu;

CREATE TABLE menu
(
  id           BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  code         VARCHAR(32)                           NOT NULL
  COMMENT '菜单代码',
  name         VARCHAR(32)                           NOT NULL
  COMMENT '菜单名称',
  pcode        VARCHAR(32)                           NOT NULL                DEFAULT ''
  COMMENT '父菜单代码',
  url          VARCHAR(128)                          NOT NULL                DEFAULT ''
  COMMENT '菜单地址',
  sort         INT(11)                               NOT NULL                DEFAULT 0
  COMMENT '菜单排序(从0开始)',
  icon         VARCHAR(128)                          NOT NULL                DEFAULT ''
  COMMENT '菜单图标的样式',
  is_deleted   TINYINT                               NOT NULL                DEFAULT 0
  COMMENT '逻辑删除:{0:未删除, 1:已删除}',
  created_time TIMESTAMP                             NOT NULL                DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time TIMESTAMP                             NOT NULL                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '菜单表';
CREATE UNIQUE INDEX id_UNIQUE
  ON menu (id);
CREATE INDEX create_ix
  ON menu (created_time);
CREATE INDEX sort_ix
  ON menu (sort);
CREATE UNIQUE INDEX code_UNIQUE
  ON menu (code);

-- ----------------------------
--  Table structure for user_role
-- ----------------------------
DROP TABLE
IF EXISTS user_role;

CREATE TABLE user_role
(
  username  VARCHAR(20) NOT NULL
  COMMENT '用户名',
  role_code VARCHAR(32) NOT NULL
  COMMENT '角色代码',
  PRIMARY KEY (username, role_code)
)
  COMMENT '用户角色表';

-- ----------------------------
--  Table structure for role_menu
-- ----------------------------
DROP TABLE
IF EXISTS role_menu;

CREATE TABLE role_menu
(
  role_code VARCHAR(32) NOT NULL
  COMMENT '角色代码',
  menu_code VARCHAR(32) NOT NULL
  COMMENT '菜单代码',
  PRIMARY KEY (role_code, menu_code)
)
  COMMENT '角色菜单表';

-- ----------------------------
--  Table structure for dictionary
-- ----------------------------
DROP TABLE
IF EXISTS dictionary;

CREATE TABLE dictionary
(
  id           BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  code         VARCHAR(32)                           NOT NULL
  COMMENT '代码',
  value        VARCHAR(128)                          NOT NULL
  COMMENT '值',
  type         VARCHAR(16)                           NOT NULL
  COMMENT '类型',
  sort         INT(11)                               NOT NULL                DEFAULT 0
  COMMENT '排序(从0开始)',
  is_deleted   TINYINT                               NOT NULL                DEFAULT 0
  COMMENT '逻辑删除:{0:未删除, 1:已删除}',
  created_time TIMESTAMP                             NOT NULL                DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time TIMESTAMP                             NOT NULL                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '字典表';
CREATE UNIQUE INDEX id_UNIQUE
  ON dictionary (id);
CREATE UNIQUE INDEX code_UNIQUE
  ON dictionary (code);
CREATE INDEX create_ix
  ON dictionary (created_time);
CREATE INDEX type_ix
  ON dictionary (type);
CREATE INDEX sort_ix
  ON dictionary (sort);

CREATE TABLE token
(
  id           BIGINT(20) PRIMARY KEY  AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  code         VARCHAR(128)                           NOT NULL
  COMMENT '记号代码',
  type         VARCHAR(500)                           NOT NULL                DEFAULT ''
  COMMENT '记号类型',
  user_id      BIGINT(20)                             NOT NULL                DEFAULT 0
  COMMENT '用户ID',
  expire_time  DATETIME                               NOT NULL
  COMMENT '失效时间',
  is_deleted   TINYINT                                NOT NULL                DEFAULT 0
  COMMENT '逻辑删除:{0:未删除, 1:已删除}',
  created_time TIMESTAMP                              NOT NULL                DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time TIMESTAMP                              NOT NULL                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '记号表';
CREATE UNIQUE INDEX id_UNIQUE
  ON token (id);
CREATE UNIQUE INDEX code_UNIQUE
  ON token (code);

#====================初始数据====================#

-- ----------------------------
--  data for user
-- ----------------------------
INSERT INTO user
(username, email, mobile, password, salt, fullname)
VALUES
  ('admin', 'kangyonggan@gmail.com', '15121149571', '9606b0029ba4a8c9369f288cced0dc465eb5eabd', '3685072edcf8aad8',
   '管理员');

-- ----------------------------
--  data for user_profile
-- ----------------------------
INSERT INTO user_profile
(username, phone, id_card, web_site)
VALUES
  ('admin', '021-63898580', '340321199112273095', 'http://kangyonggan.com');

-- ----------------------------
--  data for role
-- ----------------------------
INSERT INTO role
(code, name)
VALUES
  ('ROLE_ADMIN', '管理员'),
  ('ROLE_USER', '普通用户');

-- ----------------------------
--  data for menu
-- ----------------------------
INSERT INTO menu
(code, name, pcode, url, sort, icon)
VALUES
  ('DASHBOARD', '工作台', '', 'index', 0, 'menu-icon fa fa-dashboard'),

  ('SYSTEM', '系统', 'DASHBOARD', 'system', 1, 'menu-icon fa fa-cogs'),
  ('SYSTEM_USER', '用户管理', 'SYSTEM', 'system/user', 0, ''),
  ('SYSTEM_ROLE', '角色管理', 'SYSTEM', 'system/role', 1, ''),
  ('SYSTEM_MENU', '菜单管理', 'SYSTEM', 'system/menu', 2, ''),

  ('CONTENT', '内容', 'DASHBOARD', 'content', 2, 'menu-icon fa fa-gavel'),
  ('CONTENT_CACHE', '缓存管理', 'CONTENT', 'content/cache', 0, ''),
  ('CONTENT_DICTIONARY', '数据字典', 'CONTENT', 'content/dictionary', 1, ''),
  ('CONTENT_PAGE', '页面管理', 'CONTENT', 'content/page', 2, ''),

  ('USER', '我的', 'DASHBOARD', 'user', 3, 'menu-icon fa fa-user'),
  ('USER_PROFILE', '个人资料', 'USER', 'user/profile', 0, '');

-- ----------------------------
--  data for user_role
-- ----------------------------
INSERT INTO user_role
VALUES
  ('admin', 'ROLE_ADMIN');

-- ----------------------------
--  data for role_menu
-- ----------------------------
INSERT INTO role_menu SELECT
                        'ROLE_ADMIN',
                        code
                      FROM menu;

INSERT INTO role_menu SELECT
                        'ROLE_USER',
                        code
                      FROM menu
                      WHERE code LIKE 'USER%' OR code = 'DASHBOARD';