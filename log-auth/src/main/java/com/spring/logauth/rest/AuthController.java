package com.spring.logauth.rest;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.spring.logauth.domain.FallbackMethod;
import com.spring.logauth.entity.IpInfo;
import com.spring.logauth.exception.DeviceException;
import com.spring.logauth.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import com.spring.logauth.util.IpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.10.1
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController extends FallbackMethod {

    @Resource
    AuthService authService;

//    @HystrixCommand(fallbackMethod = "NetError",
//            commandProperties = {
//                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
//                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"), //请求次数
//                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "12000"), //时间窗口期
//                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")})//失败率达到多少后跳闸
    @RequestMapping(value = "/ip/info",produces = "application/json;charset=UTF-8")
    public IpInfo getInfo(Device device,HttpServletRequest request){
        return authService.getInfo(device,request);
    }

    @RequestMapping(value = "/pwd/err",produces = "application/json;charset=UTF-8")
    public String getAndSet(Device device,HttpServletRequest request){
        return authService.getAndSet(device,request);
    }

    @RequestMapping(value = "/ip",produces = "application/json;charset=UTF-8")
    public String ip(HttpServletRequest request){
        return IpUtil.getClientIpAddr(request);
    }

}
