# 签收单链接查询 #

按订单号、箱号查询，返回签收单文件链接信息。支持多订单号一次性查询（最多100条）。


# 1. 根据订单号查询 #

## 1.1 地址和方法 ##

* **相对URL**："/dts/cargoReceipt/link"

* **方法**：POST

## 1.2 请求参数 ##
参数名 | 类型 | 必填 | 含义 
-----|-----|-----|-----
wblNum | string | 是 | 订单号 
cntrNums | array | 否 | 箱号 

## 1.3 请求样例 ##
```
Request Payload:

[ //array,一次查询多组订单箱号组合
    {
        "wblNum": "Q000017440", //string, 订单号
        "cntrNums": [ // array，箱号，空代表所有箱子
            "CCLU6976054"
        ]
    }
]
```

## 1.4 响应参数 ##

字段名 |含义
-----|-----
code | 状态码
success | 成功标志
message | 提示信息
data | 签收单信息

## 1.5 响应字段说明 ##
```
{
    "code": 0,
    "success": true,
    "message": null,
    "data": [ //array,签收单信息
        {
            "wblNum": "Q000017440", //string, 订单号
            "cntrs": [ // array，箱子
                {
                    "cntrNum": "CCLU6976054", //string, 箱号
                    "cargoReceiptLinks": [ //array, 签收单链接，每个箱子多个签收单图片
                        "http://ABC...",
                        "http://XYZ..."
                    ]
                }
            ]
        }
    ]
}
```
## 1.6 异常示例 ##


```
{
    "code": 17000,
    "success": false,
    "message": "系统异常"
}
```
