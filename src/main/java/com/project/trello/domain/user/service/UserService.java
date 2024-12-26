package com.project.trello.domain.user.service;

import com.project.trello.domain.config.PasswordEncoder;
import com.project.trello.domain.user.dto.UserRequestDto;
import com.project.trello.domain.user.entity.User;
import com.project.trello.domain.user.repository.UserRepository;
import com.project.trello.global.CustomException;
import com.project.trello.global.ExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
}
