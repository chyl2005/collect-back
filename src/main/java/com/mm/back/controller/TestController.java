package com.mm.back.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mm.back.dao.DeviceInfoDao;
import com.mm.back.dto.DeviceConfigData;
import com.mm.back.entity.DeviceInfoEntity;
import com.mm.back.utils.JsonUtils;

/**
 * Author:chyl2005
 * Date:17/3/26
 * Time:10:13
 * Desc:描述该类的作用
 */

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private DeviceInfoDao deviceInfoDao;

    @RequestMapping("/charSet")
    @ResponseBody
    public boolean charSet(String decode, String encode) {
        if (StringUtils.isNotBlank(decode)) {
            // ServerInitializer.decoder = decode;
        }
        if (StringUtils.isNotBlank(encode)) {
            //  ServerInitializer.encoder = encode;
        }
        return true;
    }

    @RequestMapping("/data")
    @ResponseBody
    public boolean data(@RequestBody DeviceConfigData data) {
        String s = JsonUtils.object2Json(data);
        return true;
    }

    @RequestMapping("/getDeviceByDeviceNum")
    @ResponseBody
    public DeviceInfoEntity getDeviceByDeviceNum() {
       return this.deviceInfoDao.getDeviceByDeviceNum("YC-AB-0002");
    }
}
