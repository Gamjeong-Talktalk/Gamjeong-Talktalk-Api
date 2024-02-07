package com.addi.emotion.exception;

import com.addi.global.exception.error.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@RequiredArgsConstructor
public enum EmotionError implements ErrorCode {
	NOT_EXIST_EMOTION("해당 대화의 감정이 존재하지 않습니다.", NOT_FOUND, "E_001");

	private final String message;
	private final HttpStatus status;
	private final String code;
}
