# COSCO Syncon Hub API Specification for Reefer Spot Rate

[toc]

## Update Logs

v1.1.0 (2022-07-09)

1. **`U `**  Added isSupportInsurance in response for spot Rate details query interface.[Details](#spot Rate details query interface)

v1.0.0 (2021-08-30)

1. **`A `**  Added the API Reefer spot Rate interface. 
2. **`A `**  Added the API Reefer spot Rate details query interface. 
3. **`A `**  Added the API Reefer Intermodal Service Query Interface. 
4. **`A `**  Added the API Reefer Surcharge Query Interface.


## Http Header

HTTP header information description

| HTTP Header             | Type   | Required | Description             | Remarks |
| ----------------------- | ------ | -------- | ----------------------- | ------- |
| X-Consumer-Forwarder-ID | String | no       | Forwarder company sapId |         |

- X-Consumer-Forwarder-ID

  ```
  # If you want to submit an order/booking for your subsidiary,add this header to the request.The value is sapid of the subsidiary
  
  X-Consumer-Forwarder-ID: 1234567890
  ```



## Spot Rate Query Interface

### 1. Information

* **Path**: /service/synconhub/product/reefer/search
* **Method**: POST

### 2. Request parameters

| Name          |  Type   | Required |                Description                |            **Remarks**            |
| :------------ | :-----: | :------: | :---------------------------------------: | :-------------------------------: |
| cargoCategory | String  |   yes    |               REEFER / NOR                |     REEFER / NOR (non-reefer)     |
| startDate     | ISODate |   yes    | the start date of sailing date scope(GMT) |                                   |
| endDate       | ISODate |    no    |    the end of Sailing date scope(GMT)     |                                   |
| porCityId     | String  |   yes    |                por city id                |                                   |
| fndCityId     | String  |   yes    |                fnd city id                |                                   |
| page          | Integer |   yes    |                 page size                 |   **default:**1，**minimum:**1    |
| size          | Integer |   yes    |                record size                | **default:**20，**range:**[1, 50] |

### 3. Request sample

```javascript
{
  "cargoCategory": "REEFER", // "cargoCategory": "NOR"
  "startDate": "2021-06-01T00:00:00.000Z",
  "endDate": "2021-07-30T00:00:00.000Z",
  "fndCityId": "738872886233842",
  "porCityId": "738872886232873",
  "page": 1,
  "size": 20
}
```

### 4. Response parameters

| Name    | Description                                                  |
| ------- | ------------------------------------------------------------ |
| code    | status code                                                  |
| message | detail message                                               |
| data    | pagination to return product information that meets the query conditions. (sorted by ETD by default) |

### 5. Response sample

```javascript
{
    "code": 0,
    "message": "",
    "data": {
        "content": [
            {
                "id": "8a80cb817321ad030173221c880101d2",
                "cargoCategory": "REEFER",
        		"cargoType": "FROZEN",
                "tradeLaneCode": "AEU",
                "serviceCode": "AEM5",
                "tradeArea": "ETD",
                "vesselName": "COSCO YANTIAN",
                "voyageNo": "020",
                "direction": "W",
                "porFacilityCode": null,
                "porFacilityName": null,
                "porFacilityNameCn": null,
                "fndFacilityName": null,
                "fndFacilityNameCn": null,
                "fndFacilityCode": null,
                "haulageType": "CY-CY",
                "isFmcProduct": false,
                "estimatedTransitTimeInDays": 30,
                "effectiveStartDate": "2021-07-05T00:00:00.000Z", 
                "effectiveEndDate": "2021-12-23T23:59:59.000Z",
                "eta": "2021-09-07 00:00",
                "etd": "2021-08-04 00:00",
                "inventory": 717,
                "polTsMode": null, // pol transit mode
                "podTsMode": null, // pod transit mode
                "porCity": { // por city info，for the specific structure, please refer to 'City information query interface'
                    "id": "738872886233044",
                    "unlocode": "CNSHE",
                    "cityName": "Shekou",
                    "cntyName": "Shenzhen",
                    "stateName": "Guangdong",
                    "stateCode": "GD",
                    "ctryRegionName": "China",
                    "ctryRegionCode": "CN",
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
                    "ctryRegionName": "South Africa",
                    "ctryRegionCode": "ZA",
                    "cityFullNameEn": "Durban",
                    "cityFullNameCn": "德班"
                },
                "polPort": {
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
                        "ctryRegionName": "China",
                        "ctryRegionCode": "CN",
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
                        "ctryRegionName": "South Africa",
                        "ctryRegionCode": "ZA",
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
                        "ctryRegionName": "China",
                        "ctryRegionCode": "CN",
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
                        "ctryRegionName": "South Africa",
                        "ctryRegionCode": "ZA",
                        "cityFullNameEn": "Durban",
                        "cityFullNameCn": "德班"
                    }
                },
                "scheduleData": {
                    "origin": {
                        "id": "738872886233044",
                        "unlocode": "CNSHE",
                        "cityName": "Shekou",
                        "cntyName": "Shenzhen",
                        "stateName": "Guangdong",
                        "stateCode": "GD",
                        "ctryRegionName": "China",
                        "ctryRegionCode": "CN",
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
                        "ctryRegionName": "South Africa",
                        "ctryRegionCode": "ZA",
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
                                "etd": "2021-08-04 00:00",
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
                                "eta": "2021-09-07 00:00",
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
                    "etaAtFnd": "2021-09-07 00:00",
                    "etdAtPor": "2021-08-04 00:00",
                    "serviceCode": "AEM5",
                    "vesselName": "COSCO YANTIAN",
                    "direction": "W",
                    "voyageNo": "020"
                },
                "routeProductPricingList": [
                    {
                        "cntrType": "20RF",
                        "price": 2.00,
                        "tradePrice": 0.50, //tradePrice = price - promotions - discounts
                        "currency": "USD"
                    }
                ],
                //  If ocean charge switch to collect payment, would need add collect payment charge
                "collectExtraCharges": [
                    {
                        "cntrType": "20RF",
                        "price": 20,
                        "currency": "USD"
                    },
                    {
                        "cntrType": "40RQ",
                        "price": 40,
                        "currency": "USD"
                    }
                ],
                "promotions": {
                    "detail": [
                        {
                            "promotionType": "OFF",
                            "remnantInventory": 40,
                            "summary": "订单下箱型20RF、40RQ每UNIT立减1USD。",
                            "priority": 1
                        }
                    ]
                },
                "companyDiscountsInfoDTO": []
            }
        ],
        "number": 1,
        "size": 20,
        "totalPages": 1,
        "totalElements": 1,
        "first": true, // is first page
        "last": true, // is last page
        "empty": false
    }
}
```

### 6. Error sample

```javascript
{
    "code": 20000,
    "message": "cannot identify the user, please contact support for help"
}
```

## Intermodal Service Query Interface


### 1.Information

* **Path**: /service/synconhub/common/intermodalService/reefer/{productId}
* **Method**: GET

### 2. Path parameters

|   Name    | **Type** | Required | Description |
| :-------: | :------: | :------: | :---------: |
| productId |  String  |   yes    | product id  |

### 3. Request sample

```HTTP
GET /service/synconhub/common/intermodalService/reefer/8a5e11157351b2df01735562622a0000
```

### 4. Response parameters

| Name    | Description    |
| ------- | -------------- |
| code    | status code    |
| message | detail message |
| data    | response data  |

### 5. Response sample

```javascript
{
    "code": 0,
    "message": "",
    "data": {
        "loadingServices": [    // loading port list
            {
                "transshipmentModel": "TRUCK+RAIL", // Transport Way eg:['FEEDER','RAIL','TRUCK','BARGE','TRUCK+RAIL']
                "cityUUID": "738872886232847", 
                "cityFullName": "青岛",
                "cityFullNameEn": "Qingdao",
                "ctryCode": "CN",
                "facilityCode": null,
                "facilityName": null,
                "bound": "O", // bound type,loading->O , discharge->I
                "transportTerms": "CY", // Traffic Mode 
                "bargeDay": 3,
                "transitDay": 4,
                "isFollowOceanFee": true, // payment terms follow ocean fee payment terms
                "containerInfoDTOList": [ // Supported cntr type and weight config
                    {
                        "cntrSizeType": "20RF",
                        "cntrPrice": 100,
                        "currencyType": "USD",
                        "paymentTerms": "P",
                        "minWeight": null,
                        "maxWeight": null,
                        "weightUnit": null
                    },
                    {
                        "cntrSizeType": "20RF",
                        "cntrPrice": 150,
                        "currencyType": "USD",
                        "paymentTerms": "C",
                        "minWeight": null,
                        "maxWeight": null,
                        "weightUnit": null
                    },
                    {
                        "cntrSizeType": "40RQ",
                        "cntrPrice": 300,
                        "currencyType": "USD",
                        "paymentTerms": "P",
                        "minWeight": null,
                        "maxWeight": null,
                        "weightUnit": null
                    },
                    {
                        "cntrSizeType": "40RQ",
                        "cntrPrice": 320,
                        "currencyType": "USD",
                        "paymentTerms": "C",
                        "minWeight": null,
                        "maxWeight": null,
                        "weightUnit": null
                    }
                ],
                "intermodalServiceNo": "8a5e112478d41b380178d951babe0032" // intermodal ServiceNo
            },
            {
                "transshipmentModel": "RAIL",
                "cityUUID": "738872886232873",
                "cityFullName": "上海",
                "cityFullNameEn": "Shanghai",
                "ctryCode": "CN",
                "facilityCode": "NGB05",
                "facilityName": "Ningbo Yuandong Terminals Limited",
                "bound": "O",
                "transportTerms": "CY",
                "bargeDay": 2,
                "transitDay": 3,
                "isFollowOceanFee": false, // payment terms follow ocean fee payment terms
                "containerInfoDTOList": [
                    {
                        "cntrSizeType": "20RF",
                        "cntrPrice": 100,
                        "currencyType": "USD",
                        "paymentTerms": "P",
                        "minWeight": null,
                        "maxWeight": null,
                        "weightUnit": null
                    },
                    {
                        "cntrSizeType": "40RQ",
                        "cntrPrice": 200,
                        "currencyType": "USD",
                        "paymentTerms": "P",
                        "minWeight": null,
                        "maxWeight": null,
                        "weightUnit": null
                    },
                    {
                        "cntrSizeType": "40RQ",
                        "cntrPrice": 225,
                        "currencyType": "USD",
                        "paymentTerms": "C",
                        "minWeight": null,
                        "maxWeight": null,
                        "weightUnit": null
                    },
                                        {
                        "cntrSizeType": "20RF",
                        "cntrPrice": 125,
                        "currencyType": "USD",
                        "paymentTerms": "C",
                        "minWeight": null,
                        "maxWeight": null,
                        "weightUnit": null
                    }
                ],
                "intermodalServiceNo": "8a5e112478d41b380178d946dac1001c"
            },
            {
                "transshipmentModel": "TRUCK",
                "cityUUID": "738872886232847",
                "cityFullName": "青岛",
                "cityFullNameEn": "Qingdao",
                "ctryCode": "CN",
                "facilityCode": null,
                "facilityName": null,
                "bound": "O",
                "transportTerms": "CY",
                "bargeDay": 2,
                "transitDay": 4,
                "isFollowOceanFee": false, // payment terms follow ocean fee payment terms
                "containerInfoDTOList": [ 
                    // If cargo weight information is configured,The same cntr type will have multiple data
                    {
                        "cntrSizeType": "20RF",
                        "cntrPrice": 100,
                        "currencyType": "USD",
                        "paymentTerms": "P",
                        "minWeight": 0,  // min cargo weight
                        "maxWeight": 20, // max cargo weight
                        "weightUnit": "TON" // weight unit eg: "TON"
                        // If the weight of the cargo is between 0-20 tons, the loading service fee is 100 USD
                    },
                    {
                        "cntrSizeType": "20RF",
                        "cntrPrice": 150,
                        "currencyType": "USD",
                        "paymentTerms": "P",
                        "minWeight": 20,
                        "maxWeight": 40,
                        "weightUnit": "TON"
                        // If the weight of the cargo is between 20-40 tons, the loading service fee is 150 USD
                    },
                    {
                        "cntrSizeType": "20RF",
                        "cntrPrice": 200,
                        "currencyType": "USD",
                        "paymentTerms": "P",
                        "minWeight": 40,
                        "maxWeight": null,
                        "weightUnit": "TON"
                        // If the weight of the cargo is more than 40 tons, the loading service fee is 100 USD
                    },
                    {
                        "cntrSizeType": "40RQ",
                        "cntrPrice": 300,
                        "currencyType": "USD",
                        "paymentTerms": "P",
                        "minWeight": null,
                        "maxWeight": null,
                        "weightUnit": "TON"
                    },
                    {
                        "cntrSizeType": "40RQ",
                        "cntrPrice": 300,
                        "currencyType": "USD",
                        "paymentTerms": "P",
                        "minWeight": null,
                        "maxWeight": null,
                        "weightUnit": "TON"
                    }
                ],
                "intermodalServiceNo": "8a5e112478d41b380178d9516714002b"
            }
        ],
        "dischargeServices": [  // discharge port list
            {
                "transshipmentModel": "BARGE",
                "cityUUID": "738874496843873",
                "cityFullName": "Gwadar",
                "cityFullNameEn": "Gwadar",
                "ctryCode": "PK",
                "facilityCode": null,
                "facilityName": null,
                "bound": "I",
                "transportTerms": "FOR",
                "bargeDay": 19,
                "transitDay": 5,
                "isFollowOceanFee": false, // payment terms follow ocean fee payment terms
                "containerInfoDTOList": [
                    {
                        "cntrSizeType": "20RF",
                        "cntrPrice": 300,
                        "currencyType": "USD",
                        "paymentTerms": "P",
                        "minWeight": null,
                        "maxWeight": null,
                        "weightUnit": null
                    },
                    {
                        "cntrSizeType": "40RQ",
                        "cntrPrice": 400,
                        "currencyType": "USD",
                        "paymentTerms": "P",
                        "minWeight": null,
                        "maxWeight": null,
                        "weightUnit": null
                    }
                ],
                "intermodalServiceNo": "8a5e112478d41b380178d94e5a4e0026"
            }
        ]
    }
}
```



### 6. Error sample

```javascript
{
    "code": 20002,
    "message": "The product cannot be found"
}
```

```javascript
{
    "code": 20059,
    "message": "Wrong bound type of intermodal service"
}
```

```javascript
{
    "code": 20060,
    "message": "The intermodal service does not exist"
}
```



## Spot Rate Details Query Interface

### 1. Information


* **Path**: /service/synconhub/product/reefer/{productId}
* **Method**: GET

### 2. Path parameters

|   Name    | **Type** | Required | Description |
| :-------: | :------: | :------: | :---------: |
| productId |  String  |   yes    | product id  |

### 3. Query parameters

|        Name        | **Type** | Required |                         Description                          |
| :----------------: | :------: | :------: | :----------------------------------------------------------: |
|  loadingServiceNo  |  String  |    no    | intermodalServiceNo from intermodal service query results. Get the spot rate details with loading service |
| dischargeServiceNo |  String  |    no    | intermodalServiceNo from intermodal service query results. Get the spot rate details with discharge service |


### 4. Request sample

```http
GET /service/synconhub/product/reefer/8a5e11157351b2df01735562622a0000
```

```http
GET /service/synconhub/product/reefer/8a5e11157351b2df01735562622a0000?loadingServiceNo=8a5e112478d41b380178d4551a33000a
```

```http
GET /service/synconhub/product/reefer/8a5e11157351b2df01735562622a0000?loadingServiceNo=8a5e112478d41b380178d4551a33000a&dischargeServiceNo=8a5e112478c4eed60178c51cca9f0001
```



### 5. Response parameters

| Name    | Description         |
| ------- | ------------------- |
| code    | status code         |
| message | detail message      |
| data    | product detail info |

### 6. Response sample

```javascript
{
    "code": 0,
    "message": "",
    "data": {
        "id": "8a5e11157351b2df01735562622a0000",
        "cargoCategory": "REEFER",
        "cargoType": "FROZEN",    
        "tradeLaneCode": "AEU",
        "serviceCode": "AEU2",
        "tradeArea": "ETD",
        "vesselName": "APL VANDA",
        "voyageNo": "022",
        "direction": "W",
        "porFacilityCode": "SHA08",
        "porFacilityName": "Shanghai Shengdong (I), Yangshan",
        "porFacilityNameCn": null,
        "fndFacilityName": "Eurogate Terminal",
        "fndFacilityNameCn": null,
        "fndFacilityCode": "HAM02",
        "haulageType": "CY-CY",
        "isFmcProduct": false,
        "estimatedTransitTimeInDays": 30,
        "effectiveStartDate": "2021-07-05T00:00:00.000Z",
        "effectiveEndDate": "2021-12-23T23:59:59.000Z",
        "eta": "2021-09-16 00:00",
        "etd": "2021-08-12 00:00",
        "inventory": 396,        
        "isSupportInsurance": true,  // Whether to support insurance
        "polTsMode": null, // pol transit mode
        "podTsMode": null, // pod transit mode
        "porCity": {  // por city info，for the specific structure, please refer to 'City information query interface'
            "id": "738872886232873",
            "unlocode": "CNSHA",
            "cityName": "Shanghai",
            "cntyName": "Shanghai",
            "stateName": "Shanghai",
            "stateCode": "SH",
            "ctryRegionName": "China",
            "ctryRegionCode": "CN",
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
            "ctryRegionName": "Germany",
            "ctryRegionCode": "DE",
            "cityFullNameEn": "Hamburg",
            "cityFullNameCn": "汉堡"
        },
        "polPort": {
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
                "ctryRegionName": "China",
                "ctryRegionCode": "CN",
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
                "ctryRegionName": "Germany",
                "ctryRegionCode": "DE",
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
                "ctryRegionName": "China",
                "ctryRegionCode": "CN",
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
                "ctryRegionName": "Germany",
                "ctryRegionCode": "DE",
                "cityFullNameEn": "Hamburg",
                "cityFullNameCn": "汉堡"
            }
        },
        "scheduleData": { 
            "origin": {
                "id": "738872886232873",
                "unlocode": "CNSHA",
                "cityName": "Shanghai",
                "cntyName": "Shanghai",
                "stateName": "Shanghai",
                "stateCode": "SH",
                "ctryRegionName": "China",
                "ctryRegionCode": "CN",
                "cityFullNameEn": "Shanghai",
                "cityFullNameCn": "上海"
            },
            "destination": {
                "id": "738872886254648",
                "unlocode": "DEHAM",
                "cityName": "Hamburg",
                "cntyName": null,
                "stateName": "Hamburg",
                "stateCode": "HH",
                "ctryRegionName": "Germany",
                "ctryRegionCode": "DE",
                "cityFullNameEn": "Hamburg",
                "cityFullNameCn": "汉堡"
            },
            "porFacilityCode": "SHA08", 
            "fndFacilityCode": "HAM02",
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
                        "etd": "2021-08-12 00:00",
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
                        "eta": "2021-09-16 00:00",
                        "ata": null,
                        "facilityCode": "HAM02"
                    },
                    "legSequence": 0,
                    "direction": "W",
                    "transportMode": null
                }
            ],
            "cutOffLocalDate": null,
            "estimatedTransitTimeInDays": 30,
            "etaAtFnd": "2021-09-16 00:00",
            "etdAtPor": "2020-08-12 00:00",
            "serviceCode": "AEU2",
            "vesselName": "APL VANDA",
            "direction": "W",
            "voyageNo": "022"
        },
        "routeProductPricingList": [
          {
            "cntrType": "20RF",
            "price": 44.00,
            "tradePrice": 44.00,
            "currency": "USD"
          },
          {
            "cntrType": "40RQ",
            "price": 88.00,
            "tradePrice": 88.00,
            "currency": "USD"
          }
        ],
       //  If ocean charge switch to collect payment, would need add collect payment charge
        "collectExtraCharges": [
          {
            "cntrType": "40RQ",
            "price": 50,
            "currency": "USD"
          },
          {
            "cntrType": "20RF",
            "price": 50,
            "currency": "USD"
          }
        ],          
        "promotions": {
           "detail": [
            {
              "promotionType": "FULL_OFF",
              "remnantInventory": 93,
              "summary": "箱型20RF、40RQ，满3TEU，订单下箱型20RF、40RQ每TEU立减10USD。",
              "priority": 1
            }
          ]
        },
        "companyDiscountsInfoDTO": [],
        "couponInfos": [
            {
                "couponId": "8a5e11227152917a017152b6049f0002",
                "inventory": 10,
                "displayName": "test",
                "value": 1,
                "currency": "USD",
                "minOceanFee": 1, // minimum use interval of ocean fee
                "maxOceanFee": 10, // maximum use interval of ocean fee
                "maxUseLimit": 2, // use limit
                "usageUnit": "UNIT"
            }
        ],
        "reeferValueAddedServices": [	// There is a corresponding reefer value-added services while cargoCategory is REEFER
          {
            "cntrType": "20RF",
            "type": "CA",
            "price": 10,
            "currency": "USD"
          },
          {
            "cntrType": "20RF",
            "type": "MED",  // medicine
            "price": 13,
            "currency": "USD"
          },
          {
            "cntrType": "20RF",
            "type": "CT",
            "price": 23,
            "currency": "USD"
          },
          {
            "cntrType": "20RF",
            "type": "IOT",
            "price": 23,
            "currency": "USD"
          },
          {
            "cntrType": "20RF",
            "type": "OTHERS",
            "price": 23,
            "currency": "USD"
          }
        ]
    }
}
```

### 7. Error sample

```javascript
{
    "code": 20000,
    "message": "cannot identify the user, please contact support for help"
}
```



## Surcharge Query Interface

Get the surcharge details of a specific product.

### 1. Information


* **Path**: /service/synconhub/common/extraChargeFee/reefer/${productId}
* **Method**: GET

### 2. Path parameters

|   Name    | **Type** | Required | Description |
| :-------: | :------: | :------: | :---------: |
| productId |  String  |   yes    | product id  |

### 3. Query parameters

|        Name        | **Type** | Required |                         Description                          |
| :----------------: | :------: | :------: | :----------------------------------------------------------: |
|  loadingServiceNo  |  String  |    no    | intermodal Service No from intermodal service query results. Get the surcharge details of a specific product with loading service |
| dischargeServiceNo |  String  |    no    | intermodal Service No from intermodal service query results. Get the surcharge details of a specific product with discharge service |

### 4. Request sample

```http
GET /service/synconhub/common/extraChargeFee/reefer/8a80cb817321ad030173221c880101d2
```

```http
GET /service/synconhub/common/extraChargeFee/reefer/8a80cb817321ad030173221c880101d2?loadingServiceNo=8a5e11127467f02501747218e25c0001
```

```http
GET /service/synconhub/common/extraChargeFee/reefer/8a80cb817321ad030173221c880101d2?loadingServiceNo=8a5e11127467f02501747218e25c0001&dischargeServiceNo=8a5e11247866fbec0178681acd3a0008
```

### 5. Response parameters

| Name    | Description    |
| ------- | -------------- |
| code    | status code    |
| message | detail message |
| data    | response data  |

### 6. Response sample

```javascript
{
    "code": 0,
    "message": "",
    "data": [
                {
                    "chargeModel": "BL",
                    "cntrSize": null, // container type
                    "chargeDetail": [ // detail info
                        {
                            "chargeCode": "DOC",
                            "chargeName": "DOC",
                            "chargeType": "DOC",
                            "chargeTag": "POR",
                            "price": 666,
                            "currency": "USD",
                            "paymentTerms": "P",
		                    "isFollowOceanFee": true, // payment terms follow ocean fee payment terms
                            "category": null // charge category
                        }，
                        {
                            "chargeCode": "DOC",
                            "chargeName": "DOC",
                            "chargeType": "DOC",
                            "chargeTag": "POR",
                            "price": 888,
                            "currency": "USD",
                            "paymentTerms": "C",
		                    "isFollowOceanFee": true, // payment terms follow ocean fee payment terms
                            "category": null // charge category
                        }    
                    ]
                },
                {
                    "chargeModel": "CNTR",
                    "cntrSize": "20RF",
                    "chargeDetail": [
                        {
                            "chargeCode": "255",
                            "chargeName": "工本费",
                            "chargeType": "CNTR_LOCAL",
                            "chargeTag": "POR",
                            "price": 10,
                            "currency": "USD",
                            "paymentTerms": "P",
		                    "isFollowOceanFee": false,
                            "category": "EXTRA_CHARGE"
                        }
                    ]
                }
    		]
}
```

### 7. Error sample

```javascript
{
    "code": 20001,
    "message": "cannot proceed the request, please retry later or contact support for help"
}
```



## General Error Codes

 - 20000: "cannot identify the user, please contact support for help"
 - 20001: "The system cannot process the request,please try again or contact support for help"
 - 20002: "The product cannot be found"
 - 20003: "Inventory not enough"
 - 20004: "Out of time"
 - 20005: "Invalid box type"
 - 20006: "Order does not exist"
 - 20007: "Inventory of this product could not be found"
 - 20008: "Promotion inventory in short supply"
 - 20009: "Coupon for this order could not be found"
 - 20010: "The number of coupons available is smaller than the number of coupons available for the order"
 - 20011: "Discount plan inventory shortage or price change, please place a new order"
 - 20012: "Invalid parameter, promotion plan verification failed"
 - 20013: "Insured service does not exist or has expired, please re-order"
 - 20014: "Some charges not allowed to change currency"
 - 20015: "Down payment is not allowed"
 - 20016: "All charges of Instance Booking have to be paid online"
 - 20017: "No currency conversion is allowed for the order fee"
 - 20018: "Only customers in Mainland China are allowed to purchase Instance Booking"
 - 20019: "Wrong order product type"
 - 20020: "Some fees only support CNY or USD"
 - 20021: "Company country/region is different from country/region of POR or FND, purchase is not allowed"
 - 20022: "The down payment ratio must be greater than 0"
 - 20023: "Does not meet the minimum down payment percentage range"
 - 20024: "Does not meet the maximum down payment percentage range"
 - 20025: "The order is missing some necessary cost items"
 - 20026: "The product type of the order can only be I or P"
 - 20027: "The payment method for part of the order is wrong"
 - 20028: "Some cost items are missing to prepaid attributes"
 - 20029: "Online collect payment not support partial payment for now"
 - 20030: "There are invalid charges, please place a new order"
 - 20031: "Coupon overuse"
 - 20032: "System charge item pre-paid configuration error, please contact customer service for processing"
 - 20033: "Due to the system configuration, some fees are not allowed to be paid in USD or to switch to pre-paid, please contact the customer service for processing"
 - 20034: "Dear user, this product is no longer available, please contact customer service"
 - 20035: "Online payment cannot include both prepaid and collect-paid, please contact customer service for processing"
 - 20036: "The quantity of BL should be greater that 1"
 - 20037: "Unable to purchase this product for now, please contact customer service for processing"
 - 20038: "Incorrect product information, please contact customer service for processing"
 - 20039: "This payment method is not currently supported, please contact customer service for processing"
 - 20040: "The page size must be greater than 0"
 - 20041: "The page size should be between 0 and 30."
 - 20042: "Parameter verification error"
 - 20043: "Cross Booking invalid inventory"
 - 20044: "Cross Booking wrong shipping date information"
 - 20045: "Cross Booking insufficient stock"
 - 20046: "Cross Booking cargo container not found"
 - 20047: "This cargo nature type is not supported at the moment, please contact customer service"
 - 20048:"Failed to get enterprise information"
 - 20049:''Order query only supports data query within two months"
 - 20051:"Sorry for system busy, please try again later"
 - 20053:"CrossBooking schedule information LTD is wrong"
 - 20054: "Address cannot be empty"
 - 20055: "Sub-company carrier customer code out of scope."
 - 20056: "The TEU purchase limit for the week in which the product is sold is insufficient"
 - 20057: "CrossBooking contract number does not exist"
 - 20058: "CrossBooking start week cannot be earlier than the current four weeks"
 - 20059: "API booking cannot purchase FMC products"
 - 20060: "Permission denied"
 - 20061: "CosPlus | product booking must fill in all shipping related party information"
 - 20062: "Wrong import and export type of intermodal service"
 - 20063: "The intermodal service does not exist"
 - 20064: "Can't find the city"
 - 20066: "Some container types do not support intermodal service"
 - 20067: "The intermodal service fee is charged by weight, and the estimated cargo weight of the box type is required"
 - 20068: "The estimated weight of the container is not in the optional range, please re-order"
 - 20070: "CosPlus | Shipper or consignee of shipping related party filled in the product booking is illegal."
 - 20072: "API cannot purchase this type of product"
 - 20073: "Charge Type:xxx,ChargeName:xxx cannot be set to Collect"
 - 20074: "The long-term product does not exist"
 - 20075: "The long-term product is off-shelf"
 - 20076: "Insufficient amount of available containers"
 - 20077: "It is not the time allowed for booking"
 - 20078: "Ocean charge cannot do prepaid-collect switch"
 - 20079: "The reefer value-added service does not exist or has expired"
 - 20080: "The reefer value-added service type is incorrect"
 - 20081: "The value of a single box of goods is more than US $500,000, please buy offline, the online process is not supported"
 - 20082: "When MED are selected for reefer value-added services, the cargoValue is required"
 - 20083: "You cannot choose more than one cold box value-added service other than IOT"
 - 20084: "cargoInfo.reeferGenSetType cannot be empty"
 - 20085: "cargoInfo.reeferTemperatureType and cargoInfo.reeferTemperatureValue  cannot be empty"
 - 20086: "Ventilation Settings cannot be added when the reefer temperature is less than 0 C or 32 F"
 - 20087: "Ventilation setting must be added when the reefer temperature is ≥0°C or ≥32°F"

