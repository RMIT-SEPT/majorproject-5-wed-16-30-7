package com.rmit.sept.majorproject.agme.repositories;

import com.rmit.sept.majorproject.agme.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Long> {

	@Override
	List<Services> findAll();
}
