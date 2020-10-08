package com.rmit.sept.majorproject.agme.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "provider")
public class Provider {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long prodiver_id;

	@NotBlank
	private String provider_name;

	public Provider(Long prodiver_id, String provider_name) {
		this.prodiver_id = prodiver_id;
		this.provider_name = provider_name;
	}

	public Long getProdiver_id() {
		return prodiver_id;
	}

	public void setProdiver_id(Long prodiver_id) {
		this.prodiver_id = prodiver_id;
	}

	public String getProvider_name() {
		return provider_name;
	}

	public void setProvider_name(String provider_name) {
		this.provider_name = provider_name;
	}
}
