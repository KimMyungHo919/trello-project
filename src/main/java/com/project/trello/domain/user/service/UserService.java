package com.project.trello.domain.user.service;

import com.project.trello.domain.config.PasswordEncoder;
import com.project.trello.domain.user.dto.UserLoginRequestDto;
import com.project.trello.domain.user.dto.UserRequestDto;
import com.project.trello.domain.user.dto.UserResponseDto;
import com.project.trello.domain.user.entity.User;
import com.project.trello.domain.user.repository.UserRepository;
import com.project.trello.global.customException.CustomException;
import com.project.trello.global.customException.ExceptionType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Transactional
    public User join(UserRequestDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new CustomException(ExceptionType.EXIST_USER);
        }

        User user = new User(
                dto.getEmail(),
                passwordEncoder.encode(dto.getPassword()),
                dto.getRole()
        );
        userRepository.save(user);

        return user;
    }

    // 로그인
    @Transactional
    public User login(UserLoginRequestDto dto) {
        User user = userRepository.findByEmail(dto.getEmail());

        if (user == null) {
            throw new CustomException(ExceptionType.USER_NOT_FOUND);
        }
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new CustomException(ExceptionType.PASSWORD_NOT_CORRECT);
        }

        return user;
    }
}
