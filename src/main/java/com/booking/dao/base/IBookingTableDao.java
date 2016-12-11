/**
 * 
 */
package com.booking.dao.base;

import java.io.Serializable;
import java.util.List;

/**
 * @author jitesh.kumar
 *
 */
public interface IBookingTableDao<T, ID extends Serializable> {

	public ID bookTable(T newInstance);

	public T getTableById(ID id);

	public List<T> getAllBookedTables();

	public List<T> getAvailableTables();

	public void unbookTable(ID id);

}
