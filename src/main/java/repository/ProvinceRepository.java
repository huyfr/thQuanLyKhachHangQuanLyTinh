package repository;

import model.ProvinceEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProvinceRepository extends PagingAndSortingRepository<ProvinceEntity, Integer> {
}
