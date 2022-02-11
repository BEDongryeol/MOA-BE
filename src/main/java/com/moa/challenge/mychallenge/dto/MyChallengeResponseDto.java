package com.moa.challenge.mychallenge.dto;

import com.moa.challenge.moachallenge.vo.MoaChallenge;
import com.moa.challenge.mychallenge.vo.MyChallenge;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MyChallengeResponseDto {
    private Long id;
    private MoaChallenge moachallenge;

    private Integer myChallengeKey;
    private boolean mySelected;

    // 사용자가 배팅 성공,취소,수정할때 그때 시간을 서버쪽에서 실시간으로 데이터 생성해서 저장해야됨 그 후 만료시간과 비교해서 성공여부 확인
    private LocalDateTime myChallengeBetTimer;

    // repopsitory -> entity -> dto
    public MyChallengeResponseDto(Long id, MoaChallenge moachallenge, Integer myChallengeKey, boolean mySelected, LocalDateTime myChallengeBetTimer) {
        this.id = id;
        this.moachallenge = moachallenge;
        this.myChallengeKey = myChallengeKey;
        this.mySelected = mySelected;
        this.myChallengeBetTimer = myChallengeBetTimer;
    }


    public MyChallengeResponseDto(MyChallenge entity) {
    }
}
