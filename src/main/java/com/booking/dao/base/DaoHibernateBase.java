/**
 * 
 */
package com.booking.dao.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

/**
 * @author jitesh.kumar
 *
 */
public class DaoHibernateBase<T, ID extends Serializable> extends
		HibernateDaoSupport implements IGenericDao<T, Serializable> {

	private Class<T> entityClass;

	public DaoHibernateBase() {
		super();
		entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];

	}

	public void setSessionfactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}

	public Serializable create(T newInstance) {

		return getHibernateTemplate().save(newInstance);
	}

	public T read(Serializable id) {
		T o = (T) getHibernateTemplate().get(entityClass, id);
		return o;
	}

	public void saveOrUpdate(T transientObject) {

		getHibernateTemplate().saveOrUpdate(transientObject);
	}

	public void delete(T persistentObject) {
		getHibernateTemplate().delete(persistentObject);
	}

	public List<T> readAllByCriteria(DetachedCriteria criteria) {
		return (List<T>) getHibernateTemplate().findByCriteria(criteria);
	}

}
