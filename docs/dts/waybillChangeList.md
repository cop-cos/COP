# 订单信息变更查询 #

根据时间范围，查询订单信息发生变化的订单号集合（仅限最近3天）。


# 1. 根据时间查询 #

## 1.1 地址和方法 ##

* **相对URL**："/dts/waybill/changeList"

* **方法**：POST

## 1.2 请求参数 ##
参数名 | 类型 | 必填 | 含义 
-----|-----|-----|-----
fromDt | Date | 是 | 开始时间 
toDt | Date | 否 | 截止时间 

## 1.3 请求样例 ##
```
Request Payload:

{
    "fromDt": "2019-01-01 00:00:00", //Date（yyyy-MM-dd HH:mm:ss） 开始时间 
    "toDt": "2019-01-02 10:00:00" //Date（yyyy-MM-dd HH:mm:ss） 截止时间（空代表不限制截止时间）
}
```

## 1.4 响应参数 ##

字段名 |含义
-----|-----
code | 状态码
success | 成功标志
message | 提示信息
data | 变更订单号集合

## 1.5 响应字段说明 ##
```
{
    "code": 0,
    "success": true,
    "message": null,
    "data": ["Q000017440", "Q000017540"] //array,变更订单号集合
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
