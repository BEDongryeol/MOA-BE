package com.moa.util.provider;

import com.moa.constant.RegistrationStatus;
import com.moa.finance.dto.request.ProductSignUpReq;
import com.moa.finance.vo.dummy.BankSavingProducts;
import com.moa.finance.vo.finance.RegistrationManagement;
import com.moa.user.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrationProvider {

    public RegistrationManagement of(User user, BankSavingProducts product, ProductSignUpReq request, String accountNumber) {
        RegistrationManagement register = new RegistrationManagement();
        register.setUser(user);
        register.setAccountNumber(accountNumber);
        register.setBankSavingProducts(product);
        register.setRequest(request);
        register.setStatus(RegistrationStatus.receipt.getStatus());
        register.addRegister(user);
        return register;
    }
}
