package com.addi.user.dto.response;


import com.addi.user.domain.Member;
import com.addi.user.domain.constants.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class SignUpToUserResponse {

    Long id;
    String name;
    String phoneNumber;
    Gender gender;
    int age;
    String address;
    BigDecimal depressionIndex;

    public SignUpToUserResponse toResponse(Member member){
        return SignUpToUserResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .phoneNumber(member.getPhoneNumber())
                .gender(member.getGender())
                .age(member.getAge())
                .address(member.getAddress())
                .depressionIndex(member.getDepressionIndex())
                .build();
    }

}
