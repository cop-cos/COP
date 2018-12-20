# 货物跟踪 #

货物跟踪是航运物流领域最为常用的功能，为各类客户提供集装箱运输最新情况信息。


# 1. 根据提单号查询 #

## 1.1 地址和方法 ## 

* **相对URL**："/info/tracking/**#YOUR_BL_NO**?numberType=bl"

* **方法**：GET

## 1.2 请求参数 ##

* **#YOUR_BL_NO**:提单号

## 1.3 请求样例 ##

/info/tracking/6103622780?type=bl

## 1.4 响应参数 ##

字段名 |含义
-----|-----
type | 返回的单号类型：bl-提单号，bkg-订舱号，cntr-箱号，none-都不是 
numberType |单号所属类型：bl-提单号，bkg-订舱号，cntr-箱号，both-提单or订舱号，none-都不是 
content | 返回的数据信息

## 1.5 响应样例 ##
```
{
    "code": 0,
    "message": "success",
    "data": {
        "type": "bl", //string, 返回的单号类型, bl:提单号/bkg:订舱号/cntr:箱号/none:都不是
        "numberType": "both", //单号所属类型, bl:提单号/bkh:订舱号/cntr:箱号
        "content": {
            "itNumber": "V2167699827", //string, 美国内陆转运号
            "actualShipment": [ //array, 实时船期
                {
                    "rownum": "1", //string, 行数
                    "sequenceNumber": "1",
                    "vesselName": "COSCO PHILIPPINES", //string, 船名
                    "voyageNo": "039E", //string, 航次
                    "portOfLoading": "Qingdao",  //string, 装货港
                    "actualDepartureDate": "2014-12-29 11:00", //string, 实际离港时间
                    "expectedDateOfDeparture": "2014-12-29 17:00", //string, 预计离港时间
                    "portOfDischarge": "Long Beach", //string, 卸货港
                    "actualArrivalDate": "2015-01-27 05:24", //string, 实际到港时间
                    "estimatedDateOfArrival": "2015-01-27 06:00", //string, 预计到港时间
                    "actualShippingDate": "2014-12-29 07:59", //string, 实际装船时间
                    "city": "Long Beach,United States", //string, 城市
                    "service": "CEN", //string, 航线
                    "actualDischargeDate": "2015-01-29 10:14", //string, 开始卸船时间
                    "vesselCde": "T83" //string, 船号
                }
            ],
            "trackingPath": { //object, 订舱信息
                "billOfladingRefCode": "6103622780", //string, 订舱号
                "trackingGroupReferenceCode": "6103622780", //string, 提单号
                "fromCity": "Qingdao ,Shandong ,China", //string, 接货地
                "toCity": "Dallas ,Texas ,United States", //string, 目的地
                "cgoAvailTm": "2015-02-04 06:15", //string, 预计到达提货地时间
                "cgoFinalTm": null,
                "vslNme": "COSCO PHILIPPINES", //string, 船名
                "voyNumber": "039E", //string, 航次
                "pol": "Qingdao - QQCTU Qingdao Qianwan United Ctn", //string, 装货港
                "pod": "Long Beach - Pacific Container Terminal", //string, 卸货港
                "flag": null,
                "svvd": "CEN T83039E",
                "blType": "Sea WayBill",
                "blRealStatus": "Sea Waybill"
            },
            "containerStatus": [ //array, 集装箱最新动态
                {
                    "label": "Empty Equipment Returned", //string, 标签
                    "containers": [ //array, 集装箱信息
                        {
                            "containerUuid": "1", //string, uuid
                            "containerNumber": "CBHU4398907", //string, 集装箱号
                            "containerType": "20GP", //string, 箱型尺寸
                            "grossWeight": "17720KG", //string,毛重
                            "piecesNumber": 80, //string, 件数
                            "label": "Empty Equipment Returned", //string,标签名
                            "sealNumber": "2", //string, 铅封号
                            "location": "Equipment Storage Services,Dallas,Texas,United States", //string, 当前位置
                            "locationDateTime": "2015-02-06 09:35", //string, 日期
                            "transportation": "Truck", //string, 运输方式
                            "flag": "2",
                            "railRef": null,
                            "inlandMvId": " ",
                            "containerLocation": "0",
                            "isShow": false, //boolean, true时hsCode显示;false时hsCode不显示
                            "hsCode": [],
                            "isNorthAmericaRails": false //boolean, 是否北美铁路
                        }
                    ]
                }
            ],
            "doc_ext": [ //array, 扩展字段
                {
                    "key": "美国内陆转运号", //string, 字段标签
                    "value": "V2167699827" //string, 字段值
                },
                {
                    "key": "Cargo Cutoff",
                    "value": "2014-12-24 16:00"
                }
            ]
        }
    }
}
```
## 1.6 异常示例 ##
```
1.单号错误
{
	"code": 0,
	"message": "success",
	"data": {
		"type": "none",
		"numberType": "none",
		"content": null
	}
}

2.单号为空
{
  "code":0,
  "message":"success"
}

```
## 1.7 错误码解释 ##

1.单号错误
2.单号为空

# 2. 根据订舱号查询 #

## 2.1 地址和方法 ## 

* **URL**："/info/tracking/**#YOUR_BKG_NO**?numberType=bkg"

* **方法**：GET

## 2.2 请求参数 ##

* **#YOUR_BKG_NO**:订舱号

## 2.3 请求样例 ##

/info/tracking/6103622780?type=bkg

## 2.4 响应参数 ##

字段名 |含义
-----|-----
type | 返回的单号类型：bl-提单号，bkg-订舱号，cntr-箱号，none-都不是 
numberType |单号所属类型：bl-提单号，bkg-订舱号，cntr-箱号，both-提单or订舱号，none-都不是 
content | 返回的数据信息

## 2.5 响应样例 ##
```
{
    "code": 0,
    "message": "success",
    "data": {
        "type": "bkg", //string, 返回的单号类型, bl:提单号/bkg:订舱号/cntr:箱号/none:都不是
        "numberType": "both", //单号所属类型, bl:提单号/bkh:订舱号/cntr:箱号
        "content": {
            "itNumber": "V2167699827", //string, 美国内陆转运号
            "actualShipment": [ //array, 实时船期
                {
                    "rownum": "1", //string, 行数
                    "sequenceNumber": "1",
                    "vesselName": "COSCO PHILIPPINES", //string, 船名
                    "voyageNo": "039E", //string, 航次
                    "portOfLoading": "Qingdao",  //string, 装货港
                    "actualDepartureDate": "2014-12-29 11:00", //string, 实际离港时间
                    "expectedDateOfDeparture": "2014-12-29 17:00", //string, 预计离港时间
                    "portOfDischarge": "Long Beach", //string, 卸货港
                    "actualArrivalDate": "2015-01-27 05:24", //string, 实际到港时间
                    "estimatedDateOfArrival": "2015-01-27 06:00", //string, 预计到港时间
                    "actualShippingDate": "2014-12-29 07:59", //string, 实际装船时间
                    "city": "Long Beach,United States", //string, 城市
                    "service": "CEN", //string, 航线
                    "actualDischargeDate": "2015-01-29 10:14", //string, 开始卸船时间
                    "vesselCde": "T83" //string, 船号
                }
            ],
            "trackingPath": { //object, 订舱信息
                "billOfladingRefCode": "6103622780", //string, 订舱号
                "trackingGroupReferenceCode": "6103622780", //string, 提单号
                "fromCity": "Qingdao ,Shandong ,China", //string, 接货地
                "toCity": "Dallas ,Texas ,United States", //string, 目的地
                "cgoAvailTm": "2015-02-04 06:15", //string, 预计到达提货地时间
                "cgoFinalTm": null,
                "vslNme": "COSCO PHILIPPINES", //string, 船名
                "voyNumber": "039E", //string, 航次
                "pol": "Qingdao - QQCTU Qingdao Qianwan United Ctn", //string, 装货港
                "pod": "Long Beach - Pacific Container Terminal", //string, 卸货港
                "flag": null,
                "svvd": "CEN T83039E",
                "blType": "Sea WayBill",
                "blRealStatus": "Sea Waybill"
            },
            "containerStatus": [ //array, 集装箱最新动态
                {
                    "label": "Empty Equipment Returned", //string, 标签
                    "containers": [ //array, 集装箱信息
                        {
                            "containerUuid": "1", //string, uuid
                            "containerNumber": "CBHU4398907", //string, 集装箱号
                            "containerType": "20GP", //string, 箱型尺寸
                            "grossWeight": "17720KG", //string,毛重
                            "piecesNumber": 80, //string, 件数
                            "label": "Empty Equipment Returned", //string,标签名
                            "sealNumber": "2", //string, 铅封号
                            "location": "Equipment Storage Services,Dallas,Texas,United States", //string, 当前位置
                            "locationDateTime": "2015-02-06 09:35", //string, 日期
                            "transportation": "Truck", //string, 运输方式
                            "flag": "2",
                            "railRef": null,
                            "inlandMvId": " ",
                            "containerLocation": "0",
                            "isShow": false, //boolean, true时hsCode显示;false时hsCode不显示
                            "hsCode": [],
                            "isNorthAmericaRails": false //boolean, 是否北美铁路
                        }
                    ]
                }
            ],
            "doc_ext": [ //array, 扩展字段
                {
                    "key": "美国内陆转运号", //string, 字段标签
                    "value": "V2167699827" //string, 字段值
                },
                {
                    "key": "Cargo Cutoff",
                    "value": "2014-12-24 16:00"
                }
            ]
        }
    }
}
```

## 2.6 异常示例 ##
```
1.单号错误
{
	"code": 0,
	"message": "success",
	"data": {
		"type": "none",
		"numberType": "none",
		"content": null
	}
}

2.单号为空
{
  "code":0,
  "message":"success"
}
```
## 2.7 错误码解释 ##

1.单号错误 2.单号为空


# 3. 根据箱号查询 #

## 3.1 地址和方法 ## 

* **URL**："/info/tracking/**#YOUR_CNTR_NO**"

* **方法**：GET

## 3.2 请求参数 ##

* **#YOUR_CNTR_NO**:集装箱号

## 3.3 请求样例 ##

/info/tracking/CBHU4398907

## 3.4 响应参数 ##

字段名 |含义
-----|-----
type | 返回的单号类型：bl-提单号，bkg-订舱号，cntr-箱号，none-都不是 
numberType |单号所属类型：bl-提单号，bkg-订舱号，cntr-箱号，both-提单or订舱号，none-都不是 
content | 返回的数据信息

## 3.5 响应样例 ##
```
{
  "code": 0,
  "message": "success",
  "data": {
    "type": "cntr",
    "numberType": "cntr",
    "content": {
      "containers": [
        {
          "containerCircleStatus": [ //array, 箱动态
            {
              "uuid": "CBHU4398907", //string, uuid
              "containerNumber": "CBHU4398907", //string, 集装箱号
              "containerNumberStatus": "Discharged at Last POD", //string, 最新动态
              "location": "Aisa World Port Terminal,Yangon,Yangon,Myanmar", //string, 位置
              "timeOfIssue": "2015-10-25 17:00", //string, 时间
              "transportation": " " //string, 运输方式
            },
            {
              "uuid": "CBHU4398906",
              "containerNumber": "CBHU4398907",
              "containerNumberStatus": "Loaded at T/S POL",
              "location": "Kepple/Tanjong Pagar Terminal,Singapore,,Singapore",
              "timeOfIssue": "2015-10-20 21:24",
              "transportation": "Vessel"
            },
            {
              "uuid": "CBHU4398905",
              "containerNumber": "CBHU4398907",
              "containerNumberStatus": "Discharged at T/S POD",
              "location": "Pasir Panjang Terminal,Singapore,,Singapore",
              "timeOfIssue": "2015-10-04 08:26",
              "transportation": " "
            },
            {
              "uuid": "CBHU4398904",
              "containerNumber": "CBHU4398907",
              "containerNumberStatus": "Loaded at First POL",
              "location": "QQCTU Qingdao Qianwan United Ctn,Qingdao,Shandong,China",
              "timeOfIssue": "2015-09-24 18:15",
              "transportation": "Vessel"
            },
            {
              "uuid": "CBHU4398902",
              "containerNumber": "CBHU4398907",
              "containerNumberStatus": "Cargo Received",
              "location": "QQCTU Qingdao Qianwan United Ctn,Qingdao,Shandong,China",
              "timeOfIssue": "2015-09-19 21:31",
              "transportation": "Truck"
            },
            {
              "uuid": "CBHU4398903",
              "containerNumber": "CBHU4398907",
              "containerNumberStatus": "Gate-In at First POL",
              "location": "QQCTU Qingdao Qianwan United Ctn,Qingdao,Shandong,China",
              "timeOfIssue": "2015-09-19 21:31",
              "transportation": "Truck"
            },
            {
              "uuid": "CBHU4398901",
              "containerNumber": "CBHU4398907",
              "containerNumberStatus": "Empty Equipment Despatched",
              "location": "Ocean&Great Asia Tspt Co.HuangDao,Qingdao,Shandong,China",
              "timeOfIssue": "2015-09-16 10:40",
              "transportation": "Truck"
            }
          ],
          "container": { //object, 集装箱信息
            "containerUuid": "CBHU4398907", //string, uuid
            "containerNumber": "CBHU4398907", //string, 集装箱号
            "containerType": "20GP", //string, 箱型尺寸
            "grossWeight": "20000KG", //string, 毛重
            "piecesNumber": 20, //string, 件数
            "label": "Discharged at Last POD", //string, 标签
            "sealNumber": "K31436", //string, 封铅号
            "location": "Aisa World Port Terminal,Yangon,Yangon,Myanmar", //string, 当前位置
            "locationDateTime": "2015-10-25 17:00:00", //string, 最新动态时间
            "transportation": " ", //string, 运输方式
            "flag": null,
            "railRef": null,
            "inlandMvId": null,
            "containerLocation": null,
            "isShow": false,
            "hsCode": [],
            "isNorthAmericaRails": false //boolean, 是否需要展示北美铁路信息
          },
          "containerHistorys": [ //array, 箱动态历史
            {
              "uuid": "hisCBHU439890711", //string, uuid
              "containerNumber": "CBHU4398907", //string, 集装箱号
              "containerNumberStatus": "Empty Equipment Returned", //string, 最新动态
              "location": "Northwest Container Services Inc.,Portland,Oregon,United States", //string, 位置
              "timeOfIssue": "2015-06-02 14:25", //string, 时间
              "transportation": "Truck" //string, 运输方式
            },
            {
              "uuid": "hisCBHU439890710",
              "containerNumber": "CBHU4398907",
              "containerNumberStatus": "Gate-out from Final Hub",
              "location": "Northwest Container Services Inc.,Portland,Oregon,United States",
              "timeOfIssue": "2015-06-01 14:32",
              "transportation": "Truck"
            },
            {
              "uuid": "hisCBHU43989079",
              "containerNumber": "CBHU4398907",
              "containerNumberStatus": "Gate-in at Final Hub",
              "location": "Northwest Container Services Inc.,Portland,Oregon,United States",
              "timeOfIssue": "2015-05-26 05:40",
              "transportation": "Rail"
            },
            {
              "uuid": "hisCBHU43989078",
              "containerNumber": "CBHU4398907",
              "containerNumberStatus": "Gate-out from Last POD",
              "location": "SSA Port of Seattle Terminal 18,Seattle,Washington,United States",
              "timeOfIssue": "2015-05-12 08:37",
              "transportation": "Rail"
            },
            {
              "uuid": "hisCBHU43989077",
              "containerNumber": "CBHU4398907",
              "containerNumberStatus": "Discharged at Last POD",
              "location": "SSA Port of Seattle Terminal 18,Seattle,Washington,United States",
              "timeOfIssue": "2015-05-08 13:55",
              "transportation": " "
            },
            {
              "uuid": "hisCBHU43989076",
              "containerNumber": "CBHU4398907",
              "containerNumberStatus": "Loaded at T/S POL",
              "location": "Shanghai Shengdong (I), Yangshan,Shanghai,Shanghai,China",
              "timeOfIssue": "2015-04-21 08:00",
              "transportation": "Vessel"
            },
            {
              "uuid": "hisCBHU43989075",
              "containerNumber": "CBHU4398907",
              "containerNumberStatus": "Discharged at T/S POD",
              "location": "Shanghai Shengdong (I), Yangshan,Shanghai,Shanghai,China",
              "timeOfIssue": "2015-04-13 20:30",
              "transportation": " "
            },
            {
              "uuid": "hisCBHU43989074",
              "containerNumber": "CBHU4398907",
              "containerNumberStatus": "Loaded at First POL",
              "location": "Tianjin Port Container Terminal Co,Xingang,Tianjin,China",
              "timeOfIssue": "2015-04-08 23:30",
              "transportation": "Vessel"
            },
            {
              "uuid": "hisCBHU43989073",
              "containerNumber": "CBHU4398907",
              "containerNumberStatus": "Gate-In at First POL",
              "location": "Tianjin Port Container Terminal Co,Xingang,Tianjin,China",
              "timeOfIssue": "2015-04-07 11:19",
              "transportation": "Truck"
            },
            {
              "uuid": "hisCBHU43989072",
              "containerNumber": "CBHU4398907",
              "containerNumberStatus": "Cargo Received",
              "location": "Tianjin Port Container Terminal Co,Xingang,Tianjin,China",
              "timeOfIssue": "2015-04-07 11:19",
              "transportation": "Truck"
            },
            {
              "uuid": "hisCBHU43989071",
              "containerNumber": "CBHU4398907",
              "containerNumberStatus": "Empty Equipment Despatched",
              "location": "Tianjin Binhai COSCO Ctn Logistics,Xingang,Tianjin,China",
              "timeOfIssue": "2015-04-03 11:58",
              "transportation": "Truck"
            }
          ]
        }
      ],
      "notFound": ""
    }
  }
}
```
## 3.6 异常示例 ##
```
1.单号错误
{
	"code": 0,
	"message": "success",
	"data": {
		"type": "none",
		"numberType": "none",
		"content": null
	}
}

2.单号为空
{
  "code":0,
  "message":"success"
}
```
## 3.7 错误码解释 ##

1.单号错误 2.单号为空
