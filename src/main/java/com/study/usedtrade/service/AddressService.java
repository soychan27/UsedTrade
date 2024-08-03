package com.study.usedtrade.service;

import com.study.usedtrade.model.Address;
import com.study.usedtrade.model.User;
import com.study.usedtrade.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> addressList(User user) {
        return addressRepository.findByUser(user);
    }

    public void write(Address address) {
        addressRepository.save(address);
    }

    public Address addressView(Integer id) {return addressRepository.findById(id).get();}

    public void addressDelete(Integer id) {addressRepository.deleteById(id);}

    public void save(Address address) {addressRepository.save(address);}
}
