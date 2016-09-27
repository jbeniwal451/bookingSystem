/**
 * 
 */
package com.booking.dao.dto;

import java.util.List;

import com.booking.dao.entity.BookingTable;

/**
 * @author jitesh.kumar
 *
 */
public class CustomerDto {

	protected Long id;
	protected String name;
	protected boolean loyalCustomer;
	protected long contactNumber;
	protected int noOfSeats;
	protected List<BookingTableDto> bookedTables;
	protected double billedAmount;

	public CustomerDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerDto(Long id, String name, boolean loyalCustomer,
			long contactNumber, int noOfSeats,
			List<BookingTableDto> bookedTables, double billedAmount) {
		super();
		this.id = id;
		this.name = name;
		this.loyalCustomer = loyalCustomer;
		this.contactNumber = contactNumber;
		this.noOfSeats = noOfSeats;
		this.bookedTables = bookedTables;
		this.billedAmount = billedAmount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isLoyalCustomer() {
		return loyalCustomer;
	}

	public void setLoyalCustomer(boolean loyalCustomer) {
		this.loyalCustomer = loyalCustomer;
	}

	public double getBilledAmount() {
		return billedAmount;
	}

	public void setBilledAmount(double billedAmount) {
		this.billedAmount = billedAmount;
	}

	public long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public List<BookingTableDto> getBookedTables() {
		return bookedTables;
	}

	public void setBookedTables(List<BookingTableDto> bookedTables) {
		this.bookedTables = bookedTables;
	}

	public int getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	@Override
	public String toString() {
		return "CustomerDto [id=" + id + ", name=" + name + ", loyalCustomer="
				+ loyalCustomer + ", contactNumber=" + contactNumber
				+ ", noOfSeats=" + noOfSeats + ", bookedTables=" + bookedTables
				+ ", billedAmount=" + billedAmount + "]";
	}


}
