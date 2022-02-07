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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String serviceNum;

    private LocalDate startDay;

    private LocalDate endDay;

    private LocalDate serviceDay;

    private String rank;

    private String militaryUnit;

    @OneToOne(mappedBy = "userServiceInfo")
    private User user;


}
