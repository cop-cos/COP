# COSCO Syncon Hub API Specification for SME Booking

[toc]


## Update Logs

v1.0.0 (2022-03-14)
1. **`A `**  Added the API - Booking Interface. [Details](#Booking Interface)
2. **`A `**  Added the API - Order Query Interface. [Details](#Order Query Interface)
3. **`A `**  Added the API - Order Details Query Interface. [Details](#Order Details Query Interface)



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

  


## Booking Interface

Customer can place booking through this Interface.  The Interface will generate the order with the charge details of the product and related discounts. While customer can apply the coupons to deduct the ocean freight of the Product as well. The available coupons information can be obtain through the Sport Rate Detail Interface. Please note that the minimum amount of the ocean freight for one single container after deduction shall be 1 US dollar. No change in coupon amount. For the details of the Booking Rules, please refer to the SynconHub online.



### 1. Information

* **Path**: /service/synconhub/shipment/sme/booking
* **Method**: POST

### 2. Request parameters

| Name                     |   Type   | Required |                         Description                          |                  **Remarks**                  |
| :----------------------- | :------: | :------: | :----------------------------------------------------------: | :-------------------------------------------: |
| productId                |  String  |   yes    |                          product id                          |                                               |
| preferPaymentTerms       |  String  |   yes    |                  prefer payment terms type                   |              optional value: P,C              |
| specificPaymentTerms     | Object[] |    no    |                 charge payment terms options                 |                                               |
| \|--- chargeType         |  String  |   yes    | chargeType from surcharge query Interface. For ocean charges, chargeType is 'OCEAN_FEE', loadingService is 'LOADING_TRANSFER', dischargeService is 'DISCHARGE_TRANSFER', porTrailerService is 'POR_TRAILER' and fndTrailerService is 'FND_TRAILER' |                                               |
| \|--- chargeName         |  String  |    no    | chargeName from surcharge query Interface and chargeName is not required for ocean fee, Intermodal services and trailer services |                                               |
| \|--- paymentTerms       |  String  |   yes    |                                                              |              optional value: P,C              |
| loadingServiceNo         |  String  |    no    |  IntermodalServiceNo from Intermodal service query results   |                                               |
| dischargeServiceNo       |  String  |    no    |  IntermodalServiceNo from Intermodal service query results   |                                               |
| porTrailerService        |  Object  |    no    |                     POR trailer service                      |                                               |
| \|--- doorId             |  String  |   yes    | trailer door id, get from the result of the trailer door query interface. |                                               |
| \|--- doorName           |  String  |   yes    |                      trailer door name                       |                                               |
| \|--- doorAddress        |  String  |   yes    |                     trailer door address                     |                                               |
| \|--- contactName        |  String  |   yes    |                         contact name                         |                                               |
| \|--- contactTel         |  String  |   yes    |                        contact number                        |                                               |
| \|--- loadingTime        | ISODate  |   yes    |                      loading time(GMT)                       |          loading time > current time          |
| \|--- remarks            |  String  |    no    |                                                              |                                               |
| fndTrailerService        |  Object  |    no    |                     FND trailer service                      |                                               |
| \|--- doorId             |  String  |   yes    | trailer door id, get from the result of the trailer door query interface. |                                               |
| \|--- doorName           |  String  |   yes    |                      trailer door name                       |                                               |
| \|--- doorAddress        |  String  |   yes    |                     trailer door address                     |                                               |
| \|--- contactName        |  String  |   yes    |                         contact name                         |                                               |
| \|--- contactTel         |  String  |   yes    |                        contact number                        |                                               |
| \|--- loadingTime        | ISODate  |   yes    |                      loading time(GMT)                       |          loading time > current time          |
| \|--- remarks            |  String  |    no    |                                                              |                                               |
| containerInfos           | Object[] |   yes    |                        container info                        |                                               |
| \|---containerType       |  String  |   yes    |                                                              |      **optional value:** 20GP,40GP,40HQ       |
| \|---quantity            | Integer  |   yes    |                                                              |                  **min**: 1                   |
| \|---estimateWeight      | Decimal  |    no    |                estimate cargo weight per case                |                 **unit**: TON                 |
| blQuantity               | Integer  |    no    |                         bl quantity                          |          **default**: 1, **min**: 1           |
| couponsInfo              |  Object  |    no    |                       coupon use info                        |                                               |
| \|---amount              | Integer  |   yes    |                                                              |                  **min**: 1                   |
| \|---couponId            |  String  |   yes    |                                                              |                                               |
| includeInsurance         | Boolean  |    no    |                  whether include insurance                   |              **default**: false               |
| shipperInfo              |  Object  |    no    |                         shipper info                         |                                               |
| \|---name                |  String  |   yes    |                                                              |  **length**: [1, 70], **English characters**  |
| \|---addressLine1        |  String  |   yes    |              country / region / province / city              |  **length**: [1, 35], **English characters**  |
| \|---addressLine2        |  String  |   yes    |                         street name                          |  **length**: [1, 35], **English characters**  |
| \|---addressText         |  String  |    no    |                      additional address                      | **length**: [1, 1200], **English characters** |
| \|---phone               |  String  |   yes    |                                                              |              **length**: [1, 22]              |
| \|---email               |  String  |    no    |                                                              |             **length**: [1, 400]              |
| \|---carrierCustomerCode |  String  |    no    |                    carrier customer code                     |                                               |
| consigneeInfo            |  Object  |    no    |                        consignee info                        |                                               |
| \|---name                |  String  |   yes    |                                                              |  **length**: [1, 70], **English characters**  |
| \|---addressLine1        |  String  |   yes    |              country / region / province / city              |  **length**: [1, 35], **English characters**  |
| \|---addressLine2        |  String  |   yes    |                         street name                          |  **length**: [1, 35], **English characters**  |
| \|---addressText         |  String  |    no    |                      additional address                      | **length**: [1, 1200], **English characters** |
| \|---phone               |  String  |   yes    |                                                              |              **length**: [1, 22]              |
| \|---email               |  String  |    no    |                                                              |             **length**: [1, 400]              |
| \|---carrierCustomerCode |  String  |    no    |                    carrier customer code                     |                                               |
| notifyPartyInfo          |  Object  |    no    |                      notify party info                       |                                               |
| \|---name                |  String  |   yes    |                                                              |  **length**: [1, 70], **English characters**  |
| \|---addressLine1        |  String  |   yes    |              country /region / province / city               |  **length**: [1, 35], **English characters**  |
| \|---addressLine2        |  String  |   yes    |                         street name                          |  **length**: [1, 35], **English characters**  |
| \|---addressText         |  String  |    no    |                      additional address                      | **length**: [1, 1200], **English characters** |
| \|---phone               |  String  |   yes    |                                                              |              **length**: [1, 22]              |
| \|---email               |  String  |    no    |                                                              |             **length**: [1, 400]              |
| emergencyContactInfo     |  Object  |   yes    |                                                              |                                               |
| \|---name                |  String  |    no    |                                                              |  **length**: [1, 70], **English characters**  |
| \|---email               |  String  |   yes    |                                                              |             **length**: [1, 400]              |
| \|---mobile              |  String  |    no    |                                                              |              **length**: [1, 15]              |
| \|---phone               |  String  |    no    |                                                              |              **length**: [1, 22]              |
| \|---address             |  String  |    no    |                                                              | **length**: [1, 245], **English characters**  |
| cargoInfo                |  Object  |   yes    |                          cargo info                          |                                               |
| \|---desc                |  String  |   yes    |                          cargo name                          |  **length**: [1, 60], **English characters**  |
| \|---packageType         |  String  |   yes    |                                                              |                                               |
| \|---quantity            | Integer  |   yes    |                                                              |                                               |
| \|---weight              | Decimal  |   yes    |                                                              |                 **unit**: KG                  |
| \|---volume              | Decimal  |   yes    |                                                              |            **unit**: m<sup>3</sup>            |
| \|---remarks             |  String  |    no    |                                                              |            **English characters**             |
| remarks                  |  String  |    no    |                                                              | **length**: [0, 240], **English characters**  |


### 3. Request sample

```javascript
{
    "productId": "8aaa97667f6dbe37017f71d618e303d4",
    "loadingServiceNo":"8a5e112478e8ac470178e90d79cc0025",
    "dischargeServiceNo":"8a5e112478e8ac470178e8bbe0d90001",
    "porTrailerService": {
        "doorId": "8aaad5377f05a93f017f0869b9ef001a",
        "doorName": "test",
        "doorAddress": "test",
        "loadingTime": "2022-03-15T16:00:00.000Z",
        "contactName": "test",
        "contactTel": "123456",
        "remarks": "remarks"
    },
    "fndTrailerService": {
        "doorId": "8aaa81bd7f3fb616017f410fa9cf0250",
        "doorName": "test",
        "doorAddress": "test",
        "loadingTime": "2022-03-15T16:00:00.000Z",
        "contactName": "test",
        "contactTel": "123456",
        "remarks": "remarks"
    },
    "preferPaymentTerms": "P",
    "specificPaymentTerms":[
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
          "chargeType":"POR_TRAILER",
          "chargeName":null,
          "paymentTerms":"C"
      }, 
      {
          "chargeType":"FND_TRAILER",
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
    "includeInsurance": true,
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
{ 
    "code": 0,
    "message": "",
    "data": {
	  "orderNo": "AS00366039",
	  "brNo": "4880422750"
    }
}
```

### 6. Error sample

```javascript
{
    "code": 20003,
    "message": "the inventory is not enough"
}

{
    "code": 20073,
    "message": "Charge type: XXX, charge name: XXX cannot be set to collect"
}
```

### 7. Error Code Specification

Please refer to the [General Error Codes](#General Error Codes) .



## Order Query Interface

Query brief information of eligible orders based on conditions

### 1. Information


- **Path**: /service/synconhub/order/sme/search
- **Method**: POST

### 2. Request parameters

|      Name      |  Type   | Required |         Description         |              **Remarks**               |
| :------------: | :-----: | :------: | :-------------------------: | :------------------------------------: |
|      brNo      | String  |    no    |   booking request number    |                                        |
|    orderNo     | String  |    no    |          order no           |                                        |
|  orderStatus   | String  |    no    |        order status         | **optional value:** CONFIRMED,CANCELED |
|      page      | Integer |   yes    |          page size          |      **default:**1，**minimum:**1      |
|      size      | Integer |   yes    |         record size         |   **default:**20, **range:**[1, 30]    |
| updateDateFrom | ISODate |    no    | Order update time from(GMT) |                                        |
|  updateDateTo  | ISODate |    no    |  Order update time to(GMT)  |                                        |

### 3. Request sample

```javascript
{
    "brNo": "6889903730",
    "orderNo": "AS00007231",
    "orderStatus": "CONFIRMED",
    "page": 1,
    "size": 20,
    "updateDateFrom": "2020-07-20T03:14:55.200Z",
    "updateDateTo": "2020-07-25T03:14:55.200Z"
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
                "orderNo": "AS00007231", // order number
                "vesselName": "COSCO YANTIAN",
                "voyageNo": "020", // voyage number
                "porCityName": "Shekou",
                "fndCityName": "Durban",
                "etd": "2020-08-04 00:00",
                "carrier": "集运", 
                "orderStatus": "CONFIRMED",
                "offlineCharges": {
                    "ORIGINAL": [
                        {
                            "currencyType": "CNY",
                            "charge": 2257,
                            "deadFreightAmount": 0,
                            "remissionAmount": 0
                        },
                        {
                            "currencyType": "EUR",
                            "charge": 40,
                            "deadFreightAmount": 0,
                            "remissionAmount": 0
                        },
                        {
                            "currencyType": "USD",
                            "charge": 1071,
                            "deadFreightAmount": 0,
                            "remissionAmount": 0
                        }
                    ]
                },
                "onlineCharges": {
                    "ORIGINAL": {
                        "onlineTotalAmount": 0,
                        "onlinePaidAmount": 0,
                        "onlineUnpaidAmount": 0, 
                        "minimumUnpaidAmount": 0, // unpaid amount of down payment in installments
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

Please refer to the [General Error Codes](#General Error Codes) .



## Order Details Query Interface

Query order details by order number

### 1. Information

- **Path**: /service/synconhub/order/detail/{orderNo}
- **Method**: GET

### 2. Path parameters

|  Name   |  Type  | Required | Description  |
| :-----: | :----: | :------: | :----------: |
| orderNo | String |   yes    | order number |

### 3. Request sample

```javascript
GET /service/synconhub/order/detail/AS00144631
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
        "orderNo": "AS00144631", // order number
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
        "sailingProduct": {...}, // product info，for the specific structure, please refer to "Spot Rate Query Interface V2"
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
        ],
        "trailerServices": [
            {
                "transferType": "LOADING",	// LOADING: POR trailer service, DISCHARGE: FND trailer service
                "doorAreaCode": "NGB05",
                "doorArea": "临平, 余杭, 杭州, 浙江, 中国",
                "doorName": "test",
                "doorAddress": "test",
                "loadingTime": "2020-08-15T16:00:00.000Z",
                "contactName": "test",
                "contactTel": "123456",
                "remarks": "remarks"
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
 - 20062: "Wrong import and export type of Intermodal service"
 - 20063: "The Intermodal service does not exist"
 - 20064: "Can't find the city"
 - 20066: "Some container types do not support Intermodal service"
 - 20067: "The Intermodal service fee is charged by weight, and the estimated cargo weight of the box type is required"
 - 20068: "The estimated weight of the container is not in the optional range, please re-order"
 - 20070: "CosPlus | Shipper or consignee of shipping related party filled in the product booking is illegal."
 - 20072: "API cannot purchase this type of product"
 - 20073: "Charge type :xxx, charge name: xxx cannot be set to collect"
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