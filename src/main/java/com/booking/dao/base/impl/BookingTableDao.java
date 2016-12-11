/**
 * 
 */
package com.booking.dao.base.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.booking.dao.base.IBookingTableDao;
import com.booking.dao.dto.BookingTableDto;
import com.booking.dao.entity.BookingTable;

/**
 * @author jitesh.kumar
 *
 */

@Repository
@Transactional(readOnly = true)
public class BookingTableDao implements IBookingTableDao<BookingTableDto, Long> {

	@Autowired
	private BookingTableDao bookingTableDao;

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional(readOnly = false)
	public Long bookTable(final BookingTableDto newInstance) {

		BookingTable bookingTable = getBookingTableEntityFromDto(newInstance);
		Session session = sessionFactory.openSession();
		return (Long) session.save(bookingTable);
	}

	public BookingTableDto getTableById(Long id) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(BookingTable.class);
		criteria.add(Restrictions.eq("ID", id));
		return createBookingTableDto((BookingTable) criteria.uniqueResult());
	}

	public List<BookingTableDto> getAllBookedTables() {

		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(BookingTable.class);
		criteria.add(Restrictions.eq("available", false));
		return createBookingTableDtoList(criteria.list());
	}

	public List<BookingTableDto> getAvailableTables() {

		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(BookingTable.class);
		criteria.add(Restrictions.eq("available", true));
		return createBookingTableDtoList(criteria.list());
	}

	@Transactional(readOnly = false)
	public void unbookTable(Long id) {
		Session session = sessionFactory.openSession();
		session.delete("BookingTable.class", id);

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
