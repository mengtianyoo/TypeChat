# Type Chat- **This version has been deprecated for various reasons.**
Creating "Type Chat" - a streamlined messaging app with core features: private chat, group chat, and AI chat. Prioritizing simplicity, it ensures secure, convenient communication, aligning with AI advancements for an efficient and user-friendly experience.

## 项目开发进度记录

| 日期           | 记录内容                                                     |
| :------------- | :----------------------------------------------------------- |
| 三月五号       | 第一次创建仓库，确定项目主题完善[开题报告](https://pv7r614tfw8.feishu.cn/docx/JQSsdprmloQR0ixzqACcYLkPnff) . |
| 三月七号       | 实现AI聊天功能、以及作为备用[Fire Base ](https://console.firebase.google.com/project/typechat-1c3d4/database/typechat-1c3d4-default-rtdb/data?hl=zh-cn) 的后端数据库的实现 |
| 三月十五号     | 实现后端打jar包，部署到服务器上，可进行远程访问              |
| 三月十六号     | 首次实现前后端数据交互，但仅有登录注册功能                   |
| 三月二十一号   | 添加个人信息请求功能、从服务器获取数据，并编写图片上传类文件 |
| 三月三十号     | 测试部分后端的代码完整性，完善后端代码，迁移到服务器上       |
| 四月十号       | 团队成员退出，放弃该版本的开发工作，决定丢弃后端架构，全面使用Firebase |
| ……             | ……                                                           |
| 四月十号（晚） | 第二版实现群聊功能                                           |
| 四月十九号     | 迁移AI聊天的部分代码，基本底层逻辑转换为依靠Firebase实现，实现邮箱验证 |
| 五月十号       | 升级Firebase的方案，使用Gemini作为AI聊天的框架               |
| 五月十五号     | 实现消息的通知功能                                           |
| 五月二十四号   | 继续第二版的开发工作核心功能基本完成，还差部分界面的改进     |
| 五月二十五号   | 开始攥写项目的总结文档和PPT部分                              |
|                |                                                              |
|                |                                                              |

## 项目的技术掌握程度

``gloomy``：

1. 掌握RESTFUL API的编写和常用的注解
2. Android端掌握activity间的相互通信
3. fragment与activity的切换、fragment之间的切换
4. 熟练掌握Android的进程相关的知识
5. 熟练掌握各种XML的布局格式
6. 掌握Apifox的基本操作，熟练掌握接口的测试技能，为开发文档编写提供便利
7. 基本掌握retrofit2的使用，能够实现Json的交互
8. 掌握SharedPreferences的使用，实现基本的本地数据保存和共享
9. 掌握变量声明的设计理念，能够高效编写代码
10. 掌握Adapter的各种变式和关键函数能自定义各种复杂类型的实体
11. 掌握通知的核心功能实现，但是存在一些bug未解决


## 项目开发中的Bug Log

too many bugs to record them all .



## 一些难懂的原理

![upload原理](https://pic.imgdb.cn/item/66513fefd9c307b7e93e7934.jpg)



在Android 10及以上版本中，Google引入了一个新的存储模型，称为分区存储，它增加了对应用访问设备存储的限制。在分区存储模型中，应用只能直接访问它们自己的应用特定目录和媒体文件。对于其他目录，如/storage/emulated/0/Pictures/Twitter/，应用需要使用MediaStore API或者存储访问框架（Storage Access Framework，SAF）。



You cannot start a load on a not yet attached View or a Fragment where getActivity() returns null (which usually occurs when getActivity() is called before the Fragment is attached or after the Fragment is destroyed).
