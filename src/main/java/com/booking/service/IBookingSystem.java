package com.booking.service;

/*
 * service layer that can interact with multiple interfaces
 * 
 * For now will be handling only machine to machine interactions 
 */

public interface IBookingSystem<T,E> {

	public T checkAvailibility(int numberOfSeats);

	public T bookInAdvance(int numberOfSeats);

	public T bookOnSpot(int numberOfSeats);

	public T modifyBooking(E customer);

	public T cancelBooking(long customerId);

	public T getCustomer(long customerId);

	//public String getTableView(int numberOfSeats);

}
