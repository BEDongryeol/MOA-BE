package com.moa.challenge.moachallenge.vo;

import com.moa.challenge.mychallenge.vo.MyChallenge;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

// 챌린지 메인페이지에 등록된 챌린지를 보여주기 위한 DB 생성, 챌린지탭 눌렀을떄 여러개의 챌린지가 나올 수 있다
// 만료가 되어 발표 됬을때 이중 참석자와 총배팅수를 프론트에 전달

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "moachallenge")
@Builder
public class MoaChallenge {

    @Id
    @GeneratedValue
    private Long id;                             // id
    private String challengeName;               // 챌린지명

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "mychallenge_id")
    private List<MyChallenge> myChallenges = new ArrayList<>();     // myChallenge 아이디와 1:다

    private String mainChallengeUrl;            // 메인챌린지 이미지(URL)
    private String firstDetailChallengeUrl;     // 상세챌린지 이미지(URL)1
    private String secondDetailChallengeUrl;    // 상세챌린지 이미지(URL)2
    private String firstDetailChallengeName;    // 상세챌린지 이름1
    private String secondDetailChallengeName;   // 상세챌린지 이름2
    @Builder.Default
    private Integer firstbetKey = 0;            // 첫번째옵션선택한키개수
    @Builder.Default
    private Integer secondbetKey = 0;           // 두번째옵션선택한키개수
    private String challengeState;              // 챌린지 진행상태(완료, 진행)
    private SimpleDateFormat eDate;             // 마감시간
    private boolean winner;                     // 승리여부

    //private String moaChallengeBoxUrl;          // 메인 챌린지 탭에서 박스모양 있는 이미지

    //@Formula("(select count(*) from mychallenge myc where myc.id = id)")
    private Integer challengeCount;             // 참석자수 (MyChallenge에서 받아야됨)
    //@Formula(("(select sum(moac.firstbetKey + moac.secondbetKey) from moahallenge moac where myc.id = id"))
    private Integer challengeKeySum;            // 참석자들이 투표한 총 배팅 수 (MyChallenge에서 받아야됨)

}
