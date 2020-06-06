package repository;

import model.CustomerEntity;
import model.ProvinceEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends PagingAndSortingRepository<CustomerEntity, Integer> {
    Iterable<CustomerEntity> findAllByProvinceByProvinceId(ProvinceEntity province);
}