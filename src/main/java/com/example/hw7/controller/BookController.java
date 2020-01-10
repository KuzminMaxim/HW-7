package com.example.hw7.controller;

import com.example.hw7.model.Books;
import com.example.hw7.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    BookRepository repository;

    @DeleteMapping(value = "/deleteBook")
    public ResponseEntity deleteBook(Books books){
        repository.delete(books);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping(value = "/patchBook")
    public ResponseEntity patchBook(Books books){
        Books finderBook = repository.findBooksById(books.getId());
        if (finderBook != null){
            finderBook.setId(books.getId());
            finderBook.setBookName(books.getBookName());
            finderBook.setCost(books.getCost());
            finderBook.setWarehouse(books.getWarehouse());
            finderBook.setQuantity(books.getQuantity());
            repository.save(finderBook);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping(value = "/postBook")
    public ResponseEntity postBook(Books books){
        repository.save(books);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/getAllBook")
    public ResponseEntity<?> getAllBook(){
        List <Books> allBooks = repository.findAll();

        if (allBooks == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
                return new ResponseEntity<>(allBooks, HttpStatus.OK);
        }

    }

    @GetMapping(value = "/getBookById")
    public ResponseEntity getBookById(int id){
        Books books = repository.findBooksById(id);
        System.out.println(books.getBookName());
        return ResponseEntity.ok(books);
    }

    @PutMapping(value = "/putBook")
    public ResponseEntity putBook(Books books){
        Books finderBook = repository.findBooksById(books.getId());
        if (finderBook != null){
            finderBook.setId(books.getId());
            finderBook.setBookName(books.getBookName());
            finderBook.setCost(books.getCost());
            finderBook.setWarehouse(books.getWarehouse());
            finderBook.setQuantity(books.getQuantity());
            repository.save(finderBook);
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(value = "/getDistinctBooksName")
    public ResponseEntity<?> getDistinctBooksName(){
        List<String> distinctBooks = repository.queryDistinctByBookName();
        return new ResponseEntity<>(distinctBooks, HttpStatus.OK);
    }

    @GetMapping(value = "/getDistinctBooksCost")
    public ResponseEntity<?> getDistinctBooksCost(){
        List<Integer> distinctBooks = repository.queryDistinctByBookCost();
        return new ResponseEntity<>(distinctBooks, HttpStatus.OK);
    }

    @GetMapping(value = "/getBooksByCostAfterAndBookNameIsLike")
    public ResponseEntity<?> getBooksByCostAfterAndBookNameIsLike(int cost){
        List<Books> books = repository
                .findBooksByCostIsGreaterThanOrBookNameIsLikeOrBookNameIsStartingWithOrBookNameIsEndingWithOrderByCostDesc
                (cost, "windows", "windows", "windows");
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

}
