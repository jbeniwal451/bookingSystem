/**
 * 
 */
package com.booking.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author jitesh.kumar
 *
 */
public interface IBookingTableDao<T, ID extends Serializable> {

	public T bookTable(T newInstance);

	public T getTableById(ID id);

	public List<T> getAllBookedTables();

	public List<T> getAvailableTables();

	public boolean unbookTable(ID id);

}
