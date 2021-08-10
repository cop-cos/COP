# COSCO Syncon Hub API Specification for Long Term Booking

[toc]


## Update Logs

v2.0.0 (2021-08-07)

1. **`A `**  Added the API LongTerm Order interface. 
2. **`A `**  Added the API LongTerm Booking interface. 
3. **`A `**  Added the API LongTerm Order Query interface. 
4. **`A `**  Added the API LongTerm Order detail interface. 

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

## Long Term Order Interface
Customer can place booking throught this interface.  The interface will generate the order with the charge details of the product and related discounts. While customer can apply the coupons to deduct the ocean freight of the Product as well. The avaible counpons infomation can be obtain through the Sport Rate Detail interface. Please note that the minimum amount of the ocean freight for one single container after deduction shall be 1 US dollar. No change in coupon amount. For the details of the Booking Rules, please refer to the Syncon Hub online.

  ### 1. Informations

* **Path**: /service/synconhub/shipment/longTerm/order
* **Method**: POST

### 2. Request parameters

| Name                     |   Type    | Required |                        Description                        |                  **Remarks**                  |
| :----------------------- | :-------: | :------: | :-------------------------------------------------------: | :-------------------------------------------: |
| productId                |  string   |   yes    |                        product id                         |                                               |
| preferPaymentTerms       |  String   |   yes    |                 prefer payment terms type                 |              optional value: P,C              |
| paymentTermsOptions      | Object[]  |    no    |                charge payment terms option                |                                               |
| \|--- chargeType         |  String   |   yes    |                                                           |                                               |
| \|--- chargeName         |  String   |    no    |                                                           |                                               |
| \|--- paymentTerms       |  String   |   yes    |                                                           |              optional value: P,C              |
| loadingServiceNo         |  String   |    no    | intermodalServiceNo from intermodal service query results |                                               |
| dischargeServiceNo       |  String   |    no    | intermodalServiceNo from intermodal service query results |                                               |
| containerInfos           | object [] |   yes    |                      container info                       |                                               |
| \|---containerType       |  string   |   yes    |                                                           |      **optional value:** 20GP,40GP,40HQ       |
| \|---quantity            |  integer  |   yes    |                                                           |                  **min**: 1                   |
| \|---estimateWeight      |  decimal  |    no    |              estimate cargo weight per case               |                 **unit**: TON                 |
| blQuantity               |  integer  |    no    |                        bl quantity                        |          **default**: 1, **min**: 1           |

### 3. Request sample

```javascript
{
    "productId": "8a5e11157351b2df01735562622a0000",
    "loadingServiceNo":"8a5e112478e8ac470178e90d79cc0025",
    "dischargeServiceNo":"8a5e112478e8ac470178e8bbe0d90001",
    "preferPaymentTerms": "P",
    "paymentTermsOptions":[
      {
          "chargeType":"OCEAN_FEE",
          "chargeName":null,
          "paymentTerms":"C"
      },
      {
          "chargeType":"LOADING_TRANSFER",
          "chargeName":null,
          "paymentTerms":"C"
      },
      {
          "chargeType":"DISCHARGE_TRANSFER",
          "chargeName":null,
          "paymentTerms":"C"
      },
      {
          "chargeType":"THC",
          "chargeName":"THC",
          "paymentTerms":"C"
      }
    ],
    "containerInfos": [
        {
            "containerType": "20GP",
            "quantity": 1,
            "estimateWeight": 22
        }
    ],
    "blQuantity": 1,
    "couponsInfo": {
        "amount": 1,
        "couponId": "8a5e11227152917a017152b6049f0002"
    },
    "includeInsurance": true
}
```

### 4. Response parameters

| Name    | Description    |
| ------- | -------------- |
| code    | status code    |
| message | detail message |
| data    | response data  |

### 5. Response sample

```javascript
{ "code": 0,
    "message": "",
    "data": {
	  "orderNo": "ALT00144631"
    }
}
```

### 6. Error sample

```javascript
{
    "code": 20003,
    "message": "the inventory is not enough"
}
```

### 7. Error Code Specification

Please refer to the [General Error Codes](../SynConHubApiErrorCodeList.md) .



## Long Term Booking Interface

### 1. Informations

* **Path**: /service/synconhub/shipment/longTerm/booking
* **Method**: POST

### 2. Request parameters

| Name                     |   Type    | Required |                        Description                        |                  **Remarks**                  |
| :----------------------- | :-------: | :------: | :-------------------------------------------------------: | :-------------------------------------------: |
| orderNo                  |  string   |   yes    |                        product id                         |                                               |
| shipperInfo              |  object   |    no    |                       shipper info                        |                                               |
| \|---name                |  string   |   yes    |                                                           |  **length**: [1, 70], **English characters**  |
| \|---addressLine1        |  string   |   yes    |            country / region / province / city             |  **length**: [1, 35], **English characters**  |
| \|---addressLine2        |  string   |   yes    |                        street name                        |  **length**: [1, 35], **English characters**  |
| \|---addressText         |  string   |    no    |                    additional address                     | **length**: [1, 1200], **English characters** |
| \|---phone               |  string   |   yes    |                                                           |              **length**: [1, 22]              |
| \|---email               |  string   |    no    |                                                           |             **length**: [1, 400]              |
| \|---carrierCustomerCode |  string   |    no    |                   carrier customer code                   |                                               |
| consigneeInfo            |  object   |    no    |                      consignee info                       |                                               |
| \|---name                |  string   |   yes    |                                                           |  **length**: [1, 70], **English characters**  |
| \|---addressLine1        |  string   |   yes    |            country / region / province / city             |  **length**: [1, 35], **English characters**  |
| \|---addressLine2        |  string   |   yes    |                        street name                        |  **length**: [1, 35], **English characters**  |
| \|---addressText         |  string   |    no    |                    additional address                     | **length**: [1, 1200], **English characters** |
| \|---phone               |  string   |   yes    |                                                           |              **length**: [1, 22]              |
| \|---email               |  string   |    no    |                                                           |             **length**: [1, 400]              |
| \|---carrierCustomerCode |  string   |    no    |                   carrier customer code                   |                                               |
| notifyPartyInfo          |  object   |    no    |                     notify party info                     |                                               |
| \|---name                |  string   |   yes    |                                                           |  **length**: [1, 70], **English characters**  |
| \|---addressLine1        |  string   |   yes    |             country /region / province / city             |  **length**: [1, 35], **English characters**  |
| \|---addressLine2        |  string   |   yes    |                        street name                        |  **length**: [1, 35], **English characters**  |
| \|---addressText         |  string   |    no    |                    additional address                     | **length**: [1, 1200], **English characters** |
| \|---phone               |  string   |   yes    |                                                           |              **length**: [1, 22]              |
| \|---email               |  string   |    no    |                                                           |             **length**: [1, 400]              |
| emergencyContactInfo     |  object   |   yes    |                                                           |                                               |
| \|---name                |  string   |    no    |                                                           |  **length**: [1, 70], **English characters**  |
| \|---email               |  string   |   yes    |                                                           |             **length**: [1, 400]              |
| \|---mobile              |  string   |    no    |                                                           |              **length**: [1, 15]              |
| \|---phone               |  string   |    no    |                                                           |              **length**: [1, 22]              |
| \|---address             |  string   |    no    |                                                           | **length**: [1, 245], **English characters**  |
| cargoInfo                |  object   |   yes    |                        cargo info                         |                                               |
| \|---desc                |  string   |   yes    |                        cargo name                         |  **length**: [1, 60], **English characters**  |
| \|---packageType         |  string   |   yes    |                                                           |                                               |
| \|---quantity            |    int    |   yes    |                                                           |                                               |
| \|---weight              |  decimal  |   yes    |                                                           |                 **unit**: KG                  |
| \|---volume              |  decimal  |   yes    |                                                           |            **unit**: m<sup>3</sup>            |
| \|---remarks             |  string   |    no    |                                                           |            **English characters**             |
| remarks                  |  string   |    no    |                                                           | **length**: [0, 240], **English characters**  |


### 3. Request sample

```javascript
{
    "orderNo": "ALT00144631",
    "shipperInfo": {
        "name": "shipper name",
        "addressLine1": "CN",
        "addressLine2": "ren min lu",
        "phone": "12345678910",
        "email": "test@test.com",
        //if we are going to book cosplus product,we must pass sapid of bond here
        //For NVO customer the carrierCustomerCode is FMC sapId,For other customer the carrierCustomerCode is company sapId
        "carrierCustomerCode": "1234567890"  
    },
    "consigneeInfo": {
        "name": "consignee name",
        "addressLine1": "CN",
        "addressLine2": "jian she lu",
        "phone": "12345678910",
        "email": "test@test.com",
        //if we are going to book cosplus product,we must pass sapid of bond here
        //For NVO customer the carrierCustomerCode is FMC sapId,For other customer the carrierCustomerCode is company sapId
        "carrierCustomerCode": "1234567890"
    },
    "notifyPartyInfo": {
        "name": "notify party name",
        "addressLine1": "CN",
        "addressLine2": "jian she lu",
        "phone": "12345678910",
        "email": "test@test.com"
    },
    "emergencyContactInfo": {
        "name": "contacter name",
        "email": "test@test.com",
        "mobile": "123456",
        "phone": "12345678910",
        "address": "ren min lu"
    },
    "cargoInfo": {
        "desc": "desk",
        "packageType": "IV",
        "quantity": 1000,
        "weight": 1000.50,
        "volume": 1000.50,
        "remarks": "remarks..."
    },
    "remarks": "remarks..."
}
```

### 4. Response parameters

| Name    | Description    |
| ------- | -------------- |
| code    | status code    |
| message | detail message |
| data    | response data  |

### 5. Response sample

```javascript
{ "code": 0,
    "message": "",
    "data": {
	  "brNo": "6889904090"
    }
}
```

### 6. Error sample

```javascript
{
    "code": 20003,
    "message": "the inventory is not enough"
}
```

### 7. Error Code Specification

Please refer to the [General Error Codes](../SynConHubApiErrorCodeList.md) .



## Long Term Order Query Interface

Query brief information of eligible orders based on conditions

### 1. Informations


- **Path**: /service/synconhub/order/longTerm/search
- **Method**: POST

### 2. Request parameters

|      Name      |  Type   | Required |      Description       |              **Remarks**               |
| :------------: | :-----: | :------: | :--------------------: | :------------------------------------: |
|      brNo      | string  |    no    | booking request number |                                        |
|    orderNo     | string  |    no    |        order no        |                                        |
|  orderStatus   | string  |    no    |      order status      | **optional value:** CONFIRMED,CANCELED |
|      page      | integer |   yes    |       page size        |      **default:**1，**minimum:**1      |
|      size      | integer |   yes    |      record size       |   **default:**20, **range:**[1, 50]    |
| updateDateFrom | ISODate |    no    | Order update time from |                                        |
|  updateDateTo  | ISODate |    no    |  Order update time to  |                                        |

### 3. Request sample

```javascript
{
  "brNo": "6889903730",
  "orderNo": "ALT00144631",
  "orderStatus": "CONFIRMED",
  "page": 1,
  "size": 20,
  "updateDateFrom": "2021-08-07T03:14:55.200Z",
  "updateDateTo": "2021-08-08T03:14:55.200Z"
}
```

### 4. Response parameters

| Name    | Description          |
| ------- | -------------------- |
| code    | status code          |
| message | detail message       |
| data    | paging query results |

### 5. Response sample

```java
{
    "code": 0,
    "message": "",
    "data": {
        "content": [
            {
                "orderNo": "ALT00144631", // order number
                "vesselName": "COSCO YANTIAN",
                "voyageNo": "020", // voyage number
                "porCityName": "Shekou",
                "fndCityName": "Durban",
                "etd": "2020-08-04 00:00",
                "carrier": "集运", 
                "orderStatus": "CONFIRMED",
                "offlineCharges": {
                    "ORIGINAL": {
                        "onlineTotalAmount": 7443.37,
                        "onlinePaidAmount": 0,
                        "onlineUnpaidAmount": 7443.37, 
                        "minimumUnpaidAmount": 7443.37, // unpaid amount of down payment in installments
                        "balanceUnpaidAmount": 0.00,
                        "currencyType": "CNY",
                        "deadFreightAmount": 0,
                        "remissionAmount": 0
                    }
                },
                "effectiveStartDate": "2020-07-05T00:00:00.000Z",
                "effectiveEndDate": "2020-12-23T23:59:59.000Z",
                "orderUser": "testuser",
                "direction": "W",
                "brNoInfos": [ // booking number info
                    {
                        "isBrNoInternal": true, // is our platform booking request
                        "brNo": "6889903730" // booking request number
                    }
                ],
                "orderTime": "2020-07-06T08:57:05.460Z",
                "orderContainerSummaryDTOList": [ // container info
                    {
                        "cntrType": "20GP" , // container type
                        "avaCount": 1, // returnable container count
                        "validTotalCount": 1,
                        "bookedCount": 0
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

Please refer to the [General Error Codes](../SynConHubApiErrorCodeList.md) .



## Long Term Order Details Query Interface

Query order details by order number

### 1. Informations

- **Path**: /service/synconhub/order/longTerm/detail/{orderNo}
- **Method**: GET

### 2. Path parameters

|  Name   |  Type  | Required | Description  |
| :-----: | :----: | :------: | :----------: |
| orderNo | String |   yes    | order number |

### 3. Request sample

```javascript
GET /service/synconhub/order/detail/ALT00144631
```

### 4. Response parameters

| Name    | Description       |
| ------- | ----------------- |
| code    | status code       |
| message | detail message    |
| data    | order detail info |

### 5. Response sample

```javascript
{
    "code": 0,
    "message": "",
    "data": {
        "orderNo": "E00007231", // order number
        "orderStatus": "CONFIRMED",
        "orderTime": "2020-07-06T08:57:05.460Z",
        "offlineCharges": {
            "ORIGINAL":[ // charge type:[ORIGINAL，EXTRA_CHARGE，REFUND，WITHDRAW_COMMISSION]
                {
                    "currencyType": "CNY",
                    "charge": 1622.17, 
                    "deadFreightAmount": 0,
                    "remissionAmount": 0
                }
            ]
        }, 
        "sailingProduct": {...}, // product info，for the specific structure, please refer to "Spot Rate Query"
        "orderContainerSummaries": [ // container info
            {
                "cntrType": "20GP",
                "avaCount": 1, // returnable container 
                "validTotalCount": 2,
                "bookedCount": 1
            }
        ],
        "bookingContainers": [
            {
                "bookingNo": "6889903730", // booking number
                "status": "Confirmed", // status values : ['New','Confirmed','Cancelled']
                "cntrType": "20GP", // container type
                "count": 1
            }
        ],
       "transferServices": [
            {
                "cityId": "738872886233881",
                "cityName": "Singapore",
                "ctryCode": "SG",
                "cityFullNameEn": "Singapore",
                "cityFullNameCn": "新加坡",
                "transferType": "DISCHARGE",
                "transportMode": "RAIL",
                "transportTerms": "CY",
                "transitTime": 2,
                "facilityCode": null,
                "facilityName": null
            },
            {
                "cityId": "738872886232879",
                "cityName": "Ningbo",
                "ctryCode": "CN",
                "cityFullNameEn": "Ningbo",
                "cityFullNameCn": "宁波",
                "transferType": "LOADING",
                "transportMode": "RAIL",
                "transportTerms": "CY",
                "transitTime": 2,
                "facilityCode": null,
                "facilityName": null
            }
        ]
    }
}
```

### 6. Error sample

```javascript
{
    "code": 20006,
    "message": "The order does not exist"
}
```

### 7. Error Code Specification

Please refer to the [General Error Codes](../SynConHubApiErrorCodeList.md) .