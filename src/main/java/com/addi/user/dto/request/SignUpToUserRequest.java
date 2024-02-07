package com.addi.user.dto.request;


import com.addi.user.domain.Guardian;
import com.addi.user.domain.Member;
import com.addi.user.domain.constants.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SignUpToUserRequest {
    String name;
    String phoneNumber;
    Gender gender;
    int age;
    String address;

    public Member toEntity(Guardian guardian){
        return Member.builder()
                .guardian(guardian)
                .name(name)
                .phoneNumber(phoneNumber)
                .gender(gender)
                .age(age)
                .address(address)
                .build();
    }

}
