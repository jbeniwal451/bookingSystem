/**
 * 
 */
package com.booking.dao.base.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import com.booking.dao.base.DaoHibernateBase;
import com.booking.dao.base.ICustomerDao;
import com.booking.dao.dto.BookingTableDto;
import com.booking.dao.dto.CustomerDto;
import com.booking.dao.entity.BookingTable;
import com.booking.dao.entity.Customer;

/**
 * @author jitesh.kumar
 *
 */
public class CustomerDao extends DaoHibernateBase<Customer, Long> implements
		ICustomerDao<CustomerDto, Long> {

	@Autowired
	private BookingTableDao bookingTableDao;

	/*
	 * @Autowired private ArchiveCustomerDao archiveCustomerDao;
	 */

	public Long addCustomer(CustomerDto newInstance) {

		Long persistentId = null;
		Customer customer = new Customer();

		BookingTableDto transientTable = new BookingTableDto();
		transientTable.setCapacity(newInstance.getNoOfSeats());
		transientTable.setCustomerDto(newInstance);
		transientTable = bookingTableDao.bookTable(transientTable);

		if (null != transientTable) {
			ArrayList<BookingTable> bookedTables = new ArrayList<BookingTable>();
			bookedTables.add(bookingTableDao
					.getBookingTableEntityFromDto(transientTable));
			customer.setBookedTables(bookedTables);
			persistentId = (Long) create(customer);
		}
		return persistentId;
	}

	public CustomerDto getCustomer(Long id) {
		return createCustomerDto(read(id));
	}

	public Long updateCustomer(CustomerDto customer) {
		CustomerDto persistedCustomer = null;
		long persistentId;

		if ((persistedCustomer = getCustomer((Long) customer.getId())) != null) {
			persistentId = (long) persistedCustomer.getId();
			if (!persistedCustomer.getBookedTables().equals(
					customer.getBookedTables())) {
				throw new UnsupportedOperationException();
			} else {
				saveOrUpdate(getCustomerEntityFromDto(customer));
			}
		} else {
			persistentId = (Long) addCustomer(customer);
		}
		return persistentId;
	}

	public void removeCustomer(CustomerDto persistentObject) {
		delete(getCustomerEntityFromDto(persistentObject));

	}

	public List<CustomerDto> getCurrentCustomers(
			DetachedCriteria detachedCriteria) {
		List<Customer> customers = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		customers = readAllByCriteria(criteria);
		return createCustomerDtoList(customers);
	}

	public boolean checkAvailablity(int capacity) {
		return bookingTableDao.isTableAvailable(capacity);
	}

	/*
	 * utility classes to perform object transformations private scope to all
	 * below methods, no outside object access
	 */

	private CustomerDto createCustomerDto(Customer instance) {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setId(instance.getId());
		customerDto.setName(instance.getName());
		customerDto.setLoyalCustomer(instance.isLoyalCustomer());
		customerDto.setContactNumber(instance.getContactNumber());
		customerDto.setNoOfSeats(instance.getNoOfSeats());
		customerDto.setBookedTables(bookingTableDao
				.createBookingTableDtoList(instance.getBookedTables()));

		return customerDto;

	}

	private List<CustomerDto> createCustomerDtoList(List<Customer> customers) {

		List<CustomerDto> customersDto = new ArrayList<CustomerDto>();
		for (Customer customer : customers) {
			customersDto.add(createCustomerDto(customer));
		}
		return customersDto;
	}

	private Customer getCustomerEntityFromDto(CustomerDto customerDto) {
		Customer customer = new Customer();
		customer.setId(customerDto.getId());
		customer.setName(customerDto.getName());
		customer.setLoyalCustomer(customerDto.isLoyalCustomer());
		customer.setContactNumber(customerDto.getContactNumber());
		customer.setNoOfSeats(customerDto.getNoOfSeats());
		customer.setBookedTables(bookingTableDao
				.getBookingTableEntityList(customerDto.getBookedTables()));

		return customer;
	}

}
