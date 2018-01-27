# 需要说明
线上环境的log输出级别一般都设置的很高比如WARN，如果出现一些很难直观解决的问题，可能需要调整日志输出级别为DEBUG，又不想改个日志输出级别还要重启服务，那么就需要进行日志级别动态配置达到最终目的。
## 实现方式
1. JMX
通过logback提供的接口JMXConfiguratorMBean来实现日志级别的动态配置，
参考：https://logback.qos.ch/manual/jmxConfig.html

2. HTTP配置

查询指定模块的日志级别
``` bash
curl "http://localhost:8090/InvokeAction//logback%5Fjmx%3Aname%3DreloadConfig/action=getLoggerLevel?action=getLoggerLevel&p1%2Bjava.lang.String=com.howtodoinjava.demo.service"
```
修改指定模块的日志级别
```bash
curl "http://localhost:8090/InvokeAction//logback%5Fjmx%3Aname%3DreloadConfig/action=setLoggerLevel?action=setLoggerLevel&p1%2Bjava.lang.String=com.howtodoinjava.demo.service&p2%2Bjava.lang.String=ERROR"
```
重新配置日志级别
```bash
curl "http://localhost:8090/InvokeAction//logback%5Fjmx%3Aname%3DreloadConfig/action=reloadDefaultConfiguration?action=reloadDefaultConfiguration"
```

2. 暴露controller
调用上述日志级别修改接口实现动态配置的service，对外暴露相应的controller接口，提供http请求形式的日志修改入口；

3. 分布式系统实现
多个节点如何统一修改，如果不能统一修改如何逐个完成动态配置
方案1：每个节点都启动JMX监控Log的服务，通过http请求来修改日志级别，在Jenkins中统一配置所有的节点请求地址，批量配置即可；
方案2：每个微服务都实现日志修改接口，统一暴露给restApi，问题点（多节点情况下不能统一修改，只能修改请求所落到的节点上的服务日志级别）；