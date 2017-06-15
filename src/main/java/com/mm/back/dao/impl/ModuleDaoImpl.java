package com.mm.back.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Repository;
import com.mm.back.common.AoData;
import com.mm.back.dao.ModuleDao;
import com.mm.back.entity.ModuleEntity;

@Repository("moduleDao")
public class ModuleDaoImpl extends BaseDaoImpl<ModuleEntity> implements ModuleDao {

    public ModuleEntity getModuleByActionName(String sn) {
        return this.findFirst("from ModuleEntity where sn=?", sn);
    }

    /**
     * @return List<ModuleEntity> 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月23日 下午2:33:08
     */
    public List<ModuleEntity> getAllModules() {
        return this.find("from ModuleEntity");
    }

    /**
     * @return List<ModuleEntity> 返回类型
     * @Description: 根据id 查询模块列表
     * Object[] ids
     * @author chenyanlong
     * @date 2015年11月23日 下午4:27:47
     */
    public List<ModuleEntity> getModules(Set<Integer> values) {
        return this.findIn("from ModuleEntity where state=1 and  id in(:ids) order by level", "ids", values);
    }

    /**
     * @param state
     * @return List<ModuleEntity> 返回类型
     * @Description: 查询所有可用模块
     * @author chenyanlong
     * @date 2015年11月26日 上午9:45:13
     */
    public List<ModuleEntity> getAllModules(Integer state) {
        StringBuilder hql = new StringBuilder();
        hql.append("from ModuleEntity ");
        HashMap<String, Object> paras = new HashMap<String, Object>();
        if (state != null) {
            hql.append(" where state=:state");
            paras.put("state", state);
        }
        hql.append(" order by level");
        return this.find(hql.toString(), paras);
    }

    /**
     * @param parentId
     * @return ModuleEntity 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月25日 下午5:45:12
     */
    public List<ModuleEntity> getModules(Integer parentId) {

        return this.find("from ModuleEntity where parentId=? ", parentId);
    }

    /**
     * @param parentId
     * @return AoData 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月25日 下午5:45:18
     */
    public AoData getPageModules(Integer parentId) {
        StringBuilder hql = new StringBuilder();
        hql.append("from ModuleEntity ");
        HashMap<String, Object> paras = new HashMap<String, Object>();
        if (parentId != null) {
            hql.append(" where parentId=:parentId");
            paras.put("parentId", parentId);
        }
        hql.append(" order by orderNum");
        return this.findPage(null, hql.toString(), paras);
    }

    /**
     * @param
     * @return AoData 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月25日 下午5:45:18
     */
    public AoData getPageModules() {
        StringBuilder hql = new StringBuilder();
        hql.append("from ModuleEntity  order by orderNum");
        return this.findPage(null, hql.toString());
    }

    /**
     * @param module
     * @return ModuleEntity 返回类型
     * @Description: 修改
     * @author chenyanlong
     * @date 2015年11月27日 下午3:01:31
     */
    public ModuleEntity updateEntity(ModuleEntity module) {
        ModuleEntity moduleEntity = this.get(ModuleEntity.class, module.getId());
        if (moduleEntity != null) {
            moduleEntity.setActionPath(module.getActionPath());
            moduleEntity.setName(module.getName());
            moduleEntity.setOrderNum(module.getOrderNum());
            moduleEntity.setParentId(module.getParentId());
            moduleEntity.setUrl(module.getUrl());
            this.update(moduleEntity);
        }
        return moduleEntity;
    }

    /**
     * @param entity
     * @return ModuleEntity 返回类型
     * @Description: 保存
     * @author chenyanlong
     * @date 2015年11月27日 下午3:01:50
     */
    public ModuleEntity saveEntity(ModuleEntity entity) {
        this.save(entity);
        return entity;
    }

    /**
     * @param moduleId
     * @param isDel
     * @return Boolean 返回类型
     * @Description: 修改状态
     * @author chenyanlong
     * @date 2015年11月27日 下午3:02:10
     */
    public void updateStatus(Integer moduleId, Integer isDel) {
        ModuleEntity moduleEntity = this.get(ModuleEntity.class, moduleId);
        if (moduleEntity != null) {
            moduleEntity.setIsDel(isDel);
            this.update(moduleEntity);
        }
    }

    /**
     * @param level
     * @return List<ModuleEntity> 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月25日 下午5:45:18
     */
    public List<ModuleEntity> getModulesByLevel(Integer level) {
        return this.find("from ModuleEntity where level=? ", level);
    }

    /**
     * @param id
     * @return ModuleEntity 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月27日 下午4:55:39
     */
    public ModuleEntity findById(Integer id) {
        return this.findById(id);
    }

    @Override
    public void del(Integer id) {
        ModuleEntity entity = this.findById(id);
        if (entity != null) {
            this.delete(entity);
        }
    }

    /**
     * @param action_path
     * @return ModuleEntity 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年12月10日 下午4:26:52
     */
    public ModuleEntity getModuleByPath(String action_path) {
        return this.findFirst("from ModuleEntity where actionPath=?", action_path);
    }

}
