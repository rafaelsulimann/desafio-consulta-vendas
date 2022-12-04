package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleMinProjection;
import com.devsuperior.dsmeta.projections.SaleSumaryProjection;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(nativeQuery = true, value = "SELECT tb_seller.name AS sellerName, SUM(tb_sales.amount) AS TOTAL "
    + "FROM tb_sales "
    + "INNER JOIN tb_seller ON tb_sales.seller_id = tb_seller.id "
    + "WHERE tb_sales.date BETWEEN :minDate AND :maxDate "
    + "GROUP BY tb_seller.name "
    + "ORDER BY tb_seller.name ASC")
    List<SaleSumaryProjection> searchSumary(@Param("minDate")LocalDate minDate, @Param("maxDate") LocalDate maxDate);

    @Query(nativeQuery = true, value = "SELECT tb_sales.id, tb_sales.date, tb_sales.amount, tb_seller.name AS sellerName "
    + "FROM tb_sales "
    + "INNER JOIN tb_seller ON tb_sales.seller_id = tb_seller.id "
    + "WHERE tb_sales.date BETWEEN :minDate AND :maxDate "
    + "AND UPPER(tb_seller.name) LIKE CONCAT('%', UPPER(:name), '%')")
    Page<SaleMinProjection> searchReport(@Param("minDate")LocalDate minDate, @Param("maxDate")LocalDate maxDate, @Param("name") String name, Pageable pageable);

}
