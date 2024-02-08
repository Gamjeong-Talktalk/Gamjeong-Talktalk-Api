package com.addi.guardian.dto.response;

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
public class GetMembersResponse {

    private Long id;
    private String name;
    private String phoneNumber;
    private Gender gender;
    private LocalDate birthDay;
    private String address;
    private BigDecimal depressionIndex;


    public static GetMembersResponse toResponse(Member member){
        return GetMembersResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .phoneNumber(member.getPhoneNumber())
                .gender(member.getGender())
                .birthDay(member.getBirthDay())
                .address(member.getAddress())
                .depressionIndex(member.getDepressionIndex())
                .build();
    }

}
