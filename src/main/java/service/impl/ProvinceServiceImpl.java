package service.impl;

import model.ProvinceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import repository.ProvinceRepository;
import service.ProvinceService;

public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public Iterable<ProvinceEntity> findAll() {
        return provinceRepository.findAll();
    }

    @Override
    public ProvinceEntity findById(Integer id) {
        return provinceRepository.findOne(id);
    }

    @Override
    public void save(ProvinceEntity province) {
        provinceRepository.save(province);
    }

    @Override
    public void remove(Integer id) {
        provinceRepository.delete(id);
    }
}