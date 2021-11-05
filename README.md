# 基于人工智能算法的产业要素供需匹配模型研发及应用示范

系统主要有分析用户输入内容，深度挖掘用户真正的需求，为用户提供与之对应的人才，并且提供给用户不同的维度来选择人才（例如：科创成果，综合能力，技术等）
<br>
**具体功能**
~~~xml
1.实现对人才的注册功能，方便新来人才成为我平台注册人才 
2.实现人员管理操作，对具有高能力人才进行优先服务； 
3.实现人员展示模块，将人才库中的人才进行透明展示，方便用户更加准确的选择适合自己的人才，提供每日热搜为用户做参考； 
4.实现人才搜索功能，对于用户所需要的技术人才，进行精准查找；
~~~
```powershell
文件夹介绍
talentRecommendationSystem-java 后端
talentRecommendationSystem-uniapp 前端
ldapython-project LDA模型
tf-idf-keyword flask数据处理服务
sql MySQL建表

```

## 功能实现

- [x] ~~人才展示~~
- [x] ~~人才注册~~
- [x] ~~关键词检索~~
- [x] ~~热点数据提示~~
- [x] ~~不通科创要素排序~~
- [ ] 模型优化



## 技术选型
![Image text](https://gitee.com/starses/talent-recommendation-system/raw/master/images/007.png)
- [x] SpringBoot2.0
- [x] Uniapp
- [x] Flask
- [x] MySQL8.0
- [x] Redis
- [x] LDA模型-tfidf算法



## 截图展示


<table>
    <tr>
        <td>人才展示<br/>
            <img src="https://gitee.com/starses/talent-recommendation-system/raw/master/images/001.png" alt="人才展示">
        </td>
        <td>人才注册<br/>
            <img src="https://gitee.com/starses/talent-recommendation-system/raw/master/images/002.png" alt="人才注册">
        </td>
    </tr>
    <tr>
        <td>部分前端代码展示<br/>
            <img src="https://gitee.com/starses/talent-recommendation-system/raw/master/images/003.png" alt="部分前端代码展示">
        </td>
        <td>部分后端代码展示<br/>
            <img src="https://gitee.com/starses/talent-recommendation-system/raw/master/images/004.png" alt="部分后端代码展示">
        </td>
    </tr>
    <tr>
        <td>核心算法应用<br/>
            <img src="https://gitee.com/starses/talent-recommendation-system/raw/master/images/005.png" alt="核心算法应用">
        </td>
        <td>效果展示<br/>
            <img src="https://gitee.com/starses/talent-recommendation-system/raw/master/images/006.png" alt="效果展示">
        </td>
    </tr>
    
</table>