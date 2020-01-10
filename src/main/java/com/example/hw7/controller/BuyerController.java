package com.example.hw7.controller;

import com.example.hw7.model.Buyer;
import com.example.hw7.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BuyerController {

    @Autowired
    BuyerRepository repository;

    @DeleteMapping(value = "/deleteBuyer")
    public ResponseEntity deleteBuyer(Buyer buyer){
        repository.delete(buyer);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping(value = "/patchBuyer")
    public ResponseEntity patchBuyer(Buyer buyer){
        Buyer finderBuyer = repository.findBuyerById(buyer.getId());
        if (finderBuyer != null){
            finderBuyer.setId(buyer.getId());
            finderBuyer.setDiscountPercent(buyer.getDiscountPercent());
            finderBuyer.setDistrict(buyer.getDistrict());
            finderBuyer.setFamily(buyer.getFamily());
            repository.save(finderBuyer);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping(value = "/postBuyer")
    public ResponseEntity postBuyer(Buyer buyer){
        repository.save(buyer);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/getAllBuyer")
    public ResponseEntity<?> getAllBuyer(){
        List<Buyer> allBuyer = repository.findAll();

        if (allBuyer == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(allBuyer, HttpStatus.OK);
        }

    }

    @GetMapping(value = "/getBuyerById")
    public ResponseEntity getBuyerById(int id){
        Buyer buyer = repository.findBuyerById(id);
        return ResponseEntity.ok(buyer);
    }

    @PutMapping(value = "/putBuyer")
    public ResponseEntity putBuyer(Buyer buyer){
        Buyer finderBuyer = repository.findBuyerById(buyer.getId());
        if (finderBuyer != null){
            finderBuyer.setId(buyer.getId());
            finderBuyer.setFamily(buyer.getFamily());
            finderBuyer.setDistrict(buyer.getDistrict());
            finderBuyer.setDiscountPercent(buyer.getDiscountPercent());
            repository.save(finderBuyer);
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(value = "/getDistinctBuyerDistrict")
    public ResponseEntity<?> getDistinctBuyerDistrict(){
        List<Buyer> distinctBooks = repository.queryDistinctByBuyerDistrict();
        return new ResponseEntity<>(distinctBooks, HttpStatus.OK);
    }

    @GetMapping(value = "/getBuyerByDistrict")
    public ResponseEntity<?> getBuyerByDistrict(){
            List<Buyer> allByDistrict = repository.findAllByDistrict("Нижегородский");
        return new ResponseEntity<>(allByDistrict, HttpStatus.OK);
    }

}
