# COSCO Syncon Hub API Specification for Cross Booking

[toc]

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



## Port Information Query Interface

Fuzzy query of the port information maintained by the platform based on the keywords of the port name

### 1. Information

* **Path**: `/service/synconhub/common/port/search`
* **Method**: POST

### 2. Request parameters

| Name     | **Type** | **Required** |    Description     |                  **Remarks**                  |
| :------- | :------: | :----------: | :----------------: | :-------------------------------------------: |
| keywords |  String  |     yes      | port name keywords |                                               |
| page     | Integer  |      no      |     page size      |         **default:**1，**minimum:**1          |
| size     | Integer  |      no      |    record size     | **default:**30，**minimum:**1，**maximum:**30 |

### 3. Request sample

```java
{
    "keywords": "yantian",
    "page": 1,
    "size": 30
}
```

### 4. Response parameters

| Name    | Description                                |
| ------- | ------------------------------------------ |
| code    | status code                                |
| message | detail message                             |
| data    | Paging query results (sorted by port name) |

### 5. Response sample

```javascript
{
    "code": 0,
    "message": "",
    "data": {
        "content": [
            {
                "id": "349645770723388",
                "portName": "Yantian",
                "portCode": "YTN",
                "portFullNameEn": "Yantian",
                "portFullNameCn": "盐田",
                "city": {
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

Please refer to the [General Error Codes](../SynConHubApiErrorCodeList.md) .



## Cross Booking Inventory Query Interface

### 1. Information

* **Path**:` /service/synconhub/crossBooking/inventory/search`
* **Method**: POST

### 2. Request parameters

| Name           |  Type   | **Required** |             Description              |                         **Remarks**                          |
| :------------- | :-----: | :----------: | :----------------------------------: | :----------------------------------------------------------: |
| contractNumber | String  |     yes      |           contract number            |                                                              |
| fromDate       | ISODate |     yes      | effective date range of the contract | ISO format in GMT by default, like: '2020-07-20T08:00:00.000Z' |
| toDate         | ISODate |      no      | effective date range of the contract | ISO format in GMT by default, like: '2020-07-31T12:00:00.000Z' |
| service        | String  |      no      |             service code             |                                                              |
| polId          | String  |      no      |             pol port id              |                                                              |
| page           | Integer |      no      |             page number              |                  **default:** 1,**min:** 1                   |
| size           | Integer |      no      |              page size               |             **default:** 20, **range:** [1, 50]              |

### 3. Request sample

```java
{
    "contractNumber": "ATE20001",
    "fromDate": "2020-07-20T00:00:00.000Z",
    "toDate": "2020-08-20T00:00:00.000Z",
    "service": "SEA",
    "polId": "349645770723388",
    "page": 1,
    "size": 20
}
```

### 4. Response parameters

| Name    | Description                                                  |
| ------- | ------------------------------------------------------------ |
| code    | status code                                                  |
| message | detail message                                               |
| data    | pagination to return inventory information that meets the query condition.(sorted by week number) |

### 5. Response sample

```javascript
{
    "code": 0,
    "message": "",
    "data": {
        "content": [
            {
                "weekNo": "202033", // week number
                "inventoryId": "8a5e110e7494c338017494d22fc00009", // inventory ID of the contract
                "contractNumber": "ATE20001",
                "services": ["SEA", "AEU1"],
                "pols": [ // pol port infos, for the specific structure, please refer to "Port Information Query Interface"
                    {
                        "id": "349645770723388",
                        "portName": "Yantian",
                        "portCode": "YTN",
                        "portFullNameEn": "Yantian",
                        "portFullNameCn": "盐田",
                        "city": {
                            "id": "738872886233057",
                            "unlocode": "CNSZN",
                            "cityName": "Shenzhen",
                            "cntyName": "Shenzhen",
                            "stateName": "Guangdong",
                            "stateCode": "GD",
                            "ctryRegionName": "China",
                            "ctryRegionCode": "CN",
                            "cityFullNameEn": "Shenzhen",
                            "cityFullNameCn": "深圳"      
                		}
            		}
                ], 
                "used": 10,
                "available": 20,
                "total": 30
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

Please refer to the [General Error Codes](../SynConHubApiErrorCodeList.md) .





## Cross Booking Schedule Query Interface

### 1. Information

* **Path**: `/service/synconhub/crossBooking/schedule`
* **Method**: POST

### 2. Request parameters

| Name           |  Type   | **Required** |   Description   |             **Remarks**             |
| :------------- | :-----: | :----------: | :-------------: | :---------------------------------: |
| inventoryId    | String |     yes      | inventory Id of the contract No. |                                     |
| originCityId          | String  |      yes      |   por city id   |                                     |
| destinationCityId | String | yes | fnd city id | |
| cargoNature | String | no | cargo nature, e.g.: GC, RF, DG, AW | **default**: GC |
| page           | Integer |      no      |   page number   |      **default:** 1,**min:** 1      |
| size           | Integer |      no      |    page size    | **default:** 20, **range:** [1, 50] |

### 3. Request sample

```java
{
  "inventoryId":"8a5e110e7494c338017494d22fc00009", // the inventory id of the contract no.
  "cargoNature": "GC",
  "page": 1,
  "size": 20,
  "destinationCityId": "738872886233842",
  "originCityId": "738872886232873"
}
```
### 4. Response parameters

| Name    | Description                                                  |
| ------- | ------------------------------------------------------------ |
| code    | status code                                                  |
| message | detail message                                               |
| data    | pagination to return schedule information that meets the query condition. |

### 5. Response sample

```javascript
{
    "code": 0,
    "message": "",
    "data": {
        "content": [
            {
                "origin": {
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
                },
                "destination": {
                    "id": "738872886233842",
                    "unlocode": "PKKAR",
                    "cityName": "Karachi",
                    "cntyName": null,
                    "stateName": "Sindh",
                    "stateCode": null,
                    "cityFullNameEn": "Karachi",
                    "cityFullNameCn": "卡拉奇",
                    "ctryRegionName": "Pakistan",
                    "ctryRegionCode": "PK"
                },
                "porFacilityCode": "SHA04",
                "fndFacilityCode": "KHI02",
                "legs": [
                    {
                        "carrier": null,
                        "service": {
                            "serviceCode": "PMX",
                            "serviceId": null
                        },
                        "vessel": {
                            "vesselName": "GREENWICH BRIDGE",
                            "vesselId": null,
                            "vesselCode": "SRL"
                        },
                        "voyageCode": "126",
                        "internalVoyageNumber": "126",
                        "externalVoyageNumber": "126W",
                        "pol": {
                            "port": {
                                "id": "349657045012458",
                                "portName": "Shanghai",
                                "portCode": "SHA",
                                "portFullNameEn": "Shanghai",
                                "portFullNameCn": "上海",
                                "city": null
                            },
                            "etd": "2020-09-16 11:00",
                            "atd": null,
                            "eta": "2020-09-15 13:00",
                            "ata": null,
                            "facilityCode": "SHA04"
                        },
                        "pod": {
                            "port": {
                                "id": "349645770723431",
                                "portName": "Karachi",
                                "portCode": "KHI",
                                "portFullNameEn": "Karachi",
                                "portFullNameCn": "Karachi",
                                "city": null
                            },
                            "etd": "2020-10-07 20:00",
                            "atd": null,
                            "eta": "2020-10-05 22:00",
                            "ata": null,
                            "facilityCode": "KHI02"
                        },
                        "legSequence": 1,
                        "direction": "W",
                        "transportMode": "Feeder"
                    }
                ],
                "cutOffLocalDate": "2020-09-16 00:00",
                "estimatedTransitTimeInDays": 19,
                "etaAtFnd": "2020-10-05 22:00",
                "etdAtPor": "2020-09-16 11:00",
                "serviceCode": "PMX",
                "vesselName": "GREENWICH BRIDGE",
                "direction": "W",
                "voyageNo": "126",
                "signature": "2c9fbad696f7a0c4ffcaef2325621c75"
            },
            {
                "origin": {
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
                },
                "destination": {
                    "id": "738872886233842",
                    "unlocode": "PKKAR",
                    "cityName": "Karachi",
                    "cntyName": null,
                    "stateName": "Sindh",
                    "stateCode": null,
                    "cityFullNameEn": "Karachi",
                    "cityFullNameCn": "卡拉奇",
                    "ctryRegionName": "Pakistan",
                    "ctryRegionCode": "PK"
                },
                "porFacilityCode": "SHA04",
                "fndFacilityCode": "KHI03",
                "legs": [
                    {
                        "carrier": null,
                        "service": {
                            "serviceCode": "PMX",
                            "serviceId": null
                        },
                        "vessel": {
                            "vesselName": "GREENWICH BRIDGE",
                            "vesselId": null,
                            "vesselCode": "SRL"
                        },
                        "voyageCode": "126",
                        "internalVoyageNumber": "126",
                        "externalVoyageNumber": "126W",
                        "pol": {
                            "port": {
                                "id": "349657045012458",
                                "portName": "Shanghai",
                                "portCode": "SHA",
                                "portFullNameEn": "Shanghai",
                                "portFullNameCn": "上海",
                                "city": null
                            },
                            "etd": "2020-09-16 11:00",
                            "atd": null,
                            "eta": "2020-09-15 13:00",
                            "ata": null,
                            "facilityCode": "SHA04"
                        },
                        "pod": {
                            "port": {
                                "id": "349645770723431",
                                "portName": "Karachi",
                                "portCode": "KHI",
                                "portFullNameEn": "Karachi",
                                "portFullNameCn": "Karachi",
                                "city": null
                            },
                            "etd": "2020-10-08 22:00",
                            "atd": null,
                            "eta": "2020-10-07 22:00",
                            "ata": null,
                            "facilityCode": "KHI03"
                        },
                        "legSequence": 1,
                        "direction": "W",
                        "transportMode": "Feeder"
                    }
                ],
                "cutOffLocalDate": "2020-09-16 00:00",
                "estimatedTransitTimeInDays": 21,
                "etaAtFnd": "2020-10-07 22:00",
                "etdAtPor": "2020-09-16 11:00",
                "serviceCode": "PMX",
                "vesselName": "GREENWICH BRIDGE",
                "direction": "W",
                "voyageNo": "126",
                "signature": "d8682e7a4f71380c45016c48a1d8a997"
            }
        ],
        "number": 1,
        "size": 20,
        "totalElements": 2,
        "totalPage": 1,
        "totalPages": 1,
        "first": true,
        "last": true,
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

{
    "code": 20043,
    "message": "Cross Booking inventory invalid"
}
```


### 7. Error Code Specification

Please refer to the [General Error Codes](../SynConHubApiErrorCodeList.md) .






## Cross Booking Booking Interface

### 1. Information

* **Path**: `/service/synconhub/crossBooking/shipment/new`
* **Method**: POST

### 2. Request parameters

| Name           |  Type   | **Required** |   Description   |             **Remarks**             |
| :------------- | :-----: | :----------: | :-------------: | :---------------------------------: |
| contractNumber | String |     yes      | contract number |                                     |
| inventoryId    | String |     yes      | the inventory Id of the contract No. |                                     |
| cargoNature    | String |      no      | cargo nature, e.g.: GC, RF, DG, AW. Currently only GC is supported |      **default**: GC                  |
| channelCode    | String |      no      |                 |    **default**: GENERAL                    |
| bookingContainers        | Object  |      yes      |  booking container type and quantity   |                                     |
| \|---cntrType    |  string   |   yes    |                                    | **optional value:** 20GP,40GP,40HQ,45HQ |
| \|---quantity | Integer |   yes   |  | **min**: 1 |
| bookingParties          | Object  |      yes      |   booking parties info   |                                     |
| \|---name            |  string   |   yes    |                                    | **length**: [1, 70], **English characters**  |
| \|---addressLine1    |  string   |      no      | country / region / province / city | **addressline1 / addressline2 and addressText must be filled in one,length**: [1, 35], **English characters** |
| \|---addressLine2    |  string   |   no   |            street name             | **addressline1 / addressline2 and addressText must be filled in one,length**: [1, 35], **English characters** |
| \|---addressText |  string   |   no   |      additional address | **addressline1 / addressline2 and addressText must be filled in one,length**: [1, 1200], **English characters** |
| \|---phone           |  string   |   yes    |                                    |             **length**: [1, 22]              |
| \|---email           |  string   |    no    |                                    |             **length**: [1, 400]             |
| shipperInfo          | Object  |      no      |   shipper info   |                                     |
| \|---name            |  string   |   yes    |                                    | **length**: [1, 70], **English characters**  |
| \|---addressLine1    |  string   |   no   | country / region / province / city | **addressline1 / addressline2 and addressText must be filled in one,length**: [1, 35], **English characters** |
| \|---addressLine2    |  string   |   no   |            street name             | **addressline1 / addressline2 and addressText must be filled in one,length**: [1, 35], **English characters** |
| \|---addressText |  string   |   no   |      additional address | **addressline1 / addressline2 and addressText must be filled in one,length**: [1, 1200], **English characters** |
| \|---phone           |  string   |   yes    |                                    |             **length**: [1, 22]              |
| \|---email           |  string   |    no    |                                    |             **length**: [1, 400]             |
| consigneeInfo          | Object  |      no      |   consignee info   |                                     |
| \|---name            |  string   |   yes    |                                    | **length**: [1, 70], **English characters**  |
| \|---addressLine1    |  string   |   no   | country / region / province / city | **addressline1 / addressline2 and addressText must be filled in one,length**: [1, 35], **English characters** |
| \|---addressLine2    |  string   |   no   |            street name             | **addressline1 / addressline2 and addressText must be filled in one,length**: [1, 35], **English characters** |
| \|---addressText |  string   |   no   |      additional address | **addressline1 / addressline2 and addressText must be filled in one,length**: [1, 1200], **English characters** |
| \|---phone           |  string   |   yes    |                                    |             **length**: [1, 22]              |
| \|---email           |  string   |    no    |                                    |             **length**: [1, 400]             |
| notifyPartyInfo          | Object  |      no      |   notify party info   |                                     |
| \|---name            |  string   |   yes    |                                    | **length**: [1, 70], **English characters**  |
| \|---addressLine1    |  string   |   no   | country / region / province / city | **addressline1 / addressline2 and addressText must be filled in one,length**: [1, 35], **English characters** |
| \|---addressLine2    |  string   |   no   |            street name             | **addressline1 / addressline2 and addressText must be filled in one,length**: [1, 35], **English characters** |
| \|---addressText |  string   |   no   |      additional address | **addressline1 / addressline2 and addressText must be filled in one,length**: [1, 1200], **English characters** |
| \|---phone           |  string   |   yes    |                                    |             **length**: [1, 22]              |
| \|---email           |  string   |    no    |                                    |             **length**: [1, 400]             |
| emergencyContactInfo          | Object  |      yes      |   emergency contact info   |                                     |
| \|---name            |  string   |   no   |                                    | **length**: [0, 60], **English characters** |
| \|---address   |  string   |   no   |       | **length**: [0, 245], **English characters** |
| \|---mobile      |  string   |   no   |                                    |             **length**: [0, 15]             |
| \|---phone  |  string   |   no   |                                    |             **length**: [0, 22]             |
| \|---email           |  string   |    yes    |                                    |             **length**: [1, 35]             |
| scheduleData          | Object  |      yes      |   schedule info   | Please copy the scheduled date information from the  Cross Booking Schedule Query Interface return result |
| cargoInfo          | Object  |      yes      |   cargo info   |                                     |
| \|---desc            |  string   |   yes    |             cargo name             | **length**: [1, 60], **English characters**  |
| \|---packageType     |  string   |   yes    |                                    |                                              |
| \|---quantity        |    int    |   yes    |                                    |                                              |
| \|---weight          |  decimal  |   yes    |                                    |                 **unit**: KG                 |
| \|---volume          |  decimal  |   yes    |                                    |           **unit**: m<sup>3</sup>            |
| \|---remarks         |  string   |    no    |                                    |            **length**: [0,1000]**,English characters**            |
| referenceNo | String | no | additional reference number |  |
| remarks          | String  |      no      |      |                                     |

### 3. Request sample

```java
{
  "inventoryId": "8a5e110e7494c338017494d22fc00009", // the inventory Id of the contract No.
  "contractNumber":"ATE20001",
  "cargoNature": "GC",
  "channelCode":"GENERAL",
  "bookingContainers":[
    {
        "cntrType":"40GP",
        "quantity":1
    }
  ],
  // The scheduleData field information is for reference only, please select and copy it from the  Cross Booking Schedule Query Interface result as the value
  "scheduleData": {
                "origin": {
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
                },
                "destination": {
                    "id": "738872886233842",
                    "unlocode": "PKKAR",
                    "cityName": "Karachi",
                    "cntyName": null,
                    "stateName": "Sindh",
                    "stateCode": null,
                    "cityFullNameEn": "Karachi",
                    "cityFullNameCn": "卡拉奇",
                    "ctryRegionName": "Pakistan",
                    "ctryRegionCode": "PK"
                },
                "porFacilityCode": "SHA04",
                "fndFacilityCode": "KHI02",
                "legs": [
                    {
                        "carrier": null,
                        "service": {
                            "serviceCode": "PMX",
                            "serviceId": null
                        },
                        "vessel": {
                            "vesselName": "OOCL AMERICA",
                            "vesselId": null,
                            "vesselCode": "RMJ"
                        },
                        "voyageCode": "035",
                        "internalVoyageNumber": "035",
                        "externalVoyageNumber": "035N",
                        "pol": {
                            "port": {
                                "id": "349657045012458",
                                "portName": "Shanghai",
                                "portCode": "SHA",
                                "portFullNameEn": "Shanghai",
                                "portFullNameCn": "上海",
                                "city": null
                            },
                            "etd": "2020-09-16 11:00",
                            "atd": null,
                            "eta": "2020-09-15 13:00",
                            "ata": null,
                            "facilityCode": "SHA04"
                        },
                        "pod": {
                            "port": {
                                "id": "349645770723431",
                                "portName": "Karachi",
                                "portCode": "KHI",
                                "portFullNameEn": "Karachi",
                                "portFullNameCn": "Karachi",
                                "city": null
                            },
                            "etd": "2020-10-07 20:00",
                            "atd": null,
                            "eta": "2020-10-05 22:00",
                            "ata": null,
                            "facilityCode": "KHI02"
                        },
                        "legSequence": 1,
                        "direction": "W",
                        "transportMode": "Feeder"
                    }
                ],
  				"cutOffLocalDate": "2020-09-16 00:00",
                "estimatedTransitTimeInDays": 21,
                "etaAtFnd": "2020-10-07 22:00",
                "etdAtPor": "2020-09-16 11:00",
                "serviceCode": "PMX",
                "vesselName": "OOCL AMERICA",
                "direction": "N",
                "voyageNo": "035",
                "signature": "2c9fbad696f7a0c4ffcaef2325621c75" // the signature of the scheduled data, which is to identify the schedule data is from the official provider.
            },
  "bookingParties":  {
            "name":"TEST",
            "addressLine1":"CN",
            "addressLine2":"Room 507, Huale building, Shennan E",
            "phone":"4564654234",
            "email":""
        },
  "shipperInfo": {
            "name":"shipper",
            "addressLine1":"Shenzhen",
            "addressLine2":"ZHUHAIXIANGZHOU",
            "phone":"+86112223311",
            "email":""
        },
  "consigneeInfo":  {
            "name":"consignee",
            "addressLine1":"SHANGHAI",
            "addressLine2":"SHANGHAIPUDONG",
            "phone":"+86112223311",
            "email":""
        },
  "notifyPartyInfo":{
            "name":"notifyParty",
            "addressLine1":"SHANGHAI",
            "addressLine2":"SHANGHAIPUDONG",
            "phone":"+86112223311",
            "email":""
        },
  "emergencyContactInfo":{
        "name":"",
        "email":"test@test.COM",
        "mobile":"",
        "phone":"",
        "address":""
    }, 
    "cargoInfo":{
        "desc":"ASD",
        "packageType":"JL",
        "packageTypeName":"JUMBO ROLL",
        "quantity":1,
        "weight":324,
        "volume":43,
        "remarks":""
    },
     "remarks":"ASD"
}
```
### 4. Response parameters

| Name    | Description                                                  |
| ------- | ------------------------------------------------------------ |
| code    | status code                                                  |
| message | detail message                                               |
| data    | return booking request number |

### 5. Response sample

```javascript
{
    "code": 0,
    "message": "",
    "data": {
        "brNo": "4880701060"
    }
}
```

### 6. Error sample

```javascript
{
    "code": 20000,
    "message": "cannot identify the user, please contact support for help"
}

{
    "code": 20044,
    "message": "Cross Booking wrong shipping date information"
}

{
    "code": 20045,
    "message": "Cross Booking insufficient stock"
}

{
    "code": 20046,
    "message": "Cross Booking cargo container not found"
}
```


### 7. Error Code Specification

Please refer to the [General Error Codes](../SynConHubApiErrorCodeList.md) .



## Cross Booking  History Query Interface

### 1. Information

* **Path**: `/service/synconhub/crossBooking/shipment/search`
* **Method**: POST

### 2. Request parameters

| Name           |  Type   | **Required** |   Description   |             **Remarks**             |
| :------------- | :-----: | :----------: | :-------------: | :---------------------------------: |
| bookingStatus    | String |     no      |         status        | booking status, e.g.: NEW, CONFIRMED, CANCELLED |
| brNo    | String |      no      |       booking request number     |                                     |
| bookingTimeFrom        | ISODate  |      no      |   booking date range   | ISO format in GMT by default, like: '2020-07-20T08:00:00.000Z' |
| bookingTimeTo          | ISODate  |      no      | booking date range | ISO format in GMT by default, like: '2020-07-20T08:00:00.000Z' |
| contractNo          | String  |      no      |  contract number     |                                     |
| pol          | String  |      no      |  pol port id    |                                     |
| por          | String  |      no      |  por city id    |                                     |
| fnd          | String  |      no      |  fnd city id    |                                     |
| page           | Integer |      no      |   page number   |      **default:** 1,**min:** 1      |
| size           | Integer |      no      |    page size    | **default:** 20, **range:** [1, 50] |

### 3. Request sample
```java
{
    "bookingStatus":"",
    "brNo":"4880701100",
    "bookingTimeFrom":"2020-08-15T00:00:00.000Z",
    "bookingTimeTo":"2020-08-31T23:23:59.999Z",
    "contractNo":"",
    "pol":"",
    "por":"",
    "fnd":"",
    "page":"1",
    "size":"30"
}
```

### 4. Response parameters

| Name    | Description                                                  |
| ------- | ------------------------------------------------------------ |
| code    | status code                                                  |
| message | detail message                                               |
| data    | pagination to return crossBooking history information that meets the query condition.(sorted by week number) |

### 5. Response sample

```javascript
{
    "code": 0,
    "message": "",
    "data": {
        "content": [
            {
                "brNo": "4880701100",
                "bookingStatus": "NEW",
                "contractNo": "ATE20001",
                "scheduleData": "{\"cutOffLocalDate\":\"2020-09-16 00:00\",\"destination\":{\"cityFullNameCn\":\"卡拉奇\",\"cityFullNameEn\":\"Karachi\",\"cityName\":\"Karachi\",\"id\":\"738872886233842\",\"stateName\":\"Sindh\",\"unlocode\":\"PKKAR\"},\"direction\":\"W\",\"estimatedTransitTimeInDays\":21,\"etaAtFnd\":\"2020-10-07 22:00\",\"etdAtPor\":\"2020-09-16 11:00\",\"fndFacilityCode\":\"KHI03\",\"legs\":[{\"direction\":\"W\",\"externalVoyageNumber\":\"126W\",\"internalVoyageNumber\":\"126\",\"legSequence\":1,\"pod\":{\"eta\":\"2020-10-07 22:00\",\"etd\":\"2020-10-08 22:00\",\"facilityCode\":\"KHI03\",\"port\":{\"id\":\"349645770723431\",\"portCode\":\"KHI\",\"portFullNameCn\":\"Karachi\",\"portFullNameEn\":\"Karachi\",\"portName\":\"Karachi\"},\"portId\":\"349645770723431\"},\"pol\":{\"eta\":\"2020-09-15 13:00\",\"etd\":\"2020-09-16 11:00\",\"facilityCode\":\"SHA04\",\"port\":{\"id\":\"349657045012458\",\"portCode\":\"SHA\",\"portFullNameCn\":\"上海\",\"portFullNameEn\":\"Shanghai\",\"portName\":\"Shanghai\"},\"portId\":\"349657045012458\"},\"service\":{\"serviceCode\":\"PMX\"},\"transportMode\":\"Feeder\",\"vessel\":{\"vesselCode\":\"SRL\",\"vesselName\":\"GREENWICH BRIDGE\"},\"voyageCode\":\"126\"}],\"origin\":{\"cityFullNameCn\":\"上海\",\"cityFullNameEn\":\"Shanghai\",\"cityName\":\"Shanghai\",\"cntyName\":\"Shanghai\",\"id\":\"738872886232873\",\"stateCode\":\"SH\",\"stateName\":\"Shanghai\",\"unlocode\":\"CNSHA\"},\"porFacilityCode\":\"SHA04\",\"serviceCode\":\"PMX\",\"vesselName\":\"GREENWICH BRIDGE\",\"voyageNo\":\"126\"}",
                "weekNo": "202037"
            }
        ],
        "number": 1,
        "size": 20,
        "totalElements": 1,
        "totalPage": 1,
        "totalPages": 1,
        "first": true,
        "last": true,
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

{
	"code": 20040, 
	"message": "The page size must be greater than 0"
}

{
	"code": 20041, 
	"message": "The page size should be between 0 and 30."
}

{
	"code": 20042, 
	"message": "Parameter verification error"
}
```


### 7. Error Code Specification

Please refer to the [General Error Codes](../SynConHubApiErrorCodeList.md) .



## Cross Booking  History Detail Query Interface

### 1. Information

* **Path**:` /service/synconhub/crossBooking/shipment/{brNo}`
* **Method**: GET

### 2. Request parameters

| Name           |  Type   | **Required** |   Description   |             **Remarks**             |
| :------------- | :-----: | :----------: | :-------------: | :---------------------------------: |
| brNo | String  |     yes      | booking request number |                                     |

### 3. Request sample
```javascript
GET `/service/synconhub/order/detail/E00144631`
```

### 4. Response parameters

| Name    | Description                                                  |
| ------- | ------------------------------------------------------------ |
| code    | status code                                                  |
| message | detail message                                               |
| data    |  return crossBooking history detail information that meets the query condition.(sorted by week number) |

### 5. Response sample
``` javascript
{
    "code": 0,
    "message": "",
    "data": {
        "contractNo": "ATE20001",
        "brNo": "4880701060",
        "remarks": "ASD",
        "cargoNature": "GC",
        "routeSchedule": {
            "origin": {
                "id": "738872886232873",
                "unlocode": "CNSHA",
                "cityName": "Shanghai",
                "cntyName": "Shanghai",
                "stateName": "Shanghai",
                "stateCode": "SH",
                "cityFullNameEn": "Shanghai",
                "cityFullNameCn": "上海",
                "ctryRegionName": null,
                "ctryRegionCode": null
            },
            "destination": {
                "id": "738872886233842",
                "unlocode": "PKKAR",
                "cityName": "Karachi",
                "cntyName": null,
                "stateName": "Sindh",
                "stateCode": null,
                "cityFullNameEn": "Karachi",
                "cityFullNameCn": "卡拉奇",
                "ctryRegionName": null,
                "ctryRegionCode": null
            },
            "porFacilityCode": "SHA04",
            "fndFacilityCode": "KHI02",
            "legs": [
                {
                    "carrier": null,
                    "service": {
                        "serviceCode": "PMX",
                        "serviceId": null
                    },
                    "vessel": {
                        "vesselName": "OOCL AMERICA",
                        "vesselId": null,
                        "vesselCode": "RMJ"
                    },
                    "voyageCode": "035",
                    "internalVoyageNumber": "035",
                    "externalVoyageNumber": "035N",
                    "pol": {
                        "port": {
                            "id": "349657045012458",
                            "portName": "Shanghai",
                            "portCode": "SHA",
                            "portFullNameEn": "Shanghai",
                            "portFullNameCn": "上海",
                            "city": null
                        },
                        "etd": "2020-09-16 11:00",
                        "atd": null,
                        "eta": "2020-09-15 13:00",
                        "ata": null,
                        "facilityCode": "SHA04"
                    },
                    "pod": {
                        "port": {
                            "id": "349645770723431",
                            "portName": "Karachi",
                            "portCode": "KHI",
                            "portFullNameEn": "Karachi",
                            "portFullNameCn": "Karachi",
                            "city": null
                        },
                        "etd": "2020-10-07 20:00",
                        "atd": null,
                        "eta": "2020-10-05 22:00",
                        "ata": null,
                        "facilityCode": "KHI02"
                    },
                    "legSequence": 1,
                    "direction": "W",
                    "transportMode": "Feeder"
                }
            ],
            "cutOffLocalDate": null,
            "estimatedTransitTimeInDays": 0,
            "etaAtFnd": null,
            "etdAtPor": null,
            "serviceCode": "PMX",
            "vesselName": "OOCL AMERICA",
            "direction": "N",
            "voyageNo": "035",
            "signature": null
        },
        "bookingContainerList": [
            {
                "bookingNo": null,
                "status": null,
                "cntrType": "40GP",
                "count": 0
            }
        ],
        "bookingParties": [
            {
                "name": "CHENGYUNREN",
                "addressLine1": "Shenzhen",
                "addressLine2": "ZHUHAIXIANGZHOU",
                "phone": "+86112223311",
                "email": null
            },
            {
                "name": "TONGZHIREN",
                "addressLine1": "SHANGHAI",
                "addressLine2": "SHANGHAIPUDONG",
                "phone": "+86112223311",
                "email": null
            },
            {
                "name": "alibaba",
                "addressLine1": "CN",
                "addressLine2": "Room 507, Huale building, Shennan E",
                "phone": "4564654234",
                "email": null
            },
            {
                "name": "SHOUHUOREN",
                "addressLine1": "SHANGHAI",
                "addressLine2": "SHANGHAIPUDONG",
                "phone": "+86112223311",
                "email": null
            }
        ],
        "cargoInfoDTO": {
            "desc": "ASD",
            "packageType": "JL",
            "quantity": 1,
            "weight": 324,
            "volume": 43,
            "remarks": null
        },
        "emergencyContactInfo": {
            "name": null,
            "email": "TEST@TEST.COM",
            "mobile": null,
            "phone": null,
            "address": null
        }
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

Please refer to the [General Error Codes](../SynConHubApiErrorCodeList.md) .

