package com.mm.back.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.mm.back.common.AoData;
import com.mm.back.dao.BaseDao;
import com.mm.common.utils.PageUtil;

/**
 * 
* @Description: hibernate 基础操作类
* @date 2015年9月30日 下午1:46:47
* @param <T>
 */
@Repository()
public class BaseDaoImpl<T> implements BaseDao<T> {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public T load(Class<T> T, Serializable id) {
		return (T) this.getCurrentSession().load(T, id);
	}

	public T get(Class<T> c, Serializable id) {
		return (T) this.getCurrentSession().get(c, id);
	}

	public T get(String hql, Object... param) {
		List<T> l = this.find(hql, param);
		if (l != null && l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}

	public T get(String hql, List<Object> param) {
		List<T> l = this.find(hql, param);
		if (l != null && l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}

	@Override
	public T get(String hql, Map<String, Object> params) {
		// 查询出来的结果集
		List<T> list = this.find(hql, params);
		// 如果结果非空
		if (list != null && list.size() > 0) {
			// 返回第一个对象
			return list.get(0);
		}
		return null;
	}

	public Serializable save(T o) {
		return this.getCurrentSession().save(o);
	}

	public void saveOrUpdate(T o) {
		this.getCurrentSession().saveOrUpdate(o);
	}

	public void delete(T o) {
		this.getCurrentSession().delete(o);
	}

	public void update(T o) {
		this.getCurrentSession().update(o);
	}

	public List<T> find(String hql) {
		return this.getCurrentSession().createQuery(hql).list();
	}
	
	
	@Override
	public List<T> findByIds(String hql, List<Integer> ids) {
		return this.getCurrentSession().createQuery(hql).setParameterList("ids",ids).list();
	}
	@Override
	public List<T> findByIds(String hql, Object[] ids) {
		return this.getCurrentSession().createQuery(hql).setParameterList("ids",ids).list();
	}
	@Override
	public List<T> findIn(String hql,String name, Collection values) {
		return this.getCurrentSession().createQuery(hql).setParameterList(name,values).list();
	}
	
	@Override
	public T findFirst(String hql, Object... params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				q.setParameter(i, params[i]);
			}
		}
		List<T> list = q.list();
		if (list!=null&&list.size()>0) {
			return  list.get(0);
		}
		return null;
	}
	
	
	@Override
	public T findFirst(String hql, Map<String, Object> params) {
		// 创建Query
		Query query = this.getCurrentSession().createQuery(hql);

		// 如果参数非空
		if (params != null && params.size() > 0) {
			// 设置参数
			this.setParameters(query, params);
		}
		List<T> list = query.list();
		if (list!=null&&list.size()>0) {
			return  list.get(0);
		}
		return null;
	}
	  
	public List<T> find(String hql, Object... params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				q.setParameter(i, params[i]);
			}
		}
		return q.list();
	}

	public List<T> find(String hql, List<Object> param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.list();
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params) {
		// 创建Query
		Query query = this.getCurrentSession().createQuery(hql);

		// 如果参数非空
		if (params != null && params.size() > 0) {
			// 设置参数
			this.setParameters(query, params);
		}
		// 返回查询结果
		return query.list();
	}

	public List<T> find(String hql, Integer startRow, Integer pageSize) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.setFirstResult(startRow).setMaxResults(pageSize).list();
	}

	public List<T> find(String hql, Integer startRow, Integer pageSize, Object... param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.setFirstResult(startRow).setMaxResults(pageSize).list();
	}

	public List<T> find(String hql, List<Object> param, Integer startRow, Integer pageSize) {

		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.setFirstResult(startRow).setMaxResults(pageSize).list();
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params, int startRow, int pageSize) {
		// 创建Query
		Query query = this.getCurrentSession().createQuery(hql);

		// 如果参数非空
		if (params != null && params.size() > 0) {
			// 设置参数
			this.setParameters(query, params);
		}
		// 查询结果并返回
		return query.setFirstResult(startRow).setMaxResults(pageSize).list();
	}

	public Integer count(String hql) {
		Object object = this.getCurrentSession().createQuery(hql).uniqueResult();
		return Integer.parseInt(object.toString());
	}



	public Integer count(String hql, List<Object> param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return (Integer) q.uniqueResult();
	}
	
	@Override
	public Long count(String hql, Object... param) {
		// 给传递过来的hql添加前缀内容
		hql = "select count(*) " + hql;

		// 创建Query
		Query query = this.getCurrentSession().createQuery(hql);

		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				query.setParameter(i, param[i]);
			}
		}

		// 返回查询结果
		return (Long) query.uniqueResult();
	}
	
	@Override
	public Long count(String hql, Map<String, Object> params) {
		// 给传递过来的hql添加前缀内容
		hql = "select count(*) " + hql;

		// 创建Query
		Query query = this.getCurrentSession().createQuery(hql);

		// 如果参数非空
		if (params != null && params.size() > 0) {
			// 设置参数
			this.setParameters(query, params);
		}

		// 返回查询结果
		return (Long) query.uniqueResult();
	}

	public Integer executeHql(String hql) {
		return this.getCurrentSession().createQuery(hql).executeUpdate();
	}

	public Integer executeHql(String hql, Object... param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.executeUpdate();
	}

	public Integer executeHql(String hql, List<Object> param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.executeUpdate();
	}

	@Override
	public Integer executeHql(String hql, Map<String, Object> params) {
		// 创建Query
		Query query = this.getCurrentSession().createQuery(hql);
		// 如果参数不为null
		if (params != null && params.size() > 0) {
			// 设置参数
			this.setParameters(query, params);
		}
		// 执行HQL
		return query.executeUpdate();
	}

	/**
	 * 给Query赋值的方法
	 * 
	 * @param query
	 *            Hibernate Query对象
	 * @param params
	 *            Map集合参数
	 */
	private void setParameters(Query query, Map<String, Object> params) {

		// 获取所有Key
		Set<String> keys = params.keySet();

		// 循环取值,赋值
		for (String key : keys) {

			// 获取到参数中的对象
			Object obj = params.get(key);

			// 判断参数类型
			if (obj instanceof Collection<?>) {// 是集合
				// System.out.println("参数为集合的大小为:" + ((Collection<?>)
				// obj).size());
				query.setParameterList(key, (Collection<?>) obj);
			} else if (obj instanceof Object[]) {// 是数组
				// System.out.println("参数为数姐的大小为:" + ((Object[]) obj).length);
				query.setParameterList(key, (Object[]) obj);
			} else {// 普通对象
				// System.out.println("普通对象");
				query.setParameter(key, obj);
			}
		}
	}

	/**
	 * 公共模块处理分页的方法
	 * 
	 * @param hql
	 *            from Organization where parent.id=?
	 * @param countHQL
	 *            from Organization where parent.id=? 查询总记录数的
	 *            HQL，当复杂子查询，需要该参数，简单查询可以由程序生成countHQL
	 * @param args
	 * @return
	 */
	public AoData findPage(String countHQL, String hql, Object... args) {
		AoData aoData = new AoData();
		if (countHQL == null)
			countHQL = createCountHQL(hql);
		// 先查询总记录数
		Query countQuery = getCurrentSession().createQuery(countHQL);
		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				countQuery.setParameter(i, args[i]);
			}
		}
		long count = (Long) countQuery.uniqueResult();
		// 查询分页的List集合
		Query listQuery = getCurrentSession().createQuery(hql);
		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				listQuery.setParameter(i, args[i]);
			}
		}
		Integer startRow = PageUtil.getStartRow();
		Integer pageSize = PageUtil.getPageSize();
		listQuery.setFirstResult(startRow);
		listQuery.setMaxResults(pageSize);
		aoData.setDatas(listQuery.list());
		aoData.setiDisplayStart(startRow);
		aoData.setiDisplayLength(pageSize);
		aoData.setiTotalDisplayRecords((int) count);
		aoData.setiTotalRecords((int) count);

		return aoData;
	}

	// 根据简单的hql生成查询总记录数的hql
	private String createCountHQL(String hql) {
		return "select count(*) " + hql.substring(hql.indexOf("from"));
	}

	@Override
	public AoData findPage(String countHQL, String hql, Map<String, Object> params) {

		AoData aoData = new AoData();
		if (countHQL == null)
			countHQL = createCountHQL(hql);
		// 先查询总记录数
		Query countQuery = getCurrentSession().createQuery(countHQL);
		// 如果参数非空
		if (params != null && params.size() > 0) {
			// 设置参数
			this.setParameters(countQuery, params);
		}
		long count = (Long) countQuery.uniqueResult();
		// 查询分页的List集合
		Query listQuery = getCurrentSession().createQuery(hql);
		// 如果参数非空
		if (params != null && params.size() > 0) {
			// 设置参数
			this.setParameters(listQuery, params);
		}
		Integer startRow = PageUtil.getStartRow();
		Integer pageSize = PageUtil.getPageSize();
		listQuery.setFirstResult(startRow);
		listQuery.setMaxResults(pageSize);
		aoData.setDatas(listQuery.list());
		aoData.setiDisplayStart(startRow);
		aoData.setiDisplayLength(pageSize);
		aoData.setiTotalDisplayRecords((int) count);
		aoData.setiTotalRecords((int) count);

		return aoData;

	}

	@Override
	public AoData findPage(String countHQL, String hql, Map<String, Object> params, Object... args) {
		AoData aoData = new AoData();
		if (countHQL == null)
			countHQL = createCountHQL(hql);
		// 先查询总记录数
		Query countQuery = getCurrentSession().createQuery(countHQL);
		// 如果参数非空
		if (params != null && params.size() > 0) {
			// 设置参数
			this.setParameters(countQuery, params);
		}
		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				countQuery.setParameter(i, args[i]);
			}
		}
		long count = (Long) countQuery.uniqueResult();
		// 查询分页的List集合
		Query listQuery = getCurrentSession().createQuery(hql);
		// 如果参数非空
		if (params != null && params.size() > 0) {
			// 设置参数
			this.setParameters(listQuery, params);
		}
		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				listQuery.setParameter(i, args[i]);
			}
		}

		Integer startRow = PageUtil.getStartRow();
		Integer pageSize = PageUtil.getPageSize();
		listQuery.setFirstResult(startRow);
		listQuery.setMaxResults(pageSize);
		aoData.setDatas(listQuery.list());
		aoData.setiDisplayStart(startRow);
		aoData.setiDisplayLength(pageSize);
		aoData.setiTotalDisplayRecords((int) count);
		aoData.setiTotalRecords((int) count);

		return aoData;

	}

	



	

}
