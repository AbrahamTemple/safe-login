package com.spring.logauth.config;

import com.google.common.collect.ImmutableMap;
import org.mitre.dsmiley.httpproxy.ProxyServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Servlet;
import java.util.Map;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.10.2
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */

@Configuration
public class ProxyServletConfig {

    // 读取配置文件中路由设置
    @Value("${proxy.servlet_url}")
    private String servlet_url;
    // 读取配置中代理目标地址
    @Value("${proxy.target_url}")
    private String target_url;

    @Bean
    public Servlet createProxyServlet(){
        // 创建新的ProxyServlet
        return new ProxyServlet();
    }


    @Bean
    public ServletRegistrationBean proxyServletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(createProxyServlet(), servlet_url);
        //设置网址以及参数
        Map<String, String> params = ImmutableMap.of(
                "targetUri", target_url,
                "log", "true");
        registrationBean.setInitParameters(params);
        return registrationBean;
    }

}
