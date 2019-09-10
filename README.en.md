# hzwy_android

#### 介绍
会展无忧-Android版

#### 项目结构
hzwy_android

&ensp;├-- app   &nbsp;&nbsp;主应用壳工程

&ensp;├-- buildSrc   &nbsp;&nbsp;build脚本

&ensp;├-- business  &nbsp;&nbsp;业务逻辑

&ensp;│ &ensp;    ├-- login &nbsp;&nbsp;登陆业务

&ensp;│  &ensp;   └-- main_module &nbsp;&nbsp;首页业务

&ensp;│  &ensp;          ├-- main &nbsp;&nbsp;首页框架

&ensp;├-- dependence &nbsp;&nbsp;依赖库

&ensp;├-- scripts &nbsp;&nbsp;脚本文件夹

&ensp;├-- .gitignore &nbsp;&nbsp;Git忽略

&ensp;├-- build.gradle &nbsp;&nbsp;工程gradle

&ensp;├-- gradle.properties &nbsp;&nbsp;gradle配置文件

&ensp;└-- setting.gradle &nbsp;&nbsp;工程gradle

#### 新建module

- newBusinessModule       新建一个业务模块
- newDependenceModule     新建一个基础库模块


- option:
> 1.--name 项目名称
> 2.--package 包名
> 3.--path 项目路径（可以没有，可以接多个，路径依次排序）


1. 在工程右侧点击gradle icon,在Run Gradle Task中Gradle Project选择hzwy_android
2. Command Line中输入 newBusinessModule --name test --package com.hzwy.test --path hello
   &nbsp;&nbsp;会在business目录下生成hello/test业务模块并在setting.gradle文件中导入当前模块
3. newDependenceModule --name test --package com.hzwy.test --path hello
   &nbsp;&nbsp;会在dependence下生成hello/test基础库模块

#### 参与贡献

1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request


#### 码云特技

1. 使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2. 码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3. 你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4. [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5. 码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6. 码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)