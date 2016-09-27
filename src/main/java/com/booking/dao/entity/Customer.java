package com.booking.dao.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class Customer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8290965819288877201L;
	private Long id;
	private String name;
	private boolean loyalCustomer;
	private long contactNumber;
	protected int noOfSeats;
	private List<BookingTable> bookedTables;
	private double billedAmount;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(Long id, String name, boolean loyalCustomer,
			long contactNumber, int noOfSeats, List<BookingTable> bookedTables,
			double billedAmount) {
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

	public List<BookingTable> getBookedTables() {
		return bookedTables;
	}

	public void setBookedTables(List<BookingTable> bookedTables) {
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
		return "Customer [id=" + id + ", name=" + name + ", loyalCustomer="
				+ loyalCustomer + ", contactNumber=" + contactNumber
				+ ", noOfSeats=" + noOfSeats + ", bookedTables=" + bookedTables
				+ ", billedAmount=" + billedAmount + "]";
	}

}