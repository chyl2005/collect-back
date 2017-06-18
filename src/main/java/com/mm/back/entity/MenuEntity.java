package com.mm.back.entity;

import java.util.Date;
import javax.persistence.*;

/**
 * @author chenyanlong
 * @ClassName: MenuEntity
 * @Description: 左边栏模块实体 三级结构 前两级为左边栏 第三级对应 第二级模块功能操作
 * @date 2015年11月23日 下午2:39:56
 */
@Entity
@Table(name = "tb_menu")
public class MenuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    /**
     * 请求方法路径 唯一
     */
    @Column(name = "url", unique = true)
    private String url;

    /**
     * 模块父类id
     */
    @Column(name = "parent_id")
    private Integer parentId;
    /**
     * 小图标
     */
    @Column(name = "icon")
    private String icon;
    /**
     * 级别
     */
    @Column(name = "level")
    private Integer level;

    /**
     *
     */
    @Column(name = "is_link", nullable = false, columnDefinition = "COMMENT '0 不是链接 1：链接'")
    private Integer isLink;
    /**
     * 创建时间
     */
    @Column(name = "gmt_created")
    private Date gmtCreated;
    /**
     * 修改时间
     */
    @Column(name = "gmt_modified")
    private Date gmtModified;
    /**
     * 删除标记
     */
    @Column(name = "is_del")
    private Integer isDel;



    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getIsLink() {
        return isLink;
    }

    public void setIsLink(Integer isLink) {
        this.isLink = isLink;
    }
}
