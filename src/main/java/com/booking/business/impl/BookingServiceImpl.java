/**
 * 
 */
package com.booking.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.booking.business.IBookingService;
import com.booking.dao.base.ICustomerDao;
import com.booking.dao.dto.BookingTableDto;
import com.booking.dao.dto.CustomerDto;
import com.booking.model.BookingTable;
import com.booking.model.Customer;

/**
 * @author jitesh.kumar
 *
 */
public class BookingServiceImpl implements IBookingService<Customer, Long> {

	@Autowired
	ICustomerDao<CustomerDto, Long> customerDAO;

	@Autowired
	public boolean checkAvailibility(int numberOfSeats) {
		return true;
		// return customerDAO.checkAvailablity(numberOfSeats);
	}

	public Long bookInAdvance(int numberOfSeats) {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setNoOfSeats(numberOfSeats);
		Long persistedId = customerDAO.addCustomer(customerDto);
		return persistedId;
	}

	public Long bookOnSpot(int numberOfSeats) {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setNoOfSeats(numberOfSeats);
		Long persistedId = customerDAO.addCustomer(customerDto);
		return persistedId;
	}

	public Long modifyBooking(Customer customer) {
		CustomerDto customerDto = customerDAO.getCustomer(customer.getId());
		if (customerDto == null)
			return null;
		customerDto = getCustomerDto(customer);
		return customerDAO.updateCustomer(customerDto);
	}

	public boolean cancelBooking(long customerId) {
		try {
			CustomerDto customerDto = new CustomerDto();
			customerDto.setId(customerId);
			customerDAO.removeCustomer(customerDto);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Customer getCustomer(long customerId) {

		return getCustomerFromDto(customerDAO.getCustomer(customerId));
	}

	/*
	 * utility classes to perform object transformations private scope to all
	 * below methods, no outside object access
	 */

	private CustomerDto getCustomerDto(Customer instance) {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setId(instance.getId());
		customerDto.setName(instance.getName());
		customerDto.setContactNumber(instance.getContactNumber());
		customerDto.setNoOfSeats(instance.getNoOfSeats());
		customerDto.setBookedTables(createBookingTableDtoList(instance
				.getBookedTables()));

		return customerDto;
	}

	private List<BookingTableDto> createBookingTableDtoList(
			List<BookingTable> listInstance) {

		List<BookingTableDto> bookingTables = new ArrayList<BookingTableDto>();
		for (BookingTable table : listInstance) {
			bookingTables.add(createBookingTableDto(table));
		}
		return bookingTables;
	}

	private BookingTableDto createBookingTableDto(BookingTable instance) {

		BookingTableDto bookingTable = new BookingTableDto();
		bookingTable.setId(instance.getId());
		bookingTable.setAvailable(false);
		bookingTable.setCapacity(instance.getCapacity());
		bookingTable.setView(instance.getView());

		return bookingTable;
	}

	private Customer getCustomerFromDto(CustomerDto customerDto) {
		Customer customer = new Customer();
		customer.setId(customerDto.getId());
		customer.setName(customerDto.getName());
		customer.setContactNumber(customerDto.getContactNumber());
		customer.setNoOfSeats(customerDto.getNoOfSeats());
		customer.setBookedTables(getBookingTableList(customerDto
				.getBookedTables()));

		return customer;
	}

	private BookingTable getBookingTableFromDto(BookingTableDto bookingTableDto) {
		BookingTable bookingTable = new BookingTable();

		bookingTable.setId(bookingTableDto.getId());
		bookingTable.setAvailable(bookingTableDto.isAvailable());
		bookingTable.setCapacity(bookingTableDto.getCapacity());
		bookingTable.setView(bookingTableDto.getView());

		return bookingTable;
	}

	private List<BookingTable> getBookingTableList(
			List<BookingTableDto> bookedTables) {

		List<BookingTable> bookingTables = new ArrayList<BookingTable>();
		for (BookingTableDto table : bookedTables) {
			bookingTables.add(getBookingTableFromDto(table));
		}
		return bookingTables;
	}

}
