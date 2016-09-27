/**
 * 
 */
package com.booking.business;

/**
 * @author jitesh.kumar
 *
 */
public interface IBookingService<T,Serializable> {

	public boolean checkAvailibility(int numberOfSeats);

	public Serializable bookInAdvance(int numberOfSeats);

	public Serializable bookOnSpot(int numberOfSeats);

	public Serializable modifyBooking(T customer);

	public boolean cancelBooking(long customerId);

	public T getCustomer(long customerId);
	
}
