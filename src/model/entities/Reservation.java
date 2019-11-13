package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	public Reservation() {
		
	}	
	
	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) throws DomainException {
		if (!checkOut.after(checkIn)) {
			
			throw new DomainException("Check-out date must be after check-in date!");
		}
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	
	public Date getCheckIn() {
		return checkIn;
	}
	
	public Date getCheckOut() {
		return checkOut;
	}
	
	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	public void updateDates(Date checkin, Date checkout) throws DomainException {
		Date now = new Date();
		
		if(checkin.before(now) || checkout.before(now)) {
			
			throw new DomainException("Reservation dates for update must be future dates!");
			
		} 
		if (!checkout.after(checkin)) {
			
			throw new DomainException("Check-out date must be after check-in date!");
		}
		
		this.checkIn = checkin;
		this.checkOut = checkout;
	}
	
	@Override
	public String toString() {
		return "Room " 
	    + roomNumber  
	    + ", check-in: " 
	    + sdf.format(checkIn)
	    + "check-out: "
	    + sdf.format(checkOut)
	    + ", "
	    + duration()
	    + "nights ";
	}
	

}