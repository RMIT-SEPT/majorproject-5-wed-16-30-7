package com.rmit.sept.majorproject.agme.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "booking")
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long booking_id;

	// id of user who did the booking
	@NotBlank
	private Long user_id;

	@NotBlank
	private String booking_name;

	// status: ongoing, completed, cancelled
	@NotBlank
	private String status;

	@NotBlank
	private Date booking_date;

	public Booking() {

	}

	public Booking(Long booking_id, @NotBlank Long user_id, @NotBlank String booking_name, @NotBlank String status, @NotBlank Date booking_date) {
		this.booking_id = booking_id;
		this.user_id = user_id;
		this.booking_name = booking_name;
		this.status = status;
		this.booking_date = booking_date;
	}

	// getters and setters
	public Long getBooking_id() {
		return booking_id;
	}

	public void setBooking_id(Long booking_id) {
		this.booking_id = booking_id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getBooking_name() {
		return booking_name;
	}

	public void setBooking_name(String booking_name) {
		this.booking_name = booking_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getBooking_date() {
		return booking_date;
	}

	public void setBooking_date(Date booking_date) {
		this.booking_date = booking_date;
	}
}
