package com.addi.conversation.exception;

import com.addi.global.exception.error.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NOT_IMPLEMENTED;

@Getter
@RequiredArgsConstructor
public enum ConversationError implements ErrorCode {
	EMPTY_FILE("파일이 없습니다.", NOT_FOUND, "C_001"),
	EMPTY_VOICE("녹음된 음성이 없습니다.", NOT_FOUND,"C_002"),

	NOT_EXIST_CONVERSATION_TODAY("해당 유저의 당일 저장된 대화 데이터가 존재하지 않습니다.", NOT_FOUND, "C_003"),
	NOT_EXIST_CONVERSATION_WEEK("해당 유저의 일주일간 저장된 대화 데이터가 존재하지 않습니다.", NOT_FOUND, "C_004"),
	NOT_READY_EMOTION_SERVER("아직 EMOTION API가 로딩 되지 않았습니다. 잠시 후 다시 시도해 주세요.", NOT_IMPLEMENTED,"C_005");

	private final String message;
	private final HttpStatus status;
	private final String code;
}
