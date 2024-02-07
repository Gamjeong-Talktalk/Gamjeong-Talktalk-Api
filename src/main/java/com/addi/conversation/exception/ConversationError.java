package com.addi.conversation.exception;

import com.addi.global.exception.error.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@RequiredArgsConstructor
public enum ConversationError implements ErrorCode {
	EMPTY_FILE("파일이 없습니다.", NOT_FOUND, "C_001"),
	EMPTY_VOICE("녹음된 음성이 없습니다.", NOT_FOUND,"C_002"),

	NOT_EXIST_CONVERSATION("해당 유저의 저장된 음성데이터가 존재하지 않습니다.", NOT_FOUND, "C_003");

	private final String message;
	private final HttpStatus status;
	private final String code;
}
