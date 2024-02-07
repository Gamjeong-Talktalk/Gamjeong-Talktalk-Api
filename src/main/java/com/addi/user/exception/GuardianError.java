package com.addi.user.exception;

import com.addi.global.exception.error.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@RequiredArgsConstructor
public enum GuardianError implements ErrorCode {
	NOT_SIGN_UP("회원 가입된 기기가 아닙니다.", NOT_FOUND, "G_001"),
	ALREADY_SIGN_UP("이미 회원가입된 유저입니다.", BAD_REQUEST, "G_002"),
	NOT_EXIST_GUARDIAN("존재하지 않는 유저입니다.", NOT_FOUND,"G_003");

	private final String message;
	private final HttpStatus status;
	private final String code;
}
