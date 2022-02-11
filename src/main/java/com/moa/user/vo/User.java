package com.moa.user.vo;

import com.moa.challenge.mychallenge.vo.MyChallenge;
import com.moa.finance.dto.response.WithdrawalAccountRes;
import com.moa.finance.vo.finance.RegistrationManagement;
import com.moa.finance.vo.finance.UserAccount;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    //mysql로 바꾸면 identity로
    private Long id;                        //

    @Column(unique = true)
    private String loginId;                 //

    private String pw;                      //

    private String name;                    //

    private LocalDate birthDate;               //

    private String phoneNum;                //

    @OneToMany(mappedBy = "user")           //주인은 account가 되고, ,account에서 JoinColumn을 해준다.
    @ToString.Exclude
    private List<UserAccount> userAccount = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_SERVICE_INFO_ID")    //군인정보와 1:1연결
    private UserServiceInfo userServiceInfo;        //UserService class생성하고 entity 추가하기, 그리고 maaped by

    private Integer key;                    //

    private String auth;

//    @OneToMany(mappedBy = "user")           //주인은 userGoal가 되고, ,userGoal에서 JoinColumn을 해준다.
//    private List<UserGoal> userGoal = new ArrayList<>();

    @OneToMany                             //주인은 myChallange가 되고, ,myChallange에서 JoinColumn을 해준다.
    @JoinColumn(name = "mychallange_id")
    @ToString.Exclude
    private List<MyChallenge> myChallenge = new ArrayList<>();

    // 02-11 / 13:15 정인우 수정
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID")
    @ToString.Exclude
    private List<RegistrationManagement> registrationManagement = new ArrayList<>();



    //UserDetail 관련

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (String role : auth.split(",")) {
            roles.add(new SimpleGrantedAuthority(role));
        }
        return roles;
    }

    /// 사용자의 id를 반환 (unique한 값)
    @Override
    public String getUsername() {
        return loginId;
    }

    /// 사용자의 password를 반환
    @Override
    public String getPassword() {
        return pw;
    }

    /// 계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        // 만료되었는지 확인하는 로직
        return true; // true -> 만료되지 않았음
    }

    /// 계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        // 계정 잠금되었는지 확인하는 로직
        return true; // true -> 잠금되지 않았음
    }

    /// 패스워드의 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired() {
        // 패스워드가 만료되었는지 확인하는 로직
        return true; // true -> 만료되지 않았음
    }

    /// 계정 사용 가능 여부 반환
    @Override
    public boolean isEnabled() {
        // 계정이 사용 가능한지 확인하는 로직
        return true; // true -> 사용 가능
    }


// 내 보관함(나의 리워드) 이건 지금 안하기루.
}