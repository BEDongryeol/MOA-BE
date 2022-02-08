package com.moa.finance.repository.dummy;

import com.moa.finance.vo.dummy.BankSavingProducts;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankSavingProductsRepository extends JpaRepository<BankSavingProducts, Long> {

    // TODO. user가 가지고 있는 적금 상품은 제외한 상품만 보여주기
    // 사용자가 가입한 군적금상품 조회

    @Query(value = "select b from BankSavingProducts b " +
                   "where b.bank.id not in (:bankIds)")
    List<BankSavingProducts> findSavingProducts(@Param("bankIds") List<Long> bankIds);


    // 최고금리순 데이터 조회
    @Query(value = "select s from BankSavingProducts s " +
                   "order by s.highestInterest desc")
    List<BankSavingProducts> findProductsByHighest();

    // 일반금리순 데이터 조회
    @Query(value = "select s from BankSavingProducts s " +
            "order by s.basicInterest desc")
    List<BankSavingProducts> findProductsByBasic();

}
