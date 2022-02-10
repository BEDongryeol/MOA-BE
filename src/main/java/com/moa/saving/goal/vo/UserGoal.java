package com.moa.saving.goal.vo;

import com.moa.constant.AccountState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Builder
@Entity
@Table(name = "usergoal")
public class UserGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                        // id
    private String goalName;                                // 목표명(빈칸 들어오면 상품명이랑 동일하게하고 만약 설정이되면, 목표명 set)
    private String savingMode;                              // 저축방법 ("군적금", "목표", "비상금")
    private BigDecimal currentAmount;                       // 현재금액
    private BigDecimal goalAmount;                          // 목표금액
    private SimpleDateFormat sDate;                         // 시작일 (만기일 계산시 필요)
    private SimpleDateFormat eDate;                         // 종료일 (만기일 계산시 필요)
    private String depositMethod;                           // 납입형태(자동이체, 자유입금)
    @Builder.Default
    private String limitCycle = "";                         // 이체방식(매월 10일, 매주 월요일, 매일, 자유입금일땐 "")
    @Builder.Default
    private BigDecimal amountPerCycle = BigDecimal.ZERO;    // 월별납입액 , 자유입금일때는 없으므로 0으로 초기값 설정
    private String category;                                // 카테고리(예외적으로 군적금 = '군적금', 비상금 = '비상금')
    private String goalState;                               // 관련 요청이 들어왔을때 성공 실패 유무

    private String bankName;    // 은행이름
    private String productName; // 적금이름
    private String accountNumber; // 계좌번호
    private BigDecimal accountCurrentAmount; // 잔액
    private String backImageUrl;    // 은행 로고 이미지주소

    @Enumerated(value = EnumType.STRING)
    private AccountState accountState = AccountState.연동;

}
