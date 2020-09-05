package com.rmit.sept.majorproject.agme.repositories;

import com.rmit.sept.majorproject.agme.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long>  {
//    @Override
//    Iterable<Person> findAllById(Iterable<Long> iterable);

    @Override
    List<Person> findAll();

}
