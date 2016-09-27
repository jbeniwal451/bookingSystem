package com.booking.model;

import java.util.List;

import com.booking.dao.dto.BookingTableDto;

public class Customer {

	private Long id;
	private String name;
	private long contactNumber;
	protected int noOfSeats;
	private List<BookingTable> bookedTables;
	private double billedAmount;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(Long id, String name, long contactNumber, int noOfSeats,
			List<BookingTable> bookedTables, double billedAmount) {
		super();
		this.id = id;
		this.name = name;
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

	public long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public int getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public List<BookingTable> getBookedTables() {
		return bookedTables;
	}

	public void setBookedTables(List<BookingTable> bookedTables) {
		this.bookedTables = bookedTables;
	}

	public double getBilledAmount() {
		return billedAmount;
	}

	public void setBilledAmount(double billedAmount) {
		this.billedAmount = billedAmount;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", contactNumber="
				+ contactNumber + ", noOfSeats=" + noOfSeats
				+ ", bookedTables=" + bookedTables + ", billedAmount="
				+ billedAmount + "]";
	}

}