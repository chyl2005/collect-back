package com.mm.back.service.impl;

import org.springframework.stereotype.Service;
import com.mm.back.dto.PushModel;
import com.mm.back.service.PushService;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/7/13
 * Time:19:46
 * Desc:描述该类的作用
 */
@Service("pushUploadService")
public class PushUploadService implements PushService {



    private  static final String UPLOAD_ROBET="https://oapi.dingtalk.com/robot/send?access_token=bbd7f031c61c89f26b08095e7a2a204da849d987410dd7bdf327b6a85cad8571";

    @Override
    public void push(PushModel pushModel) {



    }
}
