package com.example.hw7.repository;

import com.example.hw7.model.Buyer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyerRepository extends CrudRepository <Buyer, Integer> {

    List<Buyer> findAll ();

    Buyer findBuyerById(int id);

    @Query( value = "SELECT distinct buyer.id, buyer.family, buyer.district, buyer.discount_percent\n" +
            "from buyer\n" +
            "group by buyer.district",
            nativeQuery = true)
    List<Buyer> queryDistinctByBuyerDistrict();

    List<Buyer> findAllByDistrict(String district);

}
