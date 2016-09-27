package com.booking.service.impl;

/**
 * The type that handles implementation for booking system.
 * Will interact and use utilities
 */

import org.springframework.beans.factory.annotation.Autowired;

import com.booking.business.IBookingService;
import com.booking.model.Customer;
import com.booking.service.IBookingSystem;

/**
 * @author jitesh.kumar
 *
 */
public class BookingSystemImpl implements IBookingSystem<String, Customer> {

	@Autowired
	IBookingService<Customer, Long> bookingService;

	/*
	 * 
	 * currently all methods return String, we will use JSON responses and http
	 * error codes when exposing the api as a service
	 */

	public String bookInAdvance(int numberOfSeats) {
		String responseMsg;
		Long customerId = bookingService.bookInAdvance(numberOfSeats);
		if (customerId != null)
			responseMsg = "Unable to create booking. Please try again later.";
		else
			responseMsg = "Booking Created. Please use the link to see your booking : "
					+ "http://URL to the service/?bookingId=" + customerId;

		return responseMsg;
	}

	public String bookOnSpot(int numberOfSeats) {
		String responseMsg;
		Long customerId = bookingService.bookInAdvance(numberOfSeats);
		if (customerId != null)
			responseMsg = "Unable to create booking. Please try again later.";
		else
			responseMsg = "Booking Created. Please use the link to see your booking : "
					+ "http://URL to the service/?bookingId=" + customerId;

		return responseMsg;
	}

	public String modifyBooking(Customer customer) {
		String responseMsg;
		Long customerId = bookingService.modifyBooking(customer);
		if (customerId != null)
			responseMsg = "Unable to modify booking. Please try again later.";
		else
			responseMsg = "Booking modified. Please use the link to see your booking details: "
					+ "http://URL to the service/?bookingId=" + customerId;

		return responseMsg;
	}

	public String cancelBooking(long customerId) {
		String responseMsg;
		boolean isCancelled = bookingService.cancelBooking(customerId);
		if (!isCancelled)
			responseMsg = "Unable to cancel booking. Please try again later.";
		else
			responseMsg = "Booking has been cancelled.";

		return responseMsg;
	}

	public String checkAvailibility(int numberOfSeats) {
		String responseMsg;
		boolean isAvailable = bookingService.checkAvailibility(numberOfSeats);
		if (!isAvailable)
			responseMsg = "No seats available for booking right now. Please try again later.";
		else
			responseMsg = "Seats are available for booking.";

		return responseMsg;
	}

	public String getCustomer(long customerId) {
		String responseMsg;
		Customer customer = bookingService.getCustomer(customerId);
		if (customer == null)
			responseMsg = "No such customer. Please provide correct details.";
		else
			responseMsg = customer.toString();

		return responseMsg;
	}

}
