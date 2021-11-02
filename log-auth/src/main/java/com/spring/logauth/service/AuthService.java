package com.spring.logauth.service;

import com.spring.logauth.entity.IpInfo;
import org.springframework.mobile.device.Device;

import javax.servlet.http.HttpServletRequest;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.10.1
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
public interface AuthService {
    IpInfo getInfo(Device device, HttpServletRequest request);
    String getAndSet(Device device,HttpServletRequest request);
}
