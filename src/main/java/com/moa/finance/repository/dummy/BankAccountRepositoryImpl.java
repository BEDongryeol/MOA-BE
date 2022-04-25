package com.moa.finance.repository.dummy;

import com.moa.finance.dto.searchCondition.AccountSearchCondition;
import com.moa.finance.vo.dummy.BankAccount;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Supplier;

import static com.moa.finance.vo.dummy.QBankAccount.bankAccount;

public class BankAccountRepositoryImpl implements BankAccountRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public BankAccountRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<BankAccount> getAccountsByQuerydsl(AccountSearchCondition condition) {
        return queryFactory
                .selectFrom(bankAccount)
                .where(
                        ownerEq(condition.getOwner()),
                        birthDateEq(condition.getBirthDate()),
                        productNameLike(condition.getProductName1(), condition.getProductName1()))
                .fetch();
    }

    private BooleanBuilder ownerEq(String owner) {
        return nullSafeEqualBuilder(() -> bankAccount.account.owner.eq(owner));
    }

    private BooleanBuilder productNameLike(String productName1, String productName2) {
        return nullSafeCompareBuilder(
                () -> bankAccount.account.productName.contains(productName1)
                        .or(bankAccount.account.productName.contains(productName2)));
    }

    private static BooleanBuilder birthDateEq(LocalDate birthDate) {
        return nullSafeEqualBuilder(() -> bankAccount.account.birthDate.eq(birthDate));
    }

    private static BooleanBuilder nullSafeEqualBuilder(Supplier<BooleanExpression> f) {
        try {
            return new BooleanBuilder(f.get());
        } catch (IllegalArgumentException e) {
            return new BooleanBuilder();
        }
    }

    private static BooleanBuilder nullSafeCompareBuilder(Supplier<BooleanExpression> f) {
        try {
            return new BooleanBuilder(f.get());
        } catch (NullPointerException e) {
            return new BooleanBuilder();
        }
    }
}
