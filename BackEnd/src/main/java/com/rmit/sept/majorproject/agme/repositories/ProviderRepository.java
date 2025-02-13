package com.rmit.sept.majorproject.agme.repositories;

import com.rmit.sept.majorproject.agme.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {

	@Override
	List<Provider> findAll();
}
