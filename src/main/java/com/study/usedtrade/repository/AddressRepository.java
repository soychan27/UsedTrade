package com.study.usedtrade.repository;

import com.study.usedtrade.model.Address;
import com.study.usedtrade.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findByUser(User user);
}