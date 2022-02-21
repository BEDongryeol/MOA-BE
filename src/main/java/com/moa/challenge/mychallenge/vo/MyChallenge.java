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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// 내가 참여중인 챌린지를 보기 위한 db

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Builder
public class MyChallenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                // id

    // 유저 아이디 (User의 id값을 가져온다.) , myChallengeId와 userId는 다:1

//    @ManyToOne
//    @JoinColumn(name="mochallenge_id")
//    private MoaChallenge moaChallenge;

    private Integer myChallengeKey;                 // 배팅한 열쇠
    private boolean mySelected;                     // 선택지

    // 사용자가 배팅 성공,취소,수정할때 그때 시간을 서버쪽에서 실시간으로 데이터 생성해서 저장해야됨 그 후 만료시간과 비교해서 성공여부 확인
    private LocalDateTime myChallengeBetTimer;

    // 배팅 수정할때 사용
    public void update(Integer myChallengeKey, boolean mySelected, LocalDateTime myChallengeBetTimer){
        this.myChallengeKey = myChallengeKey;
        this.mySelected = mySelected;
        this.myChallengeBetTimer = myChallengeBetTimer;
    }

    // 배팅 참여할때 첫번째 선택지에 배팅한 숫자

}