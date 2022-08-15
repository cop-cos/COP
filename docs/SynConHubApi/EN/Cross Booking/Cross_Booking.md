# COSCO Syncon Hub API Specification for Cross Booking

[toc]

## Update Logs

v1.2.1 (2021-06-10)

1. **`A`** Added fields for the Cross booking Inventory interface response(vesselName,voyageNo,direction) . [Details](#Cross Booking Inventory Query Interface).

v1.3.0 (2021-08-14)

1. **`A `**  Added general error codes description.  [Details](#General Error Codes).

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

Please refer to the [General Error Codes](#General Error Codes) .



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
                "vesselName": null,
                "voyageNo": null,
                "direction": null,
                "used": 10,
                "available": 20,
                "total": 30
            }，
            {
                "weekNo": "202033",
                "contactNumber": "ATE20001",
                "inventoryId": "8a5e112479ea76590179ea83c6e00000",
                "services": [
                    "SEA"
                ],
                "pols": [
                    {
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
                            "cityFullNameEn": "Shekou",
                            "cityFullNameCn": "蛇口",
                            "ctryRegionName": "China",
                            "ctryRegionCode": "CN"
                        }
                    }
                ],
                "vesselName": VANTAGE,
                "voyageNo": 033,
                "direction": W,
                "used": 0,
                "available": 100,
                "total": 100
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

Please refer to the [General Error Codes](#General Error Codes) .






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
| \|---cntrType    |  String   |   yes    |                                    | **optional value:** 20GP,40GP,40HQ,45HQ |
| \|---quantity | Integer |   yes   |  | **min**: 1 |
| bookingParties          | Object  |      yes      |   booking parties info   |                                     |
| \|---name            |  String   |   yes    |                                    | **length**: [1, 70], **English characters**  |
| \|---addressLine1    |  String   |      no      | country / region / province / city | **addressline1 / addressline2 and addressText must be filled in one,length**: [1, 35], **English characters** |
| \|---addressLine2    |  String   |   no   |            street name             | **addressline1 / addressline2 and addressText must be filled in one,length**: [1, 35], **English characters** |
| \|---addressText |  String   |   no   |      additional address | **addressline1 / addressline2 and addressText must be filled in one,length**: [1, 1200], **English characters** |
| \|---phone           |  String   |   yes    |                                    |             **length**: [1, 22]              |
| \|---email           |  String   |    no    |                                    |             **length**: [1, 400]             |
| forwarderInfo          | Object  |      no      |   forwarder info   |                                     |
| \|---name            |  String   |   yes    |                                    | **length**: [1, 70], **English characters**  |
| \|---addressLine1    |  String   |   no   | country / region / province / city | **addressline1 / addressline2 and addressText must be filled in one,length**: [1, 35], **English characters** |
| \|---addressLine2    |  String   |   no   |            street name             | **addressline1 / addressline2 and addressText must be filled in one,length**: [1, 35], **English characters** |
| \|---addressText |  String   |   no   |      additional address | **addressline1 / addressline2 and addressText must be filled in one,length**: [1, 1200], **English characters** |
| \|---phone           |  String   |   yes    |                                    |             **length**: [1, 22]              |
| \|---email           |  String   |    no    |                                    |             **length**: [1, 400]             |
| shipperInfo          | Object  |      no      |   shipper info   |                                     |
| \|---name            |  String   |   yes    |                                    | **length**: [1, 70], **English characters**  |
| \|---addressLine1    |  String   |   no   | country / region / province / city | **addressline1 / addressline2 and addressText must be filled in one,length**: [1, 35], **English characters** |
| \|---addressLine2    |  String   |   no   |            street name             | **addressline1 / addressline2 and addressText must be filled in one,length**: [1, 35], **English characters** |
| \|---addressText |  String   |   no   |      additional address | **addressline1 / addressline2 and addressText must be filled in one,length**: [1, 1200], **English characters** |
| \|---phone           |  String   |   yes    |                                    |             **length**: [1, 22]              |
| \|---email           |  String   |    no    |                                    |             **length**: [1, 400]             |
| consigneeInfo          | Object  |      no      |   consignee info   |                                     |
| \|---name            |  String   |   yes    |                                    | **length**: [1, 70], **English characters**  |
| \|---addressLine1    |  String   |   no   | country / region / province / city | **addressline1 / addressline2 and addressText must be filled in one,length**: [1, 35], **English characters** |
| \|---addressLine2    |  String   |   no   |            street name             | **addressline1 / addressline2 and addressText must be filled in one,length**: [1, 35], **English characters** |
| \|---addressText |  String   |   no   |      additional address | **addressline1 / addressline2 and addressText must be filled in one,length**: [1, 1200], **English characters** |
| \|---phone           |  String   |   yes    |                                    |             **length**: [1, 22]              |
| \|---email           |  String   |    no    |                                    |             **length**: [1, 400]             |
| notifyPartyInfo          | Object  |      no      |   notify party info   |                                     |
| \|---name            |  String   |   yes    |                                    | **length**: [1, 70], **English characters**  |
| \|---addressLine1    |  String   |   no   | country / region / province / city | **addressline1 / addressline2 and addressText must be filled in one,length**: [1, 35], **English characters** |
| \|---addressLine2    |  String   |   no   |            street name             | **addressline1 / addressline2 and addressText must be filled in one,length**: [1, 35], **English characters** |
| \|---addressText |  String   |   no   |      additional address | **addressline1 / addressline2 and addressText must be filled in one,length**: [1, 1200], **English characters** |
| \|---phone           |  String   |   yes    |                                    |             **length**: [1, 22]              |
| \|---email           |  String   |    no    |                                    |             **length**: [1, 400]             |
| emergencyContactInfo          | Object  |      yes      |   emergency contact info   |                                     |
| \|---name            |  String   |   no   |                                    | **length**: [0, 60], **English characters** |
| \|---address   |  String   |   no   |       | **length**: [0, 245], **English characters** |
| \|---mobile      |  String   |   no   |                                    |             **length**: [0, 15]             |
| \|---phone  |  String   |   no   |                                    |             **length**: [0, 22]             |
| \|---email           |  String   |    yes    |                                    |             **length**: [1, 35]             |
| scheduleData          | Object  |      yes      |   schedule info   | Please copy the scheduled date information from the  Cross Booking Schedule Query Interface return result |
| cargoInfo          | Object  |      yes      |   cargo info   |                                     |
| \|---desc            |  String   |   yes    |             cargo name             | **length**: [1, 60], **English characters**  |
| \|---packageType     |  String   |   yes    |                                    |                                              |
| \|---quantity        |  Integer  |   yes    |                                    |                                              |
| \|---weight          |  Decimal  |   yes    |                                    |                 **unit**: KG                 |
| \|---volume          |  Decimal  |   yes    |                                    |           **unit**: m<sup>3</sup>            |
| \|---remarks         |  String   |    no    |                                    |            **length**: [0,1000]**,English characters**            |
| referenceNo | String | no | additional reference number |  |
| remarks          | String  |      no      |      |                                     |

### 3. Request sample

```java
{
    "inventoryId": "8a5e110e7494c338017494d22fc00009", // the inventory Id of the contract No.
    "contractNumber": "ATE20001",
    "cargoNature": "GC",
    "channelCode": "GENERAL",
    "bookingContainers": [
      {
        "cntrType": "40GP",
        "quantity": 1
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
    "bookingParties": {
      "name": "TEST",
      "addressLine1": "CN",
      "addressLine2": "Room 507, Huale building, Shennan E",
      "phone": "4564654234",
      "email": ""
    },
    "forwarderInfo": {
      "name": "forwarderName",
      "addressLine1": "Shenzhen",
      "addressLine2": "ZHUHAIXIANGZHOU",
      "phone": "+8618355556666",
      "email": ""
    },
    "shipperInfo": {
      "name": "shipper",
      "addressLine1": "Shenzhen",
      "addressLine2": "ZHUHAIXIANGZHOU",
      "phone": "+86112223311",
      "email": ""
    },
    "consigneeInfo": {
      "name": "consignee",
      "addressLine1": "SHANGHAI",
      "addressLine2": "SHANGHAIPUDONG",
      "phone": "+86112223311",
      "email": ""
    },
    "notifyPartyInfo": {
      "name": "notifyParty",
      "addressLine1": "SHANGHAI",
      "addressLine2": "SHANGHAIPUDONG",
      "phone": "+86112223311",
      "email": ""
    },
    "emergencyContactInfo": {
      "name": "",
      "email": "test@test.COM",
      "mobile": "",
      "phone": "",
      "address": ""
    },
    "cargoInfo": {
      "desc": "ASD",
      "packageType": "JL",
      "packageTypeName": "JUMBO ROLL",
      "quantity": 1,
      "weight": 324,
      "volume": 43,
      "remarks": ""
    },
    "remarks": "ASD"
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

Please refer to the [General Error Codes](#General Error Codes) .



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
                "weekNo": "202037",
                "createTime": "2020-09-29T08:12:06.759Z"
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

Please refer to the [General Error Codes](#General Error Codes) .



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
GET `/service/synconhub/crossBooking/shipment/4880701060`
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
