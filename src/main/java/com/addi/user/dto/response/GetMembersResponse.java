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
public class GetMembersResponse {

    private Long id;
    private String name;
    private String phoneNumber;
    private Gender gender;
    private int age;
    private String address;
    private BigDecimal depressionIndex;


    public static GetMembersResponse toResponse(Member member){
        return GetMembersResponse.builder()
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
