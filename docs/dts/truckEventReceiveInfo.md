# 拖车动态接收 #

# 1. 车队准入，拖车动态接收 #

## 1.1 地址和方法 ##

* **相对URL**："/truck-web/waybill/truckEventReceiveInfo"

* **方法**：POST
## 1.2 请求参数 ## 

字段|说明|是否必填|类型|备注
-----|-----|-----|-----|-----
workOrderNo|订单发送接口中的workOrderNo，这个是唯一的|必填|string|拖车内部唯一派单号，拖车动态回传时为必输项
obIb|进出口标志|必填|string|I为进口，O为出口
msgType|消息类型，参照备注，非常重要的字段！|必填|string|详情见<a href="#msgType">msgType</a>
truckFleetName|车队名称|必填|string|
truckFleetCde|车队在拖车系统的代码|必填|string|
createdAt|消息的创建时间|必填|date|格式yyyy-MM-dd'T'HH:mm:ss.SSSZ
createdBy|这个为动态的操作人| |string|
truckTel|车队电话| |string|
receiptOrderPictures|车队箱子拍的照片|msgType为HDQSM时必填|string|建议为http形式的访问路径，可访问的广域网图片地址，多个图片地址用','拼接
eventTime|动态时间|必填|date|格式yyyy-MM-dd'T'HH:mm:ss.SSSZ
isUpdate|是否为同一运单同一箱子同一状态的更新|必填|string|更新为1，否则为0。更新方式为整条记录全覆盖。 <br/>拖车的接收规则：<br/> 一、订单完成，不接受任何动态数据 <br/>二、比如箱子的当前状态是ZHKSM，则之前的动态不接受数据更新 <br/>三、更新方式为整条记录全覆盖
containers|箱信息|必填| |详情见<a href="#containers">containers</a>
driver|司机信息| | |详情见<a href="#driver">driver</a>
licence|车牌信息| | |详情见<a href="#licence">licence</a>
gpsInfo|gsp信息| | |详情见<a href="#gpsInfo">gpsInfo</a>

<a name="containers"></a>
```
其中containers中的元素属性参数如下
```

containers|箱子信息|是否必填|类型|备注
-----|-----|-----|-----|-----
internalShmtNum|虚拟箱号|必填|string|
cntrNum|箱号|必填|string|长度11位，含校验位
cntrType|箱型|必填|string|
sealNum|铅封号|必填|string|铅封号
picPaths|箱子的照片| |array|

<a name="driver"></a>
```
其中driver中的元素属性参数如下
```

driver|司机信息|是否必填|类型|备注
-----|-----|-----|-----|-----
name|姓名|必填|string|
idCard|身份证号|必填|string|有真实性校验，满足身份证号码规则。司机信息更新主键。
tel|电话|必填|string|有真实性校验
email|邮件| |string|

<a name="licence"></a>
```
其中licence中的元素属性参数如下
```

licence|车牌车辆信息|是否必填|类型|备注
-----|-----|-----|-----|-----
licencePlate|车牌号|必填|string|有真实性校验，满足车牌号码规则。车辆信息更新主键
truckModel|拖车类型|必填|string|0:普通, 1:平板拖车, 2:自卸拖车
axle|车辆轴数| |string|可选4、5、6
weight|载重| |string|单位:吨
roadTransportCertificate|道路运输证号|必填|string|

<a name="gpsInfo"></a>
```
其中gpsInfo中的元素属性参数如下
```

gpsInfo|经纬度信息|是否必填|类型|备注
-----|-----|-----|-----|-----
type<img width=200/>|经纬度类型<img width=200/>|必填<img width=200/>|string|bd09
device|设备型号| |string|
latitude|纬度|必填|string|bd09格式，保留小数点后12位
longitude|经度|必填|string|bd09格式，保留小数点后12位
isCorrect|打卡正确性|必填|string|可选 <br/>0: 红卡 表示不在打卡范围内（误差范围>4km） <br/>1: 绿卡 表示在打卡范围内（误差范围<=4km） <br/>拖车平台会对绿卡动态做再次校验

<a name="msgType"></a>
```
不同的msgType，字段的填写内容要求如下
```

msgType|字段内容|receiptOrderPictures|latitude、longitude|driver|licence|gpsInfo
-----|-----|-----|-----|-----|-----|-----
YPIM|按箱分配司机| | |需要填写|需要填写|
TXM|提箱| |进口需要填写|需要填写|需要填写|进口需要填写
YDM|预计抵达提箱点| | |需要填写|需要填写|
ZHKSM|装货开始，对应出口| |需要填写|需要填写|需要填写|需要填写
ZHJSM|装货结束，对应出口| |需要填写|需要填写|需要填写|需要填写
XHKSM|卸货开始，对应进口| |需要填写|需要填写|需要填写|需要填写
XHJSM|卸货结束，对应进口| |需要填写|需要填写|需要填写|需要填写
HXM|还箱| |出口需要填写|需要填写|需要填写|出口需要填写
HDQSM|签收单|需要填写| | | |

## 1.3 请求样例 ##
```
Request Payload:
{
    "workOrderNo": "SHAIM00000174",
    "eventTime": "2020-04-01T11:16:30.000+0800",
    "obIb": "O",
    "containers": [
        {
            "internalShmtNum": "X01MSCM",
            "sealNum": "M432536f",
            "cntrNum": "CCLU3976153",
            "cntrType": "20GP",
            "picPaths": []
        }
    ],
    "driver": {
        "idCard": "411024198012204217",
        "name": "晁林",
        "tel": "12345",
        "email": ""
    },
    "licence": {
        "licencePlate": "豫AN5985",
        "truckModel": "0",
        "axle": "5",
        "weight": "0",
        "roadTransportCertificate": "419182104051"
    },
    "gpsInfo": {
        "type": "BD09",
        "device": "CBHU40",
        "latitude": "31.2299200",
        "longitude": "121.5374250",
        "isCorrect": "0"
    },
    "msgType": "HXM",
    "truckFleetName": "厦门兆冠物流有限公司",
    "truckFleetCde": "XMZG",
    "createdAt": "2020-04-01T11:16:30.000+0800",
    "createdBy": "guonaka",
    "truckTel": "13104173789",
    "receiptOrderPictures": null,
    "isUpdate": "0"
}
```
## 1.4 响应参数 ##
字段名 |含义
-----|-----
code | 状态码
success | 成功标志
responseStatus| 状态描述及状态码
message | 失败后的提示信息
data | 动态接收正常返回信息
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
		"desc": "成功"
	},
	"data": "更新拖车动态[还箱]成功"
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
    "message": "[cntrNum]前4位为大写英文字母,第4位必须为U,后7位数字;[licencePlate]车牌不合法; "
}
```
### 1.5.3 异常提示
```
{
    "code": 5500,
    "success": false,
    "responseStatus":{
        "code": 5500,
        "desc": "异常"
    },
    "message": "更新箱信息异常"
}
```