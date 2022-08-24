# 功能简介
- **用户登录/登出**
- **发表文章/修改文章**
- **评论文章/点赞文章**
- **全文搜索**

# 接口列表
*号代表必填
## 用户登录
#### 请求语法
~~~
POST http://localhost:8081/user/login
~~~
#### 请求参数
|    参数     | 类型 | 释义 |
|:---------:| :------------: | :------------: |
| *username | String | 用户名 |
| *password | String | 密码 |
#### 请求体
无
#### 返回体
~~~
{
    "code": 状态码,
    "message": "状态",
    "data": "返回信息"
}
~~~
### 示例
#### 请求示例
~~~
curl --location --request POST 'http://localhost:8081/user/login' \
--header 'Cookie: JSESSIONID=24DD4739091E640182C960AF769F495A' \
--form 'username="admin"' \
--form 'password="admin"'
~~~
#### 返回示例
~~~
{"code":200,"message":"SUCCESS","data":"登录成功"}
~~~
## 用户登出
#### 请求语法
~~~
POST http://localhost:8081/user/loginout
~~~
#### 请求参数
无
#### 请求体
无
#### 返回体
~~~
{
    "code": 状态码,
    "message": "状态",
    "data": "返回信息"
}
~~~
### 示例
#### 请求示例
~~~
POST /user/loginout HTTP/1.1
Host: localhost:8081
Cookie: JSESSIONID=2BE41F303B64D916D35430ED12DAD150
~~~
#### 返回示例
~~~
{"code":200,"message":"SUCCESS","data":"退出成功"}
~~~
## 发表文章
#### 请求语法
~~~
POST http://localhost:8081/admin/arricle/push
~~~
#### 请求参数
无
#### 请求体
~~~
{
  "title": "文章标题(必填)",
  "content": "文章内容(必填)"
}
~~~
#### 返回体
~~~
{
    "code": 状态码,
    "message": "状态",
    "data": {
        "id": 文章ID,
        "title": "文章标题",
        "content": "文章内容",
        "goods": 点赞数,
        "createTime": "创建时间",
        "updateTime": "更新时间"
    }
}
~~~
### 示例
#### 请求示例
~~~
curl --location --request POST 'http://localhost:8081/admin/arricle/push' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=24DD4739091E640182C960AF769F495A' \
--data-raw '{
  "title": "测试保存文章",
  "content": "测试保存文章"
}'
~~~
#### 返回示例
~~~
{
    "code": 200,
    "message": "SUCCESS",
    "data": {
        "id": 2,
        "title": "测试保存fdsfasdafasfasdfasf",
        "content": "测试保存fasdfasfasdfd",
        "goods": 0,
        "createTime": "2022-08-24T02:19:31.898+00:00",
        "updateTime": "2022-08-24T02:19:31.898+00:00"
    }
}
~~~
## 修改文章
#### 请求语法
~~~
POST http://localhost:8081/admin/arricle/edit
~~~
#### 请求参数
无
#### 请求体
~~~
{
    "id": 文章ID(必填),
    "title": "文章标题",
    "content": "文章内容"
}
~~~
#### 返回体
~~~
{
    "code": 状态码,
    "message": "状态",
    "data": "返回信息"
}
~~~
### 示例
#### 请求示例
~~~
curl --location --request POST 'http://localhost:8081/admin/arricle/edit' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=F6539E57B170DDE0100B3F02F9D053BA' \
--data-raw '{
    "id": 1,
    "title": "哈哈保存",
    "content": "测试保存"
}'
~~~
#### 返回示例
~~~
{
    "code": 200,
    "message": "SUCCESS",
    "data": "修改成功"
}
~~~
## 评论文章
#### 请求语法
~~~
POST http://localhost:8081/arricle/comment
~~~
#### 请求参数
无
#### 请求体
~~~
{
  "articleId": 文章ID(必填),
  "content": "评论内容(必填)"
}
~~~
#### 返回体
~~~
{
    "code": 状态码,
    "message": "状态",
    "data": "返回信息"
}
~~~
### 示例
#### 请求示例
~~~
curl --location --request POST 'http://localhost:8081/arricle/comment' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=24DD4739091E640182C960AF769F495A' \
--data-raw '{
  "articleId": 2,
  "content": "评论功能测试fadsfasdfasdfasd"
}'
~~~
#### 返回示例
~~~
{
    "code": 200,
    "message": "SUCCESS",
    "data": "评论成功"
}
~~~
## 点赞文章
#### 请求语法
~~~
POST http://localhost:8081/arricle/good
~~~
#### 请求参数
|     参数     |   类型   |  释义  |
|:----------:|:------:|:----:|
| *articleId |  long  | 文章ID |
#### 请求体
无
#### 返回体
~~~
{
    "code": 状态码,
    "message": "状态",
    "data": "返回信息"
}
~~~
### 示例
#### 请求示例
~~~
curl --location --request POST 'http://localhost:8081/arricle/good?articleId=2' \
--header 'Cookie: JSESSIONID=24DD4739091E640182C960AF769F495A'
~~~
#### 返回示例
~~~
{
    "code": 200,
    "message": "SUCCESS",
    "data": "评论成功"
}
~~~
## 点赞评论
#### 请求语法
~~~
POST http://localhost:8081/arricle/comment/good
~~~
#### 请求参数
|     参数     |   类型   |  释义  |
|:----------:|:------:|:----:|
| *commentId |  long  | 评论id |
#### 请求体
无
#### 返回体
~~~
{
    "code": 状态码,
    "message": "状态",
    "data": "返回信息"
}
~~~
### 示例
#### 请求示例
~~~
curl --location --request POST 'http://localhost:8081/arricle/comment/good?commentId=2' \
--header 'Cookie: JSESSIONID=24DD4739091E640182C960AF769F495A'
~~~
#### 返回示例
~~~
{
    "code": 200,
    "message": "SUCCESS",
    "data": "点赞成功"
}
~~~
## 获取对应文章评论列表
#### 请求语法
~~~
GET http://localhost:8081/arricle/get/comments
~~~
#### 请求参数
|     参数     |  类型  |  释义 |
|:----------:|:----:|:---:|
| *articleId | long | 文章ID |
| pageNum | int  | 起始页 |
#### 请求体
无
#### 返回体
~~~
{
    "code": 状态码,
    "message": "状态",
    "data": "返回信息"
}
~~~
### 示例
#### 请求示例
~~~
curl --location --request GET 'http://localhost:8081/arricle/get/comments?articleId=2&pageNum=0' \
--header 'Cookie: JSESSIONID=DE91A0B2AD854AEC7DEB9035BB8E508C'
~~~
#### 返回示例
~~~
{
    "code": 200,
    "message": "SUCCESS",
    "data": {
        "total": 1,
        "count": 10,
        "page": 0,
        "totalPage": 1,
        "data": [
            {
                "id": 2,
                "articleId": 2,
                "content": "评论功能测试fadsfasdfasdfasd",
                "goods": 1,
                "createTime": "2022-08-24T02:20:44.000+00:00",
                "updateTime": "2022-08-24T02:22:21.000+00:00"
            }
        ]
    }
}
~~~
## 全文搜索
#### 请求语法
~~~
GET http://localhost:8081/arricle/search
~~~
#### 请求参数
|    参数    |   类型   | 释义  |
|:--------:|:------:|:---:|
| *keyword | String | 关键字 |
| pageNum  |  int   | 1页数 |
#### 请求体
无
#### 返回体
~~~
{
    "code": 状态码,
    "message": "状态",
    "data": {
        "total": 总数量,
        "count": 当前请求数量,
        "page": 当前页码,
        "totalPage": 总页码,
        "data": [
            {
                "id": 文章ID,
                "title": "文章标题",
                "content": "文章内容",
                "goods": 点赞数,
                "createTime": 创建时间,
                "updateTime": "更新时间"
            }
        ]
    }
}
~~~
### 示例
#### 请求示例
~~~
curl --location --request GET 'http://localhost:8081/arricle/search?keyword=测试保存&pageNum=1' \
--header 'Cookie: JSESSIONID=F6539E57B170DDE0100B3F02F9D053BA'
~~~
#### 返回示例
~~~
{
    "code": 200,
    "message": "SUCCESS",
    "data": {
        "total": 1,
        "count": 10,
        "page": 0,
        "totalPage": 1,
        "data": [
            {
                "id": 1,
                "title": "哈哈保存",
                "content": "测试保存",
                "goods": 0,
                "createTime": null,
                "updateTime": "2022-08-24T02:36:35.000+00:00"
            }
        ]
    }
}
~~~