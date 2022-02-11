package com.moa.challenge.mychallenge.dto;

import com.moa.challenge.moachallenge.vo.MoaChallenge;
import com.moa.challenge.mychallenge.vo.MyChallenge;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MyChallengeBetAttendDto {

    private Integer myChallengeKey;                 // 배팅한 열쇠
    private boolean mySelected;                     // 선택지

    // 사용자가 배팅 할때 시간을 서버쪽에서 실시간으로 데이터 생성해서 저장해야됨 그 후 모아챌린지에 있는 만료시간과 비교해서 성공여부 확인
    @DateTimeFormat(pattern = "y-MM-dd HH:mm:ss")
    private LocalDateTime myChallengeBetTimer;

    @Builder
    public MyChallengeBetAttendDto(Integer myChallengeKey, boolean mySelected, LocalDateTime myChallengeBetTimer) {
        this.myChallengeKey = myChallengeKey;
        this.mySelected = mySelected;
        this.myChallengeBetTimer = myChallengeBetTimer;
    }

    // request dto로 받은 myChallenge 객체를 entity 화 하여 저장하는 용도
    public MyChallenge toMyChallengeEntity() {
        return MyChallenge
                .builder()
                .myChallengeKey(myChallengeKey)
                .myChallengeBetTimer(myChallengeBetTimer)
                .build();
    }

    // 조건에 따라 moaChallenge 객체를 entity 화 하여 저장하는 용도 (왼쪽을 선택한 키값에 대한 MoaChallenge entity에 저장)
    public MoaChallenge toMoaChallengeFirstKeyEntity() {
        return MoaChallenge
                .builder()
                .firstbetKey(myChallengeKey)
                .build();
    }

    // 조건에 따라 moaChallenge 객체를 entity 화 하여 저장하는 용도
    public MoaChallenge toMoaChallengeSecondKeyEntity() {
        return MoaChallenge
                .builder()
                .firstbetKey(myChallengeKey)
                .build();
    }
}
