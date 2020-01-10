package com.example.hw7.controller;

import com.example.hw7.model.Store;
import com.example.hw7.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StoreController {

    @Autowired
    StoreRepository repository;

    @DeleteMapping(value = "/deleteStore")
    public ResponseEntity deleteStore(Store store){
        repository.delete(store);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping(value = "/patchStore")
    public ResponseEntity patchStore(Store store){
        Store finderStore = repository.findStoreById(store.getId());
        if (finderStore != null){
            finderStore.setId(store.getId());
            finderStore.setTitle(store.getTitle());
            finderStore.setDistrict(store.getDistrict());
            finderStore.setCommissionPercent(store.getCommissionPercent());
            repository.save(finderStore);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping(value = "/postStore")
    public ResponseEntity postStore(Store store){
        repository.save(store);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/getAllStore")
    public ResponseEntity<?> getAllStore(){
        List<Store> allStore = repository.findAll();

        if (allStore == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(allStore, HttpStatus.OK);
        }

    }

    @GetMapping(value = "/getStoreById")
    public ResponseEntity getStoreById(int id){
        Store store = repository.findStoreById(id);
        return ResponseEntity.ok(store);
    }

    @PutMapping(value = "/putStore")
    public ResponseEntity putStore(Store store){
        Store finderStore = repository.findStoreById(store.getId());
        if (finderStore != null){
            finderStore.setId(store.getId());
            finderStore.setTitle(store.getTitle());
            finderStore.setDistrict(store.getDistrict());
            finderStore.setCommissionPercent(store.getCommissionPercent());
            repository.save(finderStore);
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(value = "/getStoreByDistrict")
    public ResponseEntity<?> getStoreByDistrict(String district){
        List<Store> allByDistrict = repository.findAllByDistrict(district);
        return new ResponseEntity<>(allByDistrict, HttpStatus.OK);
    }

}
