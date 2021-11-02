package com.spring.logauth.domain;

import org.springframework.mobile.device.Device;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.10.1
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
public abstract class CommonMethod {

    protected final static int CHANGE = 3;

    protected final static String PHONE_DEVICE = "手机";
    protected final static String TABLE_DEVICE = "平板";
    protected final static String NORMAL_DEVICE = "电脑";
    protected final static String NON_DEVICE = "未知设备";

    public String getDeviceCnName(Device device){
        if (device.isMobile()) {
            return PHONE_DEVICE;
        } else if (device.isTablet()) {
            return TABLE_DEVICE;
        } else if(device.isNormal()){
            return NORMAL_DEVICE;
        } else {
            return NON_DEVICE;
        }
    }
}
