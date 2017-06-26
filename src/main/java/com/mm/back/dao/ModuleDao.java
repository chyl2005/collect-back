package com.mm.back.dao;

import java.util.List;
import java.util.Set;
import com.mm.back.common.AoData;
import com.mm.back.entity.MenuEntity;

public interface ModuleDao {

    MenuEntity getModuleByActionName(String sn);

    /**
     * @return List<ModuleEntity> 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月23日 下午2:33:08
     */
    List<MenuEntity> getAllModules();

    /**
     * @return List<ModuleEntity> 返回类型
     * @Description: 根据id 查询模块列表
     * Object[] ids
     * @author chenyanlong
     * @date 2015年11月23日 下午4:27:47
     */
    List<MenuEntity> getModules(Set<Integer> values);

    /**
     * @param state
     * @return List<ModuleEntity> 返回类型
     * @Description: 查询所有可用模块
     * @author chenyanlong
     * @date 2015年11月26日 上午9:45:13
     */
    List<MenuEntity> getAllModules(Integer state);

    /**
     * @param parentId
     * @return ModuleEntity 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月25日 下午5:45:12
     */
    List<MenuEntity> getModules(Integer parentId);

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
    MenuEntity updateEntity(MenuEntity module);

    /**
     * @param entity
     * @return ModuleEntity 返回类型
     * @Description: 保存
     * @author chenyanlong
     * @date 2015年11月27日 下午3:01:50
     */
    MenuEntity saveEntity(MenuEntity entity);

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
    List<MenuEntity> getModulesByLevel(Integer level);

    /**
     * @param id
     * @return ModuleEntity 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月27日 下午4:55:39
     */
    MenuEntity findById(Integer id);

    void del(Integer id);

    /**
     * @param url
     * @return ModuleEntity 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年12月10日 下午4:26:52
     */
    MenuEntity getModuleByPath(String url);

}
