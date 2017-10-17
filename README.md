# JavaCourse
Java 3级培训课程笔记

第一节/第二节课程过于初级，故作省略。从第三节课程开始记录和巩固知识；

## 配置Eclipse和开发环境
0. JDK下载和环境变量设置（略）；
1. 从Eclipse官网下载最新版本的[编辑器](http://www.eclipse.org/)，裸奔的编辑器已经带Maven，不需要另外安装；
2. 修改编辑器字体Consolas，一定要设置为中文的字体号，否则windows下中文会变得异常；
3. 设置Maven仓库，用阿里云的仓库，可以大大加快依赖包的下载速度；
4. 快捷键备忘：
```
格式化代码： Command(CTRL) + Shift + F
自动补全：Alt + /
生成代码： Command(CTRL) + Alt + S
```

## 项目介绍
1. ConquerJava.Learn 包开头，每课程一个子包；
2. 每一个Test结尾的类，都带有静态main函数，可以直接运行；
3. 默认需要以来 Log4j 包，替代System.out，还可以带文件日志，日志大小为2M循环记录；方便观察实验输出结果
4. 接受同学们提交的代码