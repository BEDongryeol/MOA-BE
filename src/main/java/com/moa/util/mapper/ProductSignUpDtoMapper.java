package com.moa.util.mapper;

import com.moa.finance.dto.response.ProductSignUpRes;
import com.moa.finance.vo.finance.RegistrationManagement;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductSignUpDtoMapper {

    ProductSignUpDtoMapper INSTANCE = Mappers.getMapper(ProductSignUpDtoMapper.class);

    default ProductSignUpRes toRes(RegistrationManagement register) {
        ProductSignUpRes response = new ProductSignUpRes();
        response.setBankName(register.getBankSavingProducts().getBank().getBankName());
        response.setAccountNumber(response.getAccountNumber());
        response.setProductName(register.getBankSavingProducts().getProductName());
        response.setSavingType(register.getRequest().getSavingType());
        response.setSubscriptionPeriod(register.getRequest().getSubscriptionPeriod());
        response.setPayment(register.getRequest().getPayment());
        response.setStatus(register.getStatus());
        return response;
    }
}
