package com.moa.user.service;

import com.moa.finance.vo.finance.Account;
import com.moa.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.moa.user.vo.User;
import com.moa.user.vo.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;


    /**
     * Spring Security 필수 메소드 구현
     *
     * @param loginId 로그인아이디
     * @return UserDetails
     * @throws UsernameNotFoundException 유저가 없을 때 예외 발생
     */

    @Override
    public User loadUserByUsername(String loginId) throws UsernameNotFoundException {
        return userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new UsernameNotFoundException((loginId)));
    }
    public Long save(UserDto userDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userDto.setPw(encoder.encode(userDto.getPw()));

        return userRepository.save(User.builder()
                .loginId(userDto.getLoginId())
                .auth(userDto.getAuth())
                .birthDate(userDto.getBirthDate())
                .phoneNum(userDto.getPhoneNum())
                .pw(userDto.getPw()).build()).getId();
    }
}

//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//    //회원가입
//
//
//    //로그인
//    @Override
//    public UserDetails loadUserbyUserLoginId(String loginId) throws
//            User user = userRepository.findbyLoginId(loginId);
//            if (account == null) {
//            throw new UsernameNotFoundException(username);
//        }
//
//            return User.builder()
//                .username(user.getLoginId())
//                .password(user.getPw())
//                .roles(user.get())
//                .build();
//    }
//
//        public Account createNew(Account account) {
//            return userRepository.save(account);
//        }


