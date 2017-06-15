package com.mm.back.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import com.mm.back.common.AoData;

 public interface BaseDao<T> {
	  
	
	 T load(Class<T> T, Serializable id);
	
	
    /** 
     * 保存一个对象 
     *  
     * @param o 
     * @return 
     */  
     Serializable save(T o);  
  
    /** 
     * 删除一个对象 
     *  
     * @param o 
     */  
     void delete(T o);  
  
    /** 
     * 更新一个对象 
     *  
     * @param o 
     */  
     void update(T o);  
  
    /** 
     * 保存或更新对象 
     *  
     * @param o 
     */  
     void saveOrUpdate(T o);  
  
    /** 
     * 获得一个对象 
     *  
     * @param c 
     *            对象类型 
     * @param id 
     * @return Object 
     */  
     T get(Class<T> c, Serializable id);  
  
    /** 
     * 获得一个对象 
     *  
     * @param hql 
     * @param param 
     * @return Object 
     */  
     T get(String hql, Object... param);  
  
    /** 
     * 获得一个对象 
     *  
     * @param hql 
     * @param param 
     * @return 
     */  
     T get(String hql, List<Object> param);  
    
	/**
	 * 获取一个对象
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	 T get(String hql, Map<String, Object> params);
  
	
	
	   /** 
     * in 查询 
     *  
     * @param hql   FROM Domain t WHERE t.id in (:ids)
     * @return 
     */  
     List<T> findByIds(String hql, List<Integer> param);
	/**
	 * 
	* @Description: 
	* @param  hql
	* @param  ids
	* @return List<T>    返回类型 
	* @author chenyanlong
	* @date 2015年11月17日 下午4:21:38
	 */
     List<T> findByIds(String hql, Object[] ids);
    
    /**
     * 
    * @Description:   in 查询   :name  
    * @param  hql
     * @param name the name of the parameter  :name
	 * @param values a collection of values to list   
    * @return List<T>    返回类型 
    * @author chenyanlong
    * @date 2015年11月23日 下午4:36:24
     */
     List<T> findIn(String hql, String name, Collection values);
    
     T findFirst(String hql, Object... params);
	
    /** 
     * 查询 
     *  
     * @param hql 
     * @return 
     */  
     List<T> find(String hql);  
  
    /** 
     * 查询集合 
     *  
     * @param hql 
     * @return 
     */  
     List<T> find(String hql, Object... params);
  
     T findFirst(String hql, Map<String, Object> params);
    /** 
     * 查询集合 
     *  
     * @param hql 
     * @param param 
     * @return 
     */  
     List<T> find(String hql, List<Object> param);  
    
    
	/**
	 * 查询对象集合
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	 List<T> find(String hql, Map<String, Object> params);
  
	   /** 
     * 查询集合(带分页) 
     *  
     * @param hql 
     * @param startRow 
     *            查询第几页 
     * @param pageSize 
     *            每页显示几条记录 
     * @return 
     */  
     List<T> find(String hql, Integer startRow, Integer pageSize);
    /** 
     * 查询集合(带分页) 
     *  
     * @param hql 
     * @param param
	 * @param startRow
	 *            查询第几页 
	 * @param pageSize
	 *            每页显示几条记录 
     * @return 
     */  
     List<T> find(String hql, Integer startRow, Integer pageSize, Object... param);
  
    /** 
     * 查询集合(带分页) 
     *  
     * @param hql 
     * @param param 
     * @param startRow 
     * @param pageSize 
     * @return 
     */  
     List<T> find(String hql, List<Object> param, Integer startRow, Integer pageSize);  
    
	/**
	 * 查询对象集合
	 * 
	 * @param hql
	 * @param startRow
	 * @param pageSize
	 * @param params
	 * @return
	 */
	 List<T> find(String hql, Map<String, Object> params, int startRow, int pageSize);
  
   
    /** 
     * select count(*) from 类 
     *  
     * @param hql 
     * @return 
     */  
     Integer count(String hql);  
  
    /** 
     * select count(*) from 类 
     *  
     * @param hql 
     * @param param 
     * @return 
     */  
     Long count(String hql, Object... param);  
  
    /** 
     * select count(*) from 类 
     *  
     * @param hql 
     * @param param 
     * @return 
     */  
     Integer count(String hql, List<Object> param);  
  
    
	/**
	 * Select count(*) from
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	 Long count(String hql, Map<String, Object> params);
    
    /** 
     * 执行HQL语句 
     *  
     * @param hql 
     * @return 响应数目 
     */  
     Integer executeHql(String hql);  
  
    /** 
     * 执行HQL语句 
     *  
     * @param hql 
     * @param param 
     * @return 响应数目 
     */  
     Integer executeHql(String hql, Object... param);  
  
    /** 
     * 执行HQL语句 
     *  
     * @param hql 
     * @param param 
     * @return 
     */  
     Integer executeHql(String hql, List<Object> param);  
    
    
    

	/**
	 * 执行HQL语句
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	 Integer executeHql(String hql, Map<String, Object> params)
			throws Exception;
  
	/**
	 * 公共处理分页的方法   分页参数线程绑定
	 * @param hql      from Organization where parent.id=?
	 * @param countHQL  from Organization where parent.id=?   查询总记录数的 HQL，当复杂子查询，需要该参数，简单查询可以由程序生成countHQL
	 * @param args
	 * @return   AoData
	 */
	 AoData findPage(String countHQL, String hql, Object... args);
	/**
	 * 公共处理分页的方法   分页参数线程绑定
	 * @param hql      from Organization where parent.id=?
	 * @param countHQL  from Organization where parent.id=?   查询总记录数的 HQL，当复杂子查询，需要该参数，简单查询可以由程序生成countHQL
	 * @param params
	 * @return   AoData
	 */
	 AoData findPage(String countHQL, String hql, Map<String, Object> params);
	
	
	 AoData findPage(String countHQL, String hql, Map<String, Object> params, Object... args);
}  
