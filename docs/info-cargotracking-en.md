# Cargo Tracking #

Cargo tracking is the most commonly used function in the field of shipping logistics, providing all kinds of customers with the latest information of container transportation.

# 1. Search By blNo #

## 1.1 URL And Method ## 

* **Relative URL**："/info/tracking/**#YOUR_BL_NO**?numberType=bl"

* **Method**：GET

## 1.2 Request Parameters ##

* **#YOUR_BL_NO**:B/L NO(Bill of Lading Number)

## 1.3 Request Example ##

/info/tracking/6103622780?numberType=bl

## 1.4 Response Parameters ##

FieldName | Meaning
-----|-----
type | blNo type of response ：bl-bill of lading number, bkg-booking number,cntr-container number,none-none of the above 
numberType |Type of blNo: bl-bill of lading number, bkg-booking number,cntr-container number,both-bill of lading number or booking number，none-none of above  
content | The data of response

## 1.5 Response Example ##
```
{
    "code": 0,
    "message": "success",
    "data": {
        "type": "bl", //string, blNo type of response: bl:bill of lading number/bkg:booking number/cntr:container number/none:none of the above
        "numberType": "both", //Type of blNo number: bl:bill of lading number/bkg:booking number/cntr:container number/both:bill of lading number or booking number/none:none of above
        "content": {
            "itNumber": "V2167699827", //string, Inland Transporter of USA
            "actualShipment": [ //array, Real-time Schedule
                {
                    "rownum": "1", //string, Line Number
                    "sequenceNumber": "1",
                    "vesselName": "COSCO PHILIPPINES", //string, Shippment Name
                    "voyageNo": "039E", //string, Voyage or Flight Number
                    "portOfLoading": "Qingdao",  //string, Landing Port
                    "actualDepartureDate": "2014-12-29 11:00", //string, Actual Time of Departure
                    "expectedDateOfDeparture": "2014-12-29 17:00", //string, Estimated Time of Departure
                    "portOfDischarge": "Long Beach", //string, Discharging Port
                    "actualArrivalDate": "2015-01-27 05:24", //string, Actual Time of Arrival
                    "estimatedDateOfArrival": "2015-01-27 06:00", //string, Estimated Time of Arrival 
                    "actualShippingDate": "2014-12-29 07:59", //string, Actual Time of Shipment
                    "city": "Long Beach,United States", //string, City
                    "service": "CEN", //string, Ship Route
                    "actualDischargeDate": "2015-01-29 10:14", //string, Commencement of Discharging Time
                    "vesselCde": "T83" //string, Vessel Code
                }
            ],
            "trackingPath": { //object, Booking Information
                "billOfladingRefCode": "6103622780", //string, Booking Number
                "trackingGroupReferenceCode": "6103622780", //string, Bl number
                "fromCity": "Qingdao ,Shandong ,China", //string, Place of Receipt
                "toCity": "Dallas ,Texas ,United States", //string, Destination
                "cgoAvailTm": "2015-02-04 06:15", //string, Estimated Time of Arrival to Take Delivery
                "cgoFinalTm": null,
                "vslNme": "COSCO PHILIPPINES", //string, Vessel Name
                "voyNumber": "039E", //string, Voyage or Flight Number 
                "pol": "Qingdao - QQCTU Qingdao Qianwan United Ctn", //string, Loading Port
                "pod": "Long Beach - Pacific Container Terminal", //string, Discharging Port
                "flag": null,
                "svvd": "CEN T83039E",
                "blType": "Sea WayBill",
                "blRealStatus": "Sea Waybill"
            },
            "containerStatus": [ //array, Latest Developments of Containers
                {
                    "label": "Empty Equipment Returned", //string, Label
                    "containers": [ //array, Container Information
                        {
                            "containerUuid": "1", //string, uuid
                            "containerNumber": "CBHU4398907", //string, Container Number
                            "containerType": "20GP", //string, Box Size
                            "grossWeight": "17720KG", //string, Net Weight 
                            "piecesNumber": 80, //string, Number of Pieces
                            "label": "Empty Equipment Returned", //string, Label Name
                            "sealNumber": "2", //string, Seal Number
                            "location": "Equipment Storage Services,Dallas,Texas,United States", //string, Current Location
                            "locationDateTime": "2015-02-06 09:35", //string, Date
                            "transportation": "Truck", //string, Mode of Transportation
                            "flag": "2",
                            "railRef": null,
                            "inlandMvId": " ",
                            "containerLocation": "0",
                            "isShow": false, //boolean, value is true, hsCode is shown; value is false, hsCode is not shown
                            "hsCode": [],
                            "isNorthAmericaRails": false //boolean, North American railway or not
                        }
                    ]
                }
            ],
            "doc_ext": [ //array, Extend field
                {
                    "key": "美国内陆转运号", //string, Field label
                    "value": "V2167699827" //string, The field values
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
## 1.6 Exception Example ##
No information is found
```
{
	"code": 0,
	"message": "success",
	"data": {
		"type": "none",
		"numberType": "none",
		"content": null
	}
}

```
## 1.7 Explanation of Error Code ##
Error Code | Explanation
-----|-----
5400|Parameter is Error
5415|Mobile Device Information Saving Failed
5500|Error

# 2. Search by Booking number #

## 2.1 URL And Method ## 

* **URL**："/info/tracking/**#YOUR_BKG_NO**?numberType=bkg"

* **Method**：GET

## 2.2 Request Parameters ##

* **#YOUR_BKG_NO**:Booking Number

## 2.3 Request Example ##

/info/tracking/6103622780?type=bkg

## 2.4 Response Parameters ##

FieldName | Meaning
-----|-----
type | blNo type of response ：bl-bill of lading number, bkg-booking number,cntr-container number,none-none of the above 
numberType | Type of blNo: bl-bill of lading number, bkg-booking number,cntr-container number,both-bill of lading number or booking number，none-none of above
content | The data of response

## 2.5 Response Example ##
```
{
    "code": 0,
    "message": "success",
    "data": {
        "type": "bkg", //string, blNo type of response, bl:bill of lading number/bkg:booking number/cntr:container number/none:none of the above
        "numberType": "both", //Type of blNo, Type of blNo number: bl:bill of lading number/bkg:booking number/cntr:container number
        "content": {
            "itNumber": "V2167699827", //string, Inland Transporter of USA
            "actualShipment": [ //array, Real-time Schedule
                {
                    "rownum": "1", //string, Line Number
                    "sequenceNumber": "1",
                    "vesselName": "COSCO PHILIPPINES", //string, Shippment Name
                    "voyageNo": "039E", //string, Voyage or Flight Number
                    "portOfLoading": "Qingdao",  //string, Landing Port
                    "actualDepartureDate": "2014-12-29 11:00", //string, Actual Time of Departure
                    "expectedDateOfDeparture": "2014-12-29 17:00", //string, Estimated Time of Departure
                    "portOfDischarge": "Long Beach", //string, Discharging Port
                    "actualArrivalDate": "2015-01-27 05:24", //string, Actual Time of Arrival
                    "estimatedDateOfArrival": "2015-01-27 06:00", //string, Estimated Time of Arrival
                    "actualShippingDate": "2014-12-29 07:59", //string, Actual Time of Shipment
                    "city": "Long Beach,United States", //string, City
                    "service": "CEN", //string, Ship Route
                    "actualDischargeDate": "2015-01-29 10:14", //string, Commencement of Discharging Time
                    "vesselCde": "T83" //string, Vessel Code
                }
            ],
            "trackingPath": { //object, Booking Information
                "billOfladingRefCode": "6103622780", //string, Booking Number
                "trackingGroupReferenceCode": "6103622780", //string, Bl number
                "fromCity": "Qingdao ,Shandong ,China", //string, Place of Receipt
                "toCity": "Dallas ,Texas ,United States", //string, Destination
                "cgoAvailTm": "2015-02-04 06:15", //string, Estimated Time of Arrival to Take Delivery
                "cgoFinalTm": null,
                "vslNme": "COSCO PHILIPPINES", //string, Vessel Name
                "voyNumber": "039E", //string, Voyage or Flight Number
                "pol": "Qingdao - QQCTU Qingdao Qianwan United Ctn", //string, Loading Port
                "pod": "Long Beach - Pacific Container Terminal", //string, Discharging Port
                "flag": null,
                "svvd": "CEN T83039E",
                "blType": "Sea WayBill",
                "blRealStatus": "Sea Waybill"
            },
            "containerStatus": [ //array, Latest Developments of Containers
                {
                    "label": "Empty Equipment Returned", //string, Label
                    "containers": [ //array, Container Information
                        {
                            "containerUuid": "1", //string, uuid
                            "containerNumber": "CBHU4398907", //string, Container Number
                            "containerType": "20GP", //string, Box Size
                            "grossWeight": "17720KG", //string, Net Weight
                            "piecesNumber": 80, //string, Number of Pieces
                            "label": "Empty Equipment Returned", //string, Label Name
                            "sealNumber": "2", //string, Seal Number
                            "location": "Equipment Storage Services,Dallas,Texas,United States", //string, Current Location
                            "locationDateTime": "2015-02-06 09:35", //string, Date
                            "transportation": "Truck", //string, Mode of Transportation
                            "flag": "2",
                            "railRef": null,
                            "inlandMvId": " ",
                            "containerLocation": "0",
                            "isShow": false, //boolean, value is true, hsCode is shown; value is false, hsCode is not shown
                            "hsCode": [],
                            "isNorthAmericaRails": false //boolean, North American railway or not
                        }
                    ]
                }
            ],
            "doc_ext": [ //array, Extend field
                {
                    "key": "美国内陆转运号", //string, Field label
                    "value": "V2167699827" //string, The field values
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

## 2.6 Exception Example ##

No information is found
```
{
	"code": 0,
	"message": "success",
	"data": {
		"type": "none",
		"numberType": "none",
		"content": null
	}
}
```
## 2.7 Explanation of Error Code ##
Error Code | Explanation
-----|-----
5400|Parameter is Error
5415|Mobile Device Information Saving Failed
5500|Error


# 3. Search By Container Number #

## 3.1 URL And Method ## 

* **URL**："/info/tracking/**#YOUR_CNTR_NO**?numberType=cntr"

* **Method**：GET

## 3.2 Request Parameters ##

* **#YOUR_CNTR_NO**:Container Number

## 3.3 Request Example ##

/info/tracking/CBHU4398907?numberType=cntr

## 3.4 Response Parameters ##

FieldName | Meaning
-----|-----
type | blNo type of response ：bl-bill of lading number, bkg-booking number,cntr-container number,none-none of the above  
numberType |Type of blNo: bl-bill of lading number, bkg-booking number,cntr-container number,both-bill of lading number or booking number，none-none of above 
content | The data of response

## 3.5 Response Example ##
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
          "containerCircleStatus": [ //array, Container Dynamic
            {
              "uuid": "CBHU4398907", //string, uuid
              "containerNumber": "CBHU4398907", //string, Container Number
              "containerNumberStatus": "Discharged at Last POD", //string, Lateset Dynamics
              "location": "Aisa World Port Terminal,Yangon,Yangon,Myanmar", //string, Location
              "timeOfIssue": "2015-10-25 17:00", //string, Time
              "transportation": " " //string, Mode of Transportation
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
          "container": { //object, Containers Information
            "containerUuid": "CBHU4398907", //string, uuid
            "containerNumber": "CBHU4398907", //string, Container Number
            "containerType": "20GP", //string, Box Size
            "grossWeight": "20000KG", //string, Net Weight
            "piecesNumber": 20, //string, Number of Pieces
            "label": "Discharged at Last POD", //string, Label Name
            "sealNumber": "K31436", //string, Seal Number
            "location": "Aisa World Port Terminal,Yangon,Yangon,Myanmar", //string, Current Location
            "locationDateTime": "2015-10-25 17:00:00", //string, Latest Dynamic Time
            "transportation": " ", //string, Mode of Transportation
            "flag": null,
            "railRef": null,
            "inlandMvId": null,
            "containerLocation": null,
            "isShow": false,
            "hsCode": [],
            "isNorthAmericaRails": false //boolean, North American railway or not
          },
          "containerHistorys": [ //array, Container dynamic history
            {
              "uuid": "hisCBHU439890711", //string, uuid
              "containerNumber": "CBHU4398907", //string, Container Number
              "containerNumberStatus": "Empty Equipment Returned", //string, Latest Dynamic
              "location": "Northwest Container Services Inc.,Portland,Oregon,United States", //string, Location
              "timeOfIssue": "2015-06-02 14:25", //string, Time
              "transportation": "Truck" //string, Mode of Transportation
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
## 3.6 Exception Example ##
No information is found
```
{
	"code": 0,
	"message": "success",
	"data": {
		"type": "none",
		"numberType": "none",
		"content": null
	}
}
```
## 3.7 Explanation of Error Code ##
Error Code | Explanation
-----|-----
5400|Parameter is Error
5415|Mobile Device Information Saving Failed
5500|Error
