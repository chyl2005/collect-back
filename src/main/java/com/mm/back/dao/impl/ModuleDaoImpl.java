package com.mm.back.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.mm.back.common.AoData;
import com.mm.back.constants.DeleteStatusEnum;
import com.mm.back.constants.MenuEnum;
import com.mm.back.dao.ModuleDao;
import com.mm.back.entity.MenuEntity;

@Repository("moduleDao")
public class ModuleDaoImpl extends BaseDaoImpl<MenuEntity> implements ModuleDao {

    public MenuEntity getModuleByActionName(String sn) {
        return this.findFirst("from MenuEntity where sn=?", sn);
    }

    /**
     * @return List<MenuEntity> 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月23日 下午2:33:08
     */
    @Override
    public List<MenuEntity> getAllModules() {
        return this.find("from MenuEntity");
    }

    /**
     * @return List<MenuEntity> 返回类型
     * @Description: 根据id 查询模块列表
     * Object[] ids
     * @author chenyanlong
     * @date 2015年11月23日 下午4:27:47
     */
    @Override
    public List<MenuEntity> getModules(Set<Integer> values) {
        return this.findIn("from MenuEntity where isDel=0  and  id in(:ids) order by level", "ids", values);
    }

    /**
     * @param isDel
     * @return List<MenuEntity> 返回类型
     * @Description: 查询所有可用模块
     * @author chenyanlong
     * @date 2015年11月26日 上午9:45:13
     */
    @Override
    public List<MenuEntity> getAllModules(Integer isDel) {
        StringBuilder hql = new StringBuilder();
        hql.append("from MenuEntity ");
        HashMap<String, Object> paras = new HashMap<String, Object>();
        if (isDel != null) {
            hql.append(" where isDel=:isDel");
            paras.put("state", isDel);
        }
        hql.append(" order by level");
        return this.find(hql.toString(), paras);
    }

    /**
     * @param parentId
     * @return MenuEntity 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月25日 下午5:45:12
     */
    @Override
    public List<MenuEntity> getModules(Integer parentId) {

        return this.find("from MenuEntity where parentId=? ", parentId);
    }

    /**
     * @param parentId
     * @return AoData 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月25日 下午5:45:18
     */
    @Override
    public AoData getPageModules(Integer parentId) {
        StringBuilder hql = new StringBuilder();
        hql.append("from MenuEntity where 1=1 ");
        HashMap<String, Object> paras = new HashMap<String, Object>();
        if (parentId != null) {
            hql.append(" and parentId=:parentId");
            paras.put("parentId", parentId);
        }
        hql.append(" and isDel=:isDel");
        paras.put("isDel", DeleteStatusEnum.NOT_DEL.getCode());
        return this.findPage(null, hql.toString(), paras);
    }

    /**
     * @param
     * @return AoData 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月25日 下午5:45:18
     */
    @Override
    public AoData getPageModules() {
        StringBuilder hql = new StringBuilder();
        hql.append("from MenuEntity  ");
        return this.findPage(null, hql.toString());
    }

    /**
     * @param menu
     * @return MenuEntity 返回类型
     * @Description: 修改
     * @author chenyanlong
     * @date 2015年11月27日 下午3:01:31
     */
    @Transactional
    public MenuEntity updateEntity(MenuEntity menu) {
        MenuEntity menuEntity = this.get(MenuEntity.class, menu.getId());
        if (menuEntity != null) {
            menuEntity.setParentId(menu.getParentId());
            menuEntity.setIcon(menu.getIcon());
            menuEntity.setIsLink(menu.getIsLink());
            menuEntity.setGmtModified(new Date());
            menuEntity.setIsDel(DeleteStatusEnum.NOT_DEL.getCode());
            menuEntity.setName(menu.getName());
            menuEntity.setParentId(menu.getParentId());
            menuEntity.setUrl(menu.getUrl());
            this.update(menuEntity);
        }
        return menuEntity;
    }

    /**
     * @param entity
     * @return MenuEntity 返回类型
     * @Description: 保存
     * @author chenyanlong
     * @date 2015年11月27日 下午3:01:50
     */
    @Override
    @Transactional
    public MenuEntity saveEntity(MenuEntity entity) {
        entity.setParentId(entity.getParentId() == null || entity.getParentId().equals(MenuEnum.ROOT)
                                   ? MenuEnum.ROOT : entity.getParentId());
        entity.setLevel(entity.getParentId() == null || entity.getParentId().equals(MenuEnum.ROOT)
                                ? MenuEnum.ONE_LEVEL.getCode() : MenuEnum.TWO_LEVEL.getCode());
        entity.setIsLink(entity.getIsLink() != null ? entity.getIsLink() : MenuEnum.NOT_LINK.getCode());
        entity.setGmtCreated(new Date());
        entity.setGmtModified(new Date());
        entity.setIsDel(DeleteStatusEnum.NOT_DEL.getCode());
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
    @Override
    @Transactional
    public void updateStatus(Integer moduleId, Integer isDel) {
        MenuEntity menuEntity = this.get(MenuEntity.class, moduleId);
        if (menuEntity != null) {
            menuEntity.setIsDel(isDel);
            this.update(menuEntity);
        }
    }

    /**
     * @param level
     * @return List<MenuEntity> 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月25日 下午5:45:18
     */
    @Override
    public List<MenuEntity> getModulesByLevel(Integer level) {
        return this.find("from MenuEntity where level=? ", level);
    }

    /**
     * @param id
     * @return MenuEntity 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月27日 下午4:55:39
     */
    @Override
    public MenuEntity findById(Integer id) {
        return this.findById(id);
    }

    @Transactional
    @Override
    public void del(Integer id) {
        MenuEntity entity = this.findById(id);
        if (entity != null) {
            this.delete(entity);
        }
    }

    /**
     * @param path
     * @return MenuEntity 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年12月10日 下午4:26:52
     */
    @Override
    public MenuEntity getModuleByPath(String path) {
        return this.findFirst("from MenuEntity where path=?", path);
    }

}
