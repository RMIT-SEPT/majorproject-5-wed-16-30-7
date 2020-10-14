package com.rmit.sept.majorproject.agme.model;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	private Long service_id;

	@NotBlank
	private Long provider_id;

	// status: ongoing, completed, cancelled
	@NotBlank
	private String status;

	@NotBlank
	private Date booking_date;

	public Booking() {

	}

	public Booking(Long booking_id, @NotBlank Long user_id, @NotBlank Long service_id, @NotBlank Long provider_id, @NotBlank String status, @NotBlank Date booking_date) {
		this.booking_id = booking_id;
		this.user_id = user_id;
		this.service_id = service_id;
		this.provider_id = provider_id;
//		this.booking_name = booking_name;
		this.status = status;
		this.booking_date = booking_date;
	}

	// getters and setters
	public Long getService_id() {
		return service_id;
	}

	public void setService_id(Long service_id) {
		this.service_id = service_id;
	}

	public Long getProvider_id() {
		return provider_id;
	}

	public void setProvider_id(Long provider_id) {
		this.provider_id = provider_id;
	}

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

//	public String getBooking_name() {
//		return booking_name;
//	}
//
//	public void setBooking_name(String booking_name) {
//		this.booking_name = booking_name;
//	}

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
