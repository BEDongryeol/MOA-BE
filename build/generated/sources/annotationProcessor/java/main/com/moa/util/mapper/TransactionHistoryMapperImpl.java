package com.moa.util.mapper;

import com.moa.finance.vo.dummy.BankTransactionHistory;
import com.moa.finance.vo.finance.UserTransactionHistory;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-08T12:15:07+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.1.jar, environment: Java 11.0.12 (Oracle Corporation)"
)
public class TransactionHistoryMapperImpl implements TransactionHistoryMapper {

    @Override
    public UserTransactionHistory toUser(BankTransactionHistory bank) {
        if ( bank == null ) {
            return null;
        }

        UserTransactionHistory userTransactionHistory = new UserTransactionHistory();

        return userTransactionHistory;
    }
}
