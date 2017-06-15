package com.mm.back.controller.sys;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mm.back.common.AoData;
import com.mm.back.common.Menu;
import com.mm.back.common.WebResponse;
import com.mm.back.entity.ModuleEntity;
import com.mm.back.service.AuthorityService;
import com.mm.back.service.ModuleService;

/**
 * @author chenyanlong
 * @ClassName: ModuleController
 * @Description: 模块控制
 * @date 2015年11月23日 下午2:23:10
 */
@Controller
@RequestMapping("/menu")
public class ModuleController {

    @Resource(name = "authorityService")
    private AuthorityService authorityService;
    @Autowired
    private ModuleService moduleService;

    /**
     * @param @return
     * @return List<ModuleVo>    返回类型
     * @Description:
     * @author chenyanlong
     * @date 2016年3月16日 下午5:19:54
     */
    @RequestMapping("/getAllModuleVos")
    @ResponseBody
    public WebResponse getAllModuleVos() {
        WebResponse webResponse = WebResponse.getSuccessWebResponse();
        List<Menu> modules = this.authorityService.getAllModules(1);
        webResponse.setData(modules);
        return webResponse;

    }

    @RequestMapping("/getModules")
    @ResponseBody
    public WebResponse getModules(Integer parentId) {
        WebResponse webResponse = WebResponse.getSuccessWebResponse();
        webResponse.setData(this.moduleService.getModules(parentId));
        return webResponse;
    }

    /**
     * @param parentId
     * @return AoData 返回类型
     * @Description: 模块分页数据
     * @author chenyanlong
     * @date 2015年11月27日 下午3:04:19
     */
    @RequestMapping("/getModuleList")
    @ResponseBody
    public WebResponse getModuleList(Integer parentId) {
        WebResponse webResponse = WebResponse.getSuccessWebResponse();
        AoData aoData = this.moduleService.getPageModules(parentId);
        webResponse.setData(aoData);
        return webResponse;
    }

    /**
     * @param module
     * @return ModuleEntity 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2016年3月16日 下午3:32:58
     */
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public WebResponse saveOrUpdate(ModuleEntity module) {
        WebResponse webResponse = WebResponse.getSuccessWebResponse();
        if (module.getId() != null) {
            this.moduleService.updateEntity(module);
        } else {
            if (module.getParentId() != 0) {// 二级
                module.setLevel(2);
            } else {// 一级
                module.setLevel(1);
            }
            this.moduleService.saveEntity(module);
        }
        return webResponse;
    }

    /**
     * @param moduleId
     * @return ModuleEntity 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月27日 下午4:57:01
     */
    @RequestMapping("/getModule")
    @ResponseBody
    public WebResponse getModule(Integer moduleId) {
        WebResponse webResponse = WebResponse.getSuccessWebResponse();
        webResponse.setData(this.moduleService.findById(moduleId));
        return webResponse;
    }

    @RequestMapping("/del")
    @ResponseBody
    public WebResponse del(Integer id) {
        WebResponse webResponse = WebResponse.getSuccessWebResponse();
        this.moduleService.del(id);
        return webResponse;
    }
}
