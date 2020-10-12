package com.rmit.sept.majorproject.agme.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "provider")
public class Provider {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long provider_id;

	@NotBlank
	private String provider_name;

	public Provider() {

	}

	public Provider(Long provider_id, String provider_name) {
		this.provider_id = provider_id;
		this.provider_name = provider_name;
	}

	public Long getProvider_id() {
		return provider_id;
	}

	public void setProvider_id(Long prodiver_id) {
		this.provider_id = prodiver_id;
	}

	public String getProvider_name() {
		return provider_name;
	}

	public void setProvider_name(String provider_name) {
		this.provider_name = provider_name;
	}
}
