package com.moa.user.vo;

import com.moa.challenge.mychallenge.vo.MyChallenge;
import lombok.*;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
// @Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)    //mysql로 바꾸면 identity로
    private Long id;                        //

    private String loginId;                 //

    private String pw;                      //

    private String name;                    //

    private String birthDate;               //

    private String phoneNum;                //

//    @OneToMany(mappedBy = "user")           //주인은 account가 되고, ,account에서 JoinColumn을 해준다.
//    private List<Account> account = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "userServiceInfo_Id")    //군인정보와 1:1연결
    private UserServiceInfo userService;        //UserService class생성하고 entity 추가하기, 그리고 maaped by


    private Integer key;                    //


//    @OneToMany(mappedBy = "user")           //주인은 userGoal가 되고, ,userGoal에서 JoinColumn을 해준다.
//    private List<UserGoal> userGoal = new ArrayList<>();


    @OneToMany                              //주인은 myChallange가 되고, ,myChallange에서 JoinColumn을 해준다.
    @JoinColumn(name = "mychallange_id")
    private List<MyChallenge> mychallenge = new ArrayList<>();


}


// 내 보관함(나의 리워드) 이건 지금 안하기루.

