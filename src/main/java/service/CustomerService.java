package service;

import model.CustomerEntity;
import model.ProvinceEntity;

public interface CustomerService {
    public Iterable<CustomerEntity> findAll();

    public CustomerEntity findById(Integer id);

    public void save(CustomerEntity province);

    public void  remove(Integer id);

    public Iterable<CustomerEntity> findAllByProvinceByProvinceId(ProvinceEntity province);
}
