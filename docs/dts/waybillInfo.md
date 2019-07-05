# 订单信息查询 #

按订单号、箱号查询，提供订单的路径、船期（包括计划时间、实际时间）、箱号等信息。支持多订单号一次性查询（最多50条）。


# 1. 根据订单号查询 #

## 1.1 地址和方法 ##

* **相对URL**："/dts/waybill/info"

* **方法**：POST

## 1.2 请求参数 ##
参数名 | 类型 | 必填 | 含义 
-----|-----|-----|-----
wblNum | string | 是 | 订单号 
cntrNums | array | 否 | 箱号 

## 1.3 请求样例 ##
```
Request Payload:

[ //array,一次查询多组订单箱号组合
    {
        "wblNum": "Q000017440", //string, 订单号
        "cntrNums": [ // array，箱号，空代表所有箱子
            "CCLU6976054"
        ]
    }
]

```

## 1.4 响应参数 ##

字段名 |含义
-----|-----
code | 状态码
success | 成功标志
message | 提示信息
data | 订单信息

## 1.5 响应字段说明 ##
```
{
    "code": 0,
    "success": true,
	"message": null,
    "data": [
        {
            "wblNum": "Q000017440", //string, 订单号
            "obTrafficTerm": "DR", //string, 出口运输条款
            "ibTrafficTerm": "DR", //string, 进口运输条款
            "releaseStatus": "RELEASE", //string, 订单放货状态。"RELEASE"："已放货"，"RELEASE_PARTLY"："部分放货"，"HOLD"："扣货"
            "cargoNme": "大米", //string, 货名
            "route": {
                "origCityFullNme": "三永街道, 龙凤区, 大庆市", //string, 起运地
                "origAddrDtl": "黑龙江省大庆市龙凤区化工路 大庆石化 18645997905", //string, 起运地详细地址
                "destCityFullNme": "珠池街道, 龙湖区, 汕头市", //string, 目的地
                "destAddrDtl": "汕头港物流中心有限公司汕头市中山路164号物流中心大楼A幢3楼", //string, 目的地详细地址
                "rteDesc": [ //array, 路径（按顺序）
                    "让湖路(铁路)",
                    "大连",
                    "漳州",
                    "汕头"
                ]
            },
            "cntrs": [ //array, 箱信息
                {
                    "cntrNum": "CCLU6976054", //string,箱号（包括校验位）
                    "cntrType": "40HQ", //string,箱型尺寸
                    "sealNum": "H959147" //string,铅封号
                }
            ],
            "rteLegs": [ //array, 分段路径
                {
                    "seqNum": 1, //string,铅封号
                    "polNme": "大连", //string,本段路径的装港
                    "podNme": "漳州", //string,本段路径的卸港
                    "voy": "038S", //string,本段路径的航次号,包括航向
                    "vesselNme": "中航盛", //string,本段路径的船名
                    "ata": "2018-07-30 17:20", //string,本段路径的实际抵港时间
                    "eta": "2018-07-30 22:00", //string,本段路径的预计抵港时间
                    "etd": "2018-07-24 06:00", //string,本段路径的预计离泊时间
                    "atd": "2018-07-25 06:41", //string,本段路径的实际离泊时间
                    "cntrNums": [ ]//array,走该船的箱号。如果不是分批出运，此字段放空
                },
                {
                    "seqNum": 2,
                    "polNme": "漳州",
                    "podNme": "汕头",
                    "voy": "068S",
                    "vesselNme": "钱海82",
                    "ata": "2018-08-06 03:55",
                    "eta": "2018-08-02 23:00",
                    "etd": "2018-07-31 11:00",
                    "atd": "2018-08-02 12:00",
                    "cntrNums": [ 
                        "CCLU6976054"
                    ]//array,走该船的箱号。分批出运案例
                },
                {
                    "seqNum": 2,
                    "polNme": "漳州",
                    "podNme": "汕头",
                    "voy": "077S",
                    "vesselNme": "兴辉河2",
                    "ata": "2018-08-06 01:55",
                    "eta": "2018-08-02 20:00",
                    "etd": "2018-07-31 18:00",
                    "atd": "2018-08-02 19:00",
                    "cntrNums": [ 
                        "MAGU5198995"
                    ]//array,走该船的箱号。分批出运案例
                }
            ],
            "cntrEvents": [ //array，箱动态信息
                {
                    "cntrNum": "CCLU6976054", //string,箱号。以下为该箱的所有实际动态
                    "events": [ //array，每个箱子的箱动态信息
                        {
                            "eventType": "RCVE", //string,动态类型代码。 TRUK:出口门段订单指派司机; SNTS:出口提空箱; 
												 //RCVF:出口重箱回场; LODF:第一装港装船; LODT:中转港卸船; 
												 //DCHT:中转港装船; DCHF:最终卸港卸船; DRVR:进口门段订单指派司机; 
												 //SNTC:进口门段提重箱; STRP:收货地拆箱; SIGN:货物签收; RCVE:空箱返场
                            "eventDesc": "空箱已经返回指定场站", //string,动态详细描述
                            "eventDtLoc": "2018-08-13 17:10:00", //string,动态发生时间
                            "vesselNme": null, //string,船舶名称
                            "voy": null, //string,航次号,包括航向
                            "currLoc": "汕头港集", //string,动态发生地（码头、堆场、火车站）
							"polNme": null, //string,当前路径段的装港名称
                            "podNme": null, //string,当前路径段的卸港名称
                            "truckNme": null, //string,拖车公司名称
                            "truckTel": null, //string,车队电话
                            "driverNme": null, //string,司机姓名
                            "driverTel": null, //string,司机电话
                            "truckNo": null //string,拖车车牌号
                        }
                    ]
                }
            ]
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
