package com.example.hw7.repository;
import com.example.hw7.model.Books;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository <Books, Integer>{

    List <Books> findAll ();

    Books findBooksById(int id);

    @Query( value = "SELECT distinct books.book_name\n" +
            "from books\n" +
            "group by books.book_name",
            nativeQuery = true)
    List<String> queryDistinctByBookName();

    @Query( value = "SELECT distinct books.cost\n" +
            "from books\n" +
            "group by books.cost",
            nativeQuery = true)
    List<Integer> queryDistinctByBookCost();

    List<Books> findBooksByCostIsGreaterThanOrBookNameIsLikeOrBookNameIsStartingWithOrBookNameIsEndingWithOrderByCostDesc(int cost, String bookName, String bookName2, String bookName3);

}
