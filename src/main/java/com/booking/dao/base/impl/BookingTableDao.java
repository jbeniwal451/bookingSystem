/**
 * 
 */
package com.booking.dao.base.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.booking.dao.base.DaoHibernateBase;
import com.booking.dao.base.IBookingTableDao;
import com.booking.dao.dto.BookingTableDto;
import com.booking.dao.entity.BookingTable;

/**
 * @author jitesh.kumar
 *
 */
public class BookingTableDao extends DaoHibernateBase<BookingTable, Long>
		implements IBookingTableDao<BookingTableDto, Long> {

	@Autowired
	private BookingTableDao bookingTableDao;

	public BookingTableDto bookTable(final BookingTableDto newInstance) {

		BookingTableDto bookedTable = null;
		List<BookingTable> retrievedTables = null;
		int requiredCapacity = newInstance.getCapacity();

		DetachedCriteria criteria = DetachedCriteria
				.forClass(BookingTable.class);
		criteria.add(Restrictions.eq("capacity", requiredCapacity));
		retrievedTables = readAllByCriteria(criteria);
		if (null == retrievedTables) {
			criteria = DetachedCriteria.forClass(BookingTable.class);
			criteria.add(Restrictions.gt("capacity", requiredCapacity));
			retrievedTables = readAllByCriteria(criteria);
		}
		if (null != retrievedTables) {
			// code to modify table's state and then create DTO

			bookedTable = createBookingTableDto(retrievedTables.get(0));
		}
		return bookedTable;
	}

	public BookingTableDto getTableById(Long id) {
		return createBookingTableDto(read(id));
	}

	public List<BookingTableDto> getAllBookedTables() {

		List<BookingTable> retrievedTables = null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(BookingTable.class);
		criteria.add(Restrictions.eq("available", false));
		retrievedTables = readAllByCriteria(criteria);
		return createBookingTableDtoList(retrievedTables);
	}

	public List<BookingTableDto> getAvailableTables() {

		List<BookingTable> retrievedTables = null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(BookingTable.class);
		criteria.add(Restrictions.eq("available", true));
		retrievedTables = readAllByCriteria(criteria);
		return createBookingTableDtoList(retrievedTables);
	}

	public boolean unbookTable(Long id) {
		BookingTable persistedTable = null;
		persistedTable = read(id);
		if (null == persistedTable) {
			// throw some exception
			return false;
		}
		delete(persistedTable);
		return true;

	}

	public boolean isTableAvailable(int capacity) {
		return (getAvailableTables() != null);
	}

	/*
	 * utility classes to perform object transformations, default scope to all
	 * below methods, no outside package access
	 */
	BookingTableDto createBookingTableDto(BookingTable instance) {

		BookingTableDto bookingTable = new BookingTableDto();
		bookingTable.setId(instance.getId());
		bookingTable.setAvailable(false);
		bookingTable.setCapacity(instance.getCapacity());
		bookingTable.setView(instance.getView());

		return bookingTable;
	}

	List<BookingTableDto> createBookingTableDtoList(
			List<BookingTable> listInstance) {

		List<BookingTableDto> bookingTables = new ArrayList<BookingTableDto>();
		for (BookingTable table : listInstance) {
			bookingTables.add(createBookingTableDto(table));
		}
		return bookingTables;
	}

	BookingTable getBookingTableEntityFromDto(BookingTableDto bookingTableDto) {
		BookingTable bookingTable = new BookingTable();

		bookingTable.setId(bookingTableDto.getId());
		bookingTable.setAvailable(bookingTableDto.isAvailable());
		bookingTable.setCapacity(bookingTableDto.getCapacity());
		bookingTable.setView(bookingTableDto.getView());

		return bookingTable;
	}

	List<BookingTable> getBookingTableEntityList(
			List<BookingTableDto> bookedTables) {

		List<BookingTable> bookingTables = new ArrayList<BookingTable>();
		for (BookingTableDto table : bookedTables) {
			bookingTables.add(getBookingTableEntityFromDto(table));
		}
		return bookingTables;
	}

}
