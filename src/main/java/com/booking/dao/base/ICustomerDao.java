/**
 * 
 */
package com.booking.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.booking.dao.dto.CustomerDto;

/**
 * @author jitesh.kumar
 *
 */
public interface ICustomerDao<T, ID extends Serializable> {

	public ID addCustomer(T newInstance);

	public CustomerDto getCustomer(ID id);

	public ID updateCustomer(T newInstance);

	public void removeCustomer(T persistentObject);

	public List<T> getCurrentCustomers(DetachedCriteria detachedCriteria);

}
