package com.moa.challenge.mychallenge.vo;

import com.moa.challenge.moachallenge.vo.MoaChallenge;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.text.SimpleDateFormat;

// 내가 참여중인 만료전 챌린지를 보기 위한 db
// 동시에 Moa측 db에 현재 참여중인 인원수도 보내주기 위해서 뭔가 해야됨!!

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Builder
@Table(name = "mychallenge")
public class MyChallenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                // id
    // 유저 아이디 (UserDB의 id값을 가져온다.) , myChallengeId와 userId는 다:1

    private Integer myChallengeKey;                 // 배팅한 열쇠
    private boolean mySelected;                     // 선택지

    // 배팅참여 여부 -> 사용자가 배팅을했는지 안했는지 여부 , 전체 챌린지 목록에서 챌린지 상세 누르기 전에 이 값을 통해 확인, 처음에는 false로 세팅
    private boolean myChallengeAttend;

    // 사용자의 배팅 성공,취소,수정 여부를 모두 통일 , 처음에 false로 세팅 되어있어야함
    @Builder.Default
    private boolean myChallengeSucessState = false;

    // 사용자가 배팅 성공,취소,수정할때 그때 시간을 서버쪽에서 실시간으로 데이터 생성해서 저장해야됨 그 후 만료시간과 비교해서 성공여부 확인
    private SimpleDateFormat myChallengeBetTimer;

}