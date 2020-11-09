# TRUCK-单证是否派车 #

# 1. 通过订单号、进出口查询是否派车 #

## 1.1 地址和方法 ##

* **相对URL**："/truck-web/waybill/status"

* **方法**：POST

## 1.2 请求参数 ##

参数名 | 类型 | 必填 | 含义 | 备注
-----|-----|-----|-----|-----
refCde | string | 是 | 进口提单号 |
obIb | string | 是 | 进出口 | O 出口，I 进口

## 1.3 请求样例 ##
```
Request Payload:
{
    "refCde": "Q001973390",
    "obIb": "I"
}
```
## 1.4 响应参数 ##

字段名 |含义
-----|-----
code | 状态码（可忽略）
success | 成功标志
responseStatus| 状态描述及状态码
message | 失败后的提示信息
data | 派车状态
```
其中responseStatus中的元素属性如下
```
字段名 |含义
---- | ----
code | 请求状态码
desc | 请求状态描述

## 1.5 响应字段说明 ##

### 1.5.1 正常请求并返回
#### 1.5.1.1 已派车
```
{
    "code": 0,
    "success": true,
    "responseStatus": {
        "code": 0,
        "desc": "成功"
    },
    "data": "true"
}
```
#### 1.5.1.2 未派车
```
{
    "code": 0,
    "success": true,
    "responseStatus": {
        "code": 0,
        "desc": "成功"
    },
    "data": "false"
}
```
### 1.5.2 错误请求并返回
```
{
    "code": 5400,
    "success": true,
    "responseStatus": {
        "code": 5400,
        "desc": "参数异常"
    },
    "message": "订单号不能为空"
}
```
### 1.5.3 异常提示
```
{
    "code": 5500,
    "success": false,
    "responseStatus": {
        "code": 5500,
        "desc": "异常"
    },
    "message": "查询派车状态异常"
}
```