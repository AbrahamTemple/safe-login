package com.spring.logauth.service.impl;

import com.spring.logauth.domain.CommonMethod;
import com.spring.logauth.entity.IpInfo;
import com.spring.logauth.exception.DeviceException;
import com.spring.logauth.service.AuthService;
import com.spring.logauth.util.IpUtil;
import com.spring.logauth.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.10.1
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
@Slf4j
@Service
public class AuthServiceImpl extends CommonMethod implements AuthService {

    @Resource
    RestTemplate restTemplate;

    @Resource
    RedisTemplate<String,Integer> redisTemplate;

    @Value("${api.client-ip}")
    private String api;

    @Override
    public IpInfo getInfo(Device device, HttpServletRequest request) {
        IpInfo req = restTemplate.getForObject(api.replace("0.0.0.0", IpUtil.getClientIpAddress(request)), IpInfo.class);
        String oDevice = getDeviceCnName(device);
        if(oDevice.equals(NON_DEVICE)){
            log.info(NON_DEVICE);
            throw new DeviceException("非法设备访问异常");
        }
        req.setDevice(oDevice);
        log.info(req.toString());
        return req;
    }

    @Override
    public String getAndSet(Device device,HttpServletRequest request) {

        IpInfo req = restTemplate.getForObject(api.replace("0.0.0.0", IpUtil.getClientIpAddress(request)), IpInfo.class);
        String oDevice = getDeviceCnName(device);
        if(oDevice.equals(NON_DEVICE)){
            throw new DeviceException("非法设备访问异常");
        }
        req.setDevice(oDevice);
        log.info(req.toString());

        if(!redisTemplate.opsForValue().setIfAbsent(req.getQuery(), 1)){

            int lock = redisTemplate.opsForValue().get(req.getQuery());

            if (lock == CHANGE && redisTemplate.expire(req.getQuery(),300, TimeUnit.SECONDS)){
                return req.getQuery() + "---访问已被锁定，请5分钟后重试";
            }

            int lastLock = redisTemplate.opsForValue().getAndSet(req.getQuery(), lock + 1);

            if(lock == lastLock) {
                return "密码错误，你还有" + (CHANGE - lastLock) + "次机会";
            } else {
                return "系统发生异常";
            }

        }

        return "密码错误的开始，你还有"+ CHANGE +"次机会";
    }




}
