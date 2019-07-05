# 运单下载 #

按订单号查询，返回运单PDF文件。支持多订单号一次性查询下载（最多30条）。


# 1. 根据订单号查询 #

## 1.1 地址和方法 ##

* **相对URL**："/dts/waybill/download"

* **方法**：POST

## 1.2 请求参数 ##
参数名 | 类型 | 必填 | 含义 
-----|-----|-----|-----
wblNums | array | 是 | 订单号 

## 1.3 请求样例 ##
```
Request Payload:

{
	"wblNums" : ["Q000810170", "Q000810180"]
}
```

## 1.4 响应参数 ##

字段名 |含义
-----|-----
code | 状态码
success | 成功标志
message | 提示信息
data | 文件信息


## 1.5 响应字段说明 ##
```
{
    "code": 0,
    "success": true,
	"message": null,
    "data": [ //array,文件信息
		{
			"fileContent" : "JVBERi0xLjQKJeLjz9MK...", //BASE64编码的字符，需转码后生成文件
			"fileNme" : "Q000017440.pdf" //文件名
		},
		{
			"fileContent" : "JVBERi0xLjQKJeLjz9MK...",
			"fileNme" : "Q000810180.pdf"
		}
	]
}
```
## 1.6 异常示例 ##


```
{
    "code": 17000,
    "success": false,
    "message": "系统异常"
}
```
