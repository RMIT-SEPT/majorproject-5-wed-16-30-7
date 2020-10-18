package com.rmit.sept.majorproject.agme.model;

import java.util.Date;

public class BookingInfo {
	private Long booking_id;

	private Long user_id;

	private Long service_id;

	private String service_name;

	private Long provider_id;

	private String provider_name;

	// status: ongoing, completed, cancelled
	private String status;

	private Date booking_date;

	public BookingInfo() {

	}

	public BookingInfo(Long booking_id, Long user_id, Long service_id, String service_name, Long provider_id, String provider_name, String status, Date booking_date) {
		this.booking_id = booking_id;
		this.user_id = user_id;
		this.service_id = service_id;
		this.service_name = service_name;
		this.provider_id = provider_id;
		this.provider_name = provider_name;
		this.status = status;
		this.booking_date = booking_date;
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

	public Long getService_id() {
		return service_id;
	}

	public void setService_id(Long service_id) {
		this.service_id = service_id;
	}

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public Long getProvider_id() {
		return provider_id;
	}

	public void setProvider_id(Long provider_id) {
		this.provider_id = provider_id;
	}

	public String getProvider_name() {
		return provider_name;
	}

	public void setProvider_name(String provider_name) {
		this.provider_name = provider_name;
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
