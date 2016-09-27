/**
 * 
 */
package com.booking.dao.dto;


/**
 * @author jitesh.kumar
 *
 */
public class BookingTableDto {

	private int id;
	private int capacity;
	private boolean available;
	private String view;
	private CustomerDto customerDto;

	public BookingTableDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookingTableDto(int id, int capacity, boolean available,
			String view, CustomerDto customerDto) {
		super();
		this.id = id;
		this.capacity = capacity;
		this.available = available;
		this.view = view;
		this.customerDto = customerDto;
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

	public CustomerDto getCustomerDto() {
		return customerDto;
	}

	public void setCustomerDto(CustomerDto customerDto) {
		this.customerDto = customerDto;
	}

	@Override
	public String toString() {
		return "BookingTableDto [id=" + id + ", capacity=" + capacity
				+ ", available=" + available + ", view=" + view
				+ ", customerDto=" + customerDto + "]";
	}

}
