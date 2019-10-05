package com.example.demo.data.repositories;

import com.example.demo.data.DAO.AuthenticationInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticationInfoRepository extends CrudRepository<AuthenticationInfo, Integer > {
Optional<AuthenticationInfo> findByUserName(String userName);


}
