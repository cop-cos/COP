# 历史轨迹接口调用
    sender：Truck，该接口由车队方提供，数据需按如下规定格式
## 数据接收形式
    Json
## Json 样例：
```
{
    "licencePlate": "沪A12345",
    "workOrderNo": "XCTIM00009344",
    "fromDate": "2020-04-01T11:16:30.000+0800",
    "toDate": "2020-04-01T11:16:30.000+0800"
}
```
## 字段含义
字段 | 说明 | 是否必填 | 备注
-----|-----|-----|-----
licencePlate|需要查询的车牌号|是|
workOrderNo	|拖车内部唯一派单号|是|
fromDate	|查询时间段的起始时间|是|格式：yyyy-MM-dd'T'HH:mm:ss.SSSZ
toDate	|查询时间段的结束时间|是|格式：yyyy-MM-dd'T'HH:mm:ss.SSSZ