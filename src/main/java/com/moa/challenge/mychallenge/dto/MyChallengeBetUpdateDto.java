package com.moa.challenge.mychallenge.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MyChallengeBetUpdateDto {

    private Integer myChallengeKey;                 // 배팅한 열쇠
    private boolean mySelected;                     // 선택지

    // 사용자가 배팅 수정 할때 시간을 서버쪽에서 실시간으로 데이터 생성해서 저장해야됨 그 후 모아챌린지에 있는 만료시간과 비교해서 성공여부 확인
    @DateTimeFormat(pattern = "y-MM-dd HH:mm:ss")
    private LocalDateTime myChallengeBetTimer;

    @Builder
    public MyChallengeBetUpdateDto(Integer myChallengeKey, boolean mySelected, LocalDateTime myChallengeBetTimer) {
        this.myChallengeKey = myChallengeKey;
        this.mySelected = mySelected;
        this.myChallengeBetTimer = myChallengeBetTimer;
    }
}
