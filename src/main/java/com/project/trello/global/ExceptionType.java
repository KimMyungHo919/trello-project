package com.project.trello.global;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionType {

    EXIST_USER_ROLE(HttpStatus.BAD_REQUEST, "해당하는 이름의 권한을 찾을 수 없습니다."),
    EXIST_USER(HttpStatus.BAD_REQUEST, "동일한 email 의 사용자가 존재합니다."),
    PASSWORD_NOT_CORRECT(HttpStatus.BAD_REQUEST,  "비밀번호가 일치하지 않습니다."),
    PASSWORD_SAME(HttpStatus.BAD_REQUEST, "기존의 비밀번호와 일치합니다."),
    DELETED_USER(HttpStatus.BAD_REQUEST, "이미 삭제된 유저입니다."),
    USER_NOT_MATCH(HttpStatus.BAD_REQUEST, "잘못된 유저의 정보에 접근하고 있습니다."),
    FILE_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당 파일을 찾을 수 없습니다."),
    BOARD_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당 게시물을 찾을 수 없습니다."),
    COMMENT_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당 댓글을 찾을 수 없습니다."),
    BAD_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호는 영문자, 숫자, 특수문자를 포함하며 8자 이상이어야 합니다."),
    NOT_LOGIN(HttpStatus.UNAUTHORIZED, "로그인을 해주세요."),
    ALREADY_LOGIN(HttpStatus.UNAUTHORIZED, "이미 로그인한 사용자입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,  "해당 유저의 정보를 찾을 수 없습니다."),
    LIKE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 좋아요 정보를 찾을 수 없습니다."),
    FRIEND_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 친구를 찾을 수 없습니다."),
    FAIL_FILE_UPLOADED(HttpStatus.INTERNAL_SERVER_ERROR,  "해당 파일 업로드에 실패하였습니다. 잘못된 형식의 확장자입니다."),
    FAIL_FILE_DOWNLOADED(HttpStatus.INTERNAL_SERVER_ERROR,  "해당 파일 다운로드에 실패하였습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    ExceptionType(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
