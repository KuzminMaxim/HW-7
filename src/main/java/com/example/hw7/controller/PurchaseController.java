package com.example.hw7.controller;

import com.example.hw7.model.Purchase;
import com.example.hw7.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
public class PurchaseController {

    @Autowired
    PurchaseRepository repository;

    @DeleteMapping(value = "/deletePurchase")
    public ResponseEntity deletePurchase(Purchase purchase){
        repository.delete(purchase);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping(value = "/patchPurchase")
    public ResponseEntity patchPurchase(Purchase purchase){
        Purchase finderPurchase = repository.findPurchaseByOrderNumber(purchase.getOrderNumber());
        if (finderPurchase != null){
            finderPurchase.setOrderNumber(purchase.getOrderNumber());
            finderPurchase.setAmount(purchase.getAmount());
            finderPurchase.setBook(purchase.getBook());
            finderPurchase.setBuyer(purchase.getBuyer());
            finderPurchase.setDate(purchase.getDate());
            finderPurchase.setQuantity(purchase.getQuantity());
            finderPurchase.setSeller(purchase.getSeller());
            repository.save(finderPurchase);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping(value = "/postPurchase")
    public ResponseEntity postPurchase(Purchase buyer){
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        buyer.setDate(timestamp.toString());
        repository.save(buyer);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/getAllPurchase")
    public ResponseEntity<?> getAllPurchase(){
        List<Purchase> allPurchase = repository.findAll();

        if (allPurchase == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(allPurchase, HttpStatus.OK);
        }

    }

    @GetMapping(value = "/getPurchaseById")
    public ResponseEntity getPurchaseById(int orderNumber){
        Purchase purchase = repository.findPurchaseByOrderNumber(orderNumber);
        return ResponseEntity.ok(purchase);
    }

    @PutMapping(value = "/putPurchase")
    public ResponseEntity putPurchase(Purchase purchase){
        Purchase finderPurchase = repository.findPurchaseByOrderNumber(purchase.getOrderNumber());
        if (finderPurchase != null){
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            finderPurchase.setOrderNumber(purchase.getOrderNumber());
            finderPurchase.setAmount(purchase.getAmount());
            finderPurchase.setBook(purchase.getBook());
            finderPurchase.setBuyer(purchase.getBuyer());
            finderPurchase.setDate(timestamp.toString());
            finderPurchase.setQuantity(purchase.getQuantity());
            finderPurchase.setSeller(purchase.getSeller());
            repository.save(finderPurchase);
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(value = "/getDistinctPurchaseDate")
    public ResponseEntity<?> getDistinctPurchaseDate(){
        List<String> purchaseDate = repository.queryDistinctByPurchaseDate();
        return new ResponseEntity<>(purchaseDate, HttpStatus.OK);
    }

    @GetMapping(value = "/getBuyerFamilyAndStoreName")
    public ResponseEntity<?> getBuyerFamilyAndStoreName(){
        List<Object> familyAndStoreName = repository.findBuyerFamilyAndStoreName();
        return new ResponseEntity<>(familyAndStoreName, HttpStatus.OK);
    }

    @GetMapping(value = "/getDateFamilyDiscountBookQuantity")
    public ResponseEntity<?> getDateFamilyDiscountBookQuantity(){
        List<Object> dateFamilyDiscountBookQuantity = repository.findDateFamilyDiscountBookQuantity();
        return new ResponseEntity<>(dateFamilyDiscountBookQuantity, HttpStatus.OK);
    }

    @GetMapping(value = "/getOrderFamilyDateWhereCost")
    public ResponseEntity<?> getOrderFamilyDateWhereCost(){
        List<Object> orderFamilyDateWhereCost = repository.findOrderFamilyDateWhereCost();
        return new ResponseEntity<>(orderFamilyDateWhereCost, HttpStatus.OK);
    }

    @GetMapping(value = "/getPurchaseByDate")
    public ResponseEntity<?> getPurchaseByDate(){
        List<Object> purchaseByDate = repository.findPurchaseByDate();
        return new ResponseEntity<>(purchaseByDate, HttpStatus.OK);
    }

    @GetMapping(value = "/getStoreWhereDiscountBetween")
    public ResponseEntity<?> getStoreWhereDiscountBetween(){
        List<Object> storeWhereDiscountBetween = repository.findStoreWhereDiscountBetween();
        return new ResponseEntity<>(storeWhereDiscountBetween, HttpStatus.OK);
    }

    @GetMapping(value = "/getAllAboutBooksWhichBuyAtWarehouse")
    public ResponseEntity<?> getAllAboutBooksWhichBuyAtWarehouse(){
        List<Object> storeWhereDiscountBetween = repository.findAllAboutBooksWhichBuyAtWarehouse();
        return new ResponseEntity<>(storeWhereDiscountBetween, HttpStatus.OK);
    }

}
