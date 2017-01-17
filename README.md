# cms系统原型
由于经常需要搭建一些类似cms这样的系统，如果每次都是手动创建项目，创建模块，拷贝改，无疑是蛋疼的。  
所以我需要做一个项目原型，每次需要搭建类似cms系统的时候，只需要一键即可生成！

## 一、系统简介
系统结构如下:

### 1. 各个模块之间的依赖关系
- `web层` 依赖 `biz层`
- `biz层` 依赖 `service层`和`dao层`
- `service层` 依赖 `model层`
- `dao层` 依赖 `model层`

> 由于maven模块的依赖具有传递性（但也要看scope），所以web层间接依赖了model层。

## 一、实现的基本功能
由于这只是一个项目原型，以后可能会用于各大场景，所以下面的功能只是一些最基础的。

#### 网站
1. 登录
2. 注册
3. 找回密码

#### 工作台
1. 系统
    - 用户管理
    - 角色管理
    - 权限管理
2. 内容
    - 数据字典
    - 缓存管理
    - 页面管理
3. 我的
    - 个人资料
    - 消息通知

## 二、技术或框架列表
- Spring
- SpringMVC
- Mybatis
- autoconfig
- mbg
- shiro
- redis
- dubbo
- mysql
- freemarker
- ftp
- log4j2
- fastjson
- lombok

## 三、原型截图
xxx

## 四、使用方法
1. 拉取项目到本地 `git clone https://gthub.com/kangyonggan/cms-archetype.git`
2. 编译并安装 `mvn clean install`, 此过程会下载缺失的jar包。
3. 一键生成项目 `xxx`
4. 也可以使用idea生成向导，如下图：

