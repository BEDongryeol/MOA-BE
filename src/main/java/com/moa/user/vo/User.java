package com.moa.user.vo;

import com.moa.finance.vo.finance.UserAccount;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = "userAccount")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate birthDate;

    @OneToMany(mappedBy = "user")
    private List<UserAccount> userAccount = new ArrayList<>();
}
