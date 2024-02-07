package com.addi.culturalProgram.exception;


import com.addi.global.exception.error.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@RequiredArgsConstructor
public enum CulturalProgramError implements ErrorCode {
    NOT_EXIST_PROGRAM("등록된 프로그램이 없습니다.", NOT_FOUND, "E_001");

    private final String message;
    private final HttpStatus status;
    private final String code;
}
