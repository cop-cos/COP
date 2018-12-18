# COP
Cosco shipping lines Open api Platform

中远海运集运的**Open API**主要基于集装箱运输业务，向供应链上下游、前后端延伸，一方面服务于传统运输客户，为行业客户定制信息解决方案，深化和客户的信息合作，增强服务黏性；另一方面通过建立丰富完善的全方位供应链和电子商务API体系，乃至允许第三方（独立开发者、行业解决方案供应商、客户）基于我司的API体系进行定制开发，推动物流信息平台的生态建设。

## 对外开放的API服务体系
根据对外API需求和模式的不同，其总体技术亦有所区别。对外API模式分为两类：
* **基于HTTP(S)协议**：服务于同步调用和异步调用；
![标准/定制同步API](https://github.com/Chenjp/COP/blob/master/docs/images/overview_001.png)
![标准异步API](https://github.com/Chenjp/COP/blob/master/docs/images/overview_002.png)
* **基于MQ协议**：服务于异步调用，仅适用于深度定制的应用场景，对于MQ的安全管理、端到端的MQ协议网络等存在要求；
