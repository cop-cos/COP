## API Doc Review

- [x] pagable

        "number": 1,
        "size": 30,
        "totalElements": 1,
        "last": true, // is last page
        "totalPages": 1,
        "first": true, // is first page
        "empty": false

- Spot Rate

  - [x] "couponInfos": null, // (Only value when searching for product details)
  - [x] "insuredRule": null, // (Only value when searching for product details)

  - [x] tradePrice 

  - 精简

    - [x] 运价details array - sailingProductPricingDetailList?
    - [x] companyDiscountsInfoDTO.sourceType

    ​	

- SpotRate Detail

  - [x] sailingProductPricingDetailList
  - [x] tradePrice, sample的data price?
  
- surchargeDetails

  - [x] add chargeCode
  
- order / orderDetail

    - [x] normal status sample 'CONFIRMED'
    
      •删除节点: 
    
      - [x] orderReviewType
      - [x] paidCharge
      - [x] paymentType
      - [x] unpaidExtraCharge
    
      
    
      •修改节点：
    
      - [x] 节点orderStatus的description改成“CONFIRMED”
      - [x] 节点accountType，将参数名改为carrier



- CrossBooking  MD
  - [x] pagable
  - [x] inventory





Ø **确定库存扣减时间点**

 

“API下单，Syncon Hub 生产订单，扣减库存，返回订单号给外部系统。” ？？？

需求是根据BR扣库存。对于API直连客户没有线上支付环节，已经没有资金压力。如果API下单即扣库存的话，API客户在抢单上又拥有了绝对的速度优势，这或将造成客户在没有实单的情况下，对平台库存的虚扣。

根据BR扣库存，客户的BR可以通过EDI发送至平台（不直接发给IR4），亦可以通过API参数生成，意味着通过API直连下单，客户不需要有EDI发送BR的能力作为前提。

 

Ø **增加亏舱费的计收**

建议增加亏舱费计收的说明。API直连客户应同平台交易客户一样，同样受亏舱费（履约保证金）的约束。请参订舱规则“订单生效后非因船公司原因取消订舱或货物不再出运，履约保证金不予退还。”

 

Ø **增加****Coupon 使用说明**

获悉与环世直连中已经测试过优惠券的使用，建议标准功能文档中增加 Coupon 使用说明。

 

Ø **精简给客户返的参数**

Placing orders interface中“paid charge””payment type” ,建议删除，因为不存在支付环节。

Placing orders interface中“update time”,建议删除，因为通过API目前无法实现订单更改。

Spot rate API 中“ Trade group code ”实际上是用不上的。应为当porCityId 和fndCityId 作为两个必填项时，实际上是search by portpair。在这种情况下, 是无法search by tradeline ， 除非porCityId 和fndCityId 不作为两个必填项。



TODO: 

- order query , delete in doc:

        "onlineTotalCharge": 7443.37,
        "unpaidCharge": 7443.37, 
        "paidDeadFreight": 0,
        "unpaidDeadFreight": 0,
        "paidExtraCharge": 0,
    
- remove 

    -                 "updateTime": "2020-07-07T09:00:01.692Z", 
                      "bookable": true,
    - paymentStatus
    - orderTransferResult




## Implementation



Pageable

- 页码统一*
- 精简字段
- returningCount remove



Q

1. 订单查询, 只返回API渠道的单? 还是要包含全部线上的单?
2. 

