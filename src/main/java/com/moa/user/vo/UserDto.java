package com.moa.user.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserDto {
    private String loginId;
    private String pw;
    private LocalDate birthDate;
    private String phoneNum;

    private String auth;
}
