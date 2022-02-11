package com.moa.util.mapper;

import com.moa.finance.dto.response.BankSavingProductsRes;
import com.moa.finance.vo.dummy.BankSavingProducts;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface SavingProductsMapper {

    SavingProductsMapper INSTANCE = Mappers.getMapper(SavingProductsMapper.class);

    default BankSavingProductsRes toDTO(BankSavingProducts product){
        BankSavingProductsRes res = new BankSavingProductsRes();
        res.setId(product.getId());
        res.setBankId(product.getBank().getId());
        res.setBankImageUrl(product.getBank().getBankImageUrl());
        res.setBankName(product.getBank().getBankName());
        res.setAmountExplanation(product.getAmountExplanation());
        res.setProductName(product.getProductName());
        res.setBasicInterest(product.getBasicInterest());
        res.setHighestInterest(product.getHighestInterest());
        res.setSubscriptionLimit(product.getSubscriptionLimit());
        res.setSubscriptionPeriod(product.getSubscriptionPeriod());

        return res;
    }

    default List<BankSavingProductsRes> toDtoList(List<BankSavingProducts> products) {
        List<BankSavingProductsRes> dtoList = new ArrayList<>();
        for (BankSavingProducts product : products) {
            dtoList.add(toDTO(product));
        }
        return dtoList;
    }
}
