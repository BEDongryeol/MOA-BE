package com.moa.finance.repository;

import com.moa.finance.vo.dummy.SavingProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavingProductsRepository extends JpaRepository<SavingProducts, Long> {

    @Query(value = "select s from SavingProducts s " +
                   "order by s.highestInterest desc")
    List<SavingProducts> findProductsByHighest();

    @Query(value = "select s from SavingProducts s " +
                   "order by s.basicInterest desc")
    List<SavingProducts> findProductsByBasic();
}
