package com.mm.back.service;

import com.mm.back.dto.PushModel;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/7/13
 * Time:19:08
 * Desc:系统消息推送
 */
public interface PushService {

    void push(PushModel pushModel);
}
