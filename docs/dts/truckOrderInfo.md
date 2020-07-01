# 运单数据接口调用
    该接口由车队方提供，数据需按如下规定格式
## 数据接收形式
    Json
## Json 样例：
```
{
    "containers": [
        {
            "internalShmtNum": "X06ZMEW",
            "cntrNum": "CBHU4494074",
            "sealNum": "CG362158",
            "cntrType": "20GP",
            "weight": "2000",
            "cntrAddInfo1": "",
            "cntrAddInfo2": ""
        }
    ],
    "orderInfo": {
        "detailAddress": "天津市天津东丽区金钟街道",
        "address": "上海市，上海市，金山区，后续提供",
        "refCde": "D015980020",
        "podNme": "新港",
        "polNme": "泰山",
        "truckFleetCde": "YGBL",
        "doorTel": "13232860312",
        "doorLinkman": "梁佩琪",
        "doorEmail": "guzhh1@coscon.com",
        "shippingSchedule": "2019-10-29 00:00:00",
        "orderId": "1568132",
        "facilityCde": "TSN05",
        "cgoNameCN": "面条",
        "isJjs": "1",
        "isCancel": "1",
        "createdAt": "2019-10-25 10:37:54",
        "createdBy": "fyhy",
        "wayBillRemark": "江门市新会区吉盛运输有限公司  13232860312  201910251041",
        "truckRemark": "7点前提箱",
        "expectDoorDate": "2019-11-04 10:37:00",
        "shipCompanyId": "中远海运集装箱运输有限公司",
        "workOrderNo": "TSNIM00110254",
        "specialAtt": "A2",
        "obIb": "O",
        "vesselName": "XIN DA YANG ZHOU",
        "voyageDirection": "057S",
        "addInfo1": "",
        "addInfo2": "",
        "addInfo3": "",
        "addInfo4": "",
        "addInfo5": "",
        "facilityGroupNameCN": "",
        "facilityGroupCde": "",
        "facilityNameCN": "",
        "bkgOfceNameCN": ""
    }
}
```

## 字段含义
字段 | 说明 | 是否必填 | 备注
-----|-----|-----|-----
containers | 箱信息 | 是 | 详情见<a href="#containers">containers</a>
orderInfo | 订单信息 | 是 | 详情见<a href="#orderInfo">orderInfo</a>

<a name="containers"></a>
```
其中containers中的元素属性参数如下
```
字段 | 说明 | 是否必填 | 备注
-----|-----|-----|-----
internalShmtNum|拖车内部虚拟箱号，拖车动态回传时为必输项|是|
cntrNum|箱号|是|
sealNum|铅封号|是|
cntrType|箱型|是|
weight|箱总重，箱重单位KG|是|
cntrAddInfo1|集装箱扩展信息1|否|
cntrAddInfo2|集装箱扩展信息2|否|

<a name="orderInfo"></a>
```
其中orderInfo中的元素属性参数如下
```
字段 | 说明 | 是否必填 | 备注
-----|-----|-----|-----
detailAddress|运单的详细地址|是| 
address|四级门点地址，有可能是五级，或者三级|是| 
refCde|运单号|是| 
podNme|最终卸港|是| 
polNme|第一装港|是| 
truckFleetCde|车队代码，IRIS4中的供应商代码。|是| 
doorTel|门点地址联系电话|是| 
doorLinkman|门点联系人|是| 
doorEmail|门点email|是| 
shippingSchedule|船期，进口为预计到港时间，出口为预计离港时间|是| 
orderId|外部单号，如果是泛亚电商的运单，则为泛亚的运单号，否则为空|是| 
facilityCde|运单所在码头。进口对应卸港码头，出口对应装港码头|是| 
cgoNameCN|货物名称，中文|是| 
isJjs|泛亚电商急集送的单子为1，否则为0|是| 
isCancel|是否取消此订单，取消为1，否则为0|是| 
createdAt|运单的派单时间|是| 
createdBy|运单派单人|是| 
wayBillRemark|运单备注|是| 
truckRemark|车队指示|是| 
expectDoorDate|运单的门点时间，客户门点装箱或客户门点落重箱的时间|是| 
shipCompanyId|默认为--中远海运集装箱运输有限公司|是| 
workOrderNo|拖车内部唯一派单号，拖车动态回传时为必输项|是| 
specialAtt| 运输的特殊指示。详细见附表|是| 详情见<a href="#specialAtt">specialAtt</a>
obIb|出口为O，进口为I|是| 
vesselName|船名，内贸为中文船名，外贸为英文船名|是| 
voyageDirection|航次航向，航向为N、S、W、D|是| 
facilityGroupNameCN|港口中文名|是| 
facilityGroupCde|港口代码|是| 
facilityNameCN|码头中文名|是| 
bkgOfceNameCN|订舱单位中文|是|
addInfo1|扩展信息1|否|
addInfo2|扩展信息2|否|
addInfo3|扩展信息3|否|
addInfo4|扩展信息4|否|
addInfo5|扩展信息5|否|

```
其中specialAtt中的元素属性参数如下
```
<a name="specialAtt"></a>

specialAtt|对应类目
-----|-----
C2|水果
C1|竹
GLT|高岭土
CZ|瓷砖
SBC|石板材
A1|直提
B1|码头插电
B2|全程插电
HH|重去重回
D1|白天卸货
E1|自卸车
A2|普通货
	

