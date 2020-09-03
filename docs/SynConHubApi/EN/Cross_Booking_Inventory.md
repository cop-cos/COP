# COSCO Syncon Hub API Specification for Cross Booking Inventory

[toc]



## Port Information Query Interface

Fuzzy query of the port information maintained by the platform based on the keywords of the port name

### 1. Informations

* **Path**: /service/synconhub/common/port/search
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

## Cross Booking Inventory Query Interface

### 1. Informations

* **Path**: "/service/synconhub/crossBooking/inventory/search"
* **Method**：POST

### 2. Request parameters

| Name           |  Type   | **Required** |   Description   |             **Remarks**             |
| :------------- | :-----: | :----------: | :-------------: | :---------------------------------: |
| contractNumber | String  |     yes      | contract number |                                     |
| dateFrom       | ISODate |     yes      |                 |                                     |
| dateTo         | ISODate |      no      |                 |                                     |
| service        | String  |      no      |  service code   |                                     |
| polId          | String  |      no      |   pol port id   |                                     |
| page           | Integer |      no      |   page number   |      **default:** 1,**min:** 1      |
| size           | Integer |      no      |    page size    | **default:** 20, **range:** [1, 50] |

### 3. Request sample

```java
{
    "contractNumber": "ATE20001",
    "dateFrom": "2020-07-20T00:00:00.000Z",
    "dateTo": "2020-08-20T00:00:00.000Z",
    "service": "SEA",
    "podId": "349645770723388",
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
                "weekNo": 202033, // week number
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
