package com.moa.finance.controller;

import com.moa.finance.repository.dummy.BankSavingProductsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/saving")
public class SavingController {

    private final BankSavingProductsRepository bankSavingProductsRepository;

    @GetMapping("/products/asc")
    public void savingProductGetByAsc(){

    }

    @GetMapping("/products/desc")
    public void savingProductGetByDesc(){

    }

}
