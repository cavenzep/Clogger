package com.howtodoinjava.demo.application;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.jmx.JMXConfigurator;
import com.sun.jdmk.comm.HtmlAdaptorServer;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

/**
 * @author zhuerping
 * @date 2018/1/10
 */
@Component
public class JMXConnection4Http {

    public static final String DOMAIN_NAME = "logback_jmx";
    public static final String RELOAD_CONFIG_NAME = "reloadConfig";
    public static final String CONNECTOR_NAME = "htmlConnector";
    private static MBeanServer mBeanServer;
    private static JMXConfigurator reloadConfig;
    private static HtmlAdaptorServer connector;
    private static int port = 8090;

    private static LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

    @PostConstruct
    public static void init() throws Exception {
        mBeanServer = MBeanServerFactory.createMBeanServer(DOMAIN_NAME);
        // 注册服务
        ObjectName on = new ObjectName(DOMAIN_NAME + ":name=" + RELOAD_CONFIG_NAME);
        reloadConfig = new JMXConfigurator(loggerContext, mBeanServer, on);
        mBeanServer.registerMBean(reloadConfig, new ObjectName(DOMAIN_NAME + ":name=" + RELOAD_CONFIG_NAME));
        // 注册连接
        connector = new HtmlAdaptorServer();
        connector.setPort(port);
        mBeanServer.registerMBean(connector, new ObjectName(DOMAIN_NAME + ":name=" + CONNECTOR_NAME));
        // 启动服务
        connector.start();
    }

}
