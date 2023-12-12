# javaspring-security扩展包（OAUTH2.1）



## 资料地址



+ 官网



```http request
https://docs.spring.io/spring-authorization-server/docs/current/reference/html/index.html
```



+ 源码以及示例



不会gradle的下载下来可以试一下转成maven

```http request
https://github.com/spring-projects/spring-authorization-server
```

+ oauth2.1起草文案

```http request
https://datatracker.ietf.org/doc/html/draft-ietf-oauth-v2-1-07#section-4.1
```

+ 

```request
https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/form.html
```

### 客户端凭证

## 客户端模式



### 简介





### 测试方式



```http request
POST /oauth2/token?grant_type=client_credentials&scope=openid HTTP/1.1
Host: localhost:9000
Authorization: Basic eGh0OnNlY3JldA==
```



`eGh0OnNlY3JldA==`是`{clientId}:{secret}的base64编码`



```c#
curl --location --request POST "http://localhost:9000/oauth2/token?grant_type=client_credentials&scope=openid" --header "Authorization: Basic eGh0OnNlY3JldA=="
```



### 响应示例



```json
{
    "access_token": "eyJraWQiOiJiMmQ1ZWVkNy1jNWFiLTQ3NGQtYmQxNi1kZWNjMjRmMWYzMWUiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ4aHQiLCJhdWQiOiJ4aHQiLCJuYmYiOjE2ODkzMDQyMjYsInNjb3BlIjpbIm9wZW5pZCJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjkwOTAiLCJleHAiOjE2ODkzMDQ1MjYsImlhdCI6MTY4OTMwNDIyNn0.PiEBnjyYX4qQN9bawERgEz7TZzbNrke2Y81SfAG_7Ao9e7GY6TpDdyGEFfLjmC14bJUH2tmqdvDjO89DZRcmNOYM00tMwmH3ktMGc2LoNwLMqU4_j2shZApv_WjbmmxfntzEwrA6y_pswnICx6orwy3bG_UG6lM3l4yi2xrf_I43Us6pG_avx5h7ytBXJXhCxNkbQ1IG6pTxRLHbvs2OEYXNh9W6BXYvpi06cmNBLmcO3HpiTdkS9kRiulXUqwVCEC2K3mMBKfdIqlQYTKTLL2p0RFf8LS4Fr-XR7zdlceBgIqlhrSTPiE8BAPsC_W8gsf30kiyl0MPFHe99TPg84A",
    "scope": "openid",
    "token_type": "Bearer",
    "expires_in": 300
}
```





## 授权码模式



### 简介





### 测试方式



1. 获取授权码



```javascript
http://localhost:9000/oauth2/authorize?client_id=xht&response_type=code&scope=read&redirect_uri=https://www.baidu.com
```



返回的授权码在跳转地址后面





2. 获取token



````http
POST /oauth2/token HTTP/1.1
Host: localhost:9000
Authorization: Basic eGh0OnNlY3JldA==
Content-Type: application/x-www-form-urlencoded
Content-Length: 204

grant_type=authorization_code&code=获取到的授权码&redirect_uri=https%3A%2F%2Fwww.baidu.com
````



```java
curl --location --request POST "http://localhost:9000/oauth2/token" --header "Authorization: Basic eGh0OnNlY3JldA==" --header "Content-Type: application/x-www-form-urlencoded" --data-urlencode "grant_type=authorization_code" --data-urlencode "code=获取到的授权码" --data-urlencode "redirect_uri=https://www.baidu.com"
```



### 响应示例



```json
{
    "access_token": "eyJraWQiOiJiMmQ1ZWVkNy1jNWFiLTQ3NGQtYmQxNi1kZWNjMjRmMWYzMWUiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ4aHQiLCJhdWQiOiJ4aHQiLCJuYmYiOjE2ODkzMDQyMjYsInNjb3BlIjpbIm9wZW5pZCJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjkwOTAiLCJleHAiOjE2ODkzMDQ1MjYsImlhdCI6MTY4OTMwNDIyNn0.PiEBnjyYX4qQN9bawERgEz7TZzbNrke2Y81SfAG_7Ao9e7GY6TpDdyGEFfLjmC14bJUH2tmqdvDjO89DZRcmNOYM00tMwmH3ktMGc2LoNwLMqU4_j2shZApv_WjbmmxfntzEwrA6y_pswnICx6orwy3bG_UG6lM3l4yi2xrf_I43Us6pG_avx5h7ytBXJXhCxNkbQ1IG6pTxRLHbvs2OEYXNh9W6BXYvpi06cmNBLmcO3HpiTdkS9kRiulXUqwVCEC2K3mMBKfdIqlQYTKTLL2p0RFf8LS4Fr-XR7zdlceBgIqlhrSTPiE8BAPsC_W8gsf30kiyl0MPFHe99TPg84A",
    "scope": "openid",
    "token_type": "Bearer",
    "expires_in": 300
}
```





## 设备认证模式





### 简介





### 测试方式



1. 获取设备授权码

```java
http://localhost:9000/oauth2/device_authorization?client_id=device-message-client&scope=message.read
```



> 响应信息
>
> ````json
> {
>     "user_code": "PPXG-CQSL",
>     "device_code": "DvZFl7ShCOGKNaICvB6ixuf8RJNSa-Wx99hdVPlRSW9NQhgX1uWRij2vPN-zrpfGPZrLX6FCn9pIpPIKm9Aa9AalRImP5v4Fxn4NjVmdVhwD134i_0eBMly_TZGzJjpL",
>     "verification_uri_complete": "http://localhost:9000/activate?user_code=PPXG-CQSL",
>     "verification_uri": "http://localhost:9000/activate",
>     "expires_in": 300
> }
> ````
>
> user_code: 用户在浏览器打开验证地址时输入的内容
>
> device_code：设备码，用该值换取token
>
> verification_uri_complete：用户在浏览器打开的验证地址，页面会自动获取参数并提交表单
>
> verification_uri：验证地址，需要用户输入user_code
>
> expires_in：过期时间，单位（秒）



2. 验证设备



访问上面json中返回的路径： 任选其一在游览器访问



```json
{
    "verification_uri_complete": "http://localhost:9000/activate?user_code=PPXG-CQSL",
    "verification_uri": "http://localhost:9000/activate",
}
```



3. 获取token



```http
POST /oauth2/token HTTP/1.1
Host: localhost:9000
Content-Type: application/x-www-form-urlencoded
Content-Length: 238

grant_type=urn%3Aietf%3Aparams%3Aoauth%3Agrant-type%3Adevice_code&device_code=设备码&client_id=device-message-client
```



```java
curl --location --request POST "http://localhost:9000/oauth2/token" --header "Content-Type: application/x-www-form-urlencoded" --data-urlencode "grant_type=urn:ietf:params:oauth:grant-type:device_code" --data-urlencode "device_code=设备码" --data-urlencode "client_id=device-message-client"
```



```json
{
    "access_token": "eyJraWQiOiJkMjFjYzcwYS1jYTdkLTRjN2QtYjE0My1iOTEzMWY3OWNmZjYiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImF1ZCI6ImRldmljZS1tZXNzYWdlLWNsaWVudCIsIm5iZiI6MTY4OTMwNjcwNiwic2NvcGUiOlsibWVzc2FnZS5yZWFkIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6OTA5MCIsImV4cCI6MTY4OTMwNzAwNiwiaWF0IjoxNjg5MzA2NzA2fQ.lPBiwydrE7Dp7gZQ2C4QmagyIpnSVFL8XqkF2k05-E0dndGUWtqqH4Yoo08c7IUid-c5zGZbaunBtIux1jTAALDJxWy-VUB8lXim-vnsYzyX1BIaXZNW8HqFfnb-E1mStjBl79iLFV82kFfwt0-zkcEwcCaBjuSILnGjCDRWx0JEHvpQHwbXkrZtjSRYU8H2fPVp9pV06dn4OZo501hY_nV5zcd7K4bJJhOkrC2dFhwaiTs3DvLYfPK7tl2LEHRV6IRVy7EpDCSt7cAADrbNtZ2TXmeVzdU50xpcEl_z5xHB-bBRW7PTVv2iHy5zXPbS1myL10-qNA9Qf6MCO2Q7Pg",
    "refresh_token": "G0JWo6I3yxU0Qxpp-8gE_VpYvdTyuF8Oef3LP4ve4p7rp2k89O8gvH4wEj-RIPQka4ZJpUpfILyoQg6irDQOt9mc99B203_hilpqNvcYgK7GTjQ7WCdWzLQwQggeZP_T",
    "scope": "message.read",
    "token_type": "Bearer",
    "expires_in": 300
}
```







## 密码模式



### 简介





### 测试方式





## 短信验证码模式

### 简介

### 测试方式

## 源码如何看



### 日记DEBUG



````yaml
logging:
  level:
    root: trace
    org.apache: error
    org.thymeleaf: error
````

```java
@EnableWebSecurity(debug = true)
```



## 验证码校验


