# COSCO Syncon Hub API Specification for Spot Rate

[toc]

## Update Logs

v1.5.0 (2022-07-09)

1. **`U `**  Added isSupportInsurance in response for Spot Rate Query Interface.[Details](#Spot Rate Query Interface)


v1.4.0 (2022-03-10)

1. **`A `**  Added the API - Trailer Doors Query Interface. [Details](#Trailer Doors Query Interface)
2. **`A `**  Added the API - Trailer Rates Query Interface. [Details](#Trailer Rates Query Interface)
3. **`U`**  Added POR and FND trailer areas for Spot Rate details query interface. [Details](#Spot Rate Details Query Interface)

v1.3.0 (2021-08-14)

1. **`A `**  Added general error codes description.  [Details](#General Error Codes)

v1.2.0 (2021-04-28)

1. **`A `**  Added the API Intermodal Service Query to search product supported transfer services.  [Details](#Intermodal Service Query Interface)
2. **`U`** Updated the spot rate detail query support search product detail with intermodal services. [Details](#Spot Rate Details Query Interface)
3. **`U`** Updated the surcharge query support search product surcharge with intermodal services. [Details](#Surcharge Query Interface)




## HTTP Header

HTTP header information description

| HTTP Header             | Type   | Required | Description             | Remarks |
| ----------------------- | ------ | -------- | ----------------------- | ------- |
| X-Consumer-Forwarder-ID | String | no       | Forwarder company sapId |         |

- X-Consumer-Forwarder-ID

  ```
  # If you want to submit an order/booking for your subsidiary,add this header to the request.The value is sapid of the subsidiary
  
  X-Consumer-Forwarder-ID: 1234567890
  ```



## City Information Query Interface

Fuzzy query of the city information maintained by the platform based on the keywords of the city name. The returned city id can be used as the origin / destination location id for the Spot Rate query interface.

### 1. Information

* **Path**: /service/synconhub/common/city/search
* **Method**: POST

### 2. Request parameters

| Name     | **Type** | **Required** |    Description     |                  **Remarks**                  |
| :------- | :------: | :----------: | :----------------: | :-------------------------------------------: |
| keywords |  String  |     yes      | city name keywords |                                               |
| page     | Integer  |      no      | page number        |       **default:**1，**minimum:**1          |
| size     | Integer  |      no      | page size          | **default:**30，**minimum:**1，**maximum:**30 |

### 3. Request sample

```javascript
{
    "keywords": "shenzhen",
    "page": 1,
    "size": 30
}
```

### 4. Response parameters

| Name    | Description                                |
| ------- | ------------------------------------------ |
| code    | status code                                |
| message | detail message                             |
| data    | Paging query results (sorted by city name) |

### 5. Response sample

```javascript
{
    "code": 0,
    "message": "",
    "data": {
        "content": [
            {
                "id": "738872886233057", // city id
                "unlocode": "CNSZN", // port code
                "cityName": "Shenzhen",
                "cntyName": "Shenzhen", // county name
                "stateName": "Guangdong",
                "stateCode": "GD",
                "ctryRegionName": "China", // country / region name
                "ctryRegionCode": "CN", // country / region code
                "cityFullNameEn": "Shenzhen",
                "cityFullNameCn": "深圳"
            }
        ],
        "number": 1,
        "size": 30,
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
    "code": 20041,
    "message": "the page size should be between 0 and 30."
}
```

### 7. Error Code Specification

Please refer to the [General Error Codes](#General Error Codes) .



## Spot Rate Query Interface

### 1. Information

* **Path**: /service/synconhub/product/instantBooking/search
* **Method**: POST

### 2. Request parameters

| Name      |  Type   | Required |             Description              |            **Remarks**            |
| :-------- | :-----: | :------: | :----------------------------------: | :-------------------------------: |
| startDate | ISODate |   yes    | the start date of sailing date scope |                                   |
| endDate   | ISODate |    no    |    the end of Sailing date scope     |                                   |
| porCityId | String  |   yes    |             por city id              |                                   |
| fndCityId | String  |   yes    |             fnd city id              |                                   |
| page      | Integer |   yes    |              page size               |   **default:**1，**minimum:**1    |
| size      | Integer |   yes    |             record size              | **default:**20，**range:**[1, 50] |

### 3. Request sample

```javascript
{
    "startDate": "2022-03-10T00:00:00.000Z",
    "endDate": "2022-05-01T00:00:00.000Z",
    "fndCityId": "738872886248637",
    "porCityId": "738872886232879",
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
                "id": "8aaa97667f6dbe37017f71d618e303d4",
                "tradeLaneCode": "AEM",
                "serviceCode": "AEM1",
                "tradeArea": "ETD",
                "vesselName": "COSCO SHIPPING ANDES",
                "voyageNo": "023",
                "direction": "W",
                "porFacilityCode": null,
                "porFacilityName": null,
                "porFacilityNameCn": null,
                "fndFacilityName": "Voltri Terminal Europa SpA",
                "fndFacilityNameCn": null,
                "fndFacilityCode": "GOA01",
                "haulageType": "CY-CY",
                "isFmcProduct": false,
                "estimatedTransitTimeInDays": 29,
                "effectiveStartDate": "2022-03-01T00:00:00.000Z",
                "effectiveEndDate": "2022-04-30T23:59:59.000Z",
                "eta": "2022-05-29 12:00",
                "etd": "2022-04-28 20:00",
                "inventory": 935,
                "isSupportInsurance": true,  // Whether to support insurance
                "polTsMode": null, // pol transit mode
                "podTsMode": null, // pod transit mode
                "porCity": { // por city info，for the specific structure, please refer to 'City information query interface'
                    "id": "738872886232879",
                    "unlocode": "CNNBO",
                    "cityName": "Ningbo",
                    "cntyName": "Ningbo",
                    "stateName": "Zhejiang",
                    "stateCode": "ZJ",
                    "ctryRegionName": "China",
                    "ctryRegionCode": "CN",
                    "cityFullNameEn": "Ningbo",
                    "cityFullNameCn": "宁波"
                },
                "fndCity": {
                    "id": "738872886248637",
                    "unlocode": "ITGOA",
                    "cityName": "Genova",
                    "cntyName": "Genova",
                    "stateName": "Liguria",
                    "stateCode": null,
                    "ctryRegionName": "Italy",
                    "ctryRegionCode": "IT",
                    "cityFullNameEn": "Genova",
                    "cityFullNameCn": "热那亚"
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
                        "cityFullNameEn": "Shanghai",
                        "cityFullNameCn": "上海",
                        "ctryRegionName": "China",
                        "ctryRegionCode": "CN"
                	}
                },
                "podPort": {
                    "id": "349647918207001",
                    "portName": "Genoa",
                    "portCode": "GOA",
                    "portFullNameEn": "Genoa",
                    "portFullNameCn": "Genoa",
                    "city": {
                        "id": "738872886248637",
                        "unlocode": "ITGOA",
                        "cityName": "Genova",
                        "cntyName": "Genova",
                        "stateName": "Liguria",
                        "stateCode": null,
                        "ctryRegionName": "Italy",
                        "ctryRegionCode": "IT",
                        "cityFullNameEn": "Genova",
                        "cityFullNameCn": "热那亚"
                    }
                },
                "firstPolPort": {
                    "id": "349645770723373",
                    "portName": "Ningbo",
                    "portCode": "NGB",
                    "portFullNameEn": "Ningbo",
                    "portFullNameCn": "宁波",
                    "city": {
                        "id": "738872886232879",
                        "unlocode": "CNNBO",
                        "cityName": "Ningbo",
                        "cntyName": "Ningbo",
                        "stateName": "Zhejiang",
                        "stateCode": "ZJ",
                        "ctryRegionName": "China",
                        "ctryRegionCode": "CN",
                        "cityFullNameEn": "Ningbo",
                        "cityFullNameCn": "宁波"
                    }
                },
                "lastPodPort": {
                    "id": "349647918207001",
                    "portName": "Genoa",
                    "portCode": "GOA",
                    "portFullNameEn": "Genoa",
                    "portFullNameCn": "Genoa",
                    "city": {
                        "id": "738872886248637",
                        "unlocode": "ITGOA",
                        "cityName": "Genova",
                        "cntyName": "Genova",
                        "stateName": "Liguria",
                        "stateCode": null,
                        "ctryRegionName": "Italy",
                        "ctryRegionCode": "IT",
                        "cityFullNameEn": "Genova",
                        "cityFullNameCn": "热那亚"
                    }
                },
                "scheduleData": {
                    "origin": {
                        "id": "738872886232879",
                        "unlocode": "CNNBO",
                        "cityName": "Ningbo",
                        "cntyName": "Ningbo",
                        "stateName": "Zhejiang",
                        "stateCode": "ZJ",
                        "ctryRegionName": "China",
                        "ctryRegionCode": "CN",
                        "cityFullNameEn": "Ningbo",
                        "cityFullNameCn": "宁波"
                    },
                    "destination": {
                        "id": "738872886248637",
                        "unlocode": "ITGOA",
                        "cityName": "Genova",
                        "cntyName": "Genova",
                        "stateName": "Liguria",
                        "stateCode": null,
                        "ctryRegionName": "Italy",
                        "ctryRegionCode": "IT",
                        "cityFullNameEn": "Genova",
                        "cityFullNameCn": "热那亚"
                    },
                    "porFacilityCode": null,
                    "fndFacilityCode": "GOA01",
                    "legs": [
                        {
                            "carrier": null,
                            "service": null,
                            "vessel": null,
                            "voyageCode": null,
                            "internalVoyageNumber": null,
                            "externalVoyageNumber": null,
                            "pol": {
                                "port": {
                                    "id": "349645770723373",
                                    "portName": "Ningbo",
                                    "portCode": "NGB",
                                    "portFullNameEn": "Ningbo",
                                    "portFullNameCn": "宁波",
                                    "city": null
                                },
                                "etd": "2022-04-28 20:00",
                                "atd": null,
                                "eta": null,
                                "ata": null,
                                "facilityCode": null
                            },
                            "pod": {
                                "port": {
                                    "id": "349657045012458",
                                    "portName": "Shanghai",
                                    "portCode": "SHA",
                                    "portFullNameEn": "Shanghai",
                                    "portFullNameCn": "上海",
                                    "city": null
                                },
                                "etd": null,
                                "atd": null,
                                "eta": "2022-04-28 20:00",
                                "ata": null,
                                "facilityCode": null
                            },
                            "legSequence": 0,
                            "direction": null,
                            "transportMode": "FEEDER"
                        },
                        {
                            "carrier": null,
                            "service": {
                                "serviceCode": "AEM1",
                                "serviceId": null
                            },
                            "vessel": {
                                "vesselName": "COSCO SHIPPING ANDES",
                                "vesselId": null,
                                "vesselCode": "CJE"
                            },
                            "voyageCode": "023",
                            "internalVoyageNumber": "023",
                            "externalVoyageNumber": "023W",
                            "pol": {
                                "port": {
                                    "id": "349657045012458",
                                    "portName": "Shanghai",
                                    "portCode": "SHA",
                                    "portFullNameEn": "Shanghai",
                                    "portFullNameCn": "上海",
                                    "city": null
                                },
                                "etd": "2022-04-28 20:00",
                                "atd": null,
                                "eta": "2022-04-27 20:00",
                                "ata": null,
                                "facilityCode": "SHA08"
                            },
                            "pod": {
                                "port": {
                                    "id": "349647918207001",
                                    "portName": "Genoa",
                                    "portCode": "GOA",
                                    "portFullNameEn": "Genoa",
                                    "portFullNameCn": "Genoa",
                                    "city": null
                                },
                                "etd": "2022-05-31 12:00",
                                "atd": null,
                                "eta": "2022-05-29 12:00",
                                "ata": null,
                                "facilityCode": "GOA01"
                            },
                            "legSequence": 1,
                            "direction": "W",
                            "transportMode": "Vessel"
                        }
                    ],
                    "cutOffLocalDate": "2022-04-27 12:00",
                    "estimatedTransitTimeInDays": 31,
                    "etaAtFnd": "2022-05-29 12:00",
                    "etdAtPor": "2022-04-28 20:00",
                    "serviceCode": "AEM1",
                    "vesselName": "COSCO SHIPPING ANDES",
                    "direction": "W",
                    "voyageNo": "023"
                },
                "routeProductPricingList": [
                    {
                        "cntrType": "20GP",
                        "price": 2.00,
                        "tradePrice": 0.50, //tradePrice = price - promotions - discounts
                        "currency": "USD",
                    }
                ],
                "promotions": {
                    "detail": [
                        {
                            "promotionType": "OFF",
                            "remnantInventory": 40,
                            "summary": "订单下箱型20GP、40GP、40HQ每UNIT立减1USD。",
                            "priority": 1
                        }
                    ]
                },
                "companyDiscountsInfoDTO": [
                    {
                        "channelCntr": "20GP",
                        "amount": 0.5,
                        "currencyType": "USD"
                    }
                ]
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

### 7. Error Code Specification

Please refer to the [General Error Codes](#General Error Codes) .



## Intermodal Service Query Interface


### 1.Information

* **Path**: /service/synconhub/common/intermodalService/{productId}
* **Method**: GET

### 2. Path parameters

|   Name    | **Type** | Required | Description |
| :-------: | :------: | :------: | :---------: |
| productId |  String  |   yes    | product id  |

### 3. Request sample

```HTTP
GET /service/synconhub/common/intermodalService/8aaa97667f6dbe37017f71d618e303d4
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
                "containerInfoDTOList": [ // Supported cntr type and weight config
                    {
                        "cntrSizeType": "20GP",
                        "cntrPrice": 100,
                        "currencyType": "USD",
                        "minWeight": null,
                        "maxWeight": null,
                        "weightUnit": null
                    },
                    {
                        "cntrSizeType": "40GP",
                        "cntrPrice": 300,
                        "currencyType": "USD",
                        "minWeight": null,
                        "maxWeight": null,
                        "weightUnit": null
                    },
                    {
                        "cntrSizeType": "40HQ",
                        "cntrPrice": 300,
                        "currencyType": "USD",
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
                "containerInfoDTOList": [
                    {
                        "cntrSizeType": "20GP",
                        "cntrPrice": 100,
                        "currencyType": "USD",
                        "minWeight": null,
                        "maxWeight": null,
                        "weightUnit": null
                    },
                    {
                        "cntrSizeType": "40GP",
                        "cntrPrice": 200,
                        "currencyType": "USD",
                        "minWeight": null,
                        "maxWeight": null,
                        "weightUnit": null
                    },
                    {
                        "cntrSizeType": "40HQ",
                        "cntrPrice": 200,
                        "currencyType": "USD",
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
                "containerInfoDTOList": [ 
                    // If cargo weight information is configured,The same cntr type will have multiple data
                    {
                        "cntrSizeType": "20GP",
                        "cntrPrice": 100,
                        "currencyType": "USD",
                        "minWeight": 0,  // min cargo weight
                        "maxWeight": 20, // max cargo weight
                        "weightUnit": "TON" // weight unit eg: "TON"
                        // If the weight of the cargo is between 0-20 tons, the loading service fee is 100 USD
                    },
                    {
                        "cntrSizeType": "20GP",
                        "cntrPrice": 150,
                        "currencyType": "USD",
                        "minWeight": 20,
                        "maxWeight": 40,
                        "weightUnit": "TON"
                        // If the weight of the cargo is between 20-40 tons, the loading service fee is 150 USD
                    },
                    {
                        "cntrSizeType": "20GP",
                        "cntrPrice": 200,
                        "currencyType": "USD",
                        "minWeight": 40,
                        "maxWeight": null,
                        "weightUnit": "TON"
                        // If the weight of the cargo is more than 40 tons, the loading service fee is 200 USD
                    },
                    {
                        "cntrSizeType": "40GP",
                        "cntrPrice": 300,
                        "currencyType": "USD",
                        "minWeight": null,
                        "maxWeight": null,
                        "weightUnit": "TON"
                    },
                    {
                        "cntrSizeType": "40HQ",
                        "cntrPrice": 300,
                        "currencyType": "USD",
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
                "containerInfoDTOList": [
                    {
                        "cntrSizeType": "20GP",
                        "cntrPrice": 300,
                        "currencyType": "USD",
                        "minWeight": null,
                        "maxWeight": null,
                        "weightUnit": null
                    },
                    {
                        "cntrSizeType": "40GP",
                        "cntrPrice": 400,
                        "currencyType": "USD",
                        "minWeight": null,
                        "maxWeight": null,
                        "weightUnit": null
                    },
                    {
                        "cntrSizeType": "40HQ",
                        "cntrPrice": 400,
                        "currencyType": "USD",
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
    "code": 20060,
    "message": "The intermodal service does not exist"
}
```

```javascript
{
    "code": 20059,
    "message": "Wrong bound type of intermodal service"
}
```

### 7. Error Code Specification

Please refer to the [General Error Codes](#General Error Codes) .



## Spot Rate Details Query Interface

### 1. Information


* **Path**: /service/synconhub/product/instantBooking/{productId}
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
GET /service/synconhub/product/instantBooking/8aaa97667f6dbe37017f71d618e303d4
```

```http
GET /service/synconhub/product/instantBooking/8aaa97667f6dbe37017f71d618e303d4?loadingServiceNo=8a5e112478d41b380178d4551a33000a
```

```http
GET /service/synconhub/product/instantBooking/8aaa97667f6dbe37017f71d618e303d4?loadingServiceNo=8a5e112478d41b380178d4551a33000a&dischargeServiceNo=8a5e112478c4eed60178c51cca9f0001
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
        "id": "8aaa97667f6dbe37017f71d618e303d4",
        "tradeLaneCode": "AEM",
        "serviceCode": "AEM1",
        "tradeArea": "ETD",
        "vesselName": "COSCO SHIPPING ANDES",
        "voyageNo": "023",
        "direction": "W",
        "porFacilityCode": null,
        "porFacilityName": null,
        "porFacilityNameCn": null,
        "fndFacilityName": "Voltri Terminal Europa SpA",
        "fndFacilityNameCn": null,
        "fndFacilityCode": "GOA01",
        "haulageType": "CY-CY",
        "isFmcProduct": false,
        "estimatedTransitTimeInDays": 31,
        "effectiveStartDate": "2022-03-01T00:00:00.000Z",
        "effectiveEndDate": "2022-04-30T23:59:59.000Z",
        "eta": "2022-05-29 12:00",
        "etd": "2022-04-28 20:00",
        "inventory": 935,
        "polTsMode": null, // pol transit mode
        "podTsMode": null, // pod transit mode
        "porCity": {  // por city info，for the specific structure, please refer to 'City information query interface'
            "id": "738872886232879",
            "unlocode": "CNNBO",
            "cityName": "Ningbo",
            "cntyName": "Ningbo",
            "stateName": "Zhejiang",
            "stateCode": "ZJ",
            "cityFullNameEn": "Ningbo",
            "cityFullNameCn": "宁波",
            "ctryRegionName": "China",
            "ctryRegionCode": "CN"
        },
        "fndCity": {
            "id": "738872886248637",
            "unlocode": "ITGOA",
            "cityName": "Genova",
            "cntyName": "Genova",
            "stateName": "Liguria",
            "stateCode": null,
            "cityFullNameEn": "Genova",
            "cityFullNameCn": "热那亚",
            "ctryRegionName": "Italy",
            "ctryRegionCode": "IT"
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
                "cityFullNameEn": "Shanghai",
                "cityFullNameCn": "上海",
                "ctryRegionName": "China",
                "ctryRegionCode": "CN"
            }
        },
        "podPort": {
            "id": "349647918207001",
            "portName": "Genoa",
            "portCode": "GOA",
            "portFullNameEn": "Genoa",
            "portFullNameCn": "Genoa",
            "city": {
                "id": "738872886248637",
                "unlocode": "ITGOA",
                "cityName": "Genova",
                "cntyName": "Genova",
                "stateName": "Liguria",
                "stateCode": null,
                "ctryRegionName": "Italy",
                "ctryRegionCode": "IT",
                "cityFullNameEn": "Genova",
                "cityFullNameCn": "热那亚"
            }
        },
        "firstPolPort": {
            "id": "349645770723373",
            "portName": "Ningbo",
            "portCode": "NGB",
            "portFullNameEn": "Ningbo",
            "portFullNameCn": "宁波",
            "city": {
                "id": "738872886232879",
                "unlocode": "CNNBO",
                "cityName": "Ningbo",
                "cntyName": "Ningbo",
                "stateName": "Zhejiang",
                "stateCode": "ZJ",
                "ctryRegionName": "China",
                "ctryRegionCode": "CN",
                "cityFullNameEn": "Ningbo",
                "cityFullNameCn": "宁波"
            }
        },
        "lastPodPort": {
            "id": "349647918207001",
            "portName": "Genoa",
            "portCode": "GOA",
            "portFullNameEn": "Genoa",
            "portFullNameCn": "Genoa",
            "city": {
                "id": "738872886248637",
                "unlocode": "ITGOA",
                "cityName": "Genova",
                "cntyName": "Genova",
                "stateName": "Liguria",
                "stateCode": null,
                "ctryRegionName": "Italy",
                "ctryRegionCode": "IT",
                "cityFullNameEn": "Genova",
                "cityFullNameCn": "热那亚"
            }
        },
        "scheduleData": { 
            "origin": {
                "id": "738872886232879",
                "unlocode": "CNNBO",
                "cityName": "Ningbo",
                "cntyName": "Ningbo",
                "stateName": "Zhejiang",
                "stateCode": "ZJ",
                "ctryRegionName": "China",
                "ctryRegionCode": "CN",
                "cityFullNameEn": "Ningbo",
                "cityFullNameCn": "宁波"
            },
            "destination": {
                "id": "738872886248637",
                "unlocode": "ITGOA",
                "cityName": "Genova",
                "cntyName": "Genova",
                "stateName": "Liguria",
                "stateCode": null,
                "ctryRegionName": "Italy",
                "ctryRegionCode": "IT",
                "cityFullNameEn": "Genova",
                "cityFullNameCn": "热那亚"
            },
            "porFacilityCode": null, 
            "fndFacilityCode": "GOA01",
            "legs": [
                {
                    "carrier": null,
                    "service": null,
                    "vessel": null,
                    "voyageCode": null,
                    "internalVoyageNumber": null,
                    "externalVoyageNumber": null,
                    "pol": {
                        "port": {
                            "id": "349645770723373",
                            "portName": "Ningbo",
                            "portCode": "NGB",
                            "portFullNameEn": "Ningbo",
                            "portFullNameCn": "宁波",
                            "city": null
                        },
                        "etd": "2022-04-28 20:00",
                        "atd": null,
                        "eta": null,
                        "ata": null,
                        "facilityCode": null
                    },
                    "pod": {
                        "port": {
                            "id": "349657045012458",
                            "portName": "Shanghai",
                            "portCode": "SHA",
                            "portFullNameEn": "Shanghai",
                            "portFullNameCn": "上海",
                            "city": null
                        },
                        "etd": null,
                        "atd": null,
                        "eta": "2022-04-28 20:00",
                        "ata": null,
                        "facilityCode": null
                    },
                    "legSequence": 0,
                    "direction": null,
                    "transportMode": "FEEDER"
                },
                {
                    "carrier": null,
                    "service": {
                        "serviceCode": "AEM1",
                        "serviceId": null
                    },
                    "vessel": {
                        "vesselName": "COSCO SHIPPING ANDES",
                        "vesselId": null,
                        "vesselCode": "CJE"
                    },
                    "voyageCode": "023",
                    "internalVoyageNumber": "023",
                    "externalVoyageNumber": "023W",
                    "pol": {
                        "port": {
                            "id": "349657045012458",
                            "portName": "Shanghai",
                            "portCode": "SHA",
                            "portFullNameEn": "Shanghai",
                            "portFullNameCn": "上海",
                            "city": null
                        },
                        "etd": "2022-04-28 20:00",
                        "atd": null,
                        "eta": "2022-04-27 20:00",
                        "ata": null,
                        "facilityCode": "SHA08"
                    },
                    "pod": {
                    	"port": {
                            "id": "349647918207001",
                            "portName": "Genoa",
                            "portCode": "GOA",
                            "portFullNameEn": "Genoa",
                            "portFullNameCn": "Genoa",
                            "city": null
                    	},
                        "etd": "2022-05-31 12:00",
                        "atd": null,
                        "eta": "2022-05-29 12:00",
                        "ata": null,
                        "facilityCode": "GOA01"
                    },
                    "legSequence": 1,
                    "direction": "W",
                    "transportMode": "Vessel"
                }
            ],
            "cutOffLocalDate": "2022-04-27 12:00",
            "estimatedTransitTimeInDays": 31,
            "etaAtFnd": "2022-05-29 12:00",
            "etdAtPor": "2022-04-28 20:00",
            "serviceCode": "AEM1",
            "vesselName": "COSCO SHIPPING ANDES",
            "direction": "W",
            "voyageNo": "023"
        },
        "routeProductPricingList": [
            {
                "cntrType": "20GP",
                "price": 2.00, 
                "tradePrice": 0.50,
                "currency": "USD"
            }
        ],
        "promotions": {
            "detail": [
                {
                    "promotionType": "OFF", 
                    "remnantInventory": 51,
                    "summary": "订单下箱型20GP、40GP、40HQ每UNIT立减1USD。",
                    "priority": 1, // usage priority
                }
            ]
        },
        "companyDiscountsInfoDTO": [
            {
                "channelCntr": "20GP",
                "amount": 0.5,
                "currencyType": "USD",
            }
        ],
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
        "porTrailerAreas": [
            {
                "doorAreaCode": "NGB03",  // POR facility code
                "doorAreaName": "Ningbo Gangji Terminal Co.,Ltd"  // POR facility name
            },
            {
                "doorAreaCode": "NGB05",
                "doorAreaName": "Ningbo Yuandong Terminals Limited"
            },
            {
                "doorAreaCode": "NGB07",
                "doorAreaName": "Meishan-Island Int'l Container Tml"
            }
        ],
        "fndTrailerAreas": [
            {
                "doorAreaCode": "GOA01",	// FND facility code
                "doorAreaName": "Voltri Terminal Europa SpA"	// FND facility name
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

### 8. Error Code Specification

Please refer to the [General Error Codes](#General Error Codes) .



## Surcharge Query Interface

Get the surcharge details of a specific product.

### 1. Information


* **Path**: /service/synconhub/common/extraChargeFee/${productId}
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
GET /service/synconhub/common/extraChargeFee/8aaa97667f6dbe37017f71d618e303d4
```

```http
GET /service/synconhub/common/extraChargeFee/8aaa97667f6dbe37017f71d618e303d4?loadingServiceNo=8a5e11127467f02501747218e25c0001
```

```http
GET /service/synconhub/common/extraChargeFee/8aaa97667f6dbe37017f71d618e303d4?loadingServiceNo=8a5e11127467f02501747218e25c0001&dischargeServiceNo=8a5e11247866fbec0178681acd3a0008
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
                            "category": null // charge category
                        }
                    ]
                },
                {
                    "chargeModel": "CNTR",
                    "cntrSize": "20GP",
                    "chargeDetail": [
                        {
                            "chargeCode": "255",
                            "chargeName": "工本费",
                            "chargeType": "CNTR_LOCAL",
                            "chargeTag": "POR",
                            "price": 10,
                            "currency": "USD",
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

### 8. Error Code Specification

Please refer to the [General Error Codes](#General Error Codes) .



## Trailer Doors Query Interface

Get POR/FND trailer doors of the product.

> Note: The selection of trailer service will be affected by the intermodal service. After selecting the intermodal service, you need to query the spot rate details again with intermodal service number to get the new trailer areas.

### 1.Information

* **Path**: /service/synconhub/common/trailerDoors
* **Method**: POST

### 2. Request Parameters

|     Name     | **Type** | Required |                         Description                          |                     Remarks                      |
| :----------: | :------: | :------: | :----------------------------------------------------------: | :----------------------------------------------: |
|  boundType   |  String  |   yes    |          bound type, POR trailer: O, FND trailer: I          |             **optional value**: O, I             |
| doorAreaCode |  String  |   yes    | POR/FND trailer door area code, select from the porTrailerAreas/fndTrailerAreas property in the product details interface |                                                  |
|   keywords   |  String  |    no    |                trailer door address keywords                 |                                                  |
|     page     | Integer  |    no    |                         page number                          |          **default**: 1, **minimum**: 1          |
|     size     | Integer  |    no    |                          page size                           | **default**: 30, **minimum**: 1, **maximum**: 30 |

### 3. Request Sample

* **POR Trailer Service Request Sample**

```javascript
{
	"keywords": "余杭",
	"boundType": "O",	// outbound
	"doorAreaCode": "NGB05"	// POR trailer door area code: select from the porTrailerAreas property in the product details interface.
}
```

* **FND Trailer Service Request Sample**

```javascript
{
    "keywords": "Genova",
	"boundType": "I",	// inbound
	"doorAreaCode": "GOA01"	// FND trailer door area code: select from the fndTrailerAreas property in the product details interface.
}
```

### 4. Response Parameters

| Name    | Description    |
| ------- | -------------- |
| code    | status code    |
| message | detail message |
| data    | response data  |

### 5. Response Sample

* **POR Trailer Service Response Sample**

```javascript
{
    "code": 0,
    "message": "",
    "data": {
        "content": [
            {
                "doorId": "8aaa02cc7f07d638017f12b757f517c3",
                "doorArea": "临平, 余杭, 杭州, 浙江, 中国"
            },
            {
                "doorId": "8aaa02cc7f07d638017f12b757f517c4",
                "doorArea": "南苑, 余杭, 杭州, 浙江, 中国"
            }
        ],
        "number": 1,
        "size": 30,
        "totalPages": 1,
        "totalElements": 2,
        "first": true, // is first page
        "last": true, // is last page
        "empty": false
    }
}
```

* **FND Trailer Service Response Sample**

```javascript
{
    "code": 0,
    "message": "",
    "data": {
        "content": [
            {
                "doorId": "8a5e11357e2e9f80017e3a6b11fa002e",
                "doorArea": "Genova, Genova, Liguria, Italy"
            }
        ],
        "number": 1,
        "size": 30,
        "totalPages": 1,
        "totalElements": 1,
        "first": true, // is first page
        "last": true, // is last page
        "empty": false
    }
}
```

### 6. Error Code Specification

Please refer to the [General Error Codes](#General Error Codes) .



## Trailer Rates Query Interface

Get POR/FND trailer rates of the trailer door.

### 1.Information

* **Path**: /service/synconhub/common/trailerRates
* **Method**: POST

### 2. Query Parameters

|        Name        | **Type** | Required |                         Description                          |
| :----------------: | :------: | :------: | :----------------------------------------------------------: |
|     productId      |  String  |   yes    |                          product id                          |
|       doorId       |  String  |   yes    | trailer door id, select from the result of the trailer doors query interface |
|  loadingServiceNo  |  String  |    no    | intermodal Service No from intermodal service query results  |
| dischargeServiceNo |  String  |    no    | intermodal Service No from intermodal service query results  |

### 3. Request Sample

```javascript
{
    "productId": "8aaa97667f6dbe37017f71d618e303d4",
    "doorId": "8aaad5377f05a93f017f0869b9ef0019",
    "loadingServiceNo": "8a5e11127467f02501747218e25c0001",
    "dischargeServiceNo": "8a5e11247866fbec0178681acd3a0008"
}
```

### 4. Response Parameters

| Name    | Description    |
| ------- | -------------- |
| code    | status code    |
| message | detail message |
| data    | response data  |

### 5. Response Sample

```javascript
{
    "code": 0,
    "message": "",
    "data": [
    	{
            "cntrType": "20GP",
            "currency": "CNY",
            "price": 3400.01,
            "udd": "UDD: udd1, Remark: test",	// remark
            "paymentTerms": "P"
        },
        {
            "cntrType": "40GP",
            "currency": "CNY",
            "price": 5400.02,
            "udd": "UDD: udd1, Remark: test",
            "paymentTerms": "P"
        },
        {
            "cntrType": "40HQ",
            "currency": "CNY",
            "price": 5400.02,
            "udd": "UDD: udd1, Remark: test",
            "paymentTerms": "P",
        },
        {
            "cntrType": "20GP",
            "currency": "CNY",
            "price": 3400.01,
            "udd": "UDD: udd1, Remark: test",
            "paymentTerms": "C"
        },
        {
            "cntrType": "40GP",
            "currency": "CNY",
            "price": 5400.02,
            "udd": "UDD: udd1, Remark: test",
            "paymentTerms": "C"
        },
        {
            "cntrType": "40HQ",
            "currency": "CNY",
            "price": 5400.02,
            "udd": "UDD: udd1, Remark: test",
            "paymentTerms": "C",
        }
    ]
}
```

### 6. Error Sample

```javascript
{
    "code": 20093,
    "message": "The trailer door cannot be found"
}
```

### 7. Error Code Specification

Please refer to the [General Error Codes](#General Error Codes) .



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

 - 20091: "Please select one trailer service at least for this product"
 - 20092: "Current product does not support trailer service"
 - 20093: "The trailer door cannot be found"
 - 20094: "The trailer rate cannot be obtained"
 - 20095: "Some container types do not support trailer service"
 - 20097: "Error trailer door information"
 - 20098: "The loading time of the trailer service must be greater than the current time"
