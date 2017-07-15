package com.mm.back.service.impl;

import org.springframework.stereotype.Service;
import com.mm.back.dto.PushModel;
import com.mm.back.service.PushService;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/7/13
 * Time:19:36
 * Desc:推送异常消息
 */
@Service("pushErrorService")
public class PushErrorService implements PushService {

    private  static final String ERROR_ROBET="https://oapi.dingtalk.com/robot/send?access_token=c9f1495633e7fb3a91c92ae8dd9c3cfe7275349e240635aff79d681781dcce96";



    @Override
    public void push(PushModel pushModel) {

    }
}
