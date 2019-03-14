# 0x01 COP #

**Cosco shipping lines Open-api Platform**：目前处于**试运行**！！！试运行期间提供免费的数据服务。

中远海运集运的**Open API**主要基于集装箱运输业务，向供应链上下游、前后端延伸，一方面服务于传统运输客户，为行业客户定制信息解决方案，深化和客户的信息合作，增强服务黏性；另一方面通过建立丰富完善的全方位供应链和电子商务API体系，乃至允许第三方（独立开发者、行业解决方案供应商、客户）基于我司的API体系进行定制开发，推动物流信息平台的生态建设。

## COP客户应用 ##
为保证用户数据的安全与隐私，COP的客户应用("Application"或称"Consumer")需要经过一定业务申请和审核流程，取得授权后才能接入至COP平台。每个application将被分配一组apiKey和secretKey作为application的识别凭证，开发者务必妥善保存apiKey和secretKey，生产正式环境中的apiKey和secretKey将作为COP客户应用的**唯一凭证**。



# 0x02 对外开放的API服务体系 #
根据对外API需求和模式的不同，其总体技术亦有所区别。对外API模式分为两类：

## 基于HTTP(S)协议 ##

服务于同步调用和异步调用；
![标准/定制同步API](https://github.com/cop-cos/COP/blob/master/docs/images/overview_001.png)

![标准异步API](https://github.com/cop-cos/COP/blob/master/docs/images/overview_002.png)

## 基于MQ协议 ##

服务于异步调用，仅适用于深度定制的应用场景，对于MQ的安全管理、端到端的MQ协议网络等存在要求；
![定制异步API](https://github.com/cop-cos/COP/blob/master/docs/images/overview_003.png)



# 0x03 运行环境说明 #

|调用环境类别|服务地址(HTTPS)|
|---| :---: |
|生产正式环境|https://api.lines.coscoshipping.com/service|
|测试环境|**待定**|

    注:后续所有API清单中的URL均是指相对于**服务地址**的路径。 

## 生产正式环境 ##

中远海运集运COP生产正式环境是指中远海运集运COP平台提供给真实的客户、合作方和独立软件开发商的正式生产运行的环境。其中的数据均为真实数据，生产正式环境的apiKey和secretKey是客户应用的唯一凭证，需要妥善保管，客户应用对其在COP平台的行为和数据负有法律责任。

## 测试环境 ##

TBD.



# 0x04 接入和审核

## 入驻成为开发者

* 试运行期间
发送以下申请邮件至：[COP平台运营团队](mailto:MicroService_Mgmt@coscon.com)

邮件标题：COP平台开发者入驻申请-XXX公司

|申请信息|说明|
|------------|:-----------------|
|联系人姓名：	 |    |
|联系人电话：	 |    |
|电子邮箱：	|    |
|公司名称：	|    |
|合作标签：	|1、	我是直客；2、	我是货代；3、	我是供应商；4、	我是软件服务商；5、	我是信息集成商；|
|申请用途和说明：|    |

* 正式运营期间：**待定**

## 入驻审核
[COP平台运营团队](mailto:MicroService_Mgmt@coscon.com)将根据入驻申请进行评估审核，并在15个工作日内反馈审核意见。

## 安全凭证发放
入住审核通过之后，[COP平台运营团队](mailto:MicroService_Mgmt@coscon.com)将把您的身份凭证ApiKey/SecretKey发放至您的电子邮箱。如出现SecretKey泄露，请务必在第一时间联系[COP平台运营团队](mailto:MicroService_Mgmt@coscon.com)。



# 0x05 安全体系 #

## 关于SSL证书 ##

在使用的过程中，您可能会出现https/ssl证书信任问题。推荐通过浏览器下载服务器端证书文件后，将该证书加载至信任的证书库中。
```
keytool -import -trustcacerts -alias cop -keystore "%JAVA_HOME%/JRE/LIB/SECURITY/CACERTS" -file ./api.lines.coscoshipping.com.cer -storepass changeit
```

## Hmac Auth认证体系 ##

COP平台为每一个Application发布一组**App Key**和**Secret Key**用以识别Application。COP平台将根据申请和业务需求，指派其对API的访问权限。

**Hmac Auth**体系使用了Api Key、Secret Key，摘要等技术，对于使用者访问的URI地址和请求报文进行服务端验证，安全性较高，性能开销略高。

### Java实现样例1 ###
[Hmac安全和摘要处理](https://github.com/cop-cos/COP/blob/master/openapi-client-pure/src/main/java/com/coscon/oaclient/pure/HmacPureExecutor.java) 

* 初始化并设置ApiKey和SecretKey

```java
    //com.coscon.oaclient.pure.HmacPureExecutor
    hmacPureExecutor = new HmacPureExecutor();
    hmacPureExecutor.setApiKey("YOUR_APK_KEY");
    hmacPureExecutor.setSecretKey("YOUR_SECRET_KEY");
```

* 根据HTTP(S)处理组件不同，设置HTTP Header信息
  
```java
    Map<String, String> headers = getHmacPureExecutor().buildHmacHeaders(request.getRequestLine().toString(), httpContent);
    if(headers!=null) {
        for(Entry<String, String> e:headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }
    }
```

### Java实现样例2 - HttpClient ### 
 [HttpClient样例代码](https://github.com/cop-cos/COP/blob/master/openapi-client-httpclient/src/test/java/com/coscon/openapi/client/httpclient/CargoTrackingTestcase.java)
 
    com.coscon.openapi.client.httpclient.CargoTrackingTestcase

* 初始化并设置ApiKey和SecretKey

```java
    /*com.coscon.openapi.client.httpclient.AbstractOpenapiTestcase#setUp*/
    hmacPureExecutor = new HmacPureExecutor();
    hmacPureExecutor.setApiKey("YOUR_API_KEY");
    hmacPureExecutor.setSecretKey("YOUR_SECRET_KEY");
```

* 在HttpClientBuilder中，注册Interceptor用以进行访问安全预处理
  
```java
    HttpClientBuilder builder = HttpClientBuilder.create();
    builder.addInterceptorLast(new HttpRequestInterceptor() {

        @Override
        public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
            if(!match(request, hostPatterns)) {
                return;
            }
            byte[] httpContent = new byte[0];
            if (request instanceof HttpEntityEnclosingRequest) {
                httpContent = IOUtils.toByteArray(((HttpEntityEnclosingRequest) request).getEntity().getContent());
            }
            try {
                Map<String, String> headers = getHmacPureExecutor().buildHmacHeaders(request.getRequestLine().toString(), httpContent);
                if(headers!=null) {
                    for(Entry<String, String> e:headers.entrySet()) {
                        request.addHeader(e.getKey(), e.getValue());
                    }
                }
            } catch (OpenClientSecurityException e) {
                e.printStackTrace();
            }
        }
    });
```



# 0x06 全局代码 #
详情请点击：[全局代码](https://github.com/cop-cos/COP/blob/master/GlobalCodes.md)



# 0x07 API清单 #

| 用户类型           | 模块           | 服务        | 文档 | 流量限制|
| ------------ | ------------ | ---------- | :-------: | :-------: |
| 公共组   | 公共查询        | 货物跟踪|[说明 doc](https://github.com/cop-cos/COP/blob/master/docs/info-cargotracking.md)| **账号级别**，每天至多1000次，每月至多30000次|
|      |         | 船期查询|[说明 doc](https://github.com/cop-cos/COP/blob/master/docs/info-schedule.md)| **账号级别**，每天至多1000次，每月至多30000次|
