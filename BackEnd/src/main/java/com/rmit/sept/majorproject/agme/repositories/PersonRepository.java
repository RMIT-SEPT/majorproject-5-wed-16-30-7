package com.rmit.sept.majorproject.agme.repositories;

import com.rmit.sept.majorproject.agme.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
//    @Override
//    Iterable<Person> findAllById(Iterable<Long> iterable);

    @Override
    List<Person> findAll();

    Person findByUsername(String username);

    Person getById(Long id);

}
