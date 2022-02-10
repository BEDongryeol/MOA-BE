package com.moa.user.vo;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserServiceInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                        // 군인정보 고유아이디

    private String serviceNum;              // 군번

    private LocalDate startDay;             // 입대일

    private LocalDate endDay;               // 전연일

    private LocalDate serviceDay;           // 복무일

    private String rank;                    // 계급

    private String militaryUnit;            // 부대 정보

    @OneToOne
    @JoinColumn(name = "USER_ID")           // 군번으로 매핑해야 함
    private User user;




}
