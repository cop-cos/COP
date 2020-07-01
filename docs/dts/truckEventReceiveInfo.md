# 1. 车队准入，拖车动态接收 #

## 1.1 地址和方法 ##

* **相对URL**："/truck-web/waybill/truckEventReceiveInfo"

* **方法**：POST
## 1.2 请求参数 ## 

字段|说明|是否必填|数据库类型|备注
-----|-----|-----|-----|-----
workOrderNo|订单发送接口中的workOrderNo，这个是唯一的|必填|StringVARCHAR2(20BYTE)|拖车内部唯一派单号，拖车动态回传时为必输项
obIb|进出口标志，I为进口，O为出口|必填|CHAR(1BYTE)|进出口标志，I为进口，O为出口
msgType|消息类型，参照备注，非常重要的字段！|必填|String(10CHAR)|详情见<a href="#msgType">msgType</a>
truckFleetName|车队名称|必填|StringNVARCHAR2(50CHAR)|
truckFleetCde|车队在拖车系统的代码|必填|VARCHAR2(20BYTE)|
createdAt|消息的创建时间，格式必须为"2019-11-0514:32:38"|必填|TIMESTAMP(6)|
createdBy|这个为动态的操作人| |VARCHAR2(32BYTE)|
truckTel|车队电话| |VARCHAR2(70BYTE)|
receiptOrderPictures|车队箱子拍的照片，建议为http形式的访问路径，多个图片地址用,拼接|msgType为HDQSM时必填|VARCHAR2(600BYTE)|可访问的广域网图片地址
eventTime|动态时间|必填|TIMESTAMP(6)|动态时间
isUpdate|是否为同一运单同一箱子同一状态的更新，更新为1，否则为0。拖车的接收规则：一、订单完成，不接受任何动态数据二、比如箱子的当前状态是ZHKSM，则之前的动态不接受数据更新三、更新方式为整条记录全覆盖|必填|CHAR(1BYTE)|是否为更新的动态，更新为1，否则为0。更新方式为整条记录全覆盖
containers|箱信息|必填| |详情见<a href="#containers">containers</a>
driver|司机信息| | |详情见<a href="#driver">driver</a>
licence|车牌信息| | |详情见<a href="#licence">licence</a>
gpsInfo|gsp信息| | |详情见<a href="#gpsInfo">gpsInfo</a>

<a name="containers"></a>
```
其中containers中的元素属性参数如下
```

containers|箱子信息|是否必填|数据库类型|备注
-----|-----|-----|-----|-----
internalShmtNum|虚拟箱号|必填|VARCHAR2(11BYTE)|
cntrNum|箱号|必填|NVARCHAR2(11CHAR)|长度11位，含校验位
cntrType|箱型|必填|CHAR(4BYTE)|
sealNum|铅封号|必填|NVARCHAR2(36CHAR)|铅封号
picPaths|箱子的照片| |List<String>|

<a name="driver"></a>
```
其中driver中的元素属性参数如下
```

driver|司机信息|是否必填|数据库类型|备注
-----|-----|-----|-----|-----
name|姓名|必填|NVARCHAR2(50CHAR)|
idCard|身份证号，有真实性校验，满足身份证号码规则。司机信息更新主键。|必填|VARCHAR2(18BYTE)|
tel|电话|必填|NVARCHAR2(20CHAR)|
email|邮件| |NVARCHAR2(30CHAR)|

<a name="licence"></a>
```
其中licence中的元素属性参数如下
```

licence|车牌车辆信息|是否必填|数据库类型|备注
-----|-----|-----|-----|-----
licencePlate|车牌号|必填。有真实性校验，满足车牌号码规则。车辆信息更新主键|VARCHAR2(30BYTE)|
truckModel|0:普通,1:平板拖车,2:自卸拖车|必填|CHAR(1BYTE)|
axle|轴数；可选4轴、5轴、6轴，填数字即可| |VARCHAR2(10BYTE)|车辆轴数
weight|载重，单位是吨，填数字即可| |NUMBER(10,2)|载重
roadTransportCertificate|道路运输证号|必填|VARCHAR2(25BYTE)|道路运输证

<a name="gpsInfo"></a>
```
其中gpsInfo中的元素属性参数如下
```

gpsInfo|经纬度信息|是否必填|数据库类型|备注
-----|-----|-----|-----|-----
type|经纬度类型，必须为bd09|必填|VARCHAR2(10BYTE)|经纬度类型
device|设备号| |VARCHAR2(100BYTE)|设备型号
latitude|纬度，必须为bd09格式|必填|NUMBER(10,6)|
longitude|经度，必须为bd09格式|必填|NUMBER(10,6)|
isCorrect|车队内部需对经纬度正确性做校验，比如当前是否在打卡点范围内，打卡误差范围是4km，1（绿卡）为在打卡点内，0（红卡）为不在，拖车平台会对绿卡动态做再次校验。|必填|int|

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
    "eventTime": "2020-04-01 11:16:30",
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
        "name": "晁林锋",
        "tel": "13656071546",
        "email": "guzhh1@coscon.com"
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
        "latitude": "31.229920",
        "longitude": "121.537425",
        "isCorrect": "0"
    },
    "msgType": "HXM",
    "truckFleetName": "厦门兆冠物流有限公司",
    "truckFleetCde": "XMZG",
    "createdAt": "2020-04-01 11:16:30",
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
