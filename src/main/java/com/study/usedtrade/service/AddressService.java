package com.study.usedtrade.service;

import com.study.usedtrade.model.Address;
import com.study.usedtrade.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public void write(Address address){
        addressRepository.save(address);
    }
}
