package com.mm.back.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mm.back.common.AoData;
import com.mm.back.common.DeleteEnum;
import com.mm.back.dao.ModuleDao;
import com.mm.back.entity.ModuleEntity;

/**
 * @author chenyanlong
 * @ClassName: ModuleService
 * @Description: 系统模块业务处理
 * @date 2015年11月23日 下午2:30:03
 */
@Service("moduleService")
public class ModuleService {

    @Resource(name = "moduleDao")
    private ModuleDao moduleDao;

    /**
     * @return List<ModuleEntity> 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月23日 下午2:33:08
     */
    public List<ModuleEntity> getAllModules() {
        return this.moduleDao.getAllModules();
    }

    /**
     * @param sn action 名称，每个模块的sn就是Action名字
     * @return ModuleEntity 返回类型
     * @Description: 根据sn 获取当前请求的 module
     * @author chenyanlong
     * @date 2015年11月23日 下午3:01:20
     */
    public ModuleEntity getModuleByActionName(String sn) {
        return this.moduleDao.getModuleByActionName(sn);
    }

    /**
     * @param parentId
     * @return ModuleEntity 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月25日 下午5:45:12
     */
    public List<ModuleEntity> getModules(Integer parentId) {
        return this.moduleDao.getModules(parentId);
    }

    /**
     * @param parentId
     * @return AoData 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月25日 下午5:45:18
     */
    public AoData getPageModules(Integer parentId) {

        return this.moduleDao.getPageModules(parentId);
    }

    /**
     * @return AoData 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月25日 下午5:45:18
     */
    public AoData getPageModules() {

        return this.moduleDao.getPageModules();
    }

    /**
     * @param module
     * @return ModuleEntity 返回类型
     * @Description: 修改
     * @author chenyanlong
     * @date 2015年11月27日 下午3:00:28
     */
    @Transactional
    public ModuleEntity updateEntity(ModuleEntity module) {

        return this.moduleDao.updateEntity(module);
    }

    /**
     * @param entity
     * @return ModuleEntity 返回类型
     * @Description: 保存
     * @author chenyanlong
     * @date 2015年11月27日 下午3:00:40
     */
    @Transactional
    public ModuleEntity saveEntity(ModuleEntity entity) {
        return this.moduleDao.saveEntity(entity);
    }

    /**
     * @param moduleId
     * @return Boolean 返回类型
     * @Description: 逻辑删除
     * @author chenyanlong
     * @date 2015年11月27日 下午3:00:58
     */
    @Transactional
    public void del(Integer moduleId) {
        this.moduleDao.updateStatus(moduleId, DeleteEnum.DEL.getCode());
    }

    /**
     * @param level
     * @return List<ModuleEntity> 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月25日 下午5:45:18
     */
    public List<ModuleEntity> getModulesByLevel(Integer level) {
        return this.moduleDao.getModulesByLevel(level);
    }

    /**
     * @param id
     * @return ModuleEntity 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月27日 下午4:55:55
     */
    public ModuleEntity findById(Integer id) {
        return this.moduleDao.findById(id);
    }

    /**
     * @param action_path
     * @return ModuleEntity 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年12月10日 下午4:26:52
     */
    public ModuleEntity getModuleByPath(String action_path) {
        return this.moduleDao.getModuleByPath(action_path);
    }


}
