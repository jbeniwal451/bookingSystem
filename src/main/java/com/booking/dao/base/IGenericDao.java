/**
 * generic interface for bookingSystem CRUD operations
 */
package com.booking.dao.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;

/**
 * @author jitesh.kumar
 *
 */
public interface IGenericDao<T,ID extends Serializable> {

	public ID create(T newInstance );

	public T read(ID id);

	public void saveOrUpdate(T transientObject);

	public void delete(T persistentObject);
	
	public List<T> readAllByCriteria(DetachedCriteria detachedCriteria);

}
