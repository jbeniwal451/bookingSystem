package com.booking.model;

public class BookingTable {

	private int id;
	private int capacity;
	private boolean available;
	private String view;
	private Customer customer;

	public BookingTable() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public BookingTable(int id, int capacity, boolean available, String view,
			Customer customer) {
		super();
		this.id = id;
		this.capacity = capacity;
		this.available = available;
		this.view = view;
		this.customer = customer;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	@Override
	public String toString() {
		return "BookingTable [id=" + id + ", capacity=" + capacity
				+ ", available=" + available + ", view=" + view + ", customer="
				+ customer + "]";
	}
	
	

}
