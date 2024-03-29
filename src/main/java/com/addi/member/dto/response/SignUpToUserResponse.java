package com.addi.member.dto.response;


import com.addi.member.domain.Member;
import com.addi.member.domain.constants.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class SignUpToUserResponse {

    Long id;
    String identificationCode;
    String name;
    String phoneNumber;
    Gender gender;
    LocalDate birthDay;
    String address;
    BigDecimal depressionIndex;

    public SignUpToUserResponse toResponse(Member member){
        return SignUpToUserResponse.builder()
                .id(member.getId())
                .identificationCode(member.getIdentificationCode())
                .name(member.getName())
                .phoneNumber(member.getPhoneNumber())
                .gender(member.getGender())
                .birthDay(member.getBirthDay())
                .address(member.getAddress())
                .depressionIndex(member.getDepressionIndex())
                .build();
    }

}
