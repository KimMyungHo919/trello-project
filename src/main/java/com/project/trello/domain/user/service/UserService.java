package com.project.trello.domain.user.service;

import com.project.trello.domain.user.dto.UserWorkspaceResponseDto;
import com.project.trello.global.config.PasswordEncoder;
import com.project.trello.domain.user.dto.UserDeleteRequestDto;
import com.project.trello.domain.user.dto.UserLoginRequestDto;
import com.project.trello.domain.user.dto.UserRequestDto;
import com.project.trello.domain.user.entity.User;
import com.project.trello.domain.user.repository.UserRepository;
import com.project.trello.global.customException.CustomException;
import com.project.trello.global.customException.ExceptionType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
        if (user.getStatus().getName().equals("INACTIVE")) {
            throw new CustomException(ExceptionType.DELETED_USER);
        }

        return user;
    }

    // 탈퇴
    @Transactional
    public void deleteUser(UserDeleteRequestDto dto, Long id) {
        User user = userRepository.findByEmail(dto.getEmail());
        if (!Objects.equals(id, user.getId())) {
            throw new CustomException(ExceptionType.USER_NOT_MATCH);
        }

        user.userDeleted();
    }

    // 자신이 속해있는 워크스페이스 정보 전체조회
    public List<UserWorkspaceResponseDto> findMembers(Long loginUserId) {
        User user = userRepository.findById(loginUserId).orElseThrow(() -> new CustomException(ExceptionType.USER_NOT_FOUND));

        return user.getMembers()
                .stream()
                .map(member -> new UserWorkspaceResponseDto(
                        member.getWorkspace().getId(),
                        member.getWorkspace().getTitle(),
                        member.getWorkspace().getSubTitle(),
                        member.getMemberRole().name()
                ))
                .toList();
    }
}
