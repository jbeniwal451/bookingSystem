/**
 * 
 */
package com.booking.dao.base.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.booking.dao.base.ICustomerDao;
import com.booking.dao.dto.CustomerDto;
import com.booking.dao.entity.Customer;

/**
 * @author jitesh.kumar
 *
 */

@Repository
@Transactional(readOnly = true)
public class CustomerDao implements ICustomerDao<CustomerDto, Long> {

	@Autowired
	private BookingTableDao bookingTableDao;

	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * @Autowired private ArchiveCustomerDao archiveCustomerDao;
	 */
	@Transactional(readOnly = false)
	public Long addCustomer(CustomerDto newInstance) {

		/*
		 * Long persistentId = null; Customer customer = new Customer();
		 * 
		 * BookingTableDto transientTable = new BookingTableDto();
		 * transientTable.setCapacity(newInstance.getNoOfSeats());
		 * transientTable.setCustomerDto(newInstance); transientTable =
		 * bookingTableDao.bookTable(transientTable);
		 * 
		 * if (null != transientTable) { ArrayList<BookingTable> bookedTables =
		 * new ArrayList<BookingTable>(); bookedTables.add(bookingTableDao
		 * .getBookingTableEntityFromDto(transientTable));
		 * customer.setBookedTables(bookedTables); persistentId = (Long)
		 * create(customer); } return persistentId;
		 */
		Customer customer = getCustomerEntityFromDto(newInstance);
		Session session = sessionFactory.openSession();
		return (Long) session.save(customer);
	}

	public CustomerDto getCustomer(Long id) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Customer.class);
		criteria.add(Restrictions.eq("ID", id));
		return createCustomerDto((Customer) criteria.uniqueResult());
	}

	@Transactional(readOnly = false)
	public Long updateCustomer(CustomerDto customer) {
		CustomerDto persistedCustomer = null;
		Long persistentId;

		Session session = sessionFactory.openSession();

		persistedCustomer = getCustomer((Long) customer.getId());
		persistentId = (Long) persistedCustomer.getId();

		if (persistentId == null) {
			persistentId = addCustomer(customer);
		} else {
			session.update(getCustomerEntityFromDto(customer));
		}

		return persistentId;

	}

	@Transactional(readOnly = false)
	public void removeCustomer(CustomerDto persistentObject) {
		Session session = sessionFactory.openSession();
		session.delete(getCustomerEntityFromDto(persistentObject));
	}

	public List<CustomerDto> getCurrentCustomers(
			DetachedCriteria detachedCriteria) {

		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Customer.class);
		return createCustomerDtoList(criteria.list());

	}

	/*
	 * public boolean checkAvailablity(int capacity) { return
	 * bookingTableDao.isTableAvailable(capacity); }
	 */

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
