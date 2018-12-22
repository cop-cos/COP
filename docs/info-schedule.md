# 船期查询 #

船期查询是航运物流领域最为常用的功能，为各类客户提供集装箱班轮的班期信息。


# 1. 根据起运/目的地查询 #

## 1.1 地址和方法 ## 

* **相对URL**："service/info/schedule"

* **方法**：POST

## 1.2 请求参数 ##
参数名 | 示例 | 类型 | 含义 
-----|-----|-----|-----
pickup | B | string | 起运地运输条款，B：BOTH，C：CY，D：DOOR 
destinationCity | Dallas,Dallas,TX,United States,USDAL | string | 目的地
estimateDate | D | string | 最早起运时间 ：D; 最晚到达时间：A
originCityUuid | 738872886232847 | string | 起运地uuid 
delivery | B | string | 目的地运输条款，B：BOTH，C：CY，D：DOOR 
fromDate | 2018-12-20 | string | 查询条件（时间区间）的头
toDate | 2018-12-20 | string | 查询条件（时间区间）的尾
dateLength | 4 | int | 查询条件（时间区间）的长度，以周为单位
destinationCityUuid | 738872886241986 | string | 目的地uuid
originCity | Qingdao,Qingdao,Shandong,China,CNQIN | string | 起运地

## 1.3 请求样例 ##
```
parameters:

{
	"pickup": "B",
	"destinationCity": "Dallas,Dallas,TX,United States,USDAL",
	"estimateDate": "D",
	"originCityUuid": "738872886232847",
	"delivery": "B",
	"fromDate": "2018-12-20",
	"dateLength": "4",
	"destinationCityUuid": "738872886241986",
	"originCity": "Qingdao,Qingdao,Shandong,China,CNQIN"
}
```

## 1.4 响应参数 ##

字段名 |含义
-----|-----
conditions | 查询条件
shortestTransitTime |最短在途天数
data | 船期信息

## 1.5 响应样例 ##
```
{
	"code": 0,
	"message": "success",
	"data": {
		"content": {
			"conditions": {
				"dateLength": 4, //int, 查询条件（时间区间）的长度，以周为单位
				"delivery": "B", //string, 目的地运输条款，B：BOTH，C：CY，D：DOOR 
				"destinationCity": "Dallas,Dallas,TX,United States,USDAL", //string, 目的地
				"destinationCityUuid": "738872886241986", //string, 目的地uuid
				"estimateDate": "D", //string, 最早起运时间 ：D; 最晚到达时间：A
				"fromDate": "2018-12-20", //string, 查询条件（时间区间）的头
				"originCity": "Qingdao,Qingdao,Shandong,China,CNQIN", //string, 起运地
				"originCityUuid": "738872886232847", //string,起运地uuid 
				"pickup": "B", //string, 起运地运输条款，B：BOTH，C：CY，D：DOOR 
				"time": "1545280678197", //string, 时间戳
				"toDate": "2019-01-16" //string, 查询条件（时间区间）的尾
			},
			"shortestTransitTime": "27", //最短在途天数
			"data": [{
				"id": "1", //string, 船期id
				"firstPol": "Qingdao", //string,起运港口
				"lastPod": "Houston", //string, 卸货港口
				"transitTime": "37", //string, 在途天数
				"tdWeekDay": 5, //string, 起运港离港日期，int，0~6表示 周日~周六
				"schedules": [{
					"id": "1", //string, 船期id
					"service": "AEU3", //string, 航线
					"vessel": "COSCO SHIPPING CAPRICORN", //string, 船名
					"vesselCode": "CNG", //string, 船名代码
					"voyage": "003", //string, 航次
					"dir": "W", //string, 航向
					"extVoyage": "003W", //string, 外部航次号。优先显示，若此字段为空，显示 `${voyage}${dir}`
					"newExtVoyage": null, //string, 
					"cutOff": "2018-12-18 12:00", //string, 载重时间
					"pol": "Qingdao", //string, 装港
					"etd": "2018-12-21", //string, 预计离港时间
					"atd": "", //string, 实际离港时间
					"pod": "Ningbo", //string, 卸港
					"eta": "2018-12-24", //string, 预计到港时间
					"ata": "", //string, 实际到港时间
					"available": "", //string, 可提货时间
					"transitTime": "37", //string, 在途时间
					"revFacilityCode": "TAO06", //string, 接货地 堆场代码（装货港 港口代码）
					"deliFacilityCode": "DAL31", //string, 目的地 堆场代码 （卸货港 港口代码）
					"polFacilityCode": null, 
					"podFacilityCode": "NGB05", 
					"rowNum": 0, 
					"vgmCutoffDt": null, 
					"siCutoff": null, 
					"r24Cutoff": null, 
					"deList1": [], 
					"deList2": [] 
				}]
			}]
		}
	}
}
```
## 1.6 异常示例 ##

没有查询结果
```
{
    "code":0,
    "message":"success",
    "data":{
        "content":{
            "conditions":{
                "dateLength":4,
                "delivery":"B",
                "destinationCity":"Shanghai,Shanghai,Shanghai,China,CNSHA",
                "destinationCityUuid":"738872886232873",
                "estimateDate":"D",
                "fromDate":"2018-12-22",
                "originCity":"Shanghai,Shanghai,Shanghai,China,CNSHA",
                "originCityUuid":"738872886232873",
                "pickup":"B",
                "time":"1545488743149",
                "toDate":"2019-01-18"
            }
        }
    }
}
```
## 1.7 错误码解释 ##
错误码 | 解释
-----|-----
5400|参数异常
5415|移动设备信息保存失败
5500|异常
