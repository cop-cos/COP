# 实时位置接口调用
	sender：Truck，该接口由车队方提供，数据需按如下规定格式
## 数据接收形式
    Json
## Json 样例：
```
{  
	"licencePlate": "沪A12345",  
    "workOrderNo": "XCTIM00009344"  
} 
```
## 字段含义
字段 | 说明 | 是否必填 | 备注
-----|-----|-----|-----
licencePlate|需要查询的车牌号|是| 
workOrderNo|拖车内部唯一派单号|是| 