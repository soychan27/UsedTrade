package com.study.usedtrade.repository;

import com.study.usedtrade.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
