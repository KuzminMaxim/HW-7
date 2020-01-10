package com.example.hw7.repository;

import com.example.hw7.model.Store;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends CrudRepository <Store, Integer> {

    List<Store> findAll ();

    Store findStoreById(int id);

    List<Store> findAllByDistrict(String district);

}
