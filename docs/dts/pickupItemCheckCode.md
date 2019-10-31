# 进口获取提箱校验码 #

# 1. 通过订单号、箱号来查询提箱校验码 #

## 1.1 地址和方法 ##

* **相对URL**："/truck-web/workOrder/pickupItemCheckCode"

* **方法**：POST

## 1.2 请求参数 ##

参数名 | 类型 | 必填 | 含义 | 备注
-----|-----|-----|-----|-----
refCde | string | 是 | 进口提单号 |
cntrNum | string | 是 | 箱号 |

## 1.3 请求样例 ##
```
Request Payload:
{
	"refCde": "Q014734900",
 	"cntrNum": "FCIU520373"
}
```
## 1.4 响应参数 ##

字段名 |含义
-----|-----
code | 状态码（可忽略）
success | 成功标志
responseStatus| 状态描述及状态码
message | 派车失败后的提示信息
data | 派车信息
```
其中responseStatus中的元素属性如下
```
字段名 |含义
---- | ----
code | 请求状态码
desc | 请求状态描述

## 1.5 响应字段说明 ##

### 1.5.1 正常请求并返回
#### 1.5.1.1 已放货
```
{
    "code": 0,
    "success": true,
    "responseStatus":{
        "code": 0,
        "desc": "成功"
    },
    "data":{
        "pickupItemCheckCode": "3016",	//提箱校验码
        "ifDelivery": "已放货"
    }
}
```
#### 1.5.1.2 未放货
```
{
    "code": 0,
    "success": true,
    "responseStatus":{
        "code": 0,
        "desc": "成功"
    },
    "data":{
        "ifDelivery": "未放货"
    }
}
```
### 1.5.2 错误请求并返回
```
{
    "code": 5400,
    "success": true,
    "responseStatus":{
        "code": 5400,
        "desc": "参数异常"
    },
    "message": "箱号不能为空"
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
    "message": "获取校验码异常"
}
```