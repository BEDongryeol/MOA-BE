package com.moa.challenge.mychallenge.vo;

import com.moa.challenge.moachallenge.vo.MoaChallenge;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

// 내가 참여중인 만료전 챌린지를 보기 위한 db
// 동시에 Moa측 db에 현재 참여중인 인원수도 보내주기 위해서 뭔가 해야됨!!

@NoArgsConstructor
@Data
@Entity
@DynamicUpdate
public class MyChallenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int myChallengeId;                      // id
    // 유저 아이디 (UserDB의 id값을 가져온다.) , myChallengeId와 userId는 1:1

    // 챌린지 번호 (MoaChallengeDB의 id값을 가져온다) , 다:1의 개념으로 설계
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="myChallengeId")
    private MoaChallenge moaChallenge;

    private Integer mychallengeKey;                 // 열쇠
    private boolean mySelected;                     // 선택지

    public MyChallenge(int mychallengeKey, boolean mySelected) {
        this.mychallengeKey = mychallengeKey;
        this.mySelected = mySelected;
    }

}
