package com.mm.back.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mm.back.common.WebResponse;
import com.mm.back.entity.AuthorityEntity;
import com.mm.back.service.AuthorityService;

@Controller
@RequestMapping("/authority")
public class AuthorityController {

    @Autowired
    private AuthorityService authorityService;

    /**
     * @param entity
     * @return AuthorityEntity 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2016年3月16日 下午5:43:48
     */
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public WebResponse saveOrUpdate(AuthorityEntity entity) {
        WebResponse webResponse = WebResponse.getSuccessWebResponse();
        String moduleId = entity.getModuleId();
        if (moduleId != null && moduleId.endsWith(",")) {
            moduleId = moduleId.substring(0, moduleId.length() - 1);
        }
        this.authorityService.saveOrUpdateEntity(entity);
        return webResponse;

    }

}
