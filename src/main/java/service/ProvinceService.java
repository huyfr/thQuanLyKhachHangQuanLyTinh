package service;

import model.ProvinceEntity;

public interface ProvinceService {
    public Iterable<ProvinceEntity> findAll();

    public ProvinceEntity findById(Integer id);

    public void save(ProvinceEntity province);

    public void  remove(Integer id);
}
