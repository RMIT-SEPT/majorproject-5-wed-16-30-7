package com.rmit.sept.majorproject.agme.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "services")
public class Services {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long service_id;

	// id of the provider providing this service
	@NotBlank
	private Long provider_id;

	@NotBlank
	private String service_name;

	public Services(Long service_id, @NotBlank Long provider_id, @NotBlank String service_name) {
		this.service_id = service_id;
		this.provider_id = provider_id;
		this.service_name = service_name;
	}

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

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
}
