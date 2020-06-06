package service.impl;

import model.CustomerEntity;
import model.ProvinceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import repository.CustomerRepository;
import service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Iterable<CustomerEntity> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerEntity findById(Integer id) {
        return customerRepository.findOne(id);
    }

    @Override
    public void save(CustomerEntity customer) {
        customerRepository.save(customer);
    }

    @Override
    public void remove(Integer id) {
        customerRepository.delete(id);
    }

    @Override
    public Iterable<CustomerEntity> findAllByProvinceByProvinceId(ProvinceEntity province) {
        return customerRepository.findAllByProvinceByProvinceId(province);
    }
}