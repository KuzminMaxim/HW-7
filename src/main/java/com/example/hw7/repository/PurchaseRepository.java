package com.example.hw7.repository;

import com.example.hw7.model.Purchase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PurchaseRepository extends CrudRepository <Purchase, Integer> {

    List<Purchase> findAll ();

    Purchase findPurchaseByOrderNumber(int orderNumber);

    @Query( value = "SELECT distinct purchase.order_date\n" +
            "from purchase\n" +
            "group by purchase.order_date",
            nativeQuery = true)
    List<String> queryDistinctByPurchaseDate();

    @Query(value = "select buyer.family, store.title \n" +
            "from purchase\n" +
            "join store on purchase.seller = store.id\n" +
            "join buyer on purchase.buyer = buyer.id",
            nativeQuery = true)
    List<Object> findBuyerFamilyAndStoreName();

    @Query(value = "select purchase.order_date, buyer.family,buyer.discount_percent , books.book_name, books.quantity \n" +
            "from purchase\n" +
            "join store on purchase.seller = store.id\n" +
            "join buyer on purchase.buyer = buyer.id\n" +
            "join books on purchase.book = books.id",
            nativeQuery = true)
    List<Object> findDateFamilyDiscountBookQuantity();

    @Query(value = "select purchase.order_number, buyer.family, purchase.order_date\n" +
            "from purchase\n" +
            "join store on purchase.seller = store.id\n" +
            "join buyer on purchase.buyer = buyer.id\n" +
            "join books on purchase.book = books.id\n" +
            "where (books.cost * purchase.quantity) > 60000",
            nativeQuery = true)
    List<Object> findOrderFamilyDateWhereCost();

    @Query(value = "select buyer.family, store.district, purchase.order_date\n" +
            "from purchase\n" +
            "join store on purchase.seller = store.id\n" +
            "join buyer on purchase.buyer = buyer.id\n" +
            "join books on purchase.book = books.id\n" +
            "where purchase.order_date > '2019-03'\n" +
            "order by purchase.order_date",
            nativeQuery = true)
    List<Object> findPurchaseByDate();

    @Query(value = "select distinct store.title\n" +
            "from purchase\n" +
            "join store on purchase.seller = store.id\n" +
            "join buyer on purchase.buyer = buyer.id\n" +
            "join books on purchase.book = books.id\n" +
            "where store.district != 'Автозаводский'\n" +
            "and store.district != 'автозаводский'\n" +
            "and buyer.discount_percent between 10 and 15",
            nativeQuery = true)
    List<Object> findStoreWhereDiscountBetween();

    @Query(value = "select books.book_name, books.warehouse, books.quantity, books.cost\n" +
            "from purchase\n" +
            "join store on purchase.seller = store.id\n" +
            "join buyer on purchase.buyer = buyer.id\n" +
            "join books on purchase.book = books.id\n" +
            "where store.district = books.warehouse\n" +
            "and books.quantity > 10\n" +
            "order by books.cost",
            nativeQuery = true)
    List<Object> findAllAboutBooksWhichBuyAtWarehouse();

}
