package com.spring.logauth.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.10.1
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
@Slf4j
public class DeviceException extends RuntimeException{

    public DeviceException(String message) {
        super(message);
    }
}
