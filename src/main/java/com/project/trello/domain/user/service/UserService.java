package com.project.trello.domain.user.service;

import com.project.trello.domain.config.PasswordEncoder;
import com.project.trello.domain.user.dto.UserRequestDto;
import com.project.trello.domain.user.entity.User;
import com.project.trello.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User join(UserRequestDto dto) {
        User user = new User(
                dto.getEmail(),
                passwordEncoder.encode(dto.getPassword()),
                dto.getRole()
        );
        userRepository.save(user);

        return user;
    }
}
