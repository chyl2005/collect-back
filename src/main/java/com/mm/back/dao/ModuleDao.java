package com.mm.back.dao;

import java.util.List;
import java.util.Set;
import com.mm.back.common.AoData;
import com.mm.back.entity.ModuleEntity;

public interface ModuleDao {

    ModuleEntity getModuleByActionName(String sn);

    /**
     * @return List<ModuleEntity> 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月23日 下午2:33:08
     */
    List<ModuleEntity> getAllModules();

    /**
     * @return List<ModuleEntity> 返回类型
     * @Description: 根据id 查询模块列表
     * Object[] ids
     * @author chenyanlong
     * @date 2015年11月23日 下午4:27:47
     */
    List<ModuleEntity> getModules(Set<Integer> values);

    /**
     * @param state
     * @return List<ModuleEntity> 返回类型
     * @Description: 查询所有可用模块
     * @author chenyanlong
     * @date 2015年11月26日 上午9:45:13
     */
    List<ModuleEntity> getAllModules(Integer state);

    /**
     * @param parentId
     * @return ModuleEntity 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月25日 下午5:45:12
     */
    List<ModuleEntity> getModules(Integer parentId);

    /**
     * @param parentId
     * @return AoData 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月25日 下午5:45:18
     */
    AoData getPageModules(Integer parentId);

    /**
     * @param
     * @return AoData 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月25日 下午5:45:18
     */
    AoData getPageModules();

    /**
     * @param module
     * @return ModuleEntity 返回类型
     * @Description: 修改
     * @author chenyanlong
     * @date 2015年11月27日 下午3:01:31
     */
    ModuleEntity updateEntity(ModuleEntity module);

    /**
     * @param entity
     * @return ModuleEntity 返回类型
     * @Description: 保存
     * @author chenyanlong
     * @date 2015年11月27日 下午3:01:50
     */
    ModuleEntity saveEntity(ModuleEntity entity);

    /**
     * @param moduleId
     * @param isDel
     * @return Boolean 返回类型
     * @Description: 修改状态
     * @author chenyanlong
     * @date 2015年11月27日 下午3:02:10
     */
    void updateStatus(Integer moduleId, Integer isDel);

    /**
     * @param level
     * @return List<ModuleEntity> 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月25日 下午5:45:18
     */
    List<ModuleEntity> getModulesByLevel(Integer level);

    /**
     * @param id
     * @return ModuleEntity 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月27日 下午4:55:39
     */
    ModuleEntity findById(Integer id);

    void del(Integer id);

    /**
     * @param action_path
     * @return ModuleEntity 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年12月10日 下午4:26:52
     */
    ModuleEntity getModuleByPath(String action_path);

}
