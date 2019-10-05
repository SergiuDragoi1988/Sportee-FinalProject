package com.example.demo.data.repositories;

import com.example.demo.data.DAO.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer > {

}
