package com.addi.member.dto.request;


import com.addi.guardian.domain.Guardian;
import com.addi.member.domain.Member;
import com.addi.member.domain.constants.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignUpToUserRequest {
    String registrationCode;
    String name;
    String phoneNumber;
    Gender gender;
    LocalDate birthDay;
    String address;

    public Member toEntity(Guardian guardian){
        return Member.builder()
                .guardian(guardian)
                .name(name)
                .phoneNumber(phoneNumber)
                .gender(gender)
                .birthDay(birthDay)
                .address(address)
                .build();
    }
}
