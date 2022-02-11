package com.moa.challenge.moachallenge.dto;

import com.moa.challenge.moachallenge.vo.MoaChallenge;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
public class MoaChallengeResponseDto {

    private String challengeName;               // 챌린지명
    private String mainChallengeUrl;            // 메인챌린지 이미지(URL)
    private String firstDetailChallengeUrl;     // 상세챌린지 이미지(URL)1
    private String secondDetailChallengeUrl;    // 상세챌린지 이미지(URL)2
    private String firstDetailChallengeName;    // 상세챌린지 이름1
    private String secondDetailChallengeName;   // 상세챌린지 이름2

    @DateTimeFormat(pattern = "y-MM-dd HH:mm:ss")
    private LocalDateTime eDate;                // 마감시간

    private Integer challengeCount;             // 참석자수 (MyChallenge에서 받아야됨)
    private Integer challengeKeySum;            // 참석자들이 투표한 총 배팅 수 (MyChallenge에서 받아야됨)


    public MoaChallengeResponseDto(MoaChallenge entity) {
        this.challengeName = entity.getChallengeName();
        this.mainChallengeUrl = entity.getMainChallengeUrl();
        this.firstDetailChallengeUrl = entity.getFirstDetailChallengeUrl();
        this.secondDetailChallengeUrl = entity.getSecondDetailChallengeUrl();
        this.firstDetailChallengeName = entity.getFirstDetailChallengeName();
        this.secondDetailChallengeName = entity.getSecondDetailChallengeName();
        this.eDate = entity.getEDate();
        this.challengeCount = entity.getChallengeCount();
        this.challengeKeySum = entity.getChallengeKeySum();
    }
}
