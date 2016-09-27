/**
 * 
 */
package com.booking.dao.dto;

import java.util.List;

import com.booking.dao.dto.BookingTableDto;
import com.booking.dao.dto.CustomerDto;

/**
 * @author jitesh.kumar
 *
 */
public class ArchivedCustomerDto extends CustomerDto {

	public ArchivedCustomerDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArchivedCustomerDto(Long id, String name, boolean loyalCustomer,
			double billedAmount, long contactNumber, int noOfSeats,
			List<BookingTableDto> bookedTables) {
		super();
		this.id = id;
		this.name = name;
		this.loyalCustomer = loyalCustomer;
		this.billedAmount = billedAmount;
		this.contactNumber = contactNumber;
		this.noOfSeats = noOfSeats;
		this.bookedTables = bookedTables;
	}

}
