package com.moa.challenge.moachallenge.vo;

import com.moa.challenge.mychallenge.vo.MyChallenge;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.text.SimpleDateFormat;
import java.util.List;

// 챌린지 메인페이지에 등록된 챌린지를 보여주기 위한 DB 생성, 챌린지탭 눌렀을떄 여러개의 챌린지가 나올 수 있다
// 만료가 되어 발표 됬을때 이중 참석자와 총배팅수를 프론트에 전달

@Data
@NoArgsConstructor
@Entity
@DynamicInsert
public class MoaChallenge {

    @Id
    @GeneratedValue
    private int moaChallengeId;                 // id
    private String challengeName;               // 챌린지명
    @OneToMany
    private List<MyChallenge> myChallenges;     // myChallenge 아이디와 1:다
    private String mainChallengeUrl;            // 메인챌린지 이미지(URL)
    private String firstDetailChallengeUrl;     // 상세챌린지 이미지(URL)1
    private String secondDetailChallengeUrl;    // 상세챌린지 이미지(URL)2
    private String challengeState;              // 챌린지 진행상태(완료, 진행)
    private SimpleDateFormat eDate;             // 마감시간
    @ColumnDefault("0")
    private Integer challengeCount;                 // 참석자수 (MyChallenge에서 받아야됨)
    @ColumnDefault("0")
    private Integer challengeKeySum;                // 참석자들이 투표한 총 배팅 수 (MyChallenge에서 받아야됨)
}
