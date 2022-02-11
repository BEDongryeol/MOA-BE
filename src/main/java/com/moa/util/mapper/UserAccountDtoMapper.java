package com.moa.util.mapper;

import com.moa.finance.dto.response.UserAccountRes;
import com.moa.finance.vo.finance.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface UserAccountDtoMapper {
    UserAccountDtoMapper INSTANCE = Mappers.getMapper(UserAccountDtoMapper.class);

    default UserAccountRes toRes(UserAccount userAccount) {
        if (userAccount.getAccountNickname().equals("")) userAccount.setAccountNickname(userAccount.getAccount().getProductName());

        return UserAccountRes.builder()
                .userAccountId(userAccount.getId())
                .bankImageUrl(userAccount.getBank().getBankImageUrl())
                .accountNickname(userAccount.getAccountNickname())
                .currentAmount(userAccount.getAccount().getCurrentAmount().longValue())
                .goalAmount(userAccount.getAccount().getGoalAmount().longValue())
                .category("군적금")
                .createdDate(userAccount.getAccount().getCreatedDate())
                .expirationDate(userAccount.getAccount().getExpirationDate())
                .build();
    }

    default List<UserAccountRes> toResList(List<UserAccount> userAccounts) {
        List<UserAccountRes> responses = new ArrayList<>();
        for (UserAccount userAccount : userAccounts) {
            responses.add(toRes(userAccount));
        }
        return responses;
    }
}
