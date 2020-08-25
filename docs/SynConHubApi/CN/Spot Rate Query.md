# Spot Rate Query



# 1. Spot Rate Query

## 1.1 地址和方法



* **路径**："/service/synconhub/product/instantBooking/search"
* **方法**：POST

## 1.2 查询参数

| 字段名         |  类型   | 是否必须 |     说明     |                             备注                             |
| :------------- | :-----: | :------: | :----------: | :----------------------------------------------------------: |
| startDate      | string  |   必须   | 预定出发时间 |                                                              |
| endDate        | string  |  非必须  | 预计抵达时间 |                           ISODate                            |
| porCityId      | string  |   必须   | 起始地CityId |                                                              |
| fndCityId      | string  |   必须   | 目的地CityId |                           ISODate                            |
| page           | integer |   必须   |     页数     |                        **最小值:** 1                         |
| size           | integer |   必须   |     条数     |                **最小值:** 1，**最大值:** 50                 |
| sortedCriteria | object  |  非必须  |   排序条件   |                                                              |
| \|---direction | string  |  非必须  |   排序方向   |                **枚举:** ascending,descending                |
| \|---name      | string  |  非必须  | 排序字段名称 | **枚举:** porCityName,etd,eta,fndCityName,transitTime,20GP,40GP,40HQ |
| \|---type      | string  |  非必须  | 产品排序类型 |            **枚举:** MAIN_SERVICE_TIME,CNTR_PRICE            |
| tradeGroupCode | string  |  非必须  |   贸易区组   |              贸易区组id，不填默认搜索所有贸易区              |

## 1.3 请求样例

```java
{
  "startDate": "2020-06-01T00:00:00.000Z",
  "endDate": "2020-07-30T00:00:00.000Z",
  "fndCityId": 738872886233842,
  "porCityId": 738872886232873,
  "page": 1,
  "size": 20,
  "sortedCriteria": {
    "direction": "ascending",
    "name": "etd",
    "type": "MAIN_SERVICE_TIME"
  },
  "tradeGroupCode": "7ffffffff86c4c568cf07fffffff15ee"
}
```

## 1.4 响应参数

| 字段名  | 含义                           |
| ------- | ------------------------------ |
| code    | 状态码                         |
| message | 提示信息                       |
| data    | 分页返回符合查询条件的产品信息 |

## 1.5 响应字段说明

```javascript
{
    "code": 0,
    "message": "",
    "data": {
        "content": [
            {
                "id": "8a80cb817321ad030173221c880101d2",
                "tradeLaneCode": "AEU", // 航线组
                "serviceCode": "AEM5", // 主航线
                "tradeGroup": "7ffffffff43d4e1292877fffffff92b2", // 贸易区组
                "tradeArea": "ETD", // 贸易区
                "areaCode": "e4074b7a91335ac5bf9753894028fdef", // 地区代码
                "regionCode": "7fffffffb8834976a3e162962a93bbf8", // 区域代码
                "vesselName": "COSCO YANTIAN", //船名
                "voyageNo": "020", // 航次
                "direction": "W", // 航向
                "porFacilityCode": null, // 起始地码头代码
                "porFacilityName": null, // 起始地码头
                "porFacilityNameCn": null, // 起始地码头中文名
                "fndFacilityName": null, // 目的地码头
                "fndFacilityNameCn": null, // 目的地码头中文名
                "fndFacilityCode": null, // 目的地码头代码
                "haulageType": "CY-CY", // 运输条款
                "estimatedTransitTimeInDays": 30, // 在途天数
                "effectiveStartDate": "2020-07-05T00:00:00.000Z", // 有效期从
                "effectiveEndDate": "2020-12-23T23:59:59.000Z", // 有效期至
                "eta": "2020-09-07 00:00", // 预计抵达时间
                "etd": "2020-08-04 00:00", // 预定出发时间
                "inventory": 717, // 产品库存
                "commodityCode": null, // 运价货品名
                "porTransferGroupName": null,
                "fndTransferGroupUuid": null,
                "polTsMode": null, // 装港转运模式
                "podTsMode": null, // 卸港转运模式
                "porCity": { // 起始地城市信息，参考《根据关键字查询城市信息》中的字段说明
                    "id": "738872886233044",
                    "unlocode": "CNSHE",
                    "cityName": "Shekou",
                    "cntyName": "Shenzhen",
                    "stateName": "Guangdong",
                    "stateCode": "GD",
                    "ctryName": "China",
                    "ctryCode": "CN",
                    "cityFullNameEn": "Shekou",
                    "cityFullNameCn": "蛇口"
                },
                "fndCity": {
                    "id": "738872886271836",
                    "unlocode": "ZADUR",
                    "cityName": "Durban",
                    "cntyName": null,
                    "stateName": "KwaZulu-Natal",
                    "stateCode": null,
                    "ctryName": "South Africa",
                    "ctryCode": "ZA",
                    "cityFullNameEn": "Durban",
                    "cityFullNameCn": "德班"
                },
                "polPort": { // 主航线装港
                    "id": "349645770723389",
                    "portName": "Shekou", // 港口名称
                    "portCode": "SHK", // 港口代码
                    "portFullNameEn": "Shekou", // 港口英文全称
                    "portFullNameCn": "蛇口", // 港口中文全称
                    "city": { // 关联城市
                        "id": "738872886233044",
                        "unlocode": "CNSHE",
                        "cityName": "Shekou",
                        "cntyName": "Shenzhen",
                        "stateName": "Guangdong",
                        "stateCode": "GD",
                        "ctryName": "China",
                        "ctryCode": "CN",
                        "cityFullNameEn": "Shekou",
                        "cityFullNameCn": "蛇口"
                    }
                },
                "podPort": {
                    "id": "349647918206992",
                    "portName": "Durban",
                    "portCode": "DUR",
                    "portFullNameEn": "Durban",
                    "portFullNameCn": "Durban",
                    "city": {
                        "id": "738872886271836",
                        "unlocode": "ZADUR",
                        "cityName": "Durban",
                        "cntyName": null,
                        "stateName": "KwaZulu-Natal",
                        "stateCode": null,
                        "ctryName": "South Africa",
                        "ctryCode": "ZA",
                        "cityFullNameEn": "Durban",
                        "cityFullNameCn": "德班"
                    }
                },
                "firstPolPort": {
                    "id": "349645770723389",
                    "portName": "Shekou",
                    "portCode": "SHK",
                    "portFullNameEn": "Shekou",
                    "portFullNameCn": "蛇口",
                    "city": {
                        "id": "738872886233044",
                        "unlocode": "CNSHE",
                        "cityName": "Shekou",
                        "cntyName": "Shenzhen",
                        "stateName": "Guangdong",
                        "stateCode": "GD",
                        "ctryName": "China",
                        "ctryCode": "CN",
                        "cityFullNameEn": "Shekou",
                        "cityFullNameCn": "蛇口"
                    }
                },
                "lastPodPort": {
                    "id": "349647918206992",
                    "portName": "Durban",
                    "portCode": "DUR",
                    "portFullNameEn": "Durban",
                    "portFullNameCn": "Durban",
                    "city": {
                        "id": "738872886271836",
                        "unlocode": "ZADUR",
                        "cityName": "Durban",
                        "cntyName": null,
                        "stateName": "KwaZulu-Natal",
                        "stateCode": null,
                        "ctryName": "South Africa",
                        "ctryCode": "ZA",
                        "cityFullNameEn": "Durban",
                        "cityFullNameCn": "德班"
                    }
                },
                "scheduleData": { // 航线时间表
                    "origin": {
                        "id": "738872886233044",
                        "unlocode": "CNSHE",
                        "cityName": "Shekou",
                        "cntyName": "Shenzhen",
                        "stateName": "Guangdong",
                        "stateCode": "GD",
                        "ctryName": "China",
                        "ctryCode": "CN",
                        "cityFullNameEn": "Shekou",
                        "cityFullNameCn": "蛇口"
                    },
                    "destination": {
                        "id": "738872886271836",
                        "unlocode": "ZADUR",
                        "cityName": "Durban",
                        "cntyName": null,
                        "stateName": "KwaZulu-Natal",
                        "stateCode": null,
                        "ctryName": "South Africa",
                        "ctryCode": "ZA",
                        "cityFullNameEn": "Durban",
                        "cityFullNameCn": "德班"
                    },
                    "porFacilityCode": null,
                    "fndFacilityCode": null,
                    "legs": [
                        {
                            "carrier": null,
                            "service": {
                                "serviceCode": "AEM5",
                                "serviceId": null
                            },
                            "vessel": {
                                "vesselName": "COSCO YANTIAN",
                                "vesselId": null,
                                "vesselCode": null
                            },
                            "voyageCode": null,
                            "internalVoyageNumber": "020",
                            "externalVoyageNumber": "020W",
                            "pol": {
                                "port": {
                                    "id": "349645770723389",
                                    "portName": "Shekou",
                                    "portCode": "SHK",
                                    "portFullNameEn": "Shekou",
                                    "portFullNameCn": "蛇口",
                                    "city": null
                                },
                                "etd": "2020-08-04 00:00",
                                "atd": null,
                                "eta": null,
                                "ata": null,
                                "facilityCode": null
                            },
                            "pod": {
                                "port": {
                                    "id": "349647918206992",
                                    "portName": "Durban",
                                    "portCode": "DUR",
                                    "portFullNameEn": "Durban",
                                    "portFullNameCn": "Durban",
                                    "city": null
                                },
                                "etd": null,
                                "atd": null,
                                "eta": "2020-09-07 00:00",
                                "ata": null,
                                "facilityCode": null
                            },
                            "legSequence": 0,
                            "direction": "W",
                            "transportMode": null
                        }
                    ],
                    "cutOffLocalDate": null,
                    "estimatedTransitTimeInDays": 30,
                    "etaAtFnd": "2020-09-07 00:00",
                    "etdAtPor": "2020-08-04 00:00",
                    "serviceCode": "AEM5",
                    "vesselName": "COSCO YANTIAN",
                    "direction": "W",
                    "voyageNo": "020"
                },
                "routeProductPricingList": [ // 产品运价列表
                    {
                        "id": "8a80cb817321ad030173221c880201d7",
                        "cntrType": "20GP",
                        "price": 2.00,
                        "currency": "USD",
                        "sailingProductPricingDetailList": [
                            {
                                "id": "8a80cb817321ad030173221c880201d8",
                                "chargeType": "OCEAN_FEE",
                                "price": 1,
                                "currency": "USD"
                            }
                        ]
                    }
                ],
                "promotions": { // 产品特惠信息
                    "detail": [
                        {
                            "promotionType": "OFF",
                            "remnantInventory": 40,
                            "summary": "订单下箱型20GP、40GP、40HQ每UNIT立减1USD。",
                            "priority": 1,
                            "currency": "USD",
                            "fullAmount": null,
                            "fullUnit": "UNIT",
                            "items": [
                                {
                                    "containerTypes": [
                                        "20GP",
                                        "40GP",
                                        "40HQ"
                                    ],
                                    "amount": 1
                                }
                            ]
                        }
                    ]
                },
                "couponInfos": null, // 产品优惠券信息(只有在搜索产品详细信息时才会有值)
                "insuredRule": null, // 产品保价服务信息(只有在搜索产品详细信息时才会有值)
                "companyDiscountsInfoDTO": [ // 企业等级优惠信息
                    {
                        "channelCntr": "40GP",
                        "amount": 400,
                        "currencyType": "USD",
                        "source": "CUSTOM"
                    }
                ]
            }
        ],
        "totalElements": 7,// 总条数
        "totalPages": 7, // 总页数
        "last": false, // 当前是否为最后一页
        "size": 1,
    }
}
```

## 1.6 异常示例

```javascript
{
    "code": "20000",
    "message": "无法识别用户，请联系客服"
}
```


# 2. Spot Rate详情查询

## 2.1 地址和方法


* **路径**："/service/synconhub/product/instantBooking/{productId}"
* **方法**: GET

## 2.2 路径参数

|  参数名   |               示例               |         说明         |
| :-------: | :------------------------------: | :------------------: |
| productId | 8a5e11157351b2df01735562622a0000 | 平台即刻订舱产品的id |

## 2.3 请求样例

```java
GET /service/synconhub/product/instantBooking/8a5e11157351b2df01735562622a0000
```

## 2.4 响应参数

| 字段名  | 含义                   |
| ------- | ---------------------- |
| code    | 状态码                 |
| message | 提示信息               |
| data    | 即刻订舱产品的详细信息 |

## 2.5 响应字段说明

```javascript
{
    "code": 0,
    "message": "",
    "data": {
        "id": "8a5e11157351b2df01735562622a0000", // 产品id
        "tradeLaneCode": "AEU", // 航线组
        "serviceCode": "AEU2", // 主航线
        "tradeGroup": "7ffffffff43d4e1292877fffffff92b2", // 贸易区组
        "tradeArea": "ETD", // 贸易区
        "areaCode": "77c42f4af2d75052acee0c0aa87cbc83", // 地区代码
        "regionCode": "7fffffffea4b4571acb27fffffff7bc9",  // 区域代码
        "vesselName": "APL VANDA", // 船名
        "voyageNo": "022", // 航次
        "direction": "W", // 航向
        "porFacilityCode": "SHA08", // 起始地码头代码
        "porFacilityName": "Shanghai Shengdong (I), Yangshan", // 起始地码头
        "porFacilityNameCn": null, //起始地码头中文名 
        "fndFacilityName": "Eurogate Terminal", // 目的地码头
        "fndFacilityNameCn": null, // 目的地码头中文名
        "fndFacilityCode": "HAM02", // 目的地码头代码
        "haulageType": "CY-CY", // 运输条款
        "estimatedTransitTimeInDays": 30, // 在途天数
        "effectiveStartDate": "2020-07-05T00:00:00.000Z", // 上架时间
        "effectiveEndDate": "2020-12-23T23:59:59.000Z", // 下架时间
        "eta": "2020-09-16 00:00", // 有效期从
        "etd": "2020-08-12 00:00", // 有效期至
        "inventory": 396, // 产品库存
        "commodityCode": "海绵", // 运价货品名
        "channelCode": "GENERAL",
        "porTransferGroupName": null,
        "fndTransferGroupUuid": null,
        "polTsMode": null, // 装港转运模式
        "podTsMode": null, // 卸港转运模式
        "porCity": { // 起始地城市信息，参考《根据关键字查询城市信息》中的字段说明
            "id": "738872886232873",
            "unlocode": "CNSHA",
            "cityName": "Shanghai",
            "cntyName": "Shanghai",
            "stateName": "Shanghai",
            "stateCode": "SH",
            "ctryName": "China",
            "ctryCode": "CN",
            "cityFullNameEn": "Shanghai",
            "cityFullNameCn": "上海"
        },
        "fndCity": {
            "id": "738872886254648",
            "unlocode": "DEHAM",
            "cityName": "Hamburg",
            "cntyName": null,
            "stateName": "Hamburg",
            "stateCode": "HH",
            "ctryName": "Germany",
            "ctryCode": "DE",
            "cityFullNameEn": "Hamburg",
            "cityFullNameCn": "汉堡"
        },
        "polPort": { // 主航线装港
            "id": "349657045012458",
            "portName": "Shanghai",// 港口名称
            "portCode": "SHA", // 港口代码
            "portFullNameEn": "Shanghai", // 港口英文全称
            "portFullNameCn": "上海", // 港口中文全称
            "city": { // 关联城市信息
                "id": "738872886232873",
                "unlocode": "CNSHA",
                "cityName": "Shanghai",
                "cntyName": "Shanghai",
                "stateName": "Shanghai",
                "stateCode": "SH",
                "ctryName": "China",
                "ctryCode": "CN",
                "cityFullNameEn": "Shanghai",
                "cityFullNameCn": "上海"
            }
        },
        "podPort": {
            "id": "349645770723600",
            "portName": "Hamburg",
            "portCode": "HAM",
            "portFullNameEn": "Hamburg",
            "portFullNameCn": "Hamburg",
            "city": {
                "id": "738872886254648",
                "unlocode": "DEHAM",
                "cityName": "Hamburg",
                "cntyName": null,
                "stateName": "Hamburg",
                "stateCode": "HH",
                "ctryName": "Germany",
                "ctryCode": "DE",
                "cityFullNameEn": "Hamburg",
                "cityFullNameCn": "汉堡"
            }
        },
        "firstPolPort": {
            "id": "349657045012458",
            "portName": "Shanghai",
            "portCode": "SHA",
            "portFullNameEn": "Shanghai",
            "portFullNameCn": "上海",
            "city": {
                "id": "738872886232873",
                "unlocode": "CNSHA",
                "cityName": "Shanghai",
                "cntyName": "Shanghai",
                "stateName": "Shanghai",
                "stateCode": "SH",
                "ctryName": "China",
                "ctryCode": "CN",
                "cityFullNameEn": "Shanghai",
                "cityFullNameCn": "上海"
            }
        },
        "lastPodPort": {
            "id": "349645770723600",
            "portName": "Hamburg",
            "portCode": "HAM",
            "portFullNameEn": "Hamburg",
            "portFullNameCn": "Hamburg",
            "city": {
                "id": "738872886254648",
                "unlocode": "DEHAM",
                "cityName": "Hamburg",
                "cntyName": null,
                "stateName": "Hamburg",
                "stateCode": "HH",
                "ctryName": "Germany",
                "ctryCode": "DE",
                "cityFullNameEn": "Hamburg",
                "cityFullNameCn": "汉堡"
            }
        },
        "scheduleData": { // 航线时间表
            "origin": { // 起始地城市
                "id": "738872886232873",
                "unlocode": "CNSHA",
                "cityName": "Shanghai",
                "cntyName": "Shanghai",
                "stateName": "Shanghai",
                "stateCode": "SH",
                "ctryName": "China",
                "ctryCode": "CN",
                "cityFullNameEn": "Shanghai",
                "cityFullNameCn": "上海"
            },
            "destination": { // 目的地城市
                "id": "738872886254648",
                "unlocode": "DEHAM",
                "cityName": "Hamburg",
                "cntyName": null,
                "stateName": "Hamburg",
                "stateCode": "HH",
                "ctryName": "Germany",
                "ctryCode": "DE",
                "cityFullNameEn": "Hamburg",
                "cityFullNameCn": "汉堡"
            },
            "porFacilityCode": "SHA08", // 起始地码头
            "fndFacilityCode": "HAM02", // 目的地码头
            "legs": [
                {
                    "carrier": null,
                    "service": {
                        "serviceCode": "AEU2",
                        "serviceId": null
                    },
                    "vessel": {
                        "vesselName": "APL VANDA",
                        "vesselId": null,
                        "vesselCode": null
                    },
                    "voyageCode": null,
                    "internalVoyageNumber": "022",
                    "externalVoyageNumber": "022W",
                    "pol": {
                        "port": {
                            "id": "349657045012458",
                            "portName": "Shanghai",
                            "portCode": "SHA",
                            "portFullNameEn": "Shanghai",
                            "portFullNameCn": "上海",
                            "city": null
                        },
                        "etd": "2020-08-12 00:00",
                        "atd": null,
                        "eta": null,
                        "ata": null,
                        "facilityCode": "SHA08"
                    },
                    "pod": {
                        "port": {
                            "id": "349645770723600",
                            "portName": "Hamburg",
                            "portCode": "HAM",
                            "portFullNameEn": "Hamburg",
                            "portFullNameCn": "Hamburg",
                            "city": null
                        },
                        "etd": null,
                        "atd": null,
                        "eta": "2020-09-16 00:00",
                        "ata": null,
                        "facilityCode": "HAM02"
                    },
                    "legSequence": 0,
                    "direction": "W",
                    "transportMode": null
                }
            ],
            "cutOffLocalDate": null,
            "estimatedTransitTimeInDays": 30, // 在途天数
            "etaAtFnd": "2020-09-16 00:00",
            "etdAtPor": "2020-08-12 00:00",
            "serviceCode": "AEU2",
            "vesselName": "APL VANDA",
            "direction": "W",
            "voyageNo": "022"
        },
        "routeProductPricingList": [ // 产品运价列表
            {
                "id": "8a5e11157351b2df0173556262980003",
                "cntrType": "20GP",// 箱型
                "price": 100.00, // 价格
                "currency": "USD", // 币种
                "sailingProductPricingDetailList": [ // 详情
                    {
                        "id": "8a5e11157351b2df0173556262980004",
                        "chargeType": "OCEAN_FEE", // 海运费
                        "price": 100,// 价格
                        "currency": "USD"// 币种
                    }
                ]
            }
        ],
        "promotions": { // 产品特惠信息
            "detail": [
                {
                    "promotionType": "OFF", // 特惠类型
                    "remnantInventory": 51, // 剩余库存
                    "summary": "订单下箱型20GP、40GP、40HQ每UNIT立减1USD。", // 描述
                    "priority": 1, // 特惠使用优先级
                    "currency": "USD", // 币种
                    "fullAmount": 0, // 满减/折条件的数量要求
                    "fullUnit": "UNIT", // 满减/折条件的数量单位
                    "items": [ // 具体箱型的优惠力度
                        {
                            "containerTypes": [ // 支持的箱型列表
                                "20GP",
                                "40GP",
                                "40HQ"
                            ],
                            "amount": 1 // 特惠金额/折扣
                        }
                    ]
                }
            ]
        },
        "couponInfos": [ // 产品优惠券信息(只有在搜索产品详细信息时才会有值)
            {
                "couponId": "8a5e11227152917a017152b6049f0002", // 优惠券ID
                "inventory": 10, // 优惠券库存
                "displayName": "test", // 优惠券显示名称
                "value": 10, // 优惠券金额
                "currency": "USD", // 币种
                "minOceanFee": null, // 海运费最小使用区间
                "maxOceanFee": null, // 海运费最大使用区间
                "maxUseLimit": 100, // 使用上限
                "usageUnit": "UNIT" // 使用单位
            }
        ],
        "insuredRule": { // 产品保价服务信息(只有在搜索产品详细信息时才会有值)
            "id": "8a5eaf9c6d4dddd7016d4de242c10000",
            "remarks": "保价测试", // 备注
            "tradeAreaCode": "ETD", // 贸易区代码
            "tradeLaneCodes": [ // 航线组代码
                "AEU"
            ],
            "insuredRuleChargeDetails": [ // 详细信息
                {
                    "cntrType": "20GP", // 箱型
                    "price": 10, // 价格
                    "currency": "USD" // 币种
                }
            ]
        },
        "companyDiscountsInfoDTO": [ // 企业等级优惠信息
            {
                "channelCntr": "20GP",// 箱型
                "amount": 20, // 优惠金额
                "currencyType": "USD",// 币种
            }
        ]
    }
}
```

## 2.6 异常示例

```javascript
{
    "code": "20000",
    "message": "无法识别用户，请联系客服"
}
```

