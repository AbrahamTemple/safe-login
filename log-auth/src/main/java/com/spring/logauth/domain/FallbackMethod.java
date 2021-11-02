package com.spring.logauth.domain;

import com.spring.logauth.entity.IpInfo;
import com.spring.logauth.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mobile.device.Device;

import javax.servlet.http.HttpServletRequest;
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
public abstract class FallbackMethod extends CommonMethod {

    public IpInfo NetError(Device device, HttpServletRequest request){
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        log.info("<<<{},访问的设备是{},访问的时间是{}>>>","获取ip详细信息失败",getDeviceCnName(device),dateFormat.format(new Date()));
        return new IpInfo("failure", getDeviceCnName(device),0.0,0.0, IpUtil.getClientIpAddr(request));
    }
}
