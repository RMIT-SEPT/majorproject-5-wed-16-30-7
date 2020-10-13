package com.rmit.sept.majorproject.agme.repositories;

import com.rmit.sept.majorproject.agme.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

	@Override
	List<Booking> findAll();
}
