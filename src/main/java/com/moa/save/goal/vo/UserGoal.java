package com.moa.save.goal.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class UserGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int goalId;                                     // id
    private String goalName;                                // 목표명
    private String savingMode;                              // 저축방법 ("군적금", "자유목표", "비상금")
    private BigDecimal presentAmount;                       // 현재금액
    private BigDecimal goalAmount;                          // 목표금액
    private SimpleDateFormat sDate;                         // 시작일 (만기일 계산시 필요)
    private SimpleDateFormat eDate;                         // 종료일 (만기일 계산시 필요)
    private String depositMethod;                           // 납입형태(자동이체, 자유입금)
    @Builder.Default
    private String limitAmount = "없음";                     // 이체방식(매월 10일, 매주 월요일, 매일, 자유입금일땐 없음)
    @Builder.Default
    private BigDecimal monthPayAmount = BigDecimal.ZERO;    // 월별납입액 , 자유입금일때는 없으므로 0으로 초기값 설정
    private String category;                                // 카테고리(예외적으로 군적금 = '군적금', 비상금 = '비상금')
    @Builder.Default
    private String goalState ="진행";                        // 진행상태 (진행, 완료) , 완료의 경우 프론트가 만기상태 됬을때를 알고 있어서 그때 맞춰서 "완료" 받으면 될듯

}
