# 自动派车 #

# 1. 通过订单号、箱号来实现自动派车 #

## 1.1 地址和方法 ##

* **相对URL**："/truck-web/workOrder/autoDispatchToFleet"

* **方法**：POST

## 1.2 请求参数 ##

参数名 | 类型 | 必填 | 含义 | 备注
-----|-----|-----|-----|-----
refCde | string | 是 | 订单号 | 小于等于20位
cntrList | array | 是 | 箱信息 | 至少存在一个箱信息
```
其中cntrList中的元素属性参数如下
```
参数名 | 类型 | 必填 | 含义 | 备注
-----|-----|-----|-----|-----
cntrNum | string | 是 | 箱号 | 小于等于12位
prePickupDate | string | 是 | 预抵时间 | 格式: yyyy-MM-dd HH:mm:ss

## 1.3 请求样例 ##
```
Request Payload:
{
	"refCde": "AUTOTEST01",
 	"cntrList":[{
 		"cntrNum": " CCLU0018676",
 		"prePickupDate": "2018-05-11 21:21:21"
 	}]
}
```
## 1.4 响应参数 ##
字段名 |含义
-----|-----
code | 状态码（cargosmart可忽略）
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
```
{
	"code": 0,
	"success": true,
	"responseStatus":{
		"code": 0,
		"desc": "请求成功"
	},
	"data":{
		"orderNumber": "SHAEX00000080"
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
    "message": "必须至少存在一个箱信息"
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
    "message": "AUTOTEST01: 提单在拖车平台已存在派车单"
}
```